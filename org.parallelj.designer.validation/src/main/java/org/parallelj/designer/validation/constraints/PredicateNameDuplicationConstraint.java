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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.parallelj.designer.validation.tools.StringTool;
import org.parallelj.model.Predicate;
import org.parallelj.model.Program;

/**
 * Constraint: Two Predicate objects should not have same name.
 * 
 */
public class PredicateNameDuplicationConstraint extends AbstractModelConstraint {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse
	 * .emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		EObject eObject = ctx.getTarget();
		if (eObject instanceof Predicate) {
			Predicate predicate = (Predicate) eObject;
			if (!StringTool.isEmpty(predicate.getName())) {

				Program program = (Program) predicate.eContainer();

				EList<Predicate> predicateElements = program.getPredicates();
				int count = 0;
				for (Predicate predicateChild : predicateElements) {
					if (!StringTool.isEmpty(predicateChild.getName())
							&& predicate.getName().equals(
									predicateChild.getName()) && count < 2) {
						count++;
					}
				}
				if (count == 2) {
					return ctx.createFailureStatus(predicate.getName());
				}
			}
		}
		return ctx.createSuccessStatus();
	}
}
