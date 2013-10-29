package org.parallelj.code.generator.transformations.utils;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationFactory;

import org.eclipse.emf.ecore.EObject;
import org.parallelj.code.generator.helpers.ParallelJModelHelper;
import org.parallelj.model.Procedure;

/**
 * This factory allows to create a {@link ProcedureCapacityAnnotationCreation}
 * transformation for each procedure of a diagram
 * 
 * @author Antoine Neveux & a169104
 * @version 1.0
 * 
 */
public class ProcedureCapacityAnnotationCreationFactory implements
		ITransformationFactory {

	@Override
	public boolean isEligible(EObject eObject) {
		return eObject instanceof Procedure
				&& ParallelJModelHelper.isExecutable(eObject);
	}

	@Override
	public AbstractTransformation<Procedure> create(EObject eObject, String id) {
		return new ProcedureCapacityAnnotationCreation((Procedure) eObject, id,
				false);
	}

}
