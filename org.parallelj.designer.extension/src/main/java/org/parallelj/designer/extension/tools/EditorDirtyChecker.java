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
package org.parallelj.designer.extension.tools;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.parallelj.designer.part.ParallelJDiagramEditor;

/**
 * This class is used to validate if the editor is dirty or unsaved contents in
 * the file.
 * 
 */
public class EditorDirtyChecker {

	private EditorDirtyChecker() {
	}

	/**
	 * Checks if the file is saved or not saved. Opens the confirmation dialog
	 * for the user to act upon.
	 * 
	 * @param editor
	 * @return booleanValue
	 */
	public static boolean isFileUnSaved(ParallelJDiagramEditor editor) {
		boolean shouldGenerateCode = true;

		// If editor has diagram as unsaved or isDirty than open Question dialog
		// and ask user to save it. If user refuses than code generation is
		// not executed.
		if (editor.isDirty()) {
			StringBuilder message = new StringBuilder();
			message = message
					.append("The Parallel J Diagram has to be saved before Generating the Code....");
			message = message
					.append("\n Do you want to save the unsaved ParallelJ Diagram ? ");
			shouldGenerateCode = MessageDialog.openQuestion(new Shell(),
					"Save ParallelJ Diagram ?", message.toString());
			if (shouldGenerateCode) {
				editor.doSave(new NullProgressMonitor());
			}
		}
		return shouldGenerateCode;

	}

}
