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
package org.parallelj.common.jdt.helpers;

import java.util.Map;

import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.TextEdit;
import org.parallelj.common.jdt.Activator;

/**
 * Tools class used to format generated code.
 * 
 * @author Atos Worldline
 */
public class CodeFormatter {

	/**
	 * Format a String . Use the default code formatter
	 * 
	 * @param sourceToFormat
	 *            String to format
	 * 
	 */
	public static String format(String inputSource, IJavaProject project) {
		
		Map options = project != null ? project.getOptions(true)
				: JavaCore.getOptions();
		options.put("org.eclipse.jdt.core.formatter.join_lines_in_comments", "false");
		String sourceToFormat = preformat(inputSource);
		
		org.eclipse.jdt.core.formatter.CodeFormatter formatter = ToolFactory.createCodeFormatter(options, ToolFactory.M_FORMAT_EXISTING);
		IDocument document = new Document(sourceToFormat);

		TextEdit textEdit = formatter.format(org.eclipse.jdt.core.formatter.CodeFormatter.K_COMPILATION_UNIT
				| org.eclipse.jdt.core.formatter.CodeFormatter.F_INCLUDE_COMMENTS , sourceToFormat, 0,
				sourceToFormat.length(), 0, null);

		if (textEdit != null) {
			try {
				textEdit.apply(document);
				String destination = document.get();
				return destination;
			} catch (Exception e) {
				Activator.getDefault().getLog().log(new Status(Status.ERROR,Activator.PLUGIN_ID,
						"The compilation unit could not be"
								+ " read because of thrown Exception.", e));
				// In this case, return initial content
				return sourceToFormat;
			}
		} else {
			// In this case, return initial content
			return sourceToFormat;
		}
	}

	private static String preformat(String inputSource) {
		String preformatedInputSource = inputSource.replace("\r","");
		preformatedInputSource = preformatedInputSource.replace("\n\n", "\n");
		return preformatedInputSource;
	}
}
