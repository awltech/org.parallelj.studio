package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.javaxmi.core.annotations.JavaAnnotationHelper;

import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.Annotation;
import org.parallelj.code.generator.helpers.ParallelJModelHelper;
import org.parallelj.model.Condition;
import org.parallelj.model.Element;
import org.parallelj.model.Link;
import org.parallelj.model.Procedure;

/**
 * This transformation allows to add the <code>@AndJoin</code> annotation on an
 * entry method, and its parameters
 * 
 * @author Antoine Neveux & a169104
 * @version 1.0
 * 
 */
public class AndJoinAnnotationCreation extends
		AbstractTransformation<Procedure> {

	// if putting AndJoin on pipeline
	private boolean isPipeline;

	public AndJoinAnnotationCreation(Procedure eObject, String id,
			boolean isPipeline) {
		super(eObject, id);
		this.isPipeline = isPipeline;
	}

	@Override
	protected void transform(ITransformationContext context) {
		
		String key = "self";
		
		if(isPipeline){
			key = "entry";
		}
		
		Procedure procedure = getEObject();
		boolean noop = false;
		AbstractMethodDeclaration declaration = (AbstractMethodDeclaration) context
				.get(procedure, key);

		if (declaration == null) {
			noop = true;
			declaration = (AbstractMethodDeclaration) context.get(procedure,
					"exit");
		}

		Annotation annotation = JavaAnnotationHelper.addAnnotation(declaration,
				"org.parallelj", "AndJoin");

		for (Link inputLink : procedure.getInputLinks()) {
			if (inputLink.eContainer() instanceof Condition
					&& ((Element) inputLink.eContainer()).getName() != null) {
				JavaAnnotationHelper.addAnnotationParameter(annotation,
						"value", ParallelJModelHelper
								.getConditionName((Element) inputLink
										.eContainer()));
			}
		}

		if (!noop)
			context.put(procedure, key, declaration);
		else
			context.put(procedure, "exit", declaration);
	}

}
