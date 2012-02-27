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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.parallelj.designer.extension.tools.IterableDataUtils;
import org.parallelj.designer.properties.helpers.ParallelJPropertiesMessages;
import org.parallelj.ixea.Zone;
import org.parallelj.ixea.tools.CComboViewer;
import org.parallelj.ixea.tools.Commands;
import org.parallelj.ixea.tools.FormDataBuilder;
import org.parallelj.model.Data;
import org.parallelj.model.ForEachLoop;
import org.parallelj.model.ParallelJPackage;
import org.parallelj.model.Program;

public class IterableZone extends Zone {

	private CLabel iterableLabel;

	private CCombo iterableCombo;

	private CComboViewer iterableComboViewer;

	public IterableZone(Composite parent, boolean isGroup) {
		super(parent, isGroup);
	}

	@Override
	public void addItemsToZone() {
		iterableLabel = this.getWidgetFactory().createCLabel(getZone(),
				ParallelJPropertiesMessages.element_iterable.message());
		iterableCombo = this.getWidgetFactory().createCCombo(getZone());
		iterableComboViewer = new CComboViewer(iterableCombo);
		iterableComboViewer.setLabelProvider(new LabelProvider());
		iterableComboViewer.setContentProvider(new ContentProvider());
	}

	@Override
	public void addLayoutsToItems() {
		new FormDataBuilder().top().left().mediumLabel().apply(iterableLabel);
		new FormDataBuilder().top().left(iterableLabel).right()
				.apply(iterableCombo);
	}

	@Override
	public void addListenersToItems() {
		ISelectionChangedListener listener = new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if (event.getSelection() instanceof IStructuredSelection
						&& !IterableZone.this.listenerLock) {
					Object o = ((IStructuredSelection) event.getSelection())
							.getFirstElement();
					ForEachLoop fel = (ForEachLoop) getEObject();
					if (o instanceof Data) {
						Data dataOfTask = (Data) o;
						Commands.doSetValue(getEditingDomain(), fel,
								ParallelJPackage.eINSTANCE
										.getForEachLoop_Iterable(), dataOfTask,
								getEditPart());
					}
				}
			}
		};
		this.iterableComboViewer.addSelectionChangedListener(listener);
	}

	protected boolean listenerLock = false;

	@Override
	public void updateItemsValues() {
		iterableComboViewer.refresh();
		ForEachLoop fel = (ForEachLoop) getEObject();
		this.listenerLock = true;
		iterableComboViewer.setInput(fel);
		if (fel.getIterable() != null)
			iterableComboViewer.setSelection(new StructuredSelection(fel
					.getIterable()));
		this.listenerLock = false;
	}

	private class LabelProvider implements ILabelProvider {

		@Override
		public void addListener(ILabelProviderListener listener) {
		}

		@Override
		public void dispose() {
		}

		@Override
		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		@Override
		public void removeListener(ILabelProviderListener listener) {
		}

		@Override
		public Image getImage(Object element) {
			return null;
		}

		@Override
		public String getText(Object element) {
			if (element instanceof Data)
				return ((Data) element).getName();
			else
				return (String) element;
		}

	}

	private class ContentProvider implements IStructuredContentProvider {

		@Override
		public void dispose() {
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		@Override
		public Object[] getElements(Object inputElement) {
			List<Data> iterableData = new ArrayList<Data>();
			ForEachLoop currentForEach = (ForEachLoop) inputElement;
			if (currentForEach.eContainer() instanceof Program) {
				Program parentProgram = (Program) currentForEach.eContainer();
				if (parentProgram.getData() != null) {
					for (Data d : parentProgram.getData()) {
						try {
							if (d != null
									&& d.getType() != null
									&& IterableDataUtils
											.isIterable(d.getType())) {
								iterableData.add(d);
							}
						} catch (ClassNotFoundException e) {
						}
					}
				}
			}
			return iterableData.toArray();
		}

	}

}
