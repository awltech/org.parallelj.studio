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
import org.parallelj.designer.edit.commands.BlockProcedureCreateCommand;
import org.parallelj.designer.providers.ParallelJElementTypes;

/**
 * @generated
 */
public class BlockBlockCompartmentItemSemanticEditPolicy extends
		ParallelJBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public BlockBlockCompartmentItemSemanticEditPolicy() {
		super(ParallelJElementTypes.Block_3012);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (ParallelJElementTypes.Procedure_3013 == req.getElementType()) {
			return getGEFWrapper(new BlockProcedureCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
