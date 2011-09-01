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
package org.parallelj.designer.extension.edit.helpers.advices;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.parallelj.model.InputCondition;
import org.parallelj.model.OutputCondition;
import org.parallelj.model.ParallelJFactory;
import org.parallelj.model.ParallelJPackage;
import org.parallelj.model.Program;

/**
 * Program Edit Helper Advice. This helper will add InputCondition and
 * OutputCondition by default when Program node is added.
 */
public class ProgramEditHelperAdvice extends AbstractEditHelperAdvice {

	protected ICommand getAfterConfigureCommand(ConfigureRequest request) {
		EObject elementToConfigure = request.getElementToConfigure();
		if (elementToConfigure instanceof Program) {
			Program program = (Program) elementToConfigure;
			TransactionalEditingDomain transactionalEditingDomain = TransactionUtil
					.getEditingDomain(program);

			// creating InputCondition
			InputCondition inputCondition = ParallelJFactory.eINSTANCE
					.createInputCondition();

			// creating command for adding InputCondition in program's
			// element list as child
			AddCommand addInputConditionCommand = new AddCommand(
					transactionalEditingDomain, program.getElements(),
					inputCondition);
			// creating command for adding new object as Program's feature
			SetCommand setInputConditionCommand = new SetCommand(
					transactionalEditingDomain, program,
					ParallelJPackage.eINSTANCE.getProgram_InputCondition(),
					inputCondition);
			// executing both
			transactionalEditingDomain.getCommandStack().execute(
					addInputConditionCommand);
			transactionalEditingDomain.getCommandStack().execute(
					setInputConditionCommand);

			OutputCondition outputCondition = ParallelJFactory.eINSTANCE
					.createOutputCondition();
			AddCommand addOutputConditionCommand = new AddCommand(
					transactionalEditingDomain, program.getElements(),
					outputCondition);
			SetCommand setOutputConditionCommand = new SetCommand(
					transactionalEditingDomain, program,
					ParallelJPackage.eINSTANCE.getProgram_OutputCondition(),
					outputCondition);
			transactionalEditingDomain.getCommandStack().execute(
					addOutputConditionCommand);
			transactionalEditingDomain.getCommandStack().execute(
					setOutputConditionCommand);

		}
		return super.getAfterConfigureCommand(request);
	}

}
