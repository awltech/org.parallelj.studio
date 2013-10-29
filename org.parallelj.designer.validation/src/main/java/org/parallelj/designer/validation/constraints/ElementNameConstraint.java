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
import org.parallelj.designer.validation.tools.StringTool;
import org.parallelj.model.Condition;
import org.parallelj.model.Procedure;

/**
 * Constraint: A Procedure (or a subclass) or a Condition cannot be named as
 * "end" nor as "begin".
 * 
 */
public class ElementNameConstraint extends AbstractModelConstraint {

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
		if (target instanceof Procedure) {
			Procedure procedure = (Procedure) target;
			if (!isNameValid(procedure.getName())) {
				return ctx.createFailureStatus(procedure.eClass().getName());
			}
		} else if (target.eClass().getName().equals("Condition")) {
			Condition condition = (Condition) target;
			if (!isNameValid(condition.getName())) {
				return ctx.createFailureStatus(condition.eClass().getName());
			}
		}
		return ctx.createSuccessStatus();
	}

	private boolean isNameValid(String name) {
		if (!StringTool.isEmpty(name)
				&& ("begin".equalsIgnoreCase(name) || "end"
						.equalsIgnoreCase(name))) {
			return false;
		}
		return true;
	}
}
