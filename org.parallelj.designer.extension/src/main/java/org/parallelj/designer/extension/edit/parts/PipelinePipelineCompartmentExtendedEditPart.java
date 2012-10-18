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
package org.parallelj.designer.extension.edit.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.parallelj.designer.edit.parts.PipelinePipelineCompartmentEditPart;

public class PipelinePipelineCompartmentExtendedEditPart extends
		PipelinePipelineCompartmentEditPart {

	public PipelinePipelineCompartmentExtendedEditPart(View view) {
		super(view);
	}

	/**
	 * Creating compartment with inner margin.
	 */
	@Override
	public IFigure createFigure() {
		ResizableCompartmentFigure result = (ResizableCompartmentFigure) super
				.createFigure();
		result.setTitleVisibility(false);
		result.setBorder(new MarginBorder(getMapMode().DPtoLP(-1), getMapMode()
				.DPtoLP(-1), getMapMode().DPtoLP(-1), getMapMode().DPtoLP(-3)));
		ScrollPane scrollPane = result.getScrollPane();
		scrollPane.setScrollBarVisibility(ScrollPane.NEVER);
		return result;
	}

	/**
	 * Removing Drag Drop facility from Pipeline Compartment, to avoid insertion
	 * of procedure by Dragging from Program and dropping inside pipeline
	 * compartment.
	 */
	@Override
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		removeEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE);
	}
}
