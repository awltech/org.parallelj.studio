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
import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getName;
import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getSimpleName;
import static org.parallelj.common.jdt.mergers.MergerLogger.getDescription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.parallelj.common.jdt.helpers.JavaCodeHelper;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.ChildPropertyDescriptor;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;

/**
 * Defines common methods used to perform merge operations (insert, update and
 * delete) on body declaration objects.
 * 
 * @author Architecture And Methodology
 * @author Atos Worldline
 */
abstract class BodyDeclarationMerger implements FragmentMerger {

	protected ASTRewrite astr = null;

	protected MergerLogger log = null;

	/**
	 * Simple constructor with a logger as single parameter.
	 * 
	 * @param log
	 *            A Merger Logger instance
	 */
	public BodyDeclarationMerger(MergerLogger log) {
		this.log = log;
	}

	public void insert(ASTNode parent, BodyDeclaration fragmentToInsert) {
		this.log.write("\tInsert " + getName(fragmentToInsert) + " in " + getName(parent) + " "
				+ getDescription(parent));
	}

	/**
	 * This method merge a first code fragment {@code existing} with a second
	 * one {@code generated}.
	 * 
	 * @param existing
	 *            The first code fragment to merge
	 * @param generated
	 *            The second code fragment to merge
	 */
	public void merge(BodyDeclaration existing, BodyDeclaration generated) {
		this.merge(existing, generated, null);
	}

	/**
	 * This method merge a first code fragment {@code existing} with a second
	 * one {@code generated}.
	 * 
	 * @param existing
	 *            The first code fragment to merge
	 * @param generated
	 *            The second code fragment to merge
	 * @param preDefinedAnnotations
	 *            A set containing special annotations
	 */
	public void merge(BodyDeclaration existing, BodyDeclaration generated,
			Set<String> preDefinedAnnotations) {
		// Modifiers are merged
		this.mergeModifiers(existing, generated, this.astr);

		// Annotations are merged
		this.mergeAnnotations(existing, generated, preDefinedAnnotations, this.astr);
	}

	/**
	 * This method delete the code fragment {@code fragmentToRemove}.
	 * 
	 * @param fragmentToRemove
	 *            The code fragment to remove
	 */
	public void remove(BodyDeclaration fragmentToRemove) {
		this.log.write("\tRemove " + getName(fragmentToRemove) + " "
				+ getDescription(fragmentToRemove));

		/*
		 * Call simply the method remove on astr ASTRewrite instance.
		 */
		this.astr.remove(fragmentToRemove, null);
	}

	/**
	 * This method is used to store an ASTRewrite instance used to perform
	 * previous operations.
	 * 
	 * @param astr
	 *            An ASTRewrit instance used to perform previous operations
	 */
	public void setAstRewrite(ASTRewrite astr) {
		this.astr = astr;
	}

	/**
	 * Return a ChildPropertyDescriptor instance associated with Javadoc
	 * property.
	 * 
	 * @return a ChildPropertyDescriptor instance associated with Javadoc
	 *         property
	 */
	protected abstract ChildPropertyDescriptor getJavadocPropertyDescriptor();

	/**
	 * Merge modifiers from existingFragment and generatedFragment to an AST
	 * Rewrite object instance.
	 * 
	 * @param existingFragment
	 *            The existing fragment
	 * @param generatedFragment
	 *            The generated fragment
	 * @param astr
	 *            The ASTRewrite object instance containing the merge result
	 */
	@SuppressWarnings("unchecked")
	protected void mergeModifiers(BodyDeclaration existingFragment,
			BodyDeclaration generatedFragment, ASTRewrite astr) {
		/*
		 * For each modifier of the existing type, check if this modifier is a
		 * modelizable modifier. If true, remove this one (replaced by generated
		 * modifier during the next step). If false, nothing to do, let the
		 * modifier in place.
		 */
		List<IExtendedModifier> existingFragmentModifiers = existingFragment.modifiers();

		for (IExtendedModifier currentModifier : existingFragmentModifiers) {
			if (this.isAModelizableModifier(existingFragment, currentModifier)) {
				this.log.write("\tRemove modifier " + currentModifier.toString() + " from "
						+ getName(existingFragment) + " " + getDescription(existingFragment));

				this.astr.remove((ASTNode) currentModifier, null);
			} else {
				if (currentModifier.isModifier()) {
					this.log.write("\tLeave the existing modifier " + currentModifier.toString()
							+ " on " + getName(existingFragment) + " "
							+ getDescription(existingFragment));
				}
			}
		}

		/*
		 * Get a ListRewrite object used to modify modifiers in the existing
		 * code.
		 */
		ListRewrite lw = astr.getListRewrite(existingFragment, this
				.getModifiersPropertyDescriptor());

		/*
		 * For each modifier of the generated type, insert it into the merge
		 * result.
		 */
		List<IExtendedModifier> generatedFragmentModifiers = generatedFragment.modifiers();

		for (IExtendedModifier currentModifier : generatedFragmentModifiers) {
			if (this.isAModelizableModifier(existingFragment, currentModifier)) {
				this.log.write("\tAdd modifier " + currentModifier.toString() + " on "
						+ getName(existingFragment) + " " + getDescription(existingFragment));

				lw.insertLast((ASTNode) currentModifier, null);
			} else {
				if (currentModifier.isModifier()) {
					this.log.write("\tModifier " + currentModifier.toString()
							+ " has not been added on " + getName(existingFragment) + " "
							+ getDescription(existingFragment));
				}
			}
		}
	}

	/**
	 * Merge annotations from existingFragment and generatedFragment to an AST
	 * Rewrite object instance.
	 * 
	 * @param existingFragment
	 *            The existing fragment
	 * @param generatedFragment
	 *            The generated fragment
	 * @param astr
	 *            The ASTRewrite object instance containing the merge result
	 */
	@SuppressWarnings("unchecked")
	protected void mergeAnnotations(BodyDeclaration existingFragment,
			BodyDeclaration generatedFragment, Set<String> preDefinedAnnotations, ASTRewrite astr) {

		List<String> annotationsNamesInExistingFragment = new ArrayList<String>(1);
		List<String> annotationsNamesInGeneratedFragment = new ArrayList<String>(1);
		List<IExtendedModifier> existingModifiers = existingFragment.modifiers();
		List<IExtendedModifier> generatedModifiers = generatedFragment.modifiers();

		Map<String, Annotation> generatedAnnotationMap = new HashMap<String, Annotation>(3);

		ListRewrite lw = astr.getListRewrite(existingFragment, this
				.getModifiersPropertyDescriptor());

		/*
		 * Parse all annotations used on the existing fragment and save theirs
		 * names in a list
		 */
		for (IExtendedModifier iem : existingModifiers) {
			if (iem.isAnnotation()) {
				annotationsNamesInExistingFragment.add(getSimpleName(((Annotation) iem)
						.getTypeName()));
			}
		}

		/*
		 * Parse all annotations used on the generated fragment and - Save
		 * theirs names in a list - Save theirs annotations instances in a map
		 * using annotations names as key
		 */
		for (IExtendedModifier iem : generatedModifiers) {
			if (iem.isAnnotation()) {
				String annotationName = getSimpleName(((Annotation) iem).getTypeName());
				annotationsNamesInGeneratedFragment.add(annotationName);
				generatedAnnotationMap.put(annotationName, ((Annotation) iem));
			}
		}

		// For each annotation used on the generated fragment...
		for (IExtendedModifier iem : generatedModifiers) {
			if (iem.isAnnotation()) {
				if (!annotationsNamesInExistingFragment.contains(getSimpleName(((Annotation) iem)
						.getTypeName()))) {
					/*
					 * This annotation is present in the generated code AND This
					 * annotation is not present in the existing code => Add
					 * this annotation in the merge result
					 */

					this.log.write("\tAdd annotation " + ((Annotation) iem).toString() + " to "
							+ getName(existingFragment) + " " + getDescription(existingFragment));

					lw.insertFirst(((Annotation) iem), null);
				} else {
					
				}
			}
		}

		// For each annotation used on the existing fragment...
		for (IExtendedModifier iem : existingModifiers) {
			if (iem.isAnnotation()) {
				String annotationName = ((Annotation) iem).getTypeName().getFullyQualifiedName();

				// Perform the merge only if its not the Generated annotation
				if (!annotationName.equals("Generated")
						&& !annotationName.equals("javax.annotation.Generated")) {
					Annotation annotationInTheExistingFragment = (Annotation) iem;
					String annotationNameInTheExistingFragment = getSimpleName(annotationInTheExistingFragment
							.getTypeName());

					if (!annotationsNamesInGeneratedFragment
							.contains(annotationNameInTheExistingFragment)
							&& (preDefinedAnnotations.contains(annotationNameInTheExistingFragment))) {
						/*
						 * This annotation is present into the existing fragment
						 * AND This annotation is not present into the generated
						 * fragment AND This annotation is a predefined one =>
						 * This annotation must be removed - Use cases : Change
						 * association multiplicity (from OneToOne to
						 * OneToMany),...
						 */

						this.log.write("\tRemove annotation "
								+ annotationInTheExistingFragment.toString() + " from "
								+ getName(existingFragment) + " "
								+ getDescription(existingFragment));

						lw.remove(annotationInTheExistingFragment, null);
					} else {
						if (annotationsNamesInGeneratedFragment
								.contains(annotationNameInTheExistingFragment)) {
							/*
							 * This annotation is present into the existing
							 * fragment AND This annotation is present into the
							 * generated fragment => Annotation attributes must
							 * be merged
							 */
							Annotation annotationInTheGeneratedFragment = generatedAnnotationMap
									.get(annotationNameInTheExistingFragment);
							this.mergeAnnotationAttributes(annotationInTheExistingFragment,
									annotationInTheGeneratedFragment, lw);
						}
					}
				}
			}
		}
	}

	/**
	 * Annotation attributes handlings for annotation's type located in the
	 * generated annotation property file.
	 * 
	 * @param annotationInTheExistingFragment
	 * @param existingAnnotation
	 * @param lw
	 * @param generatedAnnotations
	 * @param annoName
	 */
	private void mergeAnnotationAttributes(Annotation annotationInTheExistingFragment,
			Annotation annotationInTheGeneratedFragment, ListRewrite lw) {

		if (annotationInTheExistingFragment.isMarkerAnnotation()) {
			/*
			 * The annotation used inside the existing fragment is a marker
			 * annotation (annotation without attribute)
			 */
			if (!annotationInTheGeneratedFragment.isMarkerAnnotation()) {
				/*
				 * The annotation used inside the generated fragment contains
				 * one or many attributes. The annotation used inside the
				 * generated fragment must be used in the result
				 */
				lw.replace(annotationInTheExistingFragment, annotationInTheGeneratedFragment, null);
			}
		} else {
			if (annotationInTheExistingFragment.isSingleMemberAnnotation()) {
				/*
				 * The annotation used inside the existing fragment is a single
				 * member annotation (annotation without only one attribute)
				 */
				if (annotationInTheGeneratedFragment.isSingleMemberAnnotation()) {
					/*
					 * The annotation used inside the generated fragment
					 * contains one value. Existing value must be replaced by
					 * generated value.
					 */

					astr.replace(annotationInTheExistingFragment, annotationInTheGeneratedFragment,
							null);
				} else {
					if (annotationInTheGeneratedFragment.isNormalAnnotation()) {
						/*
						 * The annotation used inside the generated fragment
						 * contains several attributes. Existing value must be
						 * replaced by generated value.
						 */
						lw.replace(annotationInTheExistingFragment,
								annotationInTheGeneratedFragment, null);
					}
				}
			} else {
				/*
				 * The annotation used inside the existing fragment is a normal
				 * annotation (annotation with several attributes)
				 */
				if (annotationInTheGeneratedFragment.isSingleMemberAnnotation()) {
					/*
					 * What should be done in that case ? The existing
					 * annotation has been probably enhance => Let the existing
					 * content as before.
					 */
				} else {
					if (annotationInTheGeneratedFragment.isNormalAnnotation()) {
						/*
						 * The annotation used inside the generated fragment
						 * contains several attributes. Existing and generated
						 * annotations must be merged.
						 */
						this.mergeAnnotationAttribute(
								(NormalAnnotation) annotationInTheExistingFragment,
								(NormalAnnotation) annotationInTheGeneratedFragment);
					}
				}
			}
		}
	}

	/**
	 * Merge two annotations attribute
	 */
	@SuppressWarnings("unchecked")
	private void mergeAnnotationAttribute(NormalAnnotation annotationInTheExistingFragment,
			NormalAnnotation annotationInTheGeneratedFragment) {

		List<String> existingAnnotationAttributesToRemoved = new ArrayList<String>(1);
		List<MemberValuePair> existingAnnotationAttributes = annotationInTheExistingFragment
				.values();
		List<MemberValuePair> generatedAnnotationAttributes = annotationInTheGeneratedFragment
				.values();

		List<String> existingAnnotationAttributesNames = new ArrayList<String>(1);
		Map<String, MemberValuePair> generatedAnnotationAttributesMap = new HashMap<String, MemberValuePair>(
				1);

		for (MemberValuePair mvp : existingAnnotationAttributes) {
			existingAnnotationAttributesNames.add(mvp.getName().getFullyQualifiedName());
		}

		for (MemberValuePair mvp : generatedAnnotationAttributes) {
			generatedAnnotationAttributesMap.put(mvp.getName().getFullyQualifiedName(), mvp);
		}

		for (MemberValuePair mvp : existingAnnotationAttributes) {
			if (generatedAnnotationAttributesMap.containsKey(mvp.getName().getFullyQualifiedName())) {

				/*
				 * The attribute is present in the existing fragment AND The
				 * attribute is present in the generated fragment => The
				 * attribute present in the existing fragment must be removed
				 * and the attribute present in the generated fragment must be
				 * copied in the result
				 */
				ListRewrite lw = astr.getListRewrite(annotationInTheExistingFragment,
						NormalAnnotation.VALUES_PROPERTY);

				lw.replace(mvp, generatedAnnotationAttributesMap.get(mvp.getName()
						.getFullyQualifiedName()), null);

				existingAnnotationAttributesToRemoved.add(mvp.getName().getFullyQualifiedName());
			}
		}

		for (MemberValuePair mvp : generatedAnnotationAttributes) {
			if (!existingAnnotationAttributes.contains(mvp.getName().getFullyQualifiedName())
					&& !existingAnnotationAttributesToRemoved.contains(mvp.getName()
							.getFullyQualifiedName())) {
				/*
				 * The attribute is not present in the existing fragment AND The
				 * attribute is present in the generated fragment => The
				 * attribute present in the generated fragment must be copied in
				 * the result
				 */
				ListRewrite lw = astr.getListRewrite(annotationInTheExistingFragment,
						NormalAnnotation.VALUES_PROPERTY);

				lw.insertFirst(mvp, null);

			} else {
				// Nothing to do
			}
		}
	}

	/**
	 * Return a ChildListPropertyDescriptor instance associated with modifiers
	 * property.
	 * 
	 * @return a ChildListPropertyDescriptor instance associated with modifiers
	 *         property
	 */
	protected abstract ChildListPropertyDescriptor getModifiersPropertyDescriptor();

	/**
	 * Return true if {@code modifier} is a modelizable modifier. A modelizable
	 * modifier is one of abstract, static, private, protected, public, final or
	 * transient. Other modifiers are not modelizable (it's technical modifiers
	 * like synchronized,...).
	 * 
	 * @param modifier
	 *            The modifier to test
	 * @return true if {@code modifier} is a modelizable modifier, false
	 *         otherwise.
	 */
	protected boolean isAModelizableModifier(BodyDeclaration existingFragment,
			IExtendedModifier modifier) {
		/* An annotation modifier is not processed by this method. */
		if (modifier.isAnnotation()) {
			return false;
		}

		Modifier m = (Modifier) modifier;
		return (m.isStatic() || m.isAbstract() || m.isPrivate() || m.isProtected() || m.isPublic()
				|| m.isTransient() || m.isFinal());
	}

	public void mergeSubFragments(BodyDeclaration existing, BodyDeclaration generated) {
		// Nothing to do by default
	}

	/**
	 * Merge types
	 */
	protected void mergeTypes(Type existingType, Type generatedType) {
		boolean typeMustBeReplaced = true;

		if (existingType.isParameterizedType() && !generatedType.isParameterizedType()) {
			typeMustBeReplaced = (!JavaCodeHelper.areTypesEquals(((ParameterizedType) existingType)
					.getType(), generatedType));
		}

		if (typeMustBeReplaced) {
			this.astr.replace(existingType, generatedType, null);
		}
	}
}
