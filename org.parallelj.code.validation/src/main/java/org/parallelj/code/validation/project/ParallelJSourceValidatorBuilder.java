package org.parallelj.code.validation.project;

import net.atos.jdt.ast.validation.engine.project.ValidationProjectBuilder;

public class ParallelJSourceValidatorBuilder extends ValidationProjectBuilder {

	private static final String[] validRepositories = new String[] { "org.parallelj.code.validation.rule-repository" };

	@Override
	public String[] getValidRepositories() {
		return validRepositories;
	}

}
