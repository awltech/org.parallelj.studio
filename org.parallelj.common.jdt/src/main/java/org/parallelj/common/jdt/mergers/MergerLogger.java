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
package org.parallelj.common.jdt.mergers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.StringTokenizer;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.parallelj.common.jdt.Activator;

public class MergerLogger {

	/**
	 * Writer instance used to write in the log file
	 */
	private Writer w = null;

	/**
	 * Create a new MergerLogger from a package name and a compilation unit
	 * name.
	 * 
	 * @param packageName
	 *            A package name
	 * @param compilationUnitName
	 *            A compilation unit name file
	 */
	MergerLogger(String packageName, String compilationUnitName) {
		/* Create a Writer to the log file */

		String logPath = logPath(packageName, compilationUnitName);
		File logDir = new File(logPath.substring(0, logPath.lastIndexOf(File.separator)));
		File logFile = new File(logPath);

		try {
			logDir.mkdirs();
			logFile.createNewFile();
		} catch (IOException ioe) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR,Activator.PLUGIN_ID,
					"An exception has been thrown", ioe));
		}

		try {
			this.w = new BufferedWriter(new FileWriter(logFile));
		} catch (IOException ioe) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR,Activator.PLUGIN_ID,
					"An exception has been thrown", ioe));
		}

		this.write("Merge " + packageName + "." + compilationUnitName);
	}

	/**
	 * Write a new line in the log file.
	 * 
	 * @param message
	 *            Message to write
	 */
	public void write(String message) {
		StringBuilder sb = new StringBuilder();

		sb.append("[");
		sb.append(new Date());
		sb.append("] ");
		sb.append(message);
		sb.append("\n");

		try {
			this.w.write(sb.toString());
		} catch (IOException ioe) {
		}
	}

	/**
	 * Build the absolute path to the log file.
	 * 
	 * @param packageName
	 *            A package name
	 * @param compilationUnitName
	 *            A compilation unit name
	 * @return An absolute path
	 */
	private String logPath(String packageName, String compilationUnitName) {
		StringBuilder path = new StringBuilder();

		if (System.getProperty("basedir") != null) {
			path.append(System.getProperty("basedir"));
			path.append(File.separatorChar);
			path.append("..");
		} else {
			path.append(ResourcesPlugin.getWorkspace().getRoot().getLocation().toString());
		}

		path.append(File.separatorChar);
		path.append(".metadata");
		path.append(File.separatorChar);
		path.append(".plugins");
		path.append(File.separatorChar);
		path.append(Activator.PLUGIN_ID);
		path.append(File.separatorChar);
		path.append("merger");
		path.append(File.separatorChar);

		StringTokenizer st = new StringTokenizer(packageName, ".");

		while (st.hasMoreTokens()) {
			path.append(st.nextToken());
			path.append(File.separatorChar);
		}

		path.append(compilationUnitName + ".log");

		return path.toString();
	}

	/**
	 * Close the Merger Logger instance.
	 */
	public void close() {
		if (this.w != null) {
			try {
				this.w.close();
			} catch (IOException ioe) {
			}
		}
	}

	/**
	 * Return the description of a BodyDeclaration
	 */
	public static String getDescription(ASTNode astn) {
		if (astn == null) {
			return "empty";
		}

		if (astn instanceof FieldDeclaration) {
			// It's a field, return "field"
			return "field";
		} else if (astn instanceof MethodDeclaration) {
			// It's a method, return "method"
			return "method";
		} else if (astn instanceof TypeDeclaration) {
			// It's a type, return "type"
			return "type";
		} else if (astn instanceof EnumDeclaration) {
			// It's an enumeration, return "enumeration"
			return "enumeration";
		} else if (astn instanceof EnumConstantDeclaration) {
			// It's an enumeration, return "enumeration constant"
			return "enumeration constant";
		} else if (astn instanceof CompilationUnit) {
			// It's an enumeration, return "compilation unit"
			return "compilation unit";
		}

		return "empty";
	}
}
