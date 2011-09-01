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

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.eef.runtime.EEFRuntimePlugin;
import org.eclipse.emf.eef.runtime.ui.utils.EEFRuntimeUIMessages;
import org.eclipse.emf.eef.runtime.ui.utils.EditingUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.FormUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.IPropertiesFilteredWidget;
import org.eclipse.emf.eef.runtime.ui.widgets.SWTUtils;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.parallelj.model.Link;
import org.parallelj.model.NamedElement;
import org.parallelj.model.Procedure;

/**
 * This composite has a label, a table that describes a tree structure, and 2
 * buttons on the side of the table to move up or down the selected element.
 */
public class LinkReferenceTableViewer<T extends EObject> implements
		IPropertiesFilteredWidget {

	/**
	 * Image for the add element button.
	 */
	final protected static Image NEW_ELEMENT_IMG = EEFRuntimePlugin
			.getImage(EEFRuntimePlugin.ICONS_16x16 + "Add_16x16.gif");

	/**
	 * Image for the delete element button.
	 */
	final protected static Image DELETE_ELEMENT_IMG = EEFRuntimePlugin
			.getImage(EEFRuntimePlugin.ICONS_16x16 + "Delete_16x16.gif");

	/**
	 * Image for the up button.
	 */
	final protected static Image UP_ELEMENT_IMG = EEFRuntimePlugin
			.getImage(EEFRuntimePlugin.ICONS_16x16 + "ArrowUp_16x16.gif");

	/**
	 * Image for the down button.
	 */
	final protected static Image DOWN_ELEMENT_IMG = EEFRuntimePlugin
			.getImage(EEFRuntimePlugin.ICONS_16x16 + "ArrowDown_16x16.gif");

	/** list of element that we want to display * */
	private List<T> listElement;

	/**
	 * Label above the table.
	 */
	private Label label;

	/**
	 * Table that displays a property for the current element.
	 */
	private Table table;

	/** the table viewer to associate the label provider * */
	private TableViewer tableViewer;

	/**
	 * Button that adds an element.
	 */
	private Button addButton;

	/**
	 * Button that removes an element.
	 */
	private Button removeButton;

	/**
	 * button that moves the element up.
	 */
	private Button upButton;

	/**
	 * button that moves the element down.
	 */
	private Button downButton;

	/**
	 * Listener for the up button.
	 */
	private MouseListener upButtonlistener;

	/**
	 * Listener for the down button.
	 */
	private MouseListener downButtonlistener;

	private Listener tableListener;

	private int upperBound = -1;
	private int lowerBound = 0;
	/**
	 * The listener used by the client to handle business events (Add, Remove,
	 * Move, NavigateTo)
	 */
	private LinkReferencesListener<T> referencesTableListener;

	private String labelToDisplay;

	/**
	 * The Form tool kit use to use this widget in an Eclipse Forms mode
	 */
	private FormToolkit widgetFactory;

	/**
	 * The main composite
	 */
	private Composite composite;

	private Procedure procedure;

	public Procedure getProcedure() {
		return procedure;
	}

	public void setProcedure(Procedure procedure) {
		this.procedure = procedure;
	}

	/**
	 * The adapter factory.
	 */
	protected AdapterFactory adapterFactory = new ComposedAdapterFactory(
			ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

	/**
	 * The help text
	 */
	private String helpText;

	/** The business rules filters. */
	protected List<ViewerFilter> bpFilters;

	/** The filters. */
	protected List<ViewerFilter> filters;

	public void setHelpText(String helpText) {
		this.helpText = helpText;
	}

	/**
	 * the constructor
	 * 
	 * @param labeltoDisplay
	 *            the label to display
	 * @param the
	 *            listener to handle Add, Remove, Move and NavigateTo events
	 */
	public LinkReferenceTableViewer(String labeltoDisplay,
			LinkReferencesListener<T> referenceListener) {
		this.labelToDisplay = labeltoDisplay;
		this.upButtonlistener = new UpButtonlistener();
		this.downButtonlistener = new DownButtonlistener();
		bpFilters = new ArrayList<ViewerFilter>();
		filters = new ArrayList<ViewerFilter>();
		addTableReferenceListener(referenceListener);
	}

	public void addTableReferenceListener(
			LinkReferencesListener<T> referenceListener) {
		this.referencesTableListener = referenceListener;
	}

	public void addSelectionListener(SelectionListener selectionListener) {
		this.table.addSelectionListener(selectionListener);
	}

	public void createControls(Composite parent, FormToolkit widgetFactory) {
		this.widgetFactory = widgetFactory;
		createControls(parent);
	}

	private Composite createComposite(Composite parent) {
		Composite composite;
		if (widgetFactory == null) {
			composite = new Composite(parent, SWT.NONE);
		} else {
			composite = widgetFactory.createComposite(parent);
		}
		return composite;
	}

	private Button createButton(Composite parent, String text, int style) {
		Button button;
		if (widgetFactory == null) {
			button = new Button(parent, style);
			button.setText(text);
		} else {
			button = widgetFactory.createButton(parent, text, style);
		}
		return button;
	}

	private Label createLabel(Composite parent, String text, int style) {
		Label label;
		if (widgetFactory == null) {
			label = new Label(parent, SWT.PUSH);
			label.setText(text);
		} else {
			label = widgetFactory.createLabel(parent, text, style);
		}
		return label;
	}

	private Table createTable(Composite parent, int style) {
		Table table;
		if (widgetFactory == null) {
			table = new Table(parent, style);
		} else {
			table = widgetFactory.createTable(parent, style);
		}
		return table;
	}

	public void createControls(Composite parent) {
		composite = createComposite(parent);
		if (parent instanceof ExpandableComposite) {
			((ExpandableComposite) parent).setClient(composite);
		}
		FormLayout formLayout = new FormLayout();
		formLayout.marginTop = 7;
		composite.setLayout(formLayout);

		FormData data;

		// Create Help Button
		data = new FormData();
		data.top = new FormAttachment(-2, 0);
		data.right = new FormAttachment(100, -ITabbedPropertyConstants.HSPACE);
		Control helpButton = null;
		if (helpText != null) {
			if (widgetFactory != null) {
				helpButton = FormUtils.createHelpButton(widgetFactory,
						composite, helpText, null);
			} else {
				helpButton = SWTUtils.createHelpButton(composite, helpText,
						null);
			}
			helpButton.setLayoutData(data);
		}

		removeButton = createButton(composite, "", SWT.PUSH);
		removeButton.setVisible(false);
		removeButton.setImage(DELETE_ELEMENT_IMG);
		removeButton
				.setToolTipText(EEFRuntimeUIMessages.ReferencesTable_remove_tooltip);
		data = new FormData();
		data.top = new FormAttachment(-6, 0);
		if (helpText != null) {
			data.right = new FormAttachment(helpButton,
					-ITabbedPropertyConstants.HSPACE);
		} else {
			data.right = new FormAttachment(100,
					-ITabbedPropertyConstants.HSPACE);
		}
		removeButton.setLayoutData(data);

		addButton = createButton(composite, "", SWT.PUSH);
		addButton.setVisible(false);
		addButton.setImage(NEW_ELEMENT_IMG);
		addButton
				.setToolTipText(EEFRuntimeUIMessages.ReferencesTable_add_tooltip);

		data = new FormData();
		data.top = new FormAttachment(-6, 0);
		data.right = new FormAttachment(removeButton,
				-ITabbedPropertyConstants.HSPACE);
		addButton.setLayoutData(data);

		// Button Up
		upButton = createButton(composite, "", SWT.PUSH);
		upButton.setVisible(true);
		upButton.setImage(UP_ELEMENT_IMG);
		upButton.setToolTipText(EEFRuntimeUIMessages.ReferencesTable_up_tooltip);

		data = new FormData();
		data.top = new FormAttachment(-6, 0);
		data.right = new FormAttachment(97, -ITabbedPropertyConstants.HSPACE);
		upButton.setLayoutData(data);
		upButton.addMouseListener(upButtonlistener);

		// Button Down
		downButton = createButton(composite, "", SWT.PUSH);
		downButton.setVisible(true);
		downButton.setImage(DOWN_ELEMENT_IMG);
		downButton
				.setToolTipText(EEFRuntimeUIMessages.ReferencesTable_down_tooltip);

		data = new FormData();
		data.top = new FormAttachment(-6, 0);
		data.right = new FormAttachment(upButton,
				-ITabbedPropertyConstants.HSPACE);
		downButton.setLayoutData(data);
		downButton.addMouseListener(downButtonlistener);

		// Create label
		label = createLabel(composite, labelToDisplay, SWT.NONE);
		data = new FormData();
		data.left = new FormAttachment(2, 0);
		data.right = new FormAttachment(downButton,
				-ITabbedPropertyConstants.HSPACE - 5/* 50 */);
		data.top = new FormAttachment(0, 0);
		label.setLayoutData(data);

		table = createTable(composite, SWT.MULTI | SWT.H_SCROLL | SWT.BORDER);
		table.setLayout(new FormLayout());
		table.setVisible(true);

		tableViewer = new TableViewer(table);

		data = new FormData();
		data.height = 100;
		data.top = new FormAttachment(label,
				ITabbedPropertyConstants.VSPACE + 4);
		data.left = new FormAttachment(0, ITabbedPropertyConstants.HSPACE);
		data.right = new FormAttachment(100, -ITabbedPropertyConstants.HSPACE);

		table.setLayoutData(data);
		table.addMouseListener(new MouseListener() {

			@SuppressWarnings("unchecked")
			public void mouseDoubleClick(MouseEvent e) {
				if (table.getSelection() != null
						&& table.getSelectionCount() != 0
						&& table.getSelection()[0].getData() instanceof EObject) {
					// Navigate
					referencesTableListener.navigateTo((T) table.getSelection()[0]
							.getData());
				}
			}

			public void mouseDown(MouseEvent e) {
			}

			public void mouseUp(MouseEvent e) {
			}
		});
	}

	/**
	 * @param layoutData
	 *            the layoutData to set
	 */
	public void setLayoutData(Object layoutData) {
		composite.setLayoutData(layoutData);
	}

	public void setUpperBound(int value) {
		if (value < 0)
			this.upperBound = -1;
		else
			this.upperBound = value;
	}

	public int getUpperBound() {
		return this.upperBound;
	}

	public void setLowerBound(int value) {
		if (value <= 0)
			this.lowerBound = 0;
		else
			this.lowerBound = value;
	}

	public int getLowerBound() {
		return this.lowerBound;
	}

	/**
	 * Sets the given ID to the EMFComboViewer
	 * 
	 * @param id
	 *            the ID to give
	 */
	public void setID(Object id) {
		EditingUtils.setID(table, id);
		EditingUtils.setID(upButton, id);
		EditingUtils.setID(downButton, id);
	}

	/**
	 * Defines the type of reference table
	 * 
	 * @param id
	 *            the type to give
	 */
	public void setEEFType(String type) {
		EditingUtils.setEEFtype(table, type + "::field");
		EditingUtils.setEEFtype(upButton, type + "::upbutton");
		EditingUtils.setEEFtype(downButton, type + "::downbutton");
	}

	/**
	 * @return the ID of the EObjectFlatComboViewer
	 */
	public Object getID() {
		return EditingUtils.getID(table);
	}

	public void refresh() {
		tableViewer.refresh();
	}

	/**
	 * display the content of the table
	 */
	public void initLabelProvider() {
		if (!table.isDisposed()) {
			// set the label provider
			tableViewer.setLabelProvider(getLabelProvider());
		}
	}

	/**
	 * Returns the label provider for the composite
	 * 
	 * @return the label provider or <code>null</code>
	 */
	public AdapterFactoryLabelProvider getLabelProvider() {
		return new AdapterFactoryLabelProvider(adapterFactory);
	}

	/**
	 * Returns the label provider for the composite
	 * 
	 * @return the label provider or <code>null</code>
	 */
	public IContentProvider getContentProvider() {
		return new TableContentProvider();

	}

	/**
	 * Disable Move capability (Hide Up and Down buttons)
	 */
	public void disableMove() {
		upButton.setVisible(false);
		downButton.setVisible(false);
	}

	/**
	 * Listener for the Up Button Specific behavior
	 */
	private class UpButtonlistener implements MouseListener {

		/**
		 * {@inheritDoc}
		 */
		public void mouseDoubleClick(MouseEvent e) {
			// do nothing
		}

		/**
		 * {@inheritDoc}
		 */
		public void mouseDown(MouseEvent e) {
			// do nothing
		}

		/**
		 * {@inheritDoc}
		 */
		@SuppressWarnings("unchecked")
		public void mouseUp(MouseEvent e) {
			// Keep selection
			TableItem[] tableItems = table.getSelection();

			for (int i = (tableItems.length - 1); i >= 0; i--) {
				// Get use case

				int newIndex = listElement.indexOf(tableItems[i].getData()) - 1;
				if (newIndex >= 0 && newIndex < listElement.size()) {
					// Move
					referencesTableListener
							.handleMove((T) tableItems[i].getData(),
									newIndex + 1, newIndex);
				}
			}

		}
	}

	/**
	 * Listener for the Down Button Specific behavior
	 */
	private class DownButtonlistener implements MouseListener {

		/**
		 * {@inheritDoc}
		 */
		public void mouseDoubleClick(MouseEvent e) {
			// do nothing
		}

		/**
		 * {@inheritDoc}
		 */
		public void mouseDown(MouseEvent e) {
			// do nothing
		}

		/**
		 * {@inheritDoc}
		 */
		@SuppressWarnings("unchecked")
		public void mouseUp(MouseEvent e) {
			TableItem[] tableItems = table.getSelection();
			for (int i = (tableItems.length - 1); i >= 0; i--) {
				// Get use case
				int newIndex = listElement.indexOf(tableItems[i].getData()) + 1;
				if (newIndex >= 0 && newIndex < listElement.size()) {
					// Move
					referencesTableListener
							.handleMove((T) tableItems[i].getData(),
									newIndex - 1, newIndex);
				}
			}
		}
	}

	/**
	 * removes listeners from buttons.
	 */
	public void dispose() {
		if (upButton != null && !upButton.isDisposed())
			upButton.removeMouseListener(upButtonlistener);
		if (downButton != null && !downButton.isDisposed())
			downButton.removeMouseListener(downButtonlistener);
		if (table != null && !table.isDisposed())
			table.removeListener(SWT.MouseDoubleClick, tableListener);
		if (filters != null) {
			filters.clear();
			filters = null;
		}
		if (bpFilters != null) {
			bpFilters.clear();
			bpFilters = null;
		}
	}

	/**
	 * get the list of element to display
	 * 
	 * @return the list of element
	 */
	public List<T> getListElement() {
		return listElement;
	}

	/**
	 * set list of element to display
	 * 
	 * @param listElement
	 */
	public void setInput(List<T> listElement, Procedure procedureElement) {
		this.listElement = listElement;
		initLabelProvider();
		tableViewer.setContentProvider(getContentProvider());
		setProcedure(procedureElement);
		tableViewer.setInput(listElement);
		for (ViewerFilter filter : filters) {
			this.tableViewer.addFilter(filter);
		}
		for (ViewerFilter filter : bpFilters) {
			this.tableViewer.addFilter(filter);
		}
	}

	/**
	 * This is the content provider to display the list of element
	 * 
	 */
	class TableContentProvider implements IStructuredContentProvider {

		private static final String OUTPUT_CONDITION = "OutputCondition";
		private static final String FORMATTED_LINK_PATTERN = " ---> ";
		private static final String OUTPUT_CONDITION_NAME = "(final)";

		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}

		public Object[] getElements(Object inputElement) {
			for (int i = 0; i < listElement.size(); i++) {
				NamedElement retrievedElement = ((Link) getProcedure()
						.getOutputLinks().get(i)).getDestination();
				if (retrievedElement != null) {
					if (OUTPUT_CONDITION.equals(retrievedElement.eClass()
							.getName())) {
						((Link) listElement.get(i)).setName(getProcedure()
								.getName() + FORMATTED_LINK_PATTERN + OUTPUT_CONDITION_NAME);
					} else {
						((Link) listElement.get(i)).setName(getProcedure()
								.getName()
								+ FORMATTED_LINK_PATTERN
								+ retrievedElement.getName());
					}

				}
			}
			return listElement.toArray();
		}
	}

	public interface LinkReferencesListener<T extends EObject> {

		void handleMove(T element, int oldIndex, int newIndex);

		void navigateTo(T element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.emf.eef.runtime.ui.widgets.IPropertiesFilteredWidget#
	 * addBusinessRuleFilter(org.eclipse. jface.viewers.ViewerFilter)
	 */
	public void addBusinessRuleFilter(ViewerFilter filter) {
		this.bpFilters.add(filter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.eef.runtime.ui.widgets.IPropertiesFilteredWidget#addFilter
	 * (org.eclipse.jface.viewers .ViewerFilter)
	 */
	public void addFilter(ViewerFilter filter) {
		this.filters.add(filter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.emf.eef.runtime.ui.widgets.IPropertiesFilteredWidget#
	 * removeBusinessRuleFilter(org.eclipse .jface.viewers.ViewerFilter)
	 */
	public void removeBusinessRuleFilter(ViewerFilter filter) {
		this.bpFilters.remove(filter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.eef.runtime.ui.widgets.IPropertiesFilteredWidget#removeFilter
	 * (org.eclipse.jface.viewers .ViewerFilter)
	 */
	public void removeFilter(ViewerFilter filter) {
		this.filters.remove(filter);
	}

	protected void refreshFilters() {
	}

	/**
	 * Sets the tables readonly or not
	 * 
	 * @param enabled
	 *            to set the table readonly or not
	 */
	public void setEnabled(boolean enabled) {
		// addButton.setEnabled(enabled);
		downButton.setEnabled(enabled);
		// removeButton.setEnabled(enabled);
		table.setEnabled(enabled);
		upButton.setEnabled(enabled);
	}

	/**
	 * Sets the tooltip text for the viewer
	 * 
	 * @param tooltip
	 *            the tooltip text
	 */
	public void setToolTipText(String tooltip) {
		downButton.setToolTipText(tooltip);
		table.setToolTipText(tooltip);
		upButton.setToolTipText(tooltip);
	}

	/**
	 * Returns the table.
	 * 
	 * @return the table.
	 */
	public Table getTable() {
		return table;
	}

}
