package org.parallelj.designer.launching.internal;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchParticipant;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.internal.debug.ui.JDIDebugUIPlugin;
import org.parallelj.designer.launching.ConfigurationConstants;

@SuppressWarnings("restriction")
public class ProgramSearchEngine {

	public IType[] searchTypeWithProgramAnnotation(
			ILaunchConfigurationDialog launchConfigurationDialog,
			IJavaSearchScope searchScope) {

		SearchPattern pattern = SearchPattern.createPattern(
				ConfigurationConstants.PARALLELJ_PROGRAM_ANNOTATION,
				IJavaSearchConstants.ANNOTATION_TYPE,
				IJavaSearchConstants.CLASS, SearchPattern.R_EXACT_MATCH
						| SearchPattern.R_CASE_SENSITIVE); //$NON-NLS-1$
		SearchParticipant[] participants = new SearchParticipant[] { SearchEngine
				.getDefaultSearchParticipant() };
		AnnotationSearchRequestor collector = new AnnotationSearchRequestor();
		try {
			new SearchEngine().search(pattern, participants, searchScope,
					collector, null);
		} catch (CoreException ce) {
			JDIDebugUIPlugin.log(ce);
		}

		@SuppressWarnings("unchecked")
		List<IType> result = collector.getResult();
		return (IType[]) result.toArray(new IType[result.size()]);
	}

}
