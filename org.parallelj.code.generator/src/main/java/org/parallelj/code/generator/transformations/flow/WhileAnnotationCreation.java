package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.javaxmi.core.annotations.JavaAnnotationHelper;

import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.Annotation;
import org.parallelj.model.WhileLoop;

/**
 * This transformation allows to add the <code>@While</code> annotation on an
 * entry method, and its parameters
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class WhileAnnotationCreation extends AbstractTransformation<WhileLoop> {

	public WhileAnnotationCreation(WhileLoop eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		WhileLoop whileLoop = getEObject();
		AbstractMethodDeclaration declaration = (AbstractMethodDeclaration) context
				.get(whileLoop, "self");

		Annotation annotation = JavaAnnotationHelper.addAnnotation(declaration,
				"org.parallelj", "While");

		JavaAnnotationHelper.addAnnotationParameter(annotation, "value",
				whileLoop.getPredicate() != null ? whileLoop.getPredicate()
						.getName() : "");

		context.put(whileLoop, "self", declaration);
	}

}
