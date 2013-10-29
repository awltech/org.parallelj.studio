package org.parallelj.code.generator.transformations.utils;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationFactory;

import org.eclipse.emf.ecore.EObject;
import org.parallelj.model.Program;

/**
 * This factory allows to create {@link ProgramTestClassCreation}
 * transformations for each program of a diagram
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class ProgramTestClassCreationFactory implements ITransformationFactory {

	@Override
	public boolean isEligible(EObject eObject) {
		return eObject instanceof Program;
	}

	@Override
	public AbstractTransformation<Program> create(EObject eObject, String id) {
		return new ProgramTestClassCreation((Program) eObject, id);
	}

}
