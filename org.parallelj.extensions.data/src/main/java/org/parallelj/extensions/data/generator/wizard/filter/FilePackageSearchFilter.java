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

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * this class is used for filtering package fragments or XSD files
 * 
 * @author A169104
 * 
 */
public class FilePackageSearchFilter extends ViewerFilter {

	private String searchString;

	/**
	 * 
	 * @param s
	 */

	public void setSearchText(String s) {
		this.searchString = s + ".*";
	}

	/**
	 * return true if element is included in the filtered set, and false if
	 * excluded
	 */

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (searchString == null || searchString.length() == 0) {
			return true;
		}
		if (element != null && element instanceof IFile) {
			if (((IFile) element).getProjectRelativePath().toString().trim()
					.matches(searchString)) {
				return true;
			}
		} else if (element != null && element instanceof IPackageFragment) {
			if (((IPackageFragment) element).getElementName().toString().trim()
					.matches(searchString)) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}
}
