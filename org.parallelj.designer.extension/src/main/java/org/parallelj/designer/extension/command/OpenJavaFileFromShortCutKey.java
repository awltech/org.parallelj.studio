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
package org.parallelj.designer.extension.command;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.TypeNameRequestor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.FileEditorInput;
import org.parallelj.designer.extension.edit.parts.ProgramExtendedEditPart;
import org.parallelj.designer.extension.tools.ResourceOpener;
import org.parallelj.designer.part.ParallelJDiagramEditor;
import org.parallelj.model.Program;

/**
 * 
 * Opens the java file from a keyboard shortcut command "F3". Initially it finds
 * the currently selected Editor and checks if it is a Parallel J Editor. If the
 * java project has file which is already created than it opens it, else the
 * information dialog is open for the file not yet created. If the editor has
 * file which is unsaved or dirty, than Java Element is not opened.
 * 
 * The Key Sequence for binding has 1 key strokes as "F3".
 * 
 */
public class OpenJavaFileFromShortCutKey extends AbstractHandler {

	protected Shell shellz;
	private IFile destinationFile;
	private IWorkbenchWindow workbenchWindow;
	private static final String PATH_SEPARATOR = "/";
	private static final String FULLY_QUALIFIED_PATTERN = ".";
	private static final String CLASS_FILE_EXTENSION = ".java";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands
	 * .ExecutionEvent)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands
	 * .ExecutionEvent)
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {

		// Fetch the currently selected Workbench Part
		IWorkbenchPart part = HandlerUtil.getActivePartChecked(event);
		if (part == null || !(part instanceof ParallelJDiagramEditor)) {
			return null;
		}
		shellz = part.getSite().getShell();

		// Fetch the currently selected Workbench Window
		this.workbenchWindow = HandlerUtil
				.getActiveWorkbenchWindowChecked(event);

		// Fetch the currently selected Workbench Selection Part
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		Object selected = ((IStructuredSelection) selection).getFirstElement();
		destinationFile = null;

		// Fetch Editor Input from Editor
		ParallelJDiagramEditor editor = (ParallelJDiagramEditor) part;

		IEditorInput editorInput = ((DiagramDocumentEditor) editor)
				.getEditorInput();
		if (!(editorInput instanceof FileEditorInput)) {
			return null;
		}

		FileEditorInput fileEditorInput = (FileEditorInput) editorInput;
		final IFile file = fileEditorInput.getFile();
		final IJavaProject javaProject = JavaCore.create(file.getProject());

		if (selected instanceof ProgramExtendedEditPart) {
			final String selectedProgram = ((Program) (((ProgramExtendedEditPart) selected)
					.getPrimaryView().getElement())).getName();
			IJavaSearchScope javaSearchScope = SearchEngine
					.createJavaSearchScope(new IJavaElement[] { javaProject });
			SearchEngine searchEngine = new SearchEngine();

			TypeNameRequestor typeNameRequestor = new TypeNameRequestor() {
				@Override
				public void acceptType(int modifiers, char[] packageName,
						char[] simpleTypeName, char[][] enclosingTypeNames,
						String path) {
					if (path.contains(javaProject.getPath().toString())) {
						String typePatternAsString = selectedProgram;
						String packagePatternAsString = "";
						if (typePatternAsString
								.contains(FULLY_QUALIFIED_PATTERN)) {
							packagePatternAsString = typePatternAsString
									.substring(0, typePatternAsString
											.lastIndexOf("."));
							typePatternAsString = typePatternAsString
									.substring(typePatternAsString
											.lastIndexOf(FULLY_QUALIFIED_PATTERN) + 1);
						}
						String testPath = path;
						if (testPath.contains(PATH_SEPARATOR)) {
							testPath = testPath.substring(testPath
									.lastIndexOf(PATH_SEPARATOR) + 1);
						}
						if (packagePatternAsString
								.contains(FULLY_QUALIFIED_PATTERN)) {
							if (path.contains(packagePatternAsString.replace(
									FULLY_QUALIFIED_PATTERN, PATH_SEPARATOR))) {
								if (testPath
										.equalsIgnoreCase(typePatternAsString
												+ CLASS_FILE_EXTENSION)) {
									String pathChunks[] = path.split(
											PATH_SEPARATOR, 3);
									IFile currentfile = file.getProject()
											.getFile(pathChunks[2]);
									destinationFile = currentfile;
								}
							}
						} else if (testPath
								.equalsIgnoreCase(typePatternAsString
										+ CLASS_FILE_EXTENSION)) {
							String pathChunks[] = path.split(PATH_SEPARATOR, 3);
							IFile currentfile = file.getProject().getFile(
									pathChunks[2]);
							destinationFile = currentfile;
						}
					}
				}
			};

			ResourceOpener.searchAllJavaTypes(javaSearchScope, searchEngine,
					typeNameRequestor);

			if (destinationFile != null) {
				ResourceOpener.openResource(destinationFile,
						this.workbenchWindow, this.shellz);
			} else {
				MessageDialog
						.openInformation(new Shell(),
								"File Not Created yet !!!",
								"The corresponding Java class has not been generated yet...");
			}
		}
		return null;

	}

}
