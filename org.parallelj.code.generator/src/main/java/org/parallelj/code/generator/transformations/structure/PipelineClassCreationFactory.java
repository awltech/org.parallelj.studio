package org.parallelj.code.generator.transformations.structure;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationFactory;

import org.eclipse.emf.ecore.EObject;
import org.parallelj.model.Pipeline;

/**
 * This factory allows to create {@link PipelineClassCreation}
 * transformations for each pipeline of the diagram
 * 
 * @author a169104
 * @version 1.0
 * 
 */
public class PipelineClassCreationFactory implements
		ITransformationFactory {

	@Override
	public boolean isEligible(EObject eObject) {
		return eObject instanceof Pipeline;				
	}

	@Override
	public AbstractTransformation<Pipeline> create(EObject eObject, String id) {
		return new PipelineClassCreation((Pipeline) eObject, id);
	}

}
