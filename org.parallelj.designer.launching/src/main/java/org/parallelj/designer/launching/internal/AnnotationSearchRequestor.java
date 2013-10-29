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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.search.SearchMatch;
import org.eclipse.jdt.core.search.SearchRequestor;

public class AnnotationSearchRequestor extends SearchRequestor {

	@SuppressWarnings("rawtypes")
	private final List fResult = new ArrayList();

	public AnnotationSearchRequestor() {
	}

	@SuppressWarnings("unchecked")
	public void acceptSearchMatch(SearchMatch match) throws CoreException {
		if (match.getAccuracy() == SearchMatch.A_ACCURATE && !match.isInsideDocComment()) {
			Object element= match.getElement();
			if (element instanceof IType) {
				IMember member= (IMember) element;
				IType type= member.getElementType() == IJavaElement.TYPE ? (IType) member : member.getDeclaringType();
				fResult.add(type);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public List getResult() {
		return fResult;
	}

}

