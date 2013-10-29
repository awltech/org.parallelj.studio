package org.parallelj.code.generator.transformations.structure;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationFactory;

import org.eclipse.emf.ecore.EObject;
import org.parallelj.model.Data;

/**
 * This factory allows to create {@link DataGetterMethodCreation}
 * transformations for each data of a ParallelJ diagram
 * 
 * @author Abhijit Gurav
 * @version 1.0
 * 
 */
public class DataGetterMethodCreationFactory implements ITransformationFactory {

	@Override
	public boolean isEligible(EObject eObject) {
		return eObject instanceof Data;
	}

	@Override
	public AbstractTransformation<Data> create(EObject eObject, String id) {
		return new DataGetterMethodCreation((Data) eObject, id);
	}

}
