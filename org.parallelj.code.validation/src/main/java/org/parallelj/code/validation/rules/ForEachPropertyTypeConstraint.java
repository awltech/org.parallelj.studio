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
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;


public class ForEachPropertyTypeConstraint extends AbstractParallelJCodeRule {

	private List<FieldDeclaration> allFieldDeclaration;

	@Override
	public boolean visit2(TypeDeclaration node) {
		this.allFieldDeclaration = new ArrayList<FieldDeclaration>();
		FieldDeclaration fields[] = node.getFields();
		for (int i = 0; i < fields.length; i++) {
			FieldDeclaration fieldDeclaration = fields[i];
			allFieldDeclaration.add(fieldDeclaration);
		}
		return true;
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		List<?> parameters = node.parameters();
		if (parameters.size() == 0) {
			return false;
		}
		SingleVariableDeclaration svd = (SingleVariableDeclaration) parameters
				.get(0);
		List<?> modifiersList = svd.modifiers();
		if (modifiersList.size() == 0) {
			return false;
		}
		Expression value = null;
		Object object = modifiersList.get(0);
		if (object instanceof SingleMemberAnnotation) {
			SingleMemberAnnotation sma = (SingleMemberAnnotation) object;
			if (sma.getTypeName().getFullyQualifiedName()
					.equals(ParallelJAPI.FOR_EACH)) {
				value = sma.getValue();
			}
		}
		if (object instanceof NormalAnnotation) {
			NormalAnnotation na = (NormalAnnotation) object;
			if (na.getTypeName().getFullyQualifiedName()
					.equals(ParallelJAPI.FOR_EACH)) {
				for (Iterator<?> iterator = na.values().iterator(); iterator
						.hasNext() && value == null;) {
					MemberValuePair pair = (MemberValuePair) iterator.next();
					if ("value".equals(pair.getName().getFullyQualifiedName()))
						value = pair.getValue();
				}
			}
		}
		if (value instanceof StringLiteral) {
			StringLiteral literal = (StringLiteral) value;
			checkForEachPropertyFieldType(value, literal.getLiteralValue());
		} else if (value instanceof SimpleName) {
			Object resolvedConstantExpressionValue = ((SimpleName) value)
					.resolveConstantExpressionValue();
			if (resolvedConstantExpressionValue instanceof String)
				checkForEachPropertyFieldType(value,
						(String) resolvedConstantExpressionValue);
		} else
			System.out.println("UNSUPPORTED CASE !!!");

		return false;
	}

	private void checkForEachPropertyFieldType(ASTNode node,
			String propertyFieldName) {
		for (FieldDeclaration fieldDeclaration : this.allFieldDeclaration) {
			VariableDeclarationFragment vdf = (VariableDeclarationFragment) fieldDeclaration
					.fragments().get(0);
			if (vdf.getName().getFullyQualifiedName().equals(propertyFieldName)) {
				ITypeBinding typeBinding = vdf.resolveBinding().getType();
				ITypeBinding iterableType = getSuperTypeBinding(typeBinding,
						"java.lang", "Iterable");
				if (iterableType == null) {
					addErrorMarker(
							node,
							ValidationMessages.INVALID_FOREACH_PROPERTY_FIELD_TYPE
									.value(propertyFieldName));
				}
			}
		}
	}

}
