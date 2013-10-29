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
import org.parallelj.model.JoinType;
import org.parallelj.model.SplitType;

/**
 * Action based on AbstractDiagramPopupAction, for modifying Split or Join
 * values of Procedure
 */
public class ProcedureSplitAndJoinModificationAction extends
		AbstractDiagramPopupAction {

	@Override
	protected Object getValue(EObject source, ActionID actionID) {
		if (actionID == ActionID.JOIN_AND)
			return JoinType.AND;
		if (actionID == ActionID.JOIN_OR)
			return JoinType.OR;
		if (actionID == ActionID.JOIN_XOR)
			return JoinType.XOR;
		if (actionID == ActionID.SPLIT_AND)
			return SplitType.AND;
		if (actionID == ActionID.SPLIT_OR)
			return SplitType.OR;
		if (actionID == ActionID.SPLIT_XOR)
			return SplitType.XOR;
		return null;
	}

}
