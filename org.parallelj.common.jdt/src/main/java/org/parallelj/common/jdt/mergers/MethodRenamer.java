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
package org.parallelj.common.jdt.mergers;

import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getAnnotation;
import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getMethod;
import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;

class MethodRenamer {

	public static final String RENAMING_ANNOTATION_NAME = "org.parallelj.common.jdt.mergers.ToBeRenamed";

	public static final String RENAMING_ANNOTATION_SIMPLE_NAME = "ToBeRenamed";

	private Map<MethodDeclaration, MethodDeclaration> methods = new HashMap<MethodDeclaration, MethodDeclaration>(
			2);

	private Map<MethodDeclaration, Block> methodsToBeRenamed = new HashMap<MethodDeclaration, Block>(
			2);

	/**
	 * This constructor take an existing Compilation Unit instance and a
	 * generated Compilation Unit instance, and save informations about methods
	 * to be renamed.
	 * 
	 * @param existingCU
	 *            The existing Copilation Unit instance
	 * @param generatedCU
	 *            The generated Copilation Unit instance
	 */
	MethodRenamer(CompilationUnit existingCU, CompilationUnit generatedCU) {
		/* For each type of the existing compilation unit... */
		for (AbstractTypeDeclaration existingType : (List<AbstractTypeDeclaration>) existingCU
				.types()) {

			String existingTypeName = existingType.getName().getFullyQualifiedName();

			/* For each body declaration of this type... */
			for (BodyDeclaration bd : (List<BodyDeclaration>) existingType.bodyDeclarations()) {
				/* Test if the body declaration is a method declaration */
				if (bd instanceof MethodDeclaration) {
					MethodDeclaration existingMD = (MethodDeclaration) bd;
					Annotation a = getAnnotation(bd, RENAMING_ANNOTATION_NAME);

					/*
					 * If this body declaration is annotated with
					 * org.parallelj.common.jdt.mergers.ToBeRenamed
					 */
					if (a != null && a.isNormalAnnotation()) {
						String methodName = null;
						String[] methodParams = null;
						NormalAnnotation na = (NormalAnnotation) a;

						/* Scan annotation members to extract informations */
						for (MemberValuePair mvp : (List<MemberValuePair>) na.values()) {
							String mvpName = mvp.getName().getFullyQualifiedName();

							if (mvpName.equalsIgnoreCase("methodName")) {
								/* Save new method name in methodName variable */
								methodName = mvp.getValue().toString();
							} else if (mvpName.equalsIgnoreCase("params")) {
								/*
								 * Save new method parameters list in
								 * methodParam variable
								 */
								ArrayInitializer ai = (ArrayInitializer) mvp.getValue();

								if (ai != null) {
									methodParams = new String[ai.expressions().size()];
									List<Expression> types = (List<Expression>) ai.expressions();

									for (int inc = 0; inc < types.size(); inc++) {
										methodParams[inc] = types.toString();
									}
								}
							}
						}

						/*
						 * If method name is null or empty, stop this method
						 * process
						 */
						if (methodName == null || methodName.length() == 0) {
							// TODO Add log here
						} else {
							AbstractTypeDeclaration generatedType = getType(generatedCU,
									existingTypeName);

							/*
							 * Test if generated type is not null before
							 * processing
							 */
							if (generatedType != null) {
								/*
								 * The annotated body declaration is a method
								 * declaration
								 */
								methodName = methodName.substring(1, methodName.length() - 1);
								MethodDeclaration generatedMD = null;

								if (methodParams != null) {
									generatedMD = getMethod(generatedType, methodName, methodParams);
								} else {
									generatedMD = getMethod(generatedType, methodName);
								}

								if (generatedMD != null) {
									this.methodsToBeRenamed.put(generatedMD, existingMD.getBody());
									this.methods.put(generatedMD, existingMD);
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Return the methods number to be renamed.
	 * 
	 * @return The methods number to be renamed.
	 */
	public int methodsNumberToBeRenamed() {
		return this.methodsToBeRenamed.size();
	}

	/**
	 * Execute renaming process.
	 * 
	 * @param existingAstr
	 *            An ASTRewrite instance for existing content
	 * @param generatedAstr
	 *            An ASTRewrite instance for generated content
	 */
	public void proceed(ASTRewrite existingAstr, ASTRewrite generatedAstr) {
		for (MethodDeclaration md : this.methodsToBeRenamed.keySet()) {
			/* Change method name */
			MethodDeclaration existingMethod = this.methods.get(md);
			existingAstr.set(existingMethod, MethodDeclaration.NAME_PROPERTY, md.getName(), null);

			/* Remove @ToBeRenamed annotation */
			existingAstr.remove(getAnnotation(existingMethod, RENAMING_ANNOTATION_NAME), null);

			/* Remove generated method */
			generatedAstr.remove(md, null);
		}
	}
}
