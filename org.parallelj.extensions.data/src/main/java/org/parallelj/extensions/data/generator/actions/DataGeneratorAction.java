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
package org.parallelj.extensions.data.generator.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.parallelj.extensions.data.generator.Activator;
import org.parallelj.extensions.data.generator.jobs.DataGeneratorJob;
import org.parallelj.extensions.data.generator.jobs.DataConfiguration;
import org.parallelj.extensions.data.generator.logs.Messages;
import org.parallelj.extensions.data.generator.wizard.dialog.PackageSelectionDialog;

public class DataGeneratorAction implements IObjectActionDelegate {

	private IWorkbenchWindow currentWindow;

	protected IStructuredSelection selection;

	/**
	 * Constructor for DataGeneratorAction.
	 */
	public DataGeneratorAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		currentWindow = targetPart.getSite().getWorkbenchWindow();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		try {

			List<IFile> selectedFiles = getSelectedFiles(selection);

			// taking xsd file
			IFile selectedFile = selectedFiles != null
					&& selectedFiles.size() > 0 ? selectedFiles.iterator()
					.next() : null;
			if (selectedFile == null) {
				Activator.sendErrorToErrorLog(Messages.SELECT_FILE_ERROR
						.message());
			}

			IProject project = selectedFile.getParent().getProject()
					.getProject();

			IJavaProject selectedJavaProject = JavaCore.create(project);

			// opening a wizard
			if (selectedJavaProject != null) {
				PackageSelectionDialog packageSelectionDialog = new PackageSelectionDialog(
						currentWindow, selectedJavaProject, selectedFile);
				packageSelectionDialog.create();
				packageSelectionDialog.setBlockOnOpen(true);
				if (packageSelectionDialog.open() == 0) {
					DataConfiguration dpConfig = packageSelectionDialog
							.getResult();
					if (dpConfig != null)
						new DataGeneratorJob(dpConfig).schedule();
				}
			} else {
				Activator.sendErrorToErrorLog(Messages.SELECT_PROJECT_ERROR
						.message());
			}
		} catch (Exception e) {
			Activator.sendErrorToErrorLog(Messages.ACTION_ERROR.message(e
					.getMessage()));
		}

	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = (IStructuredSelection) selection;
	}

	/**
	 * File selected by user
	 * 
	 * @param selection
	 * @return
	 */
	private List<IFile> getSelectedFiles(IStructuredSelection selection) {
		List<IFile> files = new ArrayList<IFile>();
		Iterator<?> selectedElements = selection.iterator();
		while (selectedElements.hasNext()) {
			files.add((IFile) selectedElements.next());
		}
		return files;
	}
}
