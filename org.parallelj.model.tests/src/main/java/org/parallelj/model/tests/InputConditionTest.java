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
package org.parallelj.model.tests;

import junit.textui.TestRunner;

import org.parallelj.model.InputCondition;
import org.parallelj.model.ParallelJFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Input Condition</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class InputConditionTest extends ConditionTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(InputConditionTest.class);
	}

	/**
	 * Constructs a new Input Condition test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InputConditionTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Input Condition test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected InputCondition getFixture() {
		return (InputCondition)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ParallelJFactory.eINSTANCE.createInputCondition());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //InputConditionTest
