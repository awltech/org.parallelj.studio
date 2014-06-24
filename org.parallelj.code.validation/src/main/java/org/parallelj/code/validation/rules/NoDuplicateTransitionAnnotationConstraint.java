package org.parallelj.code.validation.rules;

import org.parallelj.code.validation.core.AbstractParallelJCodeRule;
import org.parallelj.code.validation.core.ValidationMessages;

import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;

/**
 * This constraint validates that one entry/exit method has only one transition
 * related annotation.
 * 
 *  
 */
public class NoDuplicateTransitionAnnotationConstraint extends AbstractParallelJCodeRule {

	private static final String PACKAGE_NAME = "org.parallelj";

	private static final String[] JOIN_ANNOTATION_NAMES = { "AndJoin", "OrJoin", "XorJoin", "Begin" };

	private static final String[] SPLIT_ANNOTATION_NAMES = { "XorSplit", "OrSplit", "AndSplit", "End" };

	@Override
	public boolean visit(FieldDeclaration node) {
		return false;
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		Annotation firstAnnotationFound = null;
		for (int i = 0; i < JOIN_ANNOTATION_NAMES.length; i++) {
			Annotation annotation = getAnnotation(node, PACKAGE_NAME, JOIN_ANNOTATION_NAMES[i]);
			if (annotation != null) {
				if (firstAnnotationFound == null) {
					firstAnnotationFound = annotation;
				} else {
					addErrorMarker(node.getName(), ValidationMessages.NO_DUPLICATE_ERROR.value(firstAnnotationFound
							.getTypeName().getFullyQualifiedName(), annotation.getTypeName().getFullyQualifiedName()));
					return false;
				}
			}
		}
		firstAnnotationFound = null;
		for (int i = 0; i < SPLIT_ANNOTATION_NAMES.length; i++) {
			Annotation annotation = getAnnotation(node, PACKAGE_NAME, SPLIT_ANNOTATION_NAMES[i]);
			if (annotation != null) {
				if (firstAnnotationFound == null) {
					firstAnnotationFound = annotation;
				} else {
					addErrorMarker(node.getName(), ValidationMessages.NO_DUPLICATE_ERROR.value(firstAnnotationFound
							.getTypeName().getFullyQualifiedName(), annotation.getTypeName().getFullyQualifiedName()));
					return false;
				}
			}
		}
		return false;
	}

}
