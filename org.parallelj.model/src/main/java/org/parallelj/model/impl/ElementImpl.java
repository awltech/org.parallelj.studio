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
package org.parallelj.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.parallelj.model.Element;
import org.parallelj.model.Link;
import org.parallelj.model.ParallelJPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.parallelj.model.impl.ElementImpl#getInputLinks <em>Input Links</em>}</li>
 *   <li>{@link org.parallelj.model.impl.ElementImpl#getOutputLinks <em>Output Links</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ElementImpl extends NamedElementImpl implements Element {
	/**
	 * The cached value of the '{@link #getInputLinks() <em>Input Links</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputLinks()
	 * @generated
	 * @ordered
	 */
	protected EList<Link> inputLinks;

	/**
	 * The cached value of the '{@link #getOutputLinks() <em>Output Links</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputLinks()
	 * @generated
	 * @ordered
	 */
	protected EList<Link> outputLinks;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ParallelJPackage.Literals.ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Link> getInputLinks() {
		if (inputLinks == null) {
			inputLinks = new EObjectWithInverseResolvingEList<Link>(Link.class, this, ParallelJPackage.ELEMENT__INPUT_LINKS, ParallelJPackage.LINK__DESTINATION);
		}
		return inputLinks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Link> getOutputLinks() {
		if (outputLinks == null) {
			outputLinks = new EObjectContainmentEList<Link>(Link.class, this, ParallelJPackage.ELEMENT__OUTPUT_LINKS);
		}
		return outputLinks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ParallelJPackage.ELEMENT__INPUT_LINKS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInputLinks()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ParallelJPackage.ELEMENT__INPUT_LINKS:
				return ((InternalEList<?>)getInputLinks()).basicRemove(otherEnd, msgs);
			case ParallelJPackage.ELEMENT__OUTPUT_LINKS:
				return ((InternalEList<?>)getOutputLinks()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ParallelJPackage.ELEMENT__INPUT_LINKS:
				return getInputLinks();
			case ParallelJPackage.ELEMENT__OUTPUT_LINKS:
				return getOutputLinks();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ParallelJPackage.ELEMENT__INPUT_LINKS:
				getInputLinks().clear();
				getInputLinks().addAll((Collection<? extends Link>)newValue);
				return;
			case ParallelJPackage.ELEMENT__OUTPUT_LINKS:
				getOutputLinks().clear();
				getOutputLinks().addAll((Collection<? extends Link>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ParallelJPackage.ELEMENT__INPUT_LINKS:
				getInputLinks().clear();
				return;
			case ParallelJPackage.ELEMENT__OUTPUT_LINKS:
				getOutputLinks().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ParallelJPackage.ELEMENT__INPUT_LINKS:
				return inputLinks != null && !inputLinks.isEmpty();
			case ParallelJPackage.ELEMENT__OUTPUT_LINKS:
				return outputLinks != null && !outputLinks.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ElementImpl
