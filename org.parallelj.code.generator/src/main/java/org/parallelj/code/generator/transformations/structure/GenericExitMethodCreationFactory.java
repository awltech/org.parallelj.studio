package org.parallelj.code.generator.transformations.structure;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationFactory;

import org.eclipse.emf.ecore.EObject;
import org.parallelj.code.generator.helpers.ParallelJModelHelper;
import org.parallelj.model.Procedure;

/**
 * This factory allows to create {@link GenericExitMethodCreation}
 * transformations for each procedure of a parallelj diagram
 * 
 * @author Antoine Neveux & a169104
 * @version 1.0
 * 
 */
public class GenericExitMethodCreationFactory implements ITransformationFactory {

	//should not be pipeline procedure
	@Override
	public boolean isEligible(EObject eObject) {
		return eObject instanceof Procedure
				&& !ParallelJModelHelper.isPipelineProcedure(eObject);
	}

	@Override
	public AbstractTransformation<Procedure> create(EObject eObject, String id) {
		return new GenericExitMethodCreation((Procedure) eObject, id, false);
	}

}
