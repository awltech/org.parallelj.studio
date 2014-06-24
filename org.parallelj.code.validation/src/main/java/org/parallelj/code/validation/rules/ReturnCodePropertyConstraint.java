package org.parallelj.code.validation.rules;

import java.util.HashMap;
import java.util.Map;

import org.parallelj.code.validation.core.AbstractParallelJCodeRule;

import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

/**
 * Validate the @ReturnCode property field (type + unicity + return code)
 * 
 * 
 */
public class ReturnCodePropertyConstraint extends AbstractParallelJCodeRule {

	private FieldDeclaration returnCodeDeclaration;

	private Map<String, MethodDeclaration> gatheredSetters = new HashMap<String, MethodDeclaration>();

	@Override
	public boolean visit(FieldDeclaration node) {
		if (getAnnotation(node, "org.parallelj", "ReturnCode") != null) {
			if (returnCodeDeclaration != null) {
				this.addErrorMarker(node, "Program can only contain one property annotated with ReturnCode.");
			} else {
				returnCodeDeclaration = node;
				ITypeBinding resolvedBinding = node.getType().resolveBinding();
				if (resolvedBinding != null && resolvedBinding.getPackage() != null) {
					if (!"String".equals(resolvedBinding.getName())
							|| !"java.lang".equals(resolvedBinding.getPackage().getName())) {
						this.addErrorMarker(node, "Field annotated with ReturnCode should be typed with String.");
					}
				}
				if (node.fragments().size() != 1) {
					this.addErrorMarker(node, "Program can only contain one property annotated with ReturnCode.");
				}
			}
		}
		return false;
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		String name = node.getName().getFullyQualifiedName();

		if (name.startsWith("get") && name.length() > 3 && node.parameters().size() == 0) {
			String expectedPropertyName = name.substring(3, 4).toLowerCase() + name.substring(4);
			this.gatheredSetters.put(expectedPropertyName, node);
		}
		return false;
	}

	@Override
	public void endVisit(TypeDeclaration node) {
		if (this.returnCodeDeclaration != null && this.returnCodeDeclaration.fragments().size() == 1) {
			VariableDeclarationFragment fragment = (VariableDeclarationFragment) this.returnCodeDeclaration.fragments().get(0);
			String fullyQualifiedName = fragment.getName().getFullyQualifiedName();
			if (this.gatheredSetters.get(fullyQualifiedName) == null)
				this.addErrorMarker(node, "Getter for field annotated with ReturnCode not found in current Program.");
		}
	}

}
