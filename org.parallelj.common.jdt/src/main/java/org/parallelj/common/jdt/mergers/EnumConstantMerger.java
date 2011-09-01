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

import static org.eclipse.jdt.core.dom.EnumConstantDeclaration.JAVADOC_PROPERTY;
import static org.eclipse.jdt.core.dom.EnumConstantDeclaration.MODIFIERS2_PROPERTY;
import static org.eclipse.jdt.core.dom.EnumDeclaration.ENUM_CONSTANTS_PROPERTY;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.ChildPropertyDescriptor;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;

/**
 * Merge two EnumConstantDeclaration instance.
 * 
 * @author Atos Worldline
 */
public class EnumConstantMerger extends BodyDeclarationMerger {

	/**
	 * Simple constructor with a logger as single parameter.
	 * 
	 * @param log
	 *            A Merger Logger instance
	 */
	public EnumConstantMerger(MergerLogger log) {
		super(log);
	}

	@Override
	protected ChildListPropertyDescriptor getModifiersPropertyDescriptor() {
		return MODIFIERS2_PROPERTY;
	}

	@Override
	protected ChildPropertyDescriptor getJavadocPropertyDescriptor() {
		return JAVADOC_PROPERTY;
	}

	@Override
	public void insert(ASTNode parent, BodyDeclaration fragmentToInsert) {
		super.insert(parent, fragmentToInsert);

		EnumConstantDeclaration lastConstant = null;
		EnumDeclaration parentType = (EnumDeclaration) parent;

		/*
		 * Read all constant of the type enumeration (used to insert this
		 * constant at the right place)
		 */
		List<EnumConstantDeclaration> existingConstants = parentType.enumConstants();

		// Save the last field if a field already exist
		if (existingConstants.size() > 0) {
			lastConstant = existingConstants.get(existingConstants.size() - 1);
		}

		ListRewrite lw = this.astr.getListRewrite(parentType, ENUM_CONSTANTS_PROPERTY);

		// Insert this constant...
		if (lastConstant == null) {
			/*
			 * ... at the beginning of the enumeration if this is the first
			 * constant
			 */
			lw.insertFirst(fragmentToInsert, null);
		} else {
			// ... after the previous constant if a constant already exist
			lw.insertAfter(fragmentToInsert, lastConstant, null);
		}
	}
}
