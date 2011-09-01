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

import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;

/**
 * Defines several methods used to perform Java type checking.
 * 
 * @author Atos Worldline
 */
public class TypeChecker extends AbstractTypeChecker {

	private TypeDeclaration td = null;

	/**
	 * Constrcuct a TypeChecker with a TypeDeclaration as single parameter.
	 * 
	 * @param td
	 *            A JDT TypeDeclaration object
	 * @throws IllegalArgumentException
	 *             If {@code td} is null
	 */
	TypeChecker(TypeDeclaration td) {
		super(td);

		if (td == null) {
			throw new IllegalArgumentException(
					"Cannot create a TypeChecker with a null TypeDeclaration");
		}

		this.td = td;
	}

	/**
	 * Return true if this type is abstract.
	 * 
	 * @return true if this type is abstract, false otherwise
	 */
	public boolean isAbstract() {
		return Modifier.isAbstract(this.td.getModifiers());
	}

	/**
	 * Return true is this type is a sub type of {@code fullyQualifiedClassName}
	 * fully qualified class name.
	 * 
	 * @param fullyQualifiedClassName
	 *            Fully qualified name of the expected super class
	 * @return true is this type is a sub type of
	 *         {@code fullyQualifiedClassName} fully qualified class name, false
	 *         otherwise
	 */
	public boolean extend(String fullyQualifiedClassName) {
		if (this.td.isInterface()) {
			return this.implement(fullyQualifiedClassName);
		} else {
			Type supertype = this.td.getSuperclassType();

			return (supertype != null) ? areTypesEquals(supertype, fullyQualifiedClassName) : false;
		}
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
		List interfaces = this.td.superInterfaceTypes();

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
	 * Return the implemented interfaces number.
	 */
	public int interfacesNumber() {
		List interfaces = this.td.superInterfaceTypes();

		if (interfaces != null) {
			return interfaces.size();
		}

		return 0;
	}

	/**
	 * Return true if this type is parameterized.
	 * 
	 * @return true if this type is parameterized, false otherwise
	 */
	public boolean isParameterized() {
		return (this.td.typeParameters() != null && !this.td.typeParameters().isEmpty());
	}
}
