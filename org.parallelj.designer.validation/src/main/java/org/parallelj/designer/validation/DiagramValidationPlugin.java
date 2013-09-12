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
package org.parallelj.designer.validation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The DiagramValidationPlugin class controls the plug-in life cycle.
 */
public class DiagramValidationPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.parallelj.designer.validation";

	// The shared instance
	private static DiagramValidationPlugin plugin;

	// The enablement state of Live Validation, true by default
	private boolean isLiveValidationEnabled = true;
	
	/**
	 * The constructor
	 */
	public DiagramValidationPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		DiagramValidationPlugin.plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		DiagramValidationPlugin.plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance.
	 * 
	 * @return the shared instance
	 */
	public static DiagramValidationPlugin getDefault() {
		return DiagramValidationPlugin.plugin;
	}

	/**
	 * logs the Info message.
	 * 
	 * @param message
	 */
	public void logInfo(String message) {
		this.getLog().log(
				new Status(IStatus.INFO, DiagramValidationPlugin.PLUGIN_ID,
						message, null));
	}

	/**
	 * logs the Warn message.
	 * 
	 * @param message
	 */
	public void logWarn(String message) {
		this.getLog().log(
				new Status(IStatus.WARNING, DiagramValidationPlugin.PLUGIN_ID,
						message, null));
	}

	/**
	 * logs the Error message.
	 * 
	 * @param message
	 */
	public void logError(String message) {
		this.getLog().log(
				new Status(IStatus.ERROR, DiagramValidationPlugin.PLUGIN_ID,
						message, null));
	}

	/**
	 * logs the Error message.
	 * 
	 * @param message
	 * @param throwable
	 */
	public void logError(String message, Throwable throwable) {
		this.getLog().log(
				new Status(IStatus.ERROR, DiagramValidationPlugin.PLUGIN_ID,
						message, throwable));
	}

	/**
	 * Returns the enablement state of Live Validation
	 * @return The enablement state of Live Validation
	 */
	public boolean isLiveValidationEnabled() {
		return isLiveValidationEnabled;
	}

	/**
	 * Sets the enablement state of Live Validation
	 * @param isLiveValidationEnabled
	 */
	public void setLiveValidationEnabled(boolean isLiveValidationEnabled) {
		this.isLiveValidationEnabled = isLiveValidationEnabled;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path.
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
