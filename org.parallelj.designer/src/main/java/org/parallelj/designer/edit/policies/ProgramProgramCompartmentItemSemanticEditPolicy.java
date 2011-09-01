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
package org.parallelj.designer.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.parallelj.designer.edit.commands.ConditionCreateCommand;
import org.parallelj.designer.edit.commands.DataCreateCommand;
import org.parallelj.designer.edit.commands.ForEachLoopCreateCommand;
import org.parallelj.designer.edit.commands.HandlerCreateCommand;
import org.parallelj.designer.edit.commands.InputConditionCreateCommand;
import org.parallelj.designer.edit.commands.OutputConditionCreateCommand;
import org.parallelj.designer.edit.commands.PipelineCreateCommand;
import org.parallelj.designer.edit.commands.PredicateCreateCommand;
import org.parallelj.designer.edit.commands.ProcedureCreateCommand;
import org.parallelj.designer.edit.commands.WhileLoopCreateCommand;
import org.parallelj.designer.providers.ParallelJElementTypes;

/**
 * @generated
 */
public class ProgramProgramCompartmentItemSemanticEditPolicy extends
		ParallelJBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public ProgramProgramCompartmentItemSemanticEditPolicy() {
		super(ParallelJElementTypes.Program_2001);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (ParallelJElementTypes.InputCondition_3001 == req.getElementType()) {
			return getGEFWrapper(new InputConditionCreateCommand(req));
		}
		if (ParallelJElementTypes.OutputCondition_3002 == req.getElementType()) {
			return getGEFWrapper(new OutputConditionCreateCommand(req));
		}
		if (ParallelJElementTypes.Condition_3003 == req.getElementType()) {
			return getGEFWrapper(new ConditionCreateCommand(req));
		}
		if (ParallelJElementTypes.Predicate_3004 == req.getElementType()) {
			return getGEFWrapper(new PredicateCreateCommand(req));
		}
		if (ParallelJElementTypes.Procedure_3005 == req.getElementType()) {
			return getGEFWrapper(new ProcedureCreateCommand(req));
		}
		if (ParallelJElementTypes.ForEachLoop_3006 == req.getElementType()) {
			return getGEFWrapper(new ForEachLoopCreateCommand(req));
		}
		if (ParallelJElementTypes.WhileLoop_3007 == req.getElementType()) {
			return getGEFWrapper(new WhileLoopCreateCommand(req));
		}
		if (ParallelJElementTypes.Handler_3008 == req.getElementType()) {
			return getGEFWrapper(new HandlerCreateCommand(req));
		}
		if (ParallelJElementTypes.Pipeline_3009 == req.getElementType()) {
			return getGEFWrapper(new PipelineCreateCommand(req));
		}
		if (ParallelJElementTypes.Data_3011 == req.getElementType()) {
			return getGEFWrapper(new DataCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
