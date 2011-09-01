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


import org.eclipse.core.runtime.Status;
import org.eclipse.xpand2.output.FileHandle;
import org.eclipse.xpand2.output.PostProcessor;
import org.parallelj.codegen.Activator;
import org.parallelj.common.jdt.helpers.GeneratedAnnotationHelper;

/**
 * This class is an MWE PostProcessor for Java Code Generation.
 * The role of this class is to parse the freshly generated code, and to add (if
 * missing) a hashCode value corresponding to the method contents, in the
 * Generated annotation upon the previous method.
 * 
 * @author mvanbesien/mhays
 */
public class GeneratedAnnotationGenerator implements PostProcessor {

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.openarchitectureware.xpand2.output.PostProcessor#afterClose(org.
	 * openarchitectureware.xpand2.output.FileHandle)
	 */
	public void afterClose(FileHandle impl) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openarchitectureware.xpand2.output.PostProcessor#beforeWriteAndClose
	 * (org.openarchitectureware.xpand2.output.FileHandle)
	 */
	public void beforeWriteAndClose(FileHandle impl) {
		try {

			// Returns if generated file in not Java Class...
			if (!impl.getAbsolutePath().endsWith(".java"))
				return;

			// Uses helper class to update Annotation
			String source = impl.getBuffer().toString();
			String updatedSource = GeneratedAnnotationHelper.updateHashcodeInSource(source);

			// Stores source in impl
			impl.setBuffer(updatedSource);
		} catch (Exception e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR,Activator.PLUGIN_ID,
					"Generated Annotation Generator encountered "
							+ "an Exception while writing file on disk. ", e));
		}
	}

}
