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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.parallelj.model.Data;
import org.parallelj.model.Element;
import org.parallelj.model.InputCondition;
import org.parallelj.model.OutputCondition;
import org.parallelj.model.ParallelJPackage;
import org.parallelj.model.Predicate;
import org.parallelj.model.Program;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Program</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.parallelj.model.impl.ProgramImpl#getInputCondition <em>Input Condition</em>}</li>
 *   <li>{@link org.parallelj.model.impl.ProgramImpl#getOutputCondition <em>Output Condition</em>}</li>
 *   <li>{@link org.parallelj.model.impl.ProgramImpl#getPredicates <em>Predicates</em>}</li>
 *   <li>{@link org.parallelj.model.impl.ProgramImpl#getElements <em>Elements</em>}</li>
 *   <li>{@link org.parallelj.model.impl.ProgramImpl#getData <em>Data</em>}</li>
 *   <li>{@link org.parallelj.model.impl.ProgramImpl#getCapacity <em>Capacity</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProgramImpl extends NamedElementImpl implements Program {
	/**
	 * The cached value of the '{@link #getInputCondition() <em>Input Condition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputCondition()
	 * @generated
	 * @ordered
	 */
	protected InputCondition inputCondition;

	/**
	 * The cached value of the '{@link #getOutputCondition() <em>Output Condition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputCondition()
	 * @generated
	 * @ordered
	 */
	protected OutputCondition outputCondition;

	/**
	 * The cached value of the '{@link #getPredicates() <em>Predicates</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredicates()
	 * @generated
	 * @ordered
	 */
	protected EList<Predicate> predicates;

	/**
	 * The cached value of the '{@link #getElements() <em>Elements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElements()
	 * @generated
	 * @ordered
	 */
	protected EList<Element> elements;

	/**
	 * The cached value of the '{@link #getData() <em>Data</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getData()
	 * @generated
	 * @ordered
	 */
	protected EList<Data> data;

	/**
	 * The default value of the '{@link #getCapacity() <em>Capacity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCapacity()
	 * @generated
	 * @ordered
	 */
	protected static final int CAPACITY_EDEFAULT = -1;

	/**
	 * The cached value of the '{@link #getCapacity() <em>Capacity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCapacity()
	 * @generated
	 * @ordered
	 */
	protected int capacity = CAPACITY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProgramImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ParallelJPackage.Literals.PROGRAM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InputCondition getInputCondition() {
		if (inputCondition != null && inputCondition.eIsProxy()) {
			InternalEObject oldInputCondition = (InternalEObject)inputCondition;
			inputCondition = (InputCondition)eResolveProxy(oldInputCondition);
			if (inputCondition != oldInputCondition) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ParallelJPackage.PROGRAM__INPUT_CONDITION, oldInputCondition, inputCondition));
			}
		}
		return inputCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InputCondition basicGetInputCondition() {
		return inputCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInputCondition(InputCondition newInputCondition) {
		InputCondition oldInputCondition = inputCondition;
		inputCondition = newInputCondition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ParallelJPackage.PROGRAM__INPUT_CONDITION, oldInputCondition, inputCondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutputCondition getOutputCondition() {
		if (outputCondition != null && outputCondition.eIsProxy()) {
			InternalEObject oldOutputCondition = (InternalEObject)outputCondition;
			outputCondition = (OutputCondition)eResolveProxy(oldOutputCondition);
			if (outputCondition != oldOutputCondition) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ParallelJPackage.PROGRAM__OUTPUT_CONDITION, oldOutputCondition, outputCondition));
			}
		}
		return outputCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutputCondition basicGetOutputCondition() {
		return outputCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutputCondition(OutputCondition newOutputCondition) {
		OutputCondition oldOutputCondition = outputCondition;
		outputCondition = newOutputCondition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ParallelJPackage.PROGRAM__OUTPUT_CONDITION, oldOutputCondition, outputCondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Predicate> getPredicates() {
		if (predicates == null) {
			predicates = new EObjectContainmentEList<Predicate>(Predicate.class, this, ParallelJPackage.PROGRAM__PREDICATES);
		}
		return predicates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Element> getElements() {
		if (elements == null) {
			elements = new EObjectContainmentEList<Element>(Element.class, this, ParallelJPackage.PROGRAM__ELEMENTS);
		}
		return elements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Data> getData() {
		if (data == null) {
			data = new EObjectContainmentEList<Data>(Data.class, this, ParallelJPackage.PROGRAM__DATA);
		}
		return data;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCapacity(int newCapacity) {
		int oldCapacity = capacity;
		capacity = newCapacity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ParallelJPackage.PROGRAM__CAPACITY, oldCapacity, capacity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ParallelJPackage.PROGRAM__PREDICATES:
				return ((InternalEList<?>)getPredicates()).basicRemove(otherEnd, msgs);
			case ParallelJPackage.PROGRAM__ELEMENTS:
				return ((InternalEList<?>)getElements()).basicRemove(otherEnd, msgs);
			case ParallelJPackage.PROGRAM__DATA:
				return ((InternalEList<?>)getData()).basicRemove(otherEnd, msgs);
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
			case ParallelJPackage.PROGRAM__INPUT_CONDITION:
				if (resolve) return getInputCondition();
				return basicGetInputCondition();
			case ParallelJPackage.PROGRAM__OUTPUT_CONDITION:
				if (resolve) return getOutputCondition();
				return basicGetOutputCondition();
			case ParallelJPackage.PROGRAM__PREDICATES:
				return getPredicates();
			case ParallelJPackage.PROGRAM__ELEMENTS:
				return getElements();
			case ParallelJPackage.PROGRAM__DATA:
				return getData();
			case ParallelJPackage.PROGRAM__CAPACITY:
				return getCapacity();
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
			case ParallelJPackage.PROGRAM__INPUT_CONDITION:
				setInputCondition((InputCondition)newValue);
				return;
			case ParallelJPackage.PROGRAM__OUTPUT_CONDITION:
				setOutputCondition((OutputCondition)newValue);
				return;
			case ParallelJPackage.PROGRAM__PREDICATES:
				getPredicates().clear();
				getPredicates().addAll((Collection<? extends Predicate>)newValue);
				return;
			case ParallelJPackage.PROGRAM__ELEMENTS:
				getElements().clear();
				getElements().addAll((Collection<? extends Element>)newValue);
				return;
			case ParallelJPackage.PROGRAM__DATA:
				getData().clear();
				getData().addAll((Collection<? extends Data>)newValue);
				return;
			case ParallelJPackage.PROGRAM__CAPACITY:
				setCapacity((Integer)newValue);
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
			case ParallelJPackage.PROGRAM__INPUT_CONDITION:
				setInputCondition((InputCondition)null);
				return;
			case ParallelJPackage.PROGRAM__OUTPUT_CONDITION:
				setOutputCondition((OutputCondition)null);
				return;
			case ParallelJPackage.PROGRAM__PREDICATES:
				getPredicates().clear();
				return;
			case ParallelJPackage.PROGRAM__ELEMENTS:
				getElements().clear();
				return;
			case ParallelJPackage.PROGRAM__DATA:
				getData().clear();
				return;
			case ParallelJPackage.PROGRAM__CAPACITY:
				setCapacity(CAPACITY_EDEFAULT);
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
			case ParallelJPackage.PROGRAM__INPUT_CONDITION:
				return inputCondition != null;
			case ParallelJPackage.PROGRAM__OUTPUT_CONDITION:
				return outputCondition != null;
			case ParallelJPackage.PROGRAM__PREDICATES:
				return predicates != null && !predicates.isEmpty();
			case ParallelJPackage.PROGRAM__ELEMENTS:
				return elements != null && !elements.isEmpty();
			case ParallelJPackage.PROGRAM__DATA:
				return data != null && !data.isEmpty();
			case ParallelJPackage.PROGRAM__CAPACITY:
				return capacity != CAPACITY_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (capacity: ");
		result.append(capacity);
		result.append(')');
		return result.toString();
	}

} //ProgramImpl
