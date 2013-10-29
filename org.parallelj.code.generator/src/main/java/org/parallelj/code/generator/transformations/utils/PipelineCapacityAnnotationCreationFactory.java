package org.parallelj.code.generator.transformations.utils;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationFactory;

import org.eclipse.emf.ecore.EObject;
import org.parallelj.model.Pipeline;
import org.parallelj.model.Procedure;

/**
 * This factory allows to create a {@link PipelineCapacityAnnotationCreation}
 * transformation for each procedure of a diagram
 * 
 * @author a169104
 * @version 1.0
 * 
 */
public class PipelineCapacityAnnotationCreationFactory implements
		ITransformationFactory {

	@Override
	public boolean isEligible(EObject eObject) {
		return eObject instanceof Pipeline;				
	}

	@Override
	public AbstractTransformation<Procedure> create(EObject eObject, String id) {
		return new ProcedureCapacityAnnotationCreation((Procedure) eObject, id, true);
	}

}
