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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.parallelj.common.jdt.checkers.JavaCodeChecker;
import org.parallelj.common.jdt.checkers.MethodChecker;
import org.parallelj.common.jdt.checkers.TypeChecker;

/**
 * Test case for org.parallelj.common.jdt.checkers.MethodChecker class
 * 
 * @author Atos Worldline
 */
public class MethodCheckerTestCase {

	/**
	 * Java classes input folder
	 */
	public static final String INPUT_FOLDER = "src/test/inputs";

	/**
	 * Tested class package name
	 */
	public static final String TESTED_CLASS_PACKAGE = "org.parallelj.common.jdt.checkers";

	/**
	 * Input type name
	 */
	public static final String TESTED_TYPE_NAME = "MethodCheckerTestCaseInput";

	/**
	 * First tested method name
	 */
	public static final String FIRST_TESTED_METHOD_NAME = "firstMethod";

	/**
	 * Second tested method name
	 */
	public static final String SECOND_TESTED_METHOD_NAME = "secondMethod";

	/**
	 * Third tested method name
	 */
	public static final String THIRD_TESTED_METHOD_NAME = "thirdMethod";

	private MethodChecker mc = null;

	private MethodChecker mc2 = null;

	private MethodChecker mc3 = null;

	/**
	 * This method run before each test in this class
	 */
	@Before
	public void setUp() throws IOException {
		JavaCodeChecker jcc = new JavaCodeChecker(null, INPUT_FOLDER, TESTED_CLASS_PACKAGE,
				TESTED_TYPE_NAME);
		TypeChecker tc = (TypeChecker) jcc.getMainTypeChecker();

		this.mc = tc.getMethodChecker(FIRST_TESTED_METHOD_NAME);
		this.mc2 = tc.getMethodChecker(SECOND_TESTED_METHOD_NAME);
		this.mc3 = tc.getMethodChecker(THIRD_TESTED_METHOD_NAME);
	}

	/**
	 * Test final modifier
	 */
	@Test
	public void isFinal() {
		assertFalse(FIRST_TESTED_METHOD_NAME + " is not final", this.mc.isFinal());
		assertTrue(SECOND_TESTED_METHOD_NAME + " is final", this.mc2.isFinal());
	}

	/**
	 * Test static modifier
	 */
	@Test
	public void isStatic() {
		assertFalse(FIRST_TESTED_METHOD_NAME + " is not static", this.mc.isStatic());
		assertTrue(SECOND_TESTED_METHOD_NAME + " is static", this.mc2.isStatic());
	}

	/**
	 * Test private modifier
	 */
	@Test
	public void isPrivate() {
		assertFalse(FIRST_TESTED_METHOD_NAME + " is not private", this.mc.isPrivate());
		assertTrue(THIRD_TESTED_METHOD_NAME + " is private", this.mc3.isPrivate());
	}

	/**
	 * Test protected modifier
	 */
	@Test
	public void isProtected() {
		assertFalse(FIRST_TESTED_METHOD_NAME + " is not protected", this.mc.isProtected());
		assertTrue(SECOND_TESTED_METHOD_NAME + " is protected", this.mc2.isProtected());
	}

	/**
	 * Test public modifier
	 */
	@Test
	public void isPublic() {
		assertTrue(FIRST_TESTED_METHOD_NAME + " is public", this.mc.isPublic());
		assertFalse(SECOND_TESTED_METHOD_NAME + " is not public", this.mc2.isPublic());
	}

	/**
	 * Test synchronized modifier
	 */
	@Test
	public void isSynchronized() {
		assertTrue(FIRST_TESTED_METHOD_NAME + " is synchronized", this.mc.isSynchronized());
		assertFalse(SECOND_TESTED_METHOD_NAME + " is not synchronized", this.mc2.isSynchronized());
	}

	/**
	 * Test abstract modifier
	 */
	@Test
	public void isAbstract() {
		assertFalse(FIRST_TESTED_METHOD_NAME + " is not abstract", this.mc.isAbstract());
		assertTrue(THIRD_TESTED_METHOD_NAME + " is abstract", this.mc3.isAbstract());
	}

	/**
	 * Test native modifier
	 */
	@Test
	public void isNative() {
		assertFalse(FIRST_TESTED_METHOD_NAME + " is not native", this.mc.isNative());
		assertTrue(THIRD_TESTED_METHOD_NAME + " is native", this.mc3.isNative());
	}

	/**
	 * Test javadoc
	 */
	@Test
	public void containsJavadoc() {
		assertTrue(FIRST_TESTED_METHOD_NAME + " contains javadoc", this.mc.containsJavadoc());
		assertFalse(SECOND_TESTED_METHOD_NAME + " doesn't contain javadoc", this.mc2
				.containsJavadoc());
	}

	/**
	 * Test marker annotation
	 */
	@Test
	public void isAnnotatedWithAMarkerAnnotation() {
		assertFalse(FIRST_TESTED_METHOD_NAME + " is not annotated with a marker annotation",
				this.mc.isAnnotated("java.lang.annotation.Documented"));
		assertTrue(SECOND_TESTED_METHOD_NAME + " is annotated with a marker annotation", this.mc2
				.isAnnotated("java.lang.annotation.Documented"));
	}

	/**
	 * Test single member annotation
	 */
	@Test
	public void isAnnotatedWithASingleMemberAnnotation() {
		assertTrue(FIRST_TESTED_METHOD_NAME + " is annotated with a single member annotation",
				this.mc.isAnnotated("javax.annotation.Generated", "ParallelJ"));
		assertFalse(
				SECOND_TESTED_METHOD_NAME + " is not annotated with a single member annotation",
				this.mc2.isAnnotated("javax.annotation.Generated", "ParallelJ"));
	}

	/**
	 * Test normal annotation
	 */
	@Test
	public void isAnnotatedWithANormalAnnotation() {
		Map<String, Object> values = new HashMap<String, Object>(2);
		values.put("value", "javax.annotation.Generated");
		assertTrue(FIRST_TESTED_METHOD_NAME + " is annotated with a normal annotation", this.mc
				.isAnnotated("java.lang.annotation.Target", values));
		assertFalse(SECOND_TESTED_METHOD_NAME + " is not annotated with a normal annotation",
				this.mc2.isAnnotated("java.lang.annotation.Target", values));
	}

	/**
	 * Test exceptions
	 */
	@Test
	public void throwsExceptions() {
		assertFalse(FIRST_TESTED_METHOD_NAME + " doesn't throw an exception", this.mc
				.throwsException(null));
		assertTrue(SECOND_TESTED_METHOD_NAME + " throws java.lang.IllegalArgumentException",
				this.mc2.throwsException("java.lang.IllegalArgumentException"));
	}

	/**
	 * Test return type
	 */
	@Test
	public void returnsType() {
		assertTrue(FIRST_TESTED_METHOD_NAME + " is typed void", this.mc.returnsType("void"));
		assertTrue(SECOND_TESTED_METHOD_NAME + " is typed java.lang.String", this.mc2
				.returnsType("java.lang.String"));
		assertTrue(
				THIRD_TESTED_METHOD_NAME
						+ " is typed java.util.Map<java.lang.String, java.util.List<? extends java.lang.String>>",
				this.mc3
						.returnsType("java.util.Map<java.lang.String, java.util.List<? extends java.lang.String>>"));

	}

	/**
	 * Test method body presence
	 */
	@Test
	public void containsBody() {
		assertFalse(FIRST_TESTED_METHOD_NAME + " doesn't contain body", this.mc.containsBody());
		assertTrue(SECOND_TESTED_METHOD_NAME + " contains body", this.mc2.containsBody());
	}

	/**
	 * Test method body content
	 */
	@Test
	public void containsBodyContent() {
		String expectedBody = "System.out.println(\"IN secondMethod\");";

		assertFalse(FIRST_TESTED_METHOD_NAME + " doesn't contain this body", this.mc
				.containsBody(expectedBody));
		assertTrue(SECOND_TESTED_METHOD_NAME + " contains this body", this.mc2
				.containsBody(expectedBody));
	}
}
