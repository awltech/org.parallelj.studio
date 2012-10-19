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
 * A representation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.parallelj.model.Element#getInputLinks <em>Input Links</em>}</li>
 *   <li>{@link org.parallelj.model.Element#getOutputLinks <em>Output Links</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.parallelj.model.ParallelJPackage#getElement()
 * @model
 * @generated
 */
public interface Element extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Input Links</b></em>' reference list.
	 * The list contents are of type {@link org.parallelj.model.Link}.
	 * It is bidirectional and its opposite is '{@link org.parallelj.model.Link#getDestination <em>Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input Links</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Input Links</em>' reference list.
	 * @see org.parallelj.model.ParallelJPackage#getElement_InputLinks()
	 * @see org.parallelj.model.Link#getDestination
	 * @model opposite="destination"
	 * @generated
	 */
	EList<Link> getInputLinks();

	/**
	 * Returns the value of the '<em><b>Output Links</b></em>' containment reference list.
	 * The list contents are of type {@link org.parallelj.model.Link}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output Links</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output Links</em>' containment reference list.
	 * @see org.parallelj.model.ParallelJPackage#getElement_OutputLinks()
	 * @model containment="true"
	 * @generated
	 */
	EList<Link> getOutputLinks();

} // Element
