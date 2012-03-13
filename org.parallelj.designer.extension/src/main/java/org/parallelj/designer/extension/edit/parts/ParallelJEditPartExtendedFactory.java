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
package org.parallelj.designer.extension.edit.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.parallelj.designer.edit.parts.BusinessProcedureEditPart;
import org.parallelj.designer.edit.parts.BusinessProcedureNameEditPart;
import org.parallelj.designer.edit.parts.ConditionEditPart;
import org.parallelj.designer.edit.parts.DataEditPart;
import org.parallelj.designer.edit.parts.DataNameEditPart;
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
import org.parallelj.designer.edit.parts.ParallelJEditPartFactory;
import org.parallelj.designer.edit.parts.BlockEditPart;
import org.parallelj.designer.edit.parts.BlockIconEditPart;
import org.parallelj.designer.edit.parts.BlockBlockCompartmentEditPart;
import org.parallelj.designer.edit.parts.BlockProcedureEditPart;
import org.parallelj.designer.edit.parts.BlockProcedureExecutableEditPart;
import org.parallelj.designer.edit.parts.BlockProcedureNameEditPart;
import org.parallelj.designer.edit.parts.PredicateEditPart;
import org.parallelj.designer.edit.parts.PredicateNameEditPart;
import org.parallelj.designer.edit.parts.ProcedureEditPart;
import org.parallelj.designer.edit.parts.ProcedureExecutableEditPart;
import org.parallelj.designer.edit.parts.ProcedureNameEditPart;
import org.parallelj.designer.edit.parts.ProgramEditPart;
import org.parallelj.designer.edit.parts.ProgramNameEditPart;
import org.parallelj.designer.edit.parts.ProgramProgramCompartmentEditPart;
import org.parallelj.designer.edit.parts.WhileLoopEditPart;
import org.parallelj.designer.edit.parts.WhileLoopExecutableEditPart;
import org.parallelj.designer.edit.parts.WhileLoopNameEditPart;
import org.parallelj.designer.edit.parts.WhileLoopPredicateEditPart;
import org.parallelj.designer.part.ParallelJVisualIDRegistry;

public class ParallelJEditPartExtendedFactory extends ParallelJEditPartFactory {

	/**
	 * @return extended edit part based on model.
	 */
	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (ParallelJVisualIDRegistry.getVisualID(view)) {
			case ProgramEditPart.VISUAL_ID:
				return new ProgramExtendedEditPart(view);
			case ProgramNameEditPart.VISUAL_ID:
				return new ProgramNameExtendedEditPart(view);
			case PredicateEditPart.VISUAL_ID:
				return new PredicateExtendedEditPart(view);
			case PredicateNameEditPart.VISUAL_ID:
				return new PredicateNameExtendedEditPart(view);
			case ProcedureEditPart.VISUAL_ID:
				return new ProcedureExtendedEditPart(view);
			case ProcedureNameEditPart.VISUAL_ID:
				return new ProcedureNameExtendedEditPart(view);
			case ProcedureExecutableEditPart.VISUAL_ID:
				return new ProcedureExecutableExtendedEditPart(view);
			case BusinessProcedureEditPart.VISUAL_ID:
				return new BusinessProcedureExtendedEditPart(view);
			case BusinessProcedureNameEditPart.VISUAL_ID:
				return new BusinessProcedureNameExtendedEditPart(view);
			case InputConditionEditPart.VISUAL_ID:
				return new InputConditionExtendedEditPart(view);
			case InputConditionNameEditPart.VISUAL_ID:
				return new InputConditionNameExtendedEditPart(view);
			case OutputConditionEditPart.VISUAL_ID:
				return new OutputConditionExtendedEditPart(view);
			case OutputConditionNameEditPart.VISUAL_ID:
				return new OutputConditionNameExtendedEditPart(view);
			case WhileLoopEditPart.VISUAL_ID:
				return new WhileLoopExtendedEditPart(view);
			case WhileLoopNameEditPart.VISUAL_ID:
				return new WhileLoopNameExtendedEditPart(view);
			case WhileLoopExecutableEditPart.VISUAL_ID:
				return new WhileLoopExecutableExtendedEditPart(view);
			case ForEachLoopEditPart.VISUAL_ID:
				return new ForEachLoopExtendedEditPart(view);
			case ForEachLoopNameEditPart.VISUAL_ID:
				return new ForEachLoopNameExtendedEditPart(view);
			case ForEachLoopExecutableEditPart.VISUAL_ID:
				return new ForEachLoopExecutableExtendedEditPart(view);
			case ForEachLoopIterableEditPart.VISUAL_ID:
				return new ForEachLoopIterableExtendedEditPart(view);
			case ConditionEditPart.VISUAL_ID:
				return new ConditionExtendedEditPart(view);
			case DataEditPart.VISUAL_ID:
				return new DataExtendedEditPart(view);
			case DataNameEditPart.VISUAL_ID:
				return new DataNameExtendedEditPart(view);
			case HandlerEditPart.VISUAL_ID:
				return new HandlerExtendedEditPart(view);
			case HandlerNameEditPart.VISUAL_ID:
				return new HandlerNameExtendedEditPart(view);
			case LinkPredicateInfoEditPart.VISUAL_ID:
				return new LinkPredicateInfoExtendedEditPart(view);
			case BlockEditPart.VISUAL_ID:
				return new BlockExtendedEditPart(view);
			case BlockIconEditPart.VISUAL_ID:
				return new BlockIconExtendedEditPart(view);
			case BlockProcedureEditPart.VISUAL_ID:
				return new BlockProcedureExtendedEditPart(view);
			case BlockProcedureNameEditPart.VISUAL_ID:
				return new BlockProcedureNameExtendedEditPart(view);
			case BlockProcedureExecutableEditPart.VISUAL_ID:
				return new BlockProcedureExecutableExtendedEditPart(view);
			case BlockBlockCompartmentEditPart.VISUAL_ID:
				return new BlockBlockCompartmentExtendedEditPart(view);
			case ProgramProgramCompartmentEditPart.VISUAL_ID:
				return new ProgramProgramCompartmentExtendedEditPart(view);
			case WhileLoopPredicateEditPart.VISUAL_ID:
				return new WhileLoopPredicateExtendedEditPart(view);
			case LinkEditPart.VISUAL_ID:
				return new LinkExtendedEditPart(view);
			}
		}
		return super.createEditPart(context, model);
	}
}
