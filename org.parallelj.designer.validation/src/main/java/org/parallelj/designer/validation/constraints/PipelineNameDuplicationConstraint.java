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
package org.parallelj.designer.validation.constraints;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.parallelj.designer.validation.tools.StringTool;
import org.parallelj.model.Element;
import org.parallelj.model.Pipeline;
import org.parallelj.model.Procedure;
import org.parallelj.model.Program;

/**
 * Constraint: Two Procedure/ForEachLoop/WhileLoop/Handler/Pipeline objects
 * should not have same name.
 * 
 */
public class PipelineNameDuplicationConstraint extends AbstractModelConstraint {

	private final static String PROCEDURE = "Procedure";

	private final static String FOREACHLOOP = "ForEachLoop";

	private final static String WHILELOOP = "WhileLoop";

	private final static String HANDLER = "Handler";

	private final static String PIPELINE = "Pipeline";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse
	 * .emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		EObject eObject = ctx.getTarget();
		if (eObject instanceof Procedure) {
			Procedure procedure = (Procedure) eObject;
			if (!StringTool.isEmpty(procedure.getName())) {

				int procedureCount = 0;
				int forEachLoopCount = 0;
				int whileLoopCount = 0;
				int handlerCount = 0;
				int pipelineCount = 0;
				EList<Procedure> procedures = null;
				List<Element> elements = new ArrayList<Element>();
				Program program = null;

				// if target is pipelineProcedure i.e. Procedure from
				// Pipeline, then taking all internal sequence procedures for
				// checking
				if (procedure.eClass().getName().equals(PROCEDURE)
						&& procedure.eContainer() instanceof Pipeline) {
					Pipeline pipeline = (Pipeline) procedure.eContainer();
					procedures = pipeline.getProcedures();
					program = (Program) pipeline.eContainer();
				}

				if (program == null) {
					program = (Program) procedure.eContainer();
				}

				// taking all elements form Program
				EList<Element> programElements = program.getElements();

				// making consolidate list of sequence procedure from Pipeline
				// and procedures from Program
				if (procedures != null) {
					elements.addAll(procedures);
				}

				// if target is pipelineProcedure, then taking only Procedures
				// object from Program to compare
				if (procedure.eContainer() instanceof Pipeline
						&& programElements != null) {
					for (Element element : programElements) {
						if (element.eClass().getName().equals(PROCEDURE)) {
							elements.add(element);
						}
					}
				}
				// else all
				else if (programElements != null) {
					elements.addAll(programElements);
				}

				// checking each element according it's type, for same name
				for (Element element : elements) {
					if (!StringTool.isEmpty(element.getName())
							&& procedure.getName().equals(element.getName())) {
						// for Procedure
						if (element.eClass().getName().equals(PROCEDURE)
								&& procedure.eClass().getName()
										.equals(PROCEDURE)
								&& procedureCount < 2) {
							procedureCount++;
							// for ForEachLoop
						} else if (element.eClass().getName()
								.equals(FOREACHLOOP)
								&& procedure.eClass().getName()
										.equals(FOREACHLOOP)
								&& forEachLoopCount < 2) {
							forEachLoopCount++;
							// for WhileLoop
						} else if (element.eClass().getName().equals(WHILELOOP)
								&& procedure.eClass().getName()
										.equals(WHILELOOP)
								&& whileLoopCount < 2) {
							whileLoopCount++;
							// for Handler
						} else if (element.eClass().getName().equals(HANDLER)
								&& procedure.eClass().getName().equals(HANDLER)
								&& handlerCount < 2) {
							handlerCount++;
							// for Pipeline
						} else if (element.eClass().getName().equals(PIPELINE)
								&& procedure.eClass().getName()
										.equals(PIPELINE) && pipelineCount < 2) {
							pipelineCount++;
						}
					}

					// if target object is Procedure from Program, then checking
					// with all sequence Procedures from Pipeline for same name
					// validation
					if (element.eClass().getName().equals(PIPELINE)
							&& procedure.eClass().getName().equals(PROCEDURE)) {
						Pipeline pipeline = (Pipeline) element;
						EList<Procedure> pipelineProcs = pipeline
								.getProcedures();
						for (Procedure pipelineProc : pipelineProcs) {
							if (!StringTool.isEmpty(pipelineProc.getName())
									&& procedure.getName().equals(
											pipelineProc.getName())
									&& procedureCount < 2) {
								procedureCount++;
							}
						}
					}
				}

				// if any of above having two objects with same name, then show
				// error message for respective one.
				if (procedureCount == 2 || forEachLoopCount == 2
						|| whileLoopCount == 2 || handlerCount == 2
						|| pipelineCount == 2) {
					return ctx.createFailureStatus(
							procedure.eClass().getName(), procedure.getName());
				}
			}
		}
		return ctx.createSuccessStatus();
	}
}
