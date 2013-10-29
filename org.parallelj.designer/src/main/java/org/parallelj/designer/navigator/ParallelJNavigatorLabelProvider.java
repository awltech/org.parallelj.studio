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
package org.parallelj.designer.navigator;

import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ITreePathLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;
import org.parallelj.designer.edit.parts.BusinessProcedureEditPart;
import org.parallelj.designer.edit.parts.BusinessProcedureNameEditPart;
import org.parallelj.designer.edit.parts.ConditionEditPart;
import org.parallelj.designer.edit.parts.ConditionNameEditPart;
import org.parallelj.designer.edit.parts.DataEditPart;
import org.parallelj.designer.edit.parts.DataNameEditPart;
import org.parallelj.designer.edit.parts.ForEachLoopEditPart;
import org.parallelj.designer.edit.parts.ForEachLoopNameEditPart;
import org.parallelj.designer.edit.parts.HandlerEditPart;
import org.parallelj.designer.edit.parts.HandlerNameEditPart;
import org.parallelj.designer.edit.parts.InputConditionEditPart;
import org.parallelj.designer.edit.parts.InputConditionNameEditPart;
import org.parallelj.designer.edit.parts.LinkEditPart;
import org.parallelj.designer.edit.parts.LinkPredicateInfoEditPart;
import org.parallelj.designer.edit.parts.OutputConditionEditPart;
import org.parallelj.designer.edit.parts.OutputConditionNameEditPart;
import org.parallelj.designer.edit.parts.PipelineEditPart;
import org.parallelj.designer.edit.parts.PipelineNameEditPart;
import org.parallelj.designer.edit.parts.PipelineProcedureEditPart;
import org.parallelj.designer.edit.parts.PipelineProcedureNameEditPart;
import org.parallelj.designer.edit.parts.PredicateEditPart;
import org.parallelj.designer.edit.parts.PredicateNameEditPart;
import org.parallelj.designer.edit.parts.ProcedureEditPart;
import org.parallelj.designer.edit.parts.ProcedureNameEditPart;
import org.parallelj.designer.edit.parts.ProgramEditPart;
import org.parallelj.designer.edit.parts.ProgramNameEditPart;
import org.parallelj.designer.edit.parts.SpecificationEditPart;
import org.parallelj.designer.edit.parts.WhileLoopEditPart;
import org.parallelj.designer.edit.parts.WhileLoopNameEditPart;
import org.parallelj.designer.part.ParallelJDiagramEditorPlugin;
import org.parallelj.designer.part.ParallelJVisualIDRegistry;
import org.parallelj.designer.providers.ParallelJElementTypes;
import org.parallelj.designer.providers.ParallelJParserProvider;

/**
 * @generated
 */
public class ParallelJNavigatorLabelProvider extends LabelProvider implements
		ICommonLabelProvider, ITreePathLabelProvider {

	/**
	 * @generated
	 */
	static {
		ParallelJDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
		ParallelJDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof ParallelJNavigatorItem
				&& !isOwnView(((ParallelJNavigatorItem) element).getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	 * @generated
	 */
	public Image getImage(Object element) {
		if (element instanceof ParallelJNavigatorGroup) {
			ParallelJNavigatorGroup group = (ParallelJNavigatorGroup) element;
			return ParallelJDiagramEditorPlugin.getInstance().getBundledImage(
					group.getIcon());
		}

		if (element instanceof ParallelJNavigatorItem) {
			ParallelJNavigatorItem navigatorItem = (ParallelJNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return super.getImage(element);
			}
			return getImage(navigatorItem.getView());
		}

		return super.getImage(element);
	}

	/**
	 * @generated
	 */
	public Image getImage(View view) {
		switch (ParallelJVisualIDRegistry.getVisualID(view)) {
		case WhileLoopEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.parallelj.org/0.5.0?WhileLoop", ParallelJElementTypes.WhileLoop_3007); //$NON-NLS-1$
		case PredicateEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.parallelj.org/0.5.0?Predicate", ParallelJElementTypes.Predicate_3004); //$NON-NLS-1$
		case LinkEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.parallelj.org/0.5.0?Link", ParallelJElementTypes.Link_4001); //$NON-NLS-1$
		case DataEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.parallelj.org/0.5.0?Data", ParallelJElementTypes.Data_3011); //$NON-NLS-1$
		case OutputConditionEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.parallelj.org/0.5.0?OutputCondition", ParallelJElementTypes.OutputCondition_3002); //$NON-NLS-1$
		case ProcedureEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.parallelj.org/0.5.0?Procedure", ParallelJElementTypes.Procedure_3005); //$NON-NLS-1$
		case InputConditionEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.parallelj.org/0.5.0?InputCondition", ParallelJElementTypes.InputCondition_3001); //$NON-NLS-1$
		case SpecificationEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Diagram?http://www.parallelj.org/0.5.0?Specification", ParallelJElementTypes.Specification_1000); //$NON-NLS-1$
		case HandlerEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.parallelj.org/0.5.0?Handler", ParallelJElementTypes.Handler_3008); //$NON-NLS-1$
		case PipelineEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.parallelj.org/0.5.0?Pipeline", ParallelJElementTypes.Pipeline_3015); //$NON-NLS-1$
		case ForEachLoopEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.parallelj.org/0.5.0?ForEachLoop", ParallelJElementTypes.ForEachLoop_3006); //$NON-NLS-1$
		case BusinessProcedureEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.parallelj.org/0.5.0?BusinessProcedure", ParallelJElementTypes.BusinessProcedure_3014); //$NON-NLS-1$
		case PipelineProcedureEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.parallelj.org/0.5.0?Procedure", ParallelJElementTypes.Procedure_3016); //$NON-NLS-1$
		case ProgramEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://www.parallelj.org/0.5.0?Program", ParallelJElementTypes.Program_2001); //$NON-NLS-1$
		case ConditionEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.parallelj.org/0.5.0?Condition", ParallelJElementTypes.Condition_3003); //$NON-NLS-1$
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = ParallelJDiagramEditorPlugin
				.getInstance().getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null && elementType != null
				&& ParallelJElementTypes.isKnownElementType(elementType)) {
			image = ParallelJElementTypes.getImage(elementType);
			imageRegistry.put(key, image);
		}

		if (image == null) {
			image = imageRegistry.get("Navigator?ImageNotFound"); //$NON-NLS-1$
			imageRegistry.put(key, image);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public String getText(Object element) {
		if (element instanceof ParallelJNavigatorGroup) {
			ParallelJNavigatorGroup group = (ParallelJNavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof ParallelJNavigatorItem) {
			ParallelJNavigatorItem navigatorItem = (ParallelJNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return null;
			}
			return getText(navigatorItem.getView());
		}

		return super.getText(element);
	}

	/**
	 * @generated
	 */
	public String getText(View view) {
		if (view.getElement() != null && view.getElement().eIsProxy()) {
			return getUnresolvedDomainElementProxyText(view);
		}
		switch (ParallelJVisualIDRegistry.getVisualID(view)) {
		case WhileLoopEditPart.VISUAL_ID:
			return getWhileLoop_3007Text(view);
		case PredicateEditPart.VISUAL_ID:
			return getPredicate_3004Text(view);
		case LinkEditPart.VISUAL_ID:
			return getLink_4001Text(view);
		case DataEditPart.VISUAL_ID:
			return getData_3011Text(view);
		case OutputConditionEditPart.VISUAL_ID:
			return getOutputCondition_3002Text(view);
		case ProcedureEditPart.VISUAL_ID:
			return getProcedure_3005Text(view);
		case InputConditionEditPart.VISUAL_ID:
			return getInputCondition_3001Text(view);
		case SpecificationEditPart.VISUAL_ID:
			return getSpecification_1000Text(view);
		case HandlerEditPart.VISUAL_ID:
			return getHandler_3008Text(view);
		case PipelineEditPart.VISUAL_ID:
			return getPipeline_3015Text(view);
		case ForEachLoopEditPart.VISUAL_ID:
			return getForEachLoop_3006Text(view);
		case BusinessProcedureEditPart.VISUAL_ID:
			return getBusinessProcedure_3014Text(view);
		case PipelineProcedureEditPart.VISUAL_ID:
			return getProcedure_3016Text(view);
		case ProgramEditPart.VISUAL_ID:
			return getProgram_2001Text(view);
		case ConditionEditPart.VISUAL_ID:
			return getCondition_3003Text(view);
		}
		return getUnknownElementText(view);
	}

	/**
	 * @generated
	 */
	private String getData_3011Text(View view) {
		IParser parser = ParallelJParserProvider.getParser(
				ParallelJElementTypes.Data_3011,
				view.getElement() != null ? view.getElement() : view,
				ParallelJVisualIDRegistry.getType(DataNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ParallelJDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5018); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getProgram_2001Text(View view) {
		IParser parser = ParallelJParserProvider.getParser(
				ParallelJElementTypes.Program_2001,
				view.getElement() != null ? view.getElement() : view,
				ParallelJVisualIDRegistry
						.getType(ProgramNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ParallelJDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5020); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getHandler_3008Text(View view) {
		IParser parser = ParallelJParserProvider.getParser(
				ParallelJElementTypes.Handler_3008,
				view.getElement() != null ? view.getElement() : view,
				ParallelJVisualIDRegistry
						.getType(HandlerNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ParallelJDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5013); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getOutputCondition_3002Text(View view) {
		IParser parser = ParallelJParserProvider.getParser(
				ParallelJElementTypes.OutputCondition_3002,
				view.getElement() != null ? view.getElement() : view,
				ParallelJVisualIDRegistry
						.getType(OutputConditionNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ParallelJDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5002); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getLink_4001Text(View view) {
		IParser parser = ParallelJParserProvider.getParser(
				ParallelJElementTypes.Link_4001,
				view.getElement() != null ? view.getElement() : view,
				ParallelJVisualIDRegistry
						.getType(LinkPredicateInfoEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ParallelJDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 6001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getForEachLoop_3006Text(View view) {
		IParser parser = ParallelJParserProvider.getParser(
				ParallelJElementTypes.ForEachLoop_3006,
				view.getElement() != null ? view.getElement() : view,
				ParallelJVisualIDRegistry
						.getType(ForEachLoopNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ParallelJDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5007); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getBusinessProcedure_3014Text(View view) {
		IParser parser = ParallelJParserProvider.getParser(
				ParallelJElementTypes.BusinessProcedure_3014,
				view.getElement() != null ? view.getElement() : view,
				ParallelJVisualIDRegistry
						.getType(BusinessProcedureNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ParallelJDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5025); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getWhileLoop_3007Text(View view) {
		IParser parser = ParallelJParserProvider.getParser(
				ParallelJElementTypes.WhileLoop_3007,
				view.getElement() != null ? view.getElement() : view,
				ParallelJVisualIDRegistry
						.getType(WhileLoopNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ParallelJDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5010); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getPipeline_3015Text(View view) {
		IParser parser = ParallelJParserProvider.getParser(
				ParallelJElementTypes.Pipeline_3015,
				view.getElement() != null ? view.getElement() : view,
				ParallelJVisualIDRegistry
						.getType(PipelineNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ParallelJDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5028); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getProcedure_3005Text(View view) {
		IParser parser = ParallelJParserProvider.getParser(
				ParallelJElementTypes.Procedure_3005,
				view.getElement() != null ? view.getElement() : view,
				ParallelJVisualIDRegistry
						.getType(ProcedureNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ParallelJDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5005); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getPredicate_3004Text(View view) {
		IParser parser = ParallelJParserProvider.getParser(
				ParallelJElementTypes.Predicate_3004,
				view.getElement() != null ? view.getElement() : view,
				ParallelJVisualIDRegistry
						.getType(PredicateNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ParallelJDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5004); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getCondition_3003Text(View view) {
		IParser parser = ParallelJParserProvider.getParser(
				ParallelJElementTypes.Condition_3003,
				view.getElement() != null ? view.getElement() : view,
				ParallelJVisualIDRegistry
						.getType(ConditionNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ParallelJDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5003); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getSpecification_1000Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getInputCondition_3001Text(View view) {
		IParser parser = ParallelJParserProvider.getParser(
				ParallelJElementTypes.InputCondition_3001,
				view.getElement() != null ? view.getElement() : view,
				ParallelJVisualIDRegistry
						.getType(InputConditionNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ParallelJDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getProcedure_3016Text(View view) {
		IParser parser = ParallelJParserProvider.getParser(
				ParallelJElementTypes.Procedure_3016,
				view.getElement() != null ? view.getElement() : view,
				ParallelJVisualIDRegistry
						.getType(PipelineProcedureNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ParallelJDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5026); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getUnknownElementText(View view) {
		return "<UnknownElement Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	private String getUnresolvedDomainElementProxyText(View view) {
		return "<Unresolved domain element Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	public void init(ICommonContentExtensionSite aConfig) {
	}

	/**
	 * @generated
	 */
	public void restoreState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public void saveState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public String getDescription(Object anElement) {
		return null;
	}

	/**
	 * @generated
	 */
	private boolean isOwnView(View view) {
		return SpecificationEditPart.MODEL_ID.equals(ParallelJVisualIDRegistry
				.getModelID(view));
	}

}
