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
package org.parallelj.codegen.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.parallelj.codegen.Activator;
import org.parallelj.codegen.constants.Constants;
import org.parallelj.codegen.jobs.ProgramGenerationJob;
import org.parallelj.model.Specification;

/**
 * Abstract Test Case for ParallelJ generated classes. Process is centralized into abstract class.
 *
 * @author Atos Worldline
 */

public abstract class GeneratedCodeTestCase {
	
	protected static String FORK_PROJECT_NAME = "SampleTestProject";
	
	protected static ForkJavaProject forkProject;

	/**
	 * Tested class package name
	 */
	protected static final String PACKAGE_NAME = "org.pj.test";

	protected static ProgramGenerationJob generator = null;

	/**
	 * This method run exactly once before all the test methods in that class
	 * run
	 */
	@BeforeClass
	public static void oneTimeSetUp() throws Exception {
		if ((PACKAGE_NAME == null) || (PACKAGE_NAME == "")) {
			throw new IllegalStateException(
					"PACKAGE_NAME constant must be set before test execution");
		}
		if ((FORK_PROJECT_NAME == null) || (FORK_PROJECT_NAME == "")) {
			throw new IllegalStateException(
					"FORK_PROJECT_NAME constant must be set before test execution");
		}
		
		/*
		 * Create fork java project
		 */
		forkProject = new ForkJavaProject(FORK_PROJECT_NAME, Activator.class);
		forkProject.addTextFileToForkProject(Constants.Dirs.DIR_TESTS_RESOURCES+"/dont/erase/CallableProj.java", Constants.Dirs.DIR_MAIN_JAVA+"/dont/erase/CallableProj.java");
		forkProject.addTextFileToForkProject(Constants.Dirs.DIR_TESTS_RESOURCES+"/dont/erase/RunnableProj.java", Constants.Dirs.DIR_MAIN_JAVA+"/dont/erase/RunnableProj.java");
		/*
		 *  Copy model files to fork project and run generator		
		 */
		List<IFile> lstFiles = new ArrayList<IFile>();
		lstFiles.add(forkProject.addTextFileToForkProject(Constants.Dirs.DIR_TESTS_RESOURCES+"/diagram.pjd", Constants.Dirs.DIR_MODELS+"/diagram.pjd"));
		
		List<Specification> specifications = new ArrayList<Specification>();
		ResourceSet resourceSet = new ResourceSetImpl();
		for (IFile file : lstFiles) {
			URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
			Resource resource = resourceSet.getResource(uri, true);
			Specification specification = null;
			for (Iterator<EObject> iterator = resource.getContents().iterator(); iterator.hasNext() && specification == null;) {
				EObject next = iterator.next();
				if (next instanceof Specification)
					specification = (Specification) next;
			}
			if (specification != null)
				specifications.add(specification);
		}
		runGenerator(forkProject.getPrj(), specifications);
	}

	protected abstract String getClassName();

	/**
	 * This method run exactly once after all the tests in the class have been
	 * run
	 */
	@AfterClass
	public static void oneTimeTearDown() {
	}
	
	/**
	 * This method run before each test in this class
	 */
	@Before
	public void setUp() {
	}

	/**
	 * This method run after each test in this class
	 */
	@After
	public void tearDown() {
	}

	/**
	 * Run the Generator using WME, Xpand, Xtext and Xtend
	 */
	private static void  runGenerator(IProject project, List<Specification> Specification) {
		try {
			ProgramGenerationJob programGenerationJob = new ProgramGenerationJob(Specification);
			programGenerationJob.generate(new NullProgressMonitor());
		} catch (Exception e1) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR,Activator.PLUGIN_ID,"An exception has been thrown while generating source code", e1));
		}

		try {
			project.refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR,Activator.PLUGIN_ID,"An exception has been thrown while refreshing source code", e));
		}
	}
}
