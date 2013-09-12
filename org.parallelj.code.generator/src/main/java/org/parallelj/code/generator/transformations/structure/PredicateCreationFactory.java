package org.parallelj.code.generator.transformations.structure;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationFactory;

import org.eclipse.emf.ecore.EObject;
import org.parallelj.model.Predicate;

/**
 * This factory allows to create {@link PredicateCreation} transformations for
 * each predicate of the diagram
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class PredicateCreationFactory implements ITransformationFactory {

	@Override
	public boolean isEligible(EObject eObject) {
		return eObject instanceof Predicate;
	}

	@Override
	public AbstractTransformation<Predicate> create(EObject eObject, String id) {
		return new PredicateCreation((Predicate) eObject, id);
	}

}
