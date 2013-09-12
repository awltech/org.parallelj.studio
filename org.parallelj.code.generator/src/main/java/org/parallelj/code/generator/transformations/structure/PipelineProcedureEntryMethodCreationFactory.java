package org.parallelj.code.generator.transformations.structure;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationFactory;

import org.eclipse.emf.ecore.EObject;
import org.parallelj.code.generator.helpers.ParallelJModelHelper;
import org.parallelj.model.Procedure;

/**
 * This factory allows to create {@link ProcedureEntryMethodCreation}
 * transformations for each executable pipeline procedure of the diagram
 * 
 * @author a169104
 * @version 1.0
 * 
 */
public class PipelineProcedureEntryMethodCreationFactory implements
		ITransformationFactory {

	@Override
	public boolean isEligible(EObject eObject) {
		return eObject instanceof Procedure
				&& ParallelJModelHelper.isPipelineProcedure(eObject)
				&& ParallelJModelHelper.isExecutable(eObject);
	}

	@Override
	public AbstractTransformation<Procedure> create(EObject eObject, String id) {
		return new ProcedureEntryMethodCreation((Procedure) eObject, id,true);
	}

}
