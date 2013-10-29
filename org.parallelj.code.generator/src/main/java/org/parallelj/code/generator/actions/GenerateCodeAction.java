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
package org.parallelj.code.generator.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.parallelj.code.generator.core.ParallelJCodeGeneratorJob;
import org.parallelj.designer.edit.parts.SpecificationEditPart;
import org.parallelj.designer.extension.dialog.ValidationErrorDialog;
import org.parallelj.designer.extension.tools.ParallelJDiagramValidator;
import org.parallelj.model.Specification;

/**
 * This action allows to run the ParallelJ source code generator.
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class GenerateCodeAction implements IObjectActionDelegate {

	/**
	 * Diagram
	 */
	private Diagram diagram;

	/**
	 * Action Selection, managed by subclasses
	 */
	protected IStructuredSelection selection;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.
	 * action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		Collection<Specification> specifications = new ArrayList<Specification>();
		Resource resource = diagram.eResource();
		IProject project = WorkspaceSynchronizer.getFile(resource).getProject();
		Specification specification = null;
		boolean gotErrors = false;
		for (Iterator<EObject> iterator = resource.getContents().iterator(); iterator
				.hasNext() && specification == null;) {
			EObject next = iterator.next();
			if (next instanceof Specification)
				specification = (Specification) next;
		}
		if (specification != null) {
			specifications.add(specification);
		}

		if (specifications.size() > 0) {
			Iterator<Specification> it = specifications.iterator();
			while (it.hasNext()) {
				Specification specificationObject = it.next();

				gotErrors = ParallelJDiagramValidator
						.validate(specificationObject);
				if (gotErrors) {
					ValidationErrorDialog validationErrorDialog = new ValidationErrorDialog();
					validationErrorDialog.open();
					break;
				}
			}
		}
		if (!gotErrors) {
			ParallelJCodeGeneratorJob job = new ParallelJCodeGeneratorJob(
					specifications, project);
			job.schedule();
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
		Object selected = ((IStructuredSelection) selection).getFirstElement();
		if (selected instanceof SpecificationEditPart) {
			this.diagram = ((SpecificationEditPart) selected).getDiagramView();
		}
		action.setEnabled(this.diagram != null);
	}

}
