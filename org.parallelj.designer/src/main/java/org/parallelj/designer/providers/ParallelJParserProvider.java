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

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;
import org.parallelj.designer.edit.parts.BlockNameEditPart;
import org.parallelj.designer.edit.parts.BlockProcedureExecutableEditPart;
import org.parallelj.designer.edit.parts.BlockProcedureNameEditPart;
import org.parallelj.designer.edit.parts.BusinessProcedureNameEditPart;
import org.parallelj.designer.edit.parts.ConditionNameEditPart;
import org.parallelj.designer.edit.parts.DataNameEditPart;
import org.parallelj.designer.edit.parts.DataTypeEditPart;
import org.parallelj.designer.edit.parts.ForEachLoopExecutableEditPart;
import org.parallelj.designer.edit.parts.ForEachLoopNameEditPart;
import org.parallelj.designer.edit.parts.HandlerNameEditPart;
import org.parallelj.designer.edit.parts.PredicateNameEditPart;
import org.parallelj.designer.edit.parts.ProcedureExecutableEditPart;
import org.parallelj.designer.edit.parts.ProcedureNameEditPart;
import org.parallelj.designer.edit.parts.ProgramNameEditPart;
import org.parallelj.designer.edit.parts.WhileLoopExecutableEditPart;
import org.parallelj.designer.edit.parts.WhileLoopNameEditPart;
import org.parallelj.designer.parsers.MessageFormatParser;
import org.parallelj.designer.part.ParallelJVisualIDRegistry;
import org.parallelj.model.ParallelJPackage;

/**
 * @generated
 */
public class ParallelJParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser programName_5020Parser;

	/**
	 * @generated
	 */
	private IParser getProgramName_5020Parser() {
		if (programName_5020Parser == null) {
			EAttribute[] features = new EAttribute[] { ParallelJPackage.eINSTANCE
					.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			programName_5020Parser = parser;
		}
		return programName_5020Parser;
	}

	/**
	 * @generated
	 */
	private IParser conditionName_5003Parser;

	/**
	 * @generated
	 */
	private IParser getConditionName_5003Parser() {
		if (conditionName_5003Parser == null) {
			EAttribute[] features = new EAttribute[] { ParallelJPackage.eINSTANCE
					.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			conditionName_5003Parser = parser;
		}
		return conditionName_5003Parser;
	}

	/**
	 * @generated
	 */
	private IParser predicateName_5004Parser;

	/**
	 * @generated
	 */
	private IParser getPredicateName_5004Parser() {
		if (predicateName_5004Parser == null) {
			EAttribute[] features = new EAttribute[] { ParallelJPackage.eINSTANCE
					.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			predicateName_5004Parser = parser;
		}
		return predicateName_5004Parser;
	}

	/**
	 * @generated
	 */
	private IParser procedureName_5005Parser;

	/**
	 * @generated
	 */
	private IParser getProcedureName_5005Parser() {
		if (procedureName_5005Parser == null) {
			EAttribute[] features = new EAttribute[] { ParallelJPackage.eINSTANCE
					.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			procedureName_5005Parser = parser;
		}
		return procedureName_5005Parser;
	}

	/**
	 * @generated
	 */
	private IParser procedureExecutable_5006Parser;

	/**
	 * @generated
	 */
	private IParser getProcedureExecutable_5006Parser() {
		if (procedureExecutable_5006Parser == null) {
			EAttribute[] features = new EAttribute[] { ParallelJPackage.eINSTANCE
					.getProcedure_Executable() };
			MessageFormatParser parser = new MessageFormatParser(features);
			procedureExecutable_5006Parser = parser;
		}
		return procedureExecutable_5006Parser;
	}

	/**
	 * @generated
	 */
	private IParser forEachLoopName_5007Parser;

	/**
	 * @generated
	 */
	private IParser getForEachLoopName_5007Parser() {
		if (forEachLoopName_5007Parser == null) {
			EAttribute[] features = new EAttribute[] { ParallelJPackage.eINSTANCE
					.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			forEachLoopName_5007Parser = parser;
		}
		return forEachLoopName_5007Parser;
	}

	/**
	 * @generated
	 */
	private IParser forEachLoopExecutable_5008Parser;

	/**
	 * @generated
	 */
	private IParser getForEachLoopExecutable_5008Parser() {
		if (forEachLoopExecutable_5008Parser == null) {
			EAttribute[] features = new EAttribute[] { ParallelJPackage.eINSTANCE
					.getProcedure_Executable() };
			MessageFormatParser parser = new MessageFormatParser(features);
			forEachLoopExecutable_5008Parser = parser;
		}
		return forEachLoopExecutable_5008Parser;
	}

	/**
	 * @generated
	 */
	private IParser whileLoopName_5010Parser;

	/**
	 * @generated
	 */
	private IParser getWhileLoopName_5010Parser() {
		if (whileLoopName_5010Parser == null) {
			EAttribute[] features = new EAttribute[] { ParallelJPackage.eINSTANCE
					.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			whileLoopName_5010Parser = parser;
		}
		return whileLoopName_5010Parser;
	}

	/**
	 * @generated
	 */
	private IParser whileLoopExecutable_5011Parser;

	/**
	 * @generated
	 */
	private IParser getWhileLoopExecutable_5011Parser() {
		if (whileLoopExecutable_5011Parser == null) {
			EAttribute[] features = new EAttribute[] { ParallelJPackage.eINSTANCE
					.getProcedure_Executable() };
			MessageFormatParser parser = new MessageFormatParser(features);
			whileLoopExecutable_5011Parser = parser;
		}
		return whileLoopExecutable_5011Parser;
	}

	/**
	 * @generated
	 */
	private IParser handlerName_5013Parser;

	/**
	 * @generated
	 */
	private IParser getHandlerName_5013Parser() {
		if (handlerName_5013Parser == null) {
			EAttribute[] features = new EAttribute[] { ParallelJPackage.eINSTANCE
					.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			handlerName_5013Parser = parser;
		}
		return handlerName_5013Parser;
	}

	/**
	 * @generated
	 */
	private IParser blockName_5023Parser;

	/**
	 * @generated
	 */
	private IParser getBlockName_5023Parser() {
		if (blockName_5023Parser == null) {
			EAttribute[] features = new EAttribute[] { ParallelJPackage.eINSTANCE
					.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			blockName_5023Parser = parser;
		}
		return blockName_5023Parser;
	}

	/**
	 * @generated
	 */
	private IParser procedureName_5021Parser;

	/**
	 * @generated
	 */
	private IParser getProcedureName_5021Parser() {
		if (procedureName_5021Parser == null) {
			EAttribute[] features = new EAttribute[] { ParallelJPackage.eINSTANCE
					.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			procedureName_5021Parser = parser;
		}
		return procedureName_5021Parser;
	}

	/**
	 * @generated
	 */
	private IParser procedureExecutable_5022Parser;

	/**
	 * @generated
	 */
	private IParser getProcedureExecutable_5022Parser() {
		if (procedureExecutable_5022Parser == null) {
			EAttribute[] features = new EAttribute[] { ParallelJPackage.eINSTANCE
					.getProcedure_Executable() };
			MessageFormatParser parser = new MessageFormatParser(features);
			procedureExecutable_5022Parser = parser;
		}
		return procedureExecutable_5022Parser;
	}

	/**
	 * @generated
	 */
	private IParser dataName_5018Parser;

	/**
	 * @generated
	 */
	private IParser getDataName_5018Parser() {
		if (dataName_5018Parser == null) {
			EAttribute[] features = new EAttribute[] { ParallelJPackage.eINSTANCE
					.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			dataName_5018Parser = parser;
		}
		return dataName_5018Parser;
	}

	/**
	 * @generated
	 */
	private IParser dataType_5019Parser;

	/**
	 * @generated
	 */
	private IParser getDataType_5019Parser() {
		if (dataType_5019Parser == null) {
			EAttribute[] features = new EAttribute[] { ParallelJPackage.eINSTANCE
					.getData_Type() };
			MessageFormatParser parser = new MessageFormatParser(features);
			dataType_5019Parser = parser;
		}
		return dataType_5019Parser;
	}

	/**
	 * @generated
	 */
	private IParser businessProcedureName_5025Parser;

	/**
	 * @generated
	 */
	private IParser getBusinessProcedureName_5025Parser() {
		if (businessProcedureName_5025Parser == null) {
			EAttribute[] features = new EAttribute[] { ParallelJPackage.eINSTANCE
					.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			businessProcedureName_5025Parser = parser;
		}
		return businessProcedureName_5025Parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case ProgramNameEditPart.VISUAL_ID:
			return getProgramName_5020Parser();
		case ConditionNameEditPart.VISUAL_ID:
			return getConditionName_5003Parser();
		case PredicateNameEditPart.VISUAL_ID:
			return getPredicateName_5004Parser();
		case ProcedureNameEditPart.VISUAL_ID:
			return getProcedureName_5005Parser();
		case ProcedureExecutableEditPart.VISUAL_ID:
			return getProcedureExecutable_5006Parser();
		case ForEachLoopNameEditPart.VISUAL_ID:
			return getForEachLoopName_5007Parser();
		case ForEachLoopExecutableEditPart.VISUAL_ID:
			return getForEachLoopExecutable_5008Parser();
		case WhileLoopNameEditPart.VISUAL_ID:
			return getWhileLoopName_5010Parser();
		case WhileLoopExecutableEditPart.VISUAL_ID:
			return getWhileLoopExecutable_5011Parser();
		case HandlerNameEditPart.VISUAL_ID:
			return getHandlerName_5013Parser();
		case BlockNameEditPart.VISUAL_ID:
			return getBlockName_5023Parser();
		case BlockProcedureNameEditPart.VISUAL_ID:
			return getProcedureName_5021Parser();
		case BlockProcedureExecutableEditPart.VISUAL_ID:
			return getProcedureExecutable_5022Parser();
		case DataNameEditPart.VISUAL_ID:
			return getDataName_5018Parser();
		case DataTypeEditPart.VISUAL_ID:
			return getDataType_5019Parser();
		case BusinessProcedureNameEditPart.VISUAL_ID:
			return getBusinessProcedureName_5025Parser();
		}
		return null;
	}

	/**
	 * Utility method that consults ParserService
	 * @generated
	 */
	public static IParser getParser(IElementType type, EObject object,
			String parserHint) {
		return ParserService.getInstance().getParser(
				new HintAdapter(type, object, parserHint));
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(ParallelJVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(ParallelJVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (ParallelJElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	 * @generated
	 */
	private static class HintAdapter extends ParserHintAdapter {

		/**
		 * @generated
		 */
		private final IElementType elementType;

		/**
		 * @generated
		 */
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		 * @generated
		 */
		public Object getAdapter(Class adapter) {
			if (IElementType.class.equals(adapter)) {
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}

}
