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
package org.parallelj.components;

// Start of user code for imports
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.eef.runtime.EEFRuntimePlugin;
import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionListener;
import org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart;
import org.eclipse.emf.eef.runtime.api.providers.IPropertiesEditionPartProvider;
import org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.impl.filters.EObjectFilter;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesValidationEditionEvent;
import org.eclipse.emf.eef.runtime.impl.services.PropertiesEditionPartProviderService;
import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.parallelj.designer.properties.parts.ParalleljViewsRepository;
import org.parallelj.designer.properties.parts.PipelinePropertiesEditionPart;
import org.parallelj.model.JoinType;
import org.parallelj.model.Link;
import org.parallelj.model.ParallelJPackage;
import org.parallelj.model.Pipeline;
import org.parallelj.model.Procedure;
import org.parallelj.model.SplitType;
	

// End of user code

/**
 * @author
 * 
 */
public class PipelinePropertiesEditionComponent extends StandardPropertiesEditionComponent {

	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	private String[] parts = {BASE_PART};

	/**
	 * The EObject to edit
	 * 
	 */
	private Pipeline pipeline;

	/**
	 * The Base part
	 * 
	 */
	protected PipelinePropertiesEditionPart basePart;

	/**
	 * Default constructor
	 * 
	 */
	public PipelinePropertiesEditionComponent(EObject pipeline, String editing_mode) {
		if (pipeline instanceof Pipeline) {
			this.pipeline = (Pipeline)pipeline;
			if (IPropertiesEditionComponent.LIVE_MODE.equals(editing_mode)) {
				semanticAdapter = initializeSemanticAdapter();
				this.pipeline.eAdapters().add(semanticAdapter);
			}
		}
		this.editing_mode = editing_mode;
	}

	/**
	 * Initialize the semantic model listener for live editing mode
	 * 
	 * @return the semantic model listener
	 * 
	 */
	private AdapterImpl initializeSemanticAdapter() {
		return new EContentAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
			 * 
			 */
			public void notifyChanged(final Notification msg) {
				if (basePart == null)
					PipelinePropertiesEditionComponent.this.dispose();
				else {
					Runnable updateRunnable = new Runnable() {
						public void run() {
							runUpdateRunnable(msg);
						}
					};
					if (null == Display.getCurrent()) {
						PlatformUI.getWorkbench().getDisplay().syncExec(updateRunnable);
					} else {
						updateRunnable.run();
					}
				}
			}

		};
	}

	/**
	 * Used to update the views
	 * 
	 */
	protected void runUpdateRunnable(final Notification msg) {
		if (ParallelJPackage.eINSTANCE.getNamedElement_Name().equals(msg.getFeature()) && basePart != null){
			if (msg.getNewValue() != null) {
				basePart.setName(EcoreUtil.convertToString(EcorePackage.eINSTANCE.getEString(), msg.getNewValue()));
			} else {
				basePart.setName("");
			}
		}
		if (ParallelJPackage.eINSTANCE.getNamedElement_Description().equals(msg.getFeature()) && basePart != null){
			if (msg.getNewValue() != null) {
				basePart.setDescription(EcoreUtil.convertToString(EcorePackage.eINSTANCE.getEString(), msg.getNewValue()));
			} else {
				basePart.setDescription("");
			}
		}
		if (ParallelJPackage.eINSTANCE.getProcedure_Capacity().equals(msg.getFeature()) && basePart != null){
			if (msg.getNewValue() != null) {
				basePart.setCapacity(EcoreUtil.convertToString(EcorePackage.eINSTANCE.getEInt(), msg.getNewValue()));
			} else {
				basePart.setCapacity("");
			}
		}
		if (ParallelJPackage.eINSTANCE.getProcedure_Join().equals(msg.getFeature()) && basePart != null)
			basePart.setJoin((Enumerator)msg.getNewValue());

		if (ParallelJPackage.eINSTANCE.getProcedure_Split().equals(msg.getFeature()) && basePart != null)
			basePart.setSplit((Enumerator)msg.getNewValue());

		if (msg.getFeature() != null && ((EStructuralFeature)msg.getFeature() == ParallelJPackage.eINSTANCE.getPipeline_Procedures())) {
			basePart.updateProcedures(pipeline);
		}
		
		if (msg.getFeature() != null && ((EStructuralFeature)msg.getFeature() == ParallelJPackage.eINSTANCE.getElement_OutputLinks())) {
			basePart.updateOutputLinks(pipeline);
		}

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#translatePart(java.lang.String)
	 * 
	 */
	public java.lang.Class translatePart(String key) {
		if (BASE_PART.equals(key))
			return ParalleljViewsRepository.Pipeline.class;
		return super.translatePart(key);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#partsList()
	 * 
	 */
	public String[] partsList() {
		return parts;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#getPropertiesEditionPart
	 *  (java.lang.String, java.lang.String)
	 * 
	 */
	public IPropertiesEditionPart getPropertiesEditionPart(int kind, String key) {
		if (pipeline != null && BASE_PART.equals(key)) {
			if (basePart == null) {
				IPropertiesEditionPartProvider provider = PropertiesEditionPartProviderService.getInstance().getProvider(ParalleljViewsRepository.class);
				if (provider != null) {
					basePart = (PipelinePropertiesEditionPart)provider.getPropertiesEditionPart(ParalleljViewsRepository.Pipeline.class, kind, this);
					addListener((IPropertiesEditionListener)basePart);
				}
			}
			return (IPropertiesEditionPart)basePart;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#
	 *      setPropertiesEditionPart(java.lang.Class, int, org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart)
	 * 
	 */
	public void setPropertiesEditionPart(java.lang.Class key, int kind, IPropertiesEditionPart propertiesEditionPart) {
		if (key == ParalleljViewsRepository.Pipeline.class)
			this.basePart = (PipelinePropertiesEditionPart) propertiesEditionPart;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#initPart(java.lang.Class, int, org.eclipse.emf.ecore.EObject, 
	 *      org.eclipse.emf.ecore.resource.ResourceSet)
	 * 
	 */
	public void initPart(java.lang.Class key, int kind, EObject elt, ResourceSet allResource) {
		setInitializing(true);
		if (basePart != null && key == ParalleljViewsRepository.Pipeline.class) {
			((IPropertiesEditionPart)basePart).setContext(elt, allResource);
			final Pipeline pipeline = (Pipeline)elt;
			// init values
			if (pipeline.getName() != null)
				basePart.setName(EEFConverterUtil.convertToString(EcorePackage.eINSTANCE.getEString(), pipeline.getName()));

			if (pipeline.getDescription() != null)
				basePart.setDescription(EEFConverterUtil.convertToString(EcorePackage.eINSTANCE.getEString(), pipeline.getDescription()));

			basePart.initJoin((EEnum) ParallelJPackage.eINSTANCE.getProcedure_Join().getEType(), pipeline.getJoin());
			basePart.initSplit((EEnum) ParallelJPackage.eINSTANCE.getProcedure_Split().getEType(), pipeline.getSplit());
			basePart.initProcedures(pipeline, null, ParallelJPackage.eINSTANCE.getPipeline_Procedures());
			// init filters


			basePart.setCapacity(EEFConverterUtil.convertToString(EcorePackage.eINSTANCE.getEInt(), pipeline.getCapacity()));



			basePart.addFilterToProcedures(new ViewerFilter() {

					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof String && element.equals("")) || (element instanceof Procedure); //$NON-NLS-1$ 
				}

			});
			
			// Start of user code for additional businessfilters for procedures
			basePart.addFilterToProcedures(new EObjectFilter(ParallelJPackage.eINSTANCE.getProcedure()));
			// End of user code

			basePart.initOutputLinks(pipeline, null, ParallelJPackage.eINSTANCE.getElement_OutputLinks());
			basePart.addFilterToOutputLinks(new ViewerFilter() {

				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					return (element instanceof String && element.equals("")) || (element instanceof Link); //$NON-NLS-1$ 
			}

		});
			
		}
		// init values for referenced views

		// init filters for referenced views

		setInitializing(false);
	}








	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#getPropertiesEditionCommand
	 *     (org.eclipse.emf.edit.domain.EditingDomain)
	 * 
	 */
	public CompoundCommand getPropertiesEditionCommand(EditingDomain editingDomain) {
		CompoundCommand cc = new CompoundCommand();
		if ((pipeline != null) && (basePart != null)) { 
			cc.append(SetCommand.create(editingDomain, pipeline, ParallelJPackage.eINSTANCE.getNamedElement_Name(), EEFConverterUtil.createFromString(EcorePackage.eINSTANCE.getEString(), basePart.getName())));
			cc.append(SetCommand.create(editingDomain, pipeline, ParallelJPackage.eINSTANCE.getNamedElement_Description(), EEFConverterUtil.createFromString(EcorePackage.eINSTANCE.getEString(), basePart.getDescription())));
			cc.append(SetCommand.create(editingDomain, pipeline, ParallelJPackage.eINSTANCE.getProcedure_Join(), basePart.getJoin()));

			cc.append(SetCommand.create(editingDomain, pipeline, ParallelJPackage.eINSTANCE.getProcedure_Split(), basePart.getSplit()));

			cc.append(SetCommand.create(editingDomain, pipeline, ParallelJPackage.eINSTANCE.getProcedure_Capacity(), EEFConverterUtil.createFromString(EcorePackage.eINSTANCE.getEInt(), basePart.getCapacity())));
			
			List proceduresToAddFromProcedures = basePart.getProceduresToAdd();
			for (Iterator iter = proceduresToAddFromProcedures.iterator(); iter.hasNext();)
				cc.append(AddCommand.create(editingDomain, pipeline, ParallelJPackage.eINSTANCE.getPipeline_Procedures(), iter.next()));
			Map proceduresToRefreshFromProcedures = basePart.getProceduresToEdit();
			for (Iterator iter = proceduresToRefreshFromProcedures.keySet().iterator(); iter.hasNext();) {
				Procedure nextElement = (Procedure) iter.next();
				Procedure procedures = (Procedure) proceduresToRefreshFromProcedures.get(nextElement);
				for (EStructuralFeature feature : nextElement.eClass().getEAllStructuralFeatures()) {
					if (feature.isChangeable() && !(feature instanceof EReference && ((EReference) feature).isContainer())) {
						cc.append(SetCommand.create(editingDomain, nextElement, feature, procedures.eGet(feature)));
					}
				}
			}
			List proceduresToRemoveFromProcedures = basePart.getProceduresToRemove();
			for (Iterator iter = proceduresToRemoveFromProcedures.iterator(); iter.hasNext();)
				//cc.append(DeleteCommand.create(editingDomain, iter.next()));
				cc.append(RemoveCommand.create(editingDomain, pipeline, ParallelJPackage.eINSTANCE.getPipeline_Procedures(), iter.next()));
			
			List proceduresToMoveFromProcedures = basePart.getProceduresToMove();
			for (Iterator iter = proceduresToMoveFromProcedures.iterator(); iter.hasNext();){
				org.eclipse.emf.eef.runtime.impl.utils.EMFListEditUtil.MoveElement moveElement = (org.eclipse.emf.eef.runtime.impl.utils.EMFListEditUtil.MoveElement)iter.next();
				cc.append(MoveCommand.create(editingDomain, pipeline, ParallelJPackage.eINSTANCE.getProcedure(), moveElement.getElement(), moveElement.getIndex()));
			}

			List outputLinksToMoveFromOutputLinks = basePart.getOutputLinksToMove();
			for (Iterator iter = outputLinksToMoveFromOutputLinks.iterator(); iter.hasNext();){
				org.eclipse.emf.eef.runtime.impl.utils.EMFListEditUtil.MoveElement moveElement = (org.eclipse.emf.eef.runtime.impl.utils.EMFListEditUtil.MoveElement)iter.next();
				cc.append(MoveCommand.create(editingDomain, pipeline, ParallelJPackage.eINSTANCE.getLink(), moveElement.getElement(), moveElement.getIndex()));
			}
			
		}
		if (!cc.isEmpty())
			return cc;
		cc.append(IdentityCommand.INSTANCE);
		return cc;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#getPropertiesEditionObject()
	 * 
	 */
	public EObject getPropertiesEditionObject(EObject source) {
		if (source instanceof Pipeline) {
			Pipeline pipelineToUpdate = (Pipeline)source;
			pipelineToUpdate.setName((java.lang.String)EEFConverterUtil.createFromString(EcorePackage.eINSTANCE.getEString(), basePart.getName()));

			pipelineToUpdate.setDescription((java.lang.String)EEFConverterUtil.createFromString(EcorePackage.eINSTANCE.getEString(), basePart.getDescription()));

			pipelineToUpdate.setCapacity(EEFConverterUtil.createIntFromString(EcorePackage.eINSTANCE.getEInt(), basePart.getCapacity()));

			pipelineToUpdate.setJoin((JoinType)basePart.getJoin());

			pipelineToUpdate.setSplit((SplitType)basePart.getSplit());

			pipelineToUpdate.getProcedures().addAll(basePart.getProceduresToAdd());
			pipelineToUpdate.getOutputLinks().addAll(basePart.getOutputLinksToAdd());
			return pipelineToUpdate;
		}
		else
			return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionListener#firePropertiesChanged(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public void firePropertiesChanged(IPropertiesEditionEvent event) {
		if (!isInitializing()) {
			Diagnostic valueDiagnostic = validateValue(event);
			if (PropertiesEditionEvent.COMMIT == event.getState() && IPropertiesEditionComponent.LIVE_MODE.equals(editing_mode) && valueDiagnostic.getSeverity() == Diagnostic.OK) {
				CompoundCommand command = new CompoundCommand();
			if (ParalleljViewsRepository.Pipeline.name == event.getAffectedEditor()) {
				command.append(SetCommand.create(liveEditingDomain, pipeline, ParallelJPackage.eINSTANCE.getNamedElement_Name(), EEFConverterUtil.createFromString(EcorePackage.eINSTANCE.getEString(), (String)event.getNewValue())));
			}
			if (ParalleljViewsRepository.Pipeline.description == event.getAffectedEditor()) {
				command.append(SetCommand.create(liveEditingDomain, pipeline, ParallelJPackage.eINSTANCE.getNamedElement_Description(), EEFConverterUtil.createFromString(EcorePackage.eINSTANCE.getEString(), (String)event.getNewValue())));
			}
			if (ParalleljViewsRepository.Pipeline.capacity == event.getAffectedEditor()) {
				command.append(SetCommand.create(liveEditingDomain, pipeline, ParallelJPackage.eINSTANCE.getProcedure_Capacity(), EEFConverterUtil.createFromString(EcorePackage.eINSTANCE.getEInt(), (String)event.getNewValue())));
			}
			if (ParalleljViewsRepository.Pipeline.join == event.getAffectedEditor())
				command.append(SetCommand.create(liveEditingDomain, pipeline, ParallelJPackage.eINSTANCE.getProcedure_Join(), event.getNewValue()));

			if (ParalleljViewsRepository.Pipeline.split == event.getAffectedEditor())
				command.append(SetCommand.create(liveEditingDomain, pipeline, ParallelJPackage.eINSTANCE.getProcedure_Split(), event.getNewValue()));


			if (ParalleljViewsRepository.Pipeline.outputLinks == event.getAffectedEditor()) {
				if (PropertiesEditionEvent.MOVE == event.getKind())
					command.append(MoveCommand.create(liveEditingDomain, pipeline, ParallelJPackage.eINSTANCE.getLink(), event.getNewValue(), event.getNewIndex()));
			}
			
			if (ParalleljViewsRepository.Pipeline.procedures == event.getAffectedEditor()) {
				if (PropertiesEditionEvent.SET == event.getKind()) {
					Procedure oldValue = (Procedure)event.getOldValue();
					Procedure newValue = (Procedure)event.getNewValue();
					for (EStructuralFeature feature : newValue.eClass().getEAllStructuralFeatures()) {
						if (feature.isChangeable() && !(feature instanceof EReference && ((EReference) feature).isContainer())) {
							command.append(SetCommand.create(liveEditingDomain, oldValue, feature, newValue.eGet(feature)));
						}
					}
				}
				else if (PropertiesEditionEvent.ADD == event.getKind())
					command.append(AddCommand.create(liveEditingDomain, pipeline, ParallelJPackage.eINSTANCE.getPipeline_Procedures(), event.getNewValue()));
				else if (PropertiesEditionEvent.REMOVE == event.getKind())
					command.append(RemoveCommand.create(liveEditingDomain, pipeline, ParallelJPackage.eINSTANCE.getPipeline_Procedures(), event.getNewValue()));
				else if (PropertiesEditionEvent.MOVE == event.getKind())
					command.append(MoveCommand.create(liveEditingDomain, pipeline, ParallelJPackage.eINSTANCE.getPipeline_Procedures(), event.getNewValue(), event.getNewIndex()));
			}

				if (!command.isEmpty() && !command.canExecute()) {
					EEFRuntimePlugin.getDefault().logError("Cannot perform model change command.", null);
				} else {
					liveEditingDomain.getCommandStack().execute(command);
				}
			}
			if (valueDiagnostic.getSeverity() != Diagnostic.OK && valueDiagnostic instanceof BasicDiagnostic)
				super.firePropertiesChanged(new PropertiesValidationEditionEvent(event, valueDiagnostic));
			else {
				Diagnostic validate = validate();
				super.firePropertiesChanged(new PropertiesValidationEditionEvent(event, validate));
			}
			super.firePropertiesChanged(event);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#validateValue(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public Diagnostic validateValue(IPropertiesEditionEvent event) {
		Diagnostic ret = Diagnostic.OK_INSTANCE;
		if (event.getNewValue() != null) {
			String newStringValue = event.getNewValue().toString();
			try {
				if (ParalleljViewsRepository.Pipeline.name == event.getAffectedEditor()) {
					Object newValue = EcoreUtil.createFromString(ParallelJPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newStringValue);
					ret = Diagnostician.INSTANCE.validate(ParallelJPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
				}
				if (ParalleljViewsRepository.Pipeline.capacity == event.getAffectedEditor()) {
					Object newValue = EcoreUtil.createFromString(ParallelJPackage.eINSTANCE.getProcedure_Capacity().getEAttributeType(), newStringValue);
					ret = Diagnostician.INSTANCE.validate(ParallelJPackage.eINSTANCE.getProcedure_Capacity().getEAttributeType(), newValue);
				}
				if (ParalleljViewsRepository.Pipeline.description == event.getAffectedEditor()) {
					Object newValue = EcoreUtil.createFromString(ParallelJPackage.eINSTANCE.getNamedElement_Description().getEAttributeType(), newStringValue);
					ret = Diagnostician.INSTANCE.validate(ParallelJPackage.eINSTANCE.getNamedElement_Description().getEAttributeType(), newValue);
				}
				if (ParalleljViewsRepository.Pipeline.join == event.getAffectedEditor()) {
					Object newValue = EcoreUtil.createFromString(ParallelJPackage.eINSTANCE.getProcedure_Join().getEAttributeType(), newStringValue);
					ret = Diagnostician.INSTANCE.validate(ParallelJPackage.eINSTANCE.getProcedure_Join().getEAttributeType(), newValue);
				}
				if (ParalleljViewsRepository.Pipeline.split == event.getAffectedEditor()) {
					Object newValue = EcoreUtil.createFromString(ParallelJPackage.eINSTANCE.getProcedure_Split().getEAttributeType(), newStringValue);
					ret = Diagnostician.INSTANCE.validate(ParallelJPackage.eINSTANCE.getProcedure_Split().getEAttributeType(), newValue);
				}
			} catch (IllegalArgumentException iae) {
				ret = BasicDiagnostic.toDiagnostic(iae);
			} catch (WrappedException we) {
				ret = BasicDiagnostic.toDiagnostic(we);
			}
		}
		return ret;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#getHelpContent(java.lang.String, int)
	 * 
	 */
	public String getHelpContent(String key, int kind) {
		if (key == ParalleljViewsRepository.Pipeline.name)
			return "Enter Pipeline Name"; //$NON-NLS-1$
		if (key == ParalleljViewsRepository.Pipeline.capacity)
			return "Enter Pipeline  Capacity";
		if (key == ParalleljViewsRepository.Pipeline.description)
			return "Enter Pipeline Description"; //$NON-NLS-1$
		if (key == ParalleljViewsRepository.Pipeline.join)
			return "Enter Pipeline Join"; //$NON-NLS-1$
		if (key == ParalleljViewsRepository.Pipeline.split)
			return "Enter Pipeline Split"; //$NON-NLS-1$
		if (key == ParalleljViewsRepository.Pipeline.procedures)
			return "Enter Pipeline Procedure"; //$NON-NLS-1$
		if (key == ParalleljViewsRepository.Pipeline.outputLinks)
			return "Select Pipeline OuputLinks"; //$NON-NLS-1$		
		return super.getHelpContent(key, kind);
	}

	
	
	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#validate()
	 * 
	 */
	public Diagnostic validate() {
		Diagnostic validate = Diagnostic.OK_INSTANCE;
		if (IPropertiesEditionComponent.BATCH_MODE.equals(editing_mode)) {
			EObject copy = EcoreUtil.copy(pipeline);
			copy = getPropertiesEditionObject(copy);
			validate =  EEFRuntimePlugin.getEEFValidator().validate(copy);
		}
		else if (IPropertiesEditionComponent.LIVE_MODE.equals(editing_mode))
			validate = EEFRuntimePlugin.getEEFValidator().validate(pipeline);
		// Start of user code for custom validation check
		
		// End of user code
		return validate;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#dispose()
	 * 
	 */
	public void dispose() {
		if (semanticAdapter != null)
			pipeline.eAdapters().remove(semanticAdapter);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#getTabText(java.lang.String)
	 * 
	 */
	public String getTabText(String p_key) {
		return basePart.getTitle();
	}
}
