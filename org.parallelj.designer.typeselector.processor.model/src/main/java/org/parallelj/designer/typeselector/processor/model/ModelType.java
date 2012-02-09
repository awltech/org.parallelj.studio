/*
 *     ParallelJ, framework for parallel computing
 *     
 *     Copyright (C) 2010 Atos Worldline or third-party contributors as
 *     indicated by the @author tags or express copyright attribution
 *     statements applied by the authors.
 *     
 *     This library is free software; you can redistribute it and/or
 *     modify it under the terms of the GNU Lesser General Public
 *     License as published by the Free Software Foundation; either
 *     version 2.1 of the License.
 *     
 *     This library is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *     Lesser General Public License for more details.
 *     
 *     You should have received a copy of the GNU Lesser General Public
 *     License along with this library; if not, write to the Free Software
 *     Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */
package org.parallelj.designer.typeselector.processor.model;

import org.eclipse.emf.ecore.EClass;
import org.parallelj.model.ParallelJPackage;

/**
 * Enumeration describing all possible Parallel J Model Types.
 */
public enum ModelType {
	PROGRAM, BLOCK, ELEMENT;

	/**
	 * Returns ModelType enumeration value from Model EClass
	 * 
	 * @param eClass
	 *            Model EClass
	 * @return ModelType enumeration value
	 */
	public static ModelType create(EClass eClass) {
		if (ParallelJPackage.eINSTANCE.getProgram().equals(eClass))
			return ModelType.PROGRAM;
		else if (ParallelJPackage.eINSTANCE.getBlock().equals(eClass))
			return ModelType.BLOCK;
		else
			return ModelType.ELEMENT;
	}

	/**
	 * @return EClass associated to this ModelType value
	 */
	public EClass getEClass() {
		switch (this) {
		case PROGRAM:
			return ParallelJPackage.eINSTANCE.getProgram();
		case BLOCK:
			return ParallelJPackage.eINSTANCE.getBlock();
		default:
			return ParallelJPackage.eINSTANCE.getElement();
		}
	}
}
