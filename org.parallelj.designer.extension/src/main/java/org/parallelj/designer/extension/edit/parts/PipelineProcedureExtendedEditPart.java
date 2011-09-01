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

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.notation.DecorationNode;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.RGB;
import org.parallelj.designer.edit.parts.PipelineProcedureEditPart;
import org.parallelj.designer.edit.parts.ProgramProgramCompartmentEditPart;
import org.parallelj.designer.extension.tools.BoundsRefreshment;
import org.parallelj.designer.extension.tools.Drawer;
import org.parallelj.designer.extension.tools.JoinSplitUpdater;
import org.parallelj.model.Procedure;

public class PipelineProcedureExtendedEditPart extends
		PipelineProcedureEditPart {

	/**
	 * Default main color of this object
	 */
	private final RGB preferredColor = new RGB(116, 155, 194);

	/**
	 * Actual colors of this object
	 */
	private RGB startColor;

	private RGB endColor;

	private RGB lightColor;

	public PipelineProcedureExtendedEditPart(View view) {
		super(view);
		updateColor(preferredColor, false);
	}

	/**
	 * Fixing the height, width and location of the PipelineProcedure node as
	 * per parent Pipeline node and updating diagram with last saved color when
	 * diagram closed and opened.
	 */
	@Override
	protected void refreshBounds() {

		if (this.getParent() != null) {
			Dimension size = new Dimension();
			int procedureHeight = 43;
			// getting last saved Y co-ordinate
			int locationY = getY();

			// if insertion from palette
			if (getX() > 0 && locationY > 0) {
				locationY = BoundsRefreshment.giveYAxis(this);
			}
			EditPart root = this;
			while (!(root.getParent() instanceof ProgramProgramCompartmentEditPart)) {
				root = root.getParent();
			}
			// getting parent width
			int pipelineWidth = this.getFeatureValue(
					(PipelineExtendedEditPart) root,
					NotationPackage.eINSTANCE.getSize_Width());

			// getting current width of object
			int defaultWidth = this.getFeatureValue(this,
					NotationPackage.eINSTANCE.getSize_Width());

			if (pipelineWidth > 0) {
				// make width of sequence procedure to match the width of
				// compartment
				BoundsRefreshment.refreshBounds(this, pipelineWidth - 18,
						procedureHeight);
				size.width = pipelineWidth - 18;
			} else {
				BoundsRefreshment.refreshBounds(this, null, procedureHeight);
				size.width = defaultWidth;
			}

			size.height = procedureHeight;
			// set location as per sequence number in pipeline
			Point loc = new Point(0, locationY);
			((GraphicalEditPart) this.getParent()).setLayoutConstraint(this,
					this.getFigure(), new Rectangle(loc, new Dimension(size)));

			// updating color
			this.updateColor(Drawer.getSavedRGB(this), false);
		}
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
	 * To allow to add procedure in better manner. Otherwise need to put cursor
	 * at last or empty space from Pipeline compartment to add procedures.
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		removeEditPolicy(EditPolicy.LAYOUT_ROLE);
	}

	/**
	 * Creates rectangle for PipelineProcedure node, with color gradient
	 * starting from top-left to right-bottom, also having light and shadow
	 * effect.
	 */
	@Override
	protected IFigure createNodeShape() {
		IFigure figure = new ProcedureFigure() {
			public void paintFigure(Graphics graphics) {
				Drawer.rectangleGradient(graphics, getBounds(), endColor,
						startColor, new RGB(0, 0, 0), lightColor);
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
		JoinSplitUpdater.updateSplitJoin(getProcedure(), this.getPrimaryShape()
				.getFigureProcedureJoinFigure(), this.getPrimaryShape()
				.getFigureProcedureSplitFigure());
	}

	/**
	 * Updates JOIN icon based on passed mode. This will triggered when user
	 * change JOIN value from property view.
	 * 
	 * @param mode
	 *            it can be AND, XOR or OR
	 */
	public void setJoinIcon(String mode) {
		JoinSplitUpdater.setJoinIcon(getProcedure(), mode, this
				.getPrimaryShape().getFigureProcedureJoinFigure());
	}

	/**
	 * Updates SPLIT icon based on passed mode. This will triggered when user
	 * change SPLIT value from property view.
	 * 
	 * @param mode
	 *            it can be AND, XOR or OR
	 */
	public void setSplitIcon(String mode) {
		JoinSplitUpdater.setSplitIcon(getProcedure(), mode, this
				.getPrimaryShape().getFigureProcedureSplitFigure());
	}

	private Procedure getProcedure() {
		return (Procedure) ((View) this.getModel()).getElement();
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
	 * Handle the event for fill color, add node and location update.
	 */
	@Override
	protected void handleNotificationEvent(Notification notification) {
		super.handleNotificationEvent(notification);
		Object feature = notification.getFeature();
		// if fill color change
		if (NotationPackage.eINSTANCE.getFillStyle_FillColor().equals(feature)) {
			int newIntColor = ((Integer) notification.getNewValue()).intValue();
			RGB newRGBColor = FigureUtilities.integerToRGB(newIntColor);
			this.updateColor(newRGBColor, true);
		}
		// if node add then add with default color and location
		else if (notification.getEventType() == Notification.ADD) {
			if (notification.getNewValue() instanceof DecorationNode) {
				Drawer.drawWithDefault(notification, this);
				if (getX() > 0 && getY() > 0) {
					BoundsRefreshment.refreshLocation(this);
				}
			}
		}
		// setting values in features
		else if (NotationPackage.eINSTANCE.getLocation_X().equals(feature)
				|| NotationPackage.eINSTANCE.getLocation_Y().equals(feature)) {
			// if object insert from palette, taking default location as per
			// sequence of procedure
			if (getX() < 0 || getY() <= 0 || (getX() > 0 && getY() > 0)
					|| getY() % 45 != 0) {
				this.setStructuralFeatureValue(
						NotationPackage.eINSTANCE.getLocation_Y(),
						BoundsRefreshment.giveYAxis(this));
				this.setStructuralFeatureValue(
						NotationPackage.eINSTANCE.getLocation_X(), 0);
			}
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
	 * @return the X Co-ordinate of object
	 */
	public int getX() {
		return ((Integer) this
				.getStructuralFeatureValue(NotationPackage.eINSTANCE
						.getLocation_X())).intValue();
	}

	/**
	 * @return the Y Co-ordinate of object
	 */
	public int getY() {
		return ((Integer) this
				.getStructuralFeatureValue(NotationPackage.eINSTANCE
						.getLocation_Y())).intValue();
	}

	/**
	 * @param editPart
	 * @param eAttribute
	 * @return the feature value for passed feature
	 */
	private int getFeatureValue(ShapeNodeEditPart editPart,
			EAttribute eAttribute) {
		return ((Integer) editPart.getStructuralFeatureValue(eAttribute))
				.intValue();
	}
}
