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

import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.parallelj.designer.edit.parts.SpecificationEditPart;
import org.parallelj.designer.part.ParallelJDiagramEditorPlugin;
import org.parallelj.designer.part.ParallelJVisualIDRegistry;
import org.parallelj.model.Link;
import org.parallelj.model.Specification;

/**
 * Abstract Adapter, to force diagram's parts refreshment, when specific
 * attribute/reference is modified.
 */
public abstract class AbstractRefreshmentAdapter extends AdapterImpl {

	/**
	 * Involved AbstractGraphicalEditPart, wrapping involved EObject.
	 */
	private AbstractGraphicalEditPart editPart;

	/**
	 * Involved EObject.
	 */
	private EObject eObject;

	/**
	 * Created new Refreshment adapter for AbstractGraphicalEditPart passed as
	 * parameter.
	 * 
	 * @param editPart
	 *            AbstractGraphicalEditPart
	 */
	public AbstractRefreshmentAdapter(AbstractGraphicalEditPart editPart) {
		this.editPart = editPart;
		Object model = this.editPart.getModel();
		if (model instanceof View) {
			this.eObject = ((View) model).getElement();
		}
	}

	/**
	 * Notifies when specific attribute/reference is modified. This method will
	 * force diagram parts to refresh.
	 * 
	 * @param notification
	 */
	@Override
	public void notifyChanged(Notification notification) {
		boolean canPerform = false;
		for (int i = 0; i < this.getNotificationEventType().length
				&& !canPerform; i++) {
			if (this.getNotificationEventType()[i] == notification
					.getEventType()) {
				canPerform = true;
			}
		}

		// if any change in attribute/reference then refresh the diagram
		if (this.eObject != null && canPerform) {
			Object currentFeature = notification.getFeature();
			if (this.getFeature().equals(currentFeature)) {
				Object oldValue = notification.getOldValue();
				Object newValue = notification.getNewValue();
				if (newValue != null && !newValue.equals(oldValue)) {
					this.refresh();
				} else if (oldValue != null && !oldValue.equals(newValue)) {
					this.refresh();
				}
			}
		}
	}

	/**
	 * Refreshes all elements that should be refreshed, starting from Root.
	 */
	private void refresh() {
		EditPart root = this.editPart;
		while (root.getParent() != null) {
			root = root.getParent();
		}
		this.refresh(root);
	}

	/**
	 * Refreshes all elements that should be refreshed, starting from EditPart
	 * passed as parameter.
	 * 
	 * @param editPart
	 */
	private void refresh(EditPart editPart) {
		Object o = editPart.getModel();
		if (o instanceof View) {
			int visualID = ParallelJVisualIDRegistry.getVisualID((View) o);
			boolean loop = true;
			for (int i = 0; i < this.getVisualIDsOfEditPartsToRefresh().length
					&& loop; i++) {
				if (visualID == this.getVisualIDsOfEditPartsToRefresh()[i]) {
					try {
						View view = (View) editPart.getModel();
						EObject eObj = view.getElement();
						if (eObj instanceof Link) {
							editPart.refresh();
						} else if (!(eObj instanceof Specification)
								&& editPart.getParent() != null
								&& editPart.getParent().getParent() != null) {

							editPart.refresh();
						} else if (ParallelJVisualIDRegistry.getVisualID(view) == SpecificationEditPart.VISUAL_ID) {
							editPart.refresh();
						}
					} catch (Exception e) {
						ParallelJDiagramEditorPlugin.getInstance().logError(
								"Error while refreshing element", e);
					}
					loop = false;
				}
			}
		}
		// Refresh all connections children
		if (editPart instanceof GraphicalEditPart) {
			Iterator<?> connectionsIterator = ((GraphicalEditPart) editPart)
					.getSourceConnections().iterator();
			while (connectionsIterator.hasNext()) {
				AbstractGraphicalEditPart edgePart = (AbstractGraphicalEditPart) connectionsIterator
						.next();
				this.refresh(edgePart);
			}
		}
		// Refresh all direct children
		for (Object child : editPart.getChildren()) {
			if (child instanceof EditPart) {
				this.refresh((EditPart) child);
			}
		}
	}

	/**
	 * Returns all the VisualIDs corresponding to EditParts that should be
	 * refreshed by this adapter.
	 * 
	 * @return array of VisualIDs
	 */
	protected abstract int[] getVisualIDsOfEditPartsToRefresh();

	/**
	 * @return EStructural Feature to adapt
	 */
	protected abstract EStructuralFeature getFeature();

	/**
	 * Default Notification Event Types enabled.
	 */
	private static final int[] DEFAULT_NOTIFICATION_EVENT_TYPES = new int[] {
			Notification.SET, Notification.ADD, Notification.REMOVE,
			Notification.MOVE };

	/**
	 * @return Notification Event Types enabled. This method has to be
	 *         subclassed for modifying Element Types that should execute
	 *         notification.
	 */
	protected int[] getNotificationEventType() {
		return AbstractRefreshmentAdapter.DEFAULT_NOTIFICATION_EVENT_TYPES;
	}

}
