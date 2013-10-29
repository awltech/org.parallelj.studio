package org.parallelj.code.generator.transformations.utils;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationFactory;

import org.eclipse.emf.ecore.EObject;
import org.parallelj.model.Pipeline;

/**
 * This factory allows to create a {@link PipelineClassCapacityAnnotationCreation}
 * transformation for each pipeline of a diagram
 * 
 * @author a169104
 * @version 1.0
 * 
 */
public class PipelineClassCapacityAnnotationCreationFactory implements
		ITransformationFactory {

	@Override
	public boolean isEligible(EObject eObject) {
		return eObject instanceof Pipeline;
	}

	@Override
	public AbstractTransformation<Pipeline> create(EObject eObject, String id) {
		return new PipelineClassCapacityAnnotationCreation((Pipeline) eObject, id);
	}

}
