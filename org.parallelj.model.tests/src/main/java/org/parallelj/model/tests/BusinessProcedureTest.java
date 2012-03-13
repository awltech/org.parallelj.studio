/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.parallelj.model.tests;

import junit.textui.TestRunner;

import org.parallelj.model.BusinessProcedure;
import org.parallelj.model.ParallelJFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Business Procedure</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class BusinessProcedureTest extends ProcedureTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(BusinessProcedureTest.class);
	}

	/**
	 * Constructs a new Business Procedure test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BusinessProcedureTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Business Procedure test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected BusinessProcedure getFixture() {
		return (BusinessProcedure)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ParallelJFactory.eINSTANCE.createBusinessProcedure());
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

} //BusinessProcedureTest
