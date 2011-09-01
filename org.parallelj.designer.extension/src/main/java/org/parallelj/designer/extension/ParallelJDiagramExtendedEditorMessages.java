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

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Enumeration Messages, for ParallelJ Designer Extension Internationalization
 * 
 * @author mvanbesien
 * 
 */
public enum ParallelJDiagramExtendedEditorMessages {

	NULL_CONTRIB_EXTPOINT, EXCEPTION_IN_CONTRIB_LOAD;

	/*
	 * Resource Bundle Name
	 */
	private static final String RESOURCE_BUNDLE_NAME = "ParallelJDiagramExtendedEditorMessages";

	/*
	 * Static ResourceBundle instance
	 */
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle(
			ParallelJDiagramExtendedEditorMessages.RESOURCE_BUNDLE_NAME,
			Locale.getDefault());

	/**
	 * Returns message, as it is available in resource bundle. If resource
	 * bundle cannot be loaded, or message doesn't exist, returns key as String.
	 * 
	 * @return message
	 */
	public String getMessage() {
		if (ParallelJDiagramExtendedEditorMessages.resourceBundle == null) {
			return this.toString();
		}
		String message = ParallelJDiagramExtendedEditorMessages.resourceBundle
				.getString(this.toString());
		return message == null ? this.toString() : message;
	}

	/**
	 * Returns formatted message (according to the input parameters), as it is
	 * available in resource bundle. If resource bundle cannot be loaded, or
	 * message doesn't exist, returns key as String.
	 * 
	 * @return formatted message
	 */
	public String getMessage(Object... varargs) {
		if (ParallelJDiagramExtendedEditorMessages.resourceBundle == null) {
			return this.toString();
		}
		String message = ParallelJDiagramExtendedEditorMessages.resourceBundle
				.getString(this.toString());
		return message == null ? this.toString() : String.format(message,
				varargs);
	}

}
