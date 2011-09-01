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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.FileEditorInput;
import org.parallelj.codegen.jobs.ProgramGenerationJob;
import org.parallelj.designer.extension.dialog.ValidationErrorDialog;
import org.parallelj.designer.extension.tools.EditorDirtyChecker;
import org.parallelj.designer.extension.tools.ParallelJDiagramValidator;
import org.parallelj.designer.part.ParallelJDiagramEditor;
import org.parallelj.model.Specification;

/**
 * Generate code from a keyboard shortcut command. Initially it retrieves the
 * currently selected Editor and checks if it is a Parallel J Editor. If the
 * editor has file which is unsaved or dirty, than Code Generation is not
 * executed. Even if the current editor's diagram has errors than Code
 * Generation is skipped.
 * 
 * The Key Sequence for binding has 3 key strokes as "CTRL + SHIFT + G".
 * 
 */
public class GenerateCodeFromShortCutKey extends AbstractHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands
	 * .ExecutionEvent)
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {

		boolean gotErrors = false;
		Collection<Specification> specifications = new ArrayList<Specification>();

		// Fetch the currently selected Workbench Part
		IWorkbenchPart part = HandlerUtil.getActivePartChecked(event);
		if (part == null || !(part instanceof ParallelJDiagramEditor)) {
			return null;
		}

		ParallelJDiagramEditor editor = (ParallelJDiagramEditor) part;
		boolean shouldGenerateCode = EditorDirtyChecker.isFileUnSaved(editor);

		if (!shouldGenerateCode) {
			return null;
		}

		// Fetch Editor Input from Editor
		IEditorInput editorInput = ((DiagramDocumentEditor) editor)
				.getEditorInput();
		if (!(editorInput instanceof FileEditorInput)) {
			return null;
		}
		FileEditorInput fileEditorInput = (FileEditorInput) editorInput;
		IFile file = fileEditorInput.getFile();

		// Loads the File into EMF Resource, and fetch root specification
		ResourceSet resourceSet = new ResourceSetImpl();
		URI resourceURI = URI.createPlatformResourceURI(file.getFullPath()
				.toString(), true);
		Resource resource = resourceSet.getResource(resourceURI, true);
		if (resource == null) {
			return null;
		}

		Specification specification = null;
		for (Iterator<EObject> iterator = resource.getContents().iterator(); specification == null
				&& iterator.hasNext();) {
			EObject eObject = iterator.next();
			if (eObject instanceof Specification) {
				specification = (Specification) eObject;
				gotErrors = ParallelJDiagramValidator.validate(specification);
				if (gotErrors) {
					ValidationErrorDialog validationErrorDialog = new ValidationErrorDialog();
					validationErrorDialog.open();
					break;
				}
			}
			specifications.add(specification);
		}

		if (specification == null) {
			return null;
		}

		// Instantiate and launch //J Generation Job
		if (!gotErrors) {
			ProgramGenerationJob programGenerationJob = new ProgramGenerationJob(
					specifications);
			programGenerationJob.schedule();
		}

		return null;
	}

}
