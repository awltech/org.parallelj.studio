package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.ctxinject.api.ContextElementVisibility;
import net.atos.optimus.m2m.engine.ctxinject.api.ObjectContextElement;
import net.atos.optimus.m2m.javaxmi.operation.methods.Method;
import net.atos.optimus.m2m.javaxmi.operation.parameters.ParameterHelper;

import org.parallelj.model.Pipeline;

/**
 * This transformation allows to add the <code>@PipelineParameter</code>
 * annotation and the method parameter on an entry method. It also manages the
 * parameters of the annotation
 * 
 * @author a169104
 * @version 1.0
 * 
 */
public class PipelineParameterAnnotationCreation extends AbstractTransformation<Pipeline> {

	@ObjectContextElement(value = "entry", visibility = ContextElementVisibility.INOUT, nullable = false)
	private Method method;

	public PipelineParameterAnnotationCreation(Pipeline eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Pipeline pipeline = getEObject();
		ParameterHelper
				.builder(pipeline.getIterable().getType())
				.setName(pipeline.getIterable().getName())
				.setMethod(this.method)
				.build()
				.createAnnotation("org.parallelj", "PipelineParameter")
				.addAnnotationParameter("value",
						pipeline.getIterable() != null ? pipeline.getIterable().getName() : "", true);
	}

}
