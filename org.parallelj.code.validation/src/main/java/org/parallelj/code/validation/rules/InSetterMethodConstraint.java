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
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;


public class InSetterMethodConstraint extends AbstractParallelJCodeRule {

	@Override
	public boolean visit(FieldDeclaration node) {

		Annotation annotation = getAnnotation(node,
				ParallelJAPI.LAUNCHING_PACKAGE, ParallelJAPI.IN);

		if (annotation != null) {
			List<?> fragmentsList = node.fragments();
			if (fragmentsList.size() == 0) {
				return false;
			}
			VariableDeclarationFragment vdf = (VariableDeclarationFragment) fragmentsList
					.get(0);

			if (vdf != null && vdf.resolveBinding() != null) {
				IVariableBinding variableBinding = vdf.resolveBinding();
				checkSetterMethod(annotation, variableBinding);
			}
		}
		return false;
	}

	private void checkSetterMethod(ASTNode node,
			IVariableBinding variableBinding) {
		boolean flag = false;
		String fieldName = variableBinding.getName().substring(0, 1)
				.toUpperCase()
				+ variableBinding.getName().substring(1);
		String setterMethodName = "set" + fieldName;
		IMethodBinding methodBindingArray[] = variableBinding
				.getDeclaringClass().getDeclaredMethods();
		for (int i = 0; i < methodBindingArray.length; i++) {
			IMethodBinding methodBinding = methodBindingArray[i];
			if (setterMethodName.equals(methodBinding.getMethodDeclaration()
					.getName())) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			addErrorMarker(node,
					ValidationMessages.IN_SETTER_METHOD_NOT_FOUND.value(
							setterMethodName, variableBinding.getName()));
		}

	}

}
