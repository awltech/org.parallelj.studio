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
package org.parallelj.designer.extension.adapters;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.parallelj.designer.edit.parts.LinkEditPart;
import org.parallelj.designer.edit.parts.LinkPredicateInfoEditPart;
import org.parallelj.model.ParallelJPackage;

/**
 * Refreshment adapter for Predicate Info label, when outgoing link is added or
 * removed.
 */
public class LinkPredicateRefreshmentOnLinkAddRemAdapter extends
		AbstractRefreshmentAdapter {

	public LinkPredicateRefreshmentOnLinkAddRemAdapter(
			AbstractGraphicalEditPart editPart) {
		super(editPart);
	}

	/**
	 * @return EStructural Feature to adapt
	 */
	@Override
	protected EStructuralFeature getFeature() {
		return ParallelJPackage.eINSTANCE.getElement_OutputLinks();
	}

	/**
	 * Returns all the VisualIDs corresponding to EditParts that should be
	 * refreshed by this adapter.
	 * 
	 * @return array of VisualIDs
	 */
	@Override
	protected int[] getVisualIDsOfEditPartsToRefresh() {
		return new int[] { LinkPredicateInfoEditPart.VISUAL_ID,
				LinkEditPart.VISUAL_ID };
	}

}
