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
package org.parallelj.designer.launching.internal;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.parallelj.designer.launching.ConfigurationConstants;

/**
 * JUnitPropertyTester provides propertyTester(s) for IResource types
 * for use in XML Expression Language syntax.
 */
public class ParallelJPropertyTester extends PropertyTester {

	private static final String PROPERTY_CAN_LAUNCH_AS_PARALLELJ= "canLaunchAsParallelj"; //$NON-NLS-1$

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.internal.corext.refactoring.participants.properties.IPropertyEvaluator#test(java.lang.Object, java.lang.String, java.lang.String)
	 */
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if (!(receiver instanceof IAdaptable)) {
			throw new IllegalArgumentException("Element must be of type 'IAdaptable', is " + receiver == null ? "null" : receiver.getClass().getName()); //$NON-NLS-1$ //$NON-NLS-2$
		}

		IJavaElement element;
		if (receiver instanceof IJavaElement) {
			element= (IJavaElement) receiver;
		} else if (receiver instanceof IResource) {
			element = JavaCore.create((IResource) receiver);
			if (element == null) {
				return false;
			}
		} else { // is IAdaptable
			element= (IJavaElement) ((IAdaptable) receiver).getAdapter(IJavaElement.class);
			if (element == null) {
				IResource resource= (IResource) ((IAdaptable) receiver).getAdapter(IResource.class);
				element = JavaCore.create(resource);
				if (element == null) {
					return false;
				}
			}
		}
		
		if (PROPERTY_CAN_LAUNCH_AS_PARALLELJ.equals(property)) {
			return canLaunchAsParallelJ(element);
		}
		throw new IllegalArgumentException("Unknown test property '" + property + "'"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private boolean canLaunchAsParallelJ(IJavaElement element) {
		switch (element.getElementType()) {
			case IJavaElement.COMPILATION_UNIT:
			case IJavaElement.CLASS_FILE:
			case IJavaElement.TYPE:
				return isParallelJProgram(element);
			default:
				return false;
		}
	}

	/*
	 * Return whether the target resource is a JUnit test.
	 */
	private boolean isParallelJProgram(IJavaElement element) {
		try {
			IType testType= null;
			if (element instanceof ICompilationUnit) {
				testType= (((ICompilationUnit) element)).findPrimaryType();
			} else if (element instanceof IClassFile) {
				testType= (((IClassFile) element)).getType();
			} else if (element instanceof IType) {
				testType= (IType) element;
			} else if (element instanceof IMember) {
				testType= ((IMember) element).getDeclaringType();
			}
			
			if (testType != null && testType.exists()) {
				if (ProgramUtils.typeContainsAnnotation(testType,
						ConfigurationConstants.PARALLELJ_PROGRAM_ANNOTATION)) {
					return true;
				}
			}
		} catch (CoreException e) {
			// ignore, return false
		}
		return false;
	}
}
