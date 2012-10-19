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
package org.parallelj.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>For Each Loop</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.parallelj.model.ForEachLoop#getIterable <em>Iterable</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.parallelj.model.ParallelJPackage#getForEachLoop()
 * @model
 * @generated
 */
public interface ForEachLoop extends Procedure {
	/**
	 * Returns the value of the '<em><b>Iterable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Iterable</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Iterable</em>' reference.
	 * @see #setIterable(Data)
	 * @see org.parallelj.model.ParallelJPackage#getForEachLoop_Iterable()
	 * @model required="true"
	 * @generated
	 */
	Data getIterable();

	/**
	 * Sets the value of the '{@link org.parallelj.model.ForEachLoop#getIterable <em>Iterable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Iterable</em>' reference.
	 * @see #getIterable()
	 * @generated
	 */
	void setIterable(Data value);

} // ForEachLoop
