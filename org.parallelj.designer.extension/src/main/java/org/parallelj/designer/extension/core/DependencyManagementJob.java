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
package org.parallelj.designer.extension.core;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.core.IJavaProject;
import org.jdom.JDOMException;
import org.parallelj.designer.extension.Activator;
import org.parallelj.designer.extension.Messages;
import org.parallelj.designer.extension.helpers.DependencyManagementHelper;
import org.parallelj.designer.extension.part.contrib.BusinessProcedureContribution;

/**
 * This job will help to add dependencies in POM.xml for business procedure
 * contribution.
 * 
 * @author A169104
 * 
 */
public class DependencyManagementJob extends Job {

	/**
	 * This is the user selected project.
	 * 
	 **/
	private IJavaProject selectedJavaProject;

	private List<BusinessProcedureContribution> contributions;

	public DependencyManagementJob(String name,
			List<BusinessProcedureContribution> contributions,
			IJavaProject project) {
		super(name);
		this.contributions = contributions;
		this.selectedJavaProject = project;
		this.setPriority(Job.BUILD);
		this.setUser(true);
	}

	@Override
	protected IStatus run(IProgressMonitor mon) {
		mon.beginTask(Messages.JOB01I.getMessage(), 1);
		mon.setTaskName(Messages.JOB02I.getMessage());
		ResourceSet resourceSet = new ResourceSetImpl();
		try {
			DependencyManagementHelper.updatePom(resourceSet,
					selectedJavaProject, contributions);
		} catch (IOException e) {
			return new Status(Status.ERROR, Activator.PLUGIN_ID,
					Messages.JOB02E.getMessage(e.getMessage()));
		} catch (CoreException e) {
			return new Status(Status.ERROR, Activator.PLUGIN_ID,
					Messages.JOB02E.getMessage(e.getMessage()));
		}
		catch (JDOMException e) {
			return new Status(Status.ERROR, Activator.PLUGIN_ID,
					Messages.JOB02E.getMessage(e.getMessage()));
		}

		if (mon.isCanceled()) {
			Activator.logWarn(Messages.JOB03I.getMessage());
			return Status.CANCEL_STATUS;
		}
		mon.worked(1);
		mon.done();
		return new Status(Status.OK, Activator.PLUGIN_ID,
				Messages.JOB04I.getMessage());
	}
}
