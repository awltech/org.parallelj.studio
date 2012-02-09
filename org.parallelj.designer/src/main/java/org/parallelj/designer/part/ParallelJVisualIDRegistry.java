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
package org.parallelj.designer.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.parallelj.designer.edit.parts.BlockBlockCompartmentEditPart;
import org.parallelj.designer.edit.parts.BlockEditPart;
import org.parallelj.designer.edit.parts.BlockIconEditPart;
import org.parallelj.designer.edit.parts.BlockNameEditPart;
import org.parallelj.designer.edit.parts.BlockProcedureEditPart;
import org.parallelj.designer.edit.parts.BlockProcedureExecutableEditPart;
import org.parallelj.designer.edit.parts.BlockProcedureNameEditPart;
import org.parallelj.designer.edit.parts.ConditionEditPart;
import org.parallelj.designer.edit.parts.ConditionNameEditPart;
import org.parallelj.designer.edit.parts.DataEditPart;
import org.parallelj.designer.edit.parts.DataNameEditPart;
import org.parallelj.designer.edit.parts.DataTypeEditPart;
import org.parallelj.designer.edit.parts.ForEachLoopEditPart;
import org.parallelj.designer.edit.parts.ForEachLoopExecutableEditPart;
import org.parallelj.designer.edit.parts.ForEachLoopIterableEditPart;
import org.parallelj.designer.edit.parts.ForEachLoopNameEditPart;
import org.parallelj.designer.edit.parts.HandlerEditPart;
import org.parallelj.designer.edit.parts.HandlerNameEditPart;
import org.parallelj.designer.edit.parts.InputConditionEditPart;
import org.parallelj.designer.edit.parts.InputConditionNameEditPart;
import org.parallelj.designer.edit.parts.LinkEditPart;
import org.parallelj.designer.edit.parts.LinkPredicateInfoEditPart;
import org.parallelj.designer.edit.parts.OutputConditionEditPart;
import org.parallelj.designer.edit.parts.OutputConditionNameEditPart;
import org.parallelj.designer.edit.parts.PredicateEditPart;
import org.parallelj.designer.edit.parts.PredicateNameEditPart;
import org.parallelj.designer.edit.parts.ProcedureEditPart;
import org.parallelj.designer.edit.parts.ProcedureExecutableEditPart;
import org.parallelj.designer.edit.parts.ProcedureNameEditPart;
import org.parallelj.designer.edit.parts.ProgramEditPart;
import org.parallelj.designer.edit.parts.ProgramNameEditPart;
import org.parallelj.designer.edit.parts.ProgramProgramCompartmentEditPart;
import org.parallelj.designer.edit.parts.SpecificationEditPart;
import org.parallelj.designer.edit.parts.WhileLoopEditPart;
import org.parallelj.designer.edit.parts.WhileLoopExecutableEditPart;
import org.parallelj.designer.edit.parts.WhileLoopNameEditPart;
import org.parallelj.designer.edit.parts.WhileLoopPredicateEditPart;
import org.parallelj.designer.expressions.ParallelJOCLFactory;
import org.parallelj.model.ParallelJPackage;
import org.parallelj.model.Procedure;
import org.parallelj.model.Specification;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class ParallelJVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "org.parallelj.designer/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (SpecificationEditPart.MODEL_ID.equals(view.getType())) {
				return SpecificationEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return org.parallelj.designer.part.ParallelJVisualIDRegistry
				.getVisualID(view.getType());
	}

	/**
	 * @generated
	 */
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	 * @generated
	 */
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(
					Platform.getDebugOption(DEBUG_KEY))) {
				ParallelJDiagramEditorPlugin.getInstance().logError(
						"Unable to parse view type as a visualID number: "
								+ type);
			}
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getType(int visualID) {
		return Integer.toString(visualID);
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (ParallelJPackage.eINSTANCE.getSpecification().isSuperTypeOf(
				domainElement.eClass())
				&& isDiagram((Specification) domainElement)) {
			return SpecificationEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		String containerModelID = org.parallelj.designer.part.ParallelJVisualIDRegistry
				.getModelID(containerView);
		if (!SpecificationEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (SpecificationEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = org.parallelj.designer.part.ParallelJVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = SpecificationEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case SpecificationEditPart.VISUAL_ID:
			if (ParallelJPackage.eINSTANCE.getProgram().isSuperTypeOf(
					domainElement.eClass())) {
				return ProgramEditPart.VISUAL_ID;
			}
			break;
		case ProgramProgramCompartmentEditPart.VISUAL_ID:
			if (ParallelJPackage.eINSTANCE.getInputCondition().isSuperTypeOf(
					domainElement.eClass())) {
				return InputConditionEditPart.VISUAL_ID;
			}
			if (ParallelJPackage.eINSTANCE.getOutputCondition().isSuperTypeOf(
					domainElement.eClass())) {
				return OutputConditionEditPart.VISUAL_ID;
			}
			if (ParallelJPackage.eINSTANCE.getCondition().isSuperTypeOf(
					domainElement.eClass())) {
				return ConditionEditPart.VISUAL_ID;
			}
			if (ParallelJPackage.eINSTANCE.getPredicate().isSuperTypeOf(
					domainElement.eClass())) {
				return PredicateEditPart.VISUAL_ID;
			}
			if (ParallelJPackage.eINSTANCE.getProcedure().isSuperTypeOf(
					domainElement.eClass())
					&& isProcedure_3005((Procedure) domainElement)) {
				return ProcedureEditPart.VISUAL_ID;
			}
			if (ParallelJPackage.eINSTANCE.getForEachLoop().isSuperTypeOf(
					domainElement.eClass())) {
				return ForEachLoopEditPart.VISUAL_ID;
			}
			if (ParallelJPackage.eINSTANCE.getWhileLoop().isSuperTypeOf(
					domainElement.eClass())) {
				return WhileLoopEditPart.VISUAL_ID;
			}
			if (ParallelJPackage.eINSTANCE.getHandler().isSuperTypeOf(
					domainElement.eClass())) {
				return HandlerEditPart.VISUAL_ID;
			}
			if (ParallelJPackage.eINSTANCE.getBlock().isSuperTypeOf(
					domainElement.eClass())) {
				return BlockEditPart.VISUAL_ID;
			}
			if (ParallelJPackage.eINSTANCE.getData().isSuperTypeOf(
					domainElement.eClass())) {
				return DataEditPart.VISUAL_ID;
			}
			break;
		case BlockBlockCompartmentEditPart.VISUAL_ID:
			if (ParallelJPackage.eINSTANCE.getProcedure().isSuperTypeOf(
					domainElement.eClass())) {
				return BlockProcedureEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = org.parallelj.designer.part.ParallelJVisualIDRegistry
				.getModelID(containerView);
		if (!SpecificationEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (SpecificationEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = org.parallelj.designer.part.ParallelJVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = SpecificationEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case SpecificationEditPart.VISUAL_ID:
			if (ProgramEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ProgramEditPart.VISUAL_ID:
			if (ProgramNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ProgramProgramCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case InputConditionEditPart.VISUAL_ID:
			if (InputConditionNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case OutputConditionEditPart.VISUAL_ID:
			if (OutputConditionNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ConditionEditPart.VISUAL_ID:
			if (ConditionNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case PredicateEditPart.VISUAL_ID:
			if (PredicateNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ProcedureEditPart.VISUAL_ID:
			if (ProcedureNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ProcedureExecutableEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ForEachLoopEditPart.VISUAL_ID:
			if (ForEachLoopNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ForEachLoopExecutableEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ForEachLoopIterableEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case WhileLoopEditPart.VISUAL_ID:
			if (WhileLoopNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (WhileLoopExecutableEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (WhileLoopPredicateEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case HandlerEditPart.VISUAL_ID:
			if (HandlerNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BlockEditPart.VISUAL_ID:
			if (BlockNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BlockIconEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BlockBlockCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BlockProcedureEditPart.VISUAL_ID:
			if (BlockProcedureNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BlockProcedureExecutableEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DataEditPart.VISUAL_ID:
			if (DataNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DataTypeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ProgramProgramCompartmentEditPart.VISUAL_ID:
			if (InputConditionEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (OutputConditionEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ConditionEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (PredicateEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ProcedureEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ForEachLoopEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (WhileLoopEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (HandlerEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BlockEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DataEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BlockBlockCompartmentEditPart.VISUAL_ID:
			if (BlockProcedureEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case LinkEditPart.VISUAL_ID:
			if (LinkPredicateInfoEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (ParallelJPackage.eINSTANCE.getLink().isSuperTypeOf(
				domainElement.eClass())) {
			return LinkEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(Specification element) {
		return true;
	}

	/**
	 * @generated
	 */
	private static boolean isProcedure_3005(Procedure domainElement) {
		Object result = ParallelJOCLFactory.getExpression(0,
				ParallelJPackage.eINSTANCE.getProcedure(), null).evaluate(
				domainElement);
		return result instanceof Boolean && ((Boolean) result).booleanValue();
	}

}
