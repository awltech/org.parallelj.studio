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
package org.parallelj.designer.extension.actions;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.parallelj.designer.extension.dialog.PredicateAndDataListDialog;
import org.parallelj.model.Link;

/**
 * Action based on AbstractDiagramPopupAction, on changing or setting predicate
 * for link.
 */
public class PredicateAndDataAction extends AbstractDiagramPopupAction {

	@Override
	protected Object getValue(EObject source, ActionID actionID) {
		
		EStructuralFeature feature = actionID.getFeatureToUpdate();
		PredicateAndDataListDialog dialog = new PredicateAndDataListDialog(
				source, feature);
		dialog.open();
		Object result = dialog.getResult();

		if(source instanceof Link)
		{
			return result == PredicateAndDataListDialog.NO_ELEMENT ? "" : result;	
		}
		else
		{
			return result;
		}
		
	}


}
