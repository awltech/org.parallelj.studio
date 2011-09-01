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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipselabs.resourceselector.core.processor.ResourceProcessor;
import org.eclipselabs.resourceselector.core.resources.ResourceInfo;

/**
 * Model processor for Parallel J Models of Program EObjects.
 * 
 * If Model Resource field is set, than file will be processed to retrieve all
 * the Program EObject within a selected project for any *.pjd file
 */
public class ModelTypeProcessor extends ResourceProcessor {

	/**
	 * Model file extension value
	 */
	public static String fileExtension = "pjd";

	/**
	 * ResourceSet to use while processing elements
	 */
	protected ResourceSet resourceSet;

	/**
	 * default constructor.
	 */
	public ModelTypeProcessor() {
		super();
	}

	/**
	 * Creates new Model Type Processor with input parameters
	 * 
	 * @param resourceSet
	 * 
	 * @param project
	 *            : input IProject
	 * @param pattern
	 *            : input pattern
	 */
	public ModelTypeProcessor(ResourceSet resourceSet, IProject project,
			String pattern) {
		this.resourceSet = resourceSet;
		this.iProject = project;
		this.pattern = pattern;
	}

	/**
	 * Sets the file extension used to filter resources in project
	 * 
	 * @param fileExtension
	 *            : file extension
	 */
	public static void setFileExtension(String fileExtension) {
		if (fileExtension != null) {
			ModelTypeProcessor.fileExtension = fileExtension;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipselabs.resourceselector.core.processor.TypeProcessor#process()
	 */
	@Override
	protected void process() {
		this.searchResults.clear();
		Collection<ModelTypeInfo> typeInfos = null;

		if (this.resourceSet == null) {
			this.resourceSet = new ResourceSetImpl();
			typeInfos = this.processProject();
		}
		if (typeInfos == null) {
			return;
		}

		ModelMatcher matcher = new ModelMatcher(this.pattern);

		for (ResourceInfo typeInfo : typeInfos) {
			if (matcher.match(typeInfo.getPackageName(),
					typeInfo.getElementName())) {
				this.searchResults.add(typeInfo);
			}
		}
	}

	/**
	 * Processes the input IProject to look for Model Resources, to finally
	 * process them.
	 * 
	 * @return Collection of Model TypeInfos
	 */
	protected Collection<ModelTypeInfo> processProject() {
		Collection<ModelTypeInfo> ModelTypeInfos = new ArrayList<ModelTypeInfo>();
		this.processContainer(this.iProject, ModelTypeInfos);
		return ModelTypeInfos;
	}

	/**
	 * Processes a container. This method looks in the container's children for
	 * files and folders. If folder is found, recursive process is done. If file
	 * is found and is an Model Resource, it is processed.
	 * 
	 * @param iContainer
	 *            IContainer
	 * @param ModelTypeInfos
	 *            Collection of ModelTypeInfos
	 */
	protected void processContainer(IContainer iContainer,
			Collection<ModelTypeInfo> ModelTypeInfos) {
		try {
			for (IResource sub : iContainer.members())
				if (sub instanceof IContainer) {
					this.processContainer((IContainer) sub, ModelTypeInfos);
				} else if (sub instanceof IFile) {
					IFile file = (IFile) sub;
					if (file.getFileExtension() != null
							&& ModelTypeProcessor.fileExtension.equals(file
									.getFileExtension())) {
						URI uri = URI.createPlatformResourceURI(file
								.getFullPath().toString(), true);
						Resource resource = resourceSet.createResource(uri);
						if (resource != null) {
							if (!resource.isLoaded()) {
								try {
									resource.load(Collections.EMPTY_MAP);
								} catch (Exception e) {
									ModelActivator
											.getDefault()
											.logError(
													"An Exception has been thrown while "
															+ "loading IResources in IContainer",
													e);
								}
							}
							if (!resource.isLoaded()) {
								return;
							}
						}
						ModelTypeInfos.addAll(new ModelResourceProcessor()
								.lookForTypesInResource(resource));
					}
				}
		} catch (CoreException e) {
			ModelActivator.getDefault().logError(
					"An Exception has been thrown while "
							+ "listing IResources in IContainer", e);
		}
	}

}
