package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.ctxinject.api.ContextElementVisibility;
import net.atos.optimus.m2m.engine.ctxinject.api.ObjectContextElement;
import net.atos.optimus.m2m.javaxmi.operation.annotations.JavaAnnotation;
import net.atos.optimus.m2m.javaxmi.operation.methods.Method;

import org.parallelj.code.generator.helpers.ParallelJModelHelper;
import org.parallelj.model.Link;
import org.parallelj.model.OutputCondition;
import org.parallelj.model.Procedure;

/**
 * This transformation allows to add the <code>@OrSplit</code> on each exit
 * method, and its parameters
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class OrSplitAnnotationCreation extends AbstractTransformation<Procedure> {

	@ObjectContextElement(value = "exit", visibility = ContextElementVisibility.INOUT, nullable = false)
	private Method method;

	public OrSplitAnnotationCreation(Procedure eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Procedure procedure = getEObject();
		JavaAnnotation annotation = this.method.createAnnotation("org.parallelj", "OrSplit");
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
