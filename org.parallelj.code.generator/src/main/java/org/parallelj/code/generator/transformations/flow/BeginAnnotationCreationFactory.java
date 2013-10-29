package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationFactory;

import org.eclipse.emf.ecore.EObject;
import org.parallelj.code.generator.helpers.ParallelJModelHelper;
import org.parallelj.model.Pipeline;
import org.parallelj.model.Procedure;

/**
 * This factory allows to create {@link BeginAnnotationCreation} transformations
 * for each procedure/pipeline procedure which is the first one of the diagram
 * 
 * @author Antoine Neveux & a169104
 * @version 1.0
 * 
 */
public class BeginAnnotationCreationFactory implements ITransformationFactory {

	// checking is normal procedure or pipeline procedure based on that
	// checking is program 1st procedure or pipeline one.
	@Override
	public boolean isEligible(EObject eObject) {
		return eObject instanceof Procedure
				&& !(eObject instanceof Pipeline)
				&& ((ParallelJModelHelper.isPipelineProcedure(eObject) && ParallelJModelHelper
						.isFirstPipelineProcedure(eObject)) || (!ParallelJModelHelper
						.isPipelineProcedure(eObject) && ParallelJModelHelper
						.isFirstProcedure((Procedure) eObject)));
	}

	@Override
	public AbstractTransformation<Procedure> create(EObject eObject, String id) {
		return new BeginAnnotationCreation((Procedure) eObject, id, false);
	}

}
