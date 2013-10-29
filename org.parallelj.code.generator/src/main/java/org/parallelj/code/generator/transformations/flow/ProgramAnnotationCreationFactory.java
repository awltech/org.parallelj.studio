package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationFactory;

import org.eclipse.emf.ecore.EObject;
import org.parallelj.model.Program;

/**
 * This factory allows to create {@link ProgramAnnotationCreation}
 * transformations for each program
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class ProgramAnnotationCreationFactory implements ITransformationFactory {

	@Override
	public boolean isEligible(EObject eObject) {
		return eObject instanceof Program;
	}

	@Override
	public AbstractTransformation<Program> create(EObject eObject, String id) {
		return new ProgramAnnotationCreation((Program) eObject, id);
	}

}
