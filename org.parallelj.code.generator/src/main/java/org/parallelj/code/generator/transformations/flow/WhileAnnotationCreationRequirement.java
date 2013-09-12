package org.parallelj.code.generator.transformations.flow;

import java.util.ArrayList;
import java.util.List;

import net.atos.optimus.m2m.engine.core.requirements.AbstractRequirement;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.parallelj.model.Predicate;

/**
 * This requirement class allows to check for every While object of the diagram,
 * so as to be sure they've already been managed by a transformation
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class WhileAnnotationCreationRequirement extends AbstractRequirement {

	@Override
	public List<? extends EObject> getMatchingEObjects(EObject eObject,
			ITransformationContext context) {
		List<EObject> matchingEObjects = new ArrayList<EObject>();
		Resource resource = eObject.eResource();
		for (EObject o : resource.getContents()) {
			if (o instanceof Predicate)
				matchingEObjects.add((Predicate) o);
		}
		return matchingEObjects;
	}

}
