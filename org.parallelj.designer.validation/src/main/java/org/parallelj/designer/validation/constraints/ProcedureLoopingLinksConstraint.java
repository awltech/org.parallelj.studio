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

import java.util.HashSet;
import java.util.Set;

import org.parallelj.model.Condition;
import org.parallelj.model.Link;
import org.parallelj.model.Procedure;
import org.parallelj.model.Element;
import org.parallelj.model.SplitType;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;

/**
 * Constraint : Checks if the given Procedure loops back to itself after having
 * run down its successors
 *
 */
public class ProcedureLoopingLinksConstraint extends AbstractModelConstraint {

	@Override
	public IStatus validate(IValidationContext ctx) {
		EObject target = ctx.getTarget();
		if (!(target instanceof Procedure))
			return ctx.createSuccessStatus();

		Procedure procedure = (Procedure) target;
		// We must first check the Procedure is concerned
		if (isConcerned(procedure))
			if (hasLinksHeadingTo(procedure, procedure, new HashSet<Object>()))
				return ctx.createFailureStatus(procedure.getName());

		return ctx.createSuccessStatus();
	}

	private static boolean hasLinksHeadingTo(Element current, Element initial, Set<Object> checkedItems) {
		for (Link link : current.getOutputLinks()) {
			Element destination = link.getDestination();
			if (destination != null) {
				if (checkedItems.contains(destination))
					return false;
				else
					checkedItems.add(destination);
				
				// In case of a Condition, we dive deeper
				if (destination instanceof Condition)
					if (hasLinksHeadingTo(destination, initial, checkedItems))
						return true;
				
				// If the destination reveals to be the initial object
				if (destination.equals(initial))
					return true;
				
				// In case of a Procedure, we must first check it is concerned
				if (destination instanceof Procedure
						&& (isConcerned((Procedure) destination)))
					if (hasLinksHeadingTo(destination, initial, checkedItems))
						return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns true in case the Procedure is concerned by the looping link
	 * constraint. A Procedure is concerned provided it has one outgoing link,
	 * or it has more than one along with a AND-Split
	 * 
	 * @param procedure the checked Procedure
	 * @return
	 */
	private static boolean isConcerned(Procedure procedure) {
		return ((procedure.getSplit().getValue() == SplitType.AND_VALUE) ||
				(procedure.getOutputLinks().size() == 1));
	}
}
