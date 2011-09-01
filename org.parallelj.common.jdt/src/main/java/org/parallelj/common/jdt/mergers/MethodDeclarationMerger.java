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

import static org.eclipse.jdt.core.dom.MethodDeclaration.BODY_PROPERTY;
import static org.eclipse.jdt.core.dom.MethodDeclaration.JAVADOC_PROPERTY;
import static org.eclipse.jdt.core.dom.MethodDeclaration.MODIFIERS2_PROPERTY;
import static org.eclipse.jdt.core.dom.MethodDeclaration.PARAMETERS_PROPERTY;
import static org.eclipse.jdt.core.dom.MethodDeclaration.THROWN_EXCEPTIONS_PROPERTY;
import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getBodyDeclarationProperty;
import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getName;
import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getSimpleName;
import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getTypedChildren;
import static org.parallelj.common.jdt.mergers.MergerLogger.getDescription;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.ChildPropertyDescriptor;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.parallelj.common.jdt.helpers.ASTHelper;

/**
 * Merge two MethodDeclaration instance. A MethodDeclaration is composed by a
 * javadoc, modifiers, parameters list and exceptions.
 * 
 * @author Atos Worldline
 */
class MethodDeclarationMerger extends BodyDeclarationMerger {

	/**
	 * Simple constructor with a logger as single parameter.
	 * 
	 * @param log
	 *            A Merger Logger instance
	 */
	public MethodDeclarationMerger(MergerLogger log) {
		super(log);
	}

	@Override
	protected ChildPropertyDescriptor getJavadocPropertyDescriptor() {
		return JAVADOC_PROPERTY;
	}

	@Override
	protected ChildListPropertyDescriptor getModifiersPropertyDescriptor() {
		return MODIFIERS2_PROPERTY;
	}

	@Override
	public void insert(ASTNode parent, BodyDeclaration fragmentToInsert) {
		super.insert(parent, fragmentToInsert);

		MethodDeclaration lastMethod = null;
		AbstractTypeDeclaration parentType = (AbstractTypeDeclaration) parent;

		/*
		 * Read all methods of the type declaration (used to insert this method
		 * at the right place)
		 */
		List<MethodDeclaration> existingMethods = getTypedChildren(parentType,
				MethodDeclaration.class);

		// Save the last method if a method already exist
		if (existingMethods.size() > 0) {
			lastMethod = existingMethods.get(existingMethods.size() - 1);
		}

		ListRewrite lw = this.astr.getListRewrite(parentType, getBodyDeclarationProperty(parentType
				.getClass()));

		if (lastMethod == null) {
			// ... at the end of the type if this is the first method
			lw.insertLast(fragmentToInsert, null);
		} else {
			// ... after the previous method if a method already exist
			lw.insertAfter(fragmentToInsert, lastMethod, null);
		}
	}

	@Override
	public void merge(BodyDeclaration existing, BodyDeclaration generated,
			Set<String> generatedAnnotations) {
		super.merge(existing, generated, generatedAnnotations);

		MethodDeclaration existingMethod = (MethodDeclaration) existing;
		MethodDeclaration generatedMethod = (MethodDeclaration) generated;

		int existingMethodBodyAnnotationHashCode = 0;
		int generatedMethodBodyAnnotationHashCode = 0;
		int existingMethodBodyCalculatedHashCode = 0;

		try {
			/*
			 * Try to read the hashcode of the existing method in the associated
			 * 
			 * @Generated annotation
			 */
			existingMethodBodyAnnotationHashCode = getMethodBodyHashCodeFromAnnotation(existingMethod);

			/*
			 * Test if the method body is not null before doing the hash code
			 * processing
			 */
			if (existingMethod.getBody() != null) {
				existingMethodBodyCalculatedHashCode = ASTHelper.methodBodyHashCode(existingMethod
						.getBody().toString());
			}

			if (existingMethodBodyAnnotationHashCode == existingMethodBodyCalculatedHashCode) {
				/*
				 * The calculated hashCode body of the existing method is equal
				 * to the hashCode read from @Generated of the method. The body
				 * has not been customized by the user
				 */

				/*
				 * Try to read the hashcode of the generated method in the
				 * associated @Generated annotation
				 */
				generatedMethodBodyAnnotationHashCode = getMethodBodyHashCodeFromAnnotation(generatedMethod);

				if (existingMethodBodyAnnotationHashCode != generatedMethodBodyAnnotationHashCode) {
					/*
					 * The generated method body and the existing method body
					 * are not the same. The existing method body must (and can)
					 * be updated
					 */
					this.replaceMethodBody(existingMethod, generatedMethod);
					this.replaceGeneratedAnnotation(existingMethod, generatedMethod);
				}
			}
		} catch (IllegalArgumentException iae) {
			/*
			 * If this excpetion is thrown, the associated annotation doesn't
			 * contain this information. In this case, copy the new method body
			 */
			this.replaceMethodBody(existingMethod, generatedMethod);
			this.replaceGeneratedAnnotation(existingMethod, generatedMethod);
		}

		// Return type is replaced (test before if return types are not null)
		if (existingMethod.getReturnType2() != null && generatedMethod.getReturnType2() != null) {
			this.mergeTypes(existingMethod.getReturnType2(), generatedMethod.getReturnType2());
		}

		// Exceptions are merged
		this.mergeExceptions(existingMethod, generatedMethod);

		// Parameters from generated methods should become parameters for
		// existing method.
		if (existingMethod.parameters() != null && generatedMethod.parameters() != null) {
			this.log.write("\tReplace existing parameters by generated parameters into "
					+ getName(existing) + " " + getDescription(existing));

			ListRewrite lw = this.astr.getListRewrite(existingMethod, PARAMETERS_PROPERTY);
			for (SingleVariableDeclaration svd : (List<SingleVariableDeclaration>) existingMethod
					.parameters())
				lw.remove(svd, null);
			for (SingleVariableDeclaration svd : (List<SingleVariableDeclaration>) generatedMethod
					.parameters())
				lw.insertLast(svd, null);
		}
	}

	/**
	 * Replace method body
	 */
	private void replaceMethodBody(MethodDeclaration existingMethod,
			MethodDeclaration generatedMethod) {
		if (existingMethod.getBody() != null && generatedMethod.getBody() != null) {
			this.log.write("\tReplace existing body by generated body to "
					+ getName(existingMethod) + " " + getDescription(existingMethod));

			this.astr.replace(existingMethod.getBody(), generatedMethod.getBody(), null);
		} else if (existingMethod.getBody() != null) {
			/* Nothing to do */
		} else if (generatedMethod.getBody() != null) {
			this.log.write("\tAdd body to " + getName(existingMethod) + " "
					+ getDescription(existingMethod));

			this.astr.set(existingMethod, BODY_PROPERTY, generatedMethod.getBody(), null);
		}
	}

	/**
	 * Replace
	 * 
	 * @Generated annotation
	 */
	private void replaceGeneratedAnnotation(MethodDeclaration existingMethod,
			MethodDeclaration generatedMethod) {
		Annotation existingAnnotation = ASTHelper.getAnnotation("Generated", existingMethod);
		Annotation generatedAnnotation = ASTHelper.getAnnotation("Generated", generatedMethod);

		if (existingAnnotation != null && generatedAnnotation != null
				&& generatedAnnotation.isNormalAnnotation()) {
			this.astr.replace(existingAnnotation, generatedAnnotation, null);
		}
	}

	/**
	 * Merge exceptions from existingMethod and generatedMethod to an AST
	 * Rewrite object instance.
	 * 
	 * @param astr
	 *            The ASTRewrite object instance containing the merge result
	 */
	protected void mergeExceptions(MethodDeclaration existingMethod,
			MethodDeclaration generatedMethod) {

		List<String> existingExceptions = new ArrayList<String>(3);

		// Create an annotations list in String format.
		for (Name n : (List<Name>) existingMethod.thrownExceptions()) {
			existingExceptions.add(getSimpleName(n));
		}

		/*
		 * Get a ListRewrite object used to modify annotations in the existing
		 * code.
		 */
		ListRewrite lw = this.astr.getListRewrite(existingMethod, THROWN_EXCEPTIONS_PROPERTY);

		// For each annotation of the generated source
		for (Name n : (List<Name>) generatedMethod.thrownExceptions()) {
			if (!existingExceptions.contains(getSimpleName(n))) {
				/*
				 * This exception is containing in the generated code but not in
				 * the existing code => Add this exception in the merge result.
				 */
				this.log.write("\tAdd exception " + n.getFullyQualifiedName() + " to "
						+ getName(existingMethod) + " " + getDescription(existingMethod));

				lw.insertLast(n, null);
			}
		}
	}

	/**
	 * Return the hashCode of a method body
	 */
	private static int getMethodBodyHashCodeFromAnnotation(MethodDeclaration md) {
		boolean found = false;
		int methodBodyHashCode = 0;

		Annotation a = ASTHelper.getAnnotation("Generated", md);

		if (a != null && a.isNormalAnnotation()) {
			NormalAnnotation na = (NormalAnnotation) a;

			List<MemberValuePair> values = na.values();

			for (int inc = 0; inc < values.size() && !found; inc++) {
				MemberValuePair mvp = values.get(inc);

				if (mvp != null && mvp.getName().toString().equals("comments")) {
					found = true;
					methodBodyHashCode = Integer.parseInt(mvp.getValue().toString().substring(1,
							mvp.getValue().toString().length() - 1));
				}
			}
		}

		// If the hashCode cannot be found, throw an IllegalArgumentException
		if (!found) {
			throw new IllegalArgumentException();
		}

		return methodBodyHashCode;
	}
}
