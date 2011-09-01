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
package org.parallelj.designer.extension.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.TypeNameRequestor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.part.FileEditorInput;
import org.parallelj.designer.edit.parts.ProgramEditPart;
import org.parallelj.designer.extension.tools.ResourceOpener;
import org.parallelj.designer.part.ParallelJDiagramEditor;
import org.parallelj.model.Program;

/**
 * Opens the Created Java element file from the contextual menu.
 * 
 */
public class OpenJavaFileAction implements IObjectActionDelegate {

	private ProgramEditPart programEditPart;
	protected IStructuredSelection selection;
	private static final String PATH_SEPARATOR = "/";
	private static final String FULLY_QUALIFIED_PATTERN = ".";
	private static final String CLASS_FILE_EXTENSION = ".java";
	private IFile destinationFile;
	private IFile file;
	private IJavaProject javaProject;
	private IWorkbenchWindow workbenchWindow;
	protected Shell shellz;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.
	 * action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		if (targetPart != null
				|| (targetPart instanceof ParallelJDiagramEditor)) {
			shellz = targetPart.getSite().getShell();
			workbenchWindow = targetPart.getSite().getWorkbenchWindow();
			// Fetch Editor Input from Editor
			ParallelJDiagramEditor editor = (ParallelJDiagramEditor) targetPart;
			IEditorInput editorInput = ((DiagramDocumentEditor) editor)
					.getEditorInput();
			if (editorInput instanceof FileEditorInput) {
				FileEditorInput fileEditorInput = (FileEditorInput) editorInput;
				this.file = fileEditorInput.getFile();
				this.javaProject = JavaCore.create(file.getProject());
			}

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {

		destinationFile = null;
		final String selectedProgram = ((Program) ((programEditPart)
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
					if (typePatternAsString.contains(FULLY_QUALIFIED_PATTERN)) {
						packagePatternAsString = typePatternAsString.substring(
								0, typePatternAsString.lastIndexOf("."));
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
							if (testPath.equalsIgnoreCase(typePatternAsString
									+ CLASS_FILE_EXTENSION)) {
								String pathChunks[] = path.split(
										PATH_SEPARATOR, 3);
								IFile currentfile = file.getProject().getFile(
										pathChunks[2]);
								destinationFile = currentfile;
							}
						}
					} else if (testPath.equalsIgnoreCase(typePatternAsString
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
			ResourceOpener.openResource(destinationFile, this.workbenchWindow,
					this.shellz);
		} else {
			MessageDialog
					.openInformation(new Shell(), "File Not Created yet !!!",
							"The corresponding Java class has not been generated yet...");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action
	 * .IAction, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			Object o = ((IStructuredSelection) selection).getFirstElement();
			if (o instanceof ProgramEditPart) {
				this.programEditPart = (ProgramEditPart) o;
			}
		}
		action.setEnabled(this.programEditPart != null);
	}

}
