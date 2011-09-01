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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.parallelj.common.jdt.mergers.Tools.INPUT_TEST_PATH;
import static org.parallelj.common.jdt.mergers.Tools.OUTPUT_TEST_PATH;
import static org.parallelj.common.jdt.mergers.Tools.getFileContent;

import org.junit.Test;
import org.parallelj.common.jdt.checkers.JavaCodeChecker;
import org.parallelj.common.jdt.mergers.JavaCodeMerger;

/**
 * Test case about java code merging process
 * 
 * @author Atos WorldLine
 */
public class CompilationUnitMergerTestCase {

	/**
	 * Test Java merging when a class doesn't have a body
	 */
	@Test
	public void testEmptyMerging() {
		// Read initial content, final content and call Java merger
		try {
			new JavaCodeMerger().merge(getFileContent(INPUT_TEST_PATH + "EmptyTestClass.java"),
					getFileContent(OUTPUT_TEST_PATH + "EmptyTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Test Java imports merging
	 */
	@Test
	public void testImportMerging() {
		String resultContent = null;
		String className = "ImportTestClass";

		// Read initial content, final content and call Java merger
		try {
			resultContent = new JavaCodeMerger().merge(getFileContent(INPUT_TEST_PATH
					+ "ImportTestClass.java"), getFileContent(OUTPUT_TEST_PATH
					+ "ImportTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Check the result of the merge process
		JavaCodeChecker jcc = new JavaCodeChecker(resultContent);

		// Test import java.util.List availability in a compilation unit
		assertTrue(className + " doesn't contains import java.util.List", jcc
				.containsImport("java.util.List"));

		// Test import javax.annotation.Resource availability in a compilation
		// unit
		assertTrue(className + " doesn't contains import javax.annotation.Resource", jcc
				.containsImport("javax.annotation.Resource"));

		// Test import javax.annotation.Generated availability in a compilation
		// unit
		assertTrue(className + " doesn't contains import javax.annotation.Generated", jcc
				.containsImport("javax.annotation.Generated"));

		// Test imports number in a compilation unit
		assertEquals(className + " imports number is not equal to 3", 3, jcc.importsNumber());
	}
}
