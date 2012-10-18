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
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.draw2d.ui.figures.RoundedRectangleBorder;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.parallelj.designer.edit.parts.PipelineEditPart;
import org.parallelj.designer.edit.parts.PipelinePipelineCompartmentEditPart;
import org.parallelj.designer.edit.parts.PipelineProcedureEditPart;
import org.parallelj.designer.extension.adapters.LinkPredicateRefreshmentOnLinkAddRemAdapter;
import org.parallelj.designer.extension.adapters.LinkPredicateRefreshmentOnSplitAdapter;
import org.parallelj.designer.extension.adapters.PipelineAdapter;
import org.parallelj.designer.extension.adapters.PipelineIterableRefreshmentAdapter;
import org.parallelj.designer.extension.tools.BoundsRefreshment;
import org.parallelj.designer.extension.tools.Drawer;
import org.parallelj.designer.extension.tools.JoinSplitUpdater;
import org.parallelj.model.Pipeline;

public class PipelineExtendedEditPart extends PipelineEditPart {

	/**
	 * Default main color of this object
	 */
	private final RGB preferredColor = new RGB(50, 180, 160);

	/**
	 * Actual colors of this object
	 */
	private RGB startColor;

	private RGB endColor;

	private RGB lightColor;

	/**
	 * flag for highlighting procedure
	 */
	private boolean showSelected;

	public PipelineExtendedEditPart(View view) {
		super(view);
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
			element.eAdapters().add(new PipelineAdapter(this));
			element.eAdapters().add(
					new LinkPredicateRefreshmentOnSplitAdapter(this));
			element.eAdapters().add(
					new LinkPredicateRefreshmentOnLinkAddRemAdapter(this));
			element.eAdapters().add(
					new PipelineIterableRefreshmentAdapter(this));
		}
	}

	/**
	 * Fixing the height and width of the Pipeline node and updating diagram with
	 * last saved color when diagram closed and opened.
	 */
	@Override
	protected void refreshBounds() {

		int innerHeight = ((Integer) this
				.getStructuralFeatureValue(NotationPackage.eINSTANCE
						.getSize_Height())).intValue();

		// when pipeline is not in fold mode
		if (innerHeight != 35) {
			// calculating height as per inner procedures
			Pipeline pipeline = (Pipeline) (((View) this.getModel()).getElement());
			if (pipeline.getProcedures().size() <= 1) {
				BoundsRefreshment.refreshBounds(this, null, 98);
			} else {
				BoundsRefreshment.refreshBounds(this, null, 98 + ((pipeline
						.getProcedures().size() - 1) * 45));
			}
			this.putInnerMargin();
		}
		// when pipeline is in fold mode
		else {
			super.refreshBounds();
			PipelineFigure primaryShape = this.getPrimaryShape();
			RectangleFigure rectangleFigure = (RectangleFigure) primaryShape
					.getChildren().get(1);
			rectangleFigure.setVisible(false);
			this.clearBottomMargin();
		}
		this.updateColor(Drawer.getSavedRGB(this), false);
	}

	/**
	 * To keep compartment background color as white.
	 */
	protected void refreshBackgroundColor() {
		super.refreshBackgroundColor();
		setBackgroundColor(new Color(null, 255, 255, 255));
	}

	/**
	 * Calls on activate of diagram, based upon initial status on node, show
	 * SPLIT and JOIN icon.
	 */
	@Override
	public void activate() {
		super.activate();
		updateSplitJoin();
	}

	/**
	 * Handle the resize event for width and event for fill color change.
	 */
	@Override
	protected void handleNotificationEvent(Notification notification) {
		Object feature = notification.getFeature();
		// if event is width resize
		if (NotationPackage.eINSTANCE.getSize_Width().equals(feature)) {
			// getting new width
			int parentWidth = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE
					.getSize_Width())).intValue();
			// traverse through children
			for (Object object : this.getChildren()) {
				if (object instanceof PipelinePipelineCompartmentEditPart) {
					PipelinePipelineCompartmentEditPart compartmentEditPart = (PipelinePipelineCompartmentEditPart) object;
					for (Object procedureObject : compartmentEditPart
							.getChildren()) {
						if (procedureObject instanceof PipelineProcedureEditPart) {
							// make width of sequence procedure to match the
							// width of compartment
							BoundsRefreshment.refreshBounds(
									(PipelineProcedureEditPart) procedureObject,
									(parentWidth - 18), 43);
						}
					}
				}
			}
			super.handleNotificationEvent(notification);
		}
		// if event is fill color change
		else if (NotationPackage.eINSTANCE.getFillStyle_FillColor().equals(
				feature)) {
			int newIntColor = ((Integer) notification.getNewValue()).intValue();
			RGB newRGBColor = FigureUtilities.integerToRGB(newIntColor);
			// update the color
			this.updateColor(newRGBColor, true);
		} else if (notification.getEventType() == Notification.ADD) {
			Drawer.drawWithDefault(notification, this);
			super.handleNotificationEvent(notification);
		} else {
			super.handleNotificationEvent(notification);
		}
	}

	/**
	 * Creates rectangle for Pipeline node, with color gradient starting from
	 * top-left to right-bottom, also having light and shadow effect.It will
	 * also show Pipeline highlighted, if it's linked to any Handler and that
	 * Handler object is selected.
	 */
	@Override
	protected IFigure createNodeShape() {
		IFigure figure = new PipelineFigure() {
			public void paintFigure(Graphics graphics) {
				Drawer.rectangleGradient(graphics, getBounds(), startColor,
						endColor, new RGB(0, 0, 0), lightColor, showSelected);
			}
		};
		// this to give inner margin
		this.putInnerMargin(figure);
		return primaryShape = figure;
	}
	
	/**
	 * Updates involved diagram for SPLIT and JOIN icon, based on value of the
	 * same. SPLIT and JOIN icon will appear, based on number of incoming and
	 * outgoing links.
	 */
	public void updateSplitJoin() {
		JoinSplitUpdater.updateSplitJoin(getPipeline(), this.getPrimaryShape()
				.getFigurePipelineJoinFigure(), this.getPrimaryShape()
				.getFigurePipelineSplitFigure());
	}

	/**
	 * Updates JOIN icon based on passed mode. This will triggered when user
	 * change JOIN value from property view.
	 * 
	 * @param mode
	 *            it can be AND, XOR or OR
	 */
	public void setJoinIcon(String mode) {
		JoinSplitUpdater.setJoinIcon(getPipeline(), mode, this
				.getPrimaryShape().getFigurePipelineJoinFigure());
	}

	/**
	 * Updates SPLIT icon based on passed mode. This will triggered when user
	 * change SPLIT value from property view.
	 * 
	 * @param mode
	 *            it can be AND, XOR or OR
	 */
	public void setSplitIcon(String mode) {
		JoinSplitUpdater.setSplitIcon(getPipeline(), mode, this
				.getPrimaryShape().getFigurePipelineSplitFigure());
	}

	private Pipeline getPipeline() {
		return (Pipeline) ((View) this.getModel()).getElement();
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
	 * Update the main color and respective gradient for node.
	 * 
	 * @param rgb
	 *            new color
	 */
	public void updateColor(RGB rgb, boolean isPaint) {
		startColor = rgb;
		endColor = Drawer.darken(15, startColor);
		lightColor = Drawer.lighten(60, startColor);
		if (isPaint) {
			this.getFigure().repaint();
		}
	}

	/**
	 * This will remove the bottom margin, called at the time when pipeline is in
	 * fold mode.
	 */
	public void clearBottomMargin() {
		this.getPrimaryShape().setBorder(
				new MarginBorder(getMapMode().DPtoLP(0),
						getMapMode().DPtoLP(5), getMapMode().DPtoLP(0),
						getMapMode().DPtoLP(6)));
	}

	/**
	 * Setting margin around compartment
	 */
	public void putInnerMargin() {
		// this to give inner margin
		this.getPrimaryShape().setBorder(
				new MarginBorder(getMapMode().DPtoLP(0),
						getMapMode().DPtoLP(5), getMapMode().DPtoLP(6),
						getMapMode().DPtoLP(6)));
	}

	/**
	 * Setting margin around compartment
	 * 
	 * @param pipelineFigure
	 */
	public void putInnerMargin(IFigure pipelineFigure) {
		// this to give inner margin
		pipelineFigure.setBorder(new MarginBorder(getMapMode().DPtoLP(0),
				getMapMode().DPtoLP(5), getMapMode().DPtoLP(6), getMapMode()
						.DPtoLP(6)));
	}

	/**
	 * It will also show Pipeline highlighted, if it's linked to any Handler and
	 * that Handler object is selected.
	 */
	public void showSelected() {
		if (this.getFigure().getBorder() == null) {
			showSelected = true;
			
			PipelineFigure primaryShape = this.getPrimaryShape();
			RectangleFigure rectangleFigure = (RectangleFigure) primaryShape
					.getChildren().get(1);
			// based on visibility status, hide or show the compartment
			// this check is introduce, because when pipeline is linked to
			// more than one handler, switching between handlers creating width
			// sizing issue which highlighting
			if (rectangleFigure.isVisible()) {

				if (this.getSize().height != 102 + ((this.getPipeline()
						.getProcedures().size() - 1) * 45)) {
					BoundsRefreshment
							.refreshBounds(this, this.getSize().width + 4,
									this.getSize().height + 4);
				} else {
					BoundsRefreshment.refreshBounds(this, this.getSize().width,
							this.getSize().height);
				}
			} else if (this.getSize().height != 47) {
				BoundsRefreshment.refreshBounds(this, this.getSize().width + 2,
						this.getSize().height + 4);
			} else {
				BoundsRefreshment.refreshBounds(this, this.getSize().width,
						this.getSize().height);
			}

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
			PipelineFigure primaryShape = this.getPrimaryShape();
			RectangleFigure rectangleFigure = (RectangleFigure) primaryShape
					.getChildren().get(1);
			// based on visibility status, hide or show the compartment
			if (rectangleFigure.isVisible()) {
				BoundsRefreshment
						.refreshBounds(
								this,
								this.getSize().width - 4,
								98 + ((this.getPipeline().getProcedures().size() - 1) * 45));
			} else {
				BoundsRefreshment.refreshBounds(this, this.getSize().width - 2,
						43);
			}
			this.getFigure().setBorder(null);
			this.getFigure().repaint();
		}
	}
}
