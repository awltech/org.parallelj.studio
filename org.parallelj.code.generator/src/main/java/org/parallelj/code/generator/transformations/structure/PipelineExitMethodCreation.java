package org.parallelj.code.generator.transformations.structure;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.ctxinject.api.ContextElementVisibility;
import net.atos.optimus.m2m.engine.ctxinject.api.ObjectContextElement;
import net.atos.optimus.m2m.javaxmi.operation.methods.Method;

import org.parallelj.code.generator.helpers.StringFormatHelper;
import org.parallelj.model.Pipeline;

/**
 * This transformation allows to create an exit method for a pipeline in a
 * parallelj program
 * 
 * @author a169104
 * @version 1.0
 * 
 */
public class PipelineExitMethodCreation extends AbstractTransformation<Pipeline> {

	@ObjectContextElement(value = "exit", visibility = ContextElementVisibility.INOUT, nullable = false)
	private Method exitMethod;

	public PipelineExitMethodCreation(Pipeline eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Pipeline pipeline = getEObject();
		this.exitMethod.addParameter(StringFormatHelper.camelCase(pipeline.getName() + "Class", true), "executable");
	}
}
