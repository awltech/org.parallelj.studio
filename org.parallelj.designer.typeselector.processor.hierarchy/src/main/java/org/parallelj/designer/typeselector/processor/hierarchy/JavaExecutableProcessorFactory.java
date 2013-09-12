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
package org.parallelj.designer.typeselector.processor.hierarchy;

import org.eclipse.core.resources.IProject;
import org.eclipselabs.resourceselector.core.processor.ResourceProcessor;
import org.eclipselabs.resourceselector.core.processor.ResourceProcessorFactory;

/**
 * Resource Processor Extension for Java Hierarchy management
 * 
 */
public class JavaExecutableProcessorFactory implements
		ResourceProcessorFactory {

	/**
	 * Class that will be used to determine filtering in Scope
	 */
	private Class<?> clazz;

	/**
	 * Constructor that provides Class to filter on.
	 */
	public JavaExecutableProcessorFactory(Class<?> clazz) {
		this.clazz = clazz;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipselabs.resourceselector.core.processor.TypeProcessorFactory#
	 * createTypeProcessor(org.eclipse.core.resources.IProject,
	 * java.lang.String)
	 */
	public ResourceProcessor createResourceProcessor(IProject project,
			String pattern) {
		JavaExecutableProcessor javaExecutableProcessor = new JavaExecutableProcessor(
				project, pattern);
		javaExecutableProcessor.setTopHierarchyClass(this.clazz);
		return javaExecutableProcessor;
	}

}
