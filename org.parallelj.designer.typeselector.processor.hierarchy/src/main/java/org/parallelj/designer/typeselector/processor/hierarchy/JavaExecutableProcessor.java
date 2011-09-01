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
package org.parallelj.designer.typeselector.processor.hierarchy;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.core.search.TypeNameRequestor;
import org.eclipselabs.resourceselector.core.processor.ResourceProcessor;
import org.eclipselabs.resourceselector.processor.java.JavaTypeInfo;
import org.eclipselabs.resourceselector.processor.java.JavaTypeInfo.JavaTypeKind;
import org.eclipselabs.resourceselector.processor.java.JavaTypeInfo.JavaTypeVisibility;

/**
 * Resource Processor Extension for Java Hierarchy management excluding the
 * Interfaces Hierarchies.
 * 
 */
public class JavaExecutableProcessor extends ResourceProcessor {

	/**
	 * IJava project corresponding to input IProject
	 */
	private IJavaProject javaProject;
	private Class<?> topHierarchyClass;

	/**
	 * Creates new Java Hierarchy Type Processor with input parameters
	 * 
	 * @param project
	 *            input IProject
	 * @param pattern
	 *            pattern
	 */
	public JavaExecutableProcessor(IProject project, String pattern) {
		this.iProject = project;
		this.javaProject = JavaCore.create(this.iProject);
		this.pattern = pattern;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipselabs.resourceselector.core.processor.TypeProcessor#process()
	 */
	@Override
	protected void process() {

		IType type = null;
		IJavaSearchScope javaSearchScope = null;
		if (this.topHierarchyClass == null)
			return;
		try {
			type = this.javaProject.findType(this.topHierarchyClass
					.getCanonicalName());
			javaSearchScope = JavaExecutableScopesCache.getInstance().getScope(
					type);
		} catch (JavaModelException e1) {
			JavaExecutableActivator.getDefault().logError(
					"Error while Creating Java Hierarchy", e1);
		}

		if (type == null || javaSearchScope == null) {
			return;
		}
		TypeNameRequestor typeNameRequestor = new TypeNameRequestor() {

			@Override
			public void acceptType(int modifiers, char[] packageName,
					char[] simpleTypeName, char[][] enclosingTypeNames,
					String path) {

				String packageNameAsString = new String(packageName);
				String simpleTypeNameAsString = new String(simpleTypeName);

				JavaTypeInfo JavaTypeInfo = new JavaTypeInfo(
						simpleTypeNameAsString, packageNameAsString, path,
						null, modifiers);
				if (this.isJavaTypeInfoValid(JavaTypeInfo))
					JavaExecutableProcessor.this.addResult(JavaTypeInfo);
			}

			/**
			 * Filter on JavaTypeInfos, to remove not valid classes (e.g.
			 * private inner classes)
			 * 
			 * @param javaTypeInfo
			 *            JavaTypeInfo
			 * @return true if valid, false otherwise
			 */
			private boolean isJavaTypeInfoValid(JavaTypeInfo javaTypeInfo) {
				if (javaTypeInfo.isInnerElement()
						&& JavaTypeVisibility.PRIVATE.equals(javaTypeInfo
								.getTypeVisibility())) {
					return false;
				}
				if (JavaTypeKind.INTERFACE == javaTypeInfo.getTypeKind()) {
					return false;
				}
				return true;
			}
		};

		SearchEngine searchEngine = new SearchEngine();
		String packagePattern = null;
		String typePattern = this.pattern;
		if (!typePattern.endsWith("*"))
			typePattern += "*";
		if (typePattern.contains(ResourceProcessor.DOT)) {
			packagePattern = typePattern.substring(0,
					typePattern.lastIndexOf(ResourceProcessor.DOT));
			typePattern = typePattern.substring(typePattern
					.lastIndexOf(ResourceProcessor.DOT) + 1);
		}
		try {
			searchEngine
					.searchAllTypeNames(packagePattern == null ? null
							: packagePattern.toCharArray(),
							SearchPattern.R_EXACT_MATCH
									| SearchPattern.R_PATTERN_MATCH,
							typePattern.toCharArray(),
							SearchPattern.R_EXACT_MATCH
									| SearchPattern.R_PATTERN_MATCH,
							IJavaSearchConstants.CLASS_AND_INTERFACE,
							javaSearchScope, typeNameRequestor,
							IJavaSearchConstants.WAIT_UNTIL_READY_TO_SEARCH,
							new NullProgressMonitor());
		} catch (JavaModelException e) {
			JavaExecutableActivator.getDefault().logError(
					"Error while processing Jave Types in Hierarchy.", e);
		}
	}

	/**
	 * Adds new result to search results list
	 * 
	 * @param JavaTypeInfo
	 *            new result
	 */
	protected void addResult(JavaTypeInfo javaTypeInfo) {
		this.searchResults.add(javaTypeInfo);
	}

	@Override
	public void cancel() {
	}

	/**
	 * @param Top
	 *            Hierarchy Class of this Hierarchy Processor
	 */
	public void setTopHierarchyClass(Class<?> clazz) {
		this.topHierarchyClass = clazz;
	}

	/**
	 * @return Top Hierarchy Class of this Hierarchy Processor
	 */
	public Class<?> getTopHierarchyClass() {
		return topHierarchyClass;
	}
}
