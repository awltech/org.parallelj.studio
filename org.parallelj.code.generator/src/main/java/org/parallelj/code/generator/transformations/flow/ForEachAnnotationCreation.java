package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.ctxinject.api.ContextElementVisibility;
import net.atos.optimus.m2m.engine.ctxinject.api.ObjectContextElement;
import net.atos.optimus.m2m.engine.ctxinject.api.ParentContextElement;
import net.atos.optimus.m2m.javaxmi.operation.annotations.AnnotationHelper;
import net.atos.optimus.m2m.javaxmi.operation.methods.Method;
import net.atos.optimus.m2m.javaxmi.operation.parameters.ParameterHelper;

import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
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

	@ParentContextElement(value = "self", nullable = false)
	private ClassDeclaration parent;

	@ObjectContextElement(value = "self", visibility = ContextElementVisibility.INOUT, nullable = false)
	private MethodDeclaration declaration;

	public ForEachAnnotationCreation(ForEachLoop eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		ForEachLoop forEach = getEObject();
		this.declaration = new Method(this.declaration).addParameters(
				ParameterHelper
						.builder("Object")
						.setName("val")
						.build()
						.addAnnotations(
								AnnotationHelper
										.builder("org.parallelj", "ForEach")
										.addAnnotationParameter("value",
												forEach.getIterable() != null ? forEach.getIterable().getName() : "",
												true).build())).getDelegate();
	}

}
