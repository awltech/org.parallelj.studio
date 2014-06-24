package org.parallelj.code.validation.core;

import java.util.Iterator;

import net.atos.jdt.ast.validation.engine.rules.AbstractASTRule;

import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public abstract class AbstractParallelJCodeRule extends AbstractASTRule {

	@Override
	public final boolean visit(TypeDeclaration node) {
		if (node.isInterface() || !(hasAnnotation(node, "org.parallelj", "Program")))
			return false;
		return this.visit2(node);
	}

	public boolean visit2(TypeDeclaration node) {
		return true;
	}

	@Override
	public boolean visit(AnnotationTypeDeclaration node) {
		return false;
	}

	@Override
	public boolean visit(EnumDeclaration node) {
		return false;
	}

	protected static boolean hasAnnotation(BodyDeclaration node, String requestedPackageName,
			String requestedAnnotationName) {
		return getAnnotation(node, requestedPackageName, requestedAnnotationName) != null;
	}

	protected static Annotation getAnnotation(BodyDeclaration node, String requestedPackageName,
			String requestedAnnotationName) {
		for (Object modifier : node.modifiers()) {
			if (modifier instanceof Annotation) {
				Annotation annotation = (Annotation) modifier;
				ITypeBinding resolvedTypeBinding = annotation.resolveTypeBinding();
				if (resolvedTypeBinding != null) {
					String typeName = resolvedTypeBinding.getName();
					String packageName = resolvedTypeBinding.getPackage().getName();
					if (requestedPackageName.equals(packageName) && requestedAnnotationName.equals(typeName))
						return annotation;
				}
			}
		}
		return null;
	}

	protected static ITypeBinding getSuperTypeBinding(ITypeBinding typeBinding, String packName, String typeName) {

		String thisBindingName = typeBinding.getName();
		// Do some cleaning if type is generic
		if (thisBindingName.indexOf("<") > -1)
			thisBindingName = thisBindingName.substring(0, thisBindingName.indexOf("<"));

		if (packName.equals(typeBinding.getPackage().getName()) && typeName.equals(thisBindingName))
			return typeBinding;

		for (ITypeBinding interfaceTypeBinding : typeBinding.getInterfaces()) {
			String typeBindingName = interfaceTypeBinding.getName();
			// Do some cleaning if type is generic
			if (typeBindingName.indexOf("<") > -1)
				typeBindingName = typeBindingName.substring(0, typeBindingName.indexOf("<"));

			if (packName.equals(interfaceTypeBinding.getPackage().getName()) && typeName.equals(typeBindingName))
				return interfaceTypeBinding;
			ITypeBinding superTypeBinding = getSuperTypeBinding(interfaceTypeBinding, packName, typeName);
			if (superTypeBinding != null)
				return superTypeBinding;
		}

		if (typeBinding.getSuperclass() != null) {
			ITypeBinding superTypeBinding = getSuperTypeBinding(typeBinding.getSuperclass(), packName, typeName);
			if (superTypeBinding != null)
				return superTypeBinding;
		}
		return null;
	}

	/**
	 * Looks for Annotation value
	 * 
	 * @param annotation
	 *            Annotation to process
	 * @param key
	 *            key to specify (If annotation is SingleMemberAnnotation, key
	 *            should be null or "value")
	 * @param value
	 *            Value to look for
	 * @return
	 */
	protected static boolean hasAnnotationValue(Annotation annotation, String key, Object value) {

		Expression expression = getAnnotationExpression(annotation, key);
		if (expression instanceof ArrayInitializer) {
			for (Object o : ((ArrayInitializer) expression).expressions()) {
				Object extracted = getExpressionValue((Expression) o);
				if (extracted == null ? value == null : extracted.equals(value))
					return true;
			}
		} else {
			Object extracted = getExpressionValue(expression);
			if (extracted == null ? value == null : extracted.equals(value))
				return true;
		}
		return false;
	}

	/**
	 * Extracts the constantExpression value of the expression. If not constant,
	 * does a simple "toString"
	 * 
	 * @param expression
	 * @return
	 */
	private static Object getExpressionValue(Expression expression) {
		Object object = expression.resolveConstantExpressionValue();
		return object != null ? object : String.valueOf(expression);
	}

	/**
	 * Retrieves the expression located in the annotation, for the specified
	 * key. If annotation is SimpleMemberAnnotation, key should be null or
	 * "value"
	 * 
	 * @param annotation
	 * @param key
	 * @return
	 */
	protected static Expression getAnnotationExpression(Annotation annotation, String key) {

		if (annotation instanceof SingleMemberAnnotation) {
			if (key == null || "value".equals(key))
				return ((SingleMemberAnnotation) annotation).getValue();
		} else if (annotation instanceof NormalAnnotation) {
			NormalAnnotation normalAnnotation = (NormalAnnotation) annotation;
			for (Iterator<?> iterator = normalAnnotation.values().iterator(); iterator.hasNext();) {
				MemberValuePair memberValuePair = (MemberValuePair) iterator.next();
				if (key.equals(memberValuePair.getName().getFullyQualifiedName())) {
					return memberValuePair.getValue();
				}
			}
		}
		return null;
	}

}
