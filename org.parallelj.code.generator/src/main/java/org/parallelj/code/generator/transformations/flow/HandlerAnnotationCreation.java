package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.ctxinject.api.ContextElementVisibility;
import net.atos.optimus.m2m.engine.ctxinject.api.ObjectContextElement;
import net.atos.optimus.m2m.javaxmi.operation.annotations.JavaAnnotation;
import net.atos.optimus.m2m.javaxmi.operation.methods.Method;

import org.parallelj.model.Handler;
import org.parallelj.model.Procedure;

/**
 * This transformation allows to add the <code>@Handler</code> annotation on an
 * exit method, and its parameters
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class HandlerAnnotationCreation extends AbstractTransformation<Handler> {

	@ObjectContextElement(value = "exit", visibility = ContextElementVisibility.INOUT, nullable = false)
	private Method method;

	public HandlerAnnotationCreation(Handler eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Handler handler = getEObject();
		JavaAnnotation annotation = this.method.createAnnotation("org.parallelj", "Handler");
		for (Procedure procedure : handler.getProcedures()) {
			annotation.addAnnotationParameter("value", procedure.getName(), true);
		}
	}

}
