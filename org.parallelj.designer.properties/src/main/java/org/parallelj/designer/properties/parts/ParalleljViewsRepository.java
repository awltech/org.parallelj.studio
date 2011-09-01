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
package org.parallelj.designer.properties.parts;

/**
 * @author
 * 
 */
public class ParalleljViewsRepository {

	public static final int SWT_KIND = 0;

	public static final int FORM_KIND = 1;


	/**
	 * Program view descriptor
	 * 
	 */
	public static class Program {
		
		public static String name = "parallelj::Program::name";
		
		
		public static String capacity = "parallelj::Program::capacity";
		
		
		public static String description = "parallelj::Program::description";
		
	
	}

	/**
	 * InputCondition view descriptor
	 * 
	 */
	public static class InputCondition {
		
		public static String description = "parallelj::InputCondition::description";
		
	
	}

	/**
	 * OutputCondition view descriptor
	 * 
	 */
	public static class OutputCondition {
		
		public static String description = "parallelj::OutputCondition::description";
		
	
	}

	/**
	 * Predicate view descriptor
	 * 
	 */
	public static class Predicate {
		
		public static String name = "parallelj::Predicate::name";
		
		
		public static String description = "parallelj::Predicate::description";
		
		
	
	}

	/**
	 * Condition view descriptor
	 * 
	 */
	public static class Condition {
		
		public static String name = "parallelj::Condition::name";
		
		
		public static String description = "parallelj::Condition::description";
		
	
	}

	/**
	 * Link view descriptor
	 * 
	 */
	public static class Link {
		
		public static String predicate = "parallelj::Link::predicate";
		
		
		public static String description = "parallelj::Link::description";
		
	
	}

	/**
	 * Procedure view descriptor
	 * 
	 */
	public static class Procedure {
		
		public static String name = "parallelj::Procedure::name";
		
		
		public static String description = "parallelj::Procedure::description";
		
		
		public static String executable = "parallelj::Procedure::executable";
		
		
		public static String capacity = "parallelj::Procedure::capacity";
		
		
		public static String join = "parallelj::Procedure::join";
		
		
		public static String split = "parallelj::Procedure::split";
		

		public static String outputLinks = "parallelj::Procedure::outputLinks";
	
	}

	/**
	 * ForEachLoop view descriptor
	 * 
	 */
	public static class ForEachLoop {
		
		public static String name = "parallelj::ForEachLoop::name";
		
		
		public static String description = "parallelj::ForEachLoop::description";
		
		
		public static String executable = "parallelj::ForEachLoop::executable";
		
		
		public static String capacity = "parallelj::ForEachLoop::capacity";
		
		
		public static String join = "parallelj::ForEachLoop::join";
		
		
		public static String split = "parallelj::ForEachLoop::split";
		

		public static String outputLinks = "parallelj::ForEachLoop::outputLinks";
		

		public static String iterable = "parallelj::ForEachLoop::iterable";
		
		
	}

	/**
	 * WhileLoop view descriptor
	 * 
	 */
	public static class WhileLoop {
		
		public static String name = "parallelj::WhileLoop::name";
		
		
		public static String description = "parallelj::WhileLoop::description";
		
		
		public static String executable = "parallelj::WhileLoop::executable";
		
		
		public static String predicate = "parallelj::WhileLoop::predicate";
		
		
		public static String capacity = "parallelj::WhileLoop::capacity";
		
		
		public static String join = "parallelj::WhileLoop::join";
		
		
		public static String split = "parallelj::WhileLoop::split";
		
		
		public static String outputLinks = "parallelj::WhileLoop::outputLinks";
		
	
	}

	/**
	 * Data view descriptor
	 * 
	 */
	public static class Data {
		
		public static String name = "parallelj::Data::name";
		
		
		public static String type = "parallelj::Data::type";
		
		
		public static String description = "parallelj::Data::description";
		
	
	}

	/**
	 * Handler view descriptor
	 * 
	 */
	public static class Handler {
		
		public static String name = "parallelj::Handler::name";
		
		
		public static String description = "parallelj::Handler::description";
		
		
		public static String executable = "parallelj::Handler::executable";
		
		
		public static String capacity = "parallelj::Handler::capacity";
		
		
		public static String split = "parallelj::Handler::split";
		

		public static String procedures = "parallelj::Handler::procedures";
		
		
		public static String outputLinks = "parallelj::Handler::outputLinks";
	}

	/**
	 * Pipeline view descriptor
	 * 
	 */
	public static class Pipeline {
		
		public static String name = "parallelj::Pipeline::name";
		
		
		public static String description = "parallelj::Pipeline::description";
		
		
		public static String capacity = "parallelj::Pipeline::capacity";
		
		
		public static String join = "parallelj::Pipeline::join";
		
		
		public static String split = "parallelj::Pipeline::split";
		
		
		public static String procedures = "parallelj::Pipeline::procedures";
		

		public static String outputLinks = "parallelj::Pipeline::outputLinks";
	
	}

}
