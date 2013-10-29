package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationFactory;

import org.eclipse.emf.ecore.EObject;
import org.parallelj.code.generator.helpers.ParallelJModelHelper;
import org.parallelj.model.Procedure;

/**
 * This factory allows to create {@link XorSplitAnnotationCreation}
 * transformations for each procedure which has a XorSplit
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class XorSplitAnnotationCreationFactory implements
		ITransformationFactory {

	@Override
	public boolean isEligible(EObject eObject) {
		return eObject instanceof Procedure
				&& ParallelJModelHelper.hasXorSplit((Procedure) eObject);
	}

	@Override
	public AbstractTransformation<Procedure> create(EObject eObject, String id) {
		return new XorSplitAnnotationCreation((Procedure) eObject, id);
	}

}
