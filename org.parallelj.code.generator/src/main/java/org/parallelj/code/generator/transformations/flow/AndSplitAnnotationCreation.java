package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.javaxmi.core.annotations.JavaAnnotationHelper;

import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.Annotation;
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
public class AndSplitAnnotationCreation extends
		AbstractTransformation<Procedure> {

	// if procedure is from pipeline 
	private boolean isPipelineProcedure;

	public AndSplitAnnotationCreation(Procedure eObject, String id,
			boolean isPipelineprocedure) {
		super(eObject, id);
		this.isPipelineProcedure = isPipelineprocedure;
	}

	@Override
	protected void transform(ITransformationContext context) {
		Procedure procedure = getEObject();
		AbstractMethodDeclaration declaration = (AbstractMethodDeclaration) context
				.get(procedure, "exit");

		Annotation annotation = JavaAnnotationHelper.addAnnotation(declaration,
				"org.parallelj", "AndSplit");

		if (!isPipelineProcedure) {

			for (Link outputLink : procedure.getOutputLinks()) {
				if (outputLink.getDestination() instanceof OutputCondition)
					JavaAnnotationHelper.addAnnotationParameter(annotation,
							"value", "end");
				else
					JavaAnnotationHelper.addAnnotationParameter(annotation,
							"value", ParallelJModelHelper
									.getConditionName(outputLink
											.getDestination()));
			}
		} else {
			JavaAnnotationHelper
					.addAnnotationParameter(annotation, "value",
							ParallelJModelHelper
									.getNextPipelineProcedure(getEObject()));
		}

		context.put(procedure, "exit", declaration);
	}

}
