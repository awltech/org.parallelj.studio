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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.eef.runtime.impl.utils.EEFUtils;
import org.eclipse.emf.eef.runtime.impl.utils.ModelViewerHelper;
import org.eclipse.emf.eef.runtime.ui.utils.EEFRuntimeUIMessages;
import org.eclipse.emf.eef.runtime.ui.widgets.TabElementTreeSelectionDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.parallelj.model.Element;
import org.parallelj.model.Procedure;
import org.parallelj.model.Program;
import org.parallelj.model.Specification;

public class ProcedureSelectionDialog extends
		TabElementTreeSelectionDialog<EObject> {

	private Resource mainResource;

	private Object input;

	private List<ViewerFilter> viewerFilters;

	private IStructuredSelection selection;

	private EClass restrictToEClass;

	private EObject selectedObject;

	/**
	 * business rules filters
	 */
	private List<ViewerFilter> brFilters;

	public ProcedureSelectionDialog(Object inputs, List<ViewerFilter> filters,
			List<ViewerFilter> brFilter, String title,
			EClass restrictToEClasss, Resource mainResources, EObject current) {
		super(inputs, filters, brFilter, title, restrictToEClasss,
				mainResources);

		this.mainResource = mainResources;
		this.input = inputs;
		this.viewerFilters = filters;
		this.brFilters = brFilter;
		this.restrictToEClass = restrictToEClasss;
		this.selectedObject = current;
	}

	public void process(IStructuredSelection selection) {
		// do nothing
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		// this.parent = parent;
		final CTabFolder tabFolder = new CTabFolder(parent, SWT.BORDER);
		tabFolder.setMaximized(true);

		CTabItem tabItem = new CTabItem(tabFolder, SWT.NULL);
		tabItem.setText(EEFRuntimeUIMessages.TabElementTreeSelectionDialog_model_resource_tab_title);
		tabItem.setControl(fillModelpage(tabFolder, false, new ViewerFilter() {
			// Filter elements only in main Resource
			@Override
			public boolean select(Viewer viewer, Object parentElement,
					Object element) {
				if (input instanceof ResourceSet) {
					ResourceSet resourceSet = (ResourceSet) input;
					Resource mainResource = ProcedureSelectionDialog.this.mainResource != null ? ProcedureSelectionDialog.this.mainResource
							: resourceSet.getResources().get(0);
					if (mainResource != null && mainResource == element) {
						return true;
					}
				}
				if (element instanceof EObject) {
					EObject eObject = (EObject) element;
					ResourceSet resourceSet = (ResourceSet) input;
					Resource mainResource = ProcedureSelectionDialog.this.mainResource != null ? ProcedureSelectionDialog.this.mainResource
							: resourceSet.getResources().get(0);
					if (mainResource != null
							&& mainResource == eObject.eResource()) {
						return true;
					}
				}
				return false;
			}
		}));

		return tabFolder;
	}

	private class TreeSelectionPatternFilter extends PatternFilter {
		@Override
		protected boolean isParentMatch(Viewer viewer, Object element) {
			Object[] children = ((ITreeContentProvider) ((AbstractTreeViewer) viewer)
					.getContentProvider()).getChildren(element);
			// apply all filters
			if (viewerFilters != null && children != null) {
				// if one child match, show the parent in tree
				for (ViewerFilter viewerFilter : viewerFilters) {
					for (Object child : children) {
						if (viewerFilter.select(viewer, null, child)) {
							return super.isParentMatch(viewer, element);
						}
					}
				}
				return false;
			} else {
				return super.isParentMatch(viewer, element);
			}
		}

		@Override
		protected boolean isLeafMatch(Viewer viewer, Object element) {
			if (element instanceof EObject) {
				String labelText = ((EObject) element).toString();
				if (labelText != null) {
					return wordMatches(labelText);
				}
			}
			return false;
		}
	}

	/**
	 * Used to display a page in a tab
	 * 
	 * @param tabFolder
	 *            that contains all tabs
	 * @param specificTabFilter
	 *            a specific filter to this page
	 * @return the composite of this page
	 */
	public Control fillModelpage(CTabFolder tabFolder,
			final boolean showResourceItem, final ViewerFilter specificTabFilter) {
		Composite composite = new Composite(tabFolder, SWT.None);
		GridLayout layout = new GridLayout();
		layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
		layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
		layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
		layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
		composite.setLayout(layout);

		// use pattern filter
		PatternFilter patternFilter = new TreeSelectionPatternFilter();
		patternFilter.setIncludeLeadingWildcard(true);

		FilteredTree filteredTree = new FilteredTree(composite, SWT.MULTI
				| SWT.BORDER | SWT.V_SCROLL | SWT.RESIZE, patternFilter, true);
		// use of EMF facilities
		final TreeViewer treeViewer = filteredTree.getViewer();
		treeViewer.setFilters(new ViewerFilter[0]);
		treeViewer.setUseHashlookup(true);
		treeViewer.setContentProvider(new AdapterFactoryContentProvider(
				adapterFactory) {
			@Override
			public Object[] getElements(Object inputElement) {
				if (inputElement instanceof ResourceSet) {
					ResourceSet resourceSet = (ResourceSet) inputElement;
					if (showResourceItem) {
						return resourceSet.getResources().toArray();
					} else {
						ArrayList<EObject> contents = new ArrayList<EObject>();
						for (Resource resource : resourceSet.getResources()) {
							for (EObject eObject : resource.getContents()) {
								if (eObject instanceof Specification) {
									Specification specification = (Specification) eObject;
									EList<Program> programs = specification
											.getPrograms();
									for (Program program : programs) {
										EList<Element> elements = program
												.getElements();
										for (Element element : elements) {
											if (element.hashCode() == selectedObject
													.hashCode()) {

												contents.add(program);
											}
										}
									}
								}
							}
						}
						return contents.toArray();
					}
				}
				return null;
			}

			@Override
			public Object[] getChildren(Object object) {
				Object[] childrens = super.getChildren(object);
				ArrayList<Object> childContents = new ArrayList<Object>();
				for (Object child : childrens) {
					if (child instanceof Procedure) {
						childContents.add(child);
					}
				}
				return childContents.toArray();
			}

		});

		ArrayList<ViewerFilter> filters = new ArrayList<ViewerFilter>();
		if (specificTabFilter != null) {
			filters.add(specificTabFilter);
		}
		if (viewerFilters != null && !viewerFilters.isEmpty()) {
			for (ViewerFilter filter : viewerFilters) {
				filters.add(filter);
			}
		}
		// for now, add the businessRuleFilters to the 'normal' filters
		if (brFilters != null && !brFilters.isEmpty()) {
			for (ViewerFilter filter : brFilters) {
				filters.add(filter);
			}
		}
		filters.add(patternFilter);
		ViewerFilter[] v = filters.toArray(new ViewerFilter[filters.size()]);
		treeViewer.setFilters(v);
		treeViewer.setLabelProvider(new AdapterFactoryLabelProvider(
				adapterFactory));

		filteredTree.setLayoutData(new GridData(550, 300));
		// handle selection change
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				if (event.getSelection() instanceof IStructuredSelection) {
					// Check selection
					IStructuredSelection structuredSelection = (IStructuredSelection) event
							.getSelection();
					if (structuredSelection != null
							&& !structuredSelection.isEmpty()) {
						Object o = structuredSelection.getFirstElement();
						if (o instanceof EObject) {
							// Check type matching
							EObject eObject = (EObject) o;
							Button okButton = getButton(IDialogConstants.OK_ID);
							if (EEFUtils.isInstanceOfEClass(eObject,
									restrictToEClass)) {
								selection = structuredSelection;
								if (okButton != null) {
									okButton.setEnabled(true);
								}
							} else {
								// Reject selection
								if (okButton != null) {
									okButton.setEnabled(false);
								}
							}
						}
					}

				}
			}
		});

		// handle double click to validate
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				if (selection != null && !selection.isEmpty()) {
					Object o = selection.getFirstElement();
					if (o instanceof EObject) {
						if (EEFUtils.isInstanceOfEClass((EObject) o,
								restrictToEClass)) {
							okPressed();
						}
					}
				}
			}
		});

		treeViewer.setInput(input);

		// Init selected element
		if (selection != null) {
			treeViewer.setSelection(selection);
		}

		return composite;

	}

	/**
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	@Override
	protected void okPressed() {
		if (selection != null && !selection.isEmpty()) {
			process(selection);
			ModelViewerHelper.setLastSelection(selection);
		}
		super.okPressed();
	}
}
