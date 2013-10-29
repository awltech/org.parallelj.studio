package org.parallelj.code.generator.core;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * This enum allows to store all the messages' keys used in ParallelJ source
 * code generator. It then allows to retrieve the corresponding message from a
 * {@link ResourceBundle}.
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public enum Messages {

	// UI dedicated messages

	UI_ALL_BUTTON_MESSAGE, UI_ALL_BUTTON_MESSAGE_PROJECT, UI_DIALOG_MESSAGE, UI_DIALOG_TITLE, UI_SELECT_BUTTON_MESSAGE,

	// Job dedicated messages

	JOB_NAME, TASK_NAME, TASK_INIT_CONTEXT, TASK_INIT_JAVA, TASK_M2M, TASK_JAVA_GENERATION, TASK_UNLOAD, TASK_CONFIG_GENERATION, TASK_CONFIG_GENERATION_ERROR,

	// Javadoc templates

	JAVADOC_PROGRAM_CLASS, JAVADOC_PIPELINE_CLASS, JAVADOC_DATA, JAVADOC_PREDICATE, JAVADOC_PROCEDURE_ENTRY_METHOD, JAVADOC_PROCEDURE_EXIT_METHOD, JAVADOC_PIPELINE_ENTRY_METHOD, JAVADOC_PIPELINE_EXIT_METHOD, JAVADOC_DATA_GETTER_METHOD, JAVADOC_DATA_SETTER_METHOD,

	// Comment templates

	COMMENT_TODO,

	;

	/**
	 * Resource Bundle instance
	 */
	private static ResourceBundle resourceBundle = ResourceBundle
			.getBundle("messages");

	/**
	 * Returns message related to this message key
	 * 
	 * @return The message corresponding to the selected key
	 */
	public String message() {
		String key = this.toString();
		return resourceBundle.containsKey(key) ? resourceBundle.getString(key)
				: key;
	}

	/**
	 * Returns the message related to this message key, formatted with provided
	 * objects.
	 * 
	 * @param objects
	 *            the params to use for resolving the message
	 * @return the message formated with the parameters
	 */
	public String message(Object... objects) {
		String key = this.toString();
		return resourceBundle.containsKey(key) ? MessageFormat.format(
				resourceBundle.getString(key), objects) : key;
	}

}
