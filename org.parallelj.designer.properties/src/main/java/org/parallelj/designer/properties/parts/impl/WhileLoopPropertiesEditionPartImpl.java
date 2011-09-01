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
package org.parallelj.designer.properties.parts.impl;

// Start of user code for imports
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart;
import org.eclipse.emf.eef.runtime.api.policies.IPropertiesEditionPolicy;
import org.eclipse.emf.eef.runtime.api.providers.IPropertiesEditionPolicyProvider;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.impl.parts.CompositePropertiesEditionPart;
import org.eclipse.emf.eef.runtime.impl.policies.EObjectPropertiesEditionContext;
import org.eclipse.emf.eef.runtime.impl.services.PropertiesEditionPolicyProviderService;
import org.eclipse.emf.eef.runtime.impl.utils.EMFListEditUtil;
import org.eclipse.emf.eef.runtime.ui.providers.EMFListContentProvider;
import org.eclipse.emf.eef.runtime.ui.utils.EditingUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.EMFComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.SWTUtils;
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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.parallelj.designer.properties.parts.ParalleljViewsRepository;
import org.parallelj.designer.properties.parts.WhileLoopPropertiesEditionPart;
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
public class WhileLoopPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, WhileLoopPropertiesEditionPart {

	protected Text name;
	protected Text capacity;
	protected Text description;
	protected Text executable;
	protected EMFComboViewer predicate;
	protected EMFComboViewer join;
	protected EMFComboViewer split;
	protected EMFListEditUtil outputLinksEditUtil;
	protected LinkReferenceTableViewer<Link> outputLinks;
	protected List<ViewerFilter> outputLinksBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> outputLinksFilters = new ArrayList<ViewerFilter>();



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public WhileLoopPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
		super(editionComponent);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart#
	 * 			createFigure(org.eclipse.swt.widgets.Composite)
	 * 
	 */
	public Composite createFigure(final Composite parent) {
		view = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		view.setLayout(layout);
		createControls(view);
		return view;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart#
	 * 			createControls(org.eclipse.swt.widgets.Composite)
	 * 
	 */
	public void createControls(Composite view) { 
		createPropertiesGroup(view);


		// Start of user code for additional ui definition
		
		// End of user code
	}

	/**
	 * 
	 */
	protected void createPropertiesGroup(Composite parent) {
		Group propertiesGroup = new Group(parent, SWT.NONE);
		propertiesGroup.setText(ParalleljMessages.WhileLoopPropertiesEditionPart_PropertiesGroupLabel);
		GridData propertiesGroupData = new GridData(GridData.FILL_HORIZONTAL);
		propertiesGroupData.horizontalSpan = 3;
		propertiesGroup.setLayoutData(propertiesGroupData);
		GridLayout propertiesGroupLayout = new GridLayout();
		propertiesGroupLayout.numColumns = 3;
		propertiesGroup.setLayout(propertiesGroupLayout);
		createNameText(propertiesGroup);
		createCapacityText(propertiesGroup);
		createExecutableText(propertiesGroup);
		createPredicateEMFComboViewer(propertiesGroup);
		createJoinEMFComboViewer(propertiesGroup);
		createSplitEMFComboViewer(propertiesGroup);
		createOutputLinksAdvancedTableComposition(propertiesGroup);
		createDescriptionTextarea(propertiesGroup);
	}

	
	protected void createNameText(Composite parent) {
		SWTUtils.createPartLabel(parent, ParalleljMessages.WhileLoopPropertiesEditionPart_NameLabel, propertiesEditionComponent.isRequired(ParalleljViewsRepository.WhileLoop.name, ParalleljViewsRepository.SWT_KIND));
		name = new Text(parent, SWT.BORDER);
		GridData nameData = new GridData(GridData.FILL_HORIZONTAL);
		name.setLayoutData(nameData);
		name.addFocusListener(new FocusAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void focusLost(FocusEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(WhileLoopPropertiesEditionPartImpl.this, ParalleljViewsRepository.WhileLoop.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
			}

		});
		name.addKeyListener(new KeyAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.CR) {
					if (propertiesEditionComponent != null)
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(WhileLoopPropertiesEditionPartImpl.this, ParalleljViewsRepository.WhileLoop.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
				}
			}

		});
		EditingUtils.setID(name, ParalleljViewsRepository.WhileLoop.name);
		EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(ParalleljViewsRepository.WhileLoop.name, ParalleljViewsRepository.SWT_KIND), null); //$NON-NLS-1$
	}


	protected void createCapacityText(Composite parent) {
		SWTUtils.createPartLabel(parent, ParalleljMessages.WhileLoopPropertiesEditionPart_CapacityLabel, propertiesEditionComponent.isRequired(ParalleljViewsRepository.WhileLoop.capacity, ParalleljViewsRepository.SWT_KIND));
		capacity = new Text(parent, SWT.BORDER);
		GridData capacityData = new GridData(GridData.FILL_HORIZONTAL);
		capacity.setLayoutData(capacityData);
		capacity.addFocusListener(new FocusAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void focusLost(FocusEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(WhileLoopPropertiesEditionPartImpl.this, ParalleljViewsRepository.WhileLoop.capacity, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, capacity.getText()));
			}

		});
		capacity.addKeyListener(new KeyAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.CR) {
					if (propertiesEditionComponent != null)
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(WhileLoopPropertiesEditionPartImpl.this, ParalleljViewsRepository.WhileLoop.capacity, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, capacity.getText()));
				}
			}

		});
		EditingUtils.setID(capacity, ParalleljViewsRepository.WhileLoop.capacity);
		EditingUtils.setEEFtype(capacity, "eef::Text"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(ParalleljViewsRepository.WhileLoop.capacity, ParalleljViewsRepository.SWT_KIND), null); //$NON-NLS-1$
	}
	
	protected void createDescriptionTextarea(Composite parent) {
		Label descriptionLabel = SWTUtils.createPartLabel(parent, ParalleljMessages.WhileLoopPropertiesEditionPart_DescriptionLabel, propertiesEditionComponent.isRequired(ParalleljViewsRepository.WhileLoop.description, ParalleljViewsRepository.SWT_KIND));
		GridData descriptionLabelData = new GridData(GridData.FILL_HORIZONTAL);
		descriptionLabelData.horizontalSpan = 3;
		descriptionLabel.setLayoutData(descriptionLabelData);
		description = new Text(parent, SWT.BORDER | SWT.WRAP | SWT.MULTI | SWT.V_SCROLL);
		GridData descriptionData = new GridData(GridData.FILL_HORIZONTAL);
		descriptionData.horizontalSpan = 2;
		descriptionData.heightHint = 80;
		descriptionData.widthHint = 200;
		description.setLayoutData(descriptionData);
		EditingUtils.setID(description, ParalleljViewsRepository.WhileLoop.description);
		EditingUtils.setEEFtype(description, "eef::Textarea"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(ParalleljViewsRepository.WhileLoop.description, ParalleljViewsRepository.SWT_KIND), null); //$NON-NLS-1$
	}

	
	protected void createExecutableText(Composite parent) {
		SWTUtils.createPartLabel(parent, ParalleljMessages.WhileLoopPropertiesEditionPart_ExecutableLabel, propertiesEditionComponent.isRequired(ParalleljViewsRepository.WhileLoop.executable, ParalleljViewsRepository.SWT_KIND));
		executable = new Text(parent, SWT.BORDER);
		GridData executableData = new GridData(GridData.FILL_HORIZONTAL);
		executable.setLayoutData(executableData);
		executable.addFocusListener(new FocusAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void focusLost(FocusEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(WhileLoopPropertiesEditionPartImpl.this, ParalleljViewsRepository.WhileLoop.executable, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, executable.getText()));
			}

		});
		executable.addKeyListener(new KeyAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.CR) {
					if (propertiesEditionComponent != null)
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(WhileLoopPropertiesEditionPartImpl.this, ParalleljViewsRepository.WhileLoop.executable, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, executable.getText()));
				}
			}

		});
		EditingUtils.setID(executable, ParalleljViewsRepository.WhileLoop.executable);
		EditingUtils.setEEFtype(executable, "eef::Text"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(ParalleljViewsRepository.WhileLoop.executable, ParalleljViewsRepository.SWT_KIND), null); //$NON-NLS-1$
	}

	
	protected void createPredicateEMFComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, ParalleljMessages.WhileLoopPropertiesEditionPart_PredicateLabel, propertiesEditionComponent.isRequired(ParalleljViewsRepository.WhileLoop.predicate, ParalleljViewsRepository.SWT_KIND));
		predicate = new EMFComboViewer(parent);
		GridData predicateData = new GridData(GridData.FILL_HORIZONTAL);
		predicate.getCombo().setLayoutData(predicateData);
		predicate.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		predicate.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(WhileLoopPropertiesEditionPartImpl.this, ParalleljViewsRepository.WhileLoop.predicate, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getPredicate()));
			}
			
		});
		predicate.setContentProvider(new EMFListContentProvider(false));
		EditingUtils.setID(predicate.getCombo(), ParalleljViewsRepository.WhileLoop.predicate);
		EditingUtils.setEEFtype(predicate.getCombo(), "eef::Combo"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(ParalleljViewsRepository.WhileLoop.predicate, ParalleljViewsRepository.SWT_KIND), null); //$NON-NLS-1$
	}

	
	protected void createJoinEMFComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, ParalleljMessages.WhileLoopPropertiesEditionPart_JoinLabel, propertiesEditionComponent.isRequired(ParalleljViewsRepository.WhileLoop.join, ParalleljViewsRepository.SWT_KIND));
		join = new EMFComboViewer(parent);
		join.setContentProvider(new ArrayContentProvider());
		join.setLabelProvider(new AdapterFactoryLabelProvider(new EcoreAdapterFactory()));
		GridData joinData = new GridData(GridData.FILL_HORIZONTAL);
		join.getCombo().setLayoutData(joinData);
		join.setID(ParalleljViewsRepository.WhileLoop.join);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(ParalleljViewsRepository.WhileLoop.join, ParalleljViewsRepository.SWT_KIND), null); //$NON-NLS-1$
	}

	
	protected void createSplitEMFComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent, ParalleljMessages.WhileLoopPropertiesEditionPart_SplitLabel, propertiesEditionComponent.isRequired(ParalleljViewsRepository.WhileLoop.split, ParalleljViewsRepository.SWT_KIND));
		split = new EMFComboViewer(parent);
		split.setContentProvider(new ArrayContentProvider());
		split.setLabelProvider(new AdapterFactoryLabelProvider(new EcoreAdapterFactory()));
		GridData splitData = new GridData(GridData.FILL_HORIZONTAL);
		split.getCombo().setLayoutData(splitData);
		split.setID(ParalleljViewsRepository.WhileLoop.split);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(ParalleljViewsRepository.WhileLoop.split, ParalleljViewsRepository.SWT_KIND), null); //$NON-NLS-1$
	}

	/**
	 * @param container
	 * 
	 */
	protected void createOutputLinksAdvancedTableComposition(Composite parent) {
		this.outputLinks = new LinkReferenceTableViewer<Link>("OutputLinks : ", new LinkReferencesListener<Link>() {	
			public void handleMove(Link element, int oldIndex, int newIndex) { moveOutputLinks(element, oldIndex, newIndex); }
			public void navigateTo(Link element) { }
		});
		this.outputLinks.setHelpText(propertiesEditionComponent.getHelpContent(ParalleljViewsRepository.WhileLoop.outputLinks, ParalleljViewsRepository.SWT_KIND));
		this.outputLinks.createControls(parent);
		GridData outputLinksData = new GridData(GridData.FILL_HORIZONTAL);
		outputLinksData.horizontalSpan = 3;
		this.outputLinks.setLayoutData(outputLinksData);
		this.outputLinks.setLowerBound(0);
		this.outputLinks.setUpperBound(-1);
		outputLinks.setID(ParalleljViewsRepository.WhileLoop.outputLinks);
		outputLinks.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
	}

	/**
	 *  
	 */
	protected void moveOutputLinks(Link element, int oldIndex, int newIndex) {
		EObject editedElement = outputLinksEditUtil.foundCorrespondingEObject(element);
		outputLinksEditUtil.moveElement(element, oldIndex, newIndex);
		outputLinks.refresh();
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(WhileLoopPropertiesEditionPartImpl.this, ParalleljViewsRepository.WhileLoop.outputLinks, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, editedElement, newIndex));	
	}

	/**
	 *  
	 */
	protected void addToOutputLinks() {
		// Start of user code addToOutputLinks() method body

				Link eObject = ParallelJFactory.eINSTANCE.createLink();

				IPropertiesEditionPolicyProvider policyProvider = PropertiesEditionPolicyProviderService.getInstance().getProvider(eObject);

				IPropertiesEditionPolicy editionPolicy = policyProvider.getEditionPolicy(eObject);

				if (editionPolicy != null) {

					EObject propertiesEditionObject = editionPolicy.getPropertiesEditionObject(new EObjectPropertiesEditionContext(propertiesEditionComponent, eObject,resourceSet));

					if (propertiesEditionObject != null) {

						outputLinksEditUtil.addElement(propertiesEditionObject);

						outputLinks.refresh();

						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(WhileLoopPropertiesEditionPartImpl.this, ParalleljViewsRepository.WhileLoop.outputLinks, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, propertiesEditionObject));

					}

				}

		

		

		// End of user code
	}

	/**
	 *  
	 */
	protected void removeFromOutputLinks(Link element) {
		// Start of user code removeFromOutputLinks() method body

//				EObject editedElement = outputLinksEditUtil.foundCorrespondingEObject(element);

//				outputLinksEditUtil.removeElement(element);

//				outputLinks.refresh();

//				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(WhileLoopPropertiesEditionPartImpl.this, ParalleljViewsRepository.WhileLoop.outputLinks, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, editedElement));

//				

		// End of user code
	}

	/**
	 *  
	 */
	protected void editOutputLinks(Link element) {
		// Start of user code editOutputLinks() method body

//				EObject editedElement = outputLinksEditUtil.foundCorrespondingEObject(element);

//				IPropertiesEditionPolicyProvider policyProvider = PropertiesEditionPolicyProviderService.getInstance().getProvider(element);

//				IPropertiesEditionPolicy editionPolicy = policyProvider.getEditionPolicy(editedElement);

//				if (editionPolicy != null) {

//					EObject propertiesEditionObject = editionPolicy.getPropertiesEditionObject(new EObjectPropertiesEditionContext(null, element,resourceSet));

//					if (propertiesEditionObject != null) {

//						outputLinksEditUtil.putElementToRefresh(editedElement, propertiesEditionObject);

//						outputLinks.refresh();

//						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(WhileLoopPropertiesEditionPartImpl.this, ParalleljViewsRepository.WhileLoop.outputLinks, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, editedElement, propertiesEditionObject));

//					}

//				}

//				

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
	 * @see org.parallelj.parts.WhileLoopPropertiesEditionPart#getName()
	 * 
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.WhileLoopPropertiesEditionPart#setName(String newValue)
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
	 * @see org.parallelj.parts.WhileLoopPropertiesEditionPart#getDescription()
	 * 
	 */
	public String getDescription() {
		return description.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.WhileLoopPropertiesEditionPart#setDescription(String newValue)
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
	 * @see org.parallelj.parts.WhileLoopPropertiesEditionPart#getExecutable()
	 * 
	 */
	public String getExecutable() {
		return executable.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.WhileLoopPropertiesEditionPart#setExecutable(String newValue)
	 * 
	 */
	public void setExecutable(String newValue) {
		if (newValue != null) {
			executable.setText(newValue);
		} else {
			executable.setText(""); //$NON-NLS-1$
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.WhileLoopPropertiesEditionPart#getPredicate()
	 * 
	 */
	public Object getPredicate() {
		if (predicate.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) predicate.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return "";
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.WhileLoopPropertiesEditionPart#initPredicate(ResourceSet allResources, EObject current)
	 */
	public void initPredicate(ResourceSet allResources, EObject current) {
		predicate.setInput(allResources);
		if (current != null) {
			predicate.setSelection(new StructuredSelection(current));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.WhileLoopPropertiesEditionPart#setPredicate(Object newValue)
	 * 
	 */
	public void setPredicate(Object newValue) {
		if (newValue != null) {
			predicate.modelUpdating(new StructuredSelection(newValue));
		} else {
			predicate.modelUpdating(new StructuredSelection("")); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.WhileLoopPropertiesEditionPart#addFilterPredicate(ViewerFilter filter)
	 * 
	 */
	public void addFilterToPredicate(ViewerFilter filter) {
		predicate.addFilter(filter);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.WhileLoopPropertiesEditionPart#getJoin()
	 * 
	 */
	public Enumerator getJoin() {
		EEnumLiteral selection = (EEnumLiteral) ((StructuredSelection) join.getSelection()).getFirstElement();
		return selection.getInstance();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.WhileLoopPropertiesEditionPart#initJoin(EEnum eenum, Enumerator current)
	 */
	public void initJoin(EEnum eenum, Enumerator current) {
		join.setInput(eenum.getELiterals());
		join.modelUpdating(new StructuredSelection(current));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.WhileLoopPropertiesEditionPart#setJoin(Enumerator newValue)
	 * 
	 */
	public void setJoin(Enumerator newValue) {
		join.modelUpdating(new StructuredSelection(newValue));
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.WhileLoopPropertiesEditionPart#getSplit()
	 * 
	 */
	public Enumerator getSplit() {
		EEnumLiteral selection = (EEnumLiteral) ((StructuredSelection) split.getSelection()).getFirstElement();
		return selection.getInstance();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.WhileLoopPropertiesEditionPart#initSplit(EEnum eenum, Enumerator current)
	 */
	public void initSplit(EEnum eenum, Enumerator current) {
		split.setInput(eenum.getELiterals());
		split.modelUpdating(new StructuredSelection(current));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.WhileLoopPropertiesEditionPart#setSplit(Enumerator newValue)
	 * 
	 */
	public void setSplit(Enumerator newValue) {
		split.modelUpdating(new StructuredSelection(newValue));
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.ProgramPropertiesEditionPart#getCapacity()
	 * 
	 */
	public String getCapacity() {
		return capacity.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.ProgramPropertiesEditionPart#setCapacity(String newValue)
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
	 * @see org.parallelj.parts.WhileLoopPropertiesEditionPart#getOutputLinksToAdd()
	 * 
	 */
	public List getOutputLinksToAdd() {
		return outputLinksEditUtil.getElementsToAdd();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.WhileLoopPropertiesEditionPart#getOutputLinksToRemove()
	 * 
	 */
	public List getOutputLinksToRemove() {
		return outputLinksEditUtil.getElementsToRemove();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.WhileLoopPropertiesEditionPart#getOutputLinksToEdit()
	 * 
	 */
	public Map getOutputLinksToEdit() {
		return outputLinksEditUtil.getElementsToRefresh();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.WhileLoopPropertiesEditionPart#getOutputLinksToMove()
	 * 
	 */
	public List getOutputLinksToMove() {
		return outputLinksEditUtil.getElementsToMove();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.WhileLoopPropertiesEditionPart#getOutputLinksTable()
	 * 
	 */
	public List getOutputLinksTable() {
		return outputLinksEditUtil.getVirtualList();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.WhileLoopPropertiesEditionPart#initOutputLinks(EObject current, EReference containingFeature, EReference feature)
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
	 * @see org.parallelj.parts.WhileLoopPropertiesEditionPart#updateOutputLinks(EObject newValue)
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
	 * @see org.parallelj.parts.WhileLoopPropertiesEditionPart#addFilterOutputLinks(ViewerFilter filter)
	 * 
	 */
	public void addFilterToOutputLinks(ViewerFilter filter) {
		outputLinksFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.WhileLoopPropertiesEditionPart#addBusinessFilterOutputLinks(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToOutputLinks(ViewerFilter filter) {
		outputLinksBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.WhileLoopPropertiesEditionPart#isContainedInOutputLinksTable(EObject element)
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
		return ParalleljMessages.WhileLoop_Part_Title;
	}

	// Start of user code additional methods
	
	// End of user code


}
