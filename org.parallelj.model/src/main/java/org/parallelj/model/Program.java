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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Program</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.parallelj.model.Program#getInputCondition <em>Input Condition</em>}</li>
 *   <li>{@link org.parallelj.model.Program#getOutputCondition <em>Output Condition</em>}</li>
 *   <li>{@link org.parallelj.model.Program#getPredicates <em>Predicates</em>}</li>
 *   <li>{@link org.parallelj.model.Program#getElements <em>Elements</em>}</li>
 *   <li>{@link org.parallelj.model.Program#getData <em>Data</em>}</li>
 *   <li>{@link org.parallelj.model.Program#getCapacity <em>Capacity</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.parallelj.model.ParallelJPackage#getProgram()
 * @model
 * @generated
 */
public interface Program extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Input Condition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input Condition</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Input Condition</em>' reference.
	 * @see #setInputCondition(InputCondition)
	 * @see org.parallelj.model.ParallelJPackage#getProgram_InputCondition()
	 * @model required="true"
	 * @generated
	 */
	InputCondition getInputCondition();

	/**
	 * Sets the value of the '{@link org.parallelj.model.Program#getInputCondition <em>Input Condition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Input Condition</em>' reference.
	 * @see #getInputCondition()
	 * @generated
	 */
	void setInputCondition(InputCondition value);

	/**
	 * Returns the value of the '<em><b>Output Condition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output Condition</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output Condition</em>' reference.
	 * @see #setOutputCondition(OutputCondition)
	 * @see org.parallelj.model.ParallelJPackage#getProgram_OutputCondition()
	 * @model required="true"
	 * @generated
	 */
	OutputCondition getOutputCondition();

	/**
	 * Sets the value of the '{@link org.parallelj.model.Program#getOutputCondition <em>Output Condition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Output Condition</em>' reference.
	 * @see #getOutputCondition()
	 * @generated
	 */
	void setOutputCondition(OutputCondition value);

	/**
	 * Returns the value of the '<em><b>Predicates</b></em>' containment reference list.
	 * The list contents are of type {@link org.parallelj.model.Predicate}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Predicates</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Predicates</em>' containment reference list.
	 * @see org.parallelj.model.ParallelJPackage#getProgram_Predicates()
	 * @model containment="true"
	 * @generated
	 */
	EList<Predicate> getPredicates();

	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link org.parallelj.model.Element}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see org.parallelj.model.ParallelJPackage#getProgram_Elements()
	 * @model containment="true"
	 * @generated
	 */
	EList<Element> getElements();

	/**
	 * Returns the value of the '<em><b>Data</b></em>' containment reference list.
	 * The list contents are of type {@link org.parallelj.model.Data}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data</em>' containment reference list.
	 * @see org.parallelj.model.ParallelJPackage#getProgram_Data()
	 * @model containment="true"
	 * @generated
	 */
	EList<Data> getData();

	/**
	 * Returns the value of the '<em><b>Capacity</b></em>' attribute.
	 * The default value is <code>"-1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Capacity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Capacity</em>' attribute.
	 * @see #setCapacity(int)
	 * @see org.parallelj.model.ParallelJPackage#getProgram_Capacity()
	 * @model default="-1"
	 * @generated
	 */
	int getCapacity();

	/**
	 * Sets the value of the '{@link org.parallelj.model.Program#getCapacity <em>Capacity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Capacity</em>' attribute.
	 * @see #getCapacity()
	 * @generated
	 */
	void setCapacity(int value);

} // Program
