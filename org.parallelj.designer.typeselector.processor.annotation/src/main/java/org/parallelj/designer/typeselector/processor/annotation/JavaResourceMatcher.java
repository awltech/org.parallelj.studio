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

import java.util.regex.Pattern;

/**
 * Resource @Program Type allowing serializing sub filters.
 * 
 */
public class JavaResourceMatcher {

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

	public JavaResourceMatcher(String pattern) {
		this.pattern = pattern;
		this.init();
	}

	/**
	 * Initializes the Program Annotation @PARALLEJ Element Object Matcher
	 * 
	 */
	private void init() {
		String typePatternAsString = this.pattern;
		String packagePatternAsString = null;

		if (typePatternAsString.contains(".")) {
			packagePatternAsString = typePatternAsString.substring(0, typePatternAsString.lastIndexOf("."));
			typePatternAsString = typePatternAsString.substring(typePatternAsString.lastIndexOf(".") + 1);
		}

		if (packagePatternAsString != null) {
			packagePatternAsString = packagePatternAsString.replace(".", "[.]");
			packagePatternAsString = packagePatternAsString.replace("*", ".*");
			packagePatternAsString = packagePatternAsString.replace("..*", ".*");
		}
		if (!typePatternAsString.endsWith("*"))
			typePatternAsString += "*";
		typePatternAsString = typePatternAsString.replace("*", ".*");
		typePatternAsString = typePatternAsString.replace("..*", ".*");

		this.typePattern = Pattern.compile(typePatternAsString, Pattern.CASE_INSENSITIVE);
		if (packagePatternAsString != null)
			this.packagePattern = Pattern.compile(packagePatternAsString, Pattern.CASE_INSENSITIVE);

	}

	/**
	 * matches the package and the type name for the @PARALLEJ annotated class.
	 * 
	 * @param packageName
	 * @param typeName
	 * @return
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
