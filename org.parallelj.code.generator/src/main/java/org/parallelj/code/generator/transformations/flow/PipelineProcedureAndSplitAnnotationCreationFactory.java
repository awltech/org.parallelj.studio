package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationFactory;

import org.eclipse.emf.ecore.EObject;
import org.parallelj.code.generator.helpers.ParallelJModelHelper;
import org.parallelj.model.Pipeline;
import org.parallelj.model.Procedure;

/**
 * This factory allows to create {@link AndSplitAnnotationCreation}
 * transformations for any pipeline procedure with an AndSplit
 * 
 * @author a169104
 * @version 1.0
 * 
 */
public class PipelineProcedureAndSplitAnnotationCreationFactory implements
		ITransformationFactory {

	@Override
	public boolean isEligible(EObject eObject) {
		return eObject instanceof Procedure && !(eObject instanceof Pipeline)
				&& ParallelJModelHelper.isPipelineProcedure(eObject)
				&& ParallelJModelHelper.hasAndSplit((Procedure) eObject);
	}

	@Override
	public AbstractTransformation<Procedure> create(EObject eObject, String id) {
		return new AndSplitAnnotationCreation((Procedure) eObject, id, true);
	}

}
