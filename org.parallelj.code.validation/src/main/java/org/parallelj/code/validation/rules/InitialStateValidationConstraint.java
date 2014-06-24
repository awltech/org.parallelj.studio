/**
 * 
 */
package org.parallelj.code.validation.rules;

import java.util.ArrayList;
import java.util.List;

import org.parallelj.code.validation.core.AbstractParallelJCodeRule;
import org.parallelj.code.validation.core.ParallelJAPI;
import org.parallelj.code.validation.core.ValidationMessages;

import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.IAnnotationBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.TypeDeclaration;


public class InitialStateValidationConstraint extends AbstractParallelJCodeRule {

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

		ITypeBinding typeBinding = node.resolveBinding();
		for (IAnnotationBinding annotationBinding : typeBinding
				.getAnnotations()) {
			ITypeBinding annotationTypeBinding = annotationBinding
					.getAnnotationType();
			if (ParallelJAPI.PROGRAM.equals(annotationTypeBinding.getName())
					&& ParallelJAPI.PACKAGE.equals(annotationTypeBinding
							.getPackage().getName())) {
				boolean hasBeginAnnotation = false;
				String joinAnnotationValue = "begin";
				for (Object object : node.bodyDeclarations()) {
					if (object instanceof MethodDeclaration) {
						MethodDeclaration declaration = (MethodDeclaration) object;
						hasBeginAnnotation = getAnnotation(declaration,
								ParallelJAPI.PACKAGE, ParallelJAPI.BEGIN) != null;
						if (hasBeginAnnotation) {
							break;
						} else {
							Annotation annotation = getAnnotation(declaration,
									ParallelJAPI.PACKAGE, ParallelJAPI.AND_JOIN);
							if (annotation == null)
								annotation = getAnnotation(declaration,
										ParallelJAPI.PACKAGE,
										ParallelJAPI.OR_JOIN);
							if (annotation == null)
								getAnnotation(declaration,
										ParallelJAPI.PACKAGE,
										ParallelJAPI.XOR_JOIN);
							if (annotation instanceof SingleMemberAnnotation) {
								SingleMemberAnnotation sma = (SingleMemberAnnotation) annotation;
								Expression value = sma.getValue();
								if (value != null) {
									if (value instanceof StringLiteral) {
										StringLiteral literal = (StringLiteral) value;
										joinAnnotationValue = literal
												.getLiteralValue();
										if (joinAnnotationValue.equals("begin")) {
											hasBeginAnnotation = true;
											break;
										}

									}
								}
							}
						}
					}
				}
				if (!hasBeginAnnotation) {
					addErrorMarker(node.getName(),
							ValidationMessages.INITIAL_STATE_NOT_FOUND
									.value(node.getName()));
				}
			}

		}

		return false;
	}
}
