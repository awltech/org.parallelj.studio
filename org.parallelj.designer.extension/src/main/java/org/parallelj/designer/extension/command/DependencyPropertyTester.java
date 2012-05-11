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
package org.parallelj.designer.extension.command;

import java.util.Collection;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.parallelj.designer.extension.part.contrib.BusinessProcedureContribution;
import org.parallelj.designer.extension.part.contrib.BusinessProcedureContributionManager;

/**
 * This will check the property for menu visibility.
 * 
 * @author A169104
 *
 */
public class DependencyPropertyTester extends PropertyTester {

	public DependencyPropertyTester() {
	}

	@Override
	public boolean test(Object receiver, String property, Object[] args,
			Object expectedValue) {
		try {
			if (receiver instanceof IJavaProject
					&& ((IJavaProject) receiver).getProject().hasNature(
							"org.parallelj.designer.validation.ParallelJNature")) {
				Collection<BusinessProcedureContribution> contributions = BusinessProcedureContributionManager
						.getInstance().getContributions();
				if (contributions.isEmpty()) {
					return false;
				}
			} else {
				return false;
			}
		} catch (CoreException e) {
			return false;
		}
		return true;
	}
}
