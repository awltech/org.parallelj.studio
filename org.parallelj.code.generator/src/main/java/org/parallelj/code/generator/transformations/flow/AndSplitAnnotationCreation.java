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
 * This transformation allows to add the <code>@AndSplit</code> annotation on an
 * exit method, and its parameters
 * 
 * @author Antoine Neveux & a169104
 * @version 1.0
 * 
 */
public class AndSplitAnnotationCreation extends AbstractTransformation<Procedure> {

	@ObjectContextElement(value = "exit", visibility = ContextElementVisibility.INOUT, nullable = false)
	private MethodDeclaration declaration;

	// if procedure is from pipeline
	private boolean isPipelineProcedure;

	public AndSplitAnnotationCreation(Procedure eObject, String id, boolean isPipelineprocedure) {
		super(eObject, id);
		this.isPipelineProcedure = isPipelineprocedure;
	}

	@Override
	protected void transform(ITransformationContext context) {
		Procedure procedure = getEObject();
		JavaAnnotation annotation = (new Method(this.declaration)).createAnnotation("org.parallelj", "AndSplit");

		if (!isPipelineProcedure) {

			for (Link outputLink : procedure.getOutputLinks()) {
				if (outputLink.getDestination() instanceof OutputCondition)
					annotation.addAnnotationParameter("value", "end", true);
				else
					annotation.addAnnotationParameter("value",
							ParallelJModelHelper.getConditionName(outputLink.getDestination()), true);
			}
		} else {
			annotation.addAnnotationParameter("value", ParallelJModelHelper.getNextPipelineProcedure(getEObject()),
					true);
		}

	}

}
