package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.javaxmi.core.annotations.JavaAnnotationHelper;

import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.Annotation;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;
import org.parallelj.model.Pipeline;

/**
 * This transformation allows to add the <code>@PipelineParameter</code> annotation and
 * the method parameter on an entry method. It also manages the parameters of
 * the annotation
 * 
 * @author a169104
 * @version 1.0
 * 
 */
public class PipelineParameterAnnotationCreation extends
		AbstractTransformation<Pipeline> {

	public PipelineParameterAnnotationCreation(Pipeline eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Pipeline pipeline = getEObject();
		AbstractMethodDeclaration declaration = (AbstractMethodDeclaration) context
				.get(pipeline, "entry");

		AbstractTypeDeclaration parent = (AbstractTypeDeclaration) context.get(
				getEObject().eContainer(), "self");

		SingleVariableDeclaration variable = JavaFactory.eINSTANCE
				.createSingleVariableDeclaration();
		variable.setMethodDeclaration(declaration);
		variable.setModifier(JavaFactory.eINSTANCE.createModifier());
		variable.setVarargs(false);

		TypeAccess parameterTypeAccess = JavaFactory.eINSTANCE
				.createTypeAccess();
		Type parameterType = JavaFactory.eINSTANCE.createPrimitiveType();
		parameterType.setName(pipeline.getIterable().getType());
		parameterTypeAccess.setType(parameterType);

		variable.setType(parameterTypeAccess);
		variable.setName(pipeline.getIterable().getName());

		Annotation annotation = JavaAnnotationHelper.addAnnotation(
				variable.getMethodDeclaration(), "org.parallelj", "PipelineParameter");

		JavaAnnotationHelper.addAnnotationParameter(annotation, "value",
				pipeline.getIterable() != null ? pipeline.getIterable().getName()
						: "");

		variable.getAnnotations().add(annotation);
		variable.setOriginalCompilationUnit(parent.getOriginalCompilationUnit());

		context.put(pipeline, "self", declaration);
	}

}
