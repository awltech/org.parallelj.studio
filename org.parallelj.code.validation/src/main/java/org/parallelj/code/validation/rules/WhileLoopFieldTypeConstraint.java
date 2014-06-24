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


public class WhileLoopFieldTypeConstraint extends AbstractParallelJCodeRule {

	private List<MethodDeclaration> allMethodDeclaration;

	@Override
	public boolean visit2(TypeDeclaration node) {
		this.allMethodDeclaration = new ArrayList<MethodDeclaration>();
		MethodDeclaration methods[] = node.getMethods();
		for (int i = 0; i < methods.length; i++) {
			MethodDeclaration methodDeclaration = methods[i];
			allMethodDeclaration.add(methodDeclaration);
		}
		return true;
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		Annotation annotation = getAnnotation(node, ParallelJAPI.PACKAGE,
				ParallelJAPI.WHILE);

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
					checkWhilePredicateFieldType(annotation,
							literal.getLiteralValue());
				} else if (value instanceof SimpleName) {
					Object resolvedConstantExpressionValue = ((SimpleName) value)
							.resolveConstantExpressionValue();
					if (resolvedConstantExpressionValue instanceof String)
						checkWhilePredicateFieldType(annotation,
								(String) resolvedConstantExpressionValue);
				}
				if (value instanceof ArrayInitializer) {
					ArrayInitializer initializer = (ArrayInitializer) value;
					for (Object expression : initializer.expressions()) {
						if ((Expression) expression instanceof StringLiteral) {
							StringLiteral literal = (StringLiteral) expression;
							checkWhilePredicateFieldType(annotation,
									literal.getLiteralValue());
						} else if ((Expression) expression instanceof SimpleName) {
							Object resolvedConstantExpressionValue = ((SimpleName) expression)
									.resolveConstantExpressionValue();
							if (resolvedConstantExpressionValue instanceof String)
								checkWhilePredicateFieldType(
										(Expression) expression,
										(String) resolvedConstantExpressionValue);
						}
					}
				}
			}

		}

		return false;
	}

	private void checkWhilePredicateFieldType(ASTNode node, String predicateName) {

		String predicateFieldName = predicateName.substring(0, 1).toUpperCase()
				+ predicateName.substring(1);
		String predicateMethodName = "is" + predicateFieldName;
		for (MethodDeclaration methodDeclaration : this.allMethodDeclaration) {
			if (methodDeclaration.getName().getFullyQualifiedName()
					.equals(predicateMethodName)
					&& !methodDeclaration.getReturnType2().toString()
							.equals("boolean")) {
				addErrorMarker(node,
						ValidationMessages.INVALID_WHILE_PREDICATE_FIELD_TYPE
								.value(predicateMethodName, predicateName));
			}
		}
	}

}
