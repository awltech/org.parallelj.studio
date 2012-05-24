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
package org.parallelj.extensions.data.generator;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;

import com.sun.tools.xjc.Driver;

public class XjcGenerator {
	public static ILog logger = Platform.getLog(Platform
			.getBundle(Activator.PLUGIN_ID));

	private IPath xsdFile;
	private IPackageFragment pckGeneration;
	private IJavaProject selectedProject;
	private String options;

	public XjcGenerator(IPath xsdFile, IPackageFragment pckGeneration,
			IJavaProject selectedProject, String options) {
		this.xsdFile = xsdFile;
		this.pckGeneration = pckGeneration;
		this.selectedProject = selectedProject;
		this.options = options;
		
	}

	public void run() throws Exception {
		List<String> xjcArgs = new ArrayList<String>();
		IPath currentProjectPath = selectedProject.getProject().getLocation();

		// Output dir where JAXB files will be generated
		xjcArgs.add("-d");
		xjcArgs.add(selectedProject
				.getProject()
				.getLocation()
				.removeLastSegments(1)
				.append(pckGeneration.getAncestor(IType.PACKAGE_FRAGMENT_ROOT)
						.getPath()).toOSString());

		// For xa:date, xa:dateTime, xs:time transformation in java.util.Date
		xjcArgs.add("-extension");

		// Package for the JAXB pojos generation
		xjcArgs.add("-p");
		xjcArgs.add(pckGeneration.getElementName());

		// Schema to generate JAXB files
		xjcArgs.add("-xmlschema");
		xjcArgs.add(currentProjectPath.toString() + Path.SEPARATOR
				+ xsdFile.toString());

		// for xjc option
		if (this.options != null && this.options.trim().length() > 0) {

			String[] split = this.options.split(" ");
			for (String string : split) {
				xjcArgs.add(string);
			}
		}

		String[] a = new String[xjcArgs.size()];
		a = xjcArgs.toArray(a);

		// Generates JAXB files from XSD
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		int result = Driver.run(a, ps, ps);
		String output = os.toString("UTF8");

		if (result != 0) {
			logger.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
					IStatus.ERROR, "Error generation Pojos : " + output, null));
			throw new Exception("Error generation Pojos : " + output);
		}

		try {
			selectedProject.getProject().refreshLocal(IResource.DEPTH_INFINITE,
					new org.eclipse.core.runtime.NullProgressMonitor());
		} catch (CoreException e) {
			logger.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
					IStatus.ERROR, "Unable to refresh Project : "
							+ selectedProject.getProject().getName(), e));
		}
	}

	public static void copyTextFileFromPluginToProject(
			InputStream resourceAsStream, String destination, IProject project)
			throws IOException, CoreException {
		// destination File
		IFile destFile = project.getFile(destination);
		String destFileName = destFile.getLocation().toString();
		File dstFile = new File(destFileName);
		if (!dstFile.getParentFile().exists()) {
			dstFile.getParentFile().mkdirs();
		}

		// Copy
		InputStreamReader reader = new InputStreamReader(resourceAsStream);
		BufferedWriter writer = new BufferedWriter(new FileWriter(dstFile));
		char[] buffer;

		buffer = new char[resourceAsStream.available()];
		while (reader.ready()) {
			reader.read(buffer);
			writer.write(buffer);
		}
		writer.close();
		reader.close();
		project.refreshLocal(2, null);
	}

	public static void copyTextFileFromPluginToProject(String origine,
			String destination, IProject project) {
		try {
			// Original file
			InputStream resourceAsStream = XjcGenerator.class
					.getResourceAsStream(origine);
			copyTextFileFromPluginToProject(resourceAsStream, destination,
					project);
		} catch (Exception e) {
			logger.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
					IStatus.ERROR,
					"Error coping file from plugin to user workspace "
							+ origine, e));
		}
	}
}
