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
package org.parallelj.designer.properties.parts.forms;

// Start of user code for imports
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.impl.parts.CompositePropertiesEditionPart;
import org.eclipse.emf.eef.runtime.impl.utils.EMFListEditUtil;
import org.eclipse.emf.eef.runtime.ui.utils.EditingUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.EMFComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.FormUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.parallelj.designer.properties.parts.HandlerPropertiesEditionPart;
import org.parallelj.designer.properties.parts.ParalleljViewsRepository;
import org.parallelj.designer.properties.providers.ParalleljMessages;
import org.parallelj.designer.properties.tools.LinkReferenceTableViewer;
import org.parallelj.designer.properties.tools.LinkReferenceTableViewer.LinkReferencesListener;
import org.parallelj.designer.properties.tools.ProcedureSelectionDialog;
import org.parallelj.model.Link;
import org.parallelj.model.ParallelJPackage;
import org.parallelj.model.Procedure;


// End of user code

/**
 * @author
 * 
 */
public class HandlerPropertiesEditionPartForm extends CompositePropertiesEditionPart implements IFormPropertiesEditionPart, HandlerPropertiesEditionPart {

	protected Text name;
	protected Text description;
	protected Text capacity;
	//protected Text executable;
	protected EMFComboViewer split;
	private EMFListEditUtil proceduresEditUtil;
	protected ReferencesTable<? extends EObject> procedures;
	protected List<ViewerFilter> proceduresBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> proceduresFilters = new ArrayList<ViewerFilter>();
	protected EMFListEditUtil outputLinksEditUtil;
	protected LinkReferenceTableViewer<? extends EObject> outputLinks;
	protected List<ViewerFilter> outputLinksBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> outputLinksFilters = new ArrayList<ViewerFilter>();

	/**
	 * Uncomment this part to reactivate the buttons.
	 */
	// Start of user code
//	private Button gExecutableCreateButton;
//	private Button gExecutableSelectButton;
	// End of user code

	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public HandlerPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
		super(editionComponent);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart#
	 *  createFigure(org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.widgets.FormToolkit)
	 * 
	 */
	public Composite createFigure(final Composite parent, final FormToolkit widgetFactory) {
		ScrolledForm scrolledForm = widgetFactory.createScrolledForm(parent);
		Form form = scrolledForm.getForm();
		view = form.getBody();
		GridLayout layout = new GridLayout();
		// Start of user code
		layout.numColumns = 6;
		// End of user code
		view.setLayout(layout);
		createControls(widgetFactory, view);
		return scrolledForm;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart#
	 *  createControls(org.eclipse.ui.forms.widgets.FormToolkit, org.eclipse.swt.widgets.Composite)
	 * 
	 */
	public void createControls(final FormToolkit widgetFactory, Composite view) {
		createPropertiesGroup(widgetFactory, view);

		// Start of user code for additional ui definition
		
		// End of user code
	}
	/**
	 * 
	 */
	protected void createPropertiesGroup(FormToolkit widgetFactory, final Composite view) {
		Section propertiesSection = widgetFactory.createSection(view, Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		propertiesSection.setText(ParalleljMessages.HandlerPropertiesEditionPart_PropertiesGroupLabel);
		GridData propertiesSectionData = new GridData(GridData.FILL_HORIZONTAL);
		
		// Start of user code
		propertiesSectionData.horizontalSpan = 10;
		propertiesSection.setLayoutData(propertiesSectionData);
		Composite propertiesGroup = widgetFactory.createComposite(propertiesSection);
		GridLayout propertiesGroupLayout = new GridLayout();
		propertiesGroupLayout.numColumns = 6;
		propertiesGroup.setLayout(propertiesGroupLayout);
		// End of user code
				
		createNameText(widgetFactory, propertiesGroup);
		createCapacityText(widgetFactory, propertiesGroup);
		//createExecutableText(widgetFactory, propertiesGroup);
		createSplitEMFComboViewer(widgetFactory, propertiesGroup);
		createProceduresReferencesTable(widgetFactory, propertiesGroup);
		createOutputLinksTableComposition(widgetFactory, propertiesGroup);
		createDescriptionTextarea(widgetFactory, propertiesGroup);
		propertiesSection.setClient(propertiesGroup);
	}

	
	protected void createNameText(FormToolkit widgetFactory, Composite parent) {
		FormUtils.createPartLabel(widgetFactory, parent, ParalleljMessages.HandlerPropertiesEditionPart_NameLabel, propertiesEditionComponent.isRequired(ParalleljViewsRepository.Handler.name, ParalleljViewsRepository.FORM_KIND));
		name = widgetFactory.createText(parent, ""); //$NON-NLS-1$
		name.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		widgetFactory.paintBordersFor(parent);
		GridData nameData = new GridData(GridData.FILL_HORIZONTAL);
		name.setLayoutData(nameData);
		name.addFocusListener(new FocusAdapter() {
			/**
			 * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void focusLost(FocusEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(HandlerPropertiesEditionPartForm.this, ParalleljViewsRepository.Handler.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
			}
		});
		name.addKeyListener(new KeyAdapter() {
			/**
			 * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.CR) {
					if (propertiesEditionComponent != null)
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(HandlerPropertiesEditionPartForm.this, ParalleljViewsRepository.Handler.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
				}
			}
		});
		EditingUtils.setID(name, ParalleljViewsRepository.Handler.name);
		EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$

		// Start of user code
		FormUtils.createPartLabel(widgetFactory, parent, "", true);
		FormUtils.createPartLabel(widgetFactory, parent, "", true);
		FormUtils.createPartLabel(widgetFactory, parent, "", true);
		// End of user code
		
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(ParalleljViewsRepository.Handler.name, ParalleljViewsRepository.FORM_KIND), null); //$NON-NLS-1$
	}

	protected void createCapacityText(FormToolkit widgetFactory, Composite parent) {
		FormUtils.createPartLabel(widgetFactory, parent, ParalleljMessages.HandlerPropertiesEditionPart_CapacityLabel, propertiesEditionComponent.isRequired(ParalleljViewsRepository.Handler.capacity, ParalleljViewsRepository.FORM_KIND));
		capacity = widgetFactory.createText(parent, ""); //$NON-NLS-1$
		capacity.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		widgetFactory.paintBordersFor(parent);
		GridData capacityData = new GridData(GridData.FILL_HORIZONTAL);
		capacity.setLayoutData(capacityData);
		capacity.addFocusListener(new FocusAdapter() {
			/**
			 * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void focusLost(FocusEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(HandlerPropertiesEditionPartForm.this, ParalleljViewsRepository.Handler.capacity, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, capacity.getText()));
			}
		});
		capacity.addKeyListener(new KeyAdapter() {
			/**
			 * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.CR) {
					if (propertiesEditionComponent != null)
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(HandlerPropertiesEditionPartForm.this, ParalleljViewsRepository.Handler.capacity, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, capacity.getText()));
				}
			}
		});
		EditingUtils.setID(capacity, ParalleljViewsRepository.Handler.capacity);
		EditingUtils.setEEFtype(capacity, "eef::Text"); //$NON-NLS-1$
		// Start of user code
		FormUtils.createPartLabel(widgetFactory, parent, "", true);
		FormUtils.createPartLabel(widgetFactory, parent, "", true);
		FormUtils.createPartLabel(widgetFactory, parent, "", true);
		// End of user code
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(ParalleljViewsRepository.Handler.capacity, ParalleljViewsRepository.FORM_KIND), null); //$NON-NLS-1$
	}
	
	
	protected void createDescriptionTextarea(FormToolkit widgetFactory, Composite parent) {
		Label descriptionLabel = FormUtils.createPartLabel(widgetFactory, parent, ParalleljMessages.HandlerPropertiesEditionPart_DescriptionLabel, propertiesEditionComponent.isRequired(ParalleljViewsRepository.Handler.description, ParalleljViewsRepository.FORM_KIND));
		GridData descriptionLabelData = new GridData(GridData.FILL_HORIZONTAL);
		
		// Start of user code
		descriptionLabelData.horizontalSpan = 6;
		descriptionLabel.setLayoutData(descriptionLabelData);
		description = widgetFactory.createText(parent, "", SWT.BORDER | SWT.WRAP | SWT.MULTI | SWT.V_SCROLL); //$NON-NLS-1$
		GridData descriptionData = new GridData(GridData.FILL_HORIZONTAL);
		descriptionData.horizontalSpan = 2;
		// End of user code
		
		descriptionData.heightHint = 80;
		descriptionData.widthHint = 200;
		description.setLayoutData(descriptionData);
		description.addFocusListener(new FocusAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
			 * 
			 */
			public void focusLost(FocusEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(HandlerPropertiesEditionPartForm.this, ParalleljViewsRepository.Handler.description, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, description.getText()));
			}

		});
		EditingUtils.setID(description, ParalleljViewsRepository.Handler.description);
		EditingUtils.setEEFtype(description, "eef::Textarea"); //$NON-NLS-1$

		// Start of user code
		FormUtils.createPartLabel(widgetFactory, parent, "", true);
		FormUtils.createPartLabel(widgetFactory, parent, "", true);
		FormUtils.createPartLabel(widgetFactory, parent, "", true);
		// End of user code
		
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(ParalleljViewsRepository.Handler.description, ParalleljViewsRepository.FORM_KIND), null); //$NON-NLS-1$
	}

	
//	protected void createExecutableText(FormToolkit widgetFactory, Composite parent) {
//		FormUtils.createPartLabel(widgetFactory, parent, ParalleljMessages.HandlerPropertiesEditionPart_ExecutableLabel, propertiesEditionComponent.isRequired(ParalleljViewsRepository.Handler.executable, ParalleljViewsRepository.FORM_KIND));
//		executable = widgetFactory.createText(parent, ""); //$NON-NLS-1$
//		executable.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
//		widgetFactory.paintBordersFor(parent);
//		GridData executableData = new GridData(GridData.FILL_HORIZONTAL);
//		executable.setLayoutData(executableData);
//		//remove: ADDED to make it non Editable
//		executable.setEditable(false);
//		
//		// Start of user code
//		
//		// Not Required		
////		executable.addFocusListener(new FocusAdapter() {
////			/**
////			 * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
////			 * 
////			 */
////			@Override
////			@SuppressWarnings("synthetic-access")
////			public void focusLost(FocusEvent e) {
////				if (propertiesEditionComponent != null)
////					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(HandlerPropertiesEditionPartForm.this, ParalleljViewsRepository.Handler.executable, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, executable.getText()));
////			}
////		});
////		executable.addKeyListener(new KeyAdapter() {
////			/**
////			 * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
////			 * 
////			 */
////			@Override
////			@SuppressWarnings("synthetic-access")
////			public void keyPressed(KeyEvent e) {
////				if (e.character == SWT.CR) {
////					if (propertiesEditionComponent != null)
////						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(HandlerPropertiesEditionPartForm.this, ParalleljViewsRepository.Handler.executable, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, executable.getText()));
////				}
////			}
////		});
//		
//		EditingUtils.setID(executable, ParalleljViewsRepository.Handler.executable);
//		EditingUtils.setEEFtype(executable, "eef::Text"); //$NON-NLS-1$
//
//		/**
//		 * Uncomment this part to reactivate the buttons.
//		 */
//		
//		// Start of user code
//		
//		gExecutableCreateButton = widgetFactory.createButton(parent, "Create",
//				SWT.PUSH);
//		gExecutableCreateButton.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent event) {
//				String newExecutableValue = "";
//				if (propertiesEditionComponent != null)
//					propertiesEditionComponent
//							.firePropertiesChanged(new PropertiesEditionEvent(
//									HandlerPropertiesEditionPartForm.this,
//									ParalleljViewsRepository.Handler.executable,
//									PropertiesEditionEvent.COMMIT,
//									PropertiesEditionEvent.SET, null, newExecutableValue));
//				
//			}
//		});
//		
//		
//		gExecutableSelectButton = widgetFactory.createButton(parent, "Select",
//				SWT.PUSH);
//		gExecutableSelectButton.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent event) {
//				if (propertiesEditionComponent != null)
//					propertiesEditionComponent
//							.firePropertiesChanged(new PropertiesEditionEvent(
//									HandlerPropertiesEditionPartForm.this,
//									ParalleljViewsRepository.Handler.executable,
//									PropertiesEditionEvent.COMMIT,
//									PropertiesEditionEvent.SET, null, null));
//			}
//		});
//
//		FormUtils.createPartLabel(widgetFactory, parent, "", true);		
//		// End of user code
//		
//		/**
//		 * Remove this part to reactivate the buttons.
//		 */		
////		FormUtils.createPartLabel(widgetFactory, parent, "", true);		
////		FormUtils.createPartLabel(widgetFactory, parent, "", true);		
//		
//		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(ParalleljViewsRepository.Handler.executable, ParalleljViewsRepository.FORM_KIND), null); //$NON-NLS-1$
//	}

	
	protected void createSplitEMFComboViewer(FormToolkit widgetFactory, Composite parent) {
		FormUtils.createPartLabel(widgetFactory, parent, ParalleljMessages.HandlerPropertiesEditionPart_SplitLabel, propertiesEditionComponent.isRequired(ParalleljViewsRepository.Handler.split, ParalleljViewsRepository.FORM_KIND));
		split = new EMFComboViewer(parent);
		split.setContentProvider(new ArrayContentProvider());
		split.setLabelProvider(new AdapterFactoryLabelProvider(new EcoreAdapterFactory()));
		GridData splitData = new GridData(GridData.FILL_HORIZONTAL);
		split.getCombo().setLayoutData(splitData);
		split.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 * 	
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(HandlerPropertiesEditionPartForm.this, ParalleljViewsRepository.Handler.split, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getSplit()));
			}

		});
		split.setID(ParalleljViewsRepository.Handler.split);

		// Start of user code
		FormUtils.createPartLabel(widgetFactory, parent, "", true);
		FormUtils.createPartLabel(widgetFactory, parent, "", true);
		FormUtils.createPartLabel(widgetFactory, parent, "", true);
		// End of user code
		
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(ParalleljViewsRepository.Handler.split, ParalleljViewsRepository.FORM_KIND), null); //$NON-NLS-1$
	}

	/**
	 * @param container
	 * 
	 */
	protected void createOutputLinksTableComposition(FormToolkit widgetFactory, Composite parent) {
		this.outputLinks = new LinkReferenceTableViewer<Link>("OutputLinks : ", new LinkReferencesListener<Link>() {			
			public void handleMove(Link element, int oldIndex, int newIndex) { moveOutputLinks(element, oldIndex, newIndex); }
			public void navigateTo(Link element) { }
		});
		this.outputLinks.setHelpText(propertiesEditionComponent.getHelpContent(ParalleljViewsRepository.Handler.outputLinks, ParalleljViewsRepository.FORM_KIND));
		this.outputLinks.createControls(parent, widgetFactory);
		GridData outputLinksData = new GridData(GridData.FILL_HORIZONTAL);
		outputLinksData.horizontalSpan = 3;
		this.outputLinks.setLayoutData(outputLinksData);
		this.outputLinks.setLowerBound(0);
		this.outputLinks.setUpperBound(-1);
		outputLinks.setID(ParalleljViewsRepository.Handler.outputLinks);
		outputLinks.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
		
		// Start of user code
		FormUtils.createPartLabel(widgetFactory, parent, "", true);
		FormUtils.createPartLabel(widgetFactory, parent, "", true);
		FormUtils.createPartLabel(widgetFactory, parent, "", true);
		// End of user code
	}

	/**
	 * 
	 */
	protected void moveOutputLinks(Link element, int oldIndex, int newIndex) {
		EObject editedElement = outputLinksEditUtil.foundCorrespondingEObject(element);
		outputLinksEditUtil.moveElement(element, oldIndex, newIndex);
		outputLinks.refresh();
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(HandlerPropertiesEditionPartForm.this, ParalleljViewsRepository.Handler.outputLinks, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, editedElement, newIndex));	
	}

	
	/**
	 * 
	 */
	protected void createProceduresReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.procedures = new ReferencesTable<Procedure>("Procedures : ", new ReferencesTableListener<Procedure>() {
			public void handleAdd() {
				ProcedureSelectionDialog dialog = new ProcedureSelectionDialog(resourceSet, proceduresFilters, proceduresBusinessFilters,
				"Procedure", ParallelJPackage.eINSTANCE.getProcedure(), current.eResource(), current) {
					@Override
					public void process(IStructuredSelection selection) {
						for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
							EObject elem = (EObject) iter.next();
							if (!proceduresEditUtil.getVirtualList().contains(elem))
								proceduresEditUtil.addElement(elem);
							propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(HandlerPropertiesEditionPartForm.this, ParalleljViewsRepository.Handler.procedures,
								PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
						}
						procedures.refresh();
					}
				};
				dialog.open();
			}
			public void handleEdit(Procedure element) { editProcedures(element); }
			public void handleMove(Procedure element, int oldIndex, int newIndex) { moveProcedures(element, oldIndex, newIndex); }
			public void handleRemove(Procedure element) { removeFromProcedures(element); }
			public void navigateTo(Procedure element) { }
		});
		this.procedures.setHelpText(propertiesEditionComponent.getHelpContent(ParalleljViewsRepository.Handler.procedures, ParalleljViewsRepository.FORM_KIND));
		this.procedures.createControls(parent, widgetFactory);
		GridData proceduresData = new GridData(GridData.FILL_HORIZONTAL);
		proceduresData.horizontalSpan = 3;
		this.procedures.setLayoutData(proceduresData);
		this.procedures.disableMove();
		procedures.setID(ParalleljViewsRepository.Handler.procedures);
		procedures.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		
		// Start of user code
		FormUtils.createPartLabel(widgetFactory, parent, "", true);
		FormUtils.createPartLabel(widgetFactory, parent, "", true);
		FormUtils.createPartLabel(widgetFactory, parent, "", true);
		// End of user code
	}

	/**
	 * 
	 */
	protected void moveProcedures(Procedure element, int oldIndex, int newIndex) {
//		EObject editedElement = proceduresEditUtil.foundCorrespondingEObject(element);
//		proceduresEditUtil.moveElement(element, oldIndex, newIndex);
//		procedures.refresh();
//		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(HandlerPropertiesEditionPartForm.this, ParalleljViewsRepository.Handler.procedures, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, editedElement, newIndex));
	}

	/**
	 * 
	 */
	protected void removeFromProcedures(Procedure element) {
		// Start of user code for the removeFromProcedures() method body
				EObject editedElement = proceduresEditUtil.foundCorrespondingEObject(element);
				proceduresEditUtil.removeElement(element);
				procedures.refresh();
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(HandlerPropertiesEditionPartForm.this, ParalleljViewsRepository.Handler.procedures, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, editedElement));
		
		// End of user code
	}

	/**
	 * 
	 */
	protected void editProcedures(Procedure element) {
		// Start of user code editProcedures() method body		
//				EObject editedElement = proceduresEditUtil.foundCorrespondingEObject(element);
//				IPropertiesEditionPolicyProvider policyProvider = PropertiesEditionPolicyProviderService.getInstance().getProvider(element);
//				IPropertiesEditionPolicy editionPolicy = policyProvider	.getEditionPolicy(editedElement);
//				if (editionPolicy != null) {
//					EObject propertiesEditionObject = editionPolicy.getPropertiesEditionObject(new EObjectPropertiesEditionContext(null, element,resourceSet));
//					if (propertiesEditionObject != null) {
//						proceduresEditUtil.putElementToRefresh(editedElement, propertiesEditionObject);
//						procedures.refresh();
//						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(HandlerPropertiesEditionPartForm.this, ParalleljViewsRepository.Handler.procedures, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, editedElement, propertiesEditionObject));
//					}
//				}
		
		// End of user code
	}

	

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionListener#firePropertiesChanged(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public void firePropertiesChanged(IPropertiesEditionEvent event) {
		// Start of user code for tab synchronization
		
		// End of user code
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.HandlerPropertiesEditionPart#getName()
	 * 
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.HandlerPropertiesEditionPart#setName(String newValue)
	 * 
	 */
	public void setName(String newValue) {
		if (newValue != null) {
			name.setText(newValue);
		} else {
			name.setText(""); //$NON-NLS-1$
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.HandlerPropertiesEditionPart#getDescription()
	 * 
	 */
	public String getDescription() {
		return description.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.HandlerPropertiesEditionPart#setDescription(String newValue)
	 * 
	 */
	public void setDescription(String newValue) {
		if (newValue != null) {
			description.setText(newValue);
		} else {
			description.setText("");  //$NON-NLS-1$
		}
	}


//	/**
//	 * {@inheritDoc}
//	 * 
//	 * @see org.parallelj.parts.HandlerPropertiesEditionPart#getExecutable()
//	 * 
//	 */
//	public String getExecutable() {
//		return executable.getText();
//	}
//
//	/**
//	 * {@inheritDoc}
//	 * 
//	 * @see org.parallelj.parts.HandlerPropertiesEditionPart#setExecutable(String newValue)
//	 * 
//	 */
//	public void setExecutable(String newValue) {
//		if (newValue != null) {
//			executable.setText(newValue);
//		} else {
//			executable.setText(""); //$NON-NLS-1$
//		}
//	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.HandlerPropertiesEditionPart#getSplit()
	 * 
	 */
	public Enumerator getSplit() {
		EEnumLiteral selection = (EEnumLiteral) ((StructuredSelection) split.getSelection()).getFirstElement();
		return selection.getInstance();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.HandlerPropertiesEditionPart#initSplit(EEnum eenum, Enumerator current)
	 */
	public void initSplit(EEnum eenum, Enumerator current) {
		split.setInput(eenum.getELiterals());
		split.modelUpdating(new StructuredSelection(current));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.HandlerPropertiesEditionPart#setSplit(Enumerator newValue)
	 * 
	 */
	public void setSplit(Enumerator newValue) {
		split.modelUpdating(new StructuredSelection(newValue));
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.HandlerPropertiesEditionPart#getCapacity()
	 * 
	 */
	public String getCapacity() {
		return capacity.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.HandlerPropertiesEditionPart#setCapacity(String newValue)
	 * 
	 */
	public void setCapacity(String newValue) {
		if (newValue != null) {
			capacity.setText(newValue);
		} else {
			capacity.setText(""); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.HandlerPropertiesEditionPart#getProceduresToAdd()
	 * 
	 */
	public List getProceduresToAdd() {
		return proceduresEditUtil.getElementsToAdd();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.HandlerPropertiesEditionPart#getProceduresToRemove()
	 * 
	 */
	public List getProceduresToRemove() {
		return proceduresEditUtil.getElementsToRemove();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.HandlerPropertiesEditionPart#getProceduresTable()
	 * 
	 */
	public List getProceduresTable() {
		return proceduresEditUtil.getVirtualList();
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.HandlerPropertiesEditionPart#initProcedures(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initProcedures(EObject current, EReference containingFeature, EReference feature) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		if (containingFeature != null)
			proceduresEditUtil = new EMFListEditUtil(current, containingFeature, feature);
		else
			proceduresEditUtil = new EMFListEditUtil(current, feature);
		this.procedures.setInput(proceduresEditUtil.getVirtualList());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.HandlerPropertiesEditionPart#updateProcedures(EObject newValue)
	 * 
	 */
	public void updateProcedures(EObject newValue) {
		if(proceduresEditUtil != null){
			proceduresEditUtil.reinit(newValue);
			procedures.refresh();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.HandlerPropertiesEditionPart#addFilterProcedures(ViewerFilter filter)
	 * 
	 */
	public void addFilterToProcedures(ViewerFilter filter) {
		proceduresFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.HandlerPropertiesEditionPart#addBusinessFilterProcedures(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToProcedures(ViewerFilter filter) {
		proceduresBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.HandlerPropertiesEditionPart#isContainedInProceduresTable(EObject element)
	 * 
	 */
	public boolean isContainedInProceduresTable(EObject element) {
		return proceduresEditUtil.contains(element);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.HandlerPropertiesEditionPart#getOutputLinksToAdd()
	 * 
	 */
	public List getOutputLinksToAdd() {
		return outputLinksEditUtil.getElementsToAdd();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.HandlerPropertiesEditionPart#getOutputLinksToRemove()
	 * 
	 */
	public List getOutputLinksToRemove() {
		return outputLinksEditUtil.getElementsToRemove();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.HandlerPropertiesEditionPart#getOutputLinksToEdit()
	 * 
	 */
	public Map getOutputLinksToEdit() {
		return outputLinksEditUtil.getElementsToRefresh();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.HandlerPropertiesEditionPart#getOutputLinksToMove()
	 * 
	 */
	public List getOutputLinksToMove() {
		return outputLinksEditUtil.getElementsToMove();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.HandlerPropertiesEditionPart#getOutputLinksTable()
	 * 
	 */
	public List getOutputLinksTable() {
		return outputLinksEditUtil.getVirtualList();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.HandlerPropertiesEditionPart#initOutputLinks(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initOutputLinks(EObject current, EReference containingFeature, EReference feature) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		if (containingFeature != null)
			outputLinksEditUtil = new EMFListEditUtil(current, containingFeature, feature);
		else
			outputLinksEditUtil = new EMFListEditUtil(current, feature);
		this.outputLinks.setInput(outputLinksEditUtil.getVirtualList(), (Procedure) current);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.HandlerPropertiesEditionPart#updateOutputLinks(EObject newValue)
	 * 
	 */
	public void updateOutputLinks(EObject newValue) {
		if(outputLinksEditUtil != null){
			outputLinksEditUtil.reinit(newValue);
			outputLinks.refresh();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.HandlerPropertiesEditionPart#addFilterOutputLinks(ViewerFilter filter)
	 * 
	 */
	public void addFilterToOutputLinks(ViewerFilter filter) {
		outputLinksFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.HandlerPropertiesEditionPart#addBusinessFilterOutputLinks(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToOutputLinks(ViewerFilter filter) {
		outputLinksBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.HandlerPropertiesEditionPart#isContainedInOutputLinksTable(EObject element)
	 * 
	 */
	public boolean isContainedInOutputLinksTable(EObject element) {
		return outputLinksEditUtil.contains(element);
	}



	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * 
	 */
	public String getTitle() {
		return ParalleljMessages.Handler_Part_Title;
	}

	// Start of user code additional methods
	
	// End of user code


}
