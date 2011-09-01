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
package org.parallelj.codegen.constants;

import static java.io.File.separator;


public class Constants {

	public static class Dirs {
		/**
		 * Constant for the "src" directory
		 */
		public final static String DIR_SRC = "src";
		
		/**
		 * Constant for the "main" directory
		 */
		public final static String DIR_MAIN = DIR_SRC + separator + "main";

		/**
		 * Constant for the "test" directory
		 */
		public final static String DIR_TESTS = DIR_SRC + separator + "test";

		/**
		 * Constant for the main "java" directory
		 */
		public final static String DIR_MAIN_JAVA = DIR_MAIN + separator + "java";

		/**
		 * Constant for the test "java" directory
		 */
		public final static String DIR_TESTS_JAVA = DIR_TESTS + separator + "java";

		/**
		 * Constant for the main "resources" directory
		 */
		public final static String DIR_MAIN_RESOURCES = DIR_MAIN + separator + "resources";

		/**
		 * Constant for the tests "resources" directory
		 */
		public final static String DIR_TESTS_RESOURCES = DIR_TESTS + separator + "resources";
		
		/**
		 * Constant for the "models" directory
		 */
		public final static String DIR_MODELS = DIR_MAIN + separator + "models";

		/**
		 * Constant for the "config" directory
		 */
		public final static String DIR_CONFIG = DIR_MAIN + separator + "config";

		/**
		 * Constant for the "target" directory
		 */
		public final static String DIR_TARGET = "target";
		
		/**
		 * Constant for the "classes" directory
		 */
		public final static String DIR_CLASSES = DIR_TARGET + separator + "classes";

		/**
		 * Constant for the "lib" directory
		 */
		public final static String DIR_LIB = "lib";

	}

	/**
	 * Path to the content.xml template
	 * 
	 */
	public static final String PATH_TO_GENERATOR_FILE = "/PJGenerator.mwe";
	
	/**
	 * Location of the MWE generator file
	 */
	public final static String PATH_TO_WORKFLOW = Constants.class.getResource(PATH_TO_GENERATOR_FILE).getFile();


}
