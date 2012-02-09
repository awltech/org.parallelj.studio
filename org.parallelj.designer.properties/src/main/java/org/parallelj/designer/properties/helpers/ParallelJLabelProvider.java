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
package org.parallelj.designer.properties.helpers;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.swt.graphics.Image;
import org.parallelj.designer.providers.ParallelJElementTypes;
import org.parallelj.ixea.tools.LiteLabelProvider;
import org.parallelj.model.Condition;
import org.parallelj.model.Data;
import org.parallelj.model.ForEachLoop;
import org.parallelj.model.Handler;
import org.parallelj.model.InputCondition;
import org.parallelj.model.Link;
import org.parallelj.model.OutputCondition;
import org.parallelj.model.ParallelJPackage;
import org.parallelj.model.Block;
import org.parallelj.model.Predicate;
import org.parallelj.model.Procedure;
import org.parallelj.model.Program;
import org.parallelj.model.WhileLoop;

public class ParallelJLabelProvider extends LiteLabelProvider {

	@Override
	public Image getImageFromEClass(EClass eClass) {
		return ParallelJElementTypes.getImage(eClass);
	}

	@Override
	public void bindObjectsAndLabels() {
		ParallelJPackage ePackage = ParallelJPackage.eINSTANCE;
		this.bind(Program.class, ePackage.getNamedElement_Name());
		this.bind(Condition.class, ePackage.getNamedElement_Name());
		this.bind(Data.class, ePackage.getNamedElement_Name());
		this.bind(ForEachLoop.class, ePackage.getNamedElement_Name());
		this.bind(Handler.class, ePackage.getNamedElement_Name());
		this.bind(InputCondition.class, ePackage.getNamedElement_Name());
		this.bind(Link.class, ePackage.getNamedElement_Name());
		this.bind(OutputCondition.class, ePackage.getNamedElement_Name());
		this.bind(Block.class, ePackage.getNamedElement_Name());
		this.bind(Predicate.class, ePackage.getNamedElement_Name());
		this.bind(Procedure.class, ePackage.getNamedElement_Name());
		this.bind(WhileLoop.class, ePackage.getNamedElement_Name());
	}

	@Override
	public EPackage getEPackage() {
		return ParallelJPackage.eINSTANCE;
	}

}
