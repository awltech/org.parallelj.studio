/**
 * 
 */
package org.parallelj.code.validation.rules;

import java.util.List;

import org.parallelj.code.validation.core.AbstractParallelJCodeRule;
import org.parallelj.code.validation.core.ParallelJAPI;
import org.parallelj.code.validation.core.ValidationMessages;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;


public class OnErrorFieldTypeConstraint extends AbstractParallelJCodeRule {

	@Override
	public boolean visit(FieldDeclaration node) {

		Annotation annotation = getAnnotation(node,
				ParallelJAPI.LAUNCHING_PACKAGE, ParallelJAPI.ON_ERROR);

		if (annotation != null) {
			List<?> fragmentsList = node.fragments();
			if (fragmentsList.size() == 0) {
				return false;
			}
			VariableDeclarationFragment vdf = (VariableDeclarationFragment) fragmentsList
					.get(0);
			if (vdf != null && vdf.resolveBinding() != null) {
				IVariableBinding variableBinding = vdf.resolveBinding();
				checkType(annotation, variableBinding,
						"org.parallelj.launching.errors.ProceduresOnError");
			}
		}
		return false;

	}

	private void checkType(ASTNode node, IVariableBinding variableBinding,
			String fullQualifiedName) {

		String qualifiedName = variableBinding.getType().getQualifiedName();

		if (!fullQualifiedName.equals(qualifiedName)) {
			addErrorMarker(node,
					ValidationMessages.ON_ERROR_INVALID_TYPE
							.value(variableBinding.getName()));
		}
	}

}
