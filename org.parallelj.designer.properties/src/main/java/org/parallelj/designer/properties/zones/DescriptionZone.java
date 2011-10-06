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
import org.parallelj.designer.properties.helpers.ParallelJPropertiesMessages;
import org.parallelj.ixea.Zone;
import org.parallelj.ixea.helpers.TextChangeHelper;
import org.parallelj.ixea.tools.Commands;
import org.parallelj.ixea.tools.FormDataBuilder;
import org.parallelj.model.ParallelJPackage;

public class DescriptionZone extends Zone {

	private CLabel descriptionLabel;

	private Text descriptionText;

	public DescriptionZone(Composite parent, boolean isGroup) {
		super(parent, isGroup);
	}

	@Override
	public void addItemsToZone() {
		descriptionLabel = this.getWidgetFactory().createCLabel(getZone(),
				ParallelJPropertiesMessages.element_description.message());
		descriptionText = this.getWidgetFactory().createText(getZone(), "",
				SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		descriptionText.setEditable(true);
		descriptionText.setEnabled(true);
	}

	@Override
	public void addLayoutsToItems() {
		new FormDataBuilder().left().top().mediumLabel()
				.apply(descriptionLabel);
		new FormDataBuilder().left(descriptionLabel).top().right().height(200)
				.apply(descriptionText);
	}

	@Override
	public void addListenersToItems() {
		TextChangeHelper descriptionTextListener = new TextChangeHelper() {
			@Override
			public void textChanged(Control control) {
				String value = ((Text) control).getText();
				String formattedValue = value;
				Commands.doSetValue(getEditingDomain(), getEObject(),
						ParallelJPackage.eINSTANCE
								.getNamedElement_Description(), formattedValue,
						getEditPart());
			}
		};
		descriptionTextListener.startListeningTo(descriptionText);
		descriptionTextListener.startListeningForEnter(descriptionText);
	}

	@Override
	public void updateItemsValues() {
		Object o = getEObject().eGet(
				ParallelJPackage.eINSTANCE.getNamedElement_Description());
		descriptionText.setText(o != null ? (String) o : "");
	}

}
