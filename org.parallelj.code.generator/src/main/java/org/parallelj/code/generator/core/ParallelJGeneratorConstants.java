package org.parallelj.code.generator.core;

import static java.io.File.separator;

/**
 * This class contains all the constants needed by ParallelJ's source code
 * generator
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class ParallelJGeneratorConstants {

	/**
	 * path to the SRC folder
	 */
	public final static String SRC = "src";

	/**
	 * path to the SRC/MAIN folder
	 */
	public final static String SRC_MAIN = SRC + separator + "main";

	/**
	 * path to the SRC/TEST folder
	 */
	public final static String SRC_TEST = SRC + separator + "test";

	/**
	 * path to the SRC/MAIN/JAVA folder
	 */
	public final static String SRC_MAIN_JAVA = SRC_MAIN + separator + "java";

	/**
	 * path to the SRC/TEST/JAVA folder
	 */
	public final static String SRC_TEST_JAVA = SRC_TEST + separator + "java";

	/**
	 * path to the SRC/MAIN/RESOURCES folder
	 */
	public final static String SRC_MAIN_RESOURCES = SRC_MAIN + separator
			+ "resources";

	/**
	 * path to the SRC/TEST/RESOURCES folder
	 */
	public final static String SRC_TEST_RESOURCES = SRC_TEST + separator
			+ "resources";

	/**
	 * path to the TARGET folder
	 */
	public final static String TARGET = "target";

	/**
	 * path to the TARGET/CLASSES folder
	 */
	public final static String TARGET_CLASSES = TARGET + separator + "classes";

	/**
	 * ParallelJ's nature
	 */
	public final static String NATURE = "org.parallelj.designer.validation.ParallelJNature";

}
