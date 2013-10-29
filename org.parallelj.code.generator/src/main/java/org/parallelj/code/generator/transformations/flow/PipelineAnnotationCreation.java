package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.javaxmi.core.annotations.JavaAnnotationHelper;

import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.parallelj.model.Pipeline;

/**
 * This transformation allows to add the <code>@Pipeline</code> annotation on a
 * pipeline class
 * 
 * @author a169104
 * @version 1.0
 * 
 */
public class PipelineAnnotationCreation extends AbstractTransformation<Pipeline> {

	public PipelineAnnotationCreation(Pipeline eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Pipeline pipeline = getEObject();
		ClassDeclaration classDeclaration = (ClassDeclaration) context.get(
				pipeline, "self");

		JavaAnnotationHelper.addAnnotation(classDeclaration, "org.parallelj",
				"Pipeline");

		context.put(pipeline, "self", classDeclaration);
	}

}
