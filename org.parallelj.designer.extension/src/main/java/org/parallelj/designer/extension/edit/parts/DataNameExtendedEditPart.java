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
package org.parallelj.designer.extension.edit.parts;

import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Image;
import org.parallelj.designer.edit.parts.DataEditPart;
import org.parallelj.designer.edit.parts.DataNameEditPart;
import org.parallelj.designer.extension.tools.ImageLoader;
import org.parallelj.designer.extension.tools.IterableDataUtils;
import org.parallelj.model.Data;
import org.parallelj.model.provider.ParallelJEditPlugin;

public class DataNameExtendedEditPart extends DataNameEditPart {

	public DataNameExtendedEditPart(View view) {
		super(view);
	}

	/**
	 * 
	 * @return icon image for DataName label based on nature of Data Type
	 */
	@Override
	protected Image getLabelIcon() {
		DataEditPart dataEditPart = (DataEditPart) this.getParent();
		Data data = (Data) ((View) dataEditPart.getModel()).getElement();
		String type = data.getType();
		if (type != null && type.trim().length() != 0) {
			try {
				if (IterableDataUtils.isIterable(type)) {
					return ImageLoader.getImage(
							ParallelJEditPlugin.INSTANCE.getSymbolicName(),
							"/icons/full/obj16/Data_iterable.png");
				}
			} catch (ClassNotFoundException e) {
				//Activator.logError(e.getMessage());
			}
		}
		return ImageLoader.getImage(
				ParallelJEditPlugin.INSTANCE.getSymbolicName(),
				"/icons/full/obj16/Data.png");
	}
}
