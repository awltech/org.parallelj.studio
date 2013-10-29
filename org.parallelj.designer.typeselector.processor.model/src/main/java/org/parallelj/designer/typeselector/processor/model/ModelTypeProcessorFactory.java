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

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipselabs.resourceselector.core.processor.ResourceProcessor;
import org.eclipselabs.resourceselector.core.processor.ResourceProcessorFactory;

/**
 * Model Type Processor Factory for Parallel J Element Types.
 * 
 */
public class ModelTypeProcessorFactory implements ResourceProcessorFactory {

	public ModelTypeProcessorFactory() {
		super();
	}

	/**
	 * ResourceSet to use while processing elements
	 */
	protected ResourceSet resourceSet;

	/**
	 * element selected
	 */
	protected EObject eObject;

	/**
	 * Creates a new Factory with Model Resource
	 * 
	 * @param resource
	 *            Model Resource
	 */
	public ModelTypeProcessorFactory(Resource resource) {
		this.resourceSet = resource.getResourceSet();
	}

	/**
	 * Creates new Factory
	 */
	public ModelTypeProcessorFactory(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	public ModelTypeProcessorFactory(EObject eObject) {
		super();
		this.eObject = eObject;
	}

	public ResourceProcessor createResourceProcessor(IProject project,
			String pattern) {
		ModelTypeProcessor processor = new ModelTypeProcessor(this.resourceSet,
				project, pattern, this.eObject);
		return processor;
	}

}
