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
package org.parallelj.designer.validation.constraints;

import org.parallelj.designer.validation.DiagramValidationPlugin;
import org.parallelj.model.Procedure;
import org.parallelj.model.JoinType;
import org.parallelj.model.SplitType;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * @see org.parallelj.designer.validation.constraints.AbstractProcedureOutgoingPathsConsistenceConstraint
 */
public class ProcedureOutgoingPathsConsistenceConstraint extends
		AbstractProcedureOutgoingPathsConsistenceConstraint {

	/**
	 * Error message Pattern
	 */
	private static final String ERROR_MESSAGE_PATTERN = "Consistence Warning: Paths splitting from Procedure \"%s\" with %s-Split value should not join back at Procedure \"%s\" with %s-Join value.";

	@Override
	protected IStatus validateProcedures(Procedure initialProcedure, Procedure finalProcedure) {
		if (finalProcedure != null) {
			SplitType splitType = initialProcedure.getSplit();
			JoinType joinType = finalProcedure.getJoin();
			if (SplitType.AND_VALUE != splitType.getValue()
					&& JoinType.AND_VALUE == joinType.getValue()) {
				String message = String
						.format(
								ProcedureOutgoingPathsConsistenceConstraint.ERROR_MESSAGE_PATTERN,
								initialProcedure.getName(), initialProcedure.getSplit()
										.toString(), finalProcedure.getName(),
								finalProcedure.getJoin().toString());
				return new Status(IStatus.ERROR,
						DiagramValidationPlugin.PLUGIN_ID, message);
			}

		}
		return Status.OK_STATUS;
	}

}
