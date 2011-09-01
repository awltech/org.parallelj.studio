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

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.emf.mwe.core.WorkflowRunner;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.issues.IssuesImpl;
import org.eclipse.emf.mwe.core.issues.MWEDiagnostic;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitorAdapter;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.parallelj.codegen.Activator;

/**
 * @author mvanbesien/mhays
 * 
 * @param <T>
 */
public abstract class MWEEObjectGeneratorJob<T extends EObject> extends
		GeneratorJob<T> {

	/**
	 * Properties to put in the MWE generator.
	 */
	protected Map<String, String> properties = new HashMap<String, String>();

	/**
	 * Default constructor required for the extension point mechanism
	 */
	public MWEEObjectGeneratorJob() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param name
	 * @param eObjects
	 */
	public MWEEObjectGeneratorJob(String name, Collection<T> eObjects) {
		super(name, eObjects);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.parallelj.codegen.jobs.GeneratorJob#generateCodeFromElement
	 * (java.lang.Object, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected IStatus generateCodeFromElement(T eObject,
			IProgressMonitor monitor) {

		Map<String, Object> slotContents = new Hashtable<String, Object>();

		String projectPath = getProjectFromElement(eObject).getLocation()
				.toString();
		String curSrcPath = projectPath + "/" + getGeneratedSourcePath();
		properties.put(getOutletName(), curSrcPath);

		// Output directory for tests if any.
		if (getOutletTestName() != null) {
			properties.put(getOutletTestName(), projectPath + File.separator
					+ getGeneratedSourceTestPath());
		}

		slotContents.put(getSlotContentKey(), eObject);
		properties.put(getSlotContentKey(), eObject.eResource().getURIFragment(
				eObject));

		WorkflowRunner workflowRunner = new WorkflowRunner();

		boolean prepareOK = workflowRunner.prepare(getPathToWorkflow(),
				new ProgressMonitorAdapter(monitor), properties);
		final Issues issues = new IssuesImpl();
		IStatus returnStatus = Status.OK_STATUS;
		if (prepareOK) {
			boolean execOK = workflowRunner.executeWorkflow(slotContents,
					issues);
			if (!execOK && issues.getErrors().length > 0) {
				MultiStatus multiStatus = new MultiStatus(Activator.PLUGIN_ID,
						IStatus.OK,
						"MWE Issues were raised during Code Generation of "
								+ String.valueOf(eObject), null);
				for (MWEDiagnostic diag : issues.getIssues()) {
					Object o = diag.getElement();
					multiStatus
							.add(new Status(diag.getSeverity(),
									Activator.PLUGIN_ID, diag.getMessage()
											+ " in " + o, diag.getException()));
				}
				Platform.getLog(Platform.getBundle(Activator.PLUGIN_ID)).log(
						multiStatus);
				returnStatus = multiStatus;
			}
		}
		return returnStatus;
	}

	@Override
	protected IProject getProjectFromElement(T element) {
		return getFileFromElement(element).getProject();
	}

	protected IFile getFileFromElement(T element) {
		return WorkspaceSynchronizer.getFile(element.eResource());
	}

	protected abstract String getPathToWorkflow();

	protected abstract String getSlotContentKey();

	protected abstract String getOutletName();

	/**
	 * Get the property name, defined in the MWE workflow, for the directory
	 * where the tests are generated.
	 * 
	 * @return
	 */
	protected abstract String getOutletTestName();

	protected abstract String getGeneratedSourcePath();

	/**
	 * Return the output project directory for generated tests
	 * 
	 * @return
	 * 
	 */
	protected abstract String getGeneratedSourceTestPath();

	@Override
	protected String display(EObject element) {
		return element.eResource().getURI().lastSegment();
	}

	@Override
	protected void performCleanup() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		EPackageRegistryImpl.getRegistry(classLoader).clear();
	}

}
