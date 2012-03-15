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
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.parallelj.designer.extension.edit.parts.BusinessProcedureExtendedEditPart;
import org.parallelj.model.JoinType;
import org.parallelj.model.ParallelJPackage;
import org.parallelj.model.SplitType;

public class BusinessProcedureAdapter extends AdapterImpl {

	/**
	 * Involved AbstractGraphicalEditPart, wrapping EObject to modify.
	 */
	private AbstractGraphicalEditPart editPart;

	/**
	 * Created new adapter for ProcedureExtendedEditPart passed as parameter.
	 * 
	 * @param editPart
	 *            ProcedureExtendedEditPart
	 */
	public BusinessProcedureAdapter(AbstractGraphicalEditPart editPart) {
		this.editPart = editPart;
	}

	/**
	 * Notifies when specific attribute/reference is modified. Here, function is
	 * triggered for changing SPILT/JOIN icon, when any link (incoming/outgoing)
	 * added/removed from diagram or any change in SPLIT or JOIN property.
	 * 
	 * @param notification
	 */
	@Override
	public final void notifyChanged(Notification notification) {

		// if any link (incoming/outgoing) added/removed than update the SPILT
		// and JOIN icon
		if (notification.getEventType() == Notification.ADD
				|| notification.getEventType() == Notification.REMOVE) {
			((BusinessProcedureExtendedEditPart) this.editPart)
					.updateSplitJoin();
		}
		// if any value set from property view.
		else if (notification.getEventType() == Notification.SET) {
			Object currentFeature = notification.getFeature();
			// if JOIN value set/changed, update JOIN icon
			if (currentFeature instanceof EAttribute
					&& currentFeature == ParallelJPackage.eINSTANCE
							.getProcedure_Join()) {
				JoinType newJoin = (JoinType) notification.getNewValue();
				((BusinessProcedureExtendedEditPart) this.editPart)
						.setJoinIcon(newJoin.getName());
			}
			// if SPLIT value set/changed, update SPLIT icon
			else if (currentFeature instanceof EAttribute
					&& currentFeature == ParallelJPackage.eINSTANCE
							.getProcedure_Split()) {
				SplitType newSplit = (SplitType) notification.getNewValue();
				((BusinessProcedureExtendedEditPart) this.editPart)
						.setSplitIcon(newSplit.getName());
			} else if (currentFeature instanceof EAttribute
					&& currentFeature == ParallelJPackage.eINSTANCE
							.getNamedElement_Name()
					&& notification.getOldValue() != null) {
				((BusinessProcedureExtendedEditPart) this.editPart)
						.updatePreference(
								notification.getNewValue().toString(),
								notification.getOldValue().toString());
			}
		}
	}
}
