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
package org.parallelj.designer.extension.actions;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.parallelj.model.ParallelJPackage;

/**
 * Enumeration corresponding to the possible Actions proposed by this Diagram
 * Extension.
 */
public enum ActionID {

	SPLIT_AND("SplitAnd"), SPLIT_OR("SplitOr"), SPLIT_XOR("SplitXor"), JOIN_AND(
			"JoinAnd"), JOIN_OR("JoinOr"), JOIN_XOR("JoinXor"), SET_LINK_PREDICATE(
			"SetLinkPredicate"), SET_WHILELOOP_PREDICATE(
			"SetWhileLoopPredicate"), FOREACHLOOP_ITERABLE(
			"ForEachLoopIterable"), PIPELINE_ITERABLE("PipelineIterable"), GENERATE_CODE(
			"GenerateCode");

	/**
	 * Action Label
	 */
	private final String label;

	/**
	 * Creates new Action ID with label
	 * 
	 * @param label
	 */
	private ActionID(String label) {
		this.label = label;
	}

	/**
	 * @return ActionID's label
	 */
	public String label() {
		return this.label;
	}

	/**
	 * Returns the EMF EStructuralFeature associated to ActionID.
	 * 
	 * @return EStructuralFeature
	 */
	public EStructuralFeature getFeatureToUpdate() {
		switch (this) {
		case JOIN_AND:
		case JOIN_OR:
		case JOIN_XOR:
			return ParallelJPackage.eINSTANCE.getProcedure_Join();
		case SPLIT_AND:
		case SPLIT_OR:
		case SPLIT_XOR:
			return ParallelJPackage.eINSTANCE.getProcedure_Split();
		case SET_LINK_PREDICATE:
			return ParallelJPackage.eINSTANCE.getLink_Predicate();
		case SET_WHILELOOP_PREDICATE:
			return ParallelJPackage.eINSTANCE.getWhileLoop_Predicate();
		case FOREACHLOOP_ITERABLE:
			return ParallelJPackage.eINSTANCE.getForEachLoop_Iterable();
		case PIPELINE_ITERABLE:
			return ParallelJPackage.eINSTANCE.getPipeline_Iterable();
		case GENERATE_CODE:
			return ParallelJPackage.eINSTANCE
					.getMetaInformationContainer_MetaInformation();

		default:
			return null;
		}
	}

	/**
	 * Creates new ActionID from label passed as parameter
	 * 
	 * @param label
	 * @return ActionID
	 */
	public static ActionID createFromLabel(String label) {
		for (ActionID actionID : ActionID.values())
			if (label.equals(actionID.label()))
				return actionID;
		return null;
	}

}
