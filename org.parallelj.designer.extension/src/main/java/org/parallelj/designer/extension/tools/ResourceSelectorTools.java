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
package org.parallelj.designer.extension.tools;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

/**
 * Utility for Resource Selector to convert URI to Path or get File for URI
 * 
 */
public class ResourceSelectorTools {

	private ResourceSelectorTools() {
	}

	/**
	 * Converts EMF URI into Eclipse Path
	 * 
	 * @param uri
	 *            : EMF URI
	 * @return Eclipse IPath
	 */
	public static IPath convertURIToPath(URI uri) {
		URI tempURI = uri.trimFragment();
		String scheme = tempURI.scheme();
		if (scheme != null && scheme.endsWith("platform")) {
			String segment0 = tempURI.segment(0);
			if (segment0 != null && segment0.equals("resource")) {
				URI deresolver = URI.createPlatformResourceURI("/", false);
				String pathAsString = tempURI.deresolve(deresolver).toString();
				return new Path(pathAsString);
			}
		}
		return new Path(tempURI.toString());
	}

	/**
	 * Retrieves the Eclipse file containing the Element specified by the EMF
	 * URI
	 * 
	 * @param uri
	 *            : EMF URI
	 * @return IFile
	 */
	public static IFile getFileForURI(URI uri) {
		IPath path = ResourceSelectorTools.convertURIToPath(uri);
		if (path == null)
			return null;
		return ResourcesPlugin.getWorkspace().getRoot().getFile(path);
	}

	/**
	 * Retrieves the Eclipse Project containing the Element specified by the EMF
	 * URI
	 * 
	 * @param uri
	 *            : EMF URI
	 * @return IProject
	 */
	public static IProject getProjectForURI(URI uri) {
		IFile file = ResourceSelectorTools.getFileForURI(uri);
		if (file == null)
			return null;
		return file.getProject();
	}

	/**
	 * Retrieves the JavaProject containing this EObject
	 * 
	 * @param eObj
	 *            input EObject
	 * 
	 * @return javaProject Associated java project
	 */
	public static final IJavaProject getJavaProjectFromEObject(EObject eObj) {
		IProject projectForURI = ResourceSelectorTools.getProjectForURI(eObj
				.eResource().getURI());
		if (projectForURI == null)
			return null;
		return JavaCore.create(projectForURI);
	}

}
