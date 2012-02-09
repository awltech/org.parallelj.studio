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
package org.parallelj.designer.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.parallelj.designer.edit.parts.BlockEditPart;
import org.parallelj.designer.edit.parts.BlockProcedureEditPart;
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
import org.parallelj.designer.edit.parts.SpecificationEditPart;
import org.parallelj.designer.edit.parts.WhileLoopEditPart;
import org.parallelj.designer.part.ParallelJDiagramEditorPlugin;
import org.parallelj.model.ParallelJPackage;

/**
 * @generated
 */
public class ParallelJElementTypes {

	/**
	 * @generated
	 */
	private ParallelJElementTypes() {
	}

	/**
	 * @generated
	 */
	private static Map<IElementType, ENamedElement> elements;

	/**
	 * @generated
	 */
	private static ImageRegistry imageRegistry;

	/**
	 * @generated
	 */
	private static Set<IElementType> KNOWN_ELEMENT_TYPES;

	/**
	 * @generated
	 */
	public static final IElementType Specification_1000 = getElementType("org.parallelj.designer.Specification_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Program_2001 = getElementType("org.parallelj.designer.Program_2001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType InputCondition_3001 = getElementType("org.parallelj.designer.InputCondition_3001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType OutputCondition_3002 = getElementType("org.parallelj.designer.OutputCondition_3002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Condition_3003 = getElementType("org.parallelj.designer.Condition_3003"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Predicate_3004 = getElementType("org.parallelj.designer.Predicate_3004"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Procedure_3005 = getElementType("org.parallelj.designer.Procedure_3005"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType ForEachLoop_3006 = getElementType("org.parallelj.designer.ForEachLoop_3006"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType WhileLoop_3007 = getElementType("org.parallelj.designer.WhileLoop_3007"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Handler_3008 = getElementType("org.parallelj.designer.Handler_3008"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Block_3012 = getElementType("org.parallelj.designer.Block_3012"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Procedure_3013 = getElementType("org.parallelj.designer.Procedure_3013"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Data_3011 = getElementType("org.parallelj.designer.Data_3011"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Link_4001 = getElementType("org.parallelj.designer.Link_4001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	private static ImageRegistry getImageRegistry() {
		if (imageRegistry == null) {
			imageRegistry = new ImageRegistry();
		}
		return imageRegistry;
	}

	/**
	 * @generated
	 */
	private static String getImageRegistryKey(ENamedElement element) {
		return element.getName();
	}

	/**
	 * @generated
	 */
	private static ImageDescriptor getProvidedImageDescriptor(
			ENamedElement element) {
		if (element instanceof EStructuralFeature) {
			EStructuralFeature feature = ((EStructuralFeature) element);
			EClass eContainingClass = feature.getEContainingClass();
			EClassifier eType = feature.getEType();
			if (eContainingClass != null && !eContainingClass.isAbstract()) {
				element = eContainingClass;
			} else if (eType instanceof EClass
					&& !((EClass) eType).isAbstract()) {
				element = eType;
			}
		}
		if (element instanceof EClass) {
			EClass eClass = (EClass) element;
			if (!eClass.isAbstract()) {
				return ParallelJDiagramEditorPlugin.getInstance()
						.getItemImageDescriptor(
								eClass.getEPackage().getEFactoryInstance()
										.create(eClass));
			}
		}
		// TODO : support structural features
		return null;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		String key = getImageRegistryKey(element);
		ImageDescriptor imageDescriptor = getImageRegistry().getDescriptor(key);
		if (imageDescriptor == null) {
			imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
		}
		return imageDescriptor;
	}

	/**
	 * @generated
	 */
	public static Image getImage(ENamedElement element) {
		String key = getImageRegistryKey(element);
		Image image = getImageRegistry().get(key);
		if (image == null) {
			ImageDescriptor imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
			image = getImageRegistry().get(key);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImageDescriptor(element);
	}

	/**
	 * @generated
	 */
	public static Image getImage(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImage(element);
	}

	/**
	 * Returns 'type' of the ecore object associated with the hint.
	 * 
	 * @generated
	 */
	public static ENamedElement getElement(IAdaptable hint) {
		Object type = hint.getAdapter(IElementType.class);
		if (elements == null) {
			elements = new IdentityHashMap<IElementType, ENamedElement>();

			elements.put(Specification_1000,
					ParallelJPackage.eINSTANCE.getSpecification());

			elements.put(Program_2001, ParallelJPackage.eINSTANCE.getProgram());

			elements.put(InputCondition_3001,
					ParallelJPackage.eINSTANCE.getInputCondition());

			elements.put(OutputCondition_3002,
					ParallelJPackage.eINSTANCE.getOutputCondition());

			elements.put(Condition_3003,
					ParallelJPackage.eINSTANCE.getCondition());

			elements.put(Predicate_3004,
					ParallelJPackage.eINSTANCE.getPredicate());

			elements.put(Procedure_3005,
					ParallelJPackage.eINSTANCE.getProcedure());

			elements.put(ForEachLoop_3006,
					ParallelJPackage.eINSTANCE.getForEachLoop());

			elements.put(WhileLoop_3007,
					ParallelJPackage.eINSTANCE.getWhileLoop());

			elements.put(Handler_3008, ParallelJPackage.eINSTANCE.getHandler());

			elements.put(Block_3012, ParallelJPackage.eINSTANCE.getBlock());

			elements.put(Procedure_3013,
					ParallelJPackage.eINSTANCE.getProcedure());

			elements.put(Data_3011, ParallelJPackage.eINSTANCE.getData());

			elements.put(Link_4001, ParallelJPackage.eINSTANCE.getLink());
		}
		return (ENamedElement) elements.get(type);
	}

	/**
	 * @generated
	 */
	private static IElementType getElementType(String id) {
		return ElementTypeRegistry.getInstance().getType(id);
	}

	/**
	 * @generated
	 */
	public static boolean isKnownElementType(IElementType elementType) {
		if (KNOWN_ELEMENT_TYPES == null) {
			KNOWN_ELEMENT_TYPES = new HashSet<IElementType>();
			KNOWN_ELEMENT_TYPES.add(Specification_1000);
			KNOWN_ELEMENT_TYPES.add(Program_2001);
			KNOWN_ELEMENT_TYPES.add(InputCondition_3001);
			KNOWN_ELEMENT_TYPES.add(OutputCondition_3002);
			KNOWN_ELEMENT_TYPES.add(Condition_3003);
			KNOWN_ELEMENT_TYPES.add(Predicate_3004);
			KNOWN_ELEMENT_TYPES.add(Procedure_3005);
			KNOWN_ELEMENT_TYPES.add(ForEachLoop_3006);
			KNOWN_ELEMENT_TYPES.add(WhileLoop_3007);
			KNOWN_ELEMENT_TYPES.add(Handler_3008);
			KNOWN_ELEMENT_TYPES.add(Block_3012);
			KNOWN_ELEMENT_TYPES.add(Procedure_3013);
			KNOWN_ELEMENT_TYPES.add(Data_3011);
			KNOWN_ELEMENT_TYPES.add(Link_4001);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case SpecificationEditPart.VISUAL_ID:
			return Specification_1000;
		case ProgramEditPart.VISUAL_ID:
			return Program_2001;
		case InputConditionEditPart.VISUAL_ID:
			return InputCondition_3001;
		case OutputConditionEditPart.VISUAL_ID:
			return OutputCondition_3002;
		case ConditionEditPart.VISUAL_ID:
			return Condition_3003;
		case PredicateEditPart.VISUAL_ID:
			return Predicate_3004;
		case ProcedureEditPart.VISUAL_ID:
			return Procedure_3005;
		case ForEachLoopEditPart.VISUAL_ID:
			return ForEachLoop_3006;
		case WhileLoopEditPart.VISUAL_ID:
			return WhileLoop_3007;
		case HandlerEditPart.VISUAL_ID:
			return Handler_3008;
		case BlockEditPart.VISUAL_ID:
			return Block_3012;
		case BlockProcedureEditPart.VISUAL_ID:
			return Procedure_3013;
		case DataEditPart.VISUAL_ID:
			return Data_3011;
		case LinkEditPart.VISUAL_ID:
			return Link_4001;
		}
		return null;
	}

}
