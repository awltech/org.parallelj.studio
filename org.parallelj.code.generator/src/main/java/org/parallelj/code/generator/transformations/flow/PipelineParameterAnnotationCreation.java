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

	@ParentContextElement(value = "self", nullable = false)
	private ClassDeclaration parent;

	@ObjectContextElement(value = "entry", visibility = ContextElementVisibility.INOUT, nullable = false)
	private MethodDeclaration declaration;

	public PipelineParameterAnnotationCreation(Pipeline eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Pipeline pipeline = getEObject();
		Method method = new Method(this.declaration).addParameters(ParameterHelper
				.builder(pipeline.getIterable().getType())
				.setName(pipeline.getIterable().getName())
				.build()
				.addAnnotations(
						AnnotationHelper
								.builder("org.parallelj", "PipelineParameter")
								.addAnnotationParameter("value",
										pipeline.getIterable() != null ? pipeline.getIterable().getName() : "", true)
								.build()));
		this.declaration = method.getDelegate();
	}

}
