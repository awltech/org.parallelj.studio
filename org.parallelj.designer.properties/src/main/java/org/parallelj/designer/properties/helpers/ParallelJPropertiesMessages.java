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
package org.parallelj.designer.properties.helpers;

import java.util.Locale;
import java.util.ResourceBundle;

import org.parallelj.designer.properties.Activator;

public enum ParallelJPropertiesMessages {

	help_color, help_name, help_description, help_type, help_capacity, help_executable, help_join, help_split, help_iterable, help_outputlinks, help_procedures, help_predicate,

	element_capacity, element_color, element_description, element_executable, element_iterable, element_join, element_name, element_outputlinks, element_predicate, element_procedures, element_split, element_type,

	button_propagate, button_create, button_select, button_up, button_down, button_remove, button_add,

	tooltip_and_join, tooltip_or_join, tooltip_xor_join, tooltip_and_split, tooltip_or_split, tooltip_xor_split, tooltip_selected_color, tooltip_color_disabled,

	pattern_link,

	error_cast_int

	;

	/*
	 * Internal Resource Bundle instance
	 */
	private static ResourceBundle resourceBundle;

	/*
	 * Static initialization
	 */
	static {
		ParallelJPropertiesMessages.resourceBundle = ResourceBundle.getBundle(
				"ui-messages", Locale.getDefault());
	}

	/**
	 * Returns the message that underlies the current Message Key
	 * 
	 * @return String
	 */
	public String message() {
		if (ParallelJPropertiesMessages.resourceBundle == null)
			return this.toString();
		String key = this.toString();
		String message = ParallelJPropertiesMessages.resourceBundle
				.getString(key);
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
		if (ParallelJPropertiesMessages.resourceBundle == null)
			return this.toString();
		String key = this.toString();
		String message = ParallelJPropertiesMessages.resourceBundle
				.getString(key);
		try {
			return message != null ? String.format(message, varargs) : key;
		} catch (Exception e) {
			Activator.getDefault().logError(
					"An exception occurred while trying to format Message with key: "
							+ key, e);
		}
		return key;
	}

}
