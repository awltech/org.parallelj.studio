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
import org.parallelj.designer.edit.parts.ForEachLoopEditPart;
import org.parallelj.designer.edit.parts.ForEachLoopIterableEditPart;
import org.parallelj.model.Data;
import org.parallelj.model.ForEachLoop;

public class ForEachLoopIterableExtendedEditPart extends
		ForEachLoopIterableEditPart {

	public ForEachLoopIterableExtendedEditPart(View view) {
		super(view);
	}

	/**
	 * @return name of the Iterable (Data) associated with ForEachLoop, appends
	 *         with "on" if label set.
	 */
	@Override
	protected String getLabelText() {

		String forEachLoopIterable = "";
		ForEachLoopEditPart forEachLoopEditPart = (ForEachLoopEditPart) this
				.getParent();
		WrappingLabel figureForEachLoopIterablePrecedingFigure = forEachLoopEditPart
				.getPrimaryShape()
				.getFigureForEachLoopIterablePrecedingFigure();
		figureForEachLoopIterablePrecedingFigure.setText("");

		if (this.getParserElement() instanceof ForEachLoop) {
			ForEachLoop forEachLoop = (ForEachLoop) this.getParserElement();
			if (forEachLoop.getIterable() != null
					&& forEachLoop.getIterable() instanceof Data) {
				Data data = (Data) forEachLoop.getIterable();

				if (data.getName() != null
						&& data.getName().trim().length() > 0) {
					figureForEachLoopIterablePrecedingFigure.setText("on:");
					forEachLoopIterable = data.getName();
				}
			}
		}
		return forEachLoopIterable;
	}
}
