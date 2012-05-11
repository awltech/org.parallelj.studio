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
package org.parallelj.extensions.data.generator.wizard.filter;

import java.util.ArrayList;

import org.eclipse.core.resources.IFolder;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.parallelj.extensions.data.generator.Activator;
import org.parallelj.extensions.data.generator.wizard.dialog.WizardConstants;

/**
 * this class used to return filtered package fragments
 * 
 * @author A169104
 * 
 */
public class PackageFilter {
	/**
	 * this method is used to find out the Package fragments
	 * 
	 * @param javaProject
	 *            :javaProject contains the selected project specific
	 *            information
	 * @return java.util.List<IPackageFragment>
	 * 
	 */

	public java.util.List<IPackageFragment> getPackageFragments(
			IJavaProject javaProject) {
		java.util.List<IPackageFragment> lPackage = new ArrayList<IPackageFragment>();
		IFolder mainSourceFolder = javaProject.getProject().getFolder(
				WizardConstants.TARGET_PACKAGE_SOURCE);
		IPackageFragmentRoot mainPackageFragmentRoot = javaProject
				.getPackageFragmentRoot(mainSourceFolder);

		try {
			if (mainPackageFragmentRoot != null) {
				for (IJavaElement child : mainPackageFragmentRoot.getChildren()) {
					if (child instanceof IPackageFragment) {
						lPackage.add((IPackageFragment) child);
					}
				}
			}
		} catch (JavaModelException e) {
			Activator.sendErrorToErrorLog(new StringBuilder()
					.append(this.getClass()).append(" Error:")
					.append(e.getMessage()).toString());
		} catch (Exception e) {
			Activator.sendErrorToErrorLog(new StringBuilder()
					.append(this.getClass()).append(" Error:")
					.append(e.getMessage()).toString());
		}

		return lPackage;
	}
}
