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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;

/**
 * Cache management for the Java Hierarchy Management
 */
public class JavaExecutableScopesCache {

	private Map<IType, IJavaSearchScope> scopes = new HashMap<IType, IJavaSearchScope>();

	private static class HierarchyScopesCacheHolder {
		static JavaExecutableScopesCache instance = new JavaExecutableScopesCache();
	}

	/**
	 * Singleton constructor.
	 * 
	 */
	private JavaExecutableScopesCache() {
	}

	/**
	 * Gets the Singleton instance
	 * 
	 * @return instance of the JavaExecutableScopesCache
	 */
	public static JavaExecutableScopesCache getInstance() {
		return HierarchyScopesCacheHolder.instance;
	}

	/**
	 * Retrieves the scope for the selected type element.
	 * 
	 * @param type
	 * @return JavaSearchScope
	 * @throws JavaModelException
	 */
	public synchronized IJavaSearchScope getScope(IType type)
			throws JavaModelException {
		IJavaSearchScope scope = this.scopes.get(type);
		if (scope == null) {
			scope = SearchEngine.createHierarchyScope(type);
			this.scopes.put(type, scope);
		}
		return scope;
	}

	/**
	 * Clears the Hierarchy Scopes Cache Management.
	 */
	public void clear() {
		this.scopes.clear();
	}

}
