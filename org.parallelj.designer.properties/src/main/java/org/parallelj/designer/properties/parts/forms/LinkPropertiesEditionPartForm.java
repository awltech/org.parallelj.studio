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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.impl.parts.CompositePropertiesEditionPart;
import org.eclipse.emf.eef.runtime.ui.providers.EMFListContentProvider;
import org.eclipse.emf.eef.runtime.ui.utils.EditingUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.EMFComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.FormUtils;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.parallelj.designer.properties.parts.LinkPropertiesEditionPart;
import org.parallelj.designer.properties.parts.ParalleljViewsRepository;
import org.parallelj.designer.properties.providers.ParalleljMessages;
import org.parallelj.model.Element;
import org.parallelj.model.Link;
import org.parallelj.model.Program;
import org.parallelj.model.Specification;


// End of user code

/**
 * @author
 * 
 */
public class LinkPropertiesEditionPartForm extends CompositePropertiesEditionPart implements IFormPropertiesEditionPart, LinkPropertiesEditionPart {

	protected EMFComboViewer predicate;
	protected Text description;


	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public LinkPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
		propertiesSection.setText(ParalleljMessages.LinkPropertiesEditionPart_PropertiesGroupLabel);
		GridData propertiesSectionData = new GridData(GridData.FILL_HORIZONTAL);
		propertiesSectionData.horizontalSpan = 3;
		propertiesSection.setLayoutData(propertiesSectionData);
		Composite propertiesGroup = widgetFactory.createComposite(propertiesSection);
		GridLayout propertiesGroupLayout = new GridLayout();
		propertiesGroupLayout.numColumns = 3;
		propertiesGroup.setLayout(propertiesGroupLayout);
		createPredicateEMFComboViewer(widgetFactory, propertiesGroup);
		createDescriptionTextarea(widgetFactory, propertiesGroup);
		propertiesSection.setClient(propertiesGroup);
	}


	protected void createPredicateEMFComboViewer(FormToolkit widgetFactory, Composite parent) {
		FormUtils.createPartLabel(widgetFactory, parent, ParalleljMessages.LinkPropertiesEditionPart_PredicateLabel, false);
		predicate = new EMFComboViewer(parent);
		GridData predicateData = new GridData(GridData.FILL_HORIZONTAL);
		predicate.getCombo().setLayoutData(predicateData);
		predicate.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		predicate.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 * 	
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LinkPropertiesEditionPartForm.this, ParalleljViewsRepository.Link.predicate, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getPredicate()));
			}

		});
		predicate.setContentProvider(new EMFListContentProvider(true));
		EditingUtils.setID(predicate.getCombo(), ParalleljViewsRepository.Link.predicate);
		EditingUtils.setEEFtype(predicate.getCombo(), "eef::Combo");
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(ParalleljViewsRepository.Link.predicate, ParalleljViewsRepository.FORM_KIND), null); //$NON-NLS-1$
	}


	protected void createDescriptionTextarea(FormToolkit widgetFactory, Composite parent) {
		Label descriptionLabel = FormUtils.createPartLabel(widgetFactory, parent, ParalleljMessages.LinkPropertiesEditionPart_DescriptionLabel, propertiesEditionComponent.isRequired(ParalleljViewsRepository.Link.description, ParalleljViewsRepository.FORM_KIND));
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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(LinkPropertiesEditionPartForm.this, ParalleljViewsRepository.Link.description, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, description.getText()));
			}

		});
		EditingUtils.setID(description, ParalleljViewsRepository.Link.description);
		EditingUtils.setEEFtype(description, "eef::Textarea"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(ParalleljViewsRepository.Link.description, ParalleljViewsRepository.FORM_KIND), null); //$NON-NLS-1$
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
	 * @see org.parallelj.parts.LinkPropertiesEditionPart#getPredicate()
	 * 
	 */
	public Object getPredicate() {
		if (predicate.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) predicate.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		//TODO: Made changes to reflect the null value selected in the combo list
		// Start of user code additional methods
		return null;
		// End of user code

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.LinkPropertiesEditionPart#initPredicate(ResourceSet allResources, EObject current)
	 */
	public void initPredicate(ResourceSet allResources, EObject current) {
		//predicate.setInput(allResources);
		
		ArrayList<EObject> contents = new ArrayList<EObject>();
		if (this.current != null) {
			for (Resource resource : allResources.getResources()) {
				for (EObject eObject : resource.getContents()) {
					if (eObject instanceof Specification) {
						Specification specification = (Specification) eObject;
						EList<Program> programs = specification.getPrograms();

						for (Program program : programs) {
							EList<Element> elements = program.getElements();
							for (Element element : elements) {
								EList<Link> inputlinks = element
										.getInputLinks();
								for (Link inputLink : inputlinks) {
									if (inputLink.hashCode() == this.current
											.hashCode()) {
										contents.add(program);
									}
								}
								EList<Link> outputlinks = element
										.getOutputLinks();
								for (Link outputLink : outputlinks) {
									if (outputLink.hashCode() == this.current
											.hashCode()) {
										contents.add(program);
									}
								}

							}
						}
					}
				}
			}
			if (contents.size() > 0) {
				Program program = ((Program) contents.get(0));
				predicate.setInput(program);
			}
		}
		if (current != null) {
			predicate.setSelection(new StructuredSelection(current));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.LinkPropertiesEditionPart#setPredicate(Object newValue)
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
	 * @see org.parallelj.parts.LinkPropertiesEditionPart#addFilterPredicate(ViewerFilter filter)
	 * 
	 */
	public void addFilterToPredicate(ViewerFilter filter) {
		predicate.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.LinkPropertiesEditionPart#getDescription()
	 * 
	 */
	public String getDescription() {
		return description.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.parallelj.parts.LinkPropertiesEditionPart#setDescription(String newValue)
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
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * 
	 */
	public String getTitle() {
		return ParalleljMessages.Link_Part_Title;
	}

	// Start of user code additional methods

	// End of user code


}
