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

import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getName;
import static org.parallelj.common.jdt.mergers.MergerLogger.getDescription;
import static org.eclipse.jdt.core.dom.TypeDeclaration.BODY_DECLARATIONS_PROPERTY;
import static org.eclipse.jdt.core.dom.TypeDeclaration.SUPER_INTERFACE_TYPES_PROPERTY;
import static org.eclipse.jdt.core.dom.TypeDeclaration.JAVADOC_PROPERTY;
import static org.eclipse.jdt.core.dom.TypeDeclaration.MODIFIERS2_PROPERTY;
import static org.eclipse.jdt.core.dom.TypeDeclaration.SUPERCLASS_TYPE_PROPERTY;

import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.ChildPropertyDescriptor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;

/**
 * Merge two types. A type is composed by a TypeDeclartion, a set of fields,
 * methods and inner types.
 * 
 * @author Architecture And Methodology
 */
class TypeMerger extends EnumMerger {

	/**
	 * TypeMerger constructor
	 * 
	 * @param jcm
	 *            A JavaCodeMerger instance
	 * @param log
	 *            A Merger Logger instance
	 */
	public TypeMerger(JavaCodeMerger jcm, MergerLogger log) {
		super(jcm, log);
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
	protected ChildListPropertyDescriptor getBodyDeclarationsPropertyDescriptor() {
		return BODY_DECLARATIONS_PROPERTY;
	}

	@Override
	protected ChildListPropertyDescriptor getSuperInterfacesPropertyDescriptor() {
		return SUPER_INTERFACE_TYPES_PROPERTY;
	}

	/**
	 * Return super interfaces types defines on {@code atd} Abstract
	 * TypeDeclaration.
	 * 
	 * @param atd
	 *            An Abstract Type Declaration instance
	 * @return A super interfaces types defines on {@code atd} Abstract
	 *         TypeDeclaration.
	 */
	@Override
	protected List<Type> getSuperInterfacesTypes(AbstractTypeDeclaration atd) {
		return ((TypeDeclaration) atd).superInterfaceTypes();
	}

	/**
	 * This method launch merge process on sub fragments.
	 * 
	 * @param existing
	 *            The first code fragment containing sub fragments to merge
	 * @param generated
	 *            The second code fragment containing sub fragments to merge
	 */
	@Override
	public void mergeSubFragments(BodyDeclaration existing, BodyDeclaration generated) {
		AbstractTypeDeclaration existingType = (AbstractTypeDeclaration) existing;
		AbstractTypeDeclaration generatedType = (AbstractTypeDeclaration) generated;

		/*
		 * Merge fields, methods and inner types.
		 */
		this.mergeBodyDeclarationsChildren(existingType, generatedType, FieldDeclaration.class);
		this.mergeBodyDeclarationsChildren(existingType, generatedType, MethodDeclaration.class);
		this.mergeBodyDeclarationsChildren(existingType, generatedType,
				AbstractTypeDeclaration.class);
	}

	/**
	 * Merge type declaration
	 */
	@Override
	protected void mergeDeclaration(AbstractTypeDeclaration existingType,
			AbstractTypeDeclaration generatedType, Set<String> generatedAnnotations) {
		super.mergeDeclaration(existingType, generatedType, generatedAnnotations);

		// Extension is merged
		this.mergeExtension((TypeDeclaration) existingType, (TypeDeclaration) generatedType);
	}

	/**
	 * Merge extension from existingType {@code et} and generatedType {@code gt}
	 * to an AST Rewrite object instance.
	 * 
	 * @param et
	 *            The existing type
	 * @param generatedType
	 *            The generated type
	 */
	protected void mergeExtension(TypeDeclaration et, TypeDeclaration gt) {
		if (et.getSuperclassType() != null && gt.getSuperclassType() != null) {
			this.log.write("\tReplace existing super class (" + getName(et.getSuperclassType())
					+ ") by generated initialization (" + getName(gt.getSuperclassType()) + ") to "
					+ getName(et) + " " + getDescription(et));

			this.astr.replace(et.getSuperclassType(), gt.getSuperclassType(), null);
		} else if (et.getSuperclassType() != null) {
			/* Nothing to do */
		} else if (gt.getSuperclassType() != null) {
			this.log.write("\tAdd super class " + getName(gt.getSuperclassType()) + " to "
					+ getName(et) + " " + getDescription(et));

			this.astr.set(et, SUPERCLASS_TYPE_PROPERTY, gt.getSuperclassType(), null);
		}
	}
}
