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
package org.parallelj.extensions.data.generator.wizard.factory;

import java.util.HashMap;

import org.eclipse.swt.widgets.Shell;
import org.parallelj.extensions.data.generator.wizard.dialog.PackageSelectionDialog.DialogData;
import org.parallelj.extensions.data.generator.wizard.subdialog.ParallelJPackageDialog;
import org.parallelj.extensions.data.generator.wizard.subdialog.ParallelJSubDialog;


/**
 * this class is a factory of Sub Dialogs
 * 
 * @author A169104
 *
 */
public class SubDialogFactory 
{
	private static SubDialogFactory subDialogFactory;
	private SubDialogFactory(){}
	
	/**
	 * 
	 * @return SubDialogFactory
	 * this method returns factory for Sub Dialog selection
	 */
	
	public static SubDialogFactory getFactoryInstance()
	{
		if(subDialogFactory == null)
			return new SubDialogFactory();
		else
			return subDialogFactory;
	}
	
	/**
	 * 
	 * @param dialogType
	 * @param parentShell
	 * @param dialogMasterData
	 * @return XAEasyFlowSubDialog
	 * this method returns the Sub Dialog instance based on the dialog type in the argument list
	 */
	
	public ParallelJSubDialog getDialog(String dialogType,Shell parentShell,DialogData dialogData, HashMap<String, Object> params)
	{
		if(dialogType.equals("PackageDialog"))
		{
			return new ParallelJPackageDialog(parentShell ,dialogData);
		}else
		{
			return null;
		}
	}
}
