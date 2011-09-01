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
package org.parallelj.common.jdt.mergers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.parallelj.common.jdt.mergers.Tools.INPUT_TEST_PATH;
import static org.parallelj.common.jdt.mergers.Tools.OUTPUT_TEST_PATH;
import static org.parallelj.common.jdt.mergers.Tools.getFileContent;

import org.junit.Test;
import org.parallelj.common.jdt.checkers.EnumConstantChecker;
import org.parallelj.common.jdt.checkers.EnumTypeChecker;
import org.parallelj.common.jdt.checkers.FieldChecker;
import org.parallelj.common.jdt.checkers.JavaCodeChecker;
import org.parallelj.common.jdt.checkers.MethodChecker;
import org.parallelj.common.jdt.checkers.TypeChecker;
import org.parallelj.common.jdt.mergers.JavaCodeMerger;

public class InnerClassMergerTestCase {


	/**
	 * Test not generated inner type merge with another generated inner type.
	 */
	@Test
	public void testNotGeneratedGeneratedInnerTypeMerge() {
		String resultContent = null;

		// Read initial content, final content and call Java merger
		try {
			resultContent = new JavaCodeMerger().merge(getFileContent(INPUT_TEST_PATH
					+ "InnerTypeTestClass.java"), getFileContent(OUTPUT_TEST_PATH
					+ "InnerTypeTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Check the result of the merge process
		JavaCodeChecker jcc = new JavaCodeChecker(resultContent);
		String typeName = "InnerTypeTestClass";
		String innerTypeName = "MyInnerType2";
		TypeChecker parentTc = (TypeChecker) jcc.getTypeChecker(typeName);
		TypeChecker tc = (TypeChecker) parentTc.getInnerTypeChecker(innerTypeName);

		// Test type javadoc availability
		assertTrue(typeName + " doesn't contain javadoc", tc.containsJavadoc());

		// Test type final modifier
		assertTrue(typeName + " is not final", tc.isFinal());

		// Test type java.util.ArrayList extension
		assertTrue(typeName + " doesn't extend java.util.ArrayList", tc
				.extend("java.util.ArrayList"));

		// Test interface java.io.Serializable implementation
		assertFalse(typeName + " implements java.io.Serializable", tc
				.implement("java.io.Serializable"));
	}

	/**
	 * Test not existing inner type merge with a generated inner type.
	 */
	@Test
	public void testNotExistingGeneratedInnerTypeMerge() {
		String resultContent = null;

		// Read initial content, final content and call Java merger
		try {
			resultContent = new JavaCodeMerger().merge(getFileContent(INPUT_TEST_PATH
					+ "InnerTypeTestClass.java"), getFileContent(OUTPUT_TEST_PATH
					+ "InnerTypeTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Check the result of the merge process
		JavaCodeChecker jcc = new JavaCodeChecker(resultContent);
		String typeName = "InnerTypeTestClass";
		String innerTypeName = "MyInnerType3";
		TypeChecker parentTc = (TypeChecker) jcc.getTypeChecker(typeName);
		TypeChecker tc = (TypeChecker) parentTc.getInnerTypeChecker(innerTypeName);

		// Test type javadoc availability
		assertTrue(typeName + " doesn't contain javadoc", tc.containsJavadoc());

		// Test type final modifier
		assertTrue(typeName + " is not final", tc.isFinal());

		// Test type single member annotation availability
		assertTrue(
				typeName
						+ " doesn't contain single member annotation javax.annotation.Generated(\"XA Designer\")",
				tc.isAnnotated("javax.annotation.Generated", "XA Designer"));
	}


	/**
	 * Test not generated inner type merge with a not existing inner type.
	 */
	@Test
	public void testNotGeneratedNotExistingInnerTypeMerge() {
		String resultContent = null;

		// Read initial content, final content and call Java merger
		try {
			resultContent = new JavaCodeMerger().merge(getFileContent(INPUT_TEST_PATH
					+ "InnerTypeTestClass.java"), getFileContent(OUTPUT_TEST_PATH
					+ "InnerTypeTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Check the result of the merge process
		JavaCodeChecker jcc = new JavaCodeChecker(resultContent);
		String typeName = "InnerTypeTestClass";
		String innerTypeName = "MyInnerType5";
		TypeChecker parentTc = (TypeChecker) jcc.getTypeChecker(typeName);
		TypeChecker tc = (TypeChecker) parentTc.getInnerTypeChecker(innerTypeName);

		// Test type abstract modifier
		assertTrue(typeName + " is not abstract", tc.isAbstract());

		// Test type single member annotation availability
		assertFalse(typeName
				+ " contains single member annotation javax.annotation.Generated(\"XA Designer\")",
				tc.isAnnotated("javax.annotation.Generated", "XA Designer"));

		// Test interface java.io.Serializable implementation
		assertTrue(typeName + " doesn't implement java.io.Serializable", tc
				.implement("java.io.Serializable"));
	}

	/**
	 * Test not generated inner type merge with a not generated inner type.
	 */
	@Test
	public void testNotGeneratedNotGeneratedInnerTypeMerge() {
		String resultContent = null;

		// Read initial content, final content and call Java merger
		try {
			resultContent = new JavaCodeMerger().merge(getFileContent(INPUT_TEST_PATH
					+ "InnerTypeTestClass.java"), getFileContent(OUTPUT_TEST_PATH
					+ "InnerTypeTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Check the result of the merge process
		JavaCodeChecker jcc = new JavaCodeChecker(resultContent);
		String typeName = "InnerTypeTestClass";
		String innerTypeName = "MyInnerType6";
		TypeChecker parentTc = (TypeChecker) jcc.getTypeChecker(typeName);
		TypeChecker tc = (TypeChecker) parentTc.getInnerTypeChecker(innerTypeName);

		// Test type javadoc availability
		assertTrue(typeName + " doesn't contain javadoc", tc.containsJavadoc());

		// Test type abstract modifier
		assertTrue(typeName + " is not abstract", tc.isAbstract());

		// Test interface java.io.Serializable implementation
		assertTrue(typeName + " doesn't implement java.io.Serializable", tc
				.implement("java.io.Serializable"));

		// Test interface java.lang.Cloneable implementation
		assertFalse(typeName + " implements java.lang.Cloneable", tc
				.implement("java.lang.Cloneable"));
	}

	/**
	 * Test not existing inner type merge with a not generated inner type.
	 */
	@Test
	public void testNotExistingNotGeneratedInnerTypeMerge() {
		String resultContent = null;

		// Read initial content, final content and call Java merger
		try {
			resultContent = new JavaCodeMerger().merge(getFileContent(INPUT_TEST_PATH
					+ "InnerTypeTestClass.java"), getFileContent(OUTPUT_TEST_PATH
					+ "InnerTypeTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Check the result of the merge process
		JavaCodeChecker jcc = new JavaCodeChecker(resultContent);
		String typeName = "InnerTypeTestClass";
		String innerTypeName = "MyInnerType7";
		TypeChecker parentTc = (TypeChecker) jcc.getTypeChecker(typeName);
		TypeChecker tc = (TypeChecker) parentTc.getInnerTypeChecker(innerTypeName);

		// Test inner type MyInnerType7 availability
		assertTrue(typeName + " doesn't contain inner type MyInnerType7", parentTc
				.containsInnerType("MyInnerType7"));

		// Test type javadoc availability
		assertTrue(typeName + " doesn't contain javadoc", tc.containsJavadoc());

		// Test interface java.lang.Cloneable implementation
		assertTrue(typeName + " doesn't implement java.lang.Cloneable", tc
				.implement("java.lang.Cloneable"));
	}


}
