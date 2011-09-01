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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.parallelj.designer.extension.tools.IterableDataUtils;
import org.parallelj.model.Data;

/**
 * Content provider for structured viewers. To display the list of Iterable Data
 * for the forEachLoop.
 * 
 */
public class IterableDataContentProvider implements IStructuredContentProvider {

	private boolean nullable;

	/**
	 * @param nullable
	 *            define if there is a null value or not
	 */
	public IterableDataContentProvider(boolean nullable) {
		super();
		this.nullable = nullable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface
	 * .viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java
	 * .lang.Object)
	 */
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof EObject) {
			EObject eObject = (EObject) inputElement;
			TreeIterator<EObject> allContents = EcoreUtil.getAllContents(
					eObject, true);

			return asList(allContents).toArray();
		}
		return null;
	}

	/**
	 * Converts tree Iterator object to list of iterable data.
	 * 
	 * @param iter
	 * @return List<EObject> list of Data object which are iterable.
	 */
	private List<EObject> asList(TreeIterator<EObject> iter) {
		ArrayList<EObject> result = new ArrayList<EObject>();
		if (nullable) {
			result.add(null);
		}
		while (iter.hasNext()) {
			EObject eObject = (EObject) iter.next();
			if (eObject instanceof Data) {
				Data data = (Data) eObject;
				IterableDataUtils.populateIterableData(result, data);
			}
		}
		return result;
	}

}
