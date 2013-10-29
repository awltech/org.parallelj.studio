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
package org.parallelj.designer.validation.builder;

import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.parallelj.designer.validation.DiagramValidationEngine;
import org.parallelj.designer.validation.DiagramValidationPlugin;
import org.parallelj.designer.validation.providers.MarkerNavigationProvider;

/**
 * Project builder, used for Diagram verification On SAVE of file with
 * .pjd extension.
 */
public class ProjectBuilder extends IncrementalProjectBuilder {

	@Override
	protected IProject[] build(int kind, Map args, IProgressMonitor monitor)
			throws CoreException {

		boolean previousAutoBuildState = disableAutoBuild();
		try {
			if (isProjectBuildable()) {
				if (kind == IncrementalProjectBuilder.FULL_BUILD) {
					getProject().accept(new ResourceVisitor());
				} else {
					if (getDelta(getProject()) == null) {
						getProject().accept(new ResourceVisitor());
					} else {
						getDelta(getProject()).accept(
								new ResourceDeltaVisitor());
					}
				}
			}
		} catch (Exception e) {
			DiagramValidationPlugin
					.getDefault()
					.logError(
							"An exception has been thrown while building ParallelJ project",
							e);
		} finally {
			if (previousAutoBuildState) {
				enableAutoBuild();
			}
		}
		return null;
	}

	/**
	 * Checks if Project is Buildable
	 * 
	 * @return true if project is buildable (no build path errors), false
	 *         otherwise
	 */
	private boolean isProjectBuildable() {
		boolean hasErrors = false;
		try {
			IResource myRes = (IResource) getProject();
			IMarker[] problemsList = myRes.findMarkers(IMarker.PROBLEM, true,
					IResource.DEPTH_INFINITE);
			for (IMarker problem : problemsList) {
				if (problem.getResource().getType() == IResource.PROJECT) {
					hasErrors = true;
					problem.getType();
					String info = "unknown";
					if (problem.getType().equals(
							"org.eclipse.jdt.core.buildpath_problem")) {
						info = "build path";
					}
					String message = "ParallelJ Builder cannot be run in project '"
							+ getProject().getName()
							+ "' because it has "
							+ info + " problem(s).";
					if (info.equals("unknown")) {
						message += " (" + problem.getType() + ")";
					}
					DiagramValidationPlugin.getDefault().logWarn(message);
					break;
				}
			}
		} catch (CoreException ce) {
			DiagramValidationPlugin
					.getDefault()
					.logError(
							"An exception has been thrown while checking if project is buildable",
							ce);
		}
		return !hasErrors;
	}

	/**
	 * Enable Eclipse auto build.
	 */
	public static void enableAutoBuild() {
		try {
			IWorkspace ws = ResourcesPlugin.getWorkspace();
			IWorkspaceDescription desc = ws.getDescription();
			desc.setAutoBuilding(true);
			ws.setDescription(desc);
		} catch (CoreException e) {
			DiagramValidationPlugin
					.getDefault()
					.logError(
							"An exception has been thrown while trying to enable Auto-build",
							e);
		}
	}

	/**
	 * Disable Eclipse auto build.
	 */
	public static boolean disableAutoBuild() {
		boolean previousAutoBuildState = false;

		try {
			IWorkspace ws = ResourcesPlugin.getWorkspace();
			IWorkspaceDescription desc = ws.getDescription();
			previousAutoBuildState = desc.isAutoBuilding();
			desc.setAutoBuilding(false);
			ws.setDescription(desc);
		} catch (CoreException e) {
			DiagramValidationPlugin
					.getDefault()
					.logError(
							"An exception has been thrown while trying to disable Auto-build",
							e);
		}

		return previousAutoBuildState;
	}

	/**
	 * Inner class for Resource visitor implementing the Visitor pattern. It
	 * visits very time when the project is build.
	 * 
	 */
	private class ResourceVisitor implements IResourceVisitor {

		public boolean visit(IResource resource) throws CoreException {
			verifyResource(resource);
			return true;
		}

	}

	/**
	 * Resource delta visitor
	 * 
	 */
	private class ResourceDeltaVisitor implements IResourceDeltaVisitor {

		public boolean visit(IResourceDelta delta) throws CoreException {
			verifyResource(delta.getResource());
			return true;
		}

	}

	/**
	 * Verifies the resource passed as parameter if this one is an ParallelJ
	 * diagram
	 * 
	 * @param resource
	 *            : Resource
	 */
	protected void verifyResource(IResource resource) {
		boolean isValidFile = resource instanceof IFile
				&& "pjd"
						.equals(resource.getFullPath().getFileExtension());
		if (isValidFile) {
			if (resource.exists()) {
				DiagramValidationEngine.runValidation((IFile) resource);
			} else {
				MarkerNavigationProvider.deleteMarkers((IFile) resource);
			}
		}

	}

}
