package org.parallelj.code.generator.transformations.structure;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.ctxinject.api.ContextElementVisibility;
import net.atos.optimus.m2m.engine.ctxinject.api.ObjectContextElement;
import net.atos.optimus.m2m.javaxmi.operation.methods.Method;

import org.parallelj.model.Handler;

/**
 * This transformation allows to add the correct parameters on an exit method
 * for a Handler object
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class HandlerParametersCreation extends AbstractTransformation<Handler> {

	@ObjectContextElement(value = "exit", visibility = ContextElementVisibility.INOUT, nullable = false)
	private Method exitMethod;

	public HandlerParametersCreation(Handler eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		this.exitMethod.addParameter("Exception", "e").addParameter("Object", "context");
	}
}
