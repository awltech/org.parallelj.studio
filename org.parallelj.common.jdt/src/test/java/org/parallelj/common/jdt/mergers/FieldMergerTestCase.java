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
import org.parallelj.common.jdt.checkers.FieldChecker;
import org.parallelj.common.jdt.checkers.JavaCodeChecker;
import org.parallelj.common.jdt.checkers.TypeChecker;
import org.parallelj.common.jdt.mergers.JavaCodeMerger;

public class FieldMergerTestCase {

	/**
	 * Test not generated field merge with a generated field.
	 */
	@Test
	public void testNotGeneratedGeneratedFieldMerge() {
		String resultContent = null;

		// Read initial content, final content and call Java merger
		try {
			resultContent = new JavaCodeMerger().merge(getFileContent(INPUT_TEST_PATH
					+ "FieldTestClass.java"), getFileContent(OUTPUT_TEST_PATH
					+ "FieldTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Check the result of the merge process
		JavaCodeChecker jcc = new JavaCodeChecker(resultContent);
		String typeName = "FieldTestClass";
		String fieldName = "myProperty2";
		FieldChecker fc = jcc.getTypeChecker(typeName).getFieldChecker(fieldName);

		// Test field private modifier
		assertTrue(fieldName + " is not private", fc.isPrivate());

		// Test field initialization availability
		assertTrue(fieldName + " doesn't contain an initialization expression", fc.isInitialized());

		// Test field single member annotation availability
		assertFalse(fieldName
				+ " contains single member annotation javax.annotation.Generated(\"XA Designer\")",
				fc.isAnnotated("javax.annotation.Generated", "XA Designer"));
	}

	/**
	 * Test not existing field merge with a generated field.
	 */
	@Test
	public void testNotExistingGeneratedFieldMerge() {
		String resultContent = null;

		// Read initial content, final content and call Java merger
		try {
			resultContent = new JavaCodeMerger().merge(getFileContent(INPUT_TEST_PATH
					+ "FieldTestClass.java"), getFileContent(OUTPUT_TEST_PATH
					+ "FieldTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Check the result of the merge process
		JavaCodeChecker jcc = new JavaCodeChecker(resultContent);
		String typeName = "FieldTestClass";
		TypeChecker tc = (TypeChecker) jcc.getTypeChecker(typeName);

		// Test field myProperty3 availability
		assertTrue(typeName + " doesn't contain field myProperty3", tc.containsField("myProperty3"));

		String fieldName = "myProperty3";
		FieldChecker fc = jcc.getTypeChecker(typeName).getFieldChecker(fieldName);

		// Test field javadoc availability
		assertTrue(fieldName + " doesn't contain javadoc", fc.containsJavadoc());

		// Test field private modifier
		assertTrue(fieldName + " is not private", fc.isPrivate());

		// Test field type
		assertTrue(fieldName + " type is not equal to int", fc.isTyped("int"));
	}

	/**
	 * Test not generated field merge with a not existing field.
	 */
	@Test
	public void testNotGeneratedNotExistingFieldMerge() {
		String resultContent = null;

		// Read initial content, final content and call Java merger
		try {
			resultContent = new JavaCodeMerger().merge(getFileContent(INPUT_TEST_PATH
					+ "FieldTestClass.java"), getFileContent(OUTPUT_TEST_PATH
					+ "FieldTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Check the result of the merge process
		JavaCodeChecker jcc = new JavaCodeChecker(resultContent);
		String typeName = "FieldTestClass";
		String fieldName = "myProperty5";
		FieldChecker fc = jcc.getTypeChecker(typeName).getFieldChecker(fieldName);

		// Test field private modifier
		assertTrue(fieldName + " is not private", fc.isPrivate());

		// Test field type
		assertTrue(fieldName + " type is not equal to java.lang.String", fc
				.isTyped("java.lang.String"));

		// Test field marker annotation availability
		assertTrue(fieldName + " doesn't contain marker annotation javax.annotation.Resource", fc
				.isAnnotated("javax.annotation.Resource"));
	}

	/**
	 * Test not generated field merge with a not generated field.
	 */
	@Test
	public void testNotGeneratedNotGeneratedFieldMerge() {
		String resultContent = null;

		// Read initial content, final content and call Java merger
		try {
			resultContent = new JavaCodeMerger().merge(getFileContent(INPUT_TEST_PATH
					+ "FieldTestClass.java"), getFileContent(OUTPUT_TEST_PATH
					+ "FieldTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Check the result of the merge process
		JavaCodeChecker jcc = new JavaCodeChecker(resultContent);
		String typeName = "FieldTestClass";
		String fieldName = "myProperty6";
		FieldChecker fc = jcc.getTypeChecker(typeName).getFieldChecker(fieldName);

		// Test field private modifier
		assertTrue(fieldName + " is not private", fc.isPrivate());

		// Test field type
		assertTrue(fieldName + " type is not equal to java.lang.String", fc
				.isTyped("java.lang.String"));

		// Test field marker annotation availability
		assertTrue(fieldName + " doesn't contain marker annotation javax.annotation.Resource", fc
				.isAnnotated("javax.annotation.Resource"));
		
		}

	/**
	 * Test not existing field merge with a not generated field.
	 */
	@Test
	public void testNotExistingNotGeneratedFieldMerge() {
		String resultContent = null;

		// Read initial content, final content and call Java merger
		try {
			resultContent = new JavaCodeMerger().merge(getFileContent(INPUT_TEST_PATH
					+ "FieldTestClass.java"), getFileContent(OUTPUT_TEST_PATH
					+ "FieldTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Check the result of the merge process
		JavaCodeChecker jcc = new JavaCodeChecker(resultContent);
		String typeName = "FieldTestClass";
		TypeChecker tc = (TypeChecker) jcc.getTypeChecker(typeName);

		// Test field myProperty3 availability
		assertTrue(typeName + " doesn't contain field myProperty3", tc.containsField("myProperty3"));

		String fieldName = "myProperty7";
		FieldChecker fc = jcc.getTypeChecker(typeName).getFieldChecker(fieldName);

		// Test field private modifier
		assertTrue(fieldName + " is not private", fc.isPrivate());

		// Test field type
		assertTrue(fieldName + " type is not equal to java.util.List", fc.isTyped("java.util.List"));
	}

	/**
	 * Test existing field with generics merge with a generated field without
	 * generics.
	 */
	@Test
	public void testExistingWithGenericsGeneratedWithoutGenericsFieldMerge() {
		String resultContent = null;

		// Read initial content, final content and call Java merger
		try {
			resultContent = new JavaCodeMerger().merge(getFileContent(INPUT_TEST_PATH
					+ "FieldTestClass.java"), getFileContent(OUTPUT_TEST_PATH
					+ "FieldTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Check the result of the merge process
		JavaCodeChecker jcc = new JavaCodeChecker(resultContent);
		String typeName = "FieldTestClass";

		String fieldName = "myProperty8";
		FieldChecker fc = jcc.getTypeChecker(typeName).getFieldChecker(fieldName);

		// Test field type
		assertTrue(fieldName + " type is not equal to List<String>", fc.isTyped("List<String>"));
	}

}
