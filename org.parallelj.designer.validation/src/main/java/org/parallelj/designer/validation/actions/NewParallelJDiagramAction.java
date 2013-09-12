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
package org.parallelj.designer.validation.actions;

import static java.io.File.separator;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.parallelj.designer.part.ParallelJDiagramEditorUtil;
import org.parallelj.designer.validation.DiagramValidationPlugin;

/**
 * Action for creating a Parallel J Diagram
 * 
 */
public class NewParallelJDiagramAction implements IObjectActionDelegate {

	private IProject selectedProject = null;

	public void run(IAction action) {

		final IFolder modelsLocation = selectedProject.getFolder("src"
				+ separator + "main" + separator + "resources" + separator
				+ "META-INF" + separator + "ParallelJ");

		if (!modelsLocation.exists()) {
			createFolder(modelsLocation);
		}

		String rootName = "default";
		String name = rootName;
		int index = 2;
		while (modelsLocation.getFile(name + "." + "pjd").exists()) {
			name = rootName + "_" + index;
			index++;
		}
		int code = 1;

		InputDialog inputDialog = new InputDialog(Display.getDefault()
				.getActiveShell() == null ? new Shell(Display.getDefault())
				: Display.getDefault().getActiveShell(), "ParallelJ",
				"New ParallelJ Diagram name :", name, new IInputValidator() {

					public String isValid(String newText) {
						return modelsLocation.getFile(newText).exists() ? "File with this name already exists"
								: null;
					}

				});
		int result = inputDialog.open();
		if (result != Dialog.OK) {
			return;
		}
		name = inputDialog.getValue();

		if (name == null || name.length() == 0)
			code = 2;

		if (code != 2) {
			URI uri = URI.createPlatformResourceURI(modelsLocation
					.getFullPath().append(name + "." + "pjd").toString(),
					true);
			Resource resource = ParallelJDiagramEditorUtil.createDiagram(uri,
					new NullProgressMonitor());
			try {
				ParallelJDiagramEditorUtil.openDiagram(resource);
			} catch (PartInitException e) {
				DiagramValidationPlugin.getDefault().logError(
						"Unable to open diagram  " + name + "." + "pjd",
						e);
			}
		}

	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.selectedProject = null;
		Object o = ((IStructuredSelection) selection).getFirstElement();
		if (action.getId().equals("newParallelJDiagramFromModels")
				&& o instanceof IResource) {
			this.selectedProject = ((IResource) o).getProject();
		} else if (action.getId().equals("newParallelJDiagramFromProject")) {
			if (o instanceof IJavaProject) {
				this.selectedProject = ((IJavaProject) o).getProject();
			} else if (o instanceof IProject) {
				this.selectedProject = (IProject) o;
			}
		}
		action.setEnabled(this.selectedProject != null);
	}

	private IStatus createFolder(IFolder destinationFolder) {
		if (!destinationFolder.getParent().exists()
				&& destinationFolder.getParent() instanceof IFolder)
			this.createFolder((IFolder) destinationFolder.getParent());
		try {
			destinationFolder.create(true, true, new NullProgressMonitor());
		} catch (CoreException e) {
			return new Status(IStatus.ERROR, DiagramValidationPlugin.PLUGIN_ID,
					"An exception has been thrown", e);
		}
		return Status.OK_STATUS;
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {

	}


}
