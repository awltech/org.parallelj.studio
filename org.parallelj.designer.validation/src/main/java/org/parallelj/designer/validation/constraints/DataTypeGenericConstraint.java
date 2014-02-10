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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.parallelj.designer.validation.tools.JavaTypeValidator;
import org.parallelj.designer.validation.tools.StringTool;
import org.parallelj.model.Data;

/**
 * Constraint: Data type should not be null.
 * 
 */
public class DataTypeGenericConstraint extends AbstractModelConstraint {

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
		if (eObject instanceof Data) {
			Data data = (Data) eObject;
			if (!StringTool.isEmpty(data.getType())) {
				IFile file = WorkspaceSynchronizer.getFile(eObject.eResource());
				IProject project = file.getProject();
				IJavaProject javaProject = JavaCore.create(project);

				try {
					IStatus status = new JavaTypeValidator(
							data.getType()).validate(javaProject);

					if (status.isOK()) {
						return ctx.createSuccessStatus();
					} else {
						return ctx.createFailureStatus(status.getMessage());
					}

				} catch (JavaModelException e) {
					return ctx.createFailureStatus(e.getMessage());
				} catch (IllegalStateException e) {
					return ctx.createFailureStatus(e.getMessage());
				}

			}
		}
		return ctx.createSuccessStatus();
	}
}