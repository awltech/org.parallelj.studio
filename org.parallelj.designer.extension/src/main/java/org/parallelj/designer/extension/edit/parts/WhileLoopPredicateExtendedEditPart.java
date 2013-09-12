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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.parallelj.designer.edit.parts.WhileLoopEditPart;
import org.parallelj.designer.edit.parts.WhileLoopPredicateEditPart;
import org.parallelj.designer.extension.adapters.WhileLoopPredicateRefreshmentAdapter;
import org.parallelj.model.Predicate;
import org.parallelj.model.WhileLoop;

public class WhileLoopPredicateExtendedEditPart extends
		WhileLoopPredicateEditPart {

	public WhileLoopPredicateExtendedEditPart(View view) {
		super(view);
		this.addAdapters(view);
	}

	/**
	 * Adds adapters on view associated with Edit Part.
	 * 
	 * @param view
	 */
	private void addAdapters(View view) {
		EObject element = view.getElement();
		if (element != null) {
			element.eAdapters().add(
					new WhileLoopPredicateRefreshmentAdapter(this));
		}
	}

	/**
	 * @return name of the predicate associated with WhileLoop.
	 */
	@Override
	protected String getLabelText() {

		String whileLoopPredicate = "";
		WhileLoopEditPart whileLoopEditPart = (WhileLoopEditPart) this
				.getParent();
		WrappingLabel figureWhileLoopPredicatePrecedingFigure = whileLoopEditPart
				.getPrimaryShape().getFigureWhileLoopPredicatePrecedingFigure();
		figureWhileLoopPredicatePrecedingFigure.setText("");

		if (this.getParserElement() instanceof WhileLoop) {
			WhileLoop whileLoop = (WhileLoop) this.getParserElement();
			if (whileLoop.getPredicate() != null
					&& whileLoop.getPredicate() instanceof Predicate) {
				Predicate predicate = (Predicate) whileLoop.getPredicate();

				if (predicate.getName() != null
						&& predicate.getName().trim().length() > 0) {
					figureWhileLoopPredicatePrecedingFigure.setText("while:");
					whileLoopPredicate = predicate.getName();
				}
			}
		}
		return whileLoopPredicate;
	}
}
