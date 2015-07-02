package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.ctxinject.api.ContextElementVisibility;
import net.atos.optimus.m2m.engine.ctxinject.api.ObjectContextElement;
import net.atos.optimus.m2m.javaxmi.operation.annotations.AnnotationHelper;
import net.atos.optimus.m2m.javaxmi.operation.methods.Method;

import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.parallelj.model.WhileLoop;

/**
 * This transformation allows to add the <code>@While</code> annotation on an
 * entry method, and its parameters
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class WhileAnnotationCreation extends AbstractTransformation<WhileLoop> {

	@ObjectContextElement(value = "self", visibility = ContextElementVisibility.INOUT, nullable = false)
	private MethodDeclaration declaration;

	public WhileAnnotationCreation(WhileLoop eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		WhileLoop whileLoop = getEObject();
		this.declaration = new Method(this.declaration).addAnnotations(
				AnnotationHelper
						.builder("org.parallelj", "While")
						.addAnnotationParameter("value",
								whileLoop.getPredicate() != null ? whileLoop.getPredicate().getName() : "", true)
						.build()).getDelegate();
	}

}
