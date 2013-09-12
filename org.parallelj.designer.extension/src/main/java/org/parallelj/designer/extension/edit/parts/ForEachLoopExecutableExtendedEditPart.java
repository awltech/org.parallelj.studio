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
import org.parallelj.designer.edit.parts.ForEachLoopExecutableEditPart;

public class ForEachLoopExecutableExtendedEditPart extends
		ForEachLoopExecutableEditPart {

	public ForEachLoopExecutableExtendedEditPart(View view) {
		super(view);
	}

	/**
	 * @return label for Executable, appends with "by" if label set.
	 * 
	 */
	@Override
	protected String getLabelText() {
		String labelText = super.getLabelText();
		String name = "";
		ForEachLoopEditPart forEachLoopEditPart = (ForEachLoopEditPart) this
				.getParent();
		WrappingLabel figureForEachLoopExecutablePrecedingFigure = forEachLoopEditPart
				.getPrimaryShape()
				.getFigureForEachLoopExecutablePrecedingFigure();
		WrappingLabel figureForEachLoopExecutableNameFigure = forEachLoopEditPart
				.getPrimaryShape().getFigureForEachLoopExecutableNameFigure();
		if (labelText != null && labelText.trim().length() > 0) {
			figureForEachLoopExecutablePrecedingFigure.setText("by:");
			// Separating class name from package
			int lastIndexOf = labelText.lastIndexOf(".");

			if (lastIndexOf > 0) {
				name = labelText.substring(lastIndexOf + 1, labelText.length());
				labelText = labelText.substring(0, lastIndexOf + 1);
			} else {
				name = labelText;
				labelText = "";
			}
			figureForEachLoopExecutableNameFigure.setText(name);
		} else {
			figureForEachLoopExecutableNameFigure.setText("");
			figureForEachLoopExecutablePrecedingFigure.setText("");
		}
		return labelText;
	}
}
