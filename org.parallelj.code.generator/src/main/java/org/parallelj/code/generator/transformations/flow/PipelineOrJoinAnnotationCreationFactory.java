package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationFactory;

import org.eclipse.emf.ecore.EObject;
import org.parallelj.code.generator.helpers.ParallelJModelHelper;
import org.parallelj.model.Pipeline;
import org.parallelj.model.Procedure;

/**
 * This factory allows to create {@link AndJoinAnnotationCreation}
 * transformations for every first pipeline of a diagram which has an OrJoin
 * 
 * @author a169104
 * @version 1.0
 * 
 */
public class PipelineOrJoinAnnotationCreationFactory implements
		ITransformationFactory {

	@Override
	public boolean isEligible(EObject eObject) {
		return eObject instanceof Pipeline
				&& !ParallelJModelHelper.isFirstProcedure((Procedure) eObject)
				&& ParallelJModelHelper.hasOrJoin((Procedure) eObject);
	}

	@Override
	public AbstractTransformation<Procedure> create(EObject eObject, String id) {
		return new OrJoinAnnotationCreation((Procedure) eObject, id, true);
	}

}
