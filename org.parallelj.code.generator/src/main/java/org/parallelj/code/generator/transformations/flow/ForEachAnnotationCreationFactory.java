package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationFactory;

import org.eclipse.emf.ecore.EObject;
import org.parallelj.model.ForEachLoop;

/**
 * This factory allows to create {@link ForEachAnnotationCreation}
 * transformations for each ForEach object
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class ForEachAnnotationCreationFactory implements ITransformationFactory {

	@Override
	public boolean isEligible(EObject eObject) {
		return eObject instanceof ForEachLoop;
	}

	@Override
	public AbstractTransformation<ForEachLoop> create(EObject eObject, String id) {
		return new ForEachAnnotationCreation((ForEachLoop) eObject, id);
	}

}
