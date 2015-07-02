package org.parallelj.code.generator.transformations.structure;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.ctxinject.api.ContextElementVisibility;
import net.atos.optimus.m2m.engine.ctxinject.api.ObjectContextElement;
import net.atos.optimus.m2m.engine.ctxinject.api.ParentContextElement;
import net.atos.optimus.m2m.javaxmi.operation.methods.Method;

import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
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

	@ParentContextElement(value = "self", nullable = false)
	private ClassDeclaration parent;

	@ObjectContextElement(value = "exit", visibility = ContextElementVisibility.INOUT, nullable = false)
	private MethodDeclaration exitMethod;

	public HandlerParametersCreation(Handler eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		this.exitMethod = new Method(this.exitMethod).addParameter("Exception", "e").addParameter("Object", "context")
				.getDelegate();
	}
}
