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
package org.parallelj.designer.validation.tools;

/**
 * Utility class for String.
 * 
 */
public class StringTool {

	private StringTool() {
	}

	/**
	 * @param stringToCheck
	 * @return true if string is null or blank
	 */
	public static boolean isEmpty(String stringToCheck) {
		return stringToCheck == null || stringToCheck.trim().length() == 0;
	}

	/**
	 * Checks passed string for pattern like alpha-numerical, java.
	 * 
	 * @param name
	 * @param isDotAllowed
	 *            for checking dot is allowed in passed name
	 * @return true if passed string qualifies for pattern
	 */
	public static boolean isNameQualified(String name, boolean isDotAllowed) {
		if (!StringTool.isEmpty(name)) {
			if (isDotAllowed) {
				return name.matches("([\\w]*+.[\\w]+)*");
			} else {
				return name.matches("[\\w]*");
			}
		}
		return true;
	}
}
