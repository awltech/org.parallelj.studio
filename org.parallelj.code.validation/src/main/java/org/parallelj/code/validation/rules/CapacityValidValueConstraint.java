/**
 * 
 */
package org.parallelj.code.validation.rules;

import java.util.Iterator;

import org.parallelj.code.validation.core.AbstractParallelJCodeRule;
import org.parallelj.code.validation.core.ParallelJAPI;
import org.parallelj.code.validation.core.ValidationMessages;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;


public class CapacityValidValueConstraint extends AbstractParallelJCodeRule {

	@Override
	public boolean visit(MethodDeclaration node) {
		Annotation annotation = getAnnotation(node, ParallelJAPI.PACKAGE,
				ParallelJAPI.CAPACITY);

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
				if (value instanceof NumberLiteral) {
					NumberLiteral numberLiteral = (NumberLiteral) value;
					String capacity = numberLiteral.getToken();
					checkCapacity(annotation, node.getName()
							.getFullyQualifiedName(), capacity);

				} else {
					addErrorMarker(annotation,
							ValidationMessages.INVALID_CAPACITY_VALUE
									.value(node.getName()
											.getFullyQualifiedName()));
				}

			}
		}

		return false;
	}

	private void checkCapacity(ASTNode node, String methodName, String capacity) {
		if (Integer.parseInt(capacity) == 0) {
			addErrorMarker(node,
					ValidationMessages.NON_ZERO_CAPACITY_VALUE
							.value(methodName));
		}

	}
}
