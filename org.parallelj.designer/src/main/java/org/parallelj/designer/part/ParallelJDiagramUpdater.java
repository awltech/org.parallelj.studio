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

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.parallelj.designer.edit.parts.BlockBlockCompartmentEditPart;
import org.parallelj.designer.edit.parts.BlockEditPart;
import org.parallelj.designer.edit.parts.BlockProcedureEditPart;
import org.parallelj.designer.edit.parts.BusinessProcedureEditPart;
import org.parallelj.designer.edit.parts.ConditionEditPart;
import org.parallelj.designer.edit.parts.DataEditPart;
import org.parallelj.designer.edit.parts.ForEachLoopEditPart;
import org.parallelj.designer.edit.parts.HandlerEditPart;
import org.parallelj.designer.edit.parts.InputConditionEditPart;
import org.parallelj.designer.edit.parts.LinkEditPart;
import org.parallelj.designer.edit.parts.OutputConditionEditPart;
import org.parallelj.designer.edit.parts.PredicateEditPart;
import org.parallelj.designer.edit.parts.ProcedureEditPart;
import org.parallelj.designer.edit.parts.ProgramEditPart;
import org.parallelj.designer.edit.parts.ProgramProgramCompartmentEditPart;
import org.parallelj.designer.edit.parts.SpecificationEditPart;
import org.parallelj.designer.edit.parts.WhileLoopEditPart;
import org.parallelj.designer.providers.ParallelJElementTypes;
import org.parallelj.model.Block;
import org.parallelj.model.BusinessProcedure;
import org.parallelj.model.Condition;
import org.parallelj.model.Data;
import org.parallelj.model.Element;
import org.parallelj.model.ForEachLoop;
import org.parallelj.model.Handler;
import org.parallelj.model.InputCondition;
import org.parallelj.model.Link;
import org.parallelj.model.OutputCondition;
import org.parallelj.model.ParallelJPackage;
import org.parallelj.model.Predicate;
import org.parallelj.model.Procedure;
import org.parallelj.model.Program;
import org.parallelj.model.Specification;
import org.parallelj.model.WhileLoop;

/**
 * @generated
 */
public class ParallelJDiagramUpdater {

	/**
	 * @generated
	 */
	public static List<ParallelJNodeDescriptor> getSemanticChildren(View view) {
		switch (ParallelJVisualIDRegistry.getVisualID(view)) {
		case SpecificationEditPart.VISUAL_ID:
			return getSpecification_1000SemanticChildren(view);
		case ProgramProgramCompartmentEditPart.VISUAL_ID:
			return getProgramProgramCompartment_7001SemanticChildren(view);
		case BlockBlockCompartmentEditPart.VISUAL_ID:
			return getBlockBlockCompartment_7003SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ParallelJNodeDescriptor> getSpecification_1000SemanticChildren(
			View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		Specification modelElement = (Specification) view.getElement();
		LinkedList<ParallelJNodeDescriptor> result = new LinkedList<ParallelJNodeDescriptor>();
		for (Iterator<?> it = modelElement.getPrograms().iterator(); it
				.hasNext();) {
			Program childElement = (Program) it.next();
			int visualID = ParallelJVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ProgramEditPart.VISUAL_ID) {
				result.add(new ParallelJNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJNodeDescriptor> getProgramProgramCompartment_7001SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		Program modelElement = (Program) containerView.getElement();
		LinkedList<ParallelJNodeDescriptor> result = new LinkedList<ParallelJNodeDescriptor>();
		{
			InputCondition childElement = modelElement.getInputCondition();
			int visualID = ParallelJVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == InputConditionEditPart.VISUAL_ID) {
				result.add(new ParallelJNodeDescriptor(childElement, visualID));
			}
		}
		{
			OutputCondition childElement = modelElement.getOutputCondition();
			int visualID = ParallelJVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == OutputConditionEditPart.VISUAL_ID) {
				result.add(new ParallelJNodeDescriptor(childElement, visualID));
			}
		}
		for (Iterator<?> it = modelElement.getElements().iterator(); it
				.hasNext();) {
			Element childElement = (Element) it.next();
			int visualID = ParallelJVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ConditionEditPart.VISUAL_ID) {
				result.add(new ParallelJNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ProcedureEditPart.VISUAL_ID) {
				result.add(new ParallelJNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ForEachLoopEditPart.VISUAL_ID) {
				result.add(new ParallelJNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == WhileLoopEditPart.VISUAL_ID) {
				result.add(new ParallelJNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == HandlerEditPart.VISUAL_ID) {
				result.add(new ParallelJNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == BlockEditPart.VISUAL_ID) {
				result.add(new ParallelJNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == BusinessProcedureEditPart.VISUAL_ID) {
				result.add(new ParallelJNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getPredicates().iterator(); it
				.hasNext();) {
			Predicate childElement = (Predicate) it.next();
			int visualID = ParallelJVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == PredicateEditPart.VISUAL_ID) {
				result.add(new ParallelJNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getData().iterator(); it.hasNext();) {
			Data childElement = (Data) it.next();
			int visualID = ParallelJVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == DataEditPart.VISUAL_ID) {
				result.add(new ParallelJNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJNodeDescriptor> getBlockBlockCompartment_7003SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		Block modelElement = (Block) containerView.getElement();
		LinkedList<ParallelJNodeDescriptor> result = new LinkedList<ParallelJNodeDescriptor>();
		for (Iterator<?> it = modelElement.getProcedures().iterator(); it
				.hasNext();) {
			Procedure childElement = (Procedure) it.next();
			int visualID = ParallelJVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == BlockProcedureEditPart.VISUAL_ID) {
				result.add(new ParallelJNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getContainedLinks(View view) {
		switch (ParallelJVisualIDRegistry.getVisualID(view)) {
		case SpecificationEditPart.VISUAL_ID:
			return getSpecification_1000ContainedLinks(view);
		case ProgramEditPart.VISUAL_ID:
			return getProgram_2001ContainedLinks(view);
		case InputConditionEditPart.VISUAL_ID:
			return getInputCondition_3001ContainedLinks(view);
		case OutputConditionEditPart.VISUAL_ID:
			return getOutputCondition_3002ContainedLinks(view);
		case ConditionEditPart.VISUAL_ID:
			return getCondition_3003ContainedLinks(view);
		case PredicateEditPart.VISUAL_ID:
			return getPredicate_3004ContainedLinks(view);
		case ProcedureEditPart.VISUAL_ID:
			return getProcedure_3005ContainedLinks(view);
		case ForEachLoopEditPart.VISUAL_ID:
			return getForEachLoop_3006ContainedLinks(view);
		case WhileLoopEditPart.VISUAL_ID:
			return getWhileLoop_3007ContainedLinks(view);
		case HandlerEditPart.VISUAL_ID:
			return getHandler_3008ContainedLinks(view);
		case BlockEditPart.VISUAL_ID:
			return getBlock_3012ContainedLinks(view);
		case BlockProcedureEditPart.VISUAL_ID:
			return getProcedure_3013ContainedLinks(view);
		case DataEditPart.VISUAL_ID:
			return getData_3011ContainedLinks(view);
		case BusinessProcedureEditPart.VISUAL_ID:
			return getBusinessProcedure_3014ContainedLinks(view);
		case LinkEditPart.VISUAL_ID:
			return getLink_4001ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getIncomingLinks(View view) {
		switch (ParallelJVisualIDRegistry.getVisualID(view)) {
		case ProgramEditPart.VISUAL_ID:
			return getProgram_2001IncomingLinks(view);
		case InputConditionEditPart.VISUAL_ID:
			return getInputCondition_3001IncomingLinks(view);
		case OutputConditionEditPart.VISUAL_ID:
			return getOutputCondition_3002IncomingLinks(view);
		case ConditionEditPart.VISUAL_ID:
			return getCondition_3003IncomingLinks(view);
		case PredicateEditPart.VISUAL_ID:
			return getPredicate_3004IncomingLinks(view);
		case ProcedureEditPart.VISUAL_ID:
			return getProcedure_3005IncomingLinks(view);
		case ForEachLoopEditPart.VISUAL_ID:
			return getForEachLoop_3006IncomingLinks(view);
		case WhileLoopEditPart.VISUAL_ID:
			return getWhileLoop_3007IncomingLinks(view);
		case HandlerEditPart.VISUAL_ID:
			return getHandler_3008IncomingLinks(view);
		case BlockEditPart.VISUAL_ID:
			return getBlock_3012IncomingLinks(view);
		case BlockProcedureEditPart.VISUAL_ID:
			return getProcedure_3013IncomingLinks(view);
		case DataEditPart.VISUAL_ID:
			return getData_3011IncomingLinks(view);
		case BusinessProcedureEditPart.VISUAL_ID:
			return getBusinessProcedure_3014IncomingLinks(view);
		case LinkEditPart.VISUAL_ID:
			return getLink_4001IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getOutgoingLinks(View view) {
		switch (ParallelJVisualIDRegistry.getVisualID(view)) {
		case ProgramEditPart.VISUAL_ID:
			return getProgram_2001OutgoingLinks(view);
		case InputConditionEditPart.VISUAL_ID:
			return getInputCondition_3001OutgoingLinks(view);
		case OutputConditionEditPart.VISUAL_ID:
			return getOutputCondition_3002OutgoingLinks(view);
		case ConditionEditPart.VISUAL_ID:
			return getCondition_3003OutgoingLinks(view);
		case PredicateEditPart.VISUAL_ID:
			return getPredicate_3004OutgoingLinks(view);
		case ProcedureEditPart.VISUAL_ID:
			return getProcedure_3005OutgoingLinks(view);
		case ForEachLoopEditPart.VISUAL_ID:
			return getForEachLoop_3006OutgoingLinks(view);
		case WhileLoopEditPart.VISUAL_ID:
			return getWhileLoop_3007OutgoingLinks(view);
		case HandlerEditPart.VISUAL_ID:
			return getHandler_3008OutgoingLinks(view);
		case BlockEditPart.VISUAL_ID:
			return getBlock_3012OutgoingLinks(view);
		case BlockProcedureEditPart.VISUAL_ID:
			return getProcedure_3013OutgoingLinks(view);
		case DataEditPart.VISUAL_ID:
			return getData_3011OutgoingLinks(view);
		case BusinessProcedureEditPart.VISUAL_ID:
			return getBusinessProcedure_3014OutgoingLinks(view);
		case LinkEditPart.VISUAL_ID:
			return getLink_4001OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getSpecification_1000ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getProgram_2001ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getInputCondition_3001ContainedLinks(
			View view) {
		InputCondition modelElement = (InputCondition) view.getElement();
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getOutputCondition_3002ContainedLinks(
			View view) {
		OutputCondition modelElement = (OutputCondition) view.getElement();
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getCondition_3003ContainedLinks(
			View view) {
		Condition modelElement = (Condition) view.getElement();
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getPredicate_3004ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getProcedure_3005ContainedLinks(
			View view) {
		Procedure modelElement = (Procedure) view.getElement();
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getForEachLoop_3006ContainedLinks(
			View view) {
		ForEachLoop modelElement = (ForEachLoop) view.getElement();
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getWhileLoop_3007ContainedLinks(
			View view) {
		WhileLoop modelElement = (WhileLoop) view.getElement();
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getHandler_3008ContainedLinks(
			View view) {
		Handler modelElement = (Handler) view.getElement();
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getBlock_3012ContainedLinks(
			View view) {
		Block modelElement = (Block) view.getElement();
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getProcedure_3013ContainedLinks(
			View view) {
		Procedure modelElement = (Procedure) view.getElement();
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getData_3011ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getBusinessProcedure_3014ContainedLinks(
			View view) {
		BusinessProcedure modelElement = (BusinessProcedure) view.getElement();
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getLink_4001ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getProgram_2001IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getInputCondition_3001IncomingLinks(
			View view) {
		InputCondition modelElement = (InputCondition) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Link_4001(modelElement,
				crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getOutputCondition_3002IncomingLinks(
			View view) {
		OutputCondition modelElement = (OutputCondition) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Link_4001(modelElement,
				crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getCondition_3003IncomingLinks(
			View view) {
		Condition modelElement = (Condition) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Link_4001(modelElement,
				crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getPredicate_3004IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getProcedure_3005IncomingLinks(
			View view) {
		Procedure modelElement = (Procedure) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Link_4001(modelElement,
				crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getForEachLoop_3006IncomingLinks(
			View view) {
		ForEachLoop modelElement = (ForEachLoop) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Link_4001(modelElement,
				crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getWhileLoop_3007IncomingLinks(
			View view) {
		WhileLoop modelElement = (WhileLoop) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Link_4001(modelElement,
				crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getHandler_3008IncomingLinks(
			View view) {
		Handler modelElement = (Handler) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Link_4001(modelElement,
				crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getBlock_3012IncomingLinks(
			View view) {
		Block modelElement = (Block) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Link_4001(modelElement,
				crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getProcedure_3013IncomingLinks(
			View view) {
		Procedure modelElement = (Procedure) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Link_4001(modelElement,
				crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getData_3011IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getBusinessProcedure_3014IncomingLinks(
			View view) {
		BusinessProcedure modelElement = (BusinessProcedure) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Link_4001(modelElement,
				crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getLink_4001IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getProgram_2001OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getInputCondition_3001OutgoingLinks(
			View view) {
		InputCondition modelElement = (InputCondition) view.getElement();
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getOutputCondition_3002OutgoingLinks(
			View view) {
		OutputCondition modelElement = (OutputCondition) view.getElement();
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getCondition_3003OutgoingLinks(
			View view) {
		Condition modelElement = (Condition) view.getElement();
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getPredicate_3004OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getProcedure_3005OutgoingLinks(
			View view) {
		Procedure modelElement = (Procedure) view.getElement();
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getForEachLoop_3006OutgoingLinks(
			View view) {
		ForEachLoop modelElement = (ForEachLoop) view.getElement();
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getWhileLoop_3007OutgoingLinks(
			View view) {
		WhileLoop modelElement = (WhileLoop) view.getElement();
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getHandler_3008OutgoingLinks(
			View view) {
		Handler modelElement = (Handler) view.getElement();
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getBlock_3012OutgoingLinks(
			View view) {
		Block modelElement = (Block) view.getElement();
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getProcedure_3013OutgoingLinks(
			View view) {
		Procedure modelElement = (Procedure) view.getElement();
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getData_3011OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getBusinessProcedure_3014OutgoingLinks(
			View view) {
		BusinessProcedure modelElement = (BusinessProcedure) view.getElement();
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ParallelJLinkDescriptor> getLink_4001OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	private static Collection<ParallelJLinkDescriptor> getContainedTypeModelFacetLinks_Link_4001(
			Element container) {
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		for (Iterator<?> links = container.getOutputLinks().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Link) {
				continue;
			}
			Link link = (Link) linkObject;
			if (LinkEditPart.VISUAL_ID != ParallelJVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element dst = link.getDestination();
			result.add(new ParallelJLinkDescriptor(container, dst, link,
					ParallelJElementTypes.Link_4001, LinkEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ParallelJLinkDescriptor> getIncomingTypeModelFacetLinks_Link_4001(
			Element target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<ParallelJLinkDescriptor> result = new LinkedList<ParallelJLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != ParallelJPackage.eINSTANCE
					.getLink_Destination()
					|| false == setting.getEObject() instanceof Link) {
				continue;
			}
			Link link = (Link) setting.getEObject();
			if (LinkEditPart.VISUAL_ID != ParallelJVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			if (false == link.eContainer() instanceof Element) {
				continue;
			}
			Element container = (Element) link.eContainer();
			result.add(new ParallelJLinkDescriptor(container, target, link,
					ParallelJElementTypes.Link_4001, LinkEditPart.VISUAL_ID));

		}
		return result;
	}

}
