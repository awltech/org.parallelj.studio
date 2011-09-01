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

import org.eclipselabs.resourceselector.core.resources.ResourceInfo;
import org.parallelj.designer.providers.ParallelJElementTypes;

/**
 * TypeInfo extension for Parallel J Model Types
 */
public class ModelTypeInfo extends ResourceInfo {

	/**
	 * Model Type of this TypeInfo
	 */
	private ModelType type;

	/**
	 * element Name Type of this TypeInfo
	 */
	private String elementName;

	/**
	 * Creates new ModelTypeInfo
	 * 
	 * @param elementName
	 *            name of the element
	 * @param ModelType
	 *            Model type if the element
	 */
	public ModelTypeInfo(String elementName, String packageName, ModelType modelType) {
		super(elementName, packageName,
				packageName + " (ParallelJ Program Model Object)");
		this.type = modelType;
		this.image = ParallelJElementTypes.getImage(modelType.getEClass());
		this.containerImage = ParallelJElementTypes.getImage(modelType.getEClass());
		this.elementName = elementName;
	}

	/**
	 * @return element Name Type
	 */
	public String getElementName() {
		return elementName;
	}

	/**
	 * @return Model Type of this element
	 */
	public ModelType getType() {
		return this.type;
	}

}
