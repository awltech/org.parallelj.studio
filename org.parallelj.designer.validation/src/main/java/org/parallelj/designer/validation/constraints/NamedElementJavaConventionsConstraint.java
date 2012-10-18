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

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.internal.core.util.Messages;
import org.parallelj.model.NamedElement;
import org.parallelj.model.ParallelJPackage;

/**
 * Checks if the given element's name complies with the Java Conventions
 * depending on the element's nature.
 * 
 */
@SuppressWarnings("restriction")
public class NamedElementJavaConventionsConstraint extends
		AbstractModelConstraint {

	private static enum Formatting {
		CLASS, PACKAGE, TYPE, METHOD, FIELD;

		// Simple FQN compatible Java Type pattern
		private Pattern classPattern = Pattern.compile("^(\\w+\\.)+\\w+$");

		/**
		 * Return a status based on the element's nature and name
		 * 
		 * @param ctx
		 * @param namedElement
		 * @return
		 */
		public IStatus getStatus(IValidationContext ctx,
				NamedElement namedElement) {
			switch (this) {
			case CLASS:
				// Use of a RegExp as a workaround to the buggy
				// JavaConventions.validateJavaTypeName Eclipse method which
				// actually ignores the white spaces within the element's name
				Matcher m = classPattern.matcher(namedElement.getName());

				IStatus validateJavaTypeName = JavaConventions
						.validateJavaTypeName(namedElement.getName(), "5.0",
								"5.0");

				int severity = validateJavaTypeName.getSeverity();

				String message = validateJavaTypeName.getMessage();

				if (!m.matches()
						|| (severity == IStatus.ERROR)
						|| ((severity == IStatus.WARNING) && (message
								.equals(Messages.convention_type_lowercaseName))))
					return ctx.createFailureStatus("'" + namedElement.getName()
							+ "' is not a valid Java class identifier");
				else
					return ctx.createSuccessStatus();

			case PACKAGE:
				return JavaConventions.validatePackageName(
						namedElement.getName(), "5.0", "5.0");
			case TYPE:
				return JavaConventions.validateTypeVariableName(
						namedElement.getName(), "5.0", "5.0");
			case FIELD:
				return JavaConventions.validateFieldName(
						namedElement.getName(), "5.0", "5.0");
			case METHOD:
				return JavaConventions.validateMethodName(
						namedElement.getName(), "5.0", "5.0");
			default:
				return ctx.createSuccessStatus();
			}
		}
	}

	private static final Map<EClass, Formatting> formattingPerClass;

	static {
		formattingPerClass = new LinkedHashMap<EClass, Formatting>();
		formattingPerClass.put(ParallelJPackage.eINSTANCE.getProcedure(),
				Formatting.METHOD);
		formattingPerClass.put(ParallelJPackage.eINSTANCE.getHandler(),
				Formatting.METHOD);
		formattingPerClass.put(ParallelJPackage.eINSTANCE.getForEachLoop(),
				Formatting.METHOD);
		formattingPerClass.put(ParallelJPackage.eINSTANCE.getWhileLoop(),
				Formatting.METHOD);
		formattingPerClass.put(ParallelJPackage.eINSTANCE.getPipeline(),
				Formatting.METHOD);
		formattingPerClass.put(ParallelJPackage.eINSTANCE.getCondition(),
				Formatting.FIELD);
		formattingPerClass.put(ParallelJPackage.eINSTANCE.getProgram(),
				Formatting.CLASS);
		formattingPerClass.put(ParallelJPackage.eINSTANCE.getData(),
				Formatting.FIELD);
		formattingPerClass.put(ParallelJPackage.eINSTANCE.getPredicate(),
				Formatting.METHOD);
		formattingPerClass.put(
				ParallelJPackage.eINSTANCE.getBusinessProcedure(),
				Formatting.METHOD);
	}

	@Override
	public IStatus validate(IValidationContext ctx) {
		EObject eObject = ctx.getTarget();
		EClass eObjectEClass = eObject.eClass();
		Formatting formatting = formattingPerClass.get(eObjectEClass);
		if (formatting == null)
			return ctx.createSuccessStatus();
		NamedElement namedElement = (NamedElement) eObject;
		if (namedElement.getName() == null
				|| namedElement.getName().length() == 0)
			return ctx.createFailureStatus(eObjectEClass.getName() + " "
					+ namedElement.getName() + ": null values are not allowed");
		IStatus highStatus = formatting.getStatus(ctx, namedElement);

		if (highStatus.getSeverity() == IStatus.OK)
			return ctx.createSuccessStatus();
		else
			return ctx.createFailureStatus(eObjectEClass.getName() + " "
					+ namedElement.getName() + ": " + highStatus.getMessage());
	}
}
