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
package org.parallelj.providers;

import org.eclipse.emf.eef.runtime.impl.providers.ComposedPropertiesEditionProvider;

/**
 * @author
 * 
 */
public class ParalleljPackagePropertiesEditionProvider extends ComposedPropertiesEditionProvider {

	/**
	 * Default Constructor
	 * 
	 */
	public ParalleljPackagePropertiesEditionProvider() {
		super();
		append(createProgramPropertiesEditionProvider());
		append(createInputConditionPropertiesEditionProvider());
		append(createOutputConditionPropertiesEditionProvider());
		append(createPredicatePropertiesEditionProvider());
		append(createConditionPropertiesEditionProvider());
		append(createLinkPropertiesEditionProvider());
		append(createProcedurePropertiesEditionProvider());
		append(createForEachLoopPropertiesEditionProvider());
		append(createWhileLoopPropertiesEditionProvider());
		append(createDataPropertiesEditionProvider());
		append(createHandlerPropertiesEditionProvider());
		append(createPipelinePropertiesEditionProvider());
	}

	/**
	 * This keeps track of the one PropertiesEditionProvider used for all
	 * Program instances.
	 * 
	 */
	protected ProgramPropertiesEditionProvider programPropertiesEditionProvider;

	/**
	 * This creates an PropertiesEditionProvider for a Program
	 * 
	 */
	public ProgramPropertiesEditionProvider createProgramPropertiesEditionProvider() {
		if (programPropertiesEditionProvider == null)
			programPropertiesEditionProvider = new ProgramPropertiesEditionProvider();
		return programPropertiesEditionProvider;
	}

	/**
	 * This keeps track of the one PropertiesEditionProvider used for all
	 * InputCondition instances.
	 * 
	 */
	protected InputConditionPropertiesEditionProvider inputConditionPropertiesEditionProvider;

	/**
	 * This creates an PropertiesEditionProvider for a InputCondition
	 * 
	 */
	public InputConditionPropertiesEditionProvider createInputConditionPropertiesEditionProvider() {
		if (inputConditionPropertiesEditionProvider == null)
			inputConditionPropertiesEditionProvider = new InputConditionPropertiesEditionProvider();
		return inputConditionPropertiesEditionProvider;
	}

	/**
	 * This keeps track of the one PropertiesEditionProvider used for all
	 * OutputCondition instances.
	 * 
	 */
	protected OutputConditionPropertiesEditionProvider outputConditionPropertiesEditionProvider;

	/**
	 * This creates an PropertiesEditionProvider for a OutputCondition
	 * 
	 */
	public OutputConditionPropertiesEditionProvider createOutputConditionPropertiesEditionProvider() {
		if (outputConditionPropertiesEditionProvider == null)
			outputConditionPropertiesEditionProvider = new OutputConditionPropertiesEditionProvider();
		return outputConditionPropertiesEditionProvider;
	}

	/**
	 * This keeps track of the one PropertiesEditionProvider used for all
	 * Predicate instances.
	 * 
	 */
	protected PredicatePropertiesEditionProvider predicatePropertiesEditionProvider;

	/**
	 * This creates an PropertiesEditionProvider for a Predicate
	 * 
	 */
	public PredicatePropertiesEditionProvider createPredicatePropertiesEditionProvider() {
		if (predicatePropertiesEditionProvider == null)
			predicatePropertiesEditionProvider = new PredicatePropertiesEditionProvider();
		return predicatePropertiesEditionProvider;
	}

	/**
	 * This keeps track of the one PropertiesEditionProvider used for all
	 * Condition instances.
	 * 
	 */
	protected ConditionPropertiesEditionProvider conditionPropertiesEditionProvider;

	/**
	 * This creates an PropertiesEditionProvider for a Condition
	 * 
	 */
	public ConditionPropertiesEditionProvider createConditionPropertiesEditionProvider() {
		if (conditionPropertiesEditionProvider == null)
			conditionPropertiesEditionProvider = new ConditionPropertiesEditionProvider();
		return conditionPropertiesEditionProvider;
	}

	/**
	 * This keeps track of the one PropertiesEditionProvider used for all
	 * Link instances.
	 * 
	 */
	protected LinkPropertiesEditionProvider linkPropertiesEditionProvider;

	/**
	 * This creates an PropertiesEditionProvider for a Link
	 * 
	 */
	public LinkPropertiesEditionProvider createLinkPropertiesEditionProvider() {
		if (linkPropertiesEditionProvider == null)
			linkPropertiesEditionProvider = new LinkPropertiesEditionProvider();
		return linkPropertiesEditionProvider;
	}

	/**
	 * This keeps track of the one PropertiesEditionProvider used for all
	 * Procedure instances.
	 * 
	 */
	protected ProcedurePropertiesEditionProvider procedurePropertiesEditionProvider;

	/**
	 * This creates an PropertiesEditionProvider for a Procedure
	 * 
	 */
	public ProcedurePropertiesEditionProvider createProcedurePropertiesEditionProvider() {
		if (procedurePropertiesEditionProvider == null)
			procedurePropertiesEditionProvider = new ProcedurePropertiesEditionProvider();
		return procedurePropertiesEditionProvider;
	}

	/**
	 * This keeps track of the one PropertiesEditionProvider used for all
	 * ForEachLoop instances.
	 * 
	 */
	protected ForEachLoopPropertiesEditionProvider forEachLoopPropertiesEditionProvider;

	/**
	 * This creates an PropertiesEditionProvider for a ForEachLoop
	 * 
	 */
	public ForEachLoopPropertiesEditionProvider createForEachLoopPropertiesEditionProvider() {
		if (forEachLoopPropertiesEditionProvider == null)
			forEachLoopPropertiesEditionProvider = new ForEachLoopPropertiesEditionProvider();
		return forEachLoopPropertiesEditionProvider;
	}

	/**
	 * This keeps track of the one PropertiesEditionProvider used for all
	 * WhileLoop instances.
	 * 
	 */
	protected WhileLoopPropertiesEditionProvider whileLoopPropertiesEditionProvider;

	/**
	 * This creates an PropertiesEditionProvider for a WhileLoop
	 * 
	 */
	public WhileLoopPropertiesEditionProvider createWhileLoopPropertiesEditionProvider() {
		if (whileLoopPropertiesEditionProvider == null)
			whileLoopPropertiesEditionProvider = new WhileLoopPropertiesEditionProvider();
		return whileLoopPropertiesEditionProvider;
	}

	/**
	 * This keeps track of the one PropertiesEditionProvider used for all
	 * Data instances.
	 * 
	 */
	protected DataPropertiesEditionProvider dataPropertiesEditionProvider;

	/**
	 * This creates an PropertiesEditionProvider for a Data
	 * 
	 */
	public DataPropertiesEditionProvider createDataPropertiesEditionProvider() {
		if (dataPropertiesEditionProvider == null)
			dataPropertiesEditionProvider = new DataPropertiesEditionProvider();
		return dataPropertiesEditionProvider;
	}

	/**
	 * This keeps track of the one PropertiesEditionProvider used for all
	 * Handler instances.
	 * 
	 */
	protected HandlerPropertiesEditionProvider handlerPropertiesEditionProvider;

	/**
	 * This creates an PropertiesEditionProvider for a Handler
	 * 
	 */
	public HandlerPropertiesEditionProvider createHandlerPropertiesEditionProvider() {
		if (handlerPropertiesEditionProvider == null)
			handlerPropertiesEditionProvider = new HandlerPropertiesEditionProvider();
		return handlerPropertiesEditionProvider;
	}

	/**
	 * This keeps track of the one PropertiesEditionProvider used for all
	 * Handler instances.
	 * 
	 */
	protected PipelinePropertiesEditionProvider pipelinePropertiesEditionProvider;

	/**
	 * This creates an PropertiesEditionProvider for a Handler
	 * 
	 */
	public PipelinePropertiesEditionProvider createPipelinePropertiesEditionProvider() {
		if (pipelinePropertiesEditionProvider == null)
			pipelinePropertiesEditionProvider = new PipelinePropertiesEditionProvider();
		return pipelinePropertiesEditionProvider;
	}

}
