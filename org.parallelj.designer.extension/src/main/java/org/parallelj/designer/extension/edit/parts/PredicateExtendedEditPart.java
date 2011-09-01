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
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.RGB;
import org.parallelj.designer.edit.parts.PredicateEditPart;
import org.parallelj.designer.extension.tools.BoundsRefreshment;
import org.parallelj.designer.extension.tools.Drawer;

public class PredicateExtendedEditPart extends PredicateEditPart {

	/**
	 * Default main color of this object
	 */
	private final RGB preferredColor = new RGB(6, 201, 196);

	/**
	 * Actual colors of this object
	 */
	private RGB startColor;

	private RGB endColor;

	private RGB lightColor;

	public PredicateExtendedEditPart(View view) {
		super(view);
		updateColor(preferredColor, false);
	}

	/**
	 * Fixing the height of the Predicate node and updating diagram with last
	 * saved color when diagram closed and opened.
	 */
	@Override
	protected void refreshBounds() {
		BoundsRefreshment.refreshBounds(this, null, 35);
		this.updateColor(Drawer.getSavedRGB(this), false);
	}

	/**
	 * Creates rectangle for Predicate node, with color gradient starting from
	 * top-left to right-bottom, also having light and shadow effect.
	 */
	@Override
	protected IFigure createNodeShape() {
		IFigure figure = new PredicateFigure() {
			public void paintFigure(Graphics graphics) {
				Drawer.rectangleGradient(graphics, getBounds(), endColor,
						startColor, new RGB(0, 121, 212), lightColor);
			}
		};
		return primaryShape = figure;
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
		endColor = Drawer.lighten(60, startColor);
		lightColor = Drawer.lighten(40, endColor);
		if (isPaint) {
			this.getFigure().repaint();
		}
	}
}
