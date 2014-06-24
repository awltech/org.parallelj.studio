package org.parallelj.code.validation.rules;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.parallelj.code.validation.core.ParallelJAPI;
import org.parallelj.code.validation.core.ValidationMessages;



public class AndSplitValidValuesConstraint extends org.parallelj.code.validation.core.AbstractParallelJCodeRule {

	private List<String> joinMethodNames;

	@Override
	public boolean visit2(CompilationUnit node) {
		return super.visit2(node);
	}

	@Override
	public boolean visit2(TypeDeclaration node) {
		this.joinMethodNames = new ArrayList<String>();
		for (Object o : node.bodyDeclarations()) {
			if (o instanceof MethodDeclaration) {
				MethodDeclaration declaration = (MethodDeclaration) o;
				boolean hasJoinAnnotation = getAnnotation(declaration, ParallelJAPI.PACKAGE, org.parallelj.code.validation.core.ParallelJAPI.AND_JOIN) != null
						|| getAnnotation(declaration, ParallelJAPI.PACKAGE, org.parallelj.code.validation.core.ParallelJAPI.OR_JOIN) != null
						|| getAnnotation(declaration, ParallelJAPI.PACKAGE, ParallelJAPI.XOR_JOIN) != null;
				if (hasJoinAnnotation) {
					joinMethodNames.add(declaration.getName().getFullyQualifiedName());
				}
			}
		}
		return true;
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		Annotation annotation = getAnnotation(node, ParallelJAPI.PACKAGE, ParallelJAPI.AND_SPLIT);

		if (annotation != null) {
			// Here, we'll look into the AndSplit annotation, to get the list
			// for values.
			Expression value = null;
			if (annotation instanceof SingleMemberAnnotation) {
				SingleMemberAnnotation sma = (SingleMemberAnnotation) annotation;
				value = sma.getValue();
			} else if (annotation instanceof NormalAnnotation) {
				NormalAnnotation na = (NormalAnnotation) annotation;
				for (Iterator<?> iterator = na.values().iterator(); iterator.hasNext() && value == null;) {
					MemberValuePair pair = (MemberValuePair) iterator.next();
					if ("value".equals(pair.getName().getFullyQualifiedName()))
						value = pair.getValue();
				}
			}

			if (value != null) {
				if (value instanceof StringLiteral) {
					StringLiteral literal = (StringLiteral) value;
					checkLiteral(value, literal.getLiteralValue());
				} else if (value instanceof SimpleName) {
					Object resolvedConstantExpressionValue = ((SimpleName) value).resolveConstantExpressionValue();
					if (resolvedConstantExpressionValue instanceof String)
						checkLiteral(value, (String) resolvedConstantExpressionValue);
				} else if (value instanceof ArrayInitializer) {
					ArrayInitializer initializer = (ArrayInitializer) value;
					for (Object expression : initializer.expressions())
						if ((Expression) expression instanceof StringLiteral) {
							StringLiteral literal = (StringLiteral) expression;
							checkLiteral(annotation, literal.getLiteralValue());
						} else if ((Expression) expression instanceof SimpleName) {
							Object resolvedConstantExpressionValue = ((SimpleName) expression)
									.resolveConstantExpressionValue();
							if (resolvedConstantExpressionValue instanceof String)
								checkLiteral((Expression) expression, (String) resolvedConstantExpressionValue);
						}
				} else
					System.out.println("UNSUPPORTED CASE !!!");
			}
		}
		return false;
	}

	private void checkLiteral(ASTNode node, String escapedValue) {
		if (!"end".equals(escapedValue) && !this.joinMethodNames.contains(escapedValue))
			addErrorMarker(node, ValidationMessages.SPLIT_PROCEDURE_NOT_FOUND.value(escapedValue));
	}

}
