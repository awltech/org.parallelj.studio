package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationFactory;

import org.eclipse.emf.ecore.EObject;
import org.parallelj.model.Handler;

/**
 * This factory allows to create {@link HandlerAnnotationCreation}
 * transformations for each Handler object of the diagram
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class HandlerAnnotationCreationFactory implements ITransformationFactory {

	@Override
	public boolean isEligible(EObject eObject) {
		return eObject instanceof Handler;
	}

	@Override
	public AbstractTransformation<Handler> create(EObject eObject, String id) {
		return new HandlerAnnotationCreation((Handler) eObject, id);
	}

}
