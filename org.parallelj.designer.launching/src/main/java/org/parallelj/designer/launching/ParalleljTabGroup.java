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

import org.eclipse.ajdt.internal.launching.AJJRETab;
import org.eclipse.ajdt.internal.launching.LTWAspectPathTab;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.EnvironmentTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.debug.ui.ILaunchConfigurationTabGroup;
import org.eclipse.debug.ui.sourcelookup.SourceLookupTab;
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaArgumentsTab;
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaClasspathTab;
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaMainTab;
import org.eclipse.jdt.internal.debug.ui.launcher.LocalJavaApplicationTabGroup;
import org.parallelj.designer.launching.internal.ParalleljJavaArgumentsTab;
import org.parallelj.designer.launching.internal.ProgramParametersTab;

/**
 * Extends LocalJavaApplicationTabGroup to enable a custom 'Main' Tab, which
 * allows main methods to be discovered in aspects.
 */
@SuppressWarnings({ "restriction", "unused" })
public class ParalleljTabGroup extends LocalJavaApplicationTabGroup {

	
	/**
	 * @see ILaunchConfigurationTabGroup#createTabs(ILaunchConfigurationDialog,
	 *      String)
	 */
	public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
		ParalleljMainTab mainTab = new ParalleljMainTab();
		ProgramParametersTab programParameterTab = new ProgramParametersTab(mainTab);
		ILaunchConfigurationTab[] tabs = new ILaunchConfigurationTab[] {
				mainTab, 
				programParameterTab,
				new ParalleljJavaArgumentsTab(), 
				new AJJRETab(),
				new JavaClasspathTab(),
				new SourceLookupTab(),
				new EnvironmentTab(), 
				new CommonTab() };
		setTabs(tabs);		
	}

}
