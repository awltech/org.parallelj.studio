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
