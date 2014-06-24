package org.parallelj.code.validation.rules;

/**
 * end is a reserved name for a parallelj program's procedure.
 Hence, there should not be any method named end() annotated with one of the Transition Annotations i.e. one of 

 @Begin
 @End
 @AndSplit
 @OrSplit
 @XorSplit
 @AndJoin
 @OrJoin
 @XorJoin

 * 
 */
import org.parallelj.code.validation.core.AbstractParallelJCodeRule;
import org.parallelj.code.validation.core.ParallelJAPI;
import org.parallelj.code.validation.core.ValidationMessages;

import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class NoEndMethodAllowed extends AbstractParallelJCodeRule {
	private static final String[] ANNOTATION_NAMES = { "AndJoin", "OrJoin", "XorJoin", "Begin", "XorSplit", "OrSplit",
			"AndSplit", "End" };

	@Override
	public boolean visit2(TypeDeclaration node) {
		boolean isEndMethod = false;
		for (Object o : node.bodyDeclarations()) {
			if (o instanceof MethodDeclaration) {
				MethodDeclaration declaration = (MethodDeclaration) o;
				if (declaration.getName().getFullyQualifiedName().equals("end")) {
					/**
					 * Step 1: Checked for method has name "end" Step 2: true
					 * then checked for list of annotation it has. Step 3: if
					 * found true, violate case.
					 * 
					 */
					for (int i = 0; i < ANNOTATION_NAMES.length; i++) {
						Annotation annotation = getAnnotation(declaration, ParallelJAPI.PACKAGE, ANNOTATION_NAMES[i]);
						if (annotation != null) {
							isEndMethod = true;
							break;
						}
					}
				}
			}
		}
		if (isEndMethod != false) {
			addErrorMarker(node.getName(), ValidationMessages.NO_END_METHOD_ALLOWED.value(node.getName()));
		}
		return false;
	}
}
