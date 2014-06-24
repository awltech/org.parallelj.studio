/**
 * 
 */
package org.parallelj.code.validation.rules;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.parallelj.code.validation.core.AbstractParallelJCodeRule;
import org.parallelj.code.validation.core.ParallelJAPI;
import org.parallelj.code.validation.core.ValidationMessages;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.TypeDeclaration;


public class HandlerMethodsConstraint extends AbstractParallelJCodeRule {

	private List<String> allMethodNames;

	@Override
	public boolean visit2(TypeDeclaration node) {
		this.allMethodNames = new ArrayList<String>();
		MethodDeclaration methods[] = node.getMethods();
		for (int i = 0; i < methods.length; i++) {
			MethodDeclaration methodDeclaration = methods[i];
			allMethodNames.add(methodDeclaration.getName()
					.getFullyQualifiedName());

		}
		return true;
	}

	@Override
	public boolean visit(MethodDeclaration node) {

		Annotation annotation = getAnnotation(node, ParallelJAPI.PACKAGE,
				ParallelJAPI.HANDLER);

		if (annotation != null) {

			Expression value = null;
			if (annotation instanceof SingleMemberAnnotation) {
				SingleMemberAnnotation sma = (SingleMemberAnnotation) annotation;
				value = sma.getValue();
			} else if (annotation instanceof NormalAnnotation) {
				NormalAnnotation na = (NormalAnnotation) annotation;
				for (Iterator<?> iterator = na.values().iterator(); iterator
						.hasNext() && value == null;) {
					MemberValuePair pair = (MemberValuePair) iterator.next();
					if ("value".equals(pair.getName().getFullyQualifiedName()))
						value = pair.getValue();
				}
			}

			if (value != null) {
				if (value instanceof StringLiteral) {
					StringLiteral literal = (StringLiteral) value;
					checkHandlerMethods(annotation, literal.getLiteralValue());
				} else if (value instanceof SimpleName) {
					Object resolvedConstantExpressionValue = ((SimpleName) value)
							.resolveConstantExpressionValue();
					if (resolvedConstantExpressionValue instanceof String)
						checkHandlerMethods(annotation,
								(String) resolvedConstantExpressionValue);
				}
				if (value instanceof ArrayInitializer) {
					ArrayInitializer initializer = (ArrayInitializer) value;
					for (Object expression : initializer.expressions()) {
						if ((Expression) expression instanceof StringLiteral) {
							StringLiteral literal = (StringLiteral) expression;
							checkHandlerMethods(annotation,
									literal.getLiteralValue());
						} else if ((Expression) expression instanceof SimpleName) {
							Object resolvedConstantExpressionValue = ((SimpleName) expression)
									.resolveConstantExpressionValue();
							if (resolvedConstantExpressionValue instanceof String)
								checkHandlerMethods(
										(Expression) expression,
										(String) resolvedConstantExpressionValue);
						}
					}
				}
			}

		}
		return false;
	}

	private void checkHandlerMethods(ASTNode node, String handlerMethodName) {
		int methodCount = 0;
		for (String methodName : this.allMethodNames) {
			if (methodName.equals(handlerMethodName)) {
				methodCount = methodCount + 1;
			}
		}
		if (methodCount != 2) {
			addErrorMarker(node,
					ValidationMessages.HANDLER_METHOD_NOT_FOUND
							.value(handlerMethodName));
		}
	}
}
