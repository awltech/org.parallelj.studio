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
import org.eclipse.emf.eef.runtime.api.policies.IPropertiesEditionPolicy;
import org.eclipse.emf.eef.runtime.api.providers.IPropertiesEditionPolicyProvider;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.impl.parts.CompositePropertiesEditionPart;
import org.eclipse.emf.eef.runtime.impl.policies.EObjectPropertiesEditionContext;
import org.eclipse.emf.eef.runtime.impl.services.PropertiesEditionPolicyProviderService;
import org.eclipse.emf.eef.runtime.impl.utils.EMFListEditUtil;
import org.eclipse.emf.eef.runtime.ui.utils.EditingUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.EMFComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.FormUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
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
import org.parallelj.designer.properties.parts.ParalleljViewsRepository;
import org.parallelj.designer.properties.parts.PipelinePropertiesEditionPart;
import org.parallelj.designer.properties.providers.ParalleljMessages;
import org.parallelj.designer.properties.tools.LinkReferenceTableViewer;
import org.parallelj.designer.properties.tools.LinkReferenceTableViewer.LinkReferencesListener;
import org.parallelj.model.Link;
import org.parallelj.model.ParallelJFactory;
import org.parallelj.model.Procedure;


// End of user code

/**
 * @author
 * 
 */
public class PipelinePropertiesEditionPartForm extends CompositePropertiesEditionPart implements IFormPropertiesEditionPart, PipelinePropertiesEditionPart {

	protected Text name;
	protected Text capacity;
	protected Text description;
	protected EMFComboViewer join;
	protected EMFComboViewer split;
	protected EMFListEditUtil proceduresEditUtil;
	protected ReferencesTable<? extends EObject> procedures;
	protected List<ViewerFilter> proceduresBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> proceduresFilters = new ArrayList<ViewerFilter>();
	protected EMFListEditUtil outputLinksEditUtil;
	protected LinkReferenceTableViewer<? extends EObject> outputLinks;
	protected List<ViewerFilter> outputLinksBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> outputLinksFilters = new ArrayList<ViewerFilter>();


	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public PipelinePropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
		layout.numColumns = 3;
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
		propertiesSection.setText(ParalleljMessages.PipelinePropertiesEditionPart_PropertiesGroupLabel);
		GridData propertiesSectionData = new GridData(GridData.FILL_HORIZONTAL);
		propertiesSectionData.horizontalSpan = 3;
		propertiesSection.setLayoutData(propertiesSectionData);
		Composite propertiesGroup = widgetFactory.createComposite(propertiesSection);
		GridLayout propertiesGroupLayout = new GridLayout();
		propertiesGroupLayout.numColumns = 3;
		propertiesGroup.setLayout(propertiesGroupLayout);
		createNameText(widgetFactory, propertiesGroup);
		createCapacityText(widgetFactory, propertiesGroup);
		createJoinEMFComboViewer(widgetFactory, propertiesGroup);
		createSplitEMFComboViewer(widgetFactory, propertiesGroup);
		createProceduresTableComposition(widgetFactory, propertiesGroup);
		createOutputLinksTableComposition(widgetFactory, propertiesGroup);
		createDescriptionTextarea(widgetFactory, propertiesGroup);
		propertiesSection.setClient(propertiesGroup);
	}

	
	protected void createNameText(FormToolkit widgetFactory, Composite parent) {
		FormUtils.createPartLabel(widgetFactory, parent, ParalleljMessages.PipelinePropertiesEditionPart_NameLabel, propertiesEditionComponent.isRequired(ParalleljViewsRepository.Pipeline.name, ParalleljViewsRepository.FORM_KIND));
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PipelinePropertiesEditionPartForm.this, ParalleljViewsRepository.Pipeline.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
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
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PipelinePropertiesEditionPartForm.this, ParalleljViewsRepository.Pipeline.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
				}
			}
		});
		EditingUtils.setID(name, ParalleljViewsRepository.Pipeline.name);
		EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(ParalleljViewsRepository.Pipeline.name, ParalleljViewsRepository.FORM_KIND), null); //$NON-NLS-1$
	}

	protected void createCapacityText(FormToolkit widgetFactory, Composite parent) {
		FormUtils.createPartLabel(widgetFactory, parent, ParalleljMessages.PipelinePropertiesEditionPart_CapacityLabel, propertiesEditionComponent.isRequired(ParalleljViewsRepository.Pipeline.capacity, ParalleljViewsRepository.FORM_KIND));
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PipelinePropertiesEditionPartForm.this, ParalleljViewsRepository.Pipeline.capacity, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, capacity.getText()));
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
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PipelinePropertiesEditionPartForm.this, ParalleljViewsRepository.Pipeline.capacity, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, capacity.getText()));
				}
			}
		});
		EditingUtils.setID(capacity, ParalleljViewsRepository.Pipeline.capacity);
		EditingUtils.setEEFtype(capacity, "eef::Text"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(ParalleljViewsRepository.Pipeline.capacity, ParalleljViewsRepository.FORM_KIND), null); //$NON-NLS-1$
	}
	
	
	protected void createDescriptionTextarea(FormToolkit widgetFactory, Composite parent) {
		Label descriptionLabel = FormUtils.createPartLabel(widgetFactory, parent, ParalleljMessages.PipelinePropertiesEditionPart_DescriptionLabel, propertiesEditionComponent.isRequired(ParalleljViewsRepository.Pipeline.description, ParalleljViewsRepository.FORM_KIND));
		GridData descriptionLabelData = new GridData(GridData.FILL_HORIZONTAL);
		descriptionLabelData.horizontalSpan = 3;
		descriptionLabel.setLayoutData(descriptionLabelData);
		description = widgetFactory.createText(parent, "", SWT.BORDER | SWT.WRAP | SWT.MULTI | SWT.V_SCROLL); //$NON-NLS-1$
		GridData descriptionData = new GridData(GridData.FILL_HORIZONTAL);
		descriptionData.horizontalSpan = 2;
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PipelinePropertiesEditionPartForm.this, ParalleljViewsRepository.Pipeline.description, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, description.getText()));
			}

		});
		EditingUtils.setID(description, ParalleljViewsRepository.Pipeline.description);
		EditingUtils.setEEFtype(description, "eef::Textarea"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(ParalleljViewsRepository.Pipeline.description, ParalleljViewsRepository.FORM_KIND), null); //$NON-NLS-1$
	}

	
	protected void createJoinEMFComboViewer(FormToolkit widgetFactory, Composite parent) {
		FormUtils.createPartLabel(widgetFactory, parent, ParalleljMessages.PipelinePropertiesEditionPart_JoinLabel, propertiesEditionComponent.isRequired(ParalleljViewsRepository.Pipeline.join, ParalleljViewsRepository.FORM_KIND));
		join = new EMFComboViewer(parent);
		join.setContentProvider(new ArrayContentProvider());
		join.setLabelProvider(new AdapterFactoryLabelProvider(new EcoreAdapterFactory()));
		GridData joinData = new GridData(GridData.FILL_HORIZONTAL);
		join.getCombo().setLayoutData(joinData);
		join.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 * 	
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PipelinePropertiesEditionPartForm.this, ParalleljViewsRepository.Pipeline.join, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getJoin()));
			}

		});
		join.setID(ParalleljViewsRepository.Pipeline.join);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(ParalleljViewsRepository.Pipeline.join, ParalleljViewsRepository.FORM_KIND), null); //$NON-NLS-1$
	}

	
	protected void createSplitEMFComboViewer(FormToolkit widgetFactory, Composite parent) {
		FormUtils.createPartLabel(widgetFactory, parent, ParalleljMessages.PipelinePropertiesEditionPart_SplitLabel, propertiesEditionComponent.isRequired(ParalleljViewsRepository.Pipeline.split, ParalleljViewsRepository.FORM_KIND));
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PipelinePropertiesEditionPartForm.this, ParalleljViewsRepository.Pipeline.split, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getSplit()));
			}

		});
		split.setID(ParalleljViewsRepository.Pipeline.split);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(ParalleljViewsRepository.Pipeline.split, ParalleljViewsRepository.FORM_KIND), null); //$NON-NLS-1$
	}

	/**
	 * @param container
	 * 
	 */
	protected void createProceduresTableComposition(FormToolkit widgetFactory, Composite parent) {
		this.procedures = new ReferencesTable<Procedure>(ParalleljMessages.PipelinePropertiesEditionPart_ProceduresLabel, new ReferencesTableListener<Procedure>() {			
			public void handleAdd() { addToProcedures();}
			public void handleEdit(Procedure element) { editProcedures(element); }
			public void handleMove(Procedure element, int oldIndex, int newIndex) { moveProcedures(element, oldIndex, newIndex); }
			public void handleRemove(Procedure element) { removeFromProcedures(element); }
			public void navigateTo(Procedure element) { }
		});
		this.procedures.setHelpText(propertiesEditionComponent.getHelpContent(ParalleljViewsRepository.Pipeline.procedures, ParalleljViewsRepository.FORM_KIND));
		this.procedures.createControls(parent, widgetFactory);
		GridData proceduresData = new GridData(GridData.FILL_HORIZONTAL);
		proceduresData.horizontalSpan = 3;
		this.procedures.setLayoutData(proceduresData);
		this.procedures.setLowerBound(0);
		this.procedures.setUpperBound(-1);
		procedures.setID(ParalleljViewsRepository.Pipeline.procedures);
		procedures.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
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
		this.outputLinks.setHelpText(propertiesEditionComponent.getHelpContent(ParalleljViewsRepository.Pipeline.outputLinks, ParalleljViewsRepository.FORM_KIND));
		this.outputLinks.createControls(parent, widgetFactory);
		GridData outputLinksData = new GridData(GridData.FILL_HORIZONTAL);
		outputLinksData.horizontalSpan = 3;
		this.outputLinks.setLayoutData(outputLinksData);
		this.outputLinks.setLowerBound(0);
		this.outputLinks.setUpperBound(-1);
		outputLinks.setID(ParalleljViewsRepository.Pipeline.outputLinks);
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PipelinePropertiesEditionPartForm.this, ParalleljViewsRepository.Pipeline.outputLinks, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, editedElement, newIndex));	
	}

	
	/**
	 * 
	 */
	protected void moveProcedures(Procedure element, int oldIndex, int newIndex) {
		EObject editedElement = proceduresEditUtil.foundCorrespondingEObject(element);
		proceduresEditUtil.moveElement(element, oldIndex, newIndex);
		procedures.refresh();
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PipelinePropertiesEditionPartForm.this, ParalleljViewsRepository.Pipeline.procedures, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, editedElement, newIndex));	
	}

	/**
	 * 
	 */
	protected void addToProcedures() {
		// Start of user code addToProcedures() method body
				Procedure eObject = ParallelJFactory.eINSTANCE.createProcedure();
				IPropertiesEditionPolicyProvider policyProvider = PropertiesEditionPolicyProviderService.getInstance().getProvider(eObject);
				IPropertiesEditionPolicy editionPolicy = policyProvider.getEditionPolicy(eObject);
				if (editionPolicy != null) {
					EObject propertiesEditionObject = editionPolicy.getPropertiesEditionObject(new EObjectPropertiesEditionContext(propertiesEditionComponent, eObject,resourceSet));
					if (propertiesEditionObject != null) {
						proceduresEditUtil.addElement(propertiesEditionObject);
						procedures.refresh();
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PipelinePropertiesEditionPartForm.this, ParalleljViewsRepository.Pipeline.procedures, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, propertiesEditionObject));
					}
				}
		
		
		// End of user code

	}

	/**
	 * 
	 */
	protected void removeFromProcedures(Procedure element) {
		// Start of user code for the removeFromProcedures() method body
				EObject editedElement = proceduresEditUtil.foundCorrespondingEObject(element);
				proceduresEditUtil.removeElement(element);
				procedures.refresh();
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PipelinePropertiesEditionPartForm.this, ParalleljViewsRepository.Pipeline.procedures, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, editedElement));
		
		// End of user code
	}

	/**
	 * 
	 */
	protected void editProcedures(Procedure element) {
		// Start of user code editProcedures() method body
				EObject editedElement = proceduresEditUtil.foundCorrespondingEObject(element);
				IPropertiesEditionPolicyProvider policyProvider = PropertiesEditionPolicyProviderService.getInstance().getProvider(element);
				IPropertiesEditionPolicy editionPolicy = policyProvider	.getEditionPolicy(editedElement);
				if (editionPolicy != null) {
					EObject propertiesEditionObject = editionPolicy.getPropertiesEditionObject(new EObjectPropertiesEditionContext(null, element,resourceSet));
					if (propertiesEditionObject != null) {
						proceduresEditUtil.putElementToRefresh(editedElement, propertiesEditionObject);
						procedures.refresh();
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PipelinePropertiesEditionPartForm.this, ParalleljViewsRepository.Pipeline.procedures, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, editedElement, propertiesEditionObject));
					}
				}
		
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
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#getName()
	 * 
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#setName(String newValue)
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
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#getDescription()
	 * 
	 */
	public String getDescription() {
		return description.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#setDescription(String newValue)
	 * 
	 */
	public void setDescription(String newValue) {
		if (newValue != null) {
			description.setText(newValue);
		} else {
			description.setText("");  //$NON-NLS-1$
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#getJoin()
	 * 
	 */
	public Enumerator getJoin() {
		EEnumLiteral selection = (EEnumLiteral) ((StructuredSelection) join.getSelection()).getFirstElement();
		return selection.getInstance();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#initJoin(EEnum eenum, Enumerator current)
	 */
	public void initJoin(EEnum eenum, Enumerator current) {
		join.setInput(eenum.getELiterals());
		join.modelUpdating(new StructuredSelection(current));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#setJoin(Enumerator newValue)
	 * 
	 */
	public void setJoin(Enumerator newValue) {
		join.modelUpdating(new StructuredSelection(newValue));
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#getSplit()
	 * 
	 */
	public Enumerator getSplit() {
		EEnumLiteral selection = (EEnumLiteral) ((StructuredSelection) split.getSelection()).getFirstElement();
		return selection.getInstance();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#initSplit(EEnum eenum, Enumerator current)
	 */
	public void initSplit(EEnum eenum, Enumerator current) {
		split.setInput(eenum.getELiterals());
		split.modelUpdating(new StructuredSelection(current));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#setSplit(Enumerator newValue)
	 * 
	 */
	public void setSplit(Enumerator newValue) {
		split.modelUpdating(new StructuredSelection(newValue));
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#getProceduresToAdd()
	 * 
	 */
	public List getProceduresToAdd() {
		return proceduresEditUtil.getElementsToAdd();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#getProceduresToRemove()
	 * 
	 */
	public List getProceduresToRemove() {
		return proceduresEditUtil.getElementsToRemove();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#getProceduresToEdit()
	 * 
	 */
	public Map getProceduresToEdit() {
		return proceduresEditUtil.getElementsToRefresh();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#getProceduresToMove()
	 * 
	 */
	public List getProceduresToMove() {
		return proceduresEditUtil.getElementsToMove();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#getProceduresTable()
	 * 
	 */
	public List getProceduresTable() {
		return proceduresEditUtil.getVirtualList();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#initProcedures(EObject current, EReference containingFeature, EReference feature)
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
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#updateProcedures(EObject newValue)
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
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#addFilterProcedures(ViewerFilter filter)
	 * 
	 */
	public void addFilterToProcedures(ViewerFilter filter) {
		proceduresFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#addBusinessFilterProcedures(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToProcedures(ViewerFilter filter) {
		proceduresBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#isContainedInProceduresTable(EObject element)
	 * 
	 */
	public boolean isContainedInProceduresTable(EObject element) {
		return proceduresEditUtil.contains(element);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#getCapacity()
	 * 
	 */
	public String getCapacity() {
		return capacity.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#setCapacity(String newValue)
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
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#getOutputLinksToAdd()
	 * 
	 */
	public List getOutputLinksToAdd() {
		return outputLinksEditUtil.getElementsToAdd();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#getOutputLinksToRemove()
	 * 
	 */
	public List getOutputLinksToRemove() {
		return outputLinksEditUtil.getElementsToRemove();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#getOutputLinksToEdit()
	 * 
	 */
	public Map getOutputLinksToEdit() {
		return outputLinksEditUtil.getElementsToRefresh();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#getOutputLinksToMove()
	 * 
	 */
	public List getOutputLinksToMove() {
		return outputLinksEditUtil.getElementsToMove();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#getOutputLinksTable()
	 * 
	 */
	public List getOutputLinksTable() {
		return outputLinksEditUtil.getVirtualList();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#initOutputLinks(EObject current, EReference containingFeature, EReference feature)
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
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#updateOutputLinks(EObject newValue)
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
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#addFilterOutputLinks(ViewerFilter filter)
	 * 
	 */
	public void addFilterToOutputLinks(ViewerFilter filter) {
		outputLinksFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#addBusinessFilterOutputLinks(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToOutputLinks(ViewerFilter filter) {
		outputLinksBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#isContainedInOutputLinksTable(EObject element)
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
		return ParalleljMessages.Pipeline_Part_Title;
	}

	// Start of user code additional methods
	
	// End of user code


}
