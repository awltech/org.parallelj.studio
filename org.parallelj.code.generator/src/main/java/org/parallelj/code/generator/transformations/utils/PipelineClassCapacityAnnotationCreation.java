package org.parallelj.code.generator.transformations.utils;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.javaxmi.core.annotations.JavaAnnotationHelper;

import org.eclipse.gmt.modisco.java.Annotation;
import org.eclipse.gmt.modisco.java.AnnotationMemberValuePair;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.NumberLiteral;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;
import org.parallelj.model.Pipeline;

/**
 * This transformation allows to add the <code>@Capacity</code> annotation on a
 * parallelj Pipeline class
 * 
 * @author a169104
 * @version 1.0
 * 
 */
public class PipelineClassCapacityAnnotationCreation extends
		AbstractTransformation<Pipeline> {

	public PipelineClassCapacityAnnotationCreation(Pipeline eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Pipeline pipeline = getEObject();
		ClassDeclaration classDeclaration = (ClassDeclaration) context.get(
				pipeline, "self");

		Annotation addedAnnotation = JavaAnnotationHelper.addAnnotation(
				classDeclaration, "org.parallelj", "Capacity");

		AnnotationMemberValuePair mvp = JavaFactory.eINSTANCE
				.createAnnotationMemberValuePair();
		mvp.setName("value");
		NumberLiteral nlt = JavaFactory.eINSTANCE.createNumberLiteral();
		nlt.setTokenValue(String.valueOf(pipeline.getCapacity()));
		mvp.setValue(nlt);

		addedAnnotation.getValues().add(mvp);

		context.put(pipeline, "self", classDeclaration);
	}

}
