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
package org.parallelj.codegen.postprocessors;


import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.xpand2.output.FileHandle;
import org.eclipse.xpand2.output.PostProcessor;
import org.parallelj.codegen.Activator;
import org.parallelj.common.jdt.helpers.CodeFormatter;
import org.parallelj.common.jdt.helpers.PackageFragmentHelper;
import org.parallelj.common.jdt.mergers.JavaCodeMerger;

public class MergerOrBeautifierCaller implements PostProcessor {

	private static Set<String> annot = new HashSet<String>();
	
	static{
		annot.add("AndJoin");
		annot.add("AndSplit");
		annot.add("Begin");
		annot.add("Capacity");
		annot.add("ExceptionHandlingPolicy");
		annot.add("ForEach");
		annot.add("Handler");
		annot.add("Link");
		annot.add("OrJoin");
		annot.add("OrSplit");
		annot.add("Procedure");
		annot.add("Process");
		annot.add("Program");
		annot.add("Programs");
		annot.add("While");
		annot.add("XorJoin");
		annot.add("XorSplit");
	}
	
	
	/**
	 * Return true if compilationUnit exists in the given package. This method
	 * tests if there yet exists such a compilation unit (and do not considers
	 * the temporary file created by mwe)
	 * 
	 * @param compilationUnitName
	 * @param packageName
	 * @return
	 */
	private boolean existsCompilationUnit(String compilationUnitName, IPackageFragment packageName) {
		boolean exists = true;
		// Create ICompilationUnit object associated with initial source
		ICompilationUnit initialIcp = packageName.getCompilationUnit(compilationUnitName + ".java");
		HashSet<String>hashSet = new HashSet<String>();
		
		
		// get initial source and throws exception if not exists
		try {
			initialIcp.getSource();
		} catch (JavaModelException err) {
			/*
			 * If an exception is thrown, it's because the source does not exist
			 */
			exists = false;
		}

		return exists;
	}

	public void beforeWriteAndClose(FileHandle impl) {
		// Only manage java file
		if (impl.getTargetFile().getName().endsWith(".java")) {
			String compilationUnitName = impl.getTargetFile().getName().replaceAll(".java", "");
			String newContent = impl.getBuffer().toString();

			try {
				Path filePath = new Path(impl.getTargetFile().getAbsolutePath());
				IPackageFragment packageName = PackageFragmentHelper.getPackageFragment(filePath);

				if (packageName != null) {
					if (existsCompilationUnit(compilationUnitName, packageName)) {
						/*
						 * There yet exists such a file on the workspace, we
						 * merge it with the new version.
						 */
						StringBuffer mergeResult = merge(compilationUnitName, newContent,
								packageName);

						impl.setBuffer(new StringBuffer(CodeFormatter.format(new String(
								mergeResult), packageName.getJavaProject())));
					} else
						impl.setBuffer(new StringBuffer(CodeFormatter.format(newContent,
								packageName.getJavaProject())));
				}

			} catch (Exception e) {
				Activator.getDefault().getLog().log(new Status(Status.ERROR,Activator.PLUGIN_ID,
						"An Exception has been thrown while merging generated source code", e));
			}
		}
	}

	public void afterClose(FileHandle impl) {
	}

	protected StringBuffer merge(String compilationUnitName, String newContent,
			IPackageFragment packageName) throws JavaModelException {
		return new StringBuffer(new JavaCodeMerger(annot).merge(compilationUnitName, newContent,
				packageName));
	}

}
