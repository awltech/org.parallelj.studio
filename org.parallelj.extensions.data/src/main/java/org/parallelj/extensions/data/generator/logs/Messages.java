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

import java.util.Locale;
import java.util.ResourceBundle;

import org.parallelj.extensions.data.generator.Activator;

/**
 * Enumeration that defines all the GUI & User-dedicated messaging system Add a
 * key on this class for each internationalized message you want to add to your
 * application. The value for this key is located in
 * src/main/resources/message[Local].properties.
 * 
 * @author XA Toolbox
 * @version 1.0
 * 
 */
public enum Messages {

	// Messages related to the Logger configuration
	LOG01E, LOG02I, LOG03W,

	// Messages related to the Console Manager
	CONSOLE_NAME, COM01E,

	// Messages related to wizard
	INVALID_XMLPACKAGE_ERROR, XMLHELPER_GENERATION_INFO, JAXBANNOTED_POJO_INFO, EXISTINGJAXB_ANNOTED_POJO_INFO, NOEXISTING_JAXBPOJO_ERROR,

	PACKAGE_TEXT_ERROR, SELECT_FILE_ERROR, SELECT_PROJECT_ERROR, ACTION_ERROR, PACKAGE_DIALOG_LABEL, PACKAGE_DIALOG_TITLE,

	JAVA_GENERATION_LABEL, PACKAGE_LABEL, PACKAGE_SELECT_BUTTON, PARALLELJ_TITLE, OPTION_LABEL,

	// Messages related to job
	JOB01I, JOB02I, JOB03I, JOB04I, JOB05I, JOB01E

	;

	/**
	 * Internal Resource Bundle instance
	 */
	private static ResourceBundle resourceBundle;

	static {
		Messages.resourceBundle = ResourceBundle.getBundle("messages",
				Locale.getDefault());
	}

	/**
	 * Returns the message that underlies the current Message Key
	 * 
	 * @return String
	 */
	public String message() {
		if (Messages.resourceBundle == null)
			return this.toString();
		String key = this.toString();
		String message = Messages.resourceBundle.getString(key);
		return message != null ? message : key;
	}

	/**
	 * Returns the message that underlies the current Message Key, formatted
	 * with input parameters
	 * 
	 * @param varargs
	 *            Input Parameter
	 * @return String
	 */
	public String message(Object... varargs) {
		if (Messages.resourceBundle == null)
			return this.toString();
		String key = this.toString();
		String message = Messages.resourceBundle.getString(key);
		try {
			return message != null ? String.format(message, varargs) : key;
		} catch (Exception e) {
			Activator.sendErrorToErrorLog(
					"An exception occurred while trying to format Message with key: "
							+ key, e);
		}
		return key;
	}

}
