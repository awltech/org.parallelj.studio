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
package org.parallelj.common.jdt.checkers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;
import org.parallelj.common.jdt.checkers.JavaCodeChecker;

/**
 * Test case for org.parallelj.common.jdt.checkers.JavaCodeChecker package
 * 
 * @author Atos Worldine
 */
public class JavaCodeCheckerTestCase {

	/**
	 * Java classes input folder
	 */
	public static final String INPUT_FOLDER = "src/test/inputs";

	/**
	 * Tested class package name
	 */
	public static final String TESTED_CLASS_PACKAGE = "org.parallelj.common.jdt.checkers";

	/**
	 * Tested class simple name
	 */
	public static final String TESTED_CLASS_NAME = "JavaCodeCheckerTestCaseInput";

	/* ---------------------------------------------------------------------------------------- */
	/* ----------------------------------BEGIN_TEST_METHODS------------------------------------ */
	/* ---------------------------------------------------------------------------------------- */

	/**
	 * Test JavaCodeChecker instanciation with java source code as input
	 */
	@Test
	public void jccInstanciationWithJavaSourceCodeAsInput() {
		String sourceCode = "public class MyTest {" + "	private String myProperty;"
				+ "	private void myMethod() {}" + "}";

		JavaCodeChecker jcc = new JavaCodeChecker(sourceCode);
		assertNotNull(jcc);
	}

	/**
	 * Test JavaCodeChecker instanciation with a package name and a class name
	 */
	@Test
	public void jccInstanciationWithPackageNameAndClassName() {
		JavaCodeChecker jcc = null;

		try {
			jcc = this.createAJavaCodeCheckerFromAPackageNameAndAClassName();
		} catch (IOException ioe) {
			fail(ioe.getMessage());
		}

		assertNotNull(jcc);
	}

	/**
	 * Test java code errors
	 */
	@Test
	public void testJavaCodeErrors() {
		JavaCodeChecker jcc = null;

		try {
			jcc = this.createAJavaCodeCheckerFromAPackageNameAndAClassName();
		} catch (IOException ioe) {
			fail(ioe.getMessage());
		}

		assertEquals("JavaCodeCheckerTestCaseInput must not have error", 0, jcc.errorsNumber());
	}

	/**
	 * Test java imports presence
	 */
	@Test
	public void testImportsPresence() {
		JavaCodeChecker jcc = null;

		try {
			jcc = this.createAJavaCodeCheckerFromAPackageNameAndAClassName();
		} catch (IOException ioe) {
			fail(ioe.getMessage());
		}

		assertTrue("java.util.Map cannot be found as import", jcc
				.containsImport("java.util.ArrayList"));
		assertFalse("java.util.Vector must not be found as import", jcc
				.containsImport("java.util.Vector"));
	}

	/**
	 * Test java imports number
	 */
	@Test
	public void testImportsNumber() {
		JavaCodeChecker jcc = null;

		try {
			jcc = this.createAJavaCodeCheckerFromAPackageNameAndAClassName();
		} catch (IOException ioe) {
			fail(ioe.getMessage());
		}

		assertEquals("Imports number is not equal to 1", 1, jcc.importsNumber());
	}

	/**
	 * Test type
	 */
	@Test
	public void containsType() {
		JavaCodeChecker jcc = null;

		try {
			jcc = this.createAJavaCodeCheckerFromAPackageNameAndAClassName();
		} catch (IOException ioe) {
			fail(ioe.getMessage());
		}

		assertTrue("JavaCodeCheckerTestCaseInput cannot be found as type", jcc
				.containsType("JavaCodeCheckerTestCaseInput"));
		assertFalse("JavaCodeCheckerTestCaseInput must not be found as type", jcc
				.containsType("JavaCodeCheckerTestCaseInput2"));
	}

	/**
	 * Test package
	 */
	@Test
	public void isInPackage() {
		JavaCodeChecker jcc = null;

		try {
			jcc = this.createAJavaCodeCheckerFromAPackageNameAndAClassName();
		} catch (IOException ioe) {
			fail(ioe.getMessage());
		}

		assertTrue("JavaCodeCheckerTestCaseInput is in package org.parallelj.common.jdt.test", jcc
				.isInPackage("org.parallelj.common.jdt.test"));
		assertFalse(
				"JavaCodeCheckerTestCaseInput is not in package org.parallelj.common.jdt.test2",
				jcc.isInPackage("org.parallelj.common.jdt.test2"));
	}

	private JavaCodeChecker createAJavaCodeCheckerFromAPackageNameAndAClassName()
			throws IOException {
		return new JavaCodeChecker(null, INPUT_FOLDER, TESTED_CLASS_PACKAGE, TESTED_CLASS_NAME);
	}
}
