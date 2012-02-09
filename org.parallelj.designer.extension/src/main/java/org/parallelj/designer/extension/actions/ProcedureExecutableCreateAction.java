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
package org.parallelj.designer.extension.actions;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.parallelj.designer.extension.tools.ExecutableTools;
import org.parallelj.model.ParallelJPackage;

/**
 * Action based on AbstractDiagramPopupAction, for creating Executable for
 * Procedure and sub-type (but the Handler and Block)
 */
public class ProcedureExecutableCreateAction extends AbstractDiagramPopupAction {

	private AbstractGraphicalEditPart agep;

	@Override
	protected Object getValue(EObject source, ActionID actionID) {

		return null;
	}

	@Override
	public void run(IAction action) {

		super.run(action);
		EObject source = null;
		Object model = this.agep.getModel();
		if (model instanceof View) {
			source = ((View) model).getElement();
		}
		if (source == null) {
			return;
		}

		TransactionalEditingDomain ted = TransactionUtil
				.getEditingDomain(source);

		String newExecutableValue = ExecutableTools
				.getExecutableValueFromClassWizard(source);
		if (newExecutableValue != null) {
			SetCommand setCommand = new SetCommand(ted, source,
					ParallelJPackage.eINSTANCE.getProcedure_Executable(),
					newExecutableValue);
			ted.getCommandStack().execute(setCommand);
		}

	}

	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			Object o = ((IStructuredSelection) selection).getFirstElement();
			if (o instanceof AbstractGraphicalEditPart) {
				this.agep = (AbstractGraphicalEditPart) o;
			}
		}
		action.setEnabled(this.agep != null);
	}

}
