package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.ctxinject.api.ContextElementVisibility;
import net.atos.optimus.m2m.engine.ctxinject.api.ObjectContextElement;
import net.atos.optimus.m2m.javaxmi.operation.annotations.JavaAnnotation;
import net.atos.optimus.m2m.javaxmi.operation.methods.Method;

import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.parallelj.code.generator.helpers.ParallelJModelHelper;
import org.parallelj.model.Link;
import org.parallelj.model.OutputCondition;
import org.parallelj.model.Procedure;

/**
 * This transformation allows to add the <code>XorSplit</code> annotation on an
 * exit method, and its parameters
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class XorSplitAnnotationCreation extends AbstractTransformation<Procedure> {

	@ObjectContextElement(value = "exit", visibility = ContextElementVisibility.INOUT, nullable = false)
	private MethodDeclaration declaration;

	public XorSplitAnnotationCreation(Procedure eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Procedure procedure = getEObject();
		JavaAnnotation annotation = new Method(this.declaration).createAnnotation("org.parallelj", "XorSplit");
		for (Link outputLink : procedure.getOutputLinks()) {
			JavaAnnotation internalAnnotation = annotation.createAnnotation("org.parallelj", "Link", null);
			internalAnnotation.addAnnotationParameter("predicate", outputLink.getPredicate() != null ? outputLink
					.getPredicate().getName() : "", true);
			if (outputLink.getDestination() instanceof OutputCondition) {
				internalAnnotation.addAnnotationParameter("to", "end", true);
			} else {
				internalAnnotation.addAnnotationParameter("to",
						ParallelJModelHelper.getConditionName(outputLink.getDestination()), true);
			}
		}
	}

}
