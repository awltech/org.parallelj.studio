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
import org.eclipse.gmf.runtime.notation.View;
import org.parallelj.designer.edit.parts.LinkPredicateInfoEditPart;
import org.parallelj.designer.extension.adapters.LinkPredicateRefreshmentAdapter;
import org.parallelj.model.Link;
import org.parallelj.model.Predicate;
import org.parallelj.model.Procedure;
import org.parallelj.model.SplitType;

public class LinkPredicateInfoExtendedEditPart extends
		LinkPredicateInfoEditPart {

	public LinkPredicateInfoExtendedEditPart(View view) {
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
			element.eAdapters().add(new LinkPredicateRefreshmentAdapter(this));
		}
	}

	/**
	 * @return name of the predicate associated with link.
	 */
	@Override
	protected String getLabelText() {
		String linkPredicateInfo = "";
		if (this.getParserElement() instanceof Link) {
			Link link = (Link) this.getParserElement();
			EObject linkContainer = link.eContainer();
			if (linkContainer != null) {
				Predicate predicate = link.getPredicate();
				if (predicate != null) {
					linkPredicateInfo = predicate.getName();
				}
				if (linkContainer instanceof Procedure) {
					Procedure procedure = (Procedure) linkContainer;
					if (procedure.getOutputLinks().size() > 1
							&& (procedure.getSplit() == SplitType.XOR || procedure
									.getSplit() == SplitType.OR)) {
						int index = procedure.getOutputLinks().indexOf(link);
						if (index > -1)
							linkPredicateInfo = index + 1 + ". "
									+ linkPredicateInfo;
					}
				}
			}
		}
		return linkPredicateInfo;
	}
}
