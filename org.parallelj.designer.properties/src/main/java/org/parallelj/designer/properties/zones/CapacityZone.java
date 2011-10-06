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
package org.parallelj.designer.properties.zones;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.parallelj.designer.properties.Activator;
import org.parallelj.designer.properties.helpers.ParallelJPropertiesMessages;
import org.parallelj.ixea.Zone;
import org.parallelj.ixea.helpers.TextChangeHelper;
import org.parallelj.ixea.tools.Commands;
import org.parallelj.ixea.tools.FormDataBuilder;
import org.parallelj.model.ParallelJPackage;
import org.parallelj.model.Procedure;
import org.parallelj.model.Program;

public class CapacityZone extends Zone {

	private CLabel capacityLabel;

	private Text capacityText;

	private boolean forProgram = false;

	public CapacityZone(Composite parent, boolean isGroup, boolean forProgram) {
		super(parent, isGroup);
		this.forProgram = forProgram;
	}

	@Override
	public void addItemsToZone() {
		capacityLabel = this.getWidgetFactory().createCLabel(getZone(),
				ParallelJPropertiesMessages.element_capacity.message());
		capacityText = this.getWidgetFactory().createText(getZone(), "",
				SWT.NONE);
		capacityText.setEditable(true);
		capacityText.setEnabled(true);
	}

	@Override
	public void addLayoutsToItems() {
		new FormDataBuilder().left().top().mediumLabel().apply(capacityLabel);
		new FormDataBuilder().left(capacityLabel).right().top()
				.apply(capacityText);
	}

	@Override
	public void addListenersToItems() {
		TextChangeHelper capacityTextListener = new TextChangeHelper() {
			@Override
			public void textChanged(Control control) {
				String value = ((Text) control).getText();
				Integer formattedValue;
				try {
					formattedValue = Integer.parseInt(value);
				} catch (NumberFormatException e) {
					Activator.getDefault().logError(
							ParallelJPropertiesMessages.error_cast_int
									.message());
					formattedValue = CapacityZone.this.forProgram ? ((Program) getEObject())
							.getCapacity() : ((Procedure) getEObject())
							.getCapacity();
				}
				if (CapacityZone.this.forProgram)
					Commands.doSetValue(getEditingDomain(), getEObject(),
							ParallelJPackage.eINSTANCE.getProgram_Capacity(),
							formattedValue, getEditPart());
				else
					Commands.doSetValue(getEditingDomain(), getEObject(),
							ParallelJPackage.eINSTANCE.getProcedure_Capacity(),
							formattedValue, getEditPart());
			}
		};
		capacityTextListener.startListeningTo(capacityText);
		capacityTextListener.startListeningForEnter(capacityText);
	}

	@Override
	public void updateItemsValues() {
		Object o;
		if (this.forProgram)
			o = getEObject().eGet(
					ParallelJPackage.eINSTANCE.getProgram_Capacity());
		else
			o = getEObject().eGet(
					ParallelJPackage.eINSTANCE.getProcedure_Capacity());
		capacityText
				.setText((o != null && o instanceof Integer) ? ((Integer) o)
						.toString() : "");
	}

}
