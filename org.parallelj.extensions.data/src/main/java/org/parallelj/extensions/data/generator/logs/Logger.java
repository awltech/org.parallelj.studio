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

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.parallelj.extensions.data.generator.Activator;
import org.parallelj.extensions.data.generator.logs.ConsoleManager.ColorValue;



/**
 * Java Standard Logger extension, adapted to log in files located in the
 * current workspace metadata information
 *
 */
public class Logger {

	/**
	 * Internal Logger Instance
	 *
	 * @see java.util.logging.Logger
	 */
	private java.util.logging.Logger logger = null;

	/**
	 * File Pattern used to determine where to store files
	 */
	private static final String FILE_PATTERN = "%s/logs/logs-%s.log";

	/**
	 * Singleton Holder
	 *
	 *
	 */
	private static final class Holder {
		private static Logger instance = new Logger();
	}

	/**
	 * Custom Formatter Class. Used to format logs quite like LOG4J.
	 *
	 *
	 */
	private static final class CustomFormatter extends Formatter {

		/*
		 * Date Formatter
		 */
		private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(
		"yyyy-MM-dd HH:mm:ss.SSS");

		/*
		 * Pattern used for standard messages
		 */
		private static final String MSGPATTERN = "%1$s [%2$-4s] %4$s\n";

		/*
		 * Pattern used for messages containing an Exception
		 */
		private static final String MSGPATTERN_WITH_EXCEPTION = MSGPATTERN
		+ "%5$s\n";

		/*
		 * (non-Javadoc)
		 *
		 * @see java.util.logging.Formatter#format(java.util.logging.LogRecord)
		 */
		@Override
		public String format(LogRecord record) {
			String dateValue = DATE_FORMATTER.format(new Date(record
					.getMillis()));
			if (record.getThrown() == null)
				return String.format(MSGPATTERN, dateValue, record.getLevel()
						.getName(), record.getSourceClassName(), record
						.getMessage());
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter, true);
			record.getThrown().printStackTrace(printWriter);
			printWriter.flush();
			stringWriter.flush();
			return String.format(MSGPATTERN_WITH_EXCEPTION, dateValue, record
					.getLevel().getName(), record.getSourceClassName(), record
					.getMessage(), stringWriter.toString());
		}
	}

	/**
	 * Private constructor
	 */
	private Logger() {
		try {
			this.logger = java.util.logging.Logger
			.getLogger(Activator.PLUGIN_ID);
			this.logger.setLevel(Level.INFO);
			this.logger.setUseParentHandlers(false);
			String filePath = String.format(FILE_PATTERN, Activator
					.getDefault().getStateLocation().toOSString(),
					System.currentTimeMillis());
			File file = new File(filePath);
			if (file.getParentFile() == null || !file.getParentFile().exists())
				file.getParentFile().mkdirs();
			FileHandler fileHandler = new FileHandler(filePath);
			fileHandler.setFormatter(new CustomFormatter());
			this.logger.addHandler(fileHandler);
			this.logger.log(Level.INFO, Messages.LOG02I.message());
		} catch (Exception e) {
			Activator.sendErrorToErrorLog(
					Messages.LOG01E.message(), e);
		}
	}

	/**
	 * @return The logger instance
	 */
	public static java.util.logging.Logger getLogger() {
		return Holder.instance.logger;
	}

	/**
	 * This method allows to log a message with a {@link Level#FINEST} level
	 *
	 * @param message
	 * The message to log
	 */
	public static void finest(String message) {
		Holder.instance.logger.log(Level.FINEST, message);
		ConsoleManager.writeln(message, ColorValue.DARK_GREY);
	}

	/**
	 * This method allows to log a message with a {@link Level#FINEST} level
	 *
	 * @param message
	 * The message to log
	 * @param t
	 * The associated {@link Throwable}
	 */
	public static void finest(String message, Throwable t) {
		Holder.instance.logger.log(Level.FINEST, message, t);
		ConsoleManager.writeln(message, ColorValue.DARK_GREY);
	}

	/**
	 * This method allows to log a message with a {@link Level#FINER} level
	 *
	 * @param message
	 * The message to log
	 */
	public static void trace(String message) {
		Holder.instance.logger.log(Level.FINER, message);
		ConsoleManager.writeln(message, ColorValue.BLUE);
	}

	/**
	 * This method allows to log a message with a {@link Level#FINER} level
	 *
	 * @param message
	 * The message to log
	 * @param t
	 * The associated {@link Throwable}
	 */
	public static void trace(String message, Throwable t) {
		Holder.instance.logger.log(Level.FINER, message, t);
		ConsoleManager.writeln(message, ColorValue.BLUE);
	}

	/**
	 * This method allows to log a message with a {@link Level#FINE} level
	 *
	 * @param message
	 * The message to log
	 */
	public static void debug(String message) {
		Holder.instance.logger.log(Level.FINE, message);
		ConsoleManager.writeln(message, ColorValue.PURPLE);
	}

	/**
	 * This method allows to log a message with a {@link Level#FINE} level
	 *
	 * @param message
	 * The message to log
	 * @param t
	 * The associated {@link Throwable}
	 */
	public static void debug(String message, Throwable t) {
		Holder.instance.logger.log(Level.FINE, message, t);
		ConsoleManager.writeln(message, ColorValue.PURPLE);
	}

	/**
	 * This method allows to log a message with a {@link Level#INFO} level
	 *
	 * @param message
	 * The message to log
	 */
	public static void info(String message) {
		Holder.instance.logger.log(Level.INFO, message);
		ConsoleManager.writeln(message, ColorValue.GREEN);
	}

	/**
	 * This method allows to log a message with a {@link Level#INFO} level
	 *
	 * @param message
	 * The message to log
	 * @param t
	 * The associated {@link Throwable}
	 */
	public static void info(String message, Throwable t) {
		Holder.instance.logger.log(Level.INFO, message, t);
		ConsoleManager.writeln(message, ColorValue.GREEN);
	}

	/**
	 * This method allows to log a message with a {@link Level#WARNING} level
	 *
	 * @param message
	 * The message to log
	 */
	public static void warning(String message) {
		Holder.instance.logger.log(Level.WARNING, message);
		ConsoleManager.writeln(message, ColorValue.ORANGE);
	}

	/**
	 * This method allows to log a message with a {@link Level#WARNING} level
	 *
	 * @param message
	 * The message to log
	 * @param t
	 * The associated {@link Throwable}
	 */
	public static void warning(String message, Throwable t) {
		Holder.instance.logger.log(Level.WARNING, message, t);
		ConsoleManager.writeln(message, ColorValue.ORANGE);
	}

	/**
	 * This method allows to log a message with a {@link Level#SEVERE} level
	 *
	 * @param message
	 * The message to log
	 */
	public static void error(String message) {
		Holder.instance.logger.log(Level.SEVERE, message);
		ConsoleManager.writeln(message, ColorValue.RED);
	}

	/**
	 * This method allows to log a message with a {@link Level#SEVERE} level
	 *
	 * @param message
	 * The message to log
	 * @param t
	 * The associated {@link Throwable}
	 */
	public static void error(String message, Throwable t) {
		Holder.instance.logger.log(Level.SEVERE, message, t);
		ConsoleManager.writeln(message, ColorValue.RED);
	}

}
