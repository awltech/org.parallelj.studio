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

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.parallelj.common.jdt.checkers.FieldChecker;
import org.parallelj.common.jdt.checkers.JavaCodeChecker;
import org.parallelj.common.jdt.checkers.MethodChecker;
import org.parallelj.common.jdt.checkers.TypeChecker;
import org.parallelj.common.jdt.mergers.JavaCodeMerger;

public class AnnotationUsageMergerTestCase {

	/**
	 * Test Marker on existing / Marker on generated
	 */
	@Test
	public void testMarkerExisitingMarkerGenerated() {
		String resultContent = null;

		// Read initial content, final content and call Java merger
		try {
			Set<String> predefinedAnnotations = new HashSet<String>(6);
			predefinedAnnotations.add("javax.annotation.Resource");
			predefinedAnnotations.add("Resource");
			predefinedAnnotations.add("javax.xml.ws.BindingType");
			predefinedAnnotations.add("BindingType");
			predefinedAnnotations.add("javax.xml.bind.annotation.XmlMimeType");
			predefinedAnnotations.add("XmlMimeType");
			resultContent = new JavaCodeMerger(predefinedAnnotations).merge(
					getFileContent(INPUT_TEST_PATH + "AnnotationUsageTestClass.java"),
					getFileContent(OUTPUT_TEST_PATH + "AnnotationUsageTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Check the result of the merge process
		JavaCodeChecker jcc = new JavaCodeChecker(resultContent);
		String typeName = "AnnotationUsageTestClass";
		TypeChecker tc = (TypeChecker) jcc.getTypeChecker(typeName);

		// Test type marker annotation availability
		assertTrue(typeName + " doesn't contain marker annotation javax.annotation.Resource", tc
				.isAnnotated("javax.annotation.Resource"));

		String fieldName = "annotationUsageTestField";
		FieldChecker fc = jcc.getTypeChecker(typeName).getFieldChecker(fieldName);

		// Test field marker annotation availability
		assertTrue(fieldName + " doesn't contain marker annotation javax.annotation.Resource", fc
				.isAnnotated("javax.annotation.Resource"));

		String methodName = "annotationUsageTestMethod";
		MethodChecker mc = jcc.getTypeChecker(typeName).getMethodChecker(methodName);

		// Test method marker annotation availability
		assertTrue(methodName + " doesn't contain marker annotation javax.annotation.Resource", mc
				.isAnnotated("javax.annotation.Resource"));
	}

	/**
	 * Test Normal on not generated existing / Marker on generated
	 */
	@Test
	public void testNormalNotGeneratedExisitingMarkerGenerated() {
		String resultContent = null;

		// Read initial content, final content and call Java merger
		try {
			Set<String> predefinedAnnotations = new HashSet<String>(6);
			predefinedAnnotations.add("javax.annotation.Resource");
			predefinedAnnotations.add("Resource");
			predefinedAnnotations.add("javax.xml.ws.BindingType");
			predefinedAnnotations.add("BindingType");
			predefinedAnnotations.add("javax.xml.bind.annotation.XmlMimeType");
			predefinedAnnotations.add("XmlMimeType");
			resultContent = new JavaCodeMerger(predefinedAnnotations).merge(
					getFileContent(INPUT_TEST_PATH + "AnnotationUsageTestClass.java"),
					getFileContent(OUTPUT_TEST_PATH + "AnnotationUsageTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Check the result of the merge process
		JavaCodeChecker jcc = new JavaCodeChecker(resultContent);
		String typeName = "AnnotationUsageTestClass10";
		TypeChecker tc = (TypeChecker) jcc.getTypeChecker(typeName);

		// Test type normal annotation availability
		java.util.Map<String, Object> expectedContent = new java.util.HashMap<String, Object>(3);
		expectedContent.put("name", "foo");

		assertTrue(typeName + " doesn't contain normal annotation javax.annotation.Resource", tc
				.isAnnotated("javax.annotation.Resource", expectedContent));

		String fieldName = "annotationUsageTestField10";
		FieldChecker fc = jcc.getTypeChecker(typeName).getFieldChecker(fieldName);

		// Test field normal annotation availability
		expectedContent = new java.util.HashMap<String, Object>(3);
		expectedContent.put("name", "foo");

		assertTrue(fieldName + " doesn't contain normal annotation javax.annotation.Resource", fc
				.isAnnotated("javax.annotation.Resource", expectedContent));

		String methodName = "annotationUsageTestMethod10";
		MethodChecker mc = jcc.getTypeChecker(typeName).getMethodChecker(methodName);

		// Test method normal annotation availability
		expectedContent = new java.util.HashMap<String, Object>(3);
		expectedContent.put("name", "foo");

		assertTrue(methodName + " doesn't contain normal annotation javax.annotation.Resource", mc
				.isAnnotated("javax.annotation.Resource", expectedContent));
	}

	/**
	 * Test Normal on not generated existing / Single Member on generated
	 */
	@Test
	public void testNormalNotGeneratedExisitingSingleMemberGenerated() {
		String resultContent = null;

		// Read initial content, final content and call Java merger
		try {
			Set<String> predefinedAnnotations = new HashSet<String>(6);
			predefinedAnnotations.add("javax.annotation.Resource");
			predefinedAnnotations.add("Resource");
			predefinedAnnotations.add("javax.xml.ws.BindingType");
			predefinedAnnotations.add("BindingType");
			predefinedAnnotations.add("javax.xml.bind.annotation.XmlMimeType");
			predefinedAnnotations.add("XmlMimeType");
			resultContent = new JavaCodeMerger(predefinedAnnotations).merge(
					getFileContent(INPUT_TEST_PATH + "AnnotationUsageTestClass.java"),
					getFileContent(OUTPUT_TEST_PATH + "AnnotationUsageTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Check the result of the merge process
		JavaCodeChecker jcc = new JavaCodeChecker(resultContent);
		String typeName = "AnnotationUsageTestClass11";
		TypeChecker tc = (TypeChecker) jcc.getTypeChecker(typeName);

		// Test type normal annotation availability
		java.util.Map<String, Object> expectedContent = new java.util.HashMap<String, Object>(3);
		expectedContent.put("name", "foo");

		assertTrue(typeName + " doesn't contain normal annotation javax.annotation.Resource", tc
				.isAnnotated("javax.annotation.Resource", expectedContent));

		// Test type single member annotation availability
		assertFalse(typeName
				+ " contains single member annotation javax.xml.ws.BindingType(\"bar\")", tc
				.isAnnotated("javax.xml.ws.BindingType", "bar"));

		String fieldName = "annotationUsageTestField11";
		FieldChecker fc = jcc.getTypeChecker(typeName).getFieldChecker(fieldName);

		// Test field normal annotation availability
		expectedContent = new java.util.HashMap<String, Object>(3);
		expectedContent.put("name", "foo");

		assertTrue(fieldName + " doesn't contain normal annotation javax.annotation.Resource", fc
				.isAnnotated("javax.annotation.Resource", expectedContent));

		// Test field single member annotation availability
		assertFalse(
				fieldName
						+ " contains single member annotation javax.xml.bind.annotation.XmlMimeType(\"bar\")",
				fc.isAnnotated("javax.xml.bind.annotation.XmlMimeType", "bar"));

		String methodName = "annotationUsageTestMethod11";
		MethodChecker mc = jcc.getTypeChecker(typeName).getMethodChecker(methodName);

		// Test method normal annotation availability
		expectedContent = new java.util.HashMap<String, Object>(3);
		expectedContent.put("name", "foo");

		assertTrue(methodName + " doesn't contain normal annotation javax.annotation.Resource", mc
				.isAnnotated("javax.annotation.Resource", expectedContent));

		// Test method single member annotation availability
		assertFalse(
				methodName
						+ " contains single member annotation javax.xml.bind.annotation.XmlMimeType(\"bar\")",
				mc.isAnnotated("javax.xml.bind.annotation.XmlMimeType", "bar"));
	}

	/**
	 * Test Normal on not generated existing / Normal on generated
	 */
	@Test
	public void testNormalNotGeneratedExisitingNormalGenerated() {
		String resultContent = null;

		// Read initial content, final content and call Java merger
		try {
			Set<String> predefinedAnnotations = new HashSet<String>(6);
			predefinedAnnotations.add("javax.annotation.Resource");
			predefinedAnnotations.add("Resource");
			predefinedAnnotations.add("javax.xml.ws.BindingType");
			predefinedAnnotations.add("BindingType");
			predefinedAnnotations.add("javax.xml.bind.annotation.XmlMimeType");
			predefinedAnnotations.add("XmlMimeType");
			resultContent = new JavaCodeMerger(predefinedAnnotations).merge(
					getFileContent(INPUT_TEST_PATH + "AnnotationUsageTestClass.java"),
					getFileContent(OUTPUT_TEST_PATH + "AnnotationUsageTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Check the result of the merge process
		JavaCodeChecker jcc = new JavaCodeChecker(resultContent);
		String typeName = "AnnotationUsageTestClass12";
		TypeChecker tc = (TypeChecker) jcc.getTypeChecker(typeName);

		// Test type normal annotation availability
		java.util.Map<String, Object> expectedContent = new java.util.HashMap<String, Object>(3);
		expectedContent.put("name", "foo");

		assertTrue(typeName + " doesn't contain normal annotation javax.annotation.Resource", tc
				.isAnnotated("javax.annotation.Resource", expectedContent));

		String fieldName = "annotationUsageTestField12";
		FieldChecker fc = jcc.getTypeChecker(typeName).getFieldChecker(fieldName);

		// Test field normal annotation availability
		expectedContent = new java.util.HashMap<String, Object>(3);
		expectedContent.put("name", "foo");

		assertTrue(fieldName + " doesn't contain normal annotation javax.annotation.Resource", fc
				.isAnnotated("javax.annotation.Resource", expectedContent));

		String methodName = "annotationUsageTestMethod12";
		MethodChecker mc = jcc.getTypeChecker(typeName).getMethodChecker(methodName);

		// Test method normal annotation availability
		expectedContent = new java.util.HashMap<String, Object>(3);
		expectedContent.put("name", "foo");

		assertTrue(methodName + " doesn't contain normal annotation javax.annotation.Resource", mc
				.isAnnotated("javax.annotation.Resource", expectedContent));
	}

	/**
	 * Test Marker on existing / Empty on generated / Not predefined
	 */
	@Test
	public void testMarkerExisitingEmptyGeneratedNotPredefined() {
		String resultContent = null;

		// Read initial content, final content and call Java merger
		try {
			resultContent = new JavaCodeMerger().merge(getFileContent(INPUT_TEST_PATH
					+ "AnnotationUsageTestClass.java"), getFileContent(OUTPUT_TEST_PATH
					+ "AnnotationUsageTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Check the result of the merge process
		JavaCodeChecker jcc = new JavaCodeChecker(resultContent);
		String typeName = "AnnotationUsageTestClass16";
		TypeChecker tc = (TypeChecker) jcc.getTypeChecker(typeName);

		// Test type marker annotation availability
		assertTrue(typeName + " doesn't contain marker annotation javax.annotation.Resource", tc
				.isAnnotated("javax.annotation.Resource"));

		String fieldName = "annotationUsageTestField16";
		FieldChecker fc = jcc.getTypeChecker(typeName).getFieldChecker(fieldName);

		// Test field marker annotation availability
		assertTrue(fieldName + " doesn't contain marker annotation javax.annotation.Resource", fc
				.isAnnotated("javax.annotation.Resource"));

		String methodName = "annotationUsageTestMethod16";
		MethodChecker mc = jcc.getTypeChecker(typeName).getMethodChecker(methodName);

		// Test method marker annotation availability
		assertTrue(methodName + " doesn't contain marker annotation javax.annotation.Resource", mc
				.isAnnotated("javax.annotation.Resource"));
	}

	/**
	 * Test Single Member on existing / Empty on generated / Not predefined
	 */
	@Test
	public void testSingleMemberExisitingEmptyGeneratedNotPredefined() {
		String resultContent = null;

		// Read initial content, final content and call Java merger
		try {
			resultContent = new JavaCodeMerger().merge(getFileContent(INPUT_TEST_PATH
					+ "AnnotationUsageTestClass.java"), getFileContent(OUTPUT_TEST_PATH
					+ "AnnotationUsageTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Check the result of the merge process
		JavaCodeChecker jcc = new JavaCodeChecker(resultContent);
		String typeName = "AnnotationUsageTestClass17";
		TypeChecker tc = (TypeChecker) jcc.getTypeChecker(typeName);

		// Test type single member annotation availability
		assertTrue(typeName
				+ " doesn't contain single member annotation javax.xml.ws.BindingType(\"foo\")", tc
				.isAnnotated("javax.xml.ws.BindingType", "foo"));

		String fieldName = "annotationUsageTestField17";
		FieldChecker fc = jcc.getTypeChecker(typeName).getFieldChecker(fieldName);

		// Test field single member annotation availability
		assertTrue(
				fieldName
						+ " doesn't contain single member annotation javax.xml.bind.annotation.XmlMimeType(\"foo\")",
				fc.isAnnotated("javax.xml.bind.annotation.XmlMimeType", "foo"));

		String methodName = "annotationUsageTestMethod17";
		MethodChecker mc = jcc.getTypeChecker(typeName).getMethodChecker(methodName);

		// Test method single member annotation availability
		assertTrue(
				methodName
						+ " doesn't contain single member annotation javax.xml.bind.annotation.XmlMimeType(\"foo\")",
				mc.isAnnotated("javax.xml.bind.annotation.XmlMimeType", "foo"));
	}

	/**
	 * Test Normal on existing / Empty on generated / Not predefined
	 */
	@Test
	public void testNormalExisitingEmptyGeneratedNotPredefined() {
		String resultContent = null;

		// Read initial content, final content and call Java merger
		try {
			resultContent = new JavaCodeMerger().merge(getFileContent(INPUT_TEST_PATH
					+ "AnnotationUsageTestClass.java"), getFileContent(OUTPUT_TEST_PATH
					+ "AnnotationUsageTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Check the result of the merge process
		JavaCodeChecker jcc = new JavaCodeChecker(resultContent);
		String typeName = "AnnotationUsageTestClass18";
		TypeChecker tc = (TypeChecker) jcc.getTypeChecker(typeName);

		// Test type normal annotation availability
		java.util.Map<String, Object> expectedContent = new java.util.HashMap<String, Object>(3);
		expectedContent.put("name", "foo");

		assertTrue(typeName + " doesn't contain normal annotation javax.annotation.Resource", tc
				.isAnnotated("javax.annotation.Resource", expectedContent));

		String fieldName = "annotationUsageTestField18";
		FieldChecker fc = jcc.getTypeChecker(typeName).getFieldChecker(fieldName);

		// Test field normal annotation availability
		expectedContent = new java.util.HashMap<String, Object>(3);
		expectedContent.put("name", "foo");

		assertTrue(fieldName + " doesn't contain normal annotation javax.annotation.Resource", fc
				.isAnnotated("javax.annotation.Resource", expectedContent));

		String methodName = "annotationUsageTestMethod18";
		MethodChecker mc = jcc.getTypeChecker(typeName).getMethodChecker(methodName);

		// Test method normal annotation availability
		expectedContent = new java.util.HashMap<String, Object>(3);
		expectedContent.put("name", "foo");

		assertTrue(methodName + " doesn't contain normal annotation javax.annotation.Resource", mc
				.isAnnotated("javax.annotation.Resource", expectedContent));
	}

	/**
	 * Test Empty on not generated existing / Marker on generated
	 */
	@Test
	public void testEmptyExisitingNotGeneratedMarkerGenerated() {
		String resultContent = null;

		// Read initial content, final content and call Java merger
		try {
			Set<String> predefinedAnnotations = new HashSet<String>(6);
			predefinedAnnotations.add("javax.annotation.Resource");
			predefinedAnnotations.add("Resource");
			predefinedAnnotations.add("javax.xml.ws.BindingType");
			predefinedAnnotations.add("BindingType");
			predefinedAnnotations.add("javax.xml.bind.annotation.XmlMimeType");
			predefinedAnnotations.add("XmlMimeType");
			resultContent = new JavaCodeMerger(predefinedAnnotations).merge(
					getFileContent(INPUT_TEST_PATH + "AnnotationUsageTestClass.java"),
					getFileContent(OUTPUT_TEST_PATH + "AnnotationUsageTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Check the result of the merge process
		JavaCodeChecker jcc = new JavaCodeChecker(resultContent);
		String typeName = "AnnotationUsageTestClass22";
		TypeChecker tc = (TypeChecker) jcc.getTypeChecker(typeName);

		// Test type marker annotation availability
		assertFalse(typeName + " contains marker annotation javax.annotation.Resource", tc
				.isAnnotated("javax.annotation.Resource"));

		String fieldName = "annotationUsageTestField22";
		FieldChecker fc = jcc.getTypeChecker(typeName).getFieldChecker(fieldName);

		// Test field marker annotation availability
		assertFalse(fieldName + " contains marker annotation javax.annotation.Resource", fc
				.isAnnotated("javax.annotation.Resource"));

		String methodName = "annotationUsageTestMethod22";
		MethodChecker mc = jcc.getTypeChecker(typeName).getMethodChecker(methodName);

		// Test method marker annotation availability
		assertFalse(methodName + " contains marker annotation javax.annotation.Resource", mc
				.isAnnotated("javax.annotation.Resource"));
	}

	/**
	 * Test Empty on not generated existing / Single Member on generated
	 */
	@Test
	public void testEmptyNotGeneratedExisitingSingleMemberGenerated() {
		String resultContent = null;

		// Read initial content, final content and call Java merger
		try {
			Set<String> predefinedAnnotations = new HashSet<String>(6);
			predefinedAnnotations.add("javax.annotation.Resource");
			predefinedAnnotations.add("Resource");
			predefinedAnnotations.add("javax.xml.ws.BindingType");
			predefinedAnnotations.add("BindingType");
			predefinedAnnotations.add("javax.xml.bind.annotation.XmlMimeType");
			predefinedAnnotations.add("XmlMimeType");
			resultContent = new JavaCodeMerger(predefinedAnnotations).merge(
					getFileContent(INPUT_TEST_PATH + "AnnotationUsageTestClass.java"),
					getFileContent(OUTPUT_TEST_PATH + "AnnotationUsageTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Check the result of the merge process
		JavaCodeChecker jcc = new JavaCodeChecker(resultContent);
		String typeName = "AnnotationUsageTestClass23";
		TypeChecker tc = (TypeChecker) jcc.getTypeChecker(typeName);

		// Test type single member annotation availability
		assertFalse(typeName
				+ " contains single member annotation javax.xml.ws.BindingType(\"bar\")", tc
				.isAnnotated("javax.xml.ws.BindingType", "bar"));

		String fieldName = "annotationUsageTestField23";
		FieldChecker fc = jcc.getTypeChecker(typeName).getFieldChecker(fieldName);

		// Test field single member annotation availability
		assertFalse(
				fieldName
						+ " contains single member annotation javax.xml.bind.annotation.XmlMimeType(\"bar\")",
				fc.isAnnotated("javax.xml.bind.annotation.XmlMimeType", "bar"));

		String methodName = "annotationUsageTestMethod23";
		MethodChecker mc = jcc.getTypeChecker(typeName).getMethodChecker(methodName);

		// Test method single member annotation availability
		assertFalse(
				methodName
						+ " contains single member annotation javax.xml.bind.annotation.XmlMimeType(\"bar\")",
				mc.isAnnotated("javax.xml.bind.annotation.XmlMimeType", "bar"));
	}

	/**
	 * Test Empty on not generated existing / Normal on generated
	 */
	@Test
	public void testEmptyNotGeneratedExisitingNormalGenerated() {
		String resultContent = null;

		// Read initial content, final content and call Java merger
		try {
			Set<String> predefinedAnnotations = new HashSet<String>(6);
			predefinedAnnotations.add("javax.annotation.Resource");
			predefinedAnnotations.add("Resource");
			predefinedAnnotations.add("javax.xml.ws.BindingType");
			predefinedAnnotations.add("BindingType");
			predefinedAnnotations.add("javax.xml.bind.annotation.XmlMimeType");
			predefinedAnnotations.add("XmlMimeType");
			resultContent = new JavaCodeMerger(predefinedAnnotations).merge(
					getFileContent(INPUT_TEST_PATH + "AnnotationUsageTestClass.java"),
					getFileContent(OUTPUT_TEST_PATH + "AnnotationUsageTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Check the result of the merge process
		JavaCodeChecker jcc = new JavaCodeChecker(resultContent);
		String typeName = "AnnotationUsageTestClass24";
		TypeChecker tc = (TypeChecker) jcc.getTypeChecker(typeName);

		// Test type normal annotation availability
		java.util.Map<String, Object> expectedContent = new java.util.HashMap<String, Object>(3);
		expectedContent.put("name", "bar");

		assertFalse(typeName + " contains normal annotation javax.annotation.Resource", tc
				.isAnnotated("javax.annotation.Resource", expectedContent));

		String fieldName = "annotationUsageTestField24";
		FieldChecker fc = jcc.getTypeChecker(typeName).getFieldChecker(fieldName);

		// Test field normal annotation availability
		expectedContent = new java.util.HashMap<String, Object>(3);
		expectedContent.put("name", "bar");

		assertFalse(fieldName + " contains normal annotation javax.annotation.Resource", fc
				.isAnnotated("javax.annotation.Resource", expectedContent));

		String methodName = "annotationUsageTestMethod24";
		MethodChecker mc = jcc.getTypeChecker(typeName).getMethodChecker(methodName);

		// Test method normal annotation availability
		expectedContent = new java.util.HashMap<String, Object>(3);
		expectedContent.put("name", "bar");

		assertFalse(methodName + " contains normal annotation javax.annotation.Resource", mc
				.isAnnotated("javax.annotation.Resource", expectedContent));
	}
}
