package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationFactory;

import org.eclipse.emf.ecore.EObject;
import org.parallelj.code.generator.helpers.ParallelJModelHelper;
import org.parallelj.model.Pipeline;
import org.parallelj.model.Procedure;

/**
 * This factory allows to create {@link BeginAnnotationCreation} transformations
 * for each pipeline if it is first in diagram
 * 
 * @author a169104
 * @version 1.0
 * 
 */
public class PipelineBeginAnnotationCreationFactory implements
		ITransformationFactory {

	@Override
	public boolean isEligible(EObject eObject) {
		return eObject instanceof Pipeline
				&& ParallelJModelHelper.isFirstProcedure((Procedure) eObject);
	}

	@Override
	public AbstractTransformation<Procedure> create(EObject eObject, String id) {
		return new BeginAnnotationCreation((Procedure) eObject, id, true);
	}

}
