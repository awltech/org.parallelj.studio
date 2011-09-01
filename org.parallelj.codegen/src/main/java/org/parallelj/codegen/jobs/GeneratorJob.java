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
package org.parallelj.codegen.jobs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.ui.progress.IProgressConstants;
import org.parallelj.codegen.Activator;
import org.parallelj.codegen.constants.CodeGeneratorMessages;
import org.parallelj.codegen.constants.Constants;

public abstract class GeneratorJob<T> extends WorkspaceJob {

	protected Collection<T> initialElements;

	protected static String defaultname = "ParallelJ Code Generator";

	/**
	 * Default constructor required for the extension point mechanism
	 */
	public GeneratorJob() {
		this(defaultname, null);
	}

	/**
	 * @param name
	 * @param initialElements
	 */
	public GeneratorJob(String name, Collection<T> initialElements) {
		super(name);
		this.initialElements = initialElements;
		this.setPriority(Job.BUILD);
		this.setUser(true);
		this.setRule(ResourcesPlugin.getWorkspace().getRoot());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.resources.WorkspaceJob#runInWorkspace(org.eclipse.core
	 * .runtime.IProgressMonitor)
	 */
	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
		this.setProperty(IProgressConstants.KEEP_PROPERTY, Boolean.FALSE);
		this.setProperty(IProgressConstants.KEEPONE_PROPERTY, Boolean.FALSE);
		IStatus status = generate(monitor);
		performCleanup();
		return status;
	}

	protected void performCleanup() {
		// Does nothing. Intended to be subclassed.
	}

	/**
	 * @param monitor
	 * @return
	 */
	public IStatus generate(IProgressMonitor monitor) {
		if (this.initialElements == null || this.initialElements.size() == 0)
			return new Status(IStatus.WARNING, Activator.PLUGIN_ID,
					CodeGeneratorMessages.ABST_CODEGEN_NOELEMENT);

		IStatus returnStatus = Status.OK_STATUS;
		monitor.beginTask(CodeGeneratorMessages.ABST_CODEGEN_VALIDATING, 1);
		List<T> validElements = new ArrayList<T>();
		for (final T element : initialElements) {
			if (validate(element))
				validElements.add(element);
		}
		monitor.worked(1);
		monitor.beginTask(CodeGeneratorMessages.ABST_CODEGEN_GENERATING, validElements.size() + 3);
		monitor.worked(2);

		for (int i = 0; i < validElements.size(); i++) {
			T element = validElements.get(i);

			IProject project = getProjectFromElement(element);
			refreshSourceFolders(project, monitor);
			monitor.subTask(String.format(CodeGeneratorMessages.ABST_CODEGEN_GENERATING_SUB,
					display(element), i + 1, validElements.size()));
			generateCodeFromElement(element, monitor);
			refreshSourceFolders(project, monitor);
			monitor.worked(1);
		}

		return returnStatus;
	}

	/**
	 * @param initialElements
	 */
	public void setInitialElements(Collection<T> initialElements) {
		this.initialElements = initialElements;
	}

	/**
	 * @param element
	 * @return
	 */
	protected String display(T element) {
		return element.toString();
	}

	/**
	 * @param element
	 * @return
	 */
	protected abstract IProject getProjectFromElement(T element);

	/**
	 * @param element
	 * @return
	 */
	protected abstract boolean validate(T element);

	/**
	 * 
	 * Generate the code for on element
	 * 
	 * @param element
	 * 		The input element for the code generation  
	 * @param monitor
	 * 		Monitor useful for the job
	 * @return
	 * 		The result status for code generation
	 */
	protected abstract IStatus generateCodeFromElement(T element, IProgressMonitor monitor);

	/**
	 * @param javaProject
	 * @return
	 */
	protected static IPackageFragmentRoot getRootPackageFragmentRootFromProject(
			IJavaProject javaProject) {
		IFolder folder = javaProject.getProject().getFolder(new Path(Constants.Dirs.DIR_MAIN_JAVA));
		if (folder == null || !folder.exists())
			return null;
		return javaProject.getPackageFragmentRoot(folder);
	}

	/**
	 * @param eObject
	 * @return
	 */
	protected static IPackageFragmentRoot getRootPackageFragmentRootFromEObject(EObject eObject) {
		IFile file = WorkspaceSynchronizer.getFile(eObject.eResource());
		IProject project = file.getProject();
		if (project == null)
			return null;
		IJavaProject javaProject = JavaCore.create(project);
		return getRootPackageFragmentRootFromProject(javaProject);
	}

	/**
	 * @param project
	 * @param monitor
	 */
	protected void refreshSourceFolders(IProject project, IProgressMonitor monitor) {
		try {
			monitor.subTask(String.format(CodeGeneratorMessages.ABST_CODEGEN_REFRESHING_PROJECT,
					project.getName()));
			project.getFolder(new Path(Constants.Dirs.DIR_MAIN_JAVA)).refreshLocal(
					IResource.DEPTH_INFINITE, monitor);
			project.getFolder(new Path(Constants.Dirs.DIR_MAIN_RESOURCES)).refreshLocal(
					IResource.DEPTH_INFINITE, monitor);
			project.getFolder(new Path(Constants.Dirs.DIR_TESTS_JAVA)).refreshLocal(
					IResource.DEPTH_INFINITE, monitor);
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(
					new Status(IStatus.WARNING, Activator.PLUGIN_ID,
							CodeGeneratorMessages.ABST_CODEGEN_REFRESHING_PROJECT_ERROR, e));
		}
	}
}
