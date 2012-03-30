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
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Image;
import org.parallelj.designer.edit.parts.BusinessProcedureNameEditPart;
import org.parallelj.designer.extension.Activator;
import org.parallelj.designer.extension.tools.ImageLoader;

public class BusinessProcedureNameExtendedEditPart extends
		BusinessProcedureNameEditPart {

	public BusinessProcedureNameExtendedEditPart(View view) {
		super(view);
	}

	/**
	 * @return icon image for BusinessProcedureName label
	 */
	@Override
	protected Image getLabelIcon() {
		// this will retrieve image path from preference or from businesscontribution
		if (this.getParent() instanceof BusinessProcedureExtendedEditPart) {
			
			String path = null;
			BusinessProcedureExtendedEditPart businessProcedureExtendedEditPart = (BusinessProcedureExtendedEditPart) this
					.getParent();
			IPreferenceStore preferenceStore = Activator.getDefault()
					.getPreferenceStore();

			if (businessProcedureExtendedEditPart
					.getBusinessProcedureContribution() == null) {
				path = preferenceStore
						.getString(this.getLabelText());
			} else {
				path = businessProcedureExtendedEditPart
						.getBusinessProcedureContribution().getImgPath();
				preferenceStore.putValue(this.getLabelText(), path);
			}
			
			String[] split = path.split(":");
			return ImageLoader.getImage(split[0], split[1]);
		}
		return null;
	}
}
