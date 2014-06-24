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


public class XorSplitLinkToValidEntryMethodConstraint extends
		AbstractParallelJCodeRule {

	private List<String> joinMethodNames;

	@Override
	public boolean visit2(TypeDeclaration node) {
		this.joinMethodNames = new ArrayList<String>();
		for (Object o : node.bodyDeclarations()) {
			if (o instanceof MethodDeclaration) {
				MethodDeclaration declaration = (MethodDeclaration) o;
				boolean hasJoinAnnotation = getAnnotation(declaration,
						ParallelJAPI.PACKAGE, ParallelJAPI.AND_JOIN) != null
						|| getAnnotation(declaration, ParallelJAPI.PACKAGE,
								ParallelJAPI.OR_JOIN) != null
						|| getAnnotation(declaration, ParallelJAPI.PACKAGE,
								ParallelJAPI.XOR_JOIN) != null;
				if (hasJoinAnnotation) {
					joinMethodNames.add(declaration.getName()
							.getFullyQualifiedName());
				}
			}
		}
		return true;
	}

	@Override
	public boolean visit(MethodDeclaration node) {

		Annotation annotation = getAnnotation(node, ParallelJAPI.PACKAGE,
				ParallelJAPI.XOR_SPLIT);
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
									if ("to".equals(pair.getName()
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

	private void checkLiteral(ASTNode node, String escapedValue) {
		if (!"end".equals(escapedValue)
				&& !this.joinMethodNames.contains(escapedValue)) {
			addErrorMarker(node,
					ValidationMessages.SPLIT_PROCEDURE_NOT_FOUND
							.value(escapedValue));
		}
	}
}
