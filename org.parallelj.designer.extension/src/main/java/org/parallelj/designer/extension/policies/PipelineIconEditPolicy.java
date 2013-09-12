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
import org.parallelj.designer.edit.parts.PipelineEditPart.PipelineFigure;
import org.parallelj.designer.extension.edit.parts.PipelineExtendedEditPart;
import org.parallelj.designer.extension.tools.BoundsRefreshment;
import org.parallelj.model.Pipeline;

/**
 * This policy attaching a mouse listener to Pipeline Icon. Which will listen
 * the mouse action and based on that, will hide or show the compartment of
 * pipeline.
 */
public class PipelineIconEditPolicy extends GraphicalNodeEditPolicy {

	private EditPart editPart;

	public PipelineIconEditPolicy(EditPart editPart) {
		super();
		this.editPart = editPart;
	}

	private class TraceMouseListener implements MouseListener {

		@Override
		public void mousePressed(MouseEvent me) {

			EditPart root = editPart;
			while (root.getParent() instanceof PipelineExtendedEditPart) {
				root = root.getParent();
			}
			// getting compartment holder figure
			PipelineExtendedEditPart pipelineExtendedEditPart = (PipelineExtendedEditPart) root;
			PipelineFigure primaryShape = pipelineExtendedEditPart
					.getPrimaryShape();
			RectangleFigure rectangleFigure = (RectangleFigure) primaryShape
					.getChildren().get(1);

			// based on visibility status, hide or show the compartment
			if (rectangleFigure.isVisible()) {
				hide(rectangleFigure, pipelineExtendedEditPart);
			} else {
				show(rectangleFigure, pipelineExtendedEditPart);
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
	 * Hide the compartment, decrease the height to only show title of pipeline
	 * 
	 * @param rectangleFigure
	 * @param pipelineExtendedEditPart
	 */
	protected void hide(RectangleFigure rectangleFigure,
			PipelineExtendedEditPart pipelineExtendedEditPart) {
		rectangleFigure.setVisible(false);
		BoundsRefreshment.refreshBounds(pipelineExtendedEditPart, null, 43);
		pipelineExtendedEditPart.clearBottomMargin();
	}

	/**
	 * Show the compartment, adjust height according to the pipelined procedures
	 * 
	 * @param rectangleFigure
	 * @param pipelineExtendedEditPart
	 */
	protected void show(RectangleFigure rectangleFigure,
			PipelineExtendedEditPart pipelineExtendedEditPart) {
		rectangleFigure.setVisible(true);
		Pipeline pipeline = (Pipeline) (((View) pipelineExtendedEditPart
				.getModel()).getElement());
		if (pipeline.getProcedures().size() <= 1) {
			BoundsRefreshment
					.refreshBounds(pipelineExtendedEditPart, null, 98);
		} else {
			BoundsRefreshment.refreshBounds(pipelineExtendedEditPart, null,
					98 + ((pipeline.getProcedures().size() - 1) * 45));
		}
		pipelineExtendedEditPart.putInnerMargin();
	}
}
