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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.parallelj.model.Condition;
import org.parallelj.model.OutputCondition;

/**
 * Constraint: A Condition or an OutputCondition must have at least one incoming
 * Link.
 * 
 */
public class ConditionIncomingLinkConstraint extends AbstractModelConstraint {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse
	 * .emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		EObject target = ctx.getTarget();
		if (target.eClass().getName().equals("Condition")) {
			Condition condition = (Condition) target;
			if (condition.getInputLinks() == null
					|| condition.getInputLinks().size() == 0) {
				return ctx.createFailureStatus("Condition \""
						+ condition.getName() + "\"");
			}
		} else if (target.eClass().getName().equals("OutputCondition")) {
			OutputCondition outputCondition = (OutputCondition) target;
			if (outputCondition.getInputLinks() == null
					|| outputCondition.getInputLinks().size() == 0) {
				return ctx.createFailureStatus("OutputCondition");
			}
		}
		return ctx.createSuccessStatus();
	}
}
