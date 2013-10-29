package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationFactory;

import org.eclipse.emf.ecore.EObject;
import org.parallelj.model.Pipeline;

/**
 * This factory allows to create {@link PipelineParameterAnnotationCreation}
 * transformations for each Pipeline object
 * 
 * @author a169104
 * @version 1.0
 * 
 */
public class PipelineParameterAnnotationCreationFactory implements ITransformationFactory {

	@Override
	public boolean isEligible(EObject eObject) {
		return eObject instanceof Pipeline;
	}

	@Override
	public AbstractTransformation<Pipeline> create(EObject eObject, String id) {
		return new PipelineParameterAnnotationCreation((Pipeline)eObject, id);
	}

}
