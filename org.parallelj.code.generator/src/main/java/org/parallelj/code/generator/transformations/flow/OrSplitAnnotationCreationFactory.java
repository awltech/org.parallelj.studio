package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationFactory;

import org.eclipse.emf.ecore.EObject;
import org.parallelj.code.generator.helpers.ParallelJModelHelper;
import org.parallelj.model.Procedure;

/**
 * This factory allows to create {@link OrSplitAnnotationCreation}
 * transformations for each procedure which has an OrSplit
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class OrSplitAnnotationCreationFactory implements ITransformationFactory {

	@Override
	public boolean isEligible(EObject eObject) {
		return eObject instanceof Procedure
				&& ParallelJModelHelper.hasOrSplit((Procedure) eObject);
	}

	@Override
	public AbstractTransformation<Procedure> create(EObject eObject, String id) {
		return new OrSplitAnnotationCreation((Procedure) eObject, id);
	}

}
