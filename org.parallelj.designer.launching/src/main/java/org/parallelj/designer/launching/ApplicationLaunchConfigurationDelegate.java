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

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IResource;
//import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jdt.internal.launching.LaunchingMessages;
import org.eclipse.jdt.launching.ExecutionArguments;
import org.eclipse.jdt.launching.IVMRunner;
import org.eclipse.jdt.launching.JavaLaunchDelegate;
import org.eclipse.jdt.launching.VMRunnerConfiguration;
import org.eclipse.osgi.util.NLS;
import org.parallelj.designer.launching.internal.ProgramUtils;

@SuppressWarnings("restriction")
public class ApplicationLaunchConfigurationDelegate extends JavaLaunchDelegate {

	private static final String defaultLauncherClass = "org.parallelj.launching.internal.DefaultLauncher";
	
//	private static String defaultAspectjWeaverJavaagentOption = "-javaagent:";
//	private String aspectWeaverPath;


	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.ILaunchConfigurationDelegate#launch(org.eclipse.debug.core.ILaunchConfiguration, java.lang.String, org.eclipse.debug.core.ILaunch, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@SuppressWarnings("rawtypes")
	public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor) throws CoreException {
		
		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}
		
		monitor.beginTask(NLS.bind("{0}...", new String[]{configuration.getName()}), 3); //$NON-NLS-1$
		// check for cancellation
		if (monitor.isCanceled()) {
			return;
		}
		
		try {
			monitor.subTask(LaunchingMessages.JavaLocalApplicationLaunchConfigurationDelegate_Verifying_launch_attributes____1); 
			
			String mainTypeName = defaultLauncherClass;
			String programFullName = verifyMainTypeName(configuration);
			IVMRunner runner = getVMRunner(configuration, mode);
	
			File workingDir = verifyWorkingDirectory(configuration);
			String workingDirName = null;
			if (workingDir != null) {
				workingDirName = workingDir.getAbsolutePath();
			}
			
			// Environment variables
			String[] envp= getEnvironment(configuration);
			
			// Program & VM arguments
			String pgmArgs = getProgramArguments(configuration);
			String vmArgs = getVMArguments(configuration);
			ExecutionArguments execArgs = new ExecutionArguments(vmArgs, pgmArgs);
			
			// VM-specific attributes
			Map<String, Object> vmAttributesMap = getVMSpecificAttributesMap(configuration);
			
			// Classpath
			String[] classpath = getClasspath(configuration);
			
			// Create VM config
			VMRunnerConfiguration runConfig = new VMRunnerConfiguration(mainTypeName, classpath);
			
			Map programArguments = configuration.getAttribute(ConfigurationConstants.PARALLELJ_PARAMETERS, new HashMap());
			Map finalProgramArguments = ProgramUtils.getProgramParameters(this.getJavaProject(configuration).getProject().getName() ,programFullName, programArguments);
			String[] finalProgramArgumentsAsArray = new String[finalProgramArguments.size()+1];
			if (finalProgramArguments.size() != 0) {
				int cpt=1;
				Iterator iterator= finalProgramArguments.entrySet().iterator();
				while(iterator.hasNext()) {
		 			Map.Entry next = (Map.Entry) iterator.next();
		 			Object[] arg = new Object[]{next.getKey(), next.getValue()};
		 			finalProgramArgumentsAsArray[cpt++]= String.format("%s=%s", arg); 
				}
			}
			finalProgramArgumentsAsArray[0] =  programFullName;
			//runConfig.setProgramArguments(execArgs.getProgramArgumentsArray());
			runConfig.setProgramArguments(finalProgramArgumentsAsArray);

			runConfig.setEnvironment(envp);
			runConfig.setVMArguments(execArgs.getVMArgumentsArray());
			runConfig.setWorkingDirectory(workingDirName);
			runConfig.setVMSpecificAttributesMap(vmAttributesMap);
	
			// Bootpath
			runConfig.setBootClassPath(getBootpath(configuration));
			
			// check for cancellation
			if (monitor.isCanceled()) {
				return;
			}		
			
			// stop in main
			prepareStopInMain(configuration);
			
			// done the verification phase
			monitor.worked(1);
			
			monitor.subTask(LaunchingMessages.JavaLocalApplicationLaunchConfigurationDelegate_Creating_source_locator____2); 
			// set the default source locator if required
			setDefaultSourceLocator(launch, configuration);
			monitor.worked(1);		
			
			// Launch the configuration - 1 unit of work
			runner.run(runConfig, launch, monitor);
			
			// check for cancellation
			if (monitor.isCanceled()) {
				return;
			}	
		}
		finally {
			monitor.done();
		}
	}

	/**
	 * Override getClasspath so that we also include the load time weaving
	 * aspectpath
	 */
	/*
	public String[] getClasspath(ILaunchConfiguration configuration)
			throws CoreException {
		String[] javaClasspath=super.getClasspath(configuration);

		// Search aspectjweaver jar entry...
		for (String jarEntry : javaClasspath) {
			if(jarEntry.contains("aspectjweaver")) {
				this.aspectWeaverPath = jarEntry;
				break;
			}
		}
		return javaClasspath;
	}
	*/

}
