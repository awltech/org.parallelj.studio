package org.parallelj.code.generator.transformations.utils;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.ctxinject.api.ContextElementVisibility;
import net.atos.optimus.m2m.engine.ctxinject.api.ObjectContextElement;
import net.atos.optimus.m2m.javaxmi.operation.classes.JavaClass;

import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.parallelj.model.Pipeline;

/**
 * This transformation allows to add the <code>@Capacity</code> annotation on a
 * parallelj Pipeline class
 * 
 * @author a169104
 * @version 1.0
 * 
 */
public class PipelineClassCapacityAnnotationCreation extends AbstractTransformation<Pipeline> {

	@ObjectContextElement(value = "self", visibility = ContextElementVisibility.INOUT, nullable = false)
	private ClassDeclaration classDeclaration;

	public PipelineClassCapacityAnnotationCreation(Pipeline eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Pipeline pipeline = getEObject();
		new JavaClass(this.classDeclaration).createAnnotation("org.parallelj", "Capacity").addAnnotationParameter(
				"value", pipeline.getCapacity());
	}

}
