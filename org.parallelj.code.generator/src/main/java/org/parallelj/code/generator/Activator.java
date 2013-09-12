package org.parallelj.code.generator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class Activator extends AbstractUIPlugin {

	/**
	 * The plugin ID
	 */
	public static final String PLUGIN_ID = "org.parallelj.codegenerator"; //$NON-NLS-1$

	/**
	 * This property can be used in order to activate the DEBUG mode on this
	 * plugin: {@value #PARALLELJ_DEBUG_SYSTEM_PROPERTY}
	 */
	public static final String PARALLELJ_DEBUG_SYSTEM_PROPERTY = "org.parallelj.codegenerator.Debug";

	/**
	 * Shared instance
	 */
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * @return true if the {@value #PARALLELJ_DEBUG_SYSTEM_PROPERTY} property is
	 *         provided while executing Eclipse
	 */
	public boolean isDebugActivated() {
		return System.getProperty(PARALLELJ_DEBUG_SYSTEM_PROPERTY) != null;
	}

	/**
	 * Sends the message to the Error Log with the INFO severity
	 * 
	 * @param message
	 */
	public static void sendInfoToErrorLog(String message) {
		Activator.plugin.getLog().log(
				new Status(IStatus.INFO, Activator.PLUGIN_ID, message));
	}

	/**
	 * Sends the message to the Error Log with the WARN severity
	 * 
	 * @param message
	 */
	public static void sendWarningToErrorLog(String message) {
		Activator.plugin.getLog().log(
				new Status(IStatus.WARNING, Activator.PLUGIN_ID, message));
	}

	/**
	 * Sends the message to the Error Log with the ERROR severity
	 * 
	 * @param message
	 */
	public static void sendErrorToErrorLog(String message) {
		Activator.plugin.getLog().log(
				new Status(IStatus.ERROR, Activator.PLUGIN_ID, message));
	}

	/**
	 * Sends the message and the Throwable to the Error Log with the ERROR
	 * severity
	 * 
	 * @param message
	 * @param t
	 *            Throwable
	 */
	public static void sendErrorToErrorLog(String message, Throwable t) {
		Activator.plugin.getLog().log(
				new Status(IStatus.ERROR, Activator.PLUGIN_ID, message, t));
	}

}
