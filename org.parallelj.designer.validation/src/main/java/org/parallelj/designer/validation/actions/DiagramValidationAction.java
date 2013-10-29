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
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.parallelj.designer.edit.parts.SpecificationEditPart;
import org.parallelj.designer.validation.DiagramValidationEngine;
import org.parallelj.designer.validation.DiagramValidationPlugin;

/**
 * This DiagramValidationMenu class sets an object action that is contributed
 * into a popup menu for a view or editor and adds an initialization method for
 * connecting the delegate to the part.
 */
public class DiagramValidationAction implements IObjectActionDelegate {

	/**
	 * Diagram
	 */
	private Diagram diagram;

	/**
	 * Constructor for DiagramValidationMenu Action.
	 */
	public DiagramValidationAction() {
		super();
	}

	/**
	 * IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	/**
	 * IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		try {
			DiagramValidationEngine.runValidation(this.diagram);
		} catch (Throwable t) {
			DiagramValidationPlugin
					.getDefault()
					.logError(
							"An exception has been thrown while validating Control Flow diagram",
							t);

		}
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
