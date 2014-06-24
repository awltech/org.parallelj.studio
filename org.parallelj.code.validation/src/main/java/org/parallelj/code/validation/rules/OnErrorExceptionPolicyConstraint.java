/**
 * 
 */
package org.parallelj.code.validation.rules;

import org.parallelj.code.validation.core.AbstractParallelJCodeRule;
import org.parallelj.code.validation.core.ParallelJAPI;
import org.parallelj.code.validation.core.ValidationMessages;

import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IAnnotationBinding;
import org.eclipse.jdt.core.dom.IMemberValuePairBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class OnErrorExceptionPolicyConstraint extends AbstractParallelJCodeRule {

	@Override
	public boolean visit2(TypeDeclaration node) {
		boolean hasOnErrorAnnotation = false;
		for (Object o : node.bodyDeclarations()) {
			if (o instanceof FieldDeclaration) {
				FieldDeclaration declaration = (FieldDeclaration) o;
				hasOnErrorAnnotation = getAnnotation(declaration,
						ParallelJAPI.LAUNCHING_PACKAGE, ParallelJAPI.ON_ERROR) != null;
			}
		}
		if (hasOnErrorAnnotation) {
			ITypeBinding typeBinding = node.resolveBinding();
			for (IAnnotationBinding annotationBinding : typeBinding
					.getAnnotations()) {
				ITypeBinding annotationTypeBinding = annotationBinding
						.getAnnotationType();
				if ("Program".equals(annotationTypeBinding.getName())
						&& ParallelJAPI.PACKAGE.equals(annotationTypeBinding
								.getPackage().getName())) {
					IMemberValuePairBinding[] memberValuePairBinding = annotationBinding
							.getAllMemberValuePairs();
					for (IMemberValuePairBinding valuePairBinding : memberValuePairBinding) {
						if (valuePairBinding.getName().equals(
								"exceptionHandlingPolicy")) {
							IVariableBinding variableBinding = (IVariableBinding) valuePairBinding
									.getValue();
							if (variableBinding != null
									&& !variableBinding.getName().equals(
											"RESUME")) {
								addWarningMarker(
										node,
										ValidationMessages.INVALID_ON_ERROR_EXCEPTION_POLICY
												.value(node.getName()));
							}
						}

					}
				}
			}
		}

		return false;
	}
}
