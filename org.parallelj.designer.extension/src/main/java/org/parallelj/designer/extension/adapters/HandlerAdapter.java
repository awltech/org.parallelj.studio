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
package org.parallelj.designer.extension.adapters;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.parallelj.designer.extension.edit.parts.HandlerExtendedEditPart;
import org.parallelj.model.ParallelJPackage;
import org.parallelj.model.SplitType;

public class HandlerAdapter extends AdapterImpl {

	/**
	 * Involved AbstractGraphicalEditPart, wrapping EObject to modify.
	 */
	private AbstractGraphicalEditPart editPart;

	/**
	 * Created new adapter for HandlerExtendedEditPart passed as parameter.
	 * 
	 * @param editPart
	 *            HandlerExtendedEditPart
	 */
	public HandlerAdapter(AbstractGraphicalEditPart editPart) {
		this.editPart = editPart;
	}

	/**
	 * Notifies when specific attribute/reference is modified. Here, function is
	 * triggered for changing SPILT icon, when any link (outgoing) added/removed
	 * from diagram or any change in SPLIT property. This also triggered when
	 * any procedure added or removed, to highlight the same or clear highlight.
	 * 
	 * @param notification
	 */
	@Override
	public final void notifyChanged(Notification notification) {

		// if any link (outgoing) added/removed than update the SPILT
		// icon and if any procedure added or removed then highlight them
		// accordingly
		if (notification.getEventType() == Notification.ADD
				|| notification.getEventType() == Notification.REMOVE
				|| notification.getEventType() == Notification.REMOVE_MANY) {
			Object currentFeature = notification.getFeature();
			HandlerExtendedEditPart handlerExtendedEditPart = (HandlerExtendedEditPart) this.editPart;
			if (currentFeature instanceof EReference
					&& currentFeature == ParallelJPackage.eINSTANCE
							.getHandler_Procedures()) {

				// if handler itself removed
				if (handlerExtendedEditPart.getHandler() == null) {
					EditPart parent = handlerExtendedEditPart.getParent();
					handlerExtendedEditPart.clearAllSelection(parent);
				}
				// removed procedures from handlers
				else {
					handlerExtendedEditPart.findLinkedProcedures();
				}
			} else {
				handlerExtendedEditPart.updateSplitJoin();
			}
		}
		// if any value set from property view.
		else if (notification.getEventType() == Notification.SET) {
			Object currentFeature = notification.getFeature();
			// if SPLIT value set/changed, update SPLIT icon
			if (currentFeature instanceof EAttribute
					&& currentFeature == ParallelJPackage.eINSTANCE
							.getProcedure_Split()) {
				SplitType newSplit = (SplitType) notification.getNewValue();
				((HandlerExtendedEditPart) this.editPart).setSplitIcon(newSplit
						.getName());
			}
		}
	}
}
