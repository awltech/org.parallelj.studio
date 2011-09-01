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
import org.parallelj.common.jdt.checkers.FieldChecker;
import org.parallelj.common.jdt.checkers.JavaCodeChecker;
import org.parallelj.common.jdt.checkers.TypeChecker;

/**
 * Test case for org.parallelj.common.jdt.checkers.TypeChecker class
 * 
 * @author Atos Worldline
 */
public class FieldCheckerTestCase {

	/**
	 * Java classes input folder
	 */
	public static final String INPUT_FOLDER = "src/test/inputs";

	/**
	 * Tested class package name
	 */
	public static final String TESTED_CLASS_PACKAGE = "org.parallelj.common.jdt.checkers";

	/**
	 * First tested type name
	 */
	public static final String TESTED_TYPE_NAME = "FieldCheckerTestCaseInput";

	/**
	 * First tested field name
	 */
	public static final String FIRST_TESTED_FIELD_NAME = "firstField";

	/**
	 * Second tested field name
	 */
	public static final String SECOND_TESTED_FIELD_NAME = "secondField";

	/**
	 * Third tested field name
	 */
	public static final String THIRD_TESTED_FIELD_NAME = "thirdField";

	private FieldChecker fc = null;

	private FieldChecker fc2 = null;

	private FieldChecker fc3 = null;

	/**
	 * This method run before each test in this class
	 */
	@Before
	public void setUp() throws IOException {
		JavaCodeChecker jcc = new JavaCodeChecker(null, INPUT_FOLDER, TESTED_CLASS_PACKAGE,
				TESTED_TYPE_NAME);
		TypeChecker tc = (TypeChecker) jcc.getMainTypeChecker();

		this.fc = tc.getFieldChecker(FIRST_TESTED_FIELD_NAME);
		this.fc2 = tc.getFieldChecker(SECOND_TESTED_FIELD_NAME);
		this.fc3 = tc.getFieldChecker(THIRD_TESTED_FIELD_NAME);
	}

	/**
	 * Test final modifier
	 */
	@Test
	public void isFinal() {
		assertFalse(FIRST_TESTED_FIELD_NAME + " is not final", this.fc.isFinal());
		assertTrue(SECOND_TESTED_FIELD_NAME + " is final", this.fc2.isFinal());
	}

	/**
	 * Test static modifier
	 */
	@Test
	public void isStatic() {
		assertFalse(FIRST_TESTED_FIELD_NAME + " is not static", this.fc.isStatic());
		assertTrue(SECOND_TESTED_FIELD_NAME + " is static", this.fc2.isStatic());
	}

	/**
	 * Test private modifier
	 */
	@Test
	public void isPrivate() {
		assertFalse(FIRST_TESTED_FIELD_NAME + " is not private", this.fc.isPrivate());
		assertTrue(THIRD_TESTED_FIELD_NAME + " is private", this.fc3.isPrivate());
	}

	/**
	 * Test protected modifier
	 */
	@Test
	public void isProtected() {
		assertFalse(FIRST_TESTED_FIELD_NAME + " is not protected", this.fc.isProtected());
		assertTrue(SECOND_TESTED_FIELD_NAME + " is protected", this.fc2.isProtected());
	}

	/**
	 * Test public modifier
	 */
	@Test
	public void isPublic() {
		assertTrue(FIRST_TESTED_FIELD_NAME + " is public", this.fc.isPublic());
		assertFalse(SECOND_TESTED_FIELD_NAME + " is not public", this.fc2.isPublic());
	}

	/**
	 * Test trannsient modifier
	 */
	@Test
	public void isTransient() {
		assertTrue(FIRST_TESTED_FIELD_NAME + " is transient", this.fc.isTransient());
		assertFalse(SECOND_TESTED_FIELD_NAME + " is not transient", this.fc2.isTransient());
	}

	/**
	 * Test javadoc
	 */
	@Test
	public void containsJavadoc() {
		assertTrue(FIRST_TESTED_FIELD_NAME + " contains javadoc", this.fc.containsJavadoc());
		assertFalse(SECOND_TESTED_FIELD_NAME + " doesn't contain javadoc", this.fc2
				.containsJavadoc());
	}

	/**
	 * Test marker annotation
	 */
	@Test
	public void isAnnotatedWithAMarkerAnnotation() {
		assertFalse(FIRST_TESTED_FIELD_NAME + " is not annotated with a marker annotation", this.fc
				.isAnnotated("java.lang.annotation.Documented"));
		assertTrue(SECOND_TESTED_FIELD_NAME + " is annotated with a marker annotation", this.fc2
				.isAnnotated("java.lang.annotation.Documented"));
	}

	/**
	 * Test single member annotation
	 */
	@Test
	public void isAnnotatedWithASingleMemberAnnotation() {
		assertTrue(FIRST_TESTED_FIELD_NAME + " is annotated with a single member annotation",
				this.fc.isAnnotated("javax.annotation.Generated", "ParallelJ"));
		assertFalse(SECOND_TESTED_FIELD_NAME + " is not annotated with a single member annotation",
				this.fc2.isAnnotated("javax.annotation.Generated", "ParallelJ"));
	}

	/**
	 * Test normal annotation
	 */
	@Test
	public void isAnnotatedWithANormalAnnotation() {
		Map<String, Object> values = new HashMap<String, Object>(2);
		values.put("value", "javax.annotation.Generated");
		assertTrue(FIRST_TESTED_FIELD_NAME + " is annotated with a normal annotation", this.fc
				.isAnnotated("java.lang.annotation.Target", values));
		assertFalse(SECOND_TESTED_FIELD_NAME + " is not annotated with a normal annotation",
				this.fc2.isAnnotated("java.lang.annotation.Target", values));
	}

	/**
	 * Test initialization
	 */
	@Test
	public void isInitialized() {
		assertTrue(FIRST_TESTED_FIELD_NAME + " is initialized", this.fc.isInitialized());
		assertFalse(SECOND_TESTED_FIELD_NAME + " is not initialized", this.fc2.isInitialized());
	}

	/**
	 * Test type
	 */
	@Test
	public void isTyped() {
		assertTrue(FIRST_TESTED_FIELD_NAME + " is typed java.util.Map<String, Object>", this.fc
				.isTyped("java.util.Map<String, Object>"));
		assertTrue(SECOND_TESTED_FIELD_NAME + " is typed java.lang.String", this.fc2
				.isTyped("java.lang.String"));
		assertTrue(THIRD_TESTED_FIELD_NAME + " is typed int", this.fc3.isTyped("int"));

	}
}
