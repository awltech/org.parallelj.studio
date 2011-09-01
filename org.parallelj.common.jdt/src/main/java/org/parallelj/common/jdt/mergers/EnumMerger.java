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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.ChildPropertyDescriptor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;

import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getBodyDeclaration;
import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getName;
import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getSimpleName;
import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getTypedChildren;
import static org.parallelj.common.jdt.mergers.MergerLogger.getDescription;
import static org.eclipse.jdt.core.dom.CompilationUnit.TYPES_PROPERTY;
import static org.eclipse.jdt.core.dom.EnumDeclaration.JAVADOC_PROPERTY;
import static org.eclipse.jdt.core.dom.EnumDeclaration.MODIFIERS2_PROPERTY;
import static org.eclipse.jdt.core.dom.EnumDeclaration.BODY_DECLARATIONS_PROPERTY;
import static org.eclipse.jdt.core.dom.EnumDeclaration.SUPER_INTERFACE_TYPES_PROPERTY;

public class EnumMerger extends BodyDeclarationMerger {

	protected JavaCodeMerger jcm = null;

	/**
	 * EnumMerger constructor
	 * 
	 * @param jcm
	 *            A JavaCodeMerger instance
	 * @param log
	 *            A Merger Logger instance
	 */
	public EnumMerger(JavaCodeMerger jcm, MergerLogger log) {
		super(log);
		this.jcm = jcm;
	}

	@Override
	protected ChildPropertyDescriptor getJavadocPropertyDescriptor() {
		return JAVADOC_PROPERTY;
	}

	@Override
	protected ChildListPropertyDescriptor getModifiersPropertyDescriptor() {
		return MODIFIERS2_PROPERTY;
	}

	protected ChildListPropertyDescriptor getBodyDeclarationsPropertyDescriptor() {
		return BODY_DECLARATIONS_PROPERTY;
	}

	protected ChildListPropertyDescriptor getSuperInterfacesPropertyDescriptor() {
		return SUPER_INTERFACE_TYPES_PROPERTY;
	}

	protected List<Type> getSuperInterfacesTypes(AbstractTypeDeclaration atd) {
		return ((EnumDeclaration) atd).superInterfaceTypes();
	}

	@Override
	public void insert(ASTNode parent, BodyDeclaration fragmentToInsert) {
		super.insert(parent, fragmentToInsert);

		if (parent instanceof CompilationUnit) {
			ListRewrite listRewrite = this.astr.getListRewrite((CompilationUnit) parent,
					TYPES_PROPERTY);

			if (listRewrite != null) {
				listRewrite.insertLast(fragmentToInsert, null);
			}
		} else {
			AbstractTypeDeclaration lastType = null;

			/*
			 * Read all types of the parentType type declaration (used to insert
			 * this type at the right place)
			 */
			List<AbstractTypeDeclaration> existingTypes = getTypedChildren(
					(AbstractTypeDeclaration) parent, AbstractTypeDeclaration.class);

			// Save the last type if a type already exist
			if (existingTypes.size() > 0) {
				lastType = existingTypes.get(existingTypes.size() - 1);
			}

			ListRewrite lw = this.astr.getListRewrite(parent, this
					.getBodyDeclarationsPropertyDescriptor());

			if (lastType == null) {
				// ... at the end of the type if this is the first method
				lw.insertLast(fragmentToInsert, null);
			} else {
				// ... after the previous type if a type already exist
				lw.insertAfter(fragmentToInsert, lastType, null);
			}
		}
	}

	@Override
	public void merge(BodyDeclaration existing, BodyDeclaration generated,
			Set<String> preDefinedAnnotations) {

		AbstractTypeDeclaration existingType = (AbstractTypeDeclaration) existing;
		AbstractTypeDeclaration generatedType = (AbstractTypeDeclaration) generated;

		this.mergeDeclaration(existingType, generatedType, preDefinedAnnotations);
	}

	@Override
	public void mergeSubFragments(BodyDeclaration existing, BodyDeclaration generated) {
		EnumDeclaration existingType = (EnumDeclaration) existing;
		EnumDeclaration generatedType = (EnumDeclaration) generated;

		this.mergeBodyDeclarationsChildren(existingType, generatedType, FieldDeclaration.class);
		this.mergeBodyDeclarationsChildren(existingType, generatedType, MethodDeclaration.class);
		this.mergeBodyDeclarationsChildren(existingType, generatedType,
				AbstractTypeDeclaration.class);
		this.mergeBodyDeclarationsChildren(existingType, generatedType,
				EnumConstantDeclaration.class);
	}

	/**
	 * Merge type declaration
	 */
	protected void mergeDeclaration(AbstractTypeDeclaration existing,
			AbstractTypeDeclaration generated, Set<String> preDefinedAnnotations) {
		super.merge(existing, generated, preDefinedAnnotations);

		// Interfaces are merged
		this.mergeInterfaces(existing, generated);
	}

	/**
	 * Merge interfaces from existingType and generatedType to an AST Rewrite
	 * object instance.
	 * 
	 * @param et
	 *            The existing type
	 * @param generatedTYpe
	 *            The generated type
	 * @param astr
	 *            The ASTRewrite object instance containing the merge result
	 */
	protected void mergeInterfaces(AbstractTypeDeclaration et, AbstractTypeDeclaration gt) {

		List<String> existingInterfaces = new ArrayList<String>(3);

		// Create an interfaces list in String format.
		for (Type t : (List<Type>) this.getSuperInterfacesTypes(et)) {
			existingInterfaces.add(getSimpleName(getName(t)));
		}

		/*
		 * Get a ListRewrite object used to modify interfaces in the existing
		 * code.
		 */
		ListRewrite lw = this.astr.getListRewrite(et, this.getSuperInterfacesPropertyDescriptor());

		// For each interfaces of the generated source
		for (Type t : (List<Type>) this.getSuperInterfacesTypes(gt)) {

			if (!existingInterfaces.contains(getSimpleName(getName(t)))) {
				/*
				 * This interface is containing in the generated code but not in
				 * the existing code => Add this interface in the merge result.
				 */

				this.log.write("\tAdd interface " + getName(t) + " to " + getName(et) + " "
						+ getDescription(et));

				lw.insertLast(t, null);
			}
		}
	}

	/**
	 * Merge all body declaration of a Java source typed with
	 * {@code bodyDeclarationType} class.
	 */
	protected void mergeBodyDeclarationsChildren(AbstractTypeDeclaration existingType,
			AbstractTypeDeclaration generatedType, Class bodyDeclarationType) {

		if (generatedType != null) {
			// For each typed body declaration of the generated type...
			List<BodyDeclaration> generatedBDs = getTypedChildren(generatedType,
					bodyDeclarationType);

			for (BodyDeclaration bd : generatedBDs) {
				// ... call the merge process
				this.jcm.mergeTwoFragments(existingType, getBodyDeclaration(existingType, bd), bd,
						this.astr);
			}
		}

		if (existingType != null) {
			// For each typed body declaration of the existing type...
			List<BodyDeclaration> existingBDs = getTypedChildren(existingType, bodyDeclarationType);

			for (BodyDeclaration bd : existingBDs) {
				/*
				 * ... call the merge process if the body declaration in the
				 * generated type doesn't exist
				 */
				if (getBodyDeclaration(generatedType, bd) == null) {
					this.jcm.mergeTwoFragments(existingType, bd, null, this.astr);
				}
			}
		}
	}
}
