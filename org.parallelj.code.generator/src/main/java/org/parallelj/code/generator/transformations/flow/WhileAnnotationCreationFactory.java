package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationFactory;

import org.eclipse.emf.ecore.EObject;
import org.parallelj.model.WhileLoop;

/**
 * This factory allows to create {@link WhileAnnotationCreation} transformations
 * for each WhileLoop objects
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class WhileAnnotationCreationFactory implements ITransformationFactory {

	@Override
	public boolean isEligible(EObject eObject) {
		return eObject instanceof WhileLoop;
	}

	@Override
	public AbstractTransformation<WhileLoop> create(EObject eObject, String id) {
		return new WhileAnnotationCreation((WhileLoop) eObject, id);
	}

}
