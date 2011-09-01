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
package org.parallelj.designer.validation.contribution;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditorInput;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.parallelj.designer.extension.part.contrib.IParallelJDiagramEditorContribution;
import org.parallelj.designer.part.ParallelJDiagramEditorPlugin;
import org.parallelj.designer.validation.DiagramValidationEngine;
import org.parallelj.designer.validation.adapters.LiveValidationContentAdapter;

/**
 * DiagramEditorContribution to add Contribution (through Extension Points) to
 * the ParallelJ Designer Diagram Editor
 */
public class DiagramEditorContribution implements
		IParallelJDiagramEditorContribution {

	/**
	 * Constructor.
	 */
	public DiagramEditorContribution() {
		super();
	}

	@Override
	public void contribute(IEditorSite site, IEditorInput input) {

		// Checks that we have a Diagram Editor Input at disposal
		if (input instanceof DiagramEditorInput) {
			Diagram diagram = ((DiagramEditorInput)input).getDiagram();

			// Runs the validation whenever the file is Open.
			DiagramValidationEngine.runValidation(diagram);

			// Just adds an adapter able to perform Live Validation
			EObject element = diagram.getElement();
			try {
				element.eAdapters().add(new LiveValidationContentAdapter(
						site.getShell(), diagram));
			} catch (Throwable t) {
				ParallelJDiagramEditorPlugin.getInstance().logError(
						"Validation Adapter failed", t.getCause());
			}
		}
	}

}
