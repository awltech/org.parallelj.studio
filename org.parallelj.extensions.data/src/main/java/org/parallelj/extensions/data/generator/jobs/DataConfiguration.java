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
package org.parallelj.extensions.data.generator.jobs;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IPackageFragment;
import org.parallelj.extensions.data.generator.GenerationType;

/**
 * Data Configuration Item.
 * 
 * The role of this class is to transmit all the information from the JXAB
 * generation Wizard generator.
 * 
 * @author A169104
 * 
 */
public class DataConfiguration {

	/**
	 * Generation Type
	 */
	private GenerationType generationType;

	/**
	 * Package in which annotated Pojos will be found/generated, and in which
	 * the helping class will be generated.
	 */
	private IPackageFragment generationPackage;

	/**
	 * Boolean telling is XML pojos have to be generated from XSD file.
	 */
	private boolean generateXMLFromXSD;

	/**
	 * Path to the XSD model, used for the XML Pojos generation
	 */
	private IFile xsdModel;

	/**
	 * (Private Constructor. Should use public static methods instead)
	 * 
	 * Created a new Configuration object, according to parameters.
	 * 
	 * @param generationType
	 *            GenerationType
	 * @param generationPackage
	 *            IPackageFragment
	 * @param shouldGenerateFromXSD
	 *            boolean
	 * @param xsdModel
	 *            IFile
	 */
	public DataConfiguration(GenerationType generationType,
			IPackageFragment generationPackage, boolean shouldGenerateFromXSD,
			IFile xsdModel) {
		this.generateXMLFromXSD = shouldGenerateFromXSD;
		this.generationPackage = generationPackage;
		this.xsdModel = xsdModel;
	}

	/**
	 * @return Package in which annotated Pojos will be found/generated, and in
	 *         which the helping class will be generated.
	 */
	public IPackageFragment getGenerationPackage() {
		return this.generationPackage;
	}

	/**
	 * @return Code Generation Type
	 */
	public GenerationType getGenerationType() {
		return this.generationType;
	}

	/**
	 * @return File containing the XSD Schema that will be used to generate XML
	 *         Pojos, if necessary
	 */
	public IFile getXSDModel() {
		return this.xsdModel;
	}

	/**
	 * @return true is XML Pojos have to be generated from XSD File, false
	 *         otherwise.
	 */
	public boolean isGenerateXMLFromXSD() {
		return this.generateXMLFromXSD;
	}
}
