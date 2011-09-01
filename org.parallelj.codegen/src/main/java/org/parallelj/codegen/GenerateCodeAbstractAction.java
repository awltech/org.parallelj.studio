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
package org.parallelj.codegen;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.parallelj.codegen.jobs.ProgramGenerationJob;
import org.parallelj.model.Specification;

/**
 * This class models an abstract Code Generation Action.
 * 
 * @author mvanbesien/mhays
 * @version $Revision: 1.6 $
 * @since $Date: 2009/02/26 15:36:03 $
 * 
 */
public abstract class GenerateCodeAbstractAction implements
		IObjectActionDelegate {

	/**
	 * Action Selection, managed by subclasses
	 */
	protected IStructuredSelection selection;

	/**
	 * Workbench Window
	 */
	protected IWorkbenchWindow iWorkbenchWindow;

	/**
	 * Handle the Mbean composite tasks generate in the project
	 */
	public static List<String> generatedMBeans = new ArrayList<String>();

	/**
	 * Logger instance
	 */
	protected ILog logger = Platform.getLog(Platform
			.getBundle(Activator.PLUGIN_ID));

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
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
			if (specification != null)
				specifications.add(specification);
		}

		ProgramGenerationJob programGenerationJob = new ProgramGenerationJob(
				specifications);
		programGenerationJob.schedule();

	}

	/**
	 * To be implemented by subclasses, in order to find back files to generate
	 * from selection
	 * 
	 * @param selection
	 *            : IStructuredSelection
	 * @return List of IFiles
	 */
	protected abstract List<IFile> getIFilesFromSelection(
			IStructuredSelection selection);

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

	/**
	 * Method useful to MWE extend file (common.ext)
	 */
	public static List<String> getGeneratedMbeans() {
		return generatedMBeans;
	}

}
