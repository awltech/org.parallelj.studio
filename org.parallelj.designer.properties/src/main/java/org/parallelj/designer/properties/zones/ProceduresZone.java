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

import org.eclipse.core.resources.IProject;
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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipselabs.resourceselector.core.processor.ResourceProcessor;
import org.eclipselabs.resourceselector.core.processor.ResourceProcessorFactory;
import org.eclipselabs.resourceselector.core.resources.ResourceInfo;
import org.eclipselabs.resourceselector.core.selector.ResourceSelector;
import org.parallelj.designer.properties.helpers.ParallelJPropertiesMessages;
import org.parallelj.designer.properties.helpers.Tools;
import org.parallelj.ixea.Zone;
import org.parallelj.ixea.tools.Commands;
import org.parallelj.ixea.tools.FormDataBuilder;
import org.parallelj.model.Element;
import org.parallelj.model.Handler;
import org.parallelj.model.ParallelJPackage;
import org.parallelj.model.Block;
import org.parallelj.model.Procedure;
import org.parallelj.model.Program;

public class ProceduresZone extends Zone {

	private CLabel proceduresLabel;

	private List proceduresList;

	private ListViewer listViewer;

	private Button upButton;

	private Button downButton;

	private Button addButton;

	private Button deleteButton;

	private Procedure selectedProcedure;

	private boolean isBlock;

	public ProceduresZone(Composite parent, boolean isGroup, boolean isBlock) {
		super(parent, isGroup);
		this.isBlock = isBlock;
	}

	@Override
	public void addItemsToZone() {
		proceduresLabel = this.getWidgetFactory().createCLabel(getZone(),
				ParallelJPropertiesMessages.element_procedures.message());
		proceduresList = this.getWidgetFactory().createList(getZone(),
				SWT.V_SCROLL | SWT.BORDER);
		listViewer = new ListViewer(proceduresList);

		listViewer.setContentProvider(new ListContentProvider());
		listViewer.setLabelProvider(new ListLabelProvider());

		if (this.isBlock) {
			upButton = this.getWidgetFactory().createButton(getZone(),
					ParallelJPropertiesMessages.button_up.message(), SWT.PUSH);
			downButton = this.getWidgetFactory()
					.createButton(getZone(),
							ParallelJPropertiesMessages.button_down.message(),
							SWT.PUSH);
			upButton.setEnabled(false);
			downButton.setEnabled(false);
		} else {
			addButton = this.getWidgetFactory().createButton(getZone(),
					ParallelJPropertiesMessages.button_add.message(), SWT.PUSH);
			deleteButton = this.getWidgetFactory().createButton(getZone(),
					ParallelJPropertiesMessages.button_remove.message(),
					SWT.PUSH);
		}
	}

	@Override
	public void addLayoutsToItems() {
		new FormDataBuilder().left().top().mediumLabel().apply(proceduresLabel);
		if (!this.isBlock) {
			new FormDataBuilder().right().top().shortButton().apply(addButton);
			new FormDataBuilder().right().top(addButton).shortButton()
					.apply(deleteButton);
			new FormDataBuilder().left(proceduresLabel).right(addButton).top()
					.width(100).height(100).apply(proceduresList);
		} else {
			new FormDataBuilder().right().top().shortButton().apply(upButton);
			new FormDataBuilder().right().top(upButton).shortButton()
					.apply(downButton);
			new FormDataBuilder().left(proceduresLabel).right(upButton).top()
					.width(100).height(100).apply(proceduresList);
		}
	}

	@Override
	public void addListenersToItems() {
		listViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if (event.getSelection() instanceof IStructuredSelection) {
					ProceduresZone.this.selectedProcedure = null;
					Object procedure = ((IStructuredSelection) event
							.getSelection()).getFirstElement();
					if (procedure instanceof Procedure) {
						ProceduresZone.this.selectedProcedure = (Procedure) procedure;
						if (ProceduresZone.this.isBlock) {
							ProceduresZone.this.upButton.setEnabled(ProceduresZone.this.selectedProcedure != null
									&& !ProceduresZone.this.selectedProcedure
											.equals(ProceduresZone.this.listViewer
													.getElementAt(0)));
							ProceduresZone.this.downButton.setEnabled(ProceduresZone.this.selectedProcedure != null
									&& !ProceduresZone.this.selectedProcedure
											.equals(ProceduresZone.this.listViewer
													.getElementAt(ProceduresZone.this.proceduresList
															.getItemCount() - 1)));
						}
					}
				}
			}
		});
		if (this.isBlock) {
			upButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					Commands.doMoveValue(getEditingDomain(), getEObject(),
							ParallelJPackage.eINSTANCE.getBlock_Procedures(),
							ProceduresZone.this.selectedProcedure,
							Commands.MOVE_UP, getEditPart());
					ProceduresZone.this.listViewer.refresh();
					ProceduresZone.this.upButton.setEnabled(ProceduresZone.this.selectedProcedure != null
							&& !ProceduresZone.this.selectedProcedure
									.equals(ProceduresZone.this.listViewer
											.getElementAt(0)));
					ProceduresZone.this.downButton.setEnabled(ProceduresZone.this.selectedProcedure != null
							&& !ProceduresZone.this.selectedProcedure.equals(ProceduresZone.this.listViewer
									.getElementAt(ProceduresZone.this.proceduresList
											.getItemCount() - 1)));
				}
			});
			downButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					Commands.doMoveValue(getEditingDomain(), getEObject(),
							ParallelJPackage.eINSTANCE.getBlock_Procedures(),
							ProceduresZone.this.selectedProcedure,
							Commands.MOVE_DOWN, getEditPart());
					ProceduresZone.this.listViewer.refresh();
					ProceduresZone.this.upButton.setEnabled(ProceduresZone.this.selectedProcedure != null
							&& !ProceduresZone.this.selectedProcedure
									.equals(ProceduresZone.this.listViewer
											.getElementAt(0)));
					ProceduresZone.this.downButton.setEnabled(ProceduresZone.this.selectedProcedure != null
							&& !ProceduresZone.this.selectedProcedure.equals(ProceduresZone.this.listViewer
									.getElementAt(ProceduresZone.this.proceduresList
											.getItemCount() - 1)));
				}
			});
		} else {
			deleteButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					Commands.doRemoveValue(getEditingDomain(), getEObject(),
							ParallelJPackage.eINSTANCE.getHandler_Procedures(),
							ProceduresZone.this.selectedProcedure,
							getEditPart());
					ProceduresZone.this.listViewer
							.remove(ProceduresZone.this.selectedProcedure);
					ProceduresZone.this.listViewer.refresh();
					ProceduresZone.this.selectedProcedure = null;
				}
			});
			addButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					ResourceProcessorFactory[] factories = new ResourceProcessorFactory[] { new ProgramElementsResourceProcessorFactory() };
					ResourceSelector selector = new ResourceSelector(factories,
							Tools.getJavaProjectFromEObject(getEObject())
									.getProject());
					selector.run();
					ProgramElementResourceInfo savedResourceInfo = (ProgramElementResourceInfo) selector
							.getSavedResourceInfo();
					if (savedResourceInfo == null)
						return;
					if (getEObject() != null && getEObject() instanceof Handler) {
						if (!((Handler) getEObject()).getProcedures().contains(
								savedResourceInfo.element)) {
							Commands.doAddValue(getEditingDomain(),
									getEObject(), ParallelJPackage.eINSTANCE
											.getHandler_Procedures(),
									savedResourceInfo.element, getEditPart());
							listViewer.refresh();
						}
					}
				}
			});
		}
	}

	@Override
	public void updateItemsValues() {
		listViewer.setInput(getEObject());
		if (selectedProcedure != null)
			listViewer.setSelection(new StructuredSelection(selectedProcedure));
	}

	protected class ProgramElementsResourceProcessor extends ResourceProcessor {

		public ProgramElementsResourceProcessor(IProject project, String pattern) {
			this.iProject = project;
			this.pattern = pattern;
		}

		@Override
		protected void process() {
			Program program = getEObject().eContainer() instanceof Program ? (Program) getEObject()
					.eContainer() : null;
			if (program != null) {
				for (Element e : program.getElements()) {
					if (e instanceof Procedure && !isAlreadyUsed((Procedure) e)) {
						if ((((Procedure) e).getName() != null)
								&& (this.pattern == null
										|| "*".equals(this.pattern) || ((Procedure) e)
										.getName().startsWith(this.pattern)))
							this.searchResults
									.add(new ProgramElementResourceInfo(
											((Procedure) e).getName(), "",
											program.getName(), (Procedure) e));
					}
				}
			}
		}

		private boolean isAlreadyUsed(Procedure p) {
			if (getEObject() instanceof Handler) {
				return ((Handler) getEObject()).getProcedures().contains(p);
			} else if (getEObject() instanceof Block) {
				return ((Block) getEObject()).getProcedures().contains(p);
			} else
				return true;
		}

	}

	protected class ProgramElementResourceInfo extends ResourceInfo {

		public Procedure element;

		public ProgramElementResourceInfo(String elementName,
				String packageName, String containerName, Procedure element) {
			super(elementName, packageName, containerName);
			this.element = element;
		}

	}

	protected class ProgramElementsResourceProcessorFactory implements
			ResourceProcessorFactory {

		@Override
		public ResourceProcessor createResourceProcessor(IProject project,
				String pattern) {
			return new ProgramElementsResourceProcessor(project, pattern);
		}

	}

	protected class ListLabelProvider implements ILabelProvider {

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
			return element != null ? ((Procedure) element).getName() : null;
		}

	}

	protected class ListContentProvider implements IStructuredContentProvider {

		@Override
		public void dispose() {
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		@Override
		public Object[] getElements(Object inputElement) {
			java.util.List<Procedure> list = new ArrayList<Procedure>();
			if (ProceduresZone.this.isBlock) {
				Block p = (Block) inputElement;
				Iterator<Procedure> iterator = p.getProcedures().iterator();
				while (iterator.hasNext()) {
					Procedure procedure = iterator.next();
					if (procedure != null)
						list.add(procedure);
				}
			} else {
				Handler h = (Handler) inputElement;
				Iterator<Procedure> iterator = h.getProcedures().iterator();
				while (iterator.hasNext()) {
					Procedure procedure = iterator.next();
					if (procedure != null)
						list.add(procedure);
				}
			}
			return list.toArray();
		}

	}

}
