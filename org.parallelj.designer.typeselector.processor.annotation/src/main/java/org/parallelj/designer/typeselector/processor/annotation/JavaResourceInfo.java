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
package org.parallelj.designer.typeselector.processor.annotation;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.graphics.Image;
import org.eclipselabs.resourceselector.processor.java.JavaImagesManager;
import org.eclipselabs.resourceselector.processor.java.JavaTypeInfo;

public class JavaResourceInfo extends JavaTypeInfo {

	/**
	 * Image corresponding to the @Program, to be displayed in the Resource
	 * Selector
	 */
	protected Image image;

	/**
	 * Image corresponding to the @Program container, to be displayed in the
	 * Resource Selector
	 */
	protected Image containerImage;
	
	/**
	 * Enumeration defining the different Java Type kinds
	 * 
	 */
	public static enum JavaTypeKind {
		CLASS, INTERFACE, ENUM;

		@Override
		public String toString() {
			String name = super.toString();
			return name.substring(0, 1).toUpperCase().concat(name.substring(1).toLowerCase());
		}
	}

	/**
	 * Enumeration defining the different Java Type possible visibilities
	 * 
	 */
	public static enum JavaTypeVisibility {
		PUBLIC, PRIVATE, PROTECTED, PACKAGE;

		@Override
		public String toString() {
			if (this.equals(PACKAGE))
				return "";
			return super.toString().toLowerCase();
		}
	}

	/**
	 * Location Separator Constant
	 */
	public static final String LOCATION_SEPARATOR = "|";

	/**
	 * Path Separator Constant
	 */
	public static final String PATH_SEPARATOR = "/";

	/**
	 * Package Separator Constant
	 */
	public static final String PACKAGE_SEPARATOR = ".";

	/**
	 * Type Separator Constant
	 */
	public static final String TYPE_SEPARATOR = "$";

	/**
	 * Class File Extension Constant
	 */
	private static final String CLASS_EXTENSION = ".class";

	/**
	 * Modifiers of the Java TypeInfo
	 */
	private int modifiers;

	/**
	 * Created new Java TypeInfo
	 * 
	 * @param elementName
	 *            : name of element
	 * @param packageName
	 *            : name of the package containing the element
	 * @param location
	 *            : location of the resource containing the element
	 * @param modifiers
	 *            : element's modifiers
	 */
	public JavaResourceInfo(String elementName, String packageName, String location, String[] enclosingNames, int modifiers) {
		super(elementName, packageName, location, enclosingNames, modifiers);
		this.modifiers = modifiers;
		if (location.indexOf(JavaResourceInfo.LOCATION_SEPARATOR) > -1) {
			this.additionalDescription = location.substring(0, location.indexOf(JavaResourceInfo.LOCATION_SEPARATOR));
			this.containerName = location.substring(location.indexOf(JavaResourceInfo.LOCATION_SEPARATOR) + 1).replace(
					JavaResourceInfo.PATH_SEPARATOR, JavaResourceInfo.PACKAGE_SEPARATOR);

		} else if (location.indexOf(JavaResourceInfo.PATH_SEPARATOR) > -1) {
			this.additionalDescription = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(location))
					.getProject().getName();
			this.containerName = this.packageName + JavaResourceInfo.PACKAGE_SEPARATOR + this.elementName;
		}

		if (this.containerName.indexOf(JavaResourceInfo.TYPE_SEPARATOR) > -1
				&& !this.containerName.endsWith(JavaResourceInfo.TYPE_SEPARATOR))
			this.containerName = this.containerName.substring(0, this.containerName
					.indexOf(JavaResourceInfo.TYPE_SEPARATOR));

		if (this.containerName.endsWith(JavaResourceInfo.CLASS_EXTENSION))
			this.containerName = this.containerName.substring(0, this.containerName.length()
					- JavaResourceInfo.CLASS_EXTENSION.length());

		if (!this.isInnerElement && this.containerName.contains(JavaResourceInfo.PACKAGE_SEPARATOR))
			this.containerName = this.containerName.substring(0, this.containerName
					.lastIndexOf(JavaResourceInfo.PACKAGE_SEPARATOR));

		this.image = JavaImagesManager.getImage(this);

	}

	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * @return the modifiers
	 */
	public int getModifiers() {
		return modifiers;
	}

	/**
	 * @param modifiers the modifiers to set
	 */
	public void setModifiers(int modifiers) {
		this.modifiers = modifiers;
	}

	/**
	 * @return the containerImage
	 */
	public Image getContainerImage() {
		return containerImage;
	}

	/**
	 * @param containerImage the containerImage to set
	 */
	public void setContainerImage(Image containerImage) {
		this.containerImage = containerImage;
	}

	

}