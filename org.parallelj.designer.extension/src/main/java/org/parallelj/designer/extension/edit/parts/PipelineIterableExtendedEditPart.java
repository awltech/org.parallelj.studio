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

import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.parallelj.designer.edit.parts.PipelineEditPart;
import org.parallelj.designer.edit.parts.PipelineIterableEditPart;
import org.parallelj.model.Data;
import org.parallelj.model.Pipeline;

public class PipelineIterableExtendedEditPart extends PipelineIterableEditPart {

	public PipelineIterableExtendedEditPart(View view) {
		super(view);
	}

	/**
	 * @return name of the Iterable (Data) associated with Pipeline, appends
	 *         with "on" if label set.
	 */
	@Override
	protected String getLabelText() {

		String pipelineIterable = "";
		PipelineEditPart pipelineEditPart = (PipelineEditPart) this.getParent();
		WrappingLabel figurePipelineIterablePrecedingFigure = pipelineEditPart
				.getPrimaryShape().getFigurePipelineIterablePrecedingFigure();
		figurePipelineIterablePrecedingFigure.setText("");

		if (this.getParserElement() instanceof Pipeline) {
			Pipeline pipeline = (Pipeline) this.getParserElement();
			if (pipeline.getIterable() != null
					&& pipeline.getIterable() instanceof Data) {
				Data data = (Data) pipeline.getIterable();

				if (data.getName() != null
						&& data.getName().trim().length() > 0) {
					figurePipelineIterablePrecedingFigure.setText("on:");
					pipelineIterable = data.getName();
				}
			}
		}
		return pipelineIterable;
	}
}
