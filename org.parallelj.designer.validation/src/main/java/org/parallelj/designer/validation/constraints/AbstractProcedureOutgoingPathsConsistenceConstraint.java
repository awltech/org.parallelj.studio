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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.parallelj.model.Link;
import org.parallelj.model.Procedure;
import org.parallelj.model.Element;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;

/**
 * Constraint : Checks if outgoing paths from this Procedure are consistent.
 * 
 * A path is said to be consistent when the Procedure, where paths are joining back,
 * has a JOIN value consistent with this Procedure's SPLIT value
 * 
 * @author mvanbesien
 * 
 */
public abstract class AbstractProcedureOutgoingPathsConsistenceConstraint extends
		AbstractModelConstraint {

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
			IStatus consistenceStatus = this.checkConsistence(procedure);
			if (consistenceStatus.matches(IStatus.ERROR)
					|| consistenceStatus.matches(IStatus.WARNING))
				return ctx.createFailureStatus(consistenceStatus.getMessage());
		}
		return ctx.createSuccessStatus();
	}

	/**
	 * Checks consistence between this Procedure outgoing links.
	 * 
	 * If more than one link split out of this Procedure, the Procedure where those
	 * links join back is computed, and consistence between JOIN and SPLIT
	 * values is checked.
	 * 
	 * @param initialProcedure
	 *            : Initial Procedure
	 * @return IStatus
	 */
	protected IStatus checkConsistence(Procedure initialProcedure) {
		List<Vector<Element>> pathFamily = this
				.computePathsFrom(initialProcedure);
		this.removeCorruptedPaths(pathFamily);
		if (pathFamily.size() > 1) {
			Procedure finalProcedure = getFinalProcedure(initialProcedure, pathFamily);
			return validateProcedures(initialProcedure, finalProcedure);
		}
		return Status.OK_STATUS;
	}

	/**
	 * 
	 * @param initialProcedure
	 * @param finalProcedure
	 * @return
	 */
	protected abstract IStatus validateProcedures(Procedure initialProcedure, Procedure finalProcedure);

	/**
	 * Returns the final Procedure. The final Procedure is the common Procedure
	 * where paths ends.
	 * 
	 * @param initialProcedure
	 *            : Initial Procedure
	 * @param pathFamily
	 *            : available paths
	 * @return final Procedure
	 */
	protected Procedure getFinalProcedure(Procedure initialProcedure,
			List<Vector<Element>> pathFamily) {
		if (pathFamily.size() > 1) {
			Procedure finalProcedure = null;
			Iterator<Element> references = pathFamily.get(0).iterator();
			while (references.hasNext() && finalProcedure == null) {
				Element reference = references.next();
				if (reference instanceof Procedure && !reference.equals(initialProcedure)) {
					boolean isContainedByOtherPaths = true;
					for (int i = 1; i < pathFamily.size()
							&& isContainedByOtherPaths; i++)
						isContainedByOtherPaths = isContainedByOtherPaths
								&& pathFamily.get(i).contains(reference);
					if (isContainedByOtherPaths)
						finalProcedure = (Procedure) reference;
				}
			}
			return finalProcedure;
		}
		return null;
	}

	/**
	 * Removed corrupted Path. A corrupted path is a Vector with no value in it.
	 * Such behaviour is possible, because forced by algorithm, when loops in
	 * flows are detected.
	 * 
	 * @param The
	 *            cleaned Path Family (A path family is a group of path,
	 *            corresponding to every possible path from a given Procedure to the
	 *            end of the flow)
	 */
	private void removeCorruptedPaths(List<Vector<Element>> pathFamily) {
		List<Vector<Element>> toRemove = new ArrayList<Vector<Element>>();
		for (Vector<Element> path : pathFamily)
			if (path.size() == 0)
				toRemove.add(path);
		pathFamily.removeAll(toRemove);
	}

	/**
	 * Computes the Possible paths accessible by links from the Procedure
	 * passed as parameter
	 * 
	 * @param procedure
	 *            Procedure
	 * @return Path Family (A path family is a group of path, corresponding to
	 *         every possible path from a given Procedure to the end of the flow)
	 */
	private List<Vector<Element>> computePathsFrom(Procedure procedure) {
		List<Vector<Element>> paths = new ArrayList<Vector<Element>>();
		for (Link link : procedure.getOutputLinks()) {
			Vector<Element> vector = new Vector<Element>();
			paths.add(vector);
			vector.add(procedure);
			this.followPath(link.getDestination(), vector, paths);
		}
		return paths;
	}

	/**
	 * 
	 * Continues on the currently followed Path. The element is here a Procedure
	 * in the Path, and the Path is represented by the provided Vector.
	 * 
	 * @param element
	 *            element to follow path from.
	 * @param vector
	 *            current followed path
	 * @param paths
	 *            FamilyPath
	 */
	private void followPath(Element element,
			Vector<Element> vector, List<Vector<Element>> paths) {
		if (element == null)
			return;
		if (vector.contains(element)) {
			vector.clear();
			return;
		}
		vector.add(element);
		Iterator<Link> iterator = element.getOutputLinks().iterator();
		Link toDoLast = null;
		if (iterator.hasNext())
			toDoLast = iterator.next();

		while (iterator.hasNext()) {
			Vector<Element> otherVector = new Vector<Element>();
			otherVector.addAll(vector);
			paths.add(otherVector);
			this.followPath(iterator.next().getDestination(), otherVector,
					paths);
		}
		if (toDoLast != null)
			this.followPath(toDoLast.getDestination(), vector, paths);
	}

}
