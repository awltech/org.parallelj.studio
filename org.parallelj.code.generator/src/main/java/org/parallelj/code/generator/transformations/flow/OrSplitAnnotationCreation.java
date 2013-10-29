package org.parallelj.code.generator.transformations.flow;

import java.util.Iterator;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.javaxmi.core.annotations.JavaAnnotationHelper;

import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.Annotation;
import org.eclipse.gmt.modisco.java.AnnotationMemberValuePair;
import org.eclipse.gmt.modisco.java.ArrayInitializer;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;
import org.parallelj.code.generator.helpers.ParallelJModelHelper;
import org.parallelj.model.Link;
import org.parallelj.model.OutputCondition;
import org.parallelj.model.Procedure;

/**
 * This transformation allows to add the <code>@OrSplit</code> on each exit
 * method, and its parameters
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class OrSplitAnnotationCreation extends
		AbstractTransformation<Procedure> {

	public OrSplitAnnotationCreation(Procedure eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Procedure procedure = getEObject();
		AbstractMethodDeclaration declaration = (AbstractMethodDeclaration) context
				.get(procedure, "exit");

		for (Link outputLink : procedure.getOutputLinks()) {
			ArrayInitializer arrayInitializer = getOrSplitAnnotationInitializer(declaration);
			Annotation annotation = JavaAnnotationHelper.addAnnotation(
					arrayInitializer, "org.parallelj", "Link");

			JavaAnnotationHelper.addAnnotationParameter(annotation,
					"predicate", outputLink.getPredicate() != null ? outputLink
							.getPredicate().getName() : "");

			if (outputLink.getDestination() instanceof OutputCondition)
				JavaAnnotationHelper.addAnnotationParameter(annotation, "to",
						"end");
			else
				JavaAnnotationHelper.addAnnotationParameter(annotation, "to",
						ParallelJModelHelper.getConditionName(outputLink
								.getDestination()));
		}
		context.put(procedure, "exit", declaration);
	}

	private ArrayInitializer getOrSplitAnnotationInitializer(
			AbstractMethodDeclaration declaration) {
		Annotation annotation = null;
		for (Iterator<Annotation> iterator = declaration.getAnnotations()
				.iterator(); iterator.hasNext() && annotation == null;) {
			Annotation a = iterator.next();
			if (a.getType().getType().getName().endsWith("OrSplit"))
				annotation = a;
		}
		if (annotation == null)
			annotation = JavaAnnotationHelper.addAnnotation(declaration,
					"org.parallelj", "OrSplit");

		AnnotationMemberValuePair pair = null;
		if (annotation.getValues().size() == 0) {
			pair = JavaFactory.eINSTANCE.createAnnotationMemberValuePair();
			annotation.getValues().add(pair);
		} else
			pair = annotation.getValues().get(0);
		ArrayInitializer initializer = null;
		if (pair.getValue() == null
				|| !(pair.getValue() instanceof ArrayInitializer)) {
			initializer = JavaFactory.eINSTANCE.createArrayInitializer();
			pair.setValue(initializer);
			initializer.setOriginalCompilationUnit(declaration
					.getOriginalCompilationUnit());
		} else
			initializer = (ArrayInitializer) pair.getValue();
		return initializer;
	}
}
