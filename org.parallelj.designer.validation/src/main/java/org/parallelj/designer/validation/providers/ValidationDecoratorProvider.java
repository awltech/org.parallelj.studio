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
package org.parallelj.designer.validation.providers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Label;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.resources.FileChangeManager;
import org.eclipse.gmf.runtime.common.ui.resources.IFileObserver;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.AbstractDecorator;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.CreateDecoratorsOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecorator;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.parallelj.designer.edit.parts.SpecificationEditPart;
import org.parallelj.designer.part.ParallelJDiagramEditor;
import org.parallelj.designer.part.ParallelJVisualIDRegistry;
import org.parallelj.designer.validation.DiagramValidationPlugin;

/**
 * A ValidationDecoratorProvider is responsible for installing its decorators on
 * the decorator targets that it wishes to decorate.
 * 
 */
public class ValidationDecoratorProvider extends AbstractProvider implements
		IDecoratorProvider {

	private static final String KEY = "validationStatus";

	private static final String MARKER_TYPE = DiagramValidationPlugin.PLUGIN_ID
			+ ".diagnostic";

	private static MarkerObserver fileObserver;

	private static Map<String, List<IDecorator>> allDecorators = new HashMap<String, List<IDecorator>>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorProvider
	 * #createDecorators
	 * (org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget)
	 */
	public void createDecorators(IDecoratorTarget decoratorTarget) {

		EditPart editPart = (EditPart) decoratorTarget
				.getAdapter(EditPart.class);
		if (editPart instanceof GraphicalEditPart
				|| editPart instanceof AbstractConnectionEditPart) {
			Object model = editPart.getModel();
			if (model instanceof View) {
				View view = (View) model;
				if (!(view instanceof Edge) && !view.isSetElement()) {
					return;
				}
			}
			EditDomain ed = editPart.getViewer().getEditDomain();
			if (!(ed instanceof DiagramEditDomain)) {
				return;
			}
			if (((DiagramEditDomain) ed).getEditorPart() instanceof ParallelJDiagramEditor) {
				decoratorTarget.installDecorator(
						ValidationDecoratorProvider.KEY, new StatusDecorator(
								decoratorTarget));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gmf.runtime.common.core.service.IProvider#provides(org.eclipse
	 * .gmf.runtime.common.core.service.IOperation)
	 */
	public boolean provides(IOperation operation) {
		if (!(operation instanceof CreateDecoratorsOperation)) {
			return false;
		}
		IDecoratorTarget decoratorTarget = ((CreateDecoratorsOperation) operation)
				.getDecoratorTarget();
		View view = (View) decoratorTarget.getAdapter(View.class);
		return (view != null && SpecificationEditPart.MODEL_ID
				.equals(ParallelJVisualIDRegistry.getModelID(view)));
	}

	/**
	 * 
	 * decorates the view and refreshes it.
	 * 
	 * @param view
	 */
	public static void refreshDecorators(View view) {

		ValidationDecoratorProvider.refreshDecorators(ViewUtil.getIdStr(view),
				view.getDiagram());
	}

	/**
	 * decorates the view and refreshes it.
	 * 
	 * @param viewId
	 * @param diagram
	 */
	private static void refreshDecorators(String viewId, Diagram diagram) {
		final List<IDecorator> decorators = viewId != null ? (List<IDecorator>) ValidationDecoratorProvider.allDecorators
				.get(viewId) : null;
		if (decorators == null || decorators.isEmpty() || diagram == null) {
			return;
		}
		final Diagram fdiagram = diagram;
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

			public void run() {
				try {
					TransactionUtil.getEditingDomain(fdiagram).runExclusive(
							new Runnable() {

								public void run() {
									if (decorators != null) {
										for (IDecorator decorator : decorators) {
											if (decorator != null) {
												decorator.refresh();
											}
										}
									}

								}
							});
				} catch (Exception e) {
					DiagramValidationPlugin.getDefault().logError(
							"Decorator refresh failure", e);
				}
			}
		});
	}

	public static class StatusDecorator extends AbstractDecorator {

		private String viewId;

		public StatusDecorator(IDecoratorTarget decoratorTarget) {
			super(decoratorTarget);
			try {
				final View view = (View) this.getDecoratorTarget().getAdapter(
						View.class);
				TransactionUtil.getEditingDomain(view).runExclusive(
						new Runnable() {

							public void run() {
								StatusDecorator.this.viewId = view != null ? ViewUtil
										.getIdStr(view) : null;
							}
						});
			} catch (Exception e) {
				DiagramValidationPlugin.getDefault().logError(
						"ViewID access failure", e);
			}
		}

		public void refresh() {
			this.removeDecoration();
			View view = (View) this.getDecoratorTarget().getAdapter(View.class);
			if (view == null || view.eResource() == null) {
				return;
			}
			EditPart editPart = (EditPart) this.getDecoratorTarget()
					.getAdapter(EditPart.class);
			if (editPart == null || editPart.getViewer() == null) {
				return;
			}

			// query for all the validation markers of the current resource
			String elementId = ViewUtil.getIdStr(view);
			if (elementId == null) {
				return;
			}
			int severity = IMarker.SEVERITY_INFO;
			IMarker foundMarker = null;
			IResource resource = WorkspaceSynchronizer
					.getFile(view.eResource());
			if (resource == null || !resource.exists()) {
				return;
			}
			IMarker[] markers = null;
			try {
				markers = resource.findMarkers(
						ValidationDecoratorProvider.MARKER_TYPE, true,
						IResource.DEPTH_INFINITE);
			} catch (CoreException e) {
				DiagramValidationPlugin.getDefault().logError(
						"Validation markers refresh failure", e);
			}
			if (markers == null || markers.length == 0) {
				return;
			}
			Label toolTip = null;
			for (IMarker marker : markers) {
				String attribute = marker
						.getAttribute(
								org.eclipse.gmf.runtime.common.core.resources.IMarker.ELEMENT_ID,
								"");
				if (attribute.equals(elementId)) {
					int nextSeverity = marker.getAttribute(IMarker.SEVERITY,
							IMarker.SEVERITY_INFO);
					Image nextImage = this.getImage(nextSeverity);
					if (foundMarker == null) {
						foundMarker = marker;
						toolTip = new Label(marker.getAttribute(
								IMarker.MESSAGE, ""), nextImage);
					} else {
						if (toolTip.getChildren().isEmpty()) {
							Label comositeLabel = new Label();
							FlowLayout fl = new FlowLayout(false);
							fl.setMinorSpacing(0);
							comositeLabel.setLayoutManager(fl);
							comositeLabel.add(toolTip);
							toolTip = comositeLabel;
						}
						toolTip.add(new Label(marker.getAttribute(
								IMarker.MESSAGE, ""), nextImage));
					}
					severity = nextSeverity > severity ? nextSeverity
							: severity;
				}
			}
			if (foundMarker == null) {
				return;
			}

			// add decoration
			if (editPart instanceof org.eclipse.gef.GraphicalEditPart) {
				if (view instanceof Edge) {
					this.setDecoration(this.getDecoratorTarget()
							.addConnectionDecoration(this.getImage(severity),
									50, true));
				} else {
					int margin = -1;
					if (editPart instanceof org.eclipse.gef.GraphicalEditPart) {
						margin = MapModeUtil.getMapMode(
								((org.eclipse.gef.GraphicalEditPart) editPart)
										.getFigure()).DPtoLP(margin);
					}
					this.setDecoration(this.getDecoratorTarget()
							.addShapeDecoration(this.getImage(severity),
									IDecoratorTarget.Direction.NORTH_EAST,
									margin, true));
				}
				this.getDecoration().setToolTip(toolTip);
			}
		}

		private Image getImage(int severity) {
			String imageName = ISharedImages.IMG_OBJS_ERROR_TSK;
			switch (severity) {
			case IMarker.SEVERITY_ERROR:
				imageName = ISharedImages.IMG_OBJS_ERROR_TSK;
				break;
			case IMarker.SEVERITY_WARNING:
				imageName = ISharedImages.IMG_OBJS_WARN_TSK;
				break;
			default:
				imageName = ISharedImages.IMG_OBJS_INFO_TSK;
			}
			return PlatformUI.getWorkbench().getSharedImages()
					.getImage(imageName);
		}

		public void activate() {
			if (this.viewId == null) {
				return;
			}

			// add self to global decorators registry
			List<IDecorator> list = ValidationDecoratorProvider.allDecorators
					.get(this.viewId);
			if (list == null) {
				list = new ArrayList<IDecorator>(2);
				list.add(this);
				ValidationDecoratorProvider.allDecorators
						.put(this.viewId, list);
			} else if (!list.contains(this))
				list.add(this);

			// start listening to changes in resources
			View view = (View) this.getDecoratorTarget().getAdapter(View.class);
			if (view == null) {
				return;
			}
			Diagram diagramView = view.getDiagram();
			if (diagramView == null) {
				return;
			}
			if (ValidationDecoratorProvider.fileObserver == null) {
				FileChangeManager
						.getInstance()
						.addFileObserver(
								ValidationDecoratorProvider.fileObserver = new MarkerObserver(
										diagramView));
			}
		}

		@Override
		public void deactivate() {
			if (this.viewId == null) {
				return;
			}
			// remove self from global decorators registry
			List<IDecorator> list = ValidationDecoratorProvider.allDecorators
					.get(this.viewId);
			if (list != null) {
				list.remove(this);
				if (list.isEmpty()) {
					ValidationDecoratorProvider.allDecorators
							.remove(this.viewId);
				}
			}

			// stop listening to changes in resources if there are no more
			// decorators
			if (ValidationDecoratorProvider.fileObserver != null
					&& ValidationDecoratorProvider.allDecorators.isEmpty()) {
				FileChangeManager.getInstance().removeFileObserver(
						ValidationDecoratorProvider.fileObserver);
				ValidationDecoratorProvider.fileObserver = null;
			}
			super.deactivate();
		}
	}

	static class MarkerObserver implements IFileObserver {

		private Diagram diagram;

		private MarkerObserver(Diagram diagram) {
			this.diagram = diagram;
		}

		public void handleFileRenamed(IFile oldFile, IFile file) {
		}

		public void handleFileMoved(IFile oldFile, IFile file) {
		}

		public void handleFileDeleted(IFile file) {
		}

		public void handleFileChanged(IFile file) {
		}

		public void handleMarkerAdded(IMarker marker) {
			if (marker
					.getAttribute(
							org.eclipse.gmf.runtime.common.core.resources.IMarker.ELEMENT_ID,
							null) != null) {
				this.handleMarkerChanged(marker);
			}
		}

		public void handleMarkerDeleted(IMarker marker, Map attributes) {
			String viewId = (String) attributes
					.get(org.eclipse.gmf.runtime.common.core.resources.IMarker.ELEMENT_ID);
			ValidationDecoratorProvider.refreshDecorators(viewId, this.diagram);
		}

		public void handleMarkerChanged(IMarker marker) {
			if (!ValidationDecoratorProvider.MARKER_TYPE.equals(this
					.getType(marker))) {
				return;
			}
			String viewId = marker
					.getAttribute(
							org.eclipse.gmf.runtime.common.core.resources.IMarker.ELEMENT_ID,
							"");
			ValidationDecoratorProvider.refreshDecorators(viewId, this.diagram);
		}

		private String getType(IMarker marker) {
			try {
				return marker.getType();
			} catch (CoreException e) {
				DiagramValidationPlugin.getDefault().logError(
						"Validation marker refresh failure", e);
				return "";
			}
		}
	}
}
