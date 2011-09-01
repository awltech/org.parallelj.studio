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
package org.parallelj.designer.extension.tools;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.core.search.TypeNameRequestor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.parallelj.designer.extension.Activator;

/**
 * This class is used to Search for the generated Java element and Open the
 * file.
 * 
 */
public class ResourceOpener {

	private ResourceOpener() {
	}

	/**
	 * Opens the Java file on selecting the program model Eobject.
	 * 
	 * @param resource
	 * @param workbenchWindow
	 * @param shellz
	 */
	public static void openResource(final IFile resource,
			IWorkbenchWindow workbenchWindow, Shell shellz) {
		final IWorkbenchPage activePage = workbenchWindow.getActivePage();
		if (activePage != null) {
			final Display display = shellz.getDisplay();
			if (display != null) {
				display.asyncExec(new Runnable() {
					public void run() {
						try {
							IDE.openEditor(activePage, resource, false);

						} catch (PartInitException e) {
							Activator.logError("Resource cannot be Open "
									+ e.getMessage());
						}
					}
				});
			}
		}
	}

	/**
	 * Finds the existing java file in the current javaProject
	 * 
	 * @param javaSearchScope
	 * @param searchEngine
	 * @param typeNameRequestor
	 */
	public static void searchAllJavaTypes(IJavaSearchScope javaSearchScope,
			SearchEngine searchEngine, TypeNameRequestor typeNameRequestor) {
		try {
			searchEngine
					.searchAllTypeNames(null, SearchPattern.R_EXACT_MATCH
							| SearchPattern.R_PATTERN_MATCH, null,
							SearchPattern.R_EXACT_MATCH
									| SearchPattern.R_PATTERN_MATCH,
							IJavaSearchConstants.CLASS, javaSearchScope,
							typeNameRequestor,
							IJavaSearchConstants.WAIT_UNTIL_READY_TO_SEARCH,
							null);
		} catch (JavaModelException e) {
			Activator.logError(e.getMessage());
		}
	}
}
