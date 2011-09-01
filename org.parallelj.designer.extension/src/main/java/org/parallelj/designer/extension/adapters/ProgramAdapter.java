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
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.parallelj.designer.edit.parts.SpecificationEditPart;

public class ProgramAdapter extends AdapterImpl {

	/**
	 * Involved AbstractGraphicalEditPart, wrapping EObject to modify.
	 */
	private AbstractGraphicalEditPart editPart;

	/**
	 * Created new adapter for ProgramExtendedEditPart passed as parameter.
	 * 
	 * @param editPart
	 *            ProgramExtendedEditPart
	 */
	public ProgramAdapter(AbstractGraphicalEditPart editPart) {
		this.editPart = editPart;
	}

	/**
	 * Notifies when any object is added to program compartment node. Here
	 * visibility of Specification has been refreshed, specially for wizard.
	 * 
	 * @param notification
	 */
	@Override
	public final void notifyChanged(Notification notification) {

		// if any object is added to program
		if (notification.getEventType() == Notification.ADD) {

			EditPart root = this.editPart;
			// getting Specification part
			while (!(root instanceof SpecificationEditPart)) {
				root = root.getParent();
			}

			// refresh visibility
			((View) root.getModel()).setVisible(false);
			((View) root.getModel()).setVisible(true);
		}
	}
}
