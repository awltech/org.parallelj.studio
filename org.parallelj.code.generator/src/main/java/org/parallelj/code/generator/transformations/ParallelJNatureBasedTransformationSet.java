package org.parallelj.code.generator.transformations;

import net.atos.optimus.m2m.engine.core.transformations.NatureBasedTransformationSet;

import org.parallelj.code.generator.core.ParallelJGeneratorConstants;

/**
 * This extension of the {@link NatureBasedTransformationSet} allows to specify
 * the ParallelJ nature. This allows to activate ParallelJ source code generator
 * only for ParallelJ projects.
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class ParallelJNatureBasedTransformationSet extends
		NatureBasedTransformationSet {

	@Override
	protected String getNatureName() {
		return ParallelJGeneratorConstants.NATURE;
	}

}
