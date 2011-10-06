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
import java.util.Iterator;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.parallelj.designer.properties.helpers.ParallelJPropertiesMessages;
import org.parallelj.ixea.Zone;
import org.parallelj.ixea.tools.Commands;
import org.parallelj.ixea.tools.FormDataBuilder;
import org.parallelj.model.Link;
import org.parallelj.model.ParallelJPackage;
import org.parallelj.model.Procedure;

public class OutputLinksZone extends Zone {

	private CLabel linksLabel;

	private List linksList;

	private ListViewer listViewer;

	private Button upButton;

	private Button downButton;

	private Link selectedLink;

	public OutputLinksZone(Composite parent, boolean isGroup) {
		super(parent, isGroup);
	}

	@Override
	public void addItemsToZone() {
		linksLabel = this.getWidgetFactory().createCLabel(getZone(),
				ParallelJPropertiesMessages.element_outputlinks.message());
		linksList = this.getWidgetFactory().createList(getZone(),
				SWT.V_SCROLL | SWT.BORDER);
		listViewer = new ListViewer(linksList);

		listViewer.setContentProvider(new ListContentProvider());
		listViewer.setLabelProvider(new ListLabelProvider());

		upButton = this.getWidgetFactory().createButton(getZone(),
				ParallelJPropertiesMessages.button_up.message(), SWT.PUSH);
		downButton = this.getWidgetFactory().createButton(getZone(),
				ParallelJPropertiesMessages.button_down.message(), SWT.PUSH);

		upButton.setEnabled(false);
		downButton.setEnabled(false);
	}

	@Override
	public void addLayoutsToItems() {
		new FormDataBuilder().left().top().mediumLabel().apply(linksLabel);
		new FormDataBuilder().right().top().shortButton().apply(upButton);
		new FormDataBuilder().right().top(upButton).shortButton()
				.apply(downButton);
		new FormDataBuilder().left(linksLabel).right(upButton).top().width(100)
				.height(100).apply(linksList);
	}

	@Override
	public void addListenersToItems() {
		listViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if (event.getSelection() instanceof IStructuredSelection) {
					OutputLinksZone.this.selectedLink = null;
					Object link = ((IStructuredSelection) event.getSelection())
							.getFirstElement();
					if (link instanceof Link) {
						OutputLinksZone.this.selectedLink = (Link) link;
						OutputLinksZone.this.upButton.setEnabled(OutputLinksZone.this.selectedLink != null
								&& !OutputLinksZone.this.selectedLink
										.equals(OutputLinksZone.this.listViewer
												.getElementAt(0)));
						OutputLinksZone.this.downButton.setEnabled(OutputLinksZone.this.selectedLink != null
								&& !OutputLinksZone.this.selectedLink.equals(OutputLinksZone.this.listViewer
										.getElementAt(OutputLinksZone.this.linksList
												.getItemCount() - 1)));
					}
				}
			}
		});
		upButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Commands.doMoveValue(getEditingDomain(), getEObject(),
						ParallelJPackage.eINSTANCE.getElement_OutputLinks(),
						OutputLinksZone.this.selectedLink, Commands.MOVE_UP,
						getEditPart());
				OutputLinksZone.this.listViewer.refresh();
				OutputLinksZone.this.upButton
						.setEnabled(OutputLinksZone.this.selectedLink != null
								&& !OutputLinksZone.this.selectedLink
										.equals(OutputLinksZone.this.listViewer
												.getElementAt(0)));
				OutputLinksZone.this.downButton.setEnabled(OutputLinksZone.this.selectedLink != null
						&& !OutputLinksZone.this.selectedLink.equals(OutputLinksZone.this.listViewer
								.getElementAt(OutputLinksZone.this.linksList
										.getItemCount() - 1)));
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		downButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Commands.doMoveValue(getEditingDomain(), getEObject(),
						ParallelJPackage.eINSTANCE.getElement_OutputLinks(),
						OutputLinksZone.this.selectedLink, Commands.MOVE_DOWN,
						getEditPart());
				OutputLinksZone.this.listViewer.refresh();
				OutputLinksZone.this.upButton
						.setEnabled(OutputLinksZone.this.selectedLink != null
								&& !OutputLinksZone.this.selectedLink
										.equals(OutputLinksZone.this.listViewer
												.getElementAt(0)));
				OutputLinksZone.this.downButton.setEnabled(OutputLinksZone.this.selectedLink != null
						&& !OutputLinksZone.this.selectedLink.equals(OutputLinksZone.this.listViewer
								.getElementAt(OutputLinksZone.this.linksList
										.getItemCount() - 1)));
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}

	@Override
	public void updateItemsValues() {
		listViewer.setInput(getEObject());
		if (selectedLink != null)
			listViewer.setSelection(new StructuredSelection(selectedLink));
	}

	class ListLabelProvider implements ILabelProvider {

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
			return element != null ? ParallelJPropertiesMessages.pattern_link
					.message(((Procedure) getEObject()).getName(),
							((Link) element).getDestination().getName()) : null;
		}

	}

	class ListContentProvider implements IStructuredContentProvider {

		@Override
		public void dispose() {
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		@Override
		public Object[] getElements(Object inputElement) {
			Procedure p = (Procedure) inputElement;
			Iterator<Link> iterator = p.getOutputLinks().iterator();
			java.util.List<Link> list = new ArrayList<Link>();
			while (iterator.hasNext()) {
				Link link = iterator.next();
				if (link != null)
					list.add(link);
			}
			return list.toArray();
		}

	}

}
