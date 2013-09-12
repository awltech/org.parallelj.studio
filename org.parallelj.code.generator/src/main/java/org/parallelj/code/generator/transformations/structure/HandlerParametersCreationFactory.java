package org.parallelj.code.generator.transformations.structure;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationFactory;

import org.eclipse.emf.ecore.EObject;
import org.parallelj.model.Handler;

/**
 * This factory allows to create {@link HandlerParametersCreation}
 * transformations for each handler of the diagram
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class HandlerParametersCreationFactory implements ITransformationFactory {

	@Override
	public boolean isEligible(EObject eObject) {
		return eObject instanceof Handler;
	}

	@Override
	public AbstractTransformation<Handler> create(EObject eObject, String id) {
		return new HandlerParametersCreation((Handler) eObject, id);
	}

}
