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

import static org.parallelj.common.jdt.helpers.JavaCodeHelper.areTypesEquals;
import static org.parallelj.common.jdt.helpers.JavaCodeHelper.checkAnnotationContent;
import static org.parallelj.common.jdt.helpers.JavaCodeHelper.checkSingleAnnotationValue;
import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getAnnotation;
import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getSimpleName;

import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTMatcher;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

/**
 * Defines several methods used to perform Java class method checking.
 * 
 * @author Atos Worldline
 */
public class MethodChecker extends ElementChecker {

	private MethodDeclaration md = null;

	/**
	 * Construct a MethodChecker with a MethodDeclaration as single parameter.
	 * 
	 * @param md
	 *            A JDT MethodDeclaration object
	 * @throws IllegalArgumentException
	 *             If {@code md} is null
	 */
	MethodChecker(MethodDeclaration md) {
		super(md);

		if (md == null) {
			throw new IllegalArgumentException(
					"Cannot create a MethodChecker with a null MethodDeclaration");
		}

		this.md = md;
	}

	/**
	 * Return true if this method is abstract.
	 * 
	 * @return true if this method is abstract, false otherwise
	 */
	public boolean isAbstract() {
		return Modifier.isAbstract(this.md.getModifiers());
	}

	/**
	 * Return true if this method is synchronized.
	 * 
	 * @return true if this method is synchronized, false otherwise
	 */
	public boolean isSynchronized() {
		return Modifier.isSynchronized(this.md.getModifiers());
	}

	/**
	 * Return true if this method is native.
	 * 
	 * @return true if this method is native, false otherwise
	 */
	public boolean isNative() {
		return Modifier.isNative(this.md.getModifiers());
	}

	/**
	 * Return true if this method return a type named
	 * {@code returnTypeFullyQualifiedName}.
	 * 
	 * @return true if this method return a type named
	 *         {@code returnTypeFullyQualifiedName}, false otherwise
	 */
	public boolean returnsType(String returnTypeFullyQualifiedName) {
		return areTypesEquals(this.md.getReturnType2(), returnTypeFullyQualifiedName);
	}

	/**
	 * Checks if a parameter with name and type passed as parameter is existing
	 * in the method
	 * 
	 * @param paramTypeFQN:
	 *            Type's FQN of the parameter
	 * @param paramName
	 *            name of the parameter
	 * @return true if a parameter is matching in the Method's signature, false
	 *         otherwise.
	 */
	public boolean containsParamInSignature(String paramTypeFQN, String paramName) {
		for (Object o : this.md.parameters()) {
			if (o instanceof SingleVariableDeclaration) {
				SingleVariableDeclaration s = (SingleVariableDeclaration) o;
				if (s.getName().getFullyQualifiedName().equals(paramName)
						&& areTypesEquals(s.getType(), paramTypeFQN))
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if a parameter with name passed as parameter is final
	 * 
	 * @param paramName
	 *            name of the parameter
	 * @return true if the parameter is final, false otherwise.
	 */
	public boolean paramIsFinal(String paramName) {
		for (Object o : this.md.parameters()) {
			if (o instanceof SingleVariableDeclaration) {
				SingleVariableDeclaration s = (SingleVariableDeclaration) o;
				if (s.getName().getFullyQualifiedName().equals(paramName)) {
					List<IExtendedModifier> ems = s.modifiers();
					
					for (IExtendedModifier m : ems) {
						if(m instanceof Modifier) {
							Modifier modifier = (Modifier)m;
							if(modifier.isFinal()) return true;
						}
					}
					
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * Checks if a parameter with name passed as parameter is annotated
	 * 
	 * @param paramName
	 *            name of the parameter
	 * @return true if the parameter is annotated, false otherwise.
	 */
	public boolean paramIsAnnotated(String paramName) {
		for (Object o : this.md.parameters()) {
			if (o instanceof SingleVariableDeclaration) {
				SingleVariableDeclaration s = (SingleVariableDeclaration) o;
				if (s.getName().getFullyQualifiedName().equals(paramName)) {
					List<IExtendedModifier> ems = s.modifiers();
					
					for (IExtendedModifier m : ems) {
						if (m instanceof Annotation) return true;
					}
					
					return false;
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks if a parameter with name passed as parameter is annotated with a simple annotation
	 * 
	 * @param paramName name of the parameter
	 * @param annotationFullyQualifiedName name of the annotation
	 * @param value value of the annotation
	 * @return true if the parameter is annotated, false otherwise.
	 */
	public boolean paramIsAnnotated(String paramName, String annotationFullyQualifiedName, String value) {
		for (Object o : this.md.parameters()) {
			if (o instanceof SingleVariableDeclaration) {
				SingleVariableDeclaration s = (SingleVariableDeclaration) o;
				if (s.getName().getFullyQualifiedName().equals(paramName)) {
					Annotation a = getAnnotation(s, annotationFullyQualifiedName);
					if (a != null && a.isSingleMemberAnnotation()) {
						Expression exp = ((SingleMemberAnnotation) a).getValue();
						return checkSingleAnnotationValue(exp, value);
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks if a parameter with name passed as parameter is annotated with a complex annotation
	 * 
	 * @param paramName name of the parameter
	 * @param annotationFullyQualifiedName name of the annotation
	 * @param content values of the annotation
	 * @return true if the parameter is annotated, false otherwise.
	 */
	public boolean paramIsAnnotated(String paramName, String annotationFullyQualifiedName, Map<String, Object> content) {
		for (Object o : this.md.parameters()) {
			if (o instanceof SingleVariableDeclaration) {
				SingleVariableDeclaration s = (SingleVariableDeclaration) o;
				if (s.getName().getFullyQualifiedName().equals(paramName)) {
					Annotation a = getAnnotation(s, annotationFullyQualifiedName);			
					return checkAnnotationContent(a, content);					
				}
			}
		}
		return false;
	}
	
	
	/**
	 * Return true if this method throws exception
	 * {@code exceptionFullyQualifiedName}.
	 * 
	 * @param exceptionFullyQualifiedName
	 *            Exception fully qualified name
	 * @return true if this method throws exception
	 *         {@code exceptionFullyQualifiedName}, false otherwise
	 */
	public boolean throwsException(String exceptionFullyQualifiedName) {
		List<Name> exceptionsThrown = this.md.thrownExceptions();

		// Perform some tests to know if we are in good conditions to test
		// exceptions

		if (exceptionsThrown == null || exceptionsThrown.isEmpty()
				|| exceptionFullyQualifiedName == null) {
			return false;
		}

		boolean correct = false;

		for (int inc = 0; inc < exceptionsThrown.size() && !correct; inc++) {
			Name n = exceptionsThrown.get(inc);

			correct = (n.isQualifiedName()) ? n.getFullyQualifiedName().equalsIgnoreCase(
					exceptionFullyQualifiedName) : n.getFullyQualifiedName().equalsIgnoreCase(
					getSimpleName(exceptionFullyQualifiedName));
		}

		return correct;
	}

	/**
	 * Return the exceptions number.
	 */
	public int exceptionsNumber() {
		List<Name> exceptionsThrown = this.md.thrownExceptions();

		if (exceptionsThrown != null) {
			return exceptionsThrown.size();
		}

		return 0;
	}

	/**
	 * Return true if this method contains a body.
	 * 
	 * @return true if this method contains a body, false otherwise
	 */
	public boolean containsBody() {
		return (this.md.getBody() != null) && (this.md.getBody().statements() != null)
				&& (this.md.getBody().statements().size() > 0);
	}

	/**
	 * Return true if this method contains a body equals to {@code expectedBody}.
	 * 
	 * @param expectedBody
	 *            The expected body as string
	 * @return true if this method contains a body equals to
	 *         {@code expectedBody}, false otherwise
	 */
	public boolean containsBody(String expectedBody) {
		// Create an ASTParser from this expected body
		ASTParser generatedContentParser = ASTParser.newParser(AST.JLS3);
		generatedContentParser.setSource(expectedBody.toCharArray());
		generatedContentParser.setKind(ASTParser.K_STATEMENTS);

		// Create a Block object from this ASTParser
		Block expectedBodyAsBlock = (Block) generatedContentParser.createAST(null);

		// Compare current body and expected body
		return this.md.getBody().subtreeMatch(new ASTMatcher(), expectedBodyAsBlock);
	}
}
