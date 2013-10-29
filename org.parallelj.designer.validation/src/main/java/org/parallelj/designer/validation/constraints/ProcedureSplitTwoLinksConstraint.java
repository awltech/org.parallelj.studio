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

import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.parallelj.model.Link;
import org.parallelj.model.Procedure;
import org.parallelj.model.SplitType;

/**
 * Constraint: Checks predicates available on links according to SPLIT value of
 * Procedure/ForEachLoop/WhileLoop/Handler.
 * 
 */
public class ProcedureSplitTwoLinksConstraint extends AbstractModelConstraint {

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
		if (eObject instanceof Procedure) {
			Procedure procedure = (Procedure) eObject;
			int predicatesAmount = 0;
			int transitionsAmount = procedure.getOutputLinks().size();
			boolean hasLastTransitionPredicate = false;

			if (transitionsAmount >= 2) {
				Iterator<Link> iterator = procedure.getOutputLinks()
						.listIterator();

				while (iterator.hasNext()) {
					Link link = iterator.next();
					if (link.getPredicate() != null) {
						predicatesAmount++;
						if (!iterator.hasNext()) {
							hasLastTransitionPredicate = true;
						}
					}
				}
				if (procedure.getSplit() == SplitType.AND
						&& predicatesAmount > 0) {
					return ctx.createFailureStatus(eObject.eClass().getName(),
							procedure.getName(), procedure.getSplit()
									.getLiteral(),
							"Outgoing links shouldn't have any Predicate");
				}
				if (procedure.getSplit() == SplitType.OR
						&& predicatesAmount != transitionsAmount) {
					return ctx.createFailureStatus(eObject.eClass().getName(),
							procedure.getName(), procedure.getSplit()
									.getLiteral(),
							"Each outgoing link should have a Predicate");
				}
				if (procedure.getSplit() == SplitType.XOR
						&& (predicatesAmount != transitionsAmount - 1 || hasLastTransitionPredicate)) {
					return ctx.createFailureStatus(eObject.eClass().getName(),
							procedure.getName(), procedure.getSplit()
									.getLiteral(),
							"Each outgoing link but the last one (default one) "
									+ "should have a Predicate");
				}
			}
		}
		return ctx.createSuccessStatus();
	}
}
