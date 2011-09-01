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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.parallelj.designer.edit.parts.PipelinePipelineCompartmentEditPart;
import org.parallelj.designer.edit.parts.PipelineProcedureEditPart;
import org.parallelj.designer.extension.edit.parts.PipelineExtendedEditPart;
import org.parallelj.designer.extension.tools.BoundsRefreshment;
import org.parallelj.model.JoinType;
import org.parallelj.model.ParallelJPackage;
import org.parallelj.model.Pipeline;
import org.parallelj.model.Procedure;
import org.parallelj.model.SplitType;
import org.parallelj.model.impl.LinkImpl;
import org.parallelj.model.impl.ProcedureImpl;

public class PipelineAdapter extends AdapterImpl {

	/**
	 * Comparator for PipelineProcedureEditPart, to sort according to location
	 */
	public class NodeComparator implements
			Comparator<PipelineProcedureEditPart> {

		@Override
		public int compare(PipelineProcedureEditPart firstObject,
				PipelineProcedureEditPart secondObject) {
			int y1 = firstObject.getLocation().y;
			int y2 = secondObject.getLocation().y;
			return (y1 > y2 ? 1 : (y1 == y2 ? 0 : -1));
		}
	}

	/**
	 * Involved AbstractGraphicalEditPart, wrapping EObject to modify.
	 */
	private AbstractGraphicalEditPart editPart;

	/**
	 * Created new adapter for PipelineExtendedEditPart passed as parameter.
	 * 
	 * @param editPart
	 *            PipelineExtendedEditPart
	 */
	public PipelineAdapter(AbstractGraphicalEditPart editPart) {
		this.editPart = editPart;
	}

	/**
	 * Notifies when specific attribute/reference is modified, like adding /
	 * removing Procedure in Pipeline or adding removing incoming and outgoing
	 * links.
	 * 
	 * @param notification
	 */
	@Override
	public final void notifyChanged(Notification notification) {
		PipelineExtendedEditPart pipelineExtendedEditPart = (PipelineExtendedEditPart) this.editPart;
		Pipeline pipeline = null;

		// To avoid null reference issue after Ctrl-Z
		if (((View) pipelineExtendedEditPart.getModel()).getElement() instanceof Pipeline) {
			pipeline = (Pipeline) (((View) pipelineExtendedEditPart.getModel())
					.getElement());
		}

		if (pipeline != null
				&& (notification.getEventType() == Notification.ADD || notification
						.getEventType() == Notification.REMOVE)) {

			// if any link (incoming/outgoing) added/removed than update the
			// SPILT and JOIN icon
			if (notification.getNewValue() instanceof LinkImpl
					|| notification.getOldValue() instanceof LinkImpl) {
				pipelineExtendedEditPart.updateSplitJoin();
			}
			// if Procedure is added or removed from Pipeline
			else if (notification.getNewValue() instanceof ProcedureImpl
					|| notification.getOldValue() instanceof ProcedureImpl) {
				int height = 0;

				// pipeline height adjust according to number of procedure in
				// it.
				if (pipeline.getProcedures().size() > 1) {
					height = 89 + ((pipeline.getProcedures().size() - 1) * 45);
				}

				// for deletion or addition, since it can be multiple add or
				// removal of procedures, updating sequence in pipeline
				// accordingly
				for (Object object : editPart.getChildren()) {
					if (object instanceof PipelinePipelineCompartmentEditPart) {
						PipelinePipelineCompartmentEditPart compartmentEditPart = (PipelinePipelineCompartmentEditPart) object;
						List children = compartmentEditPart.getChildren();
						this.updateSequence(notification, children, pipeline);
					}
				}

				// based on height value refresh the bounds
				if (height > 0) {
					BoundsRefreshment.refreshBounds(pipelineExtendedEditPart,
							null, height);
				} else {
					BoundsRefreshment.refreshBounds(pipelineExtendedEditPart,
							null, 89);
				}
			}
		}
		// if any value set from property view.
		else if (notification.getEventType() == Notification.SET) {
			Object currentFeature = notification.getFeature();
			// if JOIN value set/changed, update JOIN icon
			if (currentFeature instanceof EAttribute
					&& currentFeature == ParallelJPackage.eINSTANCE
							.getProcedure_Join()) {
				JoinType newJoin = (JoinType) notification.getNewValue();
				pipelineExtendedEditPart.setJoinIcon(newJoin.getName());
			}
			// if SPLIT value set/changed, update SPLIT icon
			else if (currentFeature instanceof EAttribute
					&& currentFeature == ParallelJPackage.eINSTANCE
							.getProcedure_Split()) {
				SplitType newSplit = (SplitType) notification.getNewValue();
				pipelineExtendedEditPart.setSplitIcon(newSplit.getName());
			}
		}
		// when procedures sequence property changed from property view
		else if (notification.getEventType() == Notification.MOVE
				&& pipeline != null) {

			EList<Procedure> procedures = pipeline.getProcedures();

			// getting source and destination position
			int oldPosition = ((Integer) notification.getOldValue()).intValue();
			int newPosition = notification.getPosition();

			for (Object object : editPart.getChildren()) {
				if (object instanceof PipelinePipelineCompartmentEditPart) {
					PipelinePipelineCompartmentEditPart compartmentEditPart = (PipelinePipelineCompartmentEditPart) object;
					List children = compartmentEditPart.getChildren();

					// To avoid index out of bound issue after Ctrl-Z
					if (!(oldPosition > (children.size() - 1) || newPosition > (children
							.size() - 1))) {
						// sorting with location
						Collections.sort(children, new NodeComparator());

						// switch the position
						this.switchPosition(children, procedures, newPosition,
								oldPosition);
					}
				}
			}
		}
	}

	/**
	 * Updating sequence of pipelined procedure after addition or removal.
	 * 
	 * @param notification
	 * @param pipeline
	 * @param children
	 */
	private void updateSequence(Notification notification, List children,
			Pipeline pipeline) {
		for (Procedure procedure : pipeline.getProcedures()) {
			for (Object innerObject : children) {
				PipelineProcedureEditPart editPart = (PipelineProcedureEditPart) innerObject;
				Procedure innerProcedure = (Procedure) ((View) editPart
						.getModel()).getElement();
				// when property view procedure list index and graphical node
				// match, calculate the new position accordingly
				if (procedure.equals(innerProcedure)) {
					int yPosition = pipeline.getProcedures().indexOf(procedure) * 45;
					Point newLocation = new Point(0, yPosition);
					((GraphicalEditPart) editPart.getParent())
							.setLayoutConstraint(editPart,
									editPart.getFigure(), new Rectangle(
											newLocation, editPart.getFigure()
													.getSize()));
					editPart.setStructuralFeatureValue(
							NotationPackage.eINSTANCE.getLocation_Y(),
							newLocation.y);
					editPart.setStructuralFeatureValue(
							NotationPackage.eINSTANCE.getLocation_X(), 0);
					break;

				}
			}
		}
	}

	/**
	 * Switch procedures position
	 * 
	 * @param children
	 * @param procedures
	 * @param newPosition
	 * @param oldPosition
	 */
	private void switchPosition(List children, EList<Procedure> procedures,
			int newPosition, int oldPosition) {

		// getting older object from location
		Object inPlaceObject = children.get(newPosition);
		Object selectedObject = children.get(oldPosition);

		if (inPlaceObject instanceof PipelineProcedureEditPart
				&& selectedObject instanceof PipelineProcedureEditPart) {

			PipelineProcedureEditPart inPlaceProcedureEditPart = (PipelineProcedureEditPart) inPlaceObject;
			PipelineProcedureEditPart selectedProcedureEditPart = (PipelineProcedureEditPart) selectedObject;

			// taking location
			Point newLocation = inPlaceProcedureEditPart.getLocation();
			Point oldLocation = selectedProcedureEditPart.getLocation();

			// taking procedure
			Procedure selectedProcedure = (Procedure) ((View) selectedProcedureEditPart
					.getModel()).getElement();
			Procedure inPlaceProcedure = (Procedure) ((View) inPlaceProcedureEditPart
					.getModel()).getElement();

			// Checking is switching of positioning happening for right object
			// or not, than switch the location of objects,
			if (selectedProcedure.equals(procedures.get(newPosition))
					&& inPlaceProcedure.equals(procedures.get(oldPosition))) {
				((GraphicalEditPart) inPlaceProcedureEditPart.getParent())
						.setLayoutConstraint(inPlaceProcedureEditPart,
								inPlaceProcedureEditPart.getFigure(),
								new Rectangle(oldLocation,
										inPlaceProcedureEditPart.getFigure()
												.getSize()));

				inPlaceProcedureEditPart.setStructuralFeatureValue(
						NotationPackage.eINSTANCE.getLocation_Y(),
						oldLocation.y);
				inPlaceProcedureEditPart.setStructuralFeatureValue(
						NotationPackage.eINSTANCE.getLocation_X(), 0);

				((GraphicalEditPart) selectedProcedureEditPart.getParent())
						.setLayoutConstraint(selectedProcedureEditPart,
								selectedProcedureEditPart.getFigure(),
								new Rectangle(newLocation,
										selectedProcedureEditPart.getFigure()
												.getSize()));

				selectedProcedureEditPart.setStructuralFeatureValue(
						NotationPackage.eINSTANCE.getLocation_Y(),
						newLocation.y);
				selectedProcedureEditPart.setStructuralFeatureValue(
						NotationPackage.eINSTANCE.getLocation_X(), 0);
			}
		}
	}
}
