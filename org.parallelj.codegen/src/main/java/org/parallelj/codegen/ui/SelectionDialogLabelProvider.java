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
package org.parallelj.codegen.ui;


import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.parallelj.codegen.Activator;

/**
 * Label provider for Model object (Represented by IFile)
 */
public class SelectionDialogLabelProvider extends LabelProvider implements ITableLabelProvider {

	public String getColumnText(Object object, int column) {
		if (column == 0) {
			return ((IFile) object).getName();
		} else {
			return "";
		}
	}

	public String getText(Object element) {
		return ((IFile) element).getName();
	}

	public Image getColumnImage(Object object, int column) {
		return Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/program.gif").createImage();
	}
}
