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
import org.parallelj.common.jdt.checkers.JavaCodeChecker;
import org.parallelj.common.jdt.checkers.MethodChecker;
import org.parallelj.common.jdt.checkers.TypeChecker;
import org.parallelj.common.jdt.mergers.JavaCodeMerger;

public class MethodMergerTestCase {


	/**
	 * Test not existing method merge with generated method.
	 */
	@Test
	public void testNotExistingGeneratedMethodMerge() {
		String resultContent = null;

		// Read initial content, final content and call Java merger
		try {
			resultContent = new JavaCodeMerger().merge(getFileContent(INPUT_TEST_PATH
					+ "MethodTestClass.java"), getFileContent(OUTPUT_TEST_PATH
					+ "MethodTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Check the result of the merge process
		JavaCodeChecker jcc = new JavaCodeChecker(resultContent);
		String typeName = "MethodTestClass";
		String methodName = "myMethod3";
		MethodChecker mc = jcc.getTypeChecker(typeName).getMethodChecker(methodName);

		// Test method public modifier
		assertTrue(methodName + " is not public", mc.isPublic());

		// Test method single member annotation availability
		assertTrue(
				methodName
						+ " doesn't contain single member annotation javax.annotation.Generated(\"XA Designer\")",
				mc.isAnnotated("javax.annotation.Generated", "XA Designer"));
	}

	/**
	 * Test not generated method merge with not existing method.
	 */
	@Test
	public void testNotGeneratedNotExistingMethodMerge() {
		String resultContent = null;

		// Read initial content, final content and call Java merger
		try {
			resultContent = new JavaCodeMerger().merge(getFileContent(INPUT_TEST_PATH
					+ "MethodTestClass.java"), getFileContent(OUTPUT_TEST_PATH
					+ "MethodTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Check the result of the merge process
		JavaCodeChecker jcc = new JavaCodeChecker(resultContent);
		String typeName = "MethodTestClass";
		String methodName = "myMethod5";
		MethodChecker mc = jcc.getTypeChecker(typeName).getMethodChecker(methodName);

		// Test method javadoc availability
		assertTrue(methodName + " doesn't contain javadoc", mc.containsJavadoc());

		// Test method public modifier
		assertTrue(methodName + " is not public", mc.isPublic());

		// Test body availability
		assertTrue(methodName + " doesn't contain a body", mc.containsBody());

		// Test method body content
		String expectedBody = "System.out.println(\"IN myMethod5\");";
		assertTrue(methodName + " doesn't contains the right body", mc.containsBody(expectedBody));
	}

	/**
	 * Test not generated method merge with not generated method.
	 */
	@Test
	public void testNotGeneratedNotGeneratedMethodMerge() {
		String resultContent = null;

		// Read initial content, final content and call Java merger
		try {
			resultContent = new JavaCodeMerger().merge(getFileContent(INPUT_TEST_PATH
					+ "MethodTestClass.java"), getFileContent(OUTPUT_TEST_PATH
					+ "MethodTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Check the result of the merge process
		JavaCodeChecker jcc = new JavaCodeChecker(resultContent);
		String typeName = "MethodTestClass";
		String methodName = "myMethod6";
		MethodChecker mc = jcc.getTypeChecker(typeName).getMethodChecker(methodName);

		// Test method javadoc availability
		assertTrue(methodName + " doesn't contain javadoc", mc.containsJavadoc());

		// Test method public modifier
		assertTrue(methodName + " is not public", mc.isPublic());

		// Test body availability
		assertTrue(methodName + " doesn't contain a body", mc.containsBody());

		// Test method body content
		String expectedBody = "System.out.println(\"IN myMethod6\");";
		assertTrue(methodName + " doesn't contains the right body", mc.containsBody(expectedBody));
	}


	/**
	 * Test abstract methods merging.
	 */
	@Test
	public void testAbstractMethodsMerging() {
		String resultContent = null;

		// Read initial content, final content and call Java merger
		try {
			resultContent = new JavaCodeMerger().merge(getFileContent(INPUT_TEST_PATH
					+ "MethodTestClass.java"), getFileContent(OUTPUT_TEST_PATH
					+ "MethodTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Check the result of the merge process
		JavaCodeChecker jcc = new JavaCodeChecker(resultContent);
		String typeName = "MethodTestClass";
		String methodName = "myMethod9";
		MethodChecker mc = jcc.getTypeChecker(typeName).getMethodChecker(methodName);

		// Test method javadoc availability
		assertTrue(methodName + " doesn't contain javadoc", mc.containsJavadoc());

		// Test method exception thrown
		assertTrue(methodName + " doesn't throw exception java.lang.IllegalAccessError", mc
				.throwsException("java.lang.IllegalAccessError"));

		// Test method abstract modifier
		assertTrue(methodName + " method is not abstract", mc.isAbstract());

		// Test method public modifier
		assertTrue(methodName + " is not public", mc.isPublic());

		// Test method single member annotation availability
		assertTrue(
				methodName
						+ " doesn't contain single member annotation javax.annotation.Generated(\"XA Designer\")",
				mc.isAnnotated("javax.annotation.Generated", "XA Designer"));
	}

	/**
	 * Test method renaming with params
	 */
	@Test
	public void testMethodRenamingWithParams() {
		String resultContent = null;

		// Read initial content, final content and call Java merger
		try {
			resultContent = new JavaCodeMerger().merge(getFileContent(INPUT_TEST_PATH
					+ "MethodTestClass.java"), getFileContent(OUTPUT_TEST_PATH
					+ "MethodTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Check the result of the merge process
		JavaCodeChecker jcc = new JavaCodeChecker(resultContent);
		String typeName = "MethodTestClass";
		String methodName = "myMethod12";
		MethodChecker mc = jcc.getTypeChecker(typeName).getMethodChecker(methodName);

		// Test method javadoc availability
		assertTrue(methodName + " doesn't contain javadoc", mc.containsJavadoc());

		// Test body availability
		assertTrue(methodName + " doesn't contain a body", mc.containsBody());

		// Test method body content
		String expectedBody = "System.out.println(\"IN myMethod12\");return \"\";";
		assertTrue(methodName + " doesn't contains the right body", mc.containsBody(expectedBody));

		// Test method public modifier
		assertTrue(methodName + " is not public", mc.isPublic());

		// Test method final modifier
		assertTrue(methodName + " is not final", mc.isFinal());

		// Test method normal annotation availability
		java.util.Map<String, Object> expectedContent = new java.util.HashMap<String, Object>(3);

		// Set content here (exemple : expectedContent.put("MyExpectedKey" ,
		// "MyExpectedValue");)
		expectedContent.put("methodName", "myMethod12");

		assertFalse(methodName
				+ " contains normal annotation org.parallelj.common.jdt.mergers.ToBeRenamed", mc
				.isAnnotated("org.parallelj.common.jdt.mergers.ToBeRenamed", expectedContent));

		// Test method marker annotation availability
		assertTrue(methodName + " doesn't contain marker annotation javax.annotation.Resource", mc
				.isAnnotated("javax.annotation.Resource"));
	}

	/**
	 * Test methods merging with generics or not as parameters
	 */
	@Test
	public void testGenericNotGenericMethodMerge() {
		String resultContent = null;

		// Read initial content, final content and call Java merger
		try {
			resultContent = new JavaCodeMerger().merge(getFileContent(INPUT_TEST_PATH
					+ "MethodTestClass.java"), getFileContent(OUTPUT_TEST_PATH
					+ "MethodTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Check the result of the merge process
		JavaCodeChecker jcc = new JavaCodeChecker(resultContent);
		String typeName = "MethodTestClass";
		String methodName = "myMethod13";
		MethodChecker mc = jcc.getTypeChecker(typeName).getMethodChecker(methodName);

		// Test method marker annotation availability
		assertTrue(methodName + " doesn't contain marker annotation javax.annotation.Resource", mc
				.isAnnotated("javax.annotation.Resource"));
	}

	/**
	 * Test methods parameters modifiers
	 */
	@Test
	public void testParametersModifiersMethodMerge() {
		String resultContent = null;

		// Read initial content, final content and call Java merger
		try {
			resultContent = new JavaCodeMerger().merge(getFileContent(INPUT_TEST_PATH
					+ "MethodTestClass.java"), getFileContent(OUTPUT_TEST_PATH
					+ "MethodTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Check the result of the merge process
		JavaCodeChecker jcc = new JavaCodeChecker(resultContent);
		String typeName = "MethodTestClass";
		String methodName = "myMethod19";
		
		// Test method final modifier
		try {
			MethodChecker mc = jcc.getTypeChecker(typeName).getMethodChecker(methodName);				
			assertTrue(methodName+" doesn't exist in the merged code", mc != null);
			boolean b = mc.paramIsFinal("values");
			assertTrue("Parameter in the "+methodName+"'s signature is not final.",b);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
