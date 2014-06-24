package org.parallelj.code.validation.rules;

import org.parallelj.code.validation.core.AbstractParallelJCodeRule;
import org.parallelj.code.validation.core.ValidationMessages;

import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class DefaultConstructorAvailableConstraint extends AbstractParallelJCodeRule {

	private int constructorNumber;

	private boolean hasDefaultConstructor;

	@Override
	public boolean visit2(TypeDeclaration node) {
		this.constructorNumber = 0;
		this.hasDefaultConstructor = false;
		return true;
	}

	@Override
	public boolean visit(FieldDeclaration node) {
		// Implemented for performance issues.
		return false;
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		if (node.isConstructor()) {
			this.constructorNumber++;
			if (node.parameters().size() == 0)
				hasDefaultConstructor = true;
		}
		return false;
	}

	@Override
	public void endVisit(TypeDeclaration node) {
		if (this.constructorNumber > 0 && !hasDefaultConstructor) {
			addErrorMarker(node.getName(), ValidationMessages.NO_DEFAULT_CONSTRUCTOR_ERROR.value());
		}
	}

}
