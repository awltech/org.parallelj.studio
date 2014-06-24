package org.parallelj.code.validation.core;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public enum ValidationMessages {
	SPLIT_PROCEDURE_NOT_FOUND, EXIT_NO_PARAMETER, EXIT_RUNNABLE_ERROR, EXIT_CALLABLE_ERROR, EXIT_PROGRAM_ERROR, ON_ERROR_INVALID_TYPE, ON_ERROR_GETTER_METHOD_NOT_FOUND, HANDLER_METHOD_NOT_FOUND, IN_SETTER_METHOD_NOT_FOUND, OUT_GETTER_METHOD_NOT_FOUND, INVALID_CAPACITY_VALUE, NON_ZERO_CAPACITY_VALUE, INVALID_WHILE_PREDICATE_FIELD_TYPE, INVALID_FOREACH_PROPERTY_FIELD_TYPE, INVALID_ON_ERROR_EXCEPTION_POLICY, INVALID_ORSPLIT_PREDICATE_VALUE, INVALID_XORSPLIT_PREDICATE_VALUE, INITIAL_STATE_NOT_FOUND, FINAL_STATE_NOT_FOUND, INVALID_LINK_PREDICATE_FIELD_TYPE, NO_DUPLICATE_ERROR, NO_DEFAULT_CONSTRUCTOR_ERROR, VALIDATION_OF_FINAL_STATE, NO_END_METHOD_ALLOWED, MESSAGE_DISABLED, MESSAGE_ENABLED ;

	/*
	 * ResourceBundle instance
	 */
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("ValidationMessages");

	/*
	 * Returns value of the message
	 */
	public String value() {
		if (ValidationMessages.resourceBundle == null || !ValidationMessages.resourceBundle.containsKey(this.name()))
			return "!!" + this.name() + "!!";

		return ValidationMessages.resourceBundle.getString(this.name());
	}

	/*
	 * Returns value of the formatted message
	 */
	public String value(Object... args) {
		if (ValidationMessages.resourceBundle == null || !ValidationMessages.resourceBundle.containsKey(this.name()))
			return "!!" + this.name() + "!!";

		return MessageFormat.format(ValidationMessages.resourceBundle.getString(this.name()), args);
	}

}
