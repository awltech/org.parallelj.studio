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
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.parallelj.designer.edit.parts.ProgramEditPart;
import org.parallelj.designer.extension.adapters.ProgramAdapter;
import org.parallelj.designer.extension.tools.Drawer;

public class ProgramExtendedEditPart extends ProgramEditPart {

	/**
	 * Default main color of this object
	 */
	private final RGB preferredColor = new RGB(231, 231, 231);

	/**
	 * Actual colors of this object
	 */
	private RGB startColor;

	private RGB endColor;

	private RGB lightColor;

	public ProgramExtendedEditPart(View view) {
		super(view);
		updateColor(preferredColor, false);
		addAdapters(view);
	}

	/**
	 * Adds adapters on view associated with Edit Part.
	 * 
	 * @param view
	 */
	private void addAdapters(View view) {
		EObject element = view.getElement();
		if (element != null) {
			element.eAdapters().add(new ProgramAdapter(this));
		}
	}

	/**
	 * Creates rectangle for Program node, with color gradient starting from
	 * top-left to right-bottom, also having light and shadow effect.
	 */
	@Override
	protected IFigure createNodeShape() {
		IFigure figure = new ProgramFigure() {
			public void paintFigure(Graphics graphics) {
				Drawer.rectangleGradient(graphics, getBounds(), startColor,
						endColor, new RGB(0, 0, 0), lightColor);
			}
		};

		// this to give inner margin
		figure.setBorder(new MarginBorder(getMapMode().DPtoLP(2), getMapMode()
				.DPtoLP(5), getMapMode().DPtoLP(6), getMapMode().DPtoLP(6)));

		return primaryShape = figure;
	}

	/**
	 * Fixing the width and height of the Program node and paint editpart with
	 * last saved color when diagram close and open.
	 */
	@Override
	protected void refreshBounds() {
		super.refreshBounds();
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
	 * Handle the event for fill color change.
	 */
	@Override
	protected void handleNotificationEvent(Notification notification) {
		Object feature = notification.getFeature();
		if (NotationPackage.eINSTANCE.getFillStyle_FillColor().equals(feature)) {
			int newIntColor = ((Integer) notification.getNewValue()).intValue();
			RGB newRGBColor = FigureUtilities.integerToRGB(newIntColor);
			this.updateColor(newRGBColor, true);
		} else if (notification.getEventType() == Notification.ADD) {
			Drawer.drawWithDefault(notification, this);
			super.handleNotificationEvent(notification);
		} else {
			super.handleNotificationEvent(notification);
		}
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
		endColor = Drawer.darken(40, startColor);
		lightColor = Drawer.lighten(40, startColor);
		if (isPaint) {
			this.getFigure().repaint();
		}
	}
}
