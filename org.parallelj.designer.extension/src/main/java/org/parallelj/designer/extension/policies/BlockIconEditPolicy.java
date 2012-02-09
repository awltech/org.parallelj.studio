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
package org.parallelj.designer.extension.policies;

import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import org.parallelj.designer.edit.parts.BlockEditPart.BlockFigure;
import org.parallelj.designer.extension.edit.parts.BlockExtendedEditPart;
import org.parallelj.designer.extension.tools.BoundsRefreshment;
import org.parallelj.model.Block;

/**
 * This policy attaching a mouse listener to Block Icon. Which will listen
 * the mouse action and based on that, will hide or show the compartment of
 * block.
 */
public class BlockIconEditPolicy extends GraphicalNodeEditPolicy {

	private EditPart editPart;

	public BlockIconEditPolicy(EditPart editPart) {
		super();
		this.editPart = editPart;
	}

	private class TraceMouseListener implements MouseListener {

		@Override
		public void mousePressed(MouseEvent me) {

			EditPart root = editPart;
			while (root.getParent() instanceof BlockExtendedEditPart) {
				root = root.getParent();
			}
			// getting compartment holder figure
			BlockExtendedEditPart blockExtendedEditPart = (BlockExtendedEditPart) root;
			BlockFigure primaryShape = blockExtendedEditPart
					.getPrimaryShape();
			RectangleFigure rectangleFigure = (RectangleFigure) primaryShape
					.getChildren().get(1);

			// based on visibility status, hide or show the compartment
			if (rectangleFigure.isVisible()) {
				hide(rectangleFigure, blockExtendedEditPart);
			} else {
				show(rectangleFigure, blockExtendedEditPart);
			}
		}

		@Override
		public void mouseReleased(MouseEvent me) {
		}

		@Override
		public void mouseDoubleClicked(MouseEvent me) {
		}
	}

	private TraceMouseListener myMouseListener = new TraceMouseListener();

	/**
	 * Adding listener on activate
	 */
	public void activate() {
		super.activate();
		getHostFigure().addMouseListener(myMouseListener);
	}

	/**
	 * Removing listener on deactivate
	 */
	public void deactivate() {
		getHostFigure().removeMouseListener(myMouseListener);
		super.deactivate();
	}

	/**
	 * Hide the compartment, decrease the height to only show title of block
	 * 
	 * @param rectangleFigure
	 * @param blockExtendedEditPart
	 */
	protected void hide(RectangleFigure rectangleFigure,
			BlockExtendedEditPart blockExtendedEditPart) {
		rectangleFigure.setVisible(false);
		BoundsRefreshment.refreshBounds(blockExtendedEditPart, null, 35);
		blockExtendedEditPart.clearBottomMargin();
	}

	/**
	 * Show the compartment, adjust height according to the blockd procedures
	 * 
	 * @param rectangleFigure
	 * @param blockExtendedEditPart
	 */
	protected void show(RectangleFigure rectangleFigure,
			BlockExtendedEditPart blockExtendedEditPart) {
		rectangleFigure.setVisible(true);
		Block block = (Block) (((View) blockExtendedEditPart
				.getModel()).getElement());
		if (block.getProcedures().size() <= 1) {
			BoundsRefreshment
					.refreshBounds(blockExtendedEditPart, null, 89);
		} else {
			BoundsRefreshment.refreshBounds(blockExtendedEditPart, null,
					89 + ((block.getProcedures().size() - 1) * 45));
		}
		blockExtendedEditPart.putInnerMargin();
	}
}
