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
package org.parallelj.designer.properties.providers;

import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart;
import org.eclipse.emf.eef.runtime.api.providers.IPropertiesEditionPartProvider;
import org.parallelj.designer.properties.parts.ParalleljViewsRepository;
import org.parallelj.designer.properties.parts.forms.ConditionPropertiesEditionPartForm;
import org.parallelj.designer.properties.parts.forms.DataPropertiesEditionPartForm;
import org.parallelj.designer.properties.parts.forms.ForEachLoopPropertiesEditionPartForm;
import org.parallelj.designer.properties.parts.forms.HandlerPropertiesEditionPartForm;
import org.parallelj.designer.properties.parts.forms.InputConditionPropertiesEditionPartForm;
import org.parallelj.designer.properties.parts.forms.LinkPropertiesEditionPartForm;
import org.parallelj.designer.properties.parts.forms.OutputConditionPropertiesEditionPartForm;
import org.parallelj.designer.properties.parts.forms.PipelinePropertiesEditionPartForm;
import org.parallelj.designer.properties.parts.forms.PredicatePropertiesEditionPartForm;
import org.parallelj.designer.properties.parts.forms.ProcedurePropertiesEditionPartForm;
import org.parallelj.designer.properties.parts.forms.ProgramPropertiesEditionPartForm;
import org.parallelj.designer.properties.parts.forms.WhileLoopPropertiesEditionPartForm;
import org.parallelj.designer.properties.parts.impl.ConditionPropertiesEditionPartImpl;
import org.parallelj.designer.properties.parts.impl.DataPropertiesEditionPartImpl;
import org.parallelj.designer.properties.parts.impl.ForEachLoopPropertiesEditionPartImpl;
import org.parallelj.designer.properties.parts.impl.HandlerPropertiesEditionPartImpl;
import org.parallelj.designer.properties.parts.impl.InputConditionPropertiesEditionPartImpl;
import org.parallelj.designer.properties.parts.impl.LinkPropertiesEditionPartImpl;
import org.parallelj.designer.properties.parts.impl.OutputConditionPropertiesEditionPartImpl;
import org.parallelj.designer.properties.parts.impl.PipelinePropertiesEditionPartImpl;
import org.parallelj.designer.properties.parts.impl.PredicatePropertiesEditionPartImpl;
import org.parallelj.designer.properties.parts.impl.ProcedurePropertiesEditionPartImpl;
import org.parallelj.designer.properties.parts.impl.ProgramPropertiesEditionPartImpl;
import org.parallelj.designer.properties.parts.impl.WhileLoopPropertiesEditionPartImpl;

/**
 * @author
 * 
 */
public class ParalleljPropertiesEditionPartProvider implements IPropertiesEditionPartProvider {

	/** 
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPartProvider#provides(java.lang.Class)
	 * 
	 */
	public boolean provides(java.lang.Class key) {
		return key == ParalleljViewsRepository.class;
	}

	
	/** 
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPartProvider#getPropertiesEditionPart(java.lang.Class, int, org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent)
	 * 
	 */
	public IPropertiesEditionPart getPropertiesEditionPart(java.lang.Class key, int kind, IPropertiesEditionComponent component) {
		if (key == ParalleljViewsRepository.Program.class) {
			if (kind == ParalleljViewsRepository.SWT_KIND)
				return new ProgramPropertiesEditionPartImpl(component);
			if (kind == ParalleljViewsRepository.FORM_KIND)
				return new ProgramPropertiesEditionPartForm(component);
		}
		if (key == ParalleljViewsRepository.InputCondition.class) {
			if (kind == ParalleljViewsRepository.SWT_KIND)
				return new InputConditionPropertiesEditionPartImpl(component);
			if (kind == ParalleljViewsRepository.FORM_KIND)
				return new InputConditionPropertiesEditionPartForm(component);
		}
		if (key == ParalleljViewsRepository.OutputCondition.class) {
			if (kind == ParalleljViewsRepository.SWT_KIND)
				return new OutputConditionPropertiesEditionPartImpl(component);
			if (kind == ParalleljViewsRepository.FORM_KIND)
				return new OutputConditionPropertiesEditionPartForm(component);
		}
		if (key == ParalleljViewsRepository.Predicate.class) {
			if (kind == ParalleljViewsRepository.SWT_KIND)
				return new PredicatePropertiesEditionPartImpl(component);
			if (kind == ParalleljViewsRepository.FORM_KIND)
				return new PredicatePropertiesEditionPartForm(component);
		}
		if (key == ParalleljViewsRepository.Condition.class) {
			if (kind == ParalleljViewsRepository.SWT_KIND)
				return new ConditionPropertiesEditionPartImpl(component);
			if (kind == ParalleljViewsRepository.FORM_KIND)
				return new ConditionPropertiesEditionPartForm(component);
		}
		if (key == ParalleljViewsRepository.Link.class) {
			if (kind == ParalleljViewsRepository.SWT_KIND)
				return new LinkPropertiesEditionPartImpl(component);
			if (kind == ParalleljViewsRepository.FORM_KIND)
				return new LinkPropertiesEditionPartForm(component);
		}
		if (key == ParalleljViewsRepository.Procedure.class) {
			if (kind == ParalleljViewsRepository.SWT_KIND)
				return new ProcedurePropertiesEditionPartImpl(component);
			if (kind == ParalleljViewsRepository.FORM_KIND)
				return new ProcedurePropertiesEditionPartForm(component);
		}
		if (key == ParalleljViewsRepository.ForEachLoop.class) {
			if (kind == ParalleljViewsRepository.SWT_KIND)
				return new ForEachLoopPropertiesEditionPartImpl(component);
			if (kind == ParalleljViewsRepository.FORM_KIND)
				return new ForEachLoopPropertiesEditionPartForm(component);
		}
		if (key == ParalleljViewsRepository.WhileLoop.class) {
			if (kind == ParalleljViewsRepository.SWT_KIND)
				return new WhileLoopPropertiesEditionPartImpl(component);
			if (kind == ParalleljViewsRepository.FORM_KIND)
				return new WhileLoopPropertiesEditionPartForm(component);
		}
		if (key == ParalleljViewsRepository.Data.class) {
			if (kind == ParalleljViewsRepository.SWT_KIND)
				return new DataPropertiesEditionPartImpl(component);
			if (kind == ParalleljViewsRepository.FORM_KIND)
				return new DataPropertiesEditionPartForm(component);
		}
		if (key == ParalleljViewsRepository.Handler.class) {
			if (kind == ParalleljViewsRepository.SWT_KIND)
				return new HandlerPropertiesEditionPartImpl(component);
			if (kind == ParalleljViewsRepository.FORM_KIND)
				return new HandlerPropertiesEditionPartForm(component);
		}
		if (key == ParalleljViewsRepository.Pipeline.class) {
			if (kind == ParalleljViewsRepository.SWT_KIND)
				return new PipelinePropertiesEditionPartImpl(component);
			if (kind == ParalleljViewsRepository.FORM_KIND)
				return new PipelinePropertiesEditionPartForm(component);
		}
		return null;
	}

}
