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

import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getField;
import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getFieldsNames;
import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getInnerType;
import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getMethod;
import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getMethodsNames;

import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.parallelj.common.jdt.helpers.JavaCodeHelper;

/**
 * Defines several methods used to perform abstract type checking.
 * 
 * @author Atos Worldline
 */
public class AbstractTypeChecker extends ElementChecker {

	private AbstractTypeDeclaration td = null;

	/**
	 * Constrcuct a TypeChecker with an AbstractTypeDeclaration as single
	 * parameter.
	 * 
	 * @param td
	 *            A JDT AbstractTypeDeclaration object
	 * @throws IllegalArgumentException
	 *             If {@code td} is null
	 */
	AbstractTypeChecker(AbstractTypeDeclaration td) {
		super(td);

		if (td == null) {
			throw new IllegalArgumentException(
					"Cannot create a TypeChecker with a null TypeDeclaration");
		}

		this.td = td;
	}

	/**
	 * Return true if this type contains a field with {@code fieldName} as name.
	 * 
	 * @param fieldName
	 *            The name of the field
	 * @return true if this type contains this field, false otherwise
	 */
	public boolean containsField(String fieldName) {
		return ((getField(this.td, fieldName)) != null);
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
	public FieldChecker getFieldChecker(String fieldName) {
		return new FieldChecker(getField(this.td, fieldName));
	}

	/**
	 * Return true if this type contains a method named {@code methodName}.
	 * 
	 * @param methodName
	 *            Expected method name
	 * @return true if this type contains this method, false otherwise
	 */
	public boolean containsMethod(String methodName) {
		return ((getMethod(this.td, methodName, (String[]) null)) != null);
	}

	/**
	 * Return true if type contains a method named {@code methodName} and
	 * {@code argumentsTypes} as arguments type fully qualified name list.
	 * 
	 * @param methodName
	 *            Expected method name
	 * @param argumentsTypes
	 *            Expected arguments type fully qualified name list
	 * @return true if this type contains this method, false otherwise
	 */
	public boolean containsMethod(String methodName, String... argumentsTypes) {
		return (getMethod(this.td, methodName, argumentsTypes) != null);
	}

	/**
	 * Return a MethodChecker instance if this type contains a method named
	 * {@code methodName}.
	 * 
	 * @param methodName
	 *            Expected method name
	 * @return a MethodChecker instance if this type contains a method named
	 *         {@code methodName}
	 * @throws IllegalArgumentException
	 *             If method named {@code methodName} cannot be found
	 */
	public MethodChecker getMethodChecker(String methodName) {
		return new MethodChecker(getMethod(this.td, methodName, (String[]) null));
	}

	/**
	 * Return a MethodChecker instance if this type contains a method named
	 * {@code methodName} and {@code argumentsTypes} as arguments type fully
	 * qualified name list.
	 * 
	 * @param methodName
	 *            Expected method name
	 * @param argumentsTypes
	 *            Expected arguments type fully qualified name list
	 * @return a MethodChecker instance if this type contains a method named
	 *         {@code methodName}
	 * @throws IllegalArgumentException
	 *             If method named {@code methodName} cannot be found
	 */
	public MethodChecker getMethodChecker(String methodName, String... argumentsTypes) {
		return new MethodChecker(getMethod(this.td, methodName, argumentsTypes));
	}

	public int getMethodsCount(String methodName) {
		return JavaCodeHelper.getMethods(this.td, methodName, (String[]) null).size();
	}
	
	/**
	 * Return true if this java code contains the inner type with innerTypeName
	 * as name
	 * 
	 * @param innerTypeName
	 *            The name of the inner type
	 * @return True if this java code contains this inner type, false otherwise
	 */
	public boolean containsInnerType(String innerTypeName) {
		return ((getInnerType(this.td, innerTypeName)) != null);
	}

	/**
	 * Return a AbstractTypeChecker instance if this type contains an inner type
	 * named {@code innerTypeName}.
	 * 
	 * @param innerTypeName
	 *            Expected inner type name
	 * @return a AbstractTypeChecker instance if this type contains an inner
	 *         type named {@code innerTypeName}
	 * @throws IllegalArgumentException
	 *             If inner type named {@code innerTypeName} cannot be found
	 */
	public AbstractTypeChecker getInnerTypeChecker(String innerTypeName) {
		AbstractTypeDeclaration atd = getInnerType(this.td, innerTypeName);

		if (atd instanceof TypeDeclaration) {
			return new TypeChecker((TypeDeclaration) atd);
		} else if (atd instanceof EnumDeclaration) {
			return new EnumTypeChecker((EnumDeclaration) atd);
		} else if (atd instanceof AnnotationTypeDeclaration) {
			//TODO
		}

		return null;
	}

	/**
	 * Check fields order. Throw an IllegalStateException if this order is
	 * false.
	 * 
	 * @param fields
	 *            Fields list as variable arguments list
	 * @throws IllegalStateException
	 *             if this order is false
	 */
	public void checkFieldsOrder(String... fields) throws IllegalArgumentException {
		if (fields == null) {
			return;
		}

		String[] fieldsNames = getFieldsNames(this.td);

		// Check if fields number is right
		if ((fieldsNames == null && fields.length > 0) || (fieldsNames.length != fields.length)) {
			throw new IllegalStateException("Fields number is wrong");
		}

		int inc = 0;

		// Check if fields order is right
		for (String fieldName : fields) {
			if (!fieldName.equals(fieldsNames[inc++])) {
				throw new IllegalStateException(fieldName + " is misplaced");
			}
		}
	}

	/**
	 * Check the methods order. Throw an IllegalStateException if this order is
	 * false.
	 * 
	 * @param methods
	 *            Methods list as variable arguments list
	 * @throws IllegalStateException
	 *             if this order is false
	 */
	public void checkMethodsOrder(String... methods) throws IllegalArgumentException {
		if (methods == null) {
			return;
		}

		String[] methodsNames = getMethodsNames(this.td);

		// Check if methods number is right
		if ((methodsNames == null && methods.length > 0) || (methodsNames.length != methods.length)) {
			throw new IllegalStateException("Methods number is wrong");
		}

		int inc = 0;

		// Check if methods order is right
		for (String methodName : methods) {
			if (!methodName.equals(methodsNames[inc++])) {
				throw new IllegalStateException(methodName + " is misplaced");
			}
		}
	}
}
