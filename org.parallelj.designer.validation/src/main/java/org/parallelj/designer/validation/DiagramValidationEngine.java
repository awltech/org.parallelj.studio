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
package org.parallelj.designer.validation;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain.Factory;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.model.IConstraintStatus;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyDelegatingOperation;
import org.parallelj.designer.part.ParallelJDiagramEditorUtil;
import org.parallelj.designer.validation.providers.MarkerNavigationProvider;
import org.parallelj.designer.validation.providers.ValidationProvider;

/**
 * The DiagramValidationEngine class runs the validation on the Diagram Editor.
 */
public class DiagramValidationEngine {

	/**
	 * IWorkbenchPage an arrangement of views and editors intended to be
	 * presented together to the user in a single workbench window.
	 */
	private IWorkbenchPage page;

	/**
	 * Constructor.
	 * 
	 * @param page
	 */
	public DiagramValidationEngine(IWorkbenchPage page) {
		this.page = page;
	}

	/**
	 * Run the Engine to validate the diagram in Diagram Editor part.
	 */
	public void run() {
		IWorkbenchPart workbenchPart = this.page.getActivePart();
		if (workbenchPart instanceof IDiagramWorkbenchPart) {
			final IDiagramWorkbenchPart part = (IDiagramWorkbenchPart) workbenchPart;
			try {
				new WorkspaceModifyDelegatingOperation(
						new IRunnableWithProgress() {

							public void run(IProgressMonitor monitor)
									throws InterruptedException,
									InvocationTargetException {
								DiagramValidationEngine.runValidation(
										part.getDiagramEditPart(),
										part.getDiagram());
							}
						}).run(new NullProgressMonitor());
			} catch (Exception e) {
				DiagramValidationPlugin.getDefault().logError(
						"Validation action failed", e);
			}
		}
	}

	/**
	 * Runs validation on the diagram View.
	 * 
	 * @param view
	 */
	public static void runValidation(View view) {
		try {
			if (ParallelJDiagramEditorUtil.openDiagram(view.eResource())) {
				IEditorPart editorPart = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage()
						.getActiveEditor();
				if (editorPart instanceof IDiagramWorkbenchPart)
					DiagramValidationEngine.runValidation(
							((IDiagramWorkbenchPart) editorPart)
									.getDiagramEditPart(), view);
				else {
					DiagramValidationEngine.runNonUIValidation(view);
				}
			}
		} catch (Exception e) {
			DiagramValidationPlugin.getDefault().logError(
					"Validation action failed", e);
		}
	}

	/**
	 * Runs validation on Non UI View.
	 * 
	 * @param view
	 */
	public static void runNonUIValidation(View view) {
		if (view != null) {
			if(OffscreenEditPartFactory.getInstance() != null)
			{
				DiagramEditPart diagramEditPart = OffscreenEditPartFactory
						.getInstance().createDiagramEditPart(view.getDiagram(),
								new Shell());
				DiagramValidationEngine.runValidation(diagramEditPart, view);
			}
		}
	}

	/**
	 * Overloaded run validation method.
	 * 
	 * @param diagramEditPart
	 * @param view
	 */
	public static void runValidation(DiagramEditPart diagramEditPart, View view) {

		final DiagramEditPart fpart = diagramEditPart;
		final View fview = view;
		TransactionalEditingDomain txDomain = TransactionUtil
				.getEditingDomain(view);
		ValidationProvider.runWithConstraints(txDomain, new Runnable() {

			public void run() {
				DiagramValidationEngine.validate(fpart, fview);
			}
		});
	}

	/**
	 * Overloaded run validation method.
	 * 
	 * @param iFile
	 */
	public static void runValidation(IFile iFile) {
		if (!iFile.exists()) {
			return;
		}
		ResourceSet resourceSet = new ResourceSetImpl();
		try {
			URI uri = URI.createPlatformResourceURI(iFile.getFullPath()
					.toString(), true);
			Resource resource = resourceSet.createResource(uri);
			if (resource != null) {
				if (!resource.isLoaded()) {
					try {
						resource.load(Collections.EMPTY_MAP);
					} catch (Exception e) {
						DiagramValidationPlugin.getDefault().logError(
								"Validation action failed", e);
					}
				}
				if (!resource.isLoaded()) {
					return;
				}
				Diagram diagram = null;
				Iterator<EObject> iterator = resource.getContents().iterator();
				while (iterator.hasNext() && diagram == null) {
					EObject eo = iterator.next();
					if (eo instanceof Diagram) {
						diagram = (Diagram) eo;
					}
				}
				if (diagram != null) {
					final Diagram fDiagram = diagram;
					Display.getDefault().syncExec(new Runnable() {

						public void run() {
							DiagramValidationEngine.runValidation(fDiagram);
						}
					});
				}

			}
		} catch (Throwable t) {
			DiagramValidationPlugin
					.getDefault()
					.logError(
							"An exception has been thrown while validating Control Flow diagram",
							t);
		} finally {
			for (Resource resource : resourceSet.getResources()) {
				resource.unload();
			}
			resourceSet.getResources().clear();
		}
	}

	/**
	 * Runs validation from Diagram object
	 * 
	 * @param diagram
	 *            Diagram
	 */
	public static void runValidation(Diagram diagram) {
		if (diagram != null) {
			EditingDomain ted = TransactionUtil.getEditingDomain(diagram);
			if (ted == null) {
				if (diagram.eResource() != null) {
					ted = Factory.INSTANCE.createEditingDomain(diagram
							.eResource().getResourceSet());
				}
			}
		}
		final Diagram fDiagram = diagram;
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				DiagramValidationEngine.runNonUIValidation(fDiagram);
			}
		});

	}

	/**
	 * This validates the target if provided.
	 * 
	 * @param diagramEditPart
	 * @param view
	 */
	private static void validate(DiagramEditPart diagramEditPart, View view) {
		IFile target = view.eResource() != null ? WorkspaceSynchronizer
				.getFile(view.eResource()) : null;
		if (target != null) {
			MarkerNavigationProvider.deleteMarkers(target);
		}
		IBatchValidator validator = (IBatchValidator) ModelValidationService
				.getInstance().newValidator(EvaluationMode.BATCH);
		validator.setIncludeLiveConstraints(true);
		if (view.isSetElement() && view.getElement() != null) {
			IStatus status = validator.validate(view.getElement());
			DiagramValidationEngine.createMarkers(target, status,
					diagramEditPart);
		}
	}

	/**
	 * This method creates the error markers on the problem view.
	 * 
	 * @param target
	 * @param validationStatus
	 * @param diagramEditPart
	 */
	private static void createMarkers(IFile target, IStatus validationStatus,
			DiagramEditPart diagramEditPart) {
		if (validationStatus.isOK()) {
			return;
		}
		final IStatus rootStatus = validationStatus;
		List allStatuses = new ArrayList();
		ParallelJDiagramEditorUtil.LazyElement2ViewMap element2ViewMap = new ParallelJDiagramEditorUtil.LazyElement2ViewMap(
				diagramEditPart.getDiagramView(),
				DiagramValidationEngine.collectTargetElements(rootStatus,
						new HashSet(), allStatuses));
		for (Iterator it = allStatuses.iterator(); it.hasNext();) {
			IConstraintStatus nextStatus = (IConstraintStatus) it.next();
			View view = ParallelJDiagramEditorUtil.findView(diagramEditPart,
					nextStatus.getTarget(), element2ViewMap);
			DiagramValidationEngine.addMarker(diagramEditPart.getViewer(),
					target, view.eResource().getURIFragment(view),
					EMFCoreUtil.getQualifiedName(nextStatus.getTarget(), true),
					nextStatus.getMessage(), nextStatus.getSeverity());
		}
	}

	/**
	 * This adds the marker in the problem view.
	 * 
	 * @param viewer
	 * @param target
	 * @param elementId
	 * @param location
	 * @param message
	 * @param statusSeverity
	 */
	private static void addMarker(EditPartViewer viewer, IFile target,
			String elementId, String location, String message,
			int statusSeverity) {
		if (target == null) {
			return;
		}
		MarkerNavigationProvider.addMarker(target, elementId, location,
				message, statusSeverity);
	}

	/**
	 * This method collects the Target Elements.
	 * 
	 * @param status
	 * @param targetElementCollector
	 * @param allConstraintStatuses
	 * @return
	 */
	private static Set collectTargetElements(IStatus status,
			Set targetElementCollector, List allConstraintStatuses) {
		if (status instanceof IConstraintStatus) {
			targetElementCollector
					.add(((IConstraintStatus) status).getTarget());
			allConstraintStatuses.add(status);
		}
		if (status.isMultiStatus()) {
			IStatus[] children = status.getChildren();
			for (IStatus element : children) {
				DiagramValidationEngine.collectTargetElements(element,
						targetElementCollector, allConstraintStatuses);
			}
		}
		return targetElementCollector;
	}
}
