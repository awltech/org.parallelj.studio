/*
 *     ParallelJ, framework for parallel computing
 *     
 *     Copyright (C) 2010 Atos Worldline or third-party contributors as
 *     indicated by the @author tags or express copyright attribution
 *     statements applied by the authors.
 *     
 *     This library is free software; you can redistribute it and/or
 *     modify it under the terms of the GNU Lesser General Public
 *     License as published by the Free Software Foundation; either
 *     version 2.1 of the License.
 *     
 *     This library is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *     Lesser General Public License for more details.
 *     
 *     You should have received a copy of the GNU Lesser General Public
 *     License along with this library; if not, write to the Free Software
 *     Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */
package org.parallelj.common.jdt.checkers;

import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getAnnotation;

import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.StringLiteral;

/**
 * Defines several methods used to perform Java element (type, field, method)
 * checking.
 * 
 * @author Atos Worldline
 */
class ElementChecker {

	private BodyDeclaration bd = null;

	/**
	 * Construct an ElementChecker with a BodyDeclaration as single parameter.
	 * 
	 * @param bd
	 *            A JDT BodyDeclaration object
	 */
	protected ElementChecker(BodyDeclaration bd) {
		this.bd = bd;
	}

	/**
	 * Return true if this element contains a marker annotation (@Foo).
	 * 
	 * @param fullyQualifiedAnnotationName
	 *            Annotation type fully qualified name
	 * @return true if this element contains a marker annotation, false
	 *         otherwise
	 */
	public boolean isAnnotated(String fullyQualifiedAnnotationName) {
		return this.bodyDeclarationContainsAnnotation(this.bd, fullyQualifiedAnnotationName);
	}

	/**
	 * Return true if this element contains a single member annotation
	 * (@Foo("xxx")).
	 * 
	 * @param fullyQualifiedAnnotationName
	 *            Annotation type fully qualified name
	 * @param value
	 *            Value expected as single member
	 * @return true if this element contains a single member annotation, false
	 *         otherwise
	 */
	public boolean isAnnotated(String fullyQualifiedAnnotationName, String value) {
		return this.bodyDeclarationContainsAnnotation(this.bd, fullyQualifiedAnnotationName, value);
	}

	/**
	 * Return true if this element contains a normal annotation (@Foo(f1 = v1 ,
	 * f2 = v2)).
	 * 
	 * @param fullyQualifiedAnnotationName
	 *            Annotation type fully qualified name
	 * @param content
	 *            A Map object containing as key the expected member name and as
	 *            value the expected member value
	 * @return true if this element contains a normal annotation, false
	 *         otherwise
	 */
	public boolean isAnnotated(String fullyQualifiedAnnotationName, Map<String, Object> content) {
		return this.bodyDeclarationContainsAnnotation(this.bd, fullyQualifiedAnnotationName,
				content);
	}

	/**
	 * Return true if this element contains a javadoc.
	 * 
	 * @return true if this element contains a javadoc, false otherwise
	 */
	public boolean containsJavadoc() {
		return (this.bd.getJavadoc() != null);
	}

	/**
	 * Return true if this element is final.
	 * 
	 * @return true if this element is final, false otherwise
	 */
	public boolean isFinal() {
		return Modifier.isFinal(this.bd.getModifiers());
	}

	/**
	 * Return true if this element is static.
	 * 
	 * @return true if this element is static, false otherwise
	 */
	public boolean isStatic() {
		return Modifier.isStatic(this.bd.getModifiers());
	}

	/**
	 * Return true if this element is private.
	 * 
	 * @return true if this element is private, false otherwise
	 */
	public boolean isPrivate() {
		return Modifier.isPrivate(this.bd.getModifiers());
	}

	/**
	 * Return true if this element is protected.
	 * 
	 * @return true if this element is protected, false otherwise
	 */
	public boolean isProtected() {
		return Modifier.isProtected(this.bd.getModifiers());
	}

	/**
	 * Return true if this element is public.
	 * 
	 * @return true if this element is public, false otherwise
	 */
	public boolean isPublic() {
		return Modifier.isPublic(this.bd.getModifiers());
	}

	/**
	 * Return true if this body declaration contains a marker annotation (@Foo)
	 * 
	 * @param bd
	 *            Body Declaration used as input
	 * @param annotationFullyQualifiedName
	 *            Annotation fully qualified name
	 * @return true if this body declaration contains a marker annotation, false
	 *         otherwise
	 */
	private boolean bodyDeclarationContainsAnnotation(BodyDeclaration bd,
			String annotationFullyQualifiedName) {
		if (bd != null) {
			return (getAnnotation(bd, annotationFullyQualifiedName) != null);
		}

		return false;
	}

	/**
	 * Return true if this body declaration contains a single member annotation
	 * (@Foo("xxx")
	 * 
	 * @param bd
	 *            Body Declaration used as input
	 * @param annotationFullyQualifiedName
	 *            Annotation fully qualified name
	 * @param value
	 *            Value expected as single member
	 * @return true if this body declaration contains a single member
	 *         annotation, false otherwise
	 */
	private boolean bodyDeclarationContainsAnnotation(BodyDeclaration bd,
			String annotationFullyQualifiedName, String value) {
		if (bd != null) {
			Annotation a = getAnnotation(bd, annotationFullyQualifiedName);

			// Test if this annotation is a Single Member Annotation and the
			// value is the right value
			if (a != null && a.isSingleMemberAnnotation()) {
				Expression exp = ((SingleMemberAnnotation) a).getValue();

				return ((exp instanceof StringLiteral) && ((StringLiteral) exp).getLiteralValue()
						.equals(value));
				// TODO : Support more than only String literal
			}
		}

		return false;
	}

	/**
	 * Return true if this body declaration contains a normal annotation
	 * (@Foo(f1 = v1 , f2 = v2)
	 * 
	 * @param bd
	 *            Body Declaration used as input
	 * @param annotationFullyQualifiedName
	 *            Annotation fully qualified name
	 * @param content
	 *            A Map object containing as key the expected member name and as
	 *            value the expected member value
	 * @return true if this body declaration contains a normal annotation, false
	 *         otherwise
	 */
	private boolean bodyDeclarationContainsAnnotation(BodyDeclaration bd,
			String annotationFullyQualifiedName, Map<String, Object> content) {
		boolean correct = false;

		if (bd != null) {
			Annotation a = getAnnotation(bd, annotationFullyQualifiedName);

			// Test if this annotation is a Normal Member Annotation
			if (a != null && a.isNormalAnnotation()) {
				List<MemberValuePair> values = ((NormalAnnotation) a).values();
				correct = true;

				for (int inc = 0; inc < values.size() && correct; inc++) {
					MemberValuePair mvp = values.get(inc);
					String memberName = mvp.getName().getFullyQualifiedName();
					Object contentValue = content.get(memberName);
					correct = contentValue != null;

					Expression memberValue = mvp.getValue();

					if (memberValue != null) {
						if (contentValue instanceof String) {
							correct = ((memberValue instanceof StringLiteral) && (contentValue
									.equals(((StringLiteral) memberValue).getLiteralValue())));
						}

						// TODO : Support more than only String literal
					}
				}
			}
		}

		return correct;
	}
}
