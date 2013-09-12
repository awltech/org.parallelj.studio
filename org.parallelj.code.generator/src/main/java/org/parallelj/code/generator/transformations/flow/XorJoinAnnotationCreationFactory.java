package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationFactory;

import org.eclipse.emf.ecore.EObject;
import org.parallelj.code.generator.helpers.ParallelJModelHelper;
import org.parallelj.model.Handler;
import org.parallelj.model.Pipeline;
import org.parallelj.model.Procedure;

/**
 * This factory allows to create {@link XorJoinAnnotationCreation}
 * transformations for each Procedure which is not the first one and has a
 * XorJoin
 * 
 * @author Antoine Neveux & a169104
 * @version 1.0
 * 
 */
public class XorJoinAnnotationCreationFactory implements ITransformationFactory {

	// checking is normal procedure or pipeline procedure based on that
	// checking is program 1st procedure or pipeline one.
	@Override
	public boolean isEligible(EObject eObject) {
		return eObject instanceof Procedure && !(eObject instanceof Pipeline)
				&& !ParallelJModelHelper.isPipelineProcedure(eObject)
				&& !(eObject instanceof Handler)
				&& !ParallelJModelHelper.isFirstProcedure((Procedure) eObject)
				&& ParallelJModelHelper.hasXorJoin((Procedure) eObject);

	}

	@Override
	public AbstractTransformation<Procedure> create(EObject eObject, String id) {
		return new XorJoinAnnotationCreation((Procedure) eObject, id, false);
	}

}
