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
import org.parallelj.designer.edit.parts.WhileLoopEditPart;
import org.parallelj.designer.edit.parts.WhileLoopExecutableEditPart;

public class WhileLoopExecutableExtendedEditPart extends
		WhileLoopExecutableEditPart {

	public WhileLoopExecutableExtendedEditPart(View view) {
		super(view);
	}

	/**
	 * @return label for Executable, appends with "by" if label set.
	 */
	@Override
	protected String getLabelText() {
		String labelText = super.getLabelText();
		String name = "";
		WhileLoopEditPart whileLoopEditPart = (WhileLoopEditPart) this
				.getParent();
		WrappingLabel figureWhileLoopExecutablePrecedingFigure = whileLoopEditPart
				.getPrimaryShape()
				.getFigureWhileLoopExecutablePrecedingFigure();
		WrappingLabel figureWhileLoopExecutableNameFigure = whileLoopEditPart
				.getPrimaryShape().getFigureWhileLoopExecutableNameFigure();
		if (labelText != null && labelText.trim().length() > 0) {
			figureWhileLoopExecutablePrecedingFigure.setText("by:");
			// Separating class name from package
			int lastIndexOf = labelText.lastIndexOf(".");
			if (lastIndexOf > 0) {
				name = labelText.substring(lastIndexOf + 1, labelText.length());
				labelText = labelText.substring(0, lastIndexOf + 1);
			} else {
				name = labelText;
				labelText = "";
			}
			figureWhileLoopExecutableNameFigure.setText(name);
		} else {
			figureWhileLoopExecutableNameFigure.setText("");
			figureWhileLoopExecutablePrecedingFigure.setText("");
		}
		return labelText;
	}
}
