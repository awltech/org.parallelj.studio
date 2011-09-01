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
package org.parallelj.common.jdt.helpers;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

public class PackageFragmentHelper {

	/**
	 * Return the IPackageFragment corresponding to the Path
	 * 
	 * @param Path
	 * @return
	 */
	public static IPackageFragment getPackageFragment(Path filePath)
			throws JavaModelException {

		IFile f = getFileForLocation(ResourcesPlugin.getWorkspace().getRoot(),
				filePath);

		if (f != null) {
			ICompilationUnit compilationUnit = JavaCore
					.createCompilationUnitFrom(f);
			IPackageFragment packageFragment = null;
			IJavaElement temp = compilationUnit;
			while (packageFragment == null && temp != null) {
				if (temp instanceof IPackageFragment)
					packageFragment = (IPackageFragment) temp;
				else
					temp = temp.getParent();
			}
			return packageFragment;
		}
		return null;
	}

	/**
	 * Method to replace the WorkspaceRoot.getFileForLocation() methods, since
	 * it is not able to retrieve the right file when projects are interlocked
	 * 
	 * @param workspaceRoot :
	 *            WorkspaceRoot Instance
	 * @param filePath :
	 *            File Path
	 * @return IFile if contained by lower level project.
	 */
	private static IFile getFileForLocation(IWorkspaceRoot workspaceRoot,
			IPath filePath) {
		if (filePath.equals(workspaceRoot.getFullPath()))
			return workspaceRoot.getFileForLocation(new Path("/"));
		IProject[] projects = workspaceRoot.getProjects();
		IFile myFile = null;
		int segmentsMatching = 0;
		for (IProject project : projects) {
			IPath projectLocation = project.getLocation();
			if (projectLocation.isPrefixOf(filePath)
					&& projectLocation.segmentCount() > segmentsMatching) {
				IPath fileLocalPath = filePath
						.removeFirstSegments(projectLocation.segmentCount());
				myFile = project.getFile(fileLocalPath);
				segmentsMatching = projectLocation.segmentCount();
			}
		}
		return myFile;
	}

}
