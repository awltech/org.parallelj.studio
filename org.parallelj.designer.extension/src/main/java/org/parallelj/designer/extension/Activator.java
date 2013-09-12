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
package org.parallelj.designer.extension;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.parallelj.designer.extension"; //$NON-NLS-1$

	// The shared instance
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
	 * Logs message and Throwable in the Error Log
	 * 
	 * @param message
	 * @param throwable
	 */
	public static void logException(String message, Throwable throwable) {
		getDefault().getLog().log(
				new Status(IStatus.ERROR, PLUGIN_ID, message, throwable));
	}

	/**
	 * Logs message in the Error Log
	 * 
	 * @param message
	 */
	public static void logError(String message) {
		getDefault().getLog().log(
				new Status(IStatus.ERROR, PLUGIN_ID, message, null));
	}

	/**
	 * Logs message in the Info Log
	 * 
	 * @param message
	 */
	public static void logInfo(String message) {
		getDefault().getLog().log(new Status(IStatus.INFO, PLUGIN_ID, message, null));
	}

	/**
	 * Logs message in the Warn Log
	 * 
	 * @param message
	 */
	public static void logWarn(String message) {
		getDefault().getLog().log(new Status(IStatus.WARNING, PLUGIN_ID, message, null));
	}
}
