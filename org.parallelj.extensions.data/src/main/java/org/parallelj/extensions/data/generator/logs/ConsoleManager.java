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
package org.parallelj.extensions.data.generator.logs;

import java.io.IOException;


import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.parallelj.extensions.data.generator.Activator;

/**
 * Eclipse's console with colors
 *
 * @author XA Toolbox
 * @version 1.0
 *
 */
public class ConsoleManager {

	/**
	 * Static console instance
	 */
	private static MessageConsole console;

	/**
	 * Static instanciation block
	 */
	static {
		console = new MessageConsole(
				Messages.CONSOLE_NAME.message(), null);
		ConsolePlugin.getDefault().getConsoleManager()
		.addConsoles(new IConsole[] { console });
		console.activate();
	}

	/**
	 * "Clears the console"
	 */
	public static void clear() {
		ConsoleManager.getDisplay().asyncExec(new Runnable() {
			public void run() {
				console.getDocument().set("");
			}
		});
	}

	/**
	 * Displays the specified object in the console, with the specified color
	 *
	 * @param o
	 * the object to display
	 * @param colorValue
	 * the color to use
	 */
	public static void write(Object o, ColorValue colorValue) {
		internalWrite(o, colorValue, false);
	}

	/**
	 * Display the specified object in the console with the specified color, and
	 * go to the next line
	 *
	 * @param o
	 * the object to display
	 * @param colorValue
	 * the color to use
	 */
	public static void writeln(Object o, ColorValue colorValue) {
		internalWrite(o, colorValue, true);
	}

	/**
	 * Display the specified object in the console using the default color which
	 * is Black
	 *
	 * @param o
	 * the object to display
	 */
	public static void write(Object o) {
		internalWrite(o, ColorValue.BLACK, false);
	}

	/**
	 * Display the specified object in the console using the default color which
	 * is Black, and go to the next line
	 *
	 * @param o
	 * the object to display
	 */
	public static void writeln(Object o) {
		internalWrite(o, ColorValue.BLACK, true);
	}

	/**
	 * Display the specified object in the console, using the specified color,
	 * and go to the next line if it's asked
	 *
	 * @param o
	 * the object to display
	 * @param colorValue
	 * the color to use
	 * @param cr
	 * true to go to the next line, false otherwise
	 */
	private static void internalWrite(final Object o,
			final ColorValue colorValue, final boolean cr) {
		ConsoleManager.getDisplay().asyncExec(new Runnable() {
			public void run() {
				String message = o.toString();
				MessageConsoleStream consoleStream = console.newMessageStream();
				consoleStream.setColor(colorValue.getColor());
				try {
					consoleStream.write(message + (cr ? "\n" : ""));
					consoleStream.close();
				} catch (IOException ioe) {
					Activator.sendErrorToErrorLog(
							Messages.COM01E.message(), ioe);
				}
			}
		});
	}

	/**
	 * List of available color values
	 *
	 *
	 */
	public static enum ColorValue {
		BLACK, ORANGE, BLUE, GREEN, RED, PURPLE, DARK_GREY, LIGHT_GREY, TURQUOISE, BROWN;

		/**
		 * Returns the corresponding color
		 *
		 * @return color the color corresponding to the enum's value
		 */
		public Color getColor() {
			switch (this) {
			case RED:
				return ColorContainer.getRed();
			case ORANGE:
				return ColorContainer.getOrange();
			case BLUE:
				return ColorContainer.getBlue();
			case GREEN:
				return ColorContainer.getGreen();
			case BLACK:
				return ColorContainer.getBlack();
			case PURPLE:
				return ColorContainer.getPurple();
			case LIGHT_GREY:
				return ColorContainer.getLightGrey();
			case DARK_GREY:
				return ColorContainer.getDarkGrey();
			case TURQUOISE:
				return ColorContainer.getTurquoise();
			case BROWN:
				return ColorContainer.getBrown();
			default:
				return ColorContainer.getBlack();
			}
		}
	}

	/**
	 * <p>
	 * The color container object acts like a cache containing all the available
	 * colors.
	 * </p>
	 * <p>
	 * Obviously this class won't be documented because I think the color's
	 * names are actually pretty easy to understand.
	 * </p>
	 *
	 *
	 */
	private static class ColorContainer {

		private static Color orange;

		private static Color red;

		private static Color blue;

		private static Color green;

		private static Color black;

		private static Color purple;

		private static Color darkGrey;

		private static Color lightGrey;

		private static Color turquoise;

		private static Color brown;

		public static Color getBlack() {
			if (black == null)
				black = new Color(getDisplay(), 0, 0, 0);
			return black;
		}

		public static Color getBlue() {
			if (blue == null)
				blue = new Color(getDisplay(), 0, 0, 255);
			return blue;
		}

		public static Color getGreen() {
			if (green == null)
				green = new Color(getDisplay(), 0, 200, 0);
			return green;
		}

		public static Color getOrange() {
			if (orange == null)
				orange = new Color(getDisplay(), 255, 127, 0);
			return orange;
		}

		public static Color getRed() {
			if (red == null)
				red = new Color(getDisplay(), 255, 0, 0);
			return red;
		}

		public static Color getPurple() {
			if (purple == null)
				purple = new Color(getDisplay(), 153, 102, 204);
			return purple;
		}

		public static Color getDarkGrey() {
			if (darkGrey == null)
				darkGrey = new Color(getDisplay(), 85, 85, 85);
			return darkGrey;
		}

		public static Color getLightGrey() {
			if (lightGrey == null)
				lightGrey = new Color(getDisplay(), 170, 170, 170);
			return lightGrey;
		}

		public static Color getTurquoise() {
			if (turquoise == null)
				turquoise = new Color(getDisplay(), 64, 224, 208);
			return turquoise;
		}

		public static Color getBrown() {
			if (brown == null)
				brown = new Color(getDisplay(), 150, 75, 0);
			return brown;
		}

	}

	/**
	 * This method allows to resolve the Display object to use. It will use the
	 * current one if it's set, or the default one otherwise.
	 *
	 * @return The current display if it's set, the default one otherwise.
	 */
	private static Display getDisplay() {
		return Display.getCurrent() == null ? Display.getDefault() : Display
				.getCurrent();
	}

	/**
	 * Activate the Console in the selected perspective
	 */
	public static void show() {
		console.activate();
	}

}

