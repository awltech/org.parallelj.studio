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

import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.swt.widgets.Display;
import org.parallelj.codegen.Activator;
import org.parallelj.codegen.constants.CodeGeneratorMessages;
import org.parallelj.codegen.constants.Constants;
import org.parallelj.model.Specification;


public class ProgramGenerationJob extends MWEEObjectGeneratorJob<Specification> {

	public ProgramGenerationJob(Collection<Specification> eObject) {
		super(CodeGeneratorMessages.CODEGEN_JOBNAME, eObject);
	}


	private static final String PATH_TO_WORKFLOW = Constants.PATH_TO_WORKFLOW;
	private static final String SLOT_CONTENT_KEY = "specification";
	private static final String OUTLET_NAME = "outlet.src.once.dir";
	private static final String OUTLET_TEST_NAME = "outlet.src.test.once.dir";
	private static final String GENERATED_SOURCE_PATH = Constants.Dirs.DIR_MAIN_JAVA;
	private static final String GENERATED_SOURCE_TEST_PATH = Constants.Dirs.DIR_TESTS_JAVA;

	@Override
	public IStatus generate(IProgressMonitor monitor) {
		IStatus status = super.generate(monitor);
//		regenerateAssociatedMBeans(monitor);
		return status;
	}

	@Override
	protected boolean validate(final Specification specification) {
		final IFile element = WorkspaceSynchronizer.getFile(specification.eResource());
		Display.getDefault().syncExec(new Runnable() {

			public void run() {
				// TODO : To be implemented !
			}
		});
		boolean hasErrors = false;
		IMarker[] markers;
		try {
			markers = element.findMarkers(IMarker.PROBLEM, true,
					IResource.DEPTH_INFINITE);
			for (int i = 0; i < markers.length && !hasErrors; i++) {
				if (markers[i].getAttribute(IMarker.SEVERITY).equals(
						IMarker.SEVERITY_ERROR)) {
					hasErrors = true;
					break;
				}
			}
			return !hasErrors;
		} catch (CoreException e) {
			Activator
					.getDefault()
					.getLog()
					.log(
							new Status(
									IStatus.ERROR,
									Activator.PLUGIN_ID,
									CodeGeneratorMessages.CODEGEN_VALIDATION_ERROR,
									e));
			return false;
		}
	}

	@Override
	protected String getGeneratedSourcePath() {
		return GENERATED_SOURCE_PATH;
	}

	@Override
	protected String getOutletName() {
		return OUTLET_NAME;
	}

	@Override
	protected String getPathToWorkflow() {
		return PATH_TO_WORKFLOW;
	}

	@Override
	protected String getSlotContentKey() {
		return SLOT_CONTENT_KEY;
	}

	@Override
	protected String getGeneratedSourceTestPath() {
		return GENERATED_SOURCE_TEST_PATH;
	}

	@Override
	protected String getOutletTestName() {
		return OUTLET_TEST_NAME;
	}

}
