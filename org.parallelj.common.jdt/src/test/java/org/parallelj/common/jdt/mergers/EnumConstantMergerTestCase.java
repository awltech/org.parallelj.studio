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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.parallelj.common.jdt.mergers.Tools.INPUT_TEST_PATH;
import static org.parallelj.common.jdt.mergers.Tools.OUTPUT_TEST_PATH;
import static org.parallelj.common.jdt.mergers.Tools.getFileContent;

import org.junit.Test;
import org.parallelj.common.jdt.checkers.EnumConstantChecker;
import org.parallelj.common.jdt.checkers.EnumTypeChecker;
import org.parallelj.common.jdt.checkers.JavaCodeChecker;
import org.parallelj.common.jdt.mergers.JavaCodeMerger;

public class EnumConstantMergerTestCase {

	/**
	 * Test generated enumeration constant merge with another generated
	 * enumeration constant.
	 */
	@Test
	public void testGeneratedGeneratedConstantMerge() {
		String resultContent = null;

		// Read initial content, final content and call Java merger
		try {
			resultContent = new JavaCodeMerger().merge(getFileContent(INPUT_TEST_PATH
					+ "EnumConstantTestClass.java"), getFileContent(OUTPUT_TEST_PATH
					+ "EnumConstantTestClass.java"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		// Check the result of the merge process
		JavaCodeChecker jcc = new JavaCodeChecker(resultContent);
		String typeName = "EnumConstantTestClass";
		String constantName = "MERCURY";
		EnumConstantChecker ecc = ((EnumTypeChecker) jcc.getTypeChecker(typeName))
				.getConstantChecker(constantName);

		// Test enum constant javadoc availability
		assertTrue(constantName + " doesn't contain javadoc", ecc.containsJavadoc());

		// Test enum constant single member annotation availability
		assertTrue(
				constantName
						+ " doesn't contain single member annotation javax.annotation.Generated(\"XA Designer\")",
				ecc.isAnnotated("javax.annotation.Generated", "XA Designer"));
	}
}
