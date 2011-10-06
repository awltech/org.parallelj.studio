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
import org.parallelj.designer.properties.helpers.ParallelJPropertiesMessages;
import org.parallelj.ixea.Zone;
import org.parallelj.ixea.tools.CComboViewer;
import org.parallelj.ixea.tools.Commands;
import org.parallelj.ixea.tools.FormDataBuilder;
import org.parallelj.model.Link;
import org.parallelj.model.ParallelJPackage;
import org.parallelj.model.Predicate;
import org.parallelj.model.Program;
import org.parallelj.model.WhileLoop;

public class PredicateZone extends Zone {

	private CLabel predicateLabel;

	private CCombo predicateCombo;

	private CComboViewer predicateComboViewer;

	private boolean isWhileLoop;

	protected boolean listenerLock = false;

	public PredicateZone(Composite parent, boolean isGroup, boolean isWhileLoop) {
		super(parent, isGroup);
		this.isWhileLoop = isWhileLoop;
	}

	@Override
	public void addItemsToZone() {
		predicateLabel = this.getWidgetFactory().createCLabel(getZone(),
				ParallelJPropertiesMessages.element_predicate.message());
		predicateCombo = this.getWidgetFactory().createCCombo(getZone());
		predicateComboViewer = new CComboViewer(predicateCombo);
		predicateComboViewer.setLabelProvider(new PredicateLabelProvider());
		predicateComboViewer.setContentProvider(new PredicateContentProvider());
	}

	@Override
	public void addLayoutsToItems() {
		new FormDataBuilder().top().left().mediumLabel().apply(predicateLabel);
		new FormDataBuilder().top().left(predicateLabel).right()
				.apply(predicateCombo);
	}

	@Override
	public void addListenersToItems() {
		ISelectionChangedListener listener = new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if (event.getSelection() instanceof IStructuredSelection
						&& !PredicateZone.this.listenerLock) {
					Object o = ((IStructuredSelection) event.getSelection())
							.getFirstElement();
					if (o instanceof Predicate) {
						Predicate predicate = (Predicate) o;
						if (PredicateZone.this.isWhileLoop)
							Commands.doSetValue(getEditingDomain(),
									getEObject(), ParallelJPackage.eINSTANCE
											.getWhileLoop_Predicate(),
									predicate, getEditPart());
						else
							Commands.doSetValue(getEditingDomain(),
									getEObject(), ParallelJPackage.eINSTANCE
											.getLink_Predicate(), predicate,
									getEditPart());
					}
				}
			}
		};
		this.predicateComboViewer.addSelectionChangedListener(listener);
	}

	@Override
	public void updateItemsValues() {
		predicateComboViewer.refresh();
		this.listenerLock = true;
		predicateComboViewer.setInput(getEObject());
		if (isWhileLoop) {
			if (((WhileLoop) getEObject()).getPredicate() != null)
				predicateComboViewer.setSelection(new StructuredSelection(
						((WhileLoop) getEObject()).getPredicate()));
		} else {
			if (((Link) getEObject()).getPredicate() != null)
				predicateComboViewer.setSelection(new StructuredSelection(
						((Link) getEObject()).getPredicate()));
		}
		this.listenerLock = false;
	}

	private class PredicateLabelProvider implements ILabelProvider {

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
			if (element instanceof Predicate)
				return ((Predicate) element).getName();
			else
				return (String) element;
		}

	}

	private class PredicateContentProvider implements
			IStructuredContentProvider {

		@Override
		public void dispose() {
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		@Override
		public Object[] getElements(Object inputElement) {
			List<Predicate> predicates = new ArrayList<Predicate>();
			Program parentProgram = null;
			if (inputElement instanceof WhileLoop)
				parentProgram = ((WhileLoop) inputElement).eContainer() instanceof Program ? (Program) (((WhileLoop) inputElement)
						.eContainer()) : null;
			else if (inputElement instanceof Link) {
				Link link = (Link) inputElement;
				if (link.eContainer().eContainer() != null
						&& link.eContainer().eContainer() instanceof Program)
					parentProgram = (Program) link.eContainer().eContainer();
			}
			if (parentProgram != null && parentProgram.getPredicates() != null) {
				for (Predicate p : parentProgram.getPredicates())
					predicates.add(p);
			}
			return predicates.toArray();
		}

	}

}
