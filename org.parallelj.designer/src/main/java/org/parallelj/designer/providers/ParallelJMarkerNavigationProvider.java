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
package org.parallelj.designer.providers;

import java.util.Arrays;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.emf.ui.providers.marker.AbstractModelMarkerNavigationProvider;
import org.parallelj.designer.part.ParallelJDiagramEditorPlugin;
import org.parallelj.designer.part.ParallelJDiagramEditorUtil;

/**
 * @generated
 */
public class ParallelJMarkerNavigationProvider extends
		AbstractModelMarkerNavigationProvider {

	/**
	 * @generated
	 */
	public static final String MARKER_TYPE = ParallelJDiagramEditorPlugin.ID
			+ ".diagnostic"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	protected void doGotoMarker(IMarker marker) {
		String elementId = marker
				.getAttribute(
						org.eclipse.gmf.runtime.common.core.resources.IMarker.ELEMENT_ID,
						null);
		if (elementId == null || !(getEditor() instanceof DiagramEditor)) {
			return;
		}
		DiagramEditor editor = (DiagramEditor) getEditor();
		Map editPartRegistry = editor.getDiagramGraphicalViewer()
				.getEditPartRegistry();
		EObject targetView = editor.getDiagram().eResource()
				.getEObject(elementId);
		if (targetView == null) {
			return;
		}
		EditPart targetEditPart = (EditPart) editPartRegistry.get(targetView);
		if (targetEditPart != null) {
			ParallelJDiagramEditorUtil.selectElementsInDiagram(editor,
					Arrays.asList(new EditPart[] { targetEditPart }));
		}
	}

	/**
	 * @generated
	 */
	public static void deleteMarkers(IResource resource) {
		try {
			resource.deleteMarkers(MARKER_TYPE, true, IResource.DEPTH_ZERO);
		} catch (CoreException e) {
			ParallelJDiagramEditorPlugin.getInstance().logError(
					"Failed to delete validation markers", e); //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	public static IMarker addMarker(IFile file, String elementId,
			String location, String message, int statusSeverity) {
		IMarker marker = null;
		try {
			marker = file.createMarker(MARKER_TYPE);
			marker.setAttribute(IMarker.MESSAGE, message);
			marker.setAttribute(IMarker.LOCATION, location);
			marker.setAttribute(
					org.eclipse.gmf.runtime.common.ui.resources.IMarker.ELEMENT_ID,
					elementId);
			int markerSeverity = IMarker.SEVERITY_INFO;
			if (statusSeverity == IStatus.WARNING) {
				markerSeverity = IMarker.SEVERITY_WARNING;
			} else if (statusSeverity == IStatus.ERROR
					|| statusSeverity == IStatus.CANCEL) {
				markerSeverity = IMarker.SEVERITY_ERROR;
			}
			marker.setAttribute(IMarker.SEVERITY, markerSeverity);
		} catch (CoreException e) {
			ParallelJDiagramEditorPlugin.getInstance().logError(
					"Failed to create validation marker", e); //$NON-NLS-1$
		}
		return marker;
	}
}
