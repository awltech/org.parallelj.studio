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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.parallelj.designer.part.ParallelJDiagramEditorPlugin;

/**
 * 
 * Abstract class, designed to ease the management of Popup Extensions related
 * to this diagram extension.
 */
public abstract class AbstractDiagramPopupAction implements
		IObjectActionDelegate {

	/**
	 * Abstract Graphical Edit Part corresponding to the element the user
	 * clicked-on
	 */
	private AbstractGraphicalEditPart agep;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.
	 * action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@SuppressWarnings("unchecked")
	public void run(IAction action) {
		try {
			ActionID actionID = ActionID.createFromLabel(action.getId());
			if (actionID == null) {
				return;
			}

			EStructuralFeature feature = actionID.getFeatureToUpdate();
			if (feature == null) {
				return;
			}

			EObject source = null;
			Object model = this.agep.getModel();
			if (model instanceof View) {
				source = ((View) model).getElement();
			}
			if (source == null) {
				return;
			}

			Object newValue = this.getValue(source, actionID);
			if (newValue == null) {
				return;
			}

			if ("".equals(newValue)) {
				newValue = null;
			}

			if (feature.isMany()) {
				EList elist = (EList) source.eGet(feature);
				if (elist.contains(newValue)) {
					return;
				}
			} else {
				Object current = source.eGet(feature);
				if (current == null && newValue == null) {
					return;
				}
				if (current != null && current.equals(newValue)) {
					return;
				}
			}

			TransactionalEditingDomain ted = TransactionUtil
					.getEditingDomain(source);
			if (ted == null) {
				if (feature.isMany()) {
					((EList<Object>) source.eGet(feature)).add(newValue);
				} else {
					source.eSet(feature, newValue);
				}
			} else if (feature.isMany()) {
				AddCommand addCommand = new AddCommand(ted, source, feature,
						newValue);
				ted.getCommandStack().execute(addCommand);
			} else {
				SetCommand setCommand = new SetCommand(ted, source, feature,
						newValue);
				ted.getCommandStack().execute(setCommand);
			}
		} catch (Exception e) {
			ParallelJDiagramEditorPlugin.getInstance().logError(
					"Error "
							+ "while Executing Extended Diagram Popup Action ("
							+ this.getClass().getCanonicalName() + ")", e);
		}
	}

	/**
	 * Returns the user value to be applied by this action, according to EObject
	 * selected and Action ID
	 * 
	 * @param source
	 *            : EObject selected
	 * @param actionID
	 *            : Action ID
	 * @return value
	 */
	protected abstract Object getValue(EObject source, ActionID actionID);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action
	 * .IAction, org.eclipse.jface.viewers.ISelection)
	 */
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
