package org.parallelj.code.validation.rules;

/**
 *
 * 
 * In a ParallelJ program, there should be at least one method that holds one of the following annotations 
 * (with specific value, of course)
 @AndSplit(value="end")
 @XorSplit(value="end")
 @OrSplit(value="end")

 This validation rule is the counterpart of the InitialStateValidationConstraint
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.parallelj.code.validation.core.AbstractParallelJCodeRule;
import org.parallelj.code.validation.core.ParallelJAPI;
import org.parallelj.code.validation.core.ValidationMessages;

import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.IAnnotationBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class ValidationOfFinalState extends AbstractParallelJCodeRule {

	private static final String END = "end";
	private static final String VALUE = "value";
	private static final String TO = "to";
	private List<String> splitMethodNames;

	@Override
	public boolean visit2(TypeDeclaration node) {

		this.splitMethodNames = new ArrayList<String>();
		for (Object o : node.bodyDeclarations()) {
			if (o instanceof MethodDeclaration) {
				MethodDeclaration declaration = (MethodDeclaration) o;
				boolean hasSplitAnnotation = getAnnotation(declaration, ParallelJAPI.PACKAGE, ParallelJAPI.AND_SPLIT) != null
						|| getAnnotation(declaration, ParallelJAPI.PACKAGE, ParallelJAPI.OR_SPLIT) != null
						|| getAnnotation(declaration, ParallelJAPI.PACKAGE, ParallelJAPI.XOR_SPLIT) != null;
				if (hasSplitAnnotation) {
					splitMethodNames.add(declaration.getName().getFullyQualifiedName());
				}
			}
		}

		ITypeBinding typeBinding = node.resolveBinding();
		boolean hasEndAnnotation = false;
		for (IAnnotationBinding annotationBinding : typeBinding.getAnnotations()) {
			ITypeBinding annotationTypeBinding = annotationBinding.getAnnotationType();
			if (ParallelJAPI.PROGRAM.equals(annotationTypeBinding.getName())
					&& ParallelJAPI.PACKAGE.equals(annotationTypeBinding.getPackage().getName())) {
				Iterator<?> iterator = node.bodyDeclarations().iterator();
				while (iterator.hasNext() && !hasEndAnnotation) {
					BodyDeclaration declaration = (BodyDeclaration) iterator.next();
					if (declaration instanceof MethodDeclaration) {
						Annotation splitAnnotation = getAnnotation(declaration, ParallelJAPI.PACKAGE,
								ParallelJAPI.AND_SPLIT);
						if (splitAnnotation != null) {
							if (hasAnnotationValue(splitAnnotation, VALUE, END)) {
								hasEndAnnotation = true;
								break;
							}
						}

						Annotation orXorAnnotation = getAnnotation(declaration, ParallelJAPI.PACKAGE,
								ParallelJAPI.OR_SPLIT);
						if (orXorAnnotation == null) {
							orXorAnnotation = getAnnotation(declaration, ParallelJAPI.PACKAGE, ParallelJAPI.XOR_SPLIT);
						}
						if (orXorAnnotation != null) {
							// OrSplit and XorSplit work with @Link
							// annotations... So we need to extract them !
							Expression annotationValue = getAnnotationExpression(orXorAnnotation, VALUE);
							if (annotationValue instanceof Annotation) {
								Annotation subAnnotation = (Annotation) annotationValue;
								if (hasAnnotationValue(subAnnotation, TO, END))
									hasEndAnnotation = true;
							} else if (annotationValue instanceof ArrayInitializer) {
								ArrayInitializer arrayInitializer = (ArrayInitializer) annotationValue;
								for (Object o : arrayInitializer.expressions()) {
									if (o instanceof Annotation) {
										Annotation subAnnotation = (Annotation) o;
										if (hasAnnotationValue(subAnnotation, TO, END)) {
											hasEndAnnotation = true;
											break;
										}
									}
								}
							}
						}
					}
				}
				if (!hasEndAnnotation) {
					addErrorMarker(node.getName(), ValidationMessages.VALIDATION_OF_FINAL_STATE.value(node.getName()));
				}
			}

		}

		return false;
	}
}
