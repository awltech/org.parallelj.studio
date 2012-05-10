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
package org.parallelj.extensions.data.generator.wizard.util;

import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.parallelj.extensions.data.generator.Activator;
import org.parallelj.extensions.data.generator.wizard.dialog.WizardConstants;

/**
 * @author A169104
 * 
 */
public class CustomLabelProvider extends LabelProvider implements
		ITableLabelProvider {
	private static Image returnImage = null;

	/**
	 * return Image this method returns the label image for the given column of
	 * the given element
	 */

	public Image getColumnImage(Object element, int columnIndex) {
		if (element != null && element instanceof IPackageFragment) {
			try {
				if (((IPackageFragment) element).containsJavaResources()) {
					returnImage = Activator.getDefault().getImage(
							WizardConstants.PACKAGE_IMAGE);
				} else {
					returnImage = Activator.getDefault().getImage(
							WizardConstants.DEFAULTPACKAGE_IMAGE);
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
		}

		return returnImage;
	}

	/**
	 * return String this method returns the label text for the given column of
	 * the given element.
	 */

	public String getColumnText(Object element, int columnIndex) {
		String fileOrPackageName = null;
		if (element != null && element instanceof IPackageFragment) {
			if (((IPackageFragment) element).getElementName().equals("")) {
				fileOrPackageName = "  (default package)";
			} else {
				fileOrPackageName = "  "
						+ ((IPackageFragment) element).getElementName()
								.toString();
			}
		}
		return fileOrPackageName;
	}
}
