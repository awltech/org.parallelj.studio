package org.parallelj.code.validation.rules;

import java.util.List;

import org.parallelj.code.validation.core.AbstractParallelJCodeRule;
import org.parallelj.code.validation.core.ParallelJAPI;
import org.parallelj.code.validation.core.ValidationMessages;

import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.IAnnotationBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

public class ExitMethodsParametersConstraint extends AbstractParallelJCodeRule {

	@Override
	public boolean visit(MethodDeclaration node) {
		// Check we are in an Exit Method
		Annotation annotation = getAnnotation(node, ParallelJAPI.PACKAGE,
				"AndSplit");
		if (annotation == null)
			annotation = getAnnotation(node, ParallelJAPI.PACKAGE, "OrSplit");
		if (annotation == null)
			annotation = getAnnotation(node, ParallelJAPI.PACKAGE, "XorSplit");
		if (annotation == null)
			return false;

		List<?> parameters = node.parameters();
		if (parameters.size() == 0) {
			return false;
		}
		SingleVariableDeclaration svd = (SingleVariableDeclaration) parameters
				.get(0);
		ITypeBinding typeBinding = svd.getType().resolveBinding();
		if (typeBinding == null)
			return false;

		ITypeBinding runnableType = getSuperTypeBinding(typeBinding,
				"java.lang", "Runnable");
		ITypeBinding callableType = getSuperTypeBinding(typeBinding,
				"java.util.concurrent", "Callable");

		if (runnableType != null) {
			if (parameters.size() != 1)
				addErrorMarker(node.getName(),
						ValidationMessages.EXIT_RUNNABLE_ERROR.value());
			return false;
		} else if (callableType != null) {
			if (parameters.size() != 2) {
				addErrorMarker(node.getName(),
						ValidationMessages.EXIT_CALLABLE_ERROR.value());
				return false;
			} else {
				// TODO Test the type of the second parameter
			}
		} else {
			boolean hasProgramAnnotation = false;
			for (int i = 0; i < typeBinding.getAnnotations().length
					&& !hasProgramAnnotation; i++) {
				IAnnotationBinding annotationBinding = typeBinding
						.getAnnotations()[i];
				ITypeBinding annotationTypeBinding = annotationBinding
						.getAnnotationType();
				if ("Program".equals(annotationTypeBinding.getName())
						&& ParallelJAPI.PACKAGE.equals(annotationTypeBinding
								.getPackage().getName())) {
					hasProgramAnnotation = true;
				}
			}
			if (hasProgramAnnotation && parameters.size() != 1) {
				addErrorMarker(node.getName(),
						ValidationMessages.EXIT_PROGRAM_ERROR.value());
			}
		}
		return false;
	}

}
