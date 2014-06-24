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
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.TypeDeclaration;


public class OrSplitLinkPredicateValueValidMethodConstraint extends
		AbstractParallelJCodeRule {

	private List<String> allMethodNames;
	private List<MethodDeclaration> allMethodDeclaration;

	@Override
	public boolean visit2(TypeDeclaration node) {
		this.allMethodNames = new ArrayList<String>();
		this.allMethodDeclaration = new ArrayList<MethodDeclaration>();
		for (Object object : node.bodyDeclarations()) {
			if (object instanceof MethodDeclaration) {
				MethodDeclaration methodDeclaration = (MethodDeclaration) object;
				allMethodDeclaration.add(methodDeclaration);
				allMethodNames.add(methodDeclaration.getName()
						.getFullyQualifiedName());
			}
		}
		return true;
	}

	@Override
	public boolean visit(MethodDeclaration node) {

		Annotation annotation = getAnnotation(node, ParallelJAPI.PACKAGE,
				ParallelJAPI.OR_SPLIT);
		if (annotation != null) {
			Expression outerAnnotationValue = null;
			Expression innerAnnotationValue = null;
			if (annotation instanceof SingleMemberAnnotation) {
				SingleMemberAnnotation sma = (SingleMemberAnnotation) annotation;
				outerAnnotationValue = sma.getValue();
			} else if (annotation instanceof NormalAnnotation) {
				NormalAnnotation na = (NormalAnnotation) annotation;
				for (Iterator<?> iterator = na.values().iterator(); iterator
						.hasNext() && outerAnnotationValue == null;) {
					MemberValuePair pair = (MemberValuePair) iterator.next();
					if ("value".equals(pair.getName().getFullyQualifiedName()))
						outerAnnotationValue = pair.getValue();
				}
			}

			if (outerAnnotationValue != null) {
				if (outerAnnotationValue instanceof ArrayInitializer) {
					ArrayInitializer initializer = (ArrayInitializer) outerAnnotationValue;
					for (Object expression : initializer.expressions()) {
						if ((Expression) expression instanceof NormalAnnotation) {
							NormalAnnotation normalAnnotation = (NormalAnnotation) expression;
							if (normalAnnotation.getTypeName()
									.getFullyQualifiedName().equals("Link")) {
								for (Iterator<?> iterator = normalAnnotation
										.values().iterator(); iterator
										.hasNext();) {
									MemberValuePair pair = (MemberValuePair) iterator
											.next();
									if ("predicate".equals(pair.getName()
											.getFullyQualifiedName())) {
										innerAnnotationValue = pair.getValue();
										if (innerAnnotationValue instanceof StringLiteral) {
											StringLiteral literal = (StringLiteral) innerAnnotationValue;
											checkLiteral(normalAnnotation,
													literal.getLiteralValue());

										}
									}
								}
							}

						}
					}
				} else
					System.out.println("UNSUPPORTED CASE !!!");
			}

		}

		return false;
	}

	private void checkLiteral(ASTNode node, String predicateValue) {
		if (predicateValue.length() != 0) {
			String predicateFieldName = predicateValue.substring(0, 1)
					.toUpperCase() + predicateValue.substring(1);
			String predicateMethodName = "is" + predicateFieldName;
			if (!this.allMethodNames.contains(predicateMethodName)) {
				addErrorMarker(node,
						ValidationMessages.INVALID_ORSPLIT_PREDICATE_VALUE
								.value(predicateMethodName, predicateValue));

			} else {
				for (MethodDeclaration methodDeclaration : this.allMethodDeclaration) {
					if (methodDeclaration.getName().getFullyQualifiedName()
							.equals(predicateMethodName)
							&& !methodDeclaration.getReturnType2().toString()
									.equals("boolean")) {
						addErrorMarker(
								methodDeclaration.getReturnType2(),
								ValidationMessages.INVALID_LINK_PREDICATE_FIELD_TYPE
										.value(predicateMethodName,
												predicateValue));
					}
				}
			}
		}

	}
}
