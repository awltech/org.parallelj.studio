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
import org.eclipse.emf.eef.runtime.ui.utils.EditingUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.EMFComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;
import org.eclipse.emf.eef.runtime.ui.widgets.SWTUtils;
import org.eclipse.jface.viewers.ArrayContentProvider;
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
import org.eclipse.swt.widgets.Text;
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
public class PipelinePropertiesEditionPartImpl extends
		CompositePropertiesEditionPart implements ISWTPropertiesEditionPart,
		PipelinePropertiesEditionPart {

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
	protected LinkReferenceTableViewer<Link> outputLinks;
	protected List<ViewerFilter> outputLinksBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> outputLinksFilters = new ArrayList<ViewerFilter>();

	
	/**
	 * Default constructor
	 * 
	 * @param editionComponent
	 *            the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public PipelinePropertiesEditionPartImpl(
			IPropertiesEditionComponent editionComponent) {
		super(editionComponent);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart#
	 *      createFigure(org.eclipse.swt.widgets.Composite)
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
	 *      createControls(org.eclipse.swt.widgets.Composite)
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
		propertiesGroup
				.setText(ParalleljMessages.PipelinePropertiesEditionPart_PropertiesGroupLabel);
		GridData propertiesGroupData = new GridData(GridData.FILL_HORIZONTAL);
		propertiesGroupData.horizontalSpan = 3;
		propertiesGroup.setLayoutData(propertiesGroupData);
		GridLayout propertiesGroupLayout = new GridLayout();
		propertiesGroupLayout.numColumns = 3;
		propertiesGroup.setLayout(propertiesGroupLayout);
		createNameText(propertiesGroup);
		createCapacityText(propertiesGroup);
		createJoinEMFComboViewer(propertiesGroup);
		createSplitEMFComboViewer(propertiesGroup);
		createProceduresAdvancedTableComposition(propertiesGroup);
		createOutputLinksAdvancedTableComposition(propertiesGroup);
		createDescriptionText(propertiesGroup);
	}

	protected void createNameText(Composite parent) {
		SWTUtils.createPartLabel(parent,
				ParalleljMessages.PipelinePropertiesEditionPart_NameLabel,
				propertiesEditionComponent.isRequired(
						ParalleljViewsRepository.Pipeline.name,
						ParalleljViewsRepository.SWT_KIND));
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
					propertiesEditionComponent
							.firePropertiesChanged(new PropertiesEditionEvent(
									PipelinePropertiesEditionPartImpl.this,
									ParalleljViewsRepository.Pipeline.name,
									PropertiesEditionEvent.COMMIT,
									PropertiesEditionEvent.SET, null, name
											.getText()));
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
						propertiesEditionComponent
								.firePropertiesChanged(new PropertiesEditionEvent(
										PipelinePropertiesEditionPartImpl.this,
										ParalleljViewsRepository.Pipeline.name,
										PropertiesEditionEvent.COMMIT,
										PropertiesEditionEvent.SET, null, name
												.getText()));
				}
			}

		});
		EditingUtils.setID(name, ParalleljViewsRepository.Pipeline.name);
		EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent
				.getHelpContent(ParalleljViewsRepository.Pipeline.name,
						ParalleljViewsRepository.SWT_KIND), null); //$NON-NLS-1$
	}

	protected void createCapacityText(Composite parent) {
		SWTUtils.createPartLabel(parent, ParalleljMessages.PipelinePropertiesEditionPart_CapacityLabel, propertiesEditionComponent.isRequired(ParalleljViewsRepository.Pipeline.capacity, ParalleljViewsRepository.SWT_KIND));
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PipelinePropertiesEditionPartImpl.this, ParalleljViewsRepository.Pipeline.capacity, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, capacity.getText()));
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
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PipelinePropertiesEditionPartImpl.this, ParalleljViewsRepository.Pipeline.capacity, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, capacity.getText()));
				}
			}

		});
		EditingUtils.setID(capacity, ParalleljViewsRepository.Pipeline.capacity);
		EditingUtils.setEEFtype(capacity, "eef::Text"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(ParalleljViewsRepository.Pipeline.capacity, ParalleljViewsRepository.SWT_KIND), null); //$NON-NLS-1$
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
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PipelinePropertiesEditionPartImpl.this, ParalleljViewsRepository.WhileLoop.outputLinks, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, editedElement, newIndex));	
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

						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(PipelinePropertiesEditionPartImpl.this, ParalleljViewsRepository.WhileLoop.outputLinks, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, propertiesEditionObject));

					}

				}

		

		

		// End of user code
	}

	/**
	 *  
	 */
	protected void removeFromOutputLinks(Link element) {
		// Start of user code removeFromOutputLinks() method body

		// End of user code
	}

	/**
	 *  
	 */
	protected void editOutputLinks(Link element) {
		// Start of user code editOutputLinks() method body

		// End of user code
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



	
	protected void createDescriptionText(Composite parent) {
		SWTUtils.createPartLabel(
				parent,
				ParalleljMessages.PipelinePropertiesEditionPart_DescriptionLabel,
				propertiesEditionComponent.isRequired(
						ParalleljViewsRepository.Pipeline.description,
						ParalleljViewsRepository.SWT_KIND));
		description = new Text(parent, SWT.BORDER);
		GridData descriptionData = new GridData(GridData.FILL_HORIZONTAL);
		description.setLayoutData(descriptionData);
		description.addFocusListener(new FocusAdapter() {

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
					propertiesEditionComponent
							.firePropertiesChanged(new PropertiesEditionEvent(
									PipelinePropertiesEditionPartImpl.this,
									ParalleljViewsRepository.Pipeline.description,
									PropertiesEditionEvent.COMMIT,
									PropertiesEditionEvent.SET, null,
									description.getText()));
			}

		});
		description.addKeyListener(new KeyAdapter() {

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
						propertiesEditionComponent
								.firePropertiesChanged(new PropertiesEditionEvent(
										PipelinePropertiesEditionPartImpl.this,
										ParalleljViewsRepository.Pipeline.description,
										PropertiesEditionEvent.COMMIT,
										PropertiesEditionEvent.SET, null,
										description.getText()));
				}
			}

		});
		EditingUtils.setID(description,
				ParalleljViewsRepository.Pipeline.description);
		EditingUtils.setEEFtype(description, "eef::Text"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent
				.getHelpContent(ParalleljViewsRepository.Pipeline.description,
						ParalleljViewsRepository.SWT_KIND), null); //$NON-NLS-1$
	}

	protected void createJoinEMFComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent,
				ParalleljMessages.PipelinePropertiesEditionPart_JoinLabel,
				propertiesEditionComponent.isRequired(
						ParalleljViewsRepository.Pipeline.join,
						ParalleljViewsRepository.SWT_KIND));
		join = new EMFComboViewer(parent);
		join.setContentProvider(new ArrayContentProvider());
		join.setLabelProvider(new AdapterFactoryLabelProvider(
				new EcoreAdapterFactory()));
		GridData joinData = new GridData(GridData.FILL_HORIZONTAL);
		join.getCombo().setLayoutData(joinData);
		join.setID(ParalleljViewsRepository.Pipeline.join);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent
				.getHelpContent(ParalleljViewsRepository.Pipeline.join,
						ParalleljViewsRepository.SWT_KIND), null); //$NON-NLS-1$
	}

	protected void createSplitEMFComboViewer(Composite parent) {
		SWTUtils.createPartLabel(parent,
				ParalleljMessages.PipelinePropertiesEditionPart_SplitLabel,
				propertiesEditionComponent.isRequired(
						ParalleljViewsRepository.Pipeline.split,
						ParalleljViewsRepository.SWT_KIND));
		split = new EMFComboViewer(parent);
		split.setContentProvider(new ArrayContentProvider());
		split.setLabelProvider(new AdapterFactoryLabelProvider(
				new EcoreAdapterFactory()));
		GridData splitData = new GridData(GridData.FILL_HORIZONTAL);
		split.getCombo().setLayoutData(splitData);
		split.setID(ParalleljViewsRepository.Pipeline.split);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent
				.getHelpContent(ParalleljViewsRepository.Pipeline.split,
						ParalleljViewsRepository.SWT_KIND), null); //$NON-NLS-1$
	}

	/**
	 * @param container
	 * 
	 */
	protected void createProceduresAdvancedTableComposition(Composite parent) {
		this.procedures = new ReferencesTable<Procedure>(
				ParalleljMessages.PipelinePropertiesEditionPart_ProceduresLabel,
				new ReferencesTableListener<Procedure>() {
					public void handleAdd() {
						addToProcedures();
					}

					public void handleEdit(Procedure element) {
						editProcedures(element);
					}

					public void handleMove(Procedure element, int oldIndex,
							int newIndex) {
						moveProcedures(element, oldIndex, newIndex);
					}

					public void handleRemove(Procedure element) {
						removeFromProcedures(element);
					}

					public void navigateTo(Procedure element) {
					}
				});
		this.procedures.setHelpText(propertiesEditionComponent.getHelpContent(
				ParalleljViewsRepository.Pipeline.procedures,
				ParalleljViewsRepository.SWT_KIND));
		this.procedures.createControls(parent);
		GridData proceduresData = new GridData(GridData.FILL_HORIZONTAL);
		proceduresData.horizontalSpan = 3;
		this.procedures.setLayoutData(proceduresData);
		this.procedures.setLowerBound(0);
		this.procedures.setUpperBound(-1);
		procedures.setID(ParalleljViewsRepository.Pipeline.procedures);
		procedures.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
	}

	/**
	 *  
	 */
	protected void moveProcedures(Procedure element, int oldIndex, int newIndex) {
		EObject editedElement = proceduresEditUtil
				.foundCorrespondingEObject(element);
		proceduresEditUtil.moveElement(element, oldIndex, newIndex);
		procedures.refresh();
		propertiesEditionComponent
				.firePropertiesChanged(new PropertiesEditionEvent(
						PipelinePropertiesEditionPartImpl.this,
						ParalleljViewsRepository.Pipeline.procedures,
						PropertiesEditionEvent.COMMIT,
						PropertiesEditionEvent.MOVE, editedElement, newIndex));
	}

	/**
	 *  
	 */
	protected void addToProcedures() {
		// Start of user code addToProcedures() method body
		Procedure eObject = ParallelJFactory.eINSTANCE.createProcedure();
		IPropertiesEditionPolicyProvider policyProvider = PropertiesEditionPolicyProviderService
				.getInstance().getProvider(eObject);
		IPropertiesEditionPolicy editionPolicy = policyProvider
				.getEditionPolicy(eObject);
		if (editionPolicy != null) {
			EObject propertiesEditionObject = editionPolicy
					.getPropertiesEditionObject(new EObjectPropertiesEditionContext(
							propertiesEditionComponent, eObject, resourceSet));
			if (propertiesEditionObject != null) {
				proceduresEditUtil.addElement(propertiesEditionObject);
				procedures.refresh();
				propertiesEditionComponent
						.firePropertiesChanged(new PropertiesEditionEvent(
								PipelinePropertiesEditionPartImpl.this,
								ParalleljViewsRepository.Pipeline.procedures,
								PropertiesEditionEvent.COMMIT,
								PropertiesEditionEvent.ADD, null,
								propertiesEditionObject));
			}
		}

		// End of user code
	}

	/**
	 *  
	 */
	protected void removeFromProcedures(Procedure element) {
		// Start of user code removeFromProcedures() method body
		EObject editedElement = proceduresEditUtil
				.foundCorrespondingEObject(element);
		proceduresEditUtil.removeElement(element);
		procedures.refresh();
		propertiesEditionComponent
				.firePropertiesChanged(new PropertiesEditionEvent(
						PipelinePropertiesEditionPartImpl.this,
						ParalleljViewsRepository.Pipeline.procedures,
						PropertiesEditionEvent.CHANGE,
						PropertiesEditionEvent.REMOVE, null, editedElement));
		// End of user code
	}

	/**
	 *  
	 */
	protected void editProcedures(Procedure element) {
		// Start of user code editProcedures() method body
		EObject editedElement = proceduresEditUtil
				.foundCorrespondingEObject(element);
		IPropertiesEditionPolicyProvider policyProvider = PropertiesEditionPolicyProviderService
				.getInstance().getProvider(element);
		IPropertiesEditionPolicy editionPolicy = policyProvider
				.getEditionPolicy(editedElement);
		if (editionPolicy != null) {
			EObject propertiesEditionObject = editionPolicy
					.getPropertiesEditionObject(new EObjectPropertiesEditionContext(
							null, element, resourceSet));
			if (propertiesEditionObject != null) {
				proceduresEditUtil.putElementToRefresh(editedElement,
						propertiesEditionObject);
				procedures.refresh();
				propertiesEditionComponent
						.firePropertiesChanged(new PropertiesEditionEvent(
								PipelinePropertiesEditionPartImpl.this,
								ParalleljViewsRepository.Pipeline.procedures,
								PropertiesEditionEvent.CHANGE,
								PropertiesEditionEvent.SET, editedElement,
								propertiesEditionObject));
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
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#setName(String
	 *      newValue)
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
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#setDescription(String
	 *      newValue)
	 * 
	 */
	public void setDescription(String newValue) {
		if (newValue != null) {
			description.setText(newValue);
		} else {
			description.setText(""); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#getJoin()
	 * 
	 */
	public Enumerator getJoin() {
		EEnumLiteral selection = (EEnumLiteral) ((StructuredSelection) join
				.getSelection()).getFirstElement();
		return selection.getInstance();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#initJoin(EEnum
	 *      eenum, Enumerator current)
	 */
	public void initJoin(EEnum eenum, Enumerator current) {
		join.setInput(eenum.getELiterals());
		join.modelUpdating(new StructuredSelection(current));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#setJoin(Enumerator
	 *      newValue)
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
		EEnumLiteral selection = (EEnumLiteral) ((StructuredSelection) split
				.getSelection()).getFirstElement();
		return selection.getInstance();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#initSplit(EEnum
	 *      eenum, Enumerator current)
	 */
	public void initSplit(EEnum eenum, Enumerator current) {
		split.setInput(eenum.getELiterals());
		split.modelUpdating(new StructuredSelection(current));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#setSplit(Enumerator
	 *      newValue)
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
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#initProcedures(EObject
	 *      current, EReference containingFeature, EReference feature)
	 */
	public void initProcedures(EObject current, EReference containingFeature,
			EReference feature) {
		if (current.eResource() != null
				&& current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		if (containingFeature != null)
			proceduresEditUtil = new EMFListEditUtil(current,
					containingFeature, feature);
		else
			proceduresEditUtil = new EMFListEditUtil(current, feature);
		this.procedures.setInput(proceduresEditUtil.getVirtualList());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#updateProcedures(EObject
	 *      newValue)
	 * 
	 */
	public void updateProcedures(EObject newValue) {
		if (proceduresEditUtil != null) {
			proceduresEditUtil.reinit(newValue);
			procedures.refresh();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#addFilterProcedures(ViewerFilter
	 *      filter)
	 * 
	 */
	public void addFilterToProcedures(ViewerFilter filter) {
		proceduresFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#addBusinessFilterProcedures(ViewerFilter
	 *      filter)
	 * 
	 */
	public void addBusinessFilterToProcedures(ViewerFilter filter) {
		proceduresBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.PipelinePropertiesEditionPart#isContainedInProceduresTable(EObject
	 *      element)
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
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * 
	 */
	public String getTitle() {
		return ParalleljMessages.Pipeline_Part_Title;
	}

	// Start of user code additional methods

	// End of user code

}
