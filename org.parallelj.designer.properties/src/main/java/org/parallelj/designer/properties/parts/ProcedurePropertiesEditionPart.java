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

// Start of user code for imports
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.viewers.ViewerFilter;



// End of user code

/**
 * @author
 * 
 */
public interface ProcedurePropertiesEditionPart {

	/**
	 * @return the name
	 * 
	 */
	public String getName();

	/**
	 * Defines a new name
	 * @param newValue the new name to set
	 * 
	 */
	public void setName(String newValue);


	/**
	 * @return the description
	 * 
	 */
	public String getDescription();

	/**
	 * Defines a new description
	 * @param newValue the new description to set
	 * 
	 */
	public void setDescription(String newValue);


	/**
	 * @return the executable
	 * 
	 */
	public String getExecutable();

	/**
	 * Defines a new executable
	 * @param newValue the new executable to set
	 * 
	 */
	public void setExecutable(String newValue);


	/**
	 * @return the join
	 * 
	 */
	public Enumerator getJoin();

	/**
	 * Init the join
	 * @param eenum the enum to manage
	 * @param current the current value
	 */
	public void initJoin(EEnum eenum, Enumerator current);

	/**
	 * Defines a new join
	 * @param newValue the new join to set
	 * 
	 */
	public void setJoin(Enumerator newValue);


	/**
	 * @return the split
	 * 
	 */
	public Enumerator getSplit();

	/**
	 * Init the split
	 * @param eenum the enum to manage
	 * @param current the current value
	 */
	public void initSplit(EEnum eenum, Enumerator current);

	/**
	 * Defines a new split
	 * @param newValue the new split to set
	 * 
	 */
	public void setSplit(Enumerator newValue);


	/**
	 * @return the capacity
	 * 
	 */
	public String getCapacity();

	/**
	 * Defines a new capacity
	 * @param newValue the new capacity to set
	 * 
	 */
	public void setCapacity(String newValue);

	/**
	 * @return the outputLinks to add
	 * 
	 */
	public List getOutputLinksToAdd();

	/**
	 * @return the outputLinks to remove
	 * 
	 */
	public List getOutputLinksToRemove();

	/**
	 * @return the outputLinks to move
	 * 
	 */
	public List getOutputLinksToMove();

	/**
	 * @return the outputLinks to edit
	 * 
	 */
	public Map getOutputLinksToEdit();

	/**
	 * @return the current outputLinks table
	 * 
	 */
	public List getOutputLinksTable();

	/**
	 * Init the outputLinks
	 * @param current the current value
	 * @param containgFeature the feature where to navigate if necessary
	 * @param feature the feature to manage
	 */
	public void initOutputLinks(EObject current, EReference containingFeature, EReference feature);

	/**
	 * Update the outputLinks
	 * @param newValue the outputLinks to update
	 * 
	 */
	public void updateOutputLinks(EObject newValue);

	/**
	 * Adds the given filter to the outputLinks edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToOutputLinks(ViewerFilter filter);

	/**
	 * Adds the given filter to the outputLinks edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToOutputLinks(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the outputLinks table
	 * 
	 */
	public boolean isContainedInOutputLinksTable(EObject element);

	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 * 
	 */
	public String getTitle();

	// Start of user code for additional methods
	
	// End of user code

}
