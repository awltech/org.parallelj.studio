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
package org.parallelj.designer.extension.edit.parts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.draw2d.ui.figures.RoundedRectangleBorder;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.parallelj.designer.edit.parts.BusinessProcedureEditPart;
import org.parallelj.designer.extension.adapters.BusinessProcedureAdapter;
import org.parallelj.designer.extension.adapters.LinkPredicateRefreshmentOnLinkAddRemAdapter;
import org.parallelj.designer.extension.adapters.LinkPredicateRefreshmentOnSplitAdapter;
import org.parallelj.designer.extension.part.contrib.BusinessProcedureContribution;
import org.parallelj.designer.extension.tools.BoundsRefreshment;
import org.parallelj.designer.extension.tools.Drawer;
import org.parallelj.designer.extension.tools.JoinSplitUpdater;
import org.parallelj.model.BusinessProcedure;
import org.parallelj.model.ParallelJPackage;

public class BusinessProcedureExtendedEditPart extends
		BusinessProcedureEditPart {

	/**
	 * Default main color of this object
	 */
	private/* final */RGB preferredColor = new RGB(116, 155, 194);

	/**
	 * Actual colors of this object
	 */
	private RGB startColor;

	private RGB endColor;

	private RGB lightColor;

	private BusinessProcedureContribution businessProcedureContribution;

	/**
	 * flag for highlighting procedure
	 */
	private boolean showSelected;

	public BusinessProcedureExtendedEditPart(View view) {
		super(view);
		// for color provided by user
		this.populateBusinessProcedureContribution();
		if (businessProcedureContribution != null) {
			preferredColor = businessProcedureContribution.getRGBColor();
		}
		addAdapters(view);
		updateColor(preferredColor, false);
	}

	/**
	 * Adds adapters on view associated with Edit Part.
	 * 
	 * @param view
	 */
	private void addAdapters(View view) {
		EObject element = view.getElement();
		if (element != null) {
			element.eAdapters().add(new BusinessProcedureAdapter(this));
			element.eAdapters().add(
					new LinkPredicateRefreshmentOnSplitAdapter(this));
			element.eAdapters().add(
					new LinkPredicateRefreshmentOnLinkAddRemAdapter(this));
		}
	}

	/**
	 * Fixing the height of the Procedure node and updating diagram with last
	 * saved color when diagram closed and opened.
	 */
	@Override
	protected void refreshBounds() {
		BoundsRefreshment.refreshBounds(this, null, 43);
		this.updateColor(Drawer.getSavedRGB(this), false);
	}

	/**
	 * Calls on activate of diagram, based upon initial status on node, show
	 * SPLIT and JOIN icon.
	 */
	@Override
	public void activate() {
		super.activate();
		if (businessProcedureContribution != null) {
			this.setValues(getEditingDomain(), getBusinessProcedure(),
					ParallelJPackage.eINSTANCE.getNamedElement_Name(),
					businessProcedureContribution.getName());
			this.setValues(getEditingDomain(), getBusinessProcedure(),
					ParallelJPackage.eINSTANCE.getProcedure_Executable(),
					businessProcedureContribution.getExecutable());
			this.setValues(getEditingDomain(), getBusinessProcedure(),
					ParallelJPackage.eINSTANCE.getNamedElement_Description(),
					businessProcedureContribution.getDescription());
		}
		updateSplitJoin();
	}

	/**
	 * Creates rectangle for Procedure node, with color gradient starting from
	 * top-left to right-bottom, also having light and shadow effect. It will
	 * also show Procedure highlighted, if it's linked to any Handler and that
	 * Handler object is selected.
	 */
	@Override
	protected IFigure createNodeShape() {
		IFigure figure = new BusinessProcedureFigure() {
			public void paintFigure(Graphics graphics) {
				Drawer.rectangleGradient(graphics, getBounds(), endColor,
						startColor, new RGB(0, 0, 0), lightColor, showSelected);
			}
		};
		return primaryShape = figure;
	}

	/**
	 * Updates involved diagram for SPLIT and JOIN icon, based on value of the
	 * same. SPLIT and JOIN icon will appear, based on number of incoming and
	 * outgoing links.
	 */
	public void updateSplitJoin() {
		JoinSplitUpdater.updateSplitJoin(getBusinessProcedure(), this
				.getPrimaryShape().getFigureBusinessProcedureJoinFigure(), this
				.getPrimaryShape().getFigureBusinessProcedureSplitFigure());
	}

	/**
	 * Updates JOIN icon based on passed mode. This will triggered when user
	 * change JOIN value from property view.
	 * 
	 * @param mode
	 *            it can be AND, XOR or OR
	 */
	public void setJoinIcon(String mode) {
		JoinSplitUpdater.setJoinIcon(getBusinessProcedure(), mode, this
				.getPrimaryShape().getFigureBusinessProcedureJoinFigure());
	}

	/**
	 * Updates SPLIT icon based on passed mode. This will triggered when user
	 * change SPLIT value from property view.
	 * 
	 * @param mode
	 *            it can be AND, XOR or OR
	 */
	public void setSplitIcon(String mode) {
		JoinSplitUpdater.setSplitIcon(getBusinessProcedure(), mode, this
				.getPrimaryShape().getFigureBusinessProcedureSplitFigure());
	}

	private BusinessProcedure getBusinessProcedure() {
		return (BusinessProcedure) ((View) this.getModel()).getElement();
	}

	/**
	 * default main color, this value will be taken on reset of color.
	 */
	@Override
	public Object getPreferredValue(EStructuralFeature feature) {
		if (feature == NotationPackage.eINSTANCE.getFillStyle_FillColor()) {
			return FigureUtilities.RGBToInteger(preferredColor);
		} else {
			return super.getPreferredValue(feature);
		}
	}

	/**
	 * Handle the event for fill color change.
	 */
	@Override
	protected void handleNotificationEvent(Notification notification) {
		super.handleNotificationEvent(notification);
		Object feature = notification.getFeature();
		if (NotationPackage.eINSTANCE.getFillStyle_FillColor().equals(feature)) {
			int newIntColor = ((Integer) notification.getNewValue()).intValue();
			RGB newRGBColor = FigureUtilities.integerToRGB(newIntColor);
			this.updateColor(newRGBColor, true);
		} else if (notification.getEventType() == Notification.ADD) {
			Drawer.drawWithDefault(notification, this);
		}
	}

	/**
	 * Update the main color and respective gradient for node.
	 * 
	 * @param rgb
	 *            new color
	 */
	public void updateColor(RGB rgb, boolean isPaint) {
		startColor = rgb;
		endColor = Drawer.lighten(40, startColor);
		lightColor = Drawer.lighten(60, startColor);
		if (isPaint) {
			this.getFigure().repaint();
		}
	}

	/**
	 * It will also show Procedure highlighted, if it's linked to any Handler
	 * and that Handler object is selected.
	 */
	public void showSelected() {
		if (this.getFigure().getBorder() == null) {
			showSelected = true;
			BoundsRefreshment.refreshBounds(this, this.getSize().width + 2, 47);
			RoundedRectangleBorder border = new RoundedRectangleBorder(10, 10);
			border.setWidth(2);
			border.setColor(ColorConstants.orange);
			this.getFigure().setBorder(border);
			this.getFigure().repaint();
		}
	}

	/**
	 * It will clear highlight effect.
	 */
	public void clearSelection() {
		if (this.getFigure().getBorder() != null) {
			showSelected = false;
			BoundsRefreshment.refreshBounds(this, this.getSize().width - 2, 43);
			this.getFigure().setBorder(null);
			this.getFigure().repaint();
		}
	}

	private void populateBusinessProcedureContribution() {

		IEditorPart editor = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor();

		if (editor != null) {

			DiagramEditor diagramEditor = (DiagramEditor) editor;

			GraphicalViewer graphicalViewer = (GraphicalViewer) diagramEditor
					.getAdapter(GraphicalViewer.class);

			ToolEntry activeTool = graphicalViewer.getEditDomain()
					.getPaletteViewer().getActiveTool();

			if (businessProcedureContribution == null
					&& activeTool
							.getToolProperty("businessProcedureContribution") != null
					&& activeTool
							.getToolProperty("businessProcedureContribution") instanceof BusinessProcedureContribution) {
				businessProcedureContribution = (BusinessProcedureContribution) activeTool
						.getToolProperty("businessProcedureContribution");
			}
		}
	}

	public BusinessProcedureContribution getBusinessProcedureContribution() {
		return businessProcedureContribution;
	}

	public void setBusinessProcedureContribution(
			BusinessProcedureContribution businessProcedureContribution) {
		this.businessProcedureContribution = businessProcedureContribution;
	}

	private void setValues(TransactionalEditingDomain ted, EObject eo,
			EStructuralFeature esf, Object value) {
		//try {
			CompoundCommand cc = new CompoundCommand();
			SetRequest request = new SetRequest(ted, eo, esf, value);

			//SetValueCommand command = new SetValueCommand(request);
			Command setCommand = new ICommandProxy(new SetValueCommand(request));
			cc.add(setCommand);
			
			if(cc.canExecute()){
				cc.execute();
			}
			
			/*IStatus iStatus = command.execute(new NullProgressMonitor(), null);
			if (!iStatus.isOK()) {
				Activator
						.logWarn("An exception has been thrown while executing Command");
			}*/
		//} catch (ExecutionException e) {
			/*Activator
					.logError("An exception has been thrown while executing Command");*/
		//}
	}

}
