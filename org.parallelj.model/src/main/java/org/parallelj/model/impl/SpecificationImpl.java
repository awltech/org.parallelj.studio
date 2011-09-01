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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.parallelj.model.MetaInformationContainer;
import org.parallelj.model.ParallelJPackage;
import org.parallelj.model.Program;
import org.parallelj.model.Specification;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.parallelj.model.impl.SpecificationImpl#getPrograms <em>Programs</em>}</li>
 *   <li>{@link org.parallelj.model.impl.SpecificationImpl#getMetaInformationContainer <em>Meta Information Container</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SpecificationImpl extends EObjectImpl implements Specification {
	/**
	 * The cached value of the '{@link #getPrograms() <em>Programs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrograms()
	 * @generated
	 * @ordered
	 */
	protected EList<Program> programs;

	/**
	 * The cached value of the '{@link #getMetaInformationContainer() <em>Meta Information Container</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetaInformationContainer()
	 * @generated
	 * @ordered
	 */
	protected MetaInformationContainer metaInformationContainer;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SpecificationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ParallelJPackage.Literals.SPECIFICATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Program> getPrograms() {
		if (programs == null) {
			programs = new EObjectContainmentEList<Program>(Program.class, this, ParallelJPackage.SPECIFICATION__PROGRAMS);
		}
		return programs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetaInformationContainer getMetaInformationContainer() {
		return metaInformationContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMetaInformationContainer(MetaInformationContainer newMetaInformationContainer, NotificationChain msgs) {
		MetaInformationContainer oldMetaInformationContainer = metaInformationContainer;
		metaInformationContainer = newMetaInformationContainer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ParallelJPackage.SPECIFICATION__META_INFORMATION_CONTAINER, oldMetaInformationContainer, newMetaInformationContainer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMetaInformationContainer(MetaInformationContainer newMetaInformationContainer) {
		if (newMetaInformationContainer != metaInformationContainer) {
			NotificationChain msgs = null;
			if (metaInformationContainer != null)
				msgs = ((InternalEObject)metaInformationContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ParallelJPackage.SPECIFICATION__META_INFORMATION_CONTAINER, null, msgs);
			if (newMetaInformationContainer != null)
				msgs = ((InternalEObject)newMetaInformationContainer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ParallelJPackage.SPECIFICATION__META_INFORMATION_CONTAINER, null, msgs);
			msgs = basicSetMetaInformationContainer(newMetaInformationContainer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ParallelJPackage.SPECIFICATION__META_INFORMATION_CONTAINER, newMetaInformationContainer, newMetaInformationContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ParallelJPackage.SPECIFICATION__PROGRAMS:
				return ((InternalEList<?>)getPrograms()).basicRemove(otherEnd, msgs);
			case ParallelJPackage.SPECIFICATION__META_INFORMATION_CONTAINER:
				return basicSetMetaInformationContainer(null, msgs);
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
			case ParallelJPackage.SPECIFICATION__PROGRAMS:
				return getPrograms();
			case ParallelJPackage.SPECIFICATION__META_INFORMATION_CONTAINER:
				return getMetaInformationContainer();
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
			case ParallelJPackage.SPECIFICATION__PROGRAMS:
				getPrograms().clear();
				getPrograms().addAll((Collection<? extends Program>)newValue);
				return;
			case ParallelJPackage.SPECIFICATION__META_INFORMATION_CONTAINER:
				setMetaInformationContainer((MetaInformationContainer)newValue);
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
			case ParallelJPackage.SPECIFICATION__PROGRAMS:
				getPrograms().clear();
				return;
			case ParallelJPackage.SPECIFICATION__META_INFORMATION_CONTAINER:
				setMetaInformationContainer((MetaInformationContainer)null);
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
			case ParallelJPackage.SPECIFICATION__PROGRAMS:
				return programs != null && !programs.isEmpty();
			case ParallelJPackage.SPECIFICATION__META_INFORMATION_CONTAINER:
				return metaInformationContainer != null;
		}
		return super.eIsSet(featureID);
	}

} //SpecificationImpl
