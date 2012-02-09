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
 * A representation of the model object '<em><b>Procedure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.parallelj.model.Procedure#getExecutable <em>Executable</em>}</li>
 *   <li>{@link org.parallelj.model.Procedure#getJoin <em>Join</em>}</li>
 *   <li>{@link org.parallelj.model.Procedure#getSplit <em>Split</em>}</li>
 *   <li>{@link org.parallelj.model.Procedure#getCapacity <em>Capacity</em>}</li>
 *   <li>{@link org.parallelj.model.Procedure#getExecutionMode <em>Execution Mode</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.parallelj.model.ParallelJPackage#getProcedure()
 * @model
 * @generated
 */
public interface Procedure extends Element {
	/**
	 * Returns the value of the '<em><b>Executable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Executable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Executable</em>' attribute.
	 * @see #setExecutable(String)
	 * @see org.parallelj.model.ParallelJPackage#getProcedure_Executable()
	 * @model
	 * @generated
	 */
	String getExecutable();

	/**
	 * Sets the value of the '{@link org.parallelj.model.Procedure#getExecutable <em>Executable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Executable</em>' attribute.
	 * @see #getExecutable()
	 * @generated
	 */
	void setExecutable(String value);

	/**
	 * Returns the value of the '<em><b>Join</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * The literals are from the enumeration {@link org.parallelj.model.JoinType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Join</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Join</em>' attribute.
	 * @see org.parallelj.model.JoinType
	 * @see #setJoin(JoinType)
	 * @see org.parallelj.model.ParallelJPackage#getProcedure_Join()
	 * @model default=""
	 * @generated
	 */
	JoinType getJoin();

	/**
	 * Sets the value of the '{@link org.parallelj.model.Procedure#getJoin <em>Join</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Join</em>' attribute.
	 * @see org.parallelj.model.JoinType
	 * @see #getJoin()
	 * @generated
	 */
	void setJoin(JoinType value);

	/**
	 * Returns the value of the '<em><b>Split</b></em>' attribute.
	 * The literals are from the enumeration {@link org.parallelj.model.SplitType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Split</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Split</em>' attribute.
	 * @see org.parallelj.model.SplitType
	 * @see #setSplit(SplitType)
	 * @see org.parallelj.model.ParallelJPackage#getProcedure_Split()
	 * @model
	 * @generated
	 */
	SplitType getSplit();

	/**
	 * Sets the value of the '{@link org.parallelj.model.Procedure#getSplit <em>Split</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Split</em>' attribute.
	 * @see org.parallelj.model.SplitType
	 * @see #getSplit()
	 * @generated
	 */
	void setSplit(SplitType value);

	/**
	 * Returns the value of the '<em><b>Capacity</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Capacity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Capacity</em>' attribute.
	 * @see #setCapacity(int)
	 * @see org.parallelj.model.ParallelJPackage#getProcedure_Capacity()
	 * @model default="1"
	 * @generated
	 */
	int getCapacity();

	/**
	 * Sets the value of the '{@link org.parallelj.model.Procedure#getCapacity <em>Capacity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Capacity</em>' attribute.
	 * @see #getCapacity()
	 * @generated
	 */
	void setCapacity(int value);

	/**
	 * Returns the value of the '<em><b>Execution Mode</b></em>' attribute.
	 * The literals are from the enumeration {@link org.parallelj.model.ExecutionMode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution Mode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution Mode</em>' attribute.
	 * @see org.parallelj.model.ExecutionMode
	 * @see #setExecutionMode(ExecutionMode)
	 * @see org.parallelj.model.ParallelJPackage#getProcedure_ExecutionMode()
	 * @model
	 * @generated
	 */
	ExecutionMode getExecutionMode();

	/**
	 * Sets the value of the '{@link org.parallelj.model.Procedure#getExecutionMode <em>Execution Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Execution Mode</em>' attribute.
	 * @see org.parallelj.model.ExecutionMode
	 * @see #getExecutionMode()
	 * @generated
	 */
	void setExecutionMode(ExecutionMode value);

} // Procedure
