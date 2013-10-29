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
package org.parallelj.designer.extension.tools;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.parallelj.designer.extension.edit.parts.PipelineProcedureExtendedEditPart;
import org.parallelj.model.Pipeline;
import org.parallelj.model.Procedure;

public class BoundsRefreshment {

	private BoundsRefreshment() {
	}

	/**
	 * Allows fixing the width and / or height of the specified Part.
	 * 
	 * @param editPart
	 *            the Part whose sizes are to be fixed
	 * @param width
	 * @param height
	 */
	public static void refreshBounds(ShapeNodeEditPart editPart, Integer width,
			Integer height) {
		if (editPart.getParent() != null) {

			Integer innerWidth;
			Integer innerHeight;

			if (width == null) {
				innerWidth = ((Integer) editPart
						.getStructuralFeatureValue(NotationPackage.eINSTANCE
								.getSize_Width())).intValue();
			} else {
				innerWidth = width;
			}

			if (height == null) {
				innerHeight = ((Integer) editPart
						.getStructuralFeatureValue(NotationPackage.eINSTANCE
								.getSize_Height())).intValue();
			} else {
				innerHeight = height;
			}

			Dimension size = new Dimension(innerWidth, innerHeight);

			// This is a workaround for the resize
			editPart.getFigure().setMinimumSize(size);

			int x = ((Integer) editPart
					.getStructuralFeatureValue(NotationPackage.eINSTANCE
							.getLocation_X())).intValue();
			int y = ((Integer) editPart
					.getStructuralFeatureValue(NotationPackage.eINSTANCE
							.getLocation_Y())).intValue();
			Point loc = new Point(x, y);
			((GraphicalEditPart) editPart.getParent()).setLayoutConstraint(
					editPart, editPart.getFigure(), new Rectangle(loc, size));
		}
	}

	/**
	 * Allows fixing the width and / or height of the specified Part.
	 * 
	 * @param editPart
	 *            the Part whose sizes are to be fixed
	 */
	public static void refreshBounds(ShapeNodeEditPart editPart) {
		int width = ((Integer) editPart
				.getStructuralFeatureValue(NotationPackage.eINSTANCE
						.getSize_Width())).intValue();
		int height = ((Integer) editPart
				.getStructuralFeatureValue(NotationPackage.eINSTANCE
						.getSize_Height())).intValue();

		refreshBounds(editPart, width, height);
	}

	/**
	 * Refreshing passed pipeline procedure with default location as per
	 * sequence logic
	 * 
	 * @param editPart
	 */
	public static void refreshLocation(
			PipelineProcedureExtendedEditPart editPart) {
		int locY = giveYAxis(editPart);
		Point loc = new Point(0, locY);
		((GraphicalEditPart) editPart.getParent()).setLayoutConstraint(
				editPart, editPart.getFigure(),
				new Rectangle(loc, editPart.getSize()));
		editPart.setStructuralFeatureValue(
				NotationPackage.eINSTANCE.getLocation_X(), 0);
		editPart.setStructuralFeatureValue(
				NotationPackage.eINSTANCE.getLocation_Y(), locY);
	}

	/**
	 * @param editPart
	 * @return the Y Co-ordinate for passed Pipeline Procedure as per sequence
	 *         logic
	 */
	public static int giveYAxis(PipelineProcedureExtendedEditPart editPart) {
		int locY = 0;
		Pipeline pipeline = (Pipeline) (((View) editPart.getParent().getModel())
				.getElement());

		Procedure currentProcedure = (Procedure) (((View) editPart.getModel())
				.getElement());

		Procedure listLastProcedure = pipeline.getProcedures().get(
				pipeline.getProcedures().size() - 1);

		// if current procedure is the last from property list
		if (currentProcedure.equals(listLastProcedure)) {
			if (pipeline.getProcedures().size() > 1) {
				locY = ((pipeline.getProcedures().size() - 1) * 45);
			}
		}
		// if not that means it is getting added after Ctrl-Z
		else if (editPart.getX() == editPart.getY()) {
			locY = (editPart.getX() / 10) * 45;
		}
		return locY;
	}
}
