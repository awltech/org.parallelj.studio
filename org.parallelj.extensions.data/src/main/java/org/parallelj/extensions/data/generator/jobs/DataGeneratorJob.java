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
package org.parallelj.extensions.data.generator.jobs;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.progress.IProgressConstants;
import org.parallelj.extensions.data.generator.Activator;
import org.parallelj.extensions.data.generator.XjcGenerator;
import org.parallelj.extensions.data.generator.logs.Messages;

public class DataGeneratorJob extends WorkspaceJob {

	/**
	 * Code Generation Configuration object
	 */
	private DataConfiguration generationConfiguration;

	/**
	 * Creates new DataPerspective Generation Job with configuration passed as
	 * parameter.
	 * 
	 * @param configuration
	 *            Data Perspective Configuration
	 */
	public DataGeneratorJob(DataConfiguration configuration) {
		super(Messages.JOB05I.message());
		this.generationConfiguration = configuration;
		this.setPriority(Job.BUILD);
		this.setUser(true);
		this.setRule(ResourcesPlugin.getWorkspace().getRoot());
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor)
			throws CoreException {
		this.setProperty(IProgressConstants.KEEP_PROPERTY, Boolean.TRUE);

		// Starts the Job itself
		monitor.beginTask(Messages.JOB01I.message(), 2);

		// Generation of XML Pojos from XSD file if necessary.
		monitor.subTask(Messages.JOB02I.message());
		XjcGenerator xjc = new XjcGenerator(this.generationConfiguration
				.getXSDModel().getProjectRelativePath(),
				this.generationConfiguration.getGenerationPackage(), null,
				this.generationConfiguration.getGenerationPackage()
						.getJavaProject());
		try {
			xjc.run();
			monitor.worked(1);
			monitor.subTask(Messages.JOB03I.message());
			this.generationConfiguration
					.getGenerationPackage()
					.getResource()
					.refreshLocal(IResource.DEPTH_ONE,
							new NullProgressMonitor());
			monitor.worked(1);
		} catch (Exception e) {
			Status errorStatus = new Status(IStatus.ERROR, Activator.PLUGIN_ID,
					Messages.JOB01E.message(), e);
			Activator.getDefault().getLog().log(errorStatus);
			return errorStatus;
		}

		monitor.done();
		
		// If arrived here, it means everything went well. So returns OK status.
		return new Status(Status.OK, Activator.PLUGIN_ID,
				Messages.JOB04I.message());
	}
}
