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
package org.parallelj.common.jdt.checkers;

import static org.parallelj.common.jdt.helpers.JavaCodeHelper.areTypesEquals;
import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getEnumConstant;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.Type;

/**
 * Defines several methods used to perform Java enumeration checking.
 * 
 * @author Atos Worldline
 */
public class EnumTypeChecker extends AbstractTypeChecker {

	private EnumDeclaration ed = null;

	/**
	 * Construct a EnumTypeChecker with a EnumDeclaration as single parameter.
	 * 
	 * @param td
	 *            A JDT TypeDeclaration object
	 * @throws IllegalArgumentException
	 *             If {@code td} is null
	 */
	EnumTypeChecker(EnumDeclaration ed) {
		super(ed);

		if (ed == null) {
			throw new IllegalArgumentException(
					"Cannot create a EnumTypeChecker with a null EnumDeclaration");
		}

		this.ed = ed;
	}

	/**
	 * Return true is this type implements {@code fullyQualifiedInterfaceName}
	 * fully qualified interface name.
	 * 
	 * @param fullyQualifiedInterfaceName
	 *            Fully qualified name of the expected interface
	 * @return true is this type implements {@code fullyQualifiedInterfaceName}
	 *         fully qualified interface name, false otherwise
	 */
	public boolean implement(String fullyQualifiedInterfaceName) {
		List interfaces = this.ed.superInterfaceTypes();

		if (interfaces != null) {
			boolean interfaceFound = false;
			Iterator<Type> implementedTypes = interfaces.iterator();

			while (!interfaceFound && implementedTypes.hasNext()) {
				interfaceFound = areTypesEquals(implementedTypes.next(),
						fullyQualifiedInterfaceName);
			}

			return interfaceFound;
		} else {
			return false;
		}
	}

	/**
	 * Return true if this enum contains a constant with {@code constantName} as
	 * name.
	 * 
	 * @param constantName
	 *            The name of the constant
	 * @return true if this enum contains this constant, false otherwise
	 */
	public boolean containsConstant(String constantName) {
		return ((getEnumConstant(this.ed, constantName)) != null);
	}

	/**
	 * Return a FieldChecker instance if this type contains a field named
	 * {@code fieldName}.
	 * 
	 * @param fieldName
	 *            Expected field name
	 * @return a FieldChecker instance if this type contains a field named
	 *         {@code typeName}
	 * @throws IllegalArgumentException
	 *             If field named {@code fieldName} cannot be found
	 */
	public EnumConstantChecker getConstantChecker(String constantName) {
		return new EnumConstantChecker(getEnumConstant(this.ed, constantName));
	}
}
