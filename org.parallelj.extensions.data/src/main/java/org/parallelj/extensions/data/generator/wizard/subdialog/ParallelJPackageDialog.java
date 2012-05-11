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
package org.parallelj.extensions.data.generator.wizard.subdialog;

import java.util.ArrayList;

import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.swt.widgets.Shell;
import org.parallelj.extensions.data.generator.logs.Messages;
import org.parallelj.extensions.data.generator.wizard.dialog.PackageSelectionDialog.DialogData;
import org.parallelj.extensions.data.generator.wizard.filter.PackageFilter;

/**
 * Package Selection Dialog
 * 
 * @author A169104
 * 
 */
public class ParallelJPackageDialog extends ParallelJSubDialog {

	/**
	 * create Package Selection Dialog
	 * 
	 * @param parentShell
	 * @param dialogMasterData
	 *            : dialogMasterData contains parent dialog information
	 */

	public ParallelJPackageDialog(Shell parentShell, DialogData dialogMasterData) {
		super(parentShell, dialogMasterData);
	}

	/**
	 * this method returns Package Fragments to display in the table viewer
	 * return java.util.List<IPackageFragment> returns a list of PackageFragment
	 */
	@Override
	protected java.util.List<IPackageFragment> getObjectsList() {
		java.util.List<IPackageFragment> packageName;
		packageName = new ArrayList<IPackageFragment>();
		packageName = new PackageFilter().getPackageFragments(dialogData
				.getJavaProject());
		return packageName;
	}

	/**
	 * returns Package Dialog Label
	 */
	@Override
	protected String getLabel() {
		return Messages.PACKAGE_DIALOG_LABEL.message();
	}

	/**
	 * returns Package Dialog Title
	 */
	@Override
	protected String getTitle() {
		return Messages.PACKAGE_DIALOG_TITLE.message();
	}
}
