package org.parallelj.code.generator.transformations.structure;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationFactory;

import org.eclipse.emf.ecore.EObject;
import org.parallelj.code.generator.helpers.ParallelJModelHelper;
import org.parallelj.model.Procedure;

/**
 * This factory allows to create {@link GenericExitMethodCreation}
 * transformations for each pipeline procedure of a parallelj diagram
 * 
 * @author a169104
 * @version 1.0
 * 
 */
public class PipelineGenericExitMethodCreationFactory implements
		ITransformationFactory {

	@Override
	public boolean isEligible(EObject eObject) {
		return eObject instanceof Procedure
				&& ParallelJModelHelper.isPipelineProcedure(eObject);
	}

	@Override
	public AbstractTransformation<Procedure> create(EObject eObject, String id) {
		return new GenericExitMethodCreation((Procedure) eObject, id, true);
	}

}
