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
package org.parallelj.designer.validation.actions;

import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.parallelj.designer.edit.parts.SpecificationEditPart;
import org.parallelj.designer.validation.DiagramValidationPlugin;

/**
 * This EnableLiveValidationDelegate class sets an object action that is
 * contributed into a popup menu for a view or editor and performs the Live
 * Validation on user's action checks
 * 
 */
public class EnableLiveValidationAction implements IObjectActionDelegate {

	/**
	 * shell
	 */
	protected Shell shell = null;

	/**
	 * diagram
	 */
	protected Diagram diagram = null;

	/**
	 * Constructor for DiagramValidationMenu Action.
	 */
	public EnableLiveValidationAction() {
		super();
	}

	/**
	 * IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.shell = targetPart.getSite().getShell();
	}

	/**
	 * IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		// Sets the enablement state of the Live Validation in the plugin
		// according to the check state
		DiagramValidationPlugin.getDefault().setLiveValidationEnabled(action.isChecked());
	}

	/**
	 * IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.diagram = null;
		Object selected = ((IStructuredSelection) selection).getFirstElement();

		if (selected instanceof SpecificationEditPart) {
			this.diagram = ((SpecificationEditPart) selected).getDiagramView();
		}
		action.setEnabled(this.diagram != null);
	}

}
