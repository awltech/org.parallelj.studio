package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationFactory;

import org.eclipse.emf.ecore.EObject;
import org.parallelj.code.generator.helpers.ParallelJModelHelper;
import org.parallelj.model.Handler;
import org.parallelj.model.Pipeline;
import org.parallelj.model.Procedure;

/**
 * This factory allows to create {@link AndJoinAnnotationCreation}
 * transformations for every first pipeline of a diagram which has an AndJoin
 * 
 * @author a169104
 * @version 1.0
 * 
 */
public class PipelineAndJoinAnnotationCreationFactory implements
		ITransformationFactory {

	@Override
	public boolean isEligible(EObject eObject) {
		return eObject instanceof Pipeline
				&& !ParallelJModelHelper.isFirstProcedure((Procedure) eObject)
				&& ParallelJModelHelper.hasAndJoin((Procedure) eObject)
				&& !(eObject instanceof Handler);
	}

	@Override
	public AbstractTransformation<Procedure> create(EObject eObject, String id) {
		return new AndJoinAnnotationCreation((Procedure) eObject, id, true);
	}

}
