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
package org.parallelj.common.jdt.helpers;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.Statement;

public class ASTHelper {

	/**
	 * A static AST Parser
	 */
	private static ASTParser astp = null;

	/**
	 * Initialize this AST parser
	 */
	static {
		astp = ASTParser.newParser(AST.JLS3);
	}

	/**
	 * Return the hashCode of a Java method body.
	 * 
	 * @param methodBody
	 *            The method body as a String
	 * @return the hashCode of a Java method body
	 */
	public static int methodBodyHashCode(String methodBody) {

		// Create an ASTParser from this expected body
		astp.setSource(methodBody.toCharArray());
		astp.setKind(ASTParser.K_STATEMENTS);

		// Create an AST tree from this method body
		Block b = (Block) astp.createAST(null);

		return methodBodyHashCode(b);
	}
	
	/**
	 * Return the hashCode of a Java method body.
	 * 
	 * @param methodBody
	 *            The method body as a Block
	 * @return the hashCode of a Java method body
	 */
	private static int methodBodyHashCode(Block body) {
		StringBuilder builder = new StringBuilder();
		for (Object o : body.statements()) {
			builder.append(((Statement) o).toString());
		}
		return builder.toString().hashCode();
	}

	/**
	 * Return an Annotation instance used on this body declaration
	 */
	public static Annotation getAnnotation(String annotationName, BodyDeclaration bodyDeclaration) {
		List<?> modifiers = bodyDeclaration.modifiers();

		// Test if this MethodDeclaration contains modifiers
		if (modifiers != null) {
			Iterator<?> modifiersIterator = modifiers.iterator();

			while (modifiersIterator.hasNext()) {
				IExtendedModifier modifier = (IExtendedModifier) modifiersIterator.next();

				if (modifier.isAnnotation()) {
					Annotation a = (Annotation) modifier;
					String annotationType = a.getTypeName().toString();

					if (annotationType.endsWith(annotationName)) {
						return a;
					}
				}
			}
		}

		return null;
	}
}
