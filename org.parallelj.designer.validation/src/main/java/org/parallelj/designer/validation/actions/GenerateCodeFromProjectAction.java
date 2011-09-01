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

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.parallelj.codegen.jobs.ProgramGenerationJob;
import org.parallelj.designer.extension.tools.ParallelJDiagramValidator;
import org.parallelj.designer.validation.codegen.ui.GenerateCodeMessages;
import org.parallelj.designer.validation.codegen.ui.SelectionDialog;
import org.parallelj.model.Specification;

/**
 * Action to generate code contained by a Project.
 * 
 */
public class GenerateCodeFromProjectAction implements IObjectActionDelegate {

	/**
	 * Action Selection, managed by subclasses
	 */
	protected IStructuredSelection selection;

	/**
	 * Workbench Window
	 */
	protected IWorkbenchWindow iWorkbenchWindow;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.
	 * action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.iWorkbenchWindow = targetPart.getSite().getWorkbenchWindow();
	}

	@Override
	public void run(IAction action) {

		List<IFile> iFiles = getIFilesFromSelection(this.selection);

		List<File> selectedFiles = new ArrayList<File>();
		if (iFiles == null || iFiles.size() == 0)
			return;
		for (IFile iFile : iFiles)
			selectedFiles.add(new File(iFile.getLocation().toString()));

		ResourceSet resourceSet = new ResourceSetImpl();
		Collection<Specification> specifications = new ArrayList<Specification>();

		for (IFile iFile : iFiles) {
			Resource resource = resourceSet.getResource(URI
					.createPlatformResourceURI(iFile.getFullPath().toString(),
							true), true);
			Specification specification = null;
			for (Iterator<EObject> iterator = resource.getContents().iterator(); iterator
					.hasNext() && specification == null;) {
				EObject next = iterator.next();
				if (next instanceof Specification)
					specification = (Specification) next;
			}
			if (specification != null) {
				if (!ParallelJDiagramValidator.validate(specification)) {				
					specifications.add(specification);
				}
			}
		}

		if (specifications.size() > 0) {
			ProgramGenerationJob programGenerationJob = new ProgramGenerationJob(
					specifications);
			programGenerationJob.schedule();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action
	 * .IAction, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = (IStructuredSelection) selection;
	}

	private List<IFile> getIFilesFromSelection(IStructuredSelection selection) {
		final List<IFile> diagramFiles = new ArrayList<IFile>();
		Object firstElement = selection.getFirstElement();
		IProject project = null;
		if (firstElement instanceof IProject)
			project = (IProject) firstElement;
		else if (firstElement instanceof IJavaProject)
			project = ((IJavaProject) firstElement).getProject();
		if (project == null)
			return diagramFiles;
		new SelectionDialog(this.iWorkbenchWindow, project,
				new GenerateCodeMessages()) {

			@Override
			protected void performOperation(boolean allSelected) {
				if (allSelected)
					diagramFiles.addAll(this.allModels);
				else
					diagramFiles.addAll(this.selectedModels);

			}
		}.open();
		return diagramFiles;
	}

}
