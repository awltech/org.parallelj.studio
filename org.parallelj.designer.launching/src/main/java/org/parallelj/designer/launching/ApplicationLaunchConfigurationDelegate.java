package org.parallelj.designer.launching;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.ajdt.core.AspectJPlugin;
import org.eclipse.ajdt.internal.launching.LTWUtils;
import org.eclipse.ajdt.internal.ui.text.UIMessages;
import org.eclipse.core.internal.resources.ResourceStatus;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.internal.launching.LaunchingMessages;
import org.eclipse.jdt.launching.ExecutionArguments;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.jdt.launching.IRuntimeClasspathEntry;
import org.eclipse.jdt.launching.IVMRunner;
import org.eclipse.jdt.launching.JavaLaunchDelegate;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.VMRunnerConfiguration;
import org.eclipse.osgi.util.NLS;
import org.parallelj.designer.launching.internal.ProgramUtils;

@SuppressWarnings("restriction")
public class ApplicationLaunchConfigurationDelegate extends JavaLaunchDelegate {

	private static final String classLoaderOption = "-Djava.system.class.loader"; //$NON-NLS-1$
	private static final String ajClasspathOption = "-Daj.class.path"; //$NON-NLS-1$
	private static final String defaultLauncherClass = "org.parallelj.launching.internal.DefaultLauncher";
	

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
		
		generateAOPConfigFiles(configuration);
		
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
		
		// Classpath
		String[] classpath = getClasspath(configuration);
		
		// Program & VM args
		String pgmArgs = getProgramArguments(configuration);
		String vmArgs = getVMArguments(configuration);
		vmArgs = addExtraVMArgs(vmArgs, classpath);
		ExecutionArguments execArgs = new ExecutionArguments(vmArgs, pgmArgs);
		
		// VM-specific attributes
		Map vmAttributesMap = getVMSpecificAttributesMap(configuration);
		
		String[] ltwClasspath = null;
		try {
			ltwClasspath = getLTWClasspath();
		} catch (MalformedURLException e) {
			throw new CoreException(new ResourceStatus(IStatus.ERROR, null, UIMessages.LTW_error_launching, e));
		} catch (IOException e) {
			throw new CoreException(new ResourceStatus(IStatus.ERROR, null, UIMessages.LTW_error_launching, e));
		}
		
		// Create VM config
		VMRunnerConfiguration runConfig = new VMRunnerConfiguration(mainTypeName, ltwClasspath);
		//String[] programArgumentsAsArray = execArgs.getProgramArgumentsArray();
		
		Map programArguments = configuration.getAttribute(ConfigurationConstants.PARALLELJ_PARAMETERS, new HashMap());

		@SuppressWarnings("unchecked")
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
		
		monitor.done();
	}

	/**
	 * Generate aop-ajc.xml files for any projects on the LTW aspectpath
	 * @param configuration
	 * @throws CoreException
	 */
	private void generateAOPConfigFiles(ILaunchConfiguration configuration) throws CoreException {
		IProject[] workspaceProjects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		IRuntimeClasspathEntry[] aspectPathEntries = getAspectPathEntries(configuration);
		for (int i = 0; i < aspectPathEntries.length; i++) {
			String location = aspectPathEntries[i].getLocation();
			for (int j = 0; j < workspaceProjects.length; j++) {
				IProject project = workspaceProjects[j];
				if(project.isOpen()) {
					IRuntimeClasspathEntry entry = JavaRuntime.newProjectRuntimeClasspathEntry(JavaCore.create(project));
					String projectLocation = entry.getLocation();
					if(projectLocation != null && projectLocation.equals(location)) {
						LTWUtils.generateLTWConfigFile(JavaCore.create(project));
					}
				}
			}
		}
		// Generate aop-ajc.xml file for main project if it's an AspectJ project
		String projectName = configuration.getAttribute(
				IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME,
				(String) null);
		if (projectName != null && !projectName.trim().equals("")) { //$NON-NLS-1$
			IProject project = AspectJPlugin.getWorkspace().getRoot()
					.getProject(projectName);
			if(project.isOpen() && project.hasNature(AspectJPlugin.ID_NATURE)) {
				LTWUtils.generateLTWConfigFile(JavaCore.create(project));				
			}
		}
	}

	/**
	 * Override getClasspath so that we also include the load time weaving
	 * aspectpath
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String[] getClasspath(ILaunchConfiguration configuration)
			throws CoreException {
		IRuntimeClasspathEntry[] entries = JavaRuntime
				.computeUnresolvedRuntimeClasspath(configuration);
		IRuntimeClasspathEntry[] aspectPathEntries = getAspectPathEntries(configuration);
		entries = JavaRuntime.resolveRuntimeClasspath(entries, configuration);
		List userEntries = new ArrayList(entries.length + aspectPathEntries.length);
		for (int i = 0; i < entries.length; i++) {
			if (entries[i].getClasspathProperty() == IRuntimeClasspathEntry.USER_CLASSES) {
				String location = entries[i].getLocation();
				if (location != null) {
					userEntries.add(location);
				}
			}
		}
		for (int i = 0; i < aspectPathEntries.length; i++) {
			String location = aspectPathEntries[i].getLocation();
			if (location != null) {
				userEntries.add(location);
			}
		}
		return (String[]) userEntries.toArray(new String[userEntries.size()]);
	}
	
	/**
	 * Get the load time weaving aspectpath from the given configuration
	 * @param configuration
	 * @return
	 * @throws CoreException
	 */
	private IRuntimeClasspathEntry[] getAspectPathEntries(ILaunchConfiguration configuration) throws CoreException {
		IRuntimeClasspathEntry[] rtes = new IRuntimeClasspathEntry[0];
		return rtes;
	}

	private String[] getLTWClasspath() throws IOException {
		URL resolvedaspectjWeaverJar = FileLocator.resolve(new URL(Platform.getBundle(AspectJPlugin.WEAVER_PLUGIN_ID).getEntry("/"), "aspectjweaver.jar")); //$NON-NLS-1$ //$NON-NLS-2$
		URL resolvedaspectjRTJar = FileLocator.resolve(new URL(Platform.getBundle(AspectJPlugin.RUNTIME_PLUGIN_ID).getEntry("/"), "aspectjrt.jar")); //$NON-NLS-1$ //$NON-NLS-2$
		String weaverPath = new Path(resolvedaspectjWeaverJar.getFile()).toOSString();
		String rtPath = new Path(resolvedaspectjRTJar.getFile()).toOSString();
		return new String[] {weaverPath, rtPath};
	}

	private String addExtraVMArgs(String vmArgs, String[] ajClasspath) {
		StringBuffer sb = new StringBuffer(vmArgs);
		sb.append(' '); 
		sb.append(classLoaderOption);
		sb.append('='); 
		sb.append("org.aspectj.weaver.loadtime.WeavingURLClassLoader"); //$NON-NLS-1$
		sb.append(' '); 
		sb.append(ajClasspathOption);
		sb.append('='); 
		sb.append('\"'); 
		for (int i = 0; i < ajClasspath.length; i++) {
			if(i != 0) {
				sb.append(File.pathSeparator); 
			}
			sb.append(ajClasspath[i]);			
		}
		sb.append('\"'); 
		
		return sb.toString();
	}
	
}
