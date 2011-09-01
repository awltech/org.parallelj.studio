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
package org.parallelj.designer.extension.tools;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramColorRegistry;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.notation.DecorationNode;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.swt.graphics.Pattern;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class Drawer {

	private Drawer() {
	}

	/**
	 * Draws rectangle, with gradient from top-left to bottom-right with light
	 * and shadow effect.
	 * 
	 * @param graphics
	 *            The Graphics used to paint
	 * @param boundary
	 *            The rectangle boundary in which figure need to draw
	 * @param startColor
	 *            The starting color of the gradient
	 * @param endColor
	 *            The ending color of the gradient
	 * @param borderColor
	 *            The border color of shape
	 * @param lightColor
	 *            The light effect color
	 */
	public static void rectangleGradient(Graphics graphics, Rectangle boundary,
			RGB startColor, RGB endColor, RGB borderColor, RGB lightColor) {
		rectangleGradient(graphics, boundary, startColor, endColor,
				borderColor, lightColor, false);
	}

	/**
	 * Draws rectangle, with gradient from top-left to bottom-right with light
	 * and shadow effect.
	 * 
	 * @param graphics
	 *            The Graphics used to paint
	 * @param boundary
	 *            The rectangle boundary in which figure need to draw
	 * @param startColor
	 *            The starting color of the gradient
	 * @param endColor
	 *            The ending color of the gradient
	 * @param borderColor
	 *            The border color of shape
	 * @param lightColor
	 *            The light effect color
	 * @param showSelected
	 *            The rectangle will be shown with colored 2-px rounded shape
	 *            border, if true
	 */
	public static void rectangleGradient(Graphics graphics, Rectangle boundary,
			RGB startColor, RGB endColor, RGB borderColor, RGB lightColor,
			boolean showSelected) {

		// highlight effect
		if (showSelected) {
			RoundedRectangle bounds = new RoundedRectangle();
			bounds.setBounds(boundary);
			graphics.setBackgroundColor(DiagramColorRegistry.getInstance()
					.getColor(ColorConstants.orange.getRGB()));
			graphics.fillRoundRectangle(bounds.getBounds(), 10, 10);
		}

		RoundedRectangle bounds = null;

		// shadow effect, it will be shown when not highlight
		if (!showSelected) {
			bounds = new RoundedRectangle();
			bounds.setBounds(boundary);
			graphics.setBackgroundColor(DiagramColorRegistry.getInstance()
					.getColor(new RGB(190, 190, 190)));
			bounds.getBounds().expand(-2, -2);
			bounds.getBounds().translate(2, 2);
			graphics.fillRoundRectangle(bounds.getBounds(), 10, 10);
		}

		// light effect
		bounds = new RoundedRectangle();
		bounds.setBounds(boundary);
		bounds.getBounds().expand(-2, -2);
		bounds.getBounds().translate(-1, -1);
		graphics.setForegroundColor(DiagramColorRegistry.getInstance()
				.getColor(lightColor));
		graphics.setBackgroundColor(DiagramColorRegistry.getInstance()
				.getColor(lightColor));
		graphics.fillRoundRectangle(bounds.getBounds(), 8, 8);

		// preparing gradient
		bounds.getBounds().translate(1, 1);
		bounds.getBounds().expand(-1, -1);
		Point start = bounds.getBounds().getTopLeft();
		Point end = bounds.getBounds().getBottomRight();

		Pattern pattern = new Pattern(Display.getCurrent(), start.x, start.y,
				end.x, end.y, DiagramColorRegistry.getInstance().getColor(
						startColor), DiagramColorRegistry.getInstance()
						.getColor(endColor));

		// filling rectangle with gradient
		graphics.setBackgroundPattern(pattern);
		graphics.fillRoundRectangle(bounds.getBounds(), 8, 8);

		// drawing boundary of rectangle
		bounds = new RoundedRectangle();
		bounds.setBounds(boundary);
		bounds.getBounds().translate(-1, -1);
		bounds.getBounds().expand(-2, -2);
		graphics.setLineWidth(1);
		graphics.setForegroundColor(DiagramColorRegistry.getInstance()
				.getColor(borderColor));
		graphics.drawRoundRectangle(bounds.getBounds(), 8, 8);
	}

	/**
	 * Draws circle, with gradient from top-left to bottom-right with light and
	 * shadow effect.
	 * 
	 * @param graphics
	 *            The Graphics used to paint
	 * @param boundary
	 *            The rectangle boundary in which figure need to draw
	 * @param startColor
	 *            the starting color of the gradient
	 * @param endColor
	 *            the ending color of the gradient
	 * @param lightColor
	 *            The light effect color
	 */
	public static void circleGradient(Graphics graphics, Rectangle boundary,
			RGB startColor, RGB endColor, RGB lightColor) {

		// shadow effect
		Rectangle bounds = new Rectangle(boundary);
		bounds.expand(-2, -2);
		bounds.translate(2, 2);
		graphics.setBackgroundColor(DiagramColorRegistry.getInstance()
				.getColor(new RGB(175, 175, 175)));
		graphics.fillRoundRectangle(bounds, 80, 80);

		// preparing gradient
		Point location = bounds.getLocation();
		Dimension size = bounds.getSize();

		location.x = location.x - 2;
		location.y = location.y - 2;

		bounds = new Rectangle(location, size);
		bounds.translate(-1, -1);
		Point start = bounds.getTopLeft();
		Point end = bounds.getBottomRight();
		Pattern pattern = new Pattern(Display.getCurrent(), start.x, start.y,
				end.x, end.y, DiagramColorRegistry.getInstance().getColor(
						startColor), DiagramColorRegistry.getInstance()
						.getColor(endColor));

		// filling circle with gradient
		graphics.setBackgroundPattern(pattern);
		graphics.fillRoundRectangle(bounds, 80, 80);

		// drawing boundary of circle
		graphics.setLineWidth(1);
		graphics.setForegroundColor(DiagramColorRegistry.getInstance()
				.getColor(ColorConstants.black.getRGB()));
		graphics.drawRoundRectangle(bounds, 80, 80);

		// light effect
		bounds.expand(-1, -1);
		graphics.setForegroundColor(DiagramColorRegistry.getInstance()
				.getColor(lightColor));
		graphics.drawArc(bounds, 75, 100);
	}

	/**
	 * Performs a weighted interpolation from an RGB toward another
	 * 
	 * @param pct
	 *            percentage standing for the interpolation weigh
	 * @param source
	 *            source RGB
	 * @param target
	 *            target RGB
	 * @return resulting RGB
	 */
	private static RGB fade(int pct, RGB source, RGB target) {
		RGB result = new RGB(0, 0, 0);
		result.red = source.red + (target.red - source.red) * pct / 100;
		result.green = source.green + (target.green - source.green) * pct / 100;
		result.blue = source.blue + (target.blue - source.blue) * pct / 100;

		return result;
	}

	/**
	 * Performs a weighted interpolation from an RGB toward white
	 * 
	 * @param pct
	 *            percentage standing for the interpolation weigh
	 * @param source
	 *            source RGB
	 * @return resulting RGB
	 */
	public static RGB lighten(int pct, RGB source) {
		return fade(pct, source, new RGB(252, 255, 255));
	}

	/**
	 * Performs a weighted interpolation from an RGB toward black
	 * 
	 * @param pct
	 *            percentage standing for the interpolation weigh
	 * @param source
	 *            source RGB
	 * @return resulting RGB
	 */
	public static RGB darken(int pct, RGB source) {
		return fade(pct, source, new RGB(0, 0, 0));
	}

	/**
	 * Performs a weighted interpolation from an RGB toward a deeper color
	 * Beware, pct * depth cannot exceed 100, otherwise unwanted side-effects
	 * will appear
	 * 
	 * @param pct
	 *            percentage standing for the darkening weigh
	 * @param depth
	 *            rate of deepening. The more the deeper.
	 * @param source
	 *            source RGB
	 * @return resulting RGB
	 */
	public static RGB deepen(int pct, float depth, RGB source) {
		RGB result = new RGB(source.red, source.green, source.blue);

		if ((source.red > source.green) && (source.red > source.blue)) {
			result.red = (int) (source.red + (0 - source.red) * pct
					/ (100 * depth));
			result.green = (int) (source.green + (0 - source.green) * pct
					* depth / 100);
			result.blue = (int) (source.blue + (0 - source.blue) * pct * depth
					/ 100);
		} else if ((source.blue > source.green) && (source.blue > source.red)) {
			result.red = (int) (source.red + (0 - source.red) * pct * depth
					/ 100);
			result.green = (int) (source.green + (0 - source.green) * pct
					* depth / 100);
			result.blue = (int) (source.blue + (0 - source.blue) * pct
					/ (100 * depth));
		} else if ((source.green > source.blue) && (source.green > source.red)) {
			result.red = (int) (source.red + (0 - source.red) * pct * depth
					/ 100);
			result.green = (int) (source.green + (0 - source.green) * pct
					/ (100 * depth));
			result.blue = (int) (source.blue + (0 - source.blue) * pct * depth
					/ 100);
		}

		return result;
	}

	/**
	 * @param editPart
	 * @return RGB color saved for passed editpart when diagram file close &
	 *         open.
	 */
	public static RGB getSavedRGB(ShapeNodeEditPart editPart) {
		// taking saved last color
		int intSavedColor = ((Integer) editPart
				.getStructuralFeatureValue(NotationPackage.eINSTANCE
						.getFillStyle_FillColor())).intValue();
		return FigureUtilities.integerToRGB(intSavedColor);
	}

	/**
	 * When object drawn first time, draw with preferred default color.
	 * 
	 * @param notification
	 * @param editPart
	 */
	public static void drawWithDefault(Notification notification,
			ShapeNodeEditPart editPart) {
		if (notification.getNewValue() instanceof DecorationNode) {
			editPart.setStructuralFeatureValue(NotationPackage.eINSTANCE
					.getFillStyle_FillColor(), editPart
					.getPreferredValue(NotationPackage.eINSTANCE
							.getFillStyle_FillColor()));
		}
	}
}
