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
package org.parallelj.designer.validation.providers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.validation.model.IClientSelector;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.parallelj.designer.edit.parts.SpecificationEditPart;
import org.parallelj.designer.part.ParallelJDiagramEditorPlugin;
import org.parallelj.designer.part.ParallelJVisualIDRegistry;

/**
 * ValidationProvider
 */
public class ValidationProvider {

	private static boolean constraintsActive = false;

	/**
	 * Checks if the constraints provided needs to be not public.
	 * 
	 * @return boolean
	 */
	public static boolean shouldConstraintsBePrivate() {
		return false;
	}

	/**
	 * runs the provider with the constraints defined.
	 * 
	 * @param editingDomain
	 * @param operation
	 */
	public static void runWithConstraints(
			TransactionalEditingDomain editingDomain, Runnable operation) {
		final Runnable op = operation;
		Runnable task = new Runnable() {
			public void run() {
				try {
					ValidationProvider.constraintsActive = true;
					op.run();
				} finally {
					ValidationProvider.constraintsActive = false;
				}
			}
		};
		if (editingDomain != null)
			try {
				editingDomain.runExclusive(task);
			} catch (Exception e) {
				ParallelJDiagramEditorPlugin.getInstance().logError(
						"Validation failed", e);
			}
		else {
			task.run();
		}
	}

	/**
	 * Checks if In Default Editor Context using object.
	 * 
	 * @param object
	 * @return
	 */
	static boolean isInDefaultEditorContext(Object object) {
		if (ValidationProvider.shouldConstraintsBePrivate()
				|| !ValidationProvider.constraintsActive)
		{
			return false;
		}
		if (object instanceof View) {
			return ValidationProvider.constraintsActive
					&& SpecificationEditPart.MODEL_ID
							.equals(ParallelJVisualIDRegistry
									.getModelID((View) object));
		}
		return true;
	}

	public static class DefaultCtx implements IClientSelector {

		public boolean selects(Object object) {
			return ValidationProvider.isInDefaultEditorContext(object);
		}
	}

	/**
	 * formats the Element
	 * 
	 * @param object
	 * @return
	 */
	static String formatElement(EObject object) {
		return EMFCoreUtil.getQualifiedName(object, true);
	}

}
