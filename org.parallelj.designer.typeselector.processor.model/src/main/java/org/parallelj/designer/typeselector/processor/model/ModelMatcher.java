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
package org.parallelj.designer.typeselector.processor.model;

import java.util.regex.Pattern;

/**
 * Tool class to perform pattern matching on Program Model Element Object
 * 
 */

public class ModelMatcher {

	/**
	 * Initial Patterns as String 
	 */
	private String pattern;

	/**
	 * Pattern dedicated to Type names.
	 */
	private Pattern typePattern;

	/**
	 * Pattern dedicated to Types' Package names
	 */
	private Pattern packagePattern;

	/**
	 * Creates a new Instance of Program Model Element Object Matcher from the
	 * pattern passed as parameter
	 * 
	 * @param pattern
	 *            : Pattern as String
	 */
	public ModelMatcher(String pattern) {
		this.pattern = pattern;
		this.init();
	}

	/**
	 * Initializes the Program Model Element Object Matcher by parsing the
	 * String pattern
	 * 
	 */
	private void init() {
		String typePatternAsString = this.pattern;
		String packagePatternAsString = null;

		if (typePatternAsString.contains(".")) {
			packagePatternAsString = typePatternAsString.substring(0,
					typePatternAsString.lastIndexOf("."));
			typePatternAsString = typePatternAsString
					.substring(typePatternAsString.lastIndexOf(".") + 1);
		}

		if (packagePatternAsString != null) {
			packagePatternAsString = packagePatternAsString.replace(".", "[.]");
			packagePatternAsString = packagePatternAsString.replace("*", ".*");
			packagePatternAsString = packagePatternAsString
					.replace("..*", ".*");
		}
		if (!typePatternAsString.endsWith("*"))
			typePatternAsString += "*";
		typePatternAsString = typePatternAsString.replace("*", ".*");
		typePatternAsString = typePatternAsString.replace("..*", ".*");

		this.typePattern = Pattern.compile(typePatternAsString,
				Pattern.CASE_INSENSITIVE);
		if (packagePatternAsString != null)
			this.packagePattern = Pattern.compile(packagePatternAsString,
					Pattern.CASE_INSENSITIVE);

	}

	/**
	 * Tests if the names passed as parameters match with the internal Patterns.
	 * 
	 * @param packageName
	 *            : Type's Package name
	 * @param typeName
	 *            : Type name
	 * @return true if both names match, false otherwise.
	 */
	public boolean match(String packageName, String typeName) {
		if (packageName != null && this.packagePattern != null) {
			if (!this.packagePattern.matcher(packageName).matches()) {
				return false;
			} else {
				return true;
			}
		}
		if (typeName != null && this.typePattern != null) {
			if (!"".equals(typeName)) {
				if (!this.typePattern.matcher(typeName).matches())
					return false;
				else {
					return true;
				}
			}
		}
		return false;
	}

}