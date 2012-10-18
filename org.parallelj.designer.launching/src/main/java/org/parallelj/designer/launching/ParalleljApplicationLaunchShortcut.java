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
package org.parallelj.designer.launching;
/**********************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sian January - initial version
 * ...
 **********************************************************************/


import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaApplicationLaunchShortcut;
import org.eclipse.jdt.internal.core.CompilationUnit;
import org.eclipse.jdt.internal.debug.ui.launcher.LauncherMessages;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableContext;

/**
 * Shortcut to launching an AspectJ application. Extends
 * JavaApplicationLaunchShortcut to enable the launch of main methods in both
 * Java classes and Aspects. Methods are partly copied from the super class.
 */
@SuppressWarnings("restriction")
public class ParalleljApplicationLaunchShortcut extends
		JavaApplicationLaunchShortcut {

	protected IType[] findTypes(Object[] elements, IRunnableContext context) throws InterruptedException, CoreException {
			IJavaElement[] javaElements = getJavaElements(elements);
			if (javaElements.length==1 && javaElements[0] instanceof CompilationUnit) {
				CompilationUnit unit = (CompilationUnit)javaElements[0];
				return new IType[]{unit.getTypeRoot().findPrimaryType()};
			}
			return new IType[]{};
	}

	/**
	 * Returns the AspectJ launch config type
	 */
	protected static ILaunchConfigurationType getAJConfigurationType() {
		return DebugPlugin.getDefault().getLaunchManager().getLaunchConfigurationType(ConfigurationConstants.PARALLELJ_LAUNCH_ID);
	}

	protected ILaunchConfiguration createConfiguration(IType type) {
		ILaunchConfiguration config = null;
		ILaunchConfigurationWorkingCopy wc = null;
		try {
			ILaunchConfigurationType configType = getAJConfigurationType();
			wc = configType.newInstance(null, getLaunchManager().generateLaunchConfigurationName(type.getElementName()));
			wc.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, type.getJavaProject().getElementName());
			wc.setAttribute(IJavaLaunchConfigurationConstants.ATTR_MAIN_TYPE_NAME, type.getFullyQualifiedName());
			config = wc.doSave();
		} catch (CoreException exception) {
			reportErorr(exception);		
		} 
		return config;
	}

	/**
	 * Returns the singleton launch manager.
	 * 
	 * @return launch manager
	 */
	private ILaunchManager getLaunchManager() {
		return DebugPlugin.getDefault().getLaunchManager();
	}

	/**
	 * Opens an error dialog on the given exception.
	 * 
	 * @param exception
	 */
	protected void reportErorr(CoreException exception) {
		MessageDialog.openError(getShell(), LauncherMessages.JavaLaunchShortcut_3, exception.getStatus().getMessage());  
	}

}
