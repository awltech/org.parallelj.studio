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

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.parallelj.model.Data;
import org.parallelj.model.ForEachLoop;
import org.parallelj.model.ParallelJPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>For Each Loop</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.parallelj.model.impl.ForEachLoopImpl#getIterable <em>Iterable</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ForEachLoopImpl extends ProcedureImpl implements ForEachLoop {
	/**
	 * The cached value of the '{@link #getIterable() <em>Iterable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIterable()
	 * @generated
	 * @ordered
	 */
	protected Data iterable;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ForEachLoopImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ParallelJPackage.Literals.FOR_EACH_LOOP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Data getIterable() {
		if (iterable != null && iterable.eIsProxy()) {
			InternalEObject oldIterable = (InternalEObject)iterable;
			iterable = (Data)eResolveProxy(oldIterable);
			if (iterable != oldIterable) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ParallelJPackage.FOR_EACH_LOOP__ITERABLE, oldIterable, iterable));
			}
		}
		return iterable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Data basicGetIterable() {
		return iterable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIterable(Data newIterable) {
		Data oldIterable = iterable;
		iterable = newIterable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ParallelJPackage.FOR_EACH_LOOP__ITERABLE, oldIterable, iterable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ParallelJPackage.FOR_EACH_LOOP__ITERABLE:
				if (resolve) return getIterable();
				return basicGetIterable();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ParallelJPackage.FOR_EACH_LOOP__ITERABLE:
				setIterable((Data)newValue);
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
			case ParallelJPackage.FOR_EACH_LOOP__ITERABLE:
				setIterable((Data)null);
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
			case ParallelJPackage.FOR_EACH_LOOP__ITERABLE:
				return iterable != null;
		}
		return super.eIsSet(featureID);
	}

} //ForEachLoopImpl
