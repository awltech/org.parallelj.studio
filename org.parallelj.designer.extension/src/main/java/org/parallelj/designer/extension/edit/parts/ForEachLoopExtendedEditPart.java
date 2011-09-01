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
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramColorRegistry;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.draw2d.ui.figures.RoundedRectangleBorder;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.RGB;
import org.parallelj.designer.edit.parts.ForEachLoopEditPart;
import org.parallelj.designer.extension.adapters.ForEachLoopAdapter;
import org.parallelj.designer.extension.adapters.ForEachLoopIterableRefreshmentAdapter;
import org.parallelj.designer.extension.adapters.LinkPredicateRefreshmentOnLinkAddRemAdapter;
import org.parallelj.designer.extension.adapters.LinkPredicateRefreshmentOnSplitAdapter;
import org.parallelj.designer.extension.tools.BoundsRefreshment;
import org.parallelj.designer.extension.tools.Drawer;
import org.parallelj.designer.extension.tools.JoinSplitUpdater;
import org.parallelj.model.ForEachLoop;

public class ForEachLoopExtendedEditPart extends ForEachLoopEditPart {

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

	/**
	 * flag for highlighting procedure
	 */
	private boolean showSelected;

	public ForEachLoopExtendedEditPart(View view) {
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
			element.eAdapters().add(new ForEachLoopAdapter(this));
			element.eAdapters().add(
					new LinkPredicateRefreshmentOnSplitAdapter(this));
			element.eAdapters().add(
					new LinkPredicateRefreshmentOnLinkAddRemAdapter(this));
			element.eAdapters().add(
					new ForEachLoopIterableRefreshmentAdapter(this));
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
	 * Fixing the height of the ForEachLoop node and updating diagram with last
	 * saved color when diagram closed and opened.
	 */
	@Override
	protected void refreshBounds() {
		BoundsRefreshment.refreshBounds(this, null, 55);
		this.updateColor(Drawer.getSavedRGB(this), false);
	}

	/**
	 * Creates rectangle for ForEachLoop node, with color gradient starting from
	 * top-left to right-bottom, also having light and shadow effect. It will
	 * also show ForEachLoop highlighted, if it's linked to any Handler and that
	 * Handler object is selected.
	 */
	@Override
	protected IFigure createNodeShape() {
		IFigure figure = new ForEachLoopFigure() {
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
		JoinSplitUpdater.updateSplitJoin(getForEachLoop(), this
				.getPrimaryShape().getFigureForEachLoopJoinFigure(), this
				.getPrimaryShape().getFigureForEachLoopSplitFigure());
	}

	/**
	 * Updates JOIN icon based on passed mode. This will triggered when user
	 * change JOIN value from property view.
	 * 
	 * @param mode
	 *            it can be AND, XOR or OR
	 */
	public void setJoinIcon(String mode) {
		JoinSplitUpdater.setJoinIcon(getForEachLoop(), mode, this
				.getPrimaryShape().getFigureForEachLoopJoinFigure());
	}

	/**
	 * Updates SPLIT icon based on passed mode. This will triggered when user
	 * change SPLIT value from property view.
	 * 
	 * @param mode
	 *            it can be AND, XOR or OR
	 */
	public void setSplitIcon(String mode) {
		JoinSplitUpdater.setSplitIcon(getForEachLoop(), mode, this
				.getPrimaryShape().getFigureForEachLoopSplitFigure());
	}

	private ForEachLoop getForEachLoop() {
		return (ForEachLoop) ((View) this.getModel()).getElement();
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
	 * It will also show ForEachLoop highlighted, if it's linked to any Handler
	 * and that Handler object is selected.
	 */
	public void showSelected() {
		if (this.getFigure().getBorder() == null) {
			showSelected = true;
			BoundsRefreshment.refreshBounds(this, this.getSize().width + 2, 59);
			RoundedRectangleBorder border = new RoundedRectangleBorder(10, 10);
			border.setWidth(2);
			border.setColor(DiagramColorRegistry.getInstance().getColor(
					ColorConstants.orange.getRGB()));
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
			BoundsRefreshment.refreshBounds(this, this.getSize().width - 2, 55);
			this.getFigure().setBorder(null);
			this.getFigure().repaint();
		}
	}
}
