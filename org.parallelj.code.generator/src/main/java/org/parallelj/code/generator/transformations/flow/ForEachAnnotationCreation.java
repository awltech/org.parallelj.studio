package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.ctxinject.api.ContextElementVisibility;
import net.atos.optimus.m2m.engine.ctxinject.api.ObjectContextElement;
import net.atos.optimus.m2m.javaxmi.operation.methods.Method;
import net.atos.optimus.m2m.javaxmi.operation.parameters.ParameterHelper;

import org.parallelj.model.ForEachLoop;

/**
 * This transformation allows to add the <code>@ForEach</code> annotation and
 * the method parameter on an entry method. It also manages the parameters of
 * the annotation
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class ForEachAnnotationCreation extends AbstractTransformation<ForEachLoop> {

	@ObjectContextElement(value = "self", visibility = ContextElementVisibility.INOUT, nullable = false)
	private Method method;

	public ForEachAnnotationCreation(ForEachLoop eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		ForEachLoop forEach = getEObject();
		ParameterHelper
				.builder("Object")
				.setName("val")
				.setMethod(this.method)
				.build()
				.createAnnotation("org.parallelj", "ForEach")
				.addAnnotationParameter("value", forEach.getIterable() != null ? forEach.getIterable().getName() : "",
						true);
	}
}
