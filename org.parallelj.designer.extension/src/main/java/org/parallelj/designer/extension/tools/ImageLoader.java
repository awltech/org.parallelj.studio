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

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.parallelj.designer.part.ParallelJDiagramEditorPlugin;

public class ImageLoader {

	private ImageLoader() {
	}

	/**
	 * Returns image in plugin.
	 * 
	 * @param pluginId
	 *            : Id of the plugin containing the image
	 * @param imageFilePath
	 *            : image File Path in plugin
	 * @return Image if exists
	 */
	public static Image getImage(String pluginId, String imageFilePath) {
		Image image = ParallelJDiagramEditorPlugin.getInstance()
				.getImageRegistry().get(pluginId + ":" + imageFilePath);
		// if not in image registry
		if (image == null) {
			image = loadImage(pluginId, imageFilePath);
		}
		return image;
	}

	/**
	 * Loads image in Image Registry.
	 * 
	 * @param pluginId
	 *            : Id of the plugin containing the image
	 * @param imageFilePath
	 *            : image File Path in plugin
	 * @return Image if loaded
	 */
	private synchronized static Image loadImage(String pluginId,
			String imageFilePath) {
		Image image = null;
		ImageDescriptor imageDescriptor = AbstractUIPlugin
				.imageDescriptorFromPlugin(pluginId, imageFilePath);
		if (imageDescriptor != null) {
			image = imageDescriptor.createImage();
			ParallelJDiagramEditorPlugin.getInstance().getImageRegistry()
					.put(pluginId + ":" + imageFilePath, image);
		}
		return image;
	}
}
