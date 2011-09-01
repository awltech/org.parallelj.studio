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
package org.parallelj.designer.properties.tools;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Image;
import org.parallelj.designer.providers.ParallelJElementTypes;

/**
 * ParallelJ Label Provider
 * 
 */
public class ParallelJLabelProvider extends
		org.eclipse.jface.viewers.LabelProvider {

	/**
	 * Constructor
	 * 
	 */
	public ParallelJLabelProvider() {
		super();
	}

	/**
	 * Retrieves the Image associated with Object passed as parameter.
	 */
	@Override
	public final Image getImage(Object element) {
		if (element == null)
			return null;

		EObject eObject = this.convertToEMF(element);
		if (eObject == null)
			return null;
		EClass eObjectClass = eObject.eClass();
		if (eObjectClass == null)
			return null;
		return this.getImageFromEClass(eObjectClass);
	}

	/**
	 * Retrieves the label associated with Object passed as parameter
	 */
	@Override
	public final String getText(Object element) {
		if (element instanceof IStructuredSelection)
			element = ((IStructuredSelection) element).getFirstElement();

		EObject eObject = this.convertToEMF(element);
		String className = null;
		if (eObject == null) {
			className = "";
		} else {
			className = "<" + eObject.eClass().getName() + ">";
		}
		return className;
	}

	/**
	 * Method used to retrieve the EObject linked with the Object passed as
	 * parameter
	 * 
	 * @param object
	 *            : Object from which EObject has to be revealed
	 * @return EObject
	 */
	private final EObject convertToEMF(Object object) {
		EObject eObject = null;
		Object element = null;
		if (object instanceof ISelection)
			element = ((IStructuredSelection) object).getFirstElement();
		else
			element = object;
		if (element instanceof GraphicalEditPart) {
			AbstractGraphicalEditPart myGEP = (AbstractGraphicalEditPart) element;
			Object model = myGEP.getModel();
			if (model instanceof View)
				eObject = ((View) model).getElement();
			return eObject;
		} else if (element instanceof EObject)
			return (EObject) element;
		else
			return null;
	}

	public Image getImageFromEClass(EClass eClass) {
		return ParallelJElementTypes.getImage(eClass);
	}
}
