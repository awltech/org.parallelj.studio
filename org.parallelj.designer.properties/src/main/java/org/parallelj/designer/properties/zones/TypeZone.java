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

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.ui.IJavaElementSearchConstants;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.SelectionDialog;
import org.parallelj.designer.properties.helpers.ParallelJPropertiesMessages;
import org.parallelj.ixea.Zone;
import org.parallelj.ixea.helpers.TextChangeHelper;
import org.parallelj.ixea.tools.Commands;
import org.parallelj.ixea.tools.FormDataBuilder;
import org.parallelj.model.ParallelJPackage;

public class TypeZone extends Zone {

	private CLabel typeLabel;

	private Text typeText;

	private Button typeSelector;

	public TypeZone(Composite parent, boolean isGroup) {
		super(parent, isGroup);
	}

	@Override
	public void addItemsToZone() {
		typeLabel = this.getWidgetFactory().createCLabel(getZone(),
				ParallelJPropertiesMessages.element_type.message());
		typeText = this.getWidgetFactory().createText(getZone(), "",
				SWT.READ_ONLY);
		typeText.setEditable(false);
		typeSelector = this.getWidgetFactory().createButton(getZone(),
				ParallelJPropertiesMessages.button_select.message(), SWT.PUSH);
	}

	@Override
	public void addLayoutsToItems() {
		new FormDataBuilder().left().top().mediumLabel().apply(typeLabel);
		new FormDataBuilder().top().right().shortButton().apply(typeSelector);
		new FormDataBuilder().left(typeLabel).top().right(typeSelector)
				.apply(typeText);
	}

	@Override
	public void addListenersToItems() {
		TextChangeHelper typeTextListener = new TextChangeHelper() {
			@Override
			public void textChanged(Control control) {
				String value = ((Text) control).getText();
				String formattedValue = value;
				Commands.doSetValue(getEditingDomain(), getEObject(),
						ParallelJPackage.eINSTANCE.getData_Type(),
						formattedValue, getEditPart());
			}
		};
		typeTextListener.startListeningTo(typeText);
		typeTextListener.startListeningForEnter(typeText);
		typeSelector.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				TypeZone.this.askForType();
			}
		});
	}

	@Override
	public void updateItemsValues() {
		Object o = getEObject().eGet(ParallelJPackage.eINSTANCE.getData_Type());
		typeText.setText(o != null ? (String) o : "");
	}

	protected void askForType() {
		IProject p = WorkspaceSynchronizer.getFile(getEObject().eResource())
				.getProject();
		IJavaProject jp = JavaCore.create(p);
		try {
			SelectionDialog dialog = JavaUI
					.createTypeDialog(
							getZone().getShell(),
							new ProgressMonitorDialog(getZone().getShell()),
							SearchEngine
									.createJavaSearchScope(new IJavaElement[] { jp }),
							IJavaElementSearchConstants.CONSIDER_CLASSES_AND_INTERFACES,
							false);
			dialog.open();
			if (dialog.getResult() != null) {
				IType result = (IType) (dialog.getResult()[0]);
				this.typeText.setText(result.getFullyQualifiedName());
				Commands.doSetValue(getEditingDomain(), getEObject(),
						ParallelJPackage.eINSTANCE.getData_Type(),
						result.getFullyQualifiedName(), getEditPart());
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
	}

}
