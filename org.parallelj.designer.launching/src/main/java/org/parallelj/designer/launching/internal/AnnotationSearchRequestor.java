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

