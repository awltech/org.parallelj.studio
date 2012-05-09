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
package org.parallelj.designer.extension.command;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.parallelj.designer.extension.Activator;
import org.parallelj.designer.extension.core.DependencyManagementJob;
import org.parallelj.designer.extension.dialog.SelectionDialog;
import org.parallelj.designer.extension.part.contrib.BusinessProcedureContribution;

public class DependencyManagementCommand extends AbstractHandler {

	/**
	 * Action Selection, managed by subclasses
	 */
	protected IStructuredSelection selection;

	/**
	 * Workbench Window
	 */
	protected IWorkbenchWindow iWorkbenchWindow;

	private IJavaProject project = null;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		iWorkbenchWindow = HandlerUtil.getActiveWorkbenchWindow(event);
		selection = (IStructuredSelection) HandlerUtil
				.getCurrentSelection(event);
		List<BusinessProcedureContribution> contributions = getIFilesFromSelection(this.selection);
		if (!contributions.isEmpty()) {
			DependencyManagementJob job = new DependencyManagementJob(
					"ParallelJ : " + Activator.PLUGIN_ID, contributions,
					project);
			job.schedule();
		}
		return null;
	}

	/**
	 * This method allows to display a wizard to show all the business
	 * procedures as per user contribution , and ask the user to select the ones
	 * he wants
	 * 
	 * @param selection
	 *            The project selected by an user
	 * @return a List of all the business procedure contribution selected by the
	 *         user
	 */
	private List<BusinessProcedureContribution> getIFilesFromSelection(
			IStructuredSelection selection) {
		final List<BusinessProcedureContribution> contributions = new ArrayList<BusinessProcedureContribution>();
		Object firstElement = selection.getFirstElement();
		if (firstElement instanceof IJavaProject)
			project = ((IJavaProject) firstElement);
		if (project == null)
			return contributions;
		new SelectionDialog(this.iWorkbenchWindow, project) {
			@Override
			protected void performOperation(boolean allSelected) {
				if (allSelected)
					contributions.addAll(this.allContributions);
				else
					contributions.addAll(this.selectedContributions);
			}
		}.open();
		return contributions;
	}
}
