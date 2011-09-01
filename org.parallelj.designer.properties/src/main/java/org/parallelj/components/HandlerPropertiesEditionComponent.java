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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
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
import org.parallelj.designer.properties.parts.HandlerPropertiesEditionPart;
import org.parallelj.designer.properties.parts.ParalleljViewsRepository;
import org.parallelj.model.Handler;
import org.parallelj.model.Link;
import org.parallelj.model.ParallelJPackage;
import org.parallelj.model.SplitType;


// End of user code

/**
 * @author
 * 
 */
public class HandlerPropertiesEditionComponent extends StandardPropertiesEditionComponent {

	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	private String[] parts = {BASE_PART};

	/**
	 * The EObject to edit
	 * 
	 */
	private Handler handler;

	/**
	 * The Base part
	 * 
	 */
	protected HandlerPropertiesEditionPart basePart;

	/**
	 * Default constructor
	 * 
	 */
	public HandlerPropertiesEditionComponent(EObject handler, String editing_mode) {
		if (handler instanceof Handler) {
			this.handler = (Handler)handler;
			if (IPropertiesEditionComponent.LIVE_MODE.equals(editing_mode)) {
				semanticAdapter = initializeSemanticAdapter();
				this.handler.eAdapters().add(semanticAdapter);
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
					HandlerPropertiesEditionComponent.this.dispose();
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
		
//		if (ParallelJPackage.eINSTANCE.getProcedure_Executable().equals(msg.getFeature()) && basePart != null){
//			if (msg.getNewValue() != null) {
//				basePart.setExecutable(EcoreUtil.convertToString(EcorePackage.eINSTANCE.getEString(), msg.getNewValue()));
//			} else {
//				basePart.setExecutable("");
//			}
//		}

		if (ParallelJPackage.eINSTANCE.getProcedure_Split().equals(msg.getFeature()) && basePart != null)
			basePart.setSplit((Enumerator)msg.getNewValue());

		if (ParallelJPackage.eINSTANCE.getHandler_Procedures().equals(msg.getFeature()))
			basePart.updateProcedures(handler);

		if (msg.getFeature() != null && ((EStructuralFeature)msg.getFeature() == ParallelJPackage.eINSTANCE.getElement_OutputLinks())) {
			basePart.updateOutputLinks(handler);
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
			return ParalleljViewsRepository.Handler.class;
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
		if (handler != null && BASE_PART.equals(key)) {
			if (basePart == null) {
				IPropertiesEditionPartProvider provider = PropertiesEditionPartProviderService.getInstance().getProvider(ParalleljViewsRepository.class);
				if (provider != null) {
					basePart = (HandlerPropertiesEditionPart)provider.getPropertiesEditionPart(ParalleljViewsRepository.Handler.class, kind, this);
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
		if (key == ParalleljViewsRepository.Handler.class)
			this.basePart = (HandlerPropertiesEditionPart) propertiesEditionPart;
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
		if (basePart != null && key == ParalleljViewsRepository.Handler.class) {
			((IPropertiesEditionPart)basePart).setContext(elt, allResource);
			final Handler handler = (Handler)elt;
			// init values
			if (handler.getName() != null)
				basePart.setName(EEFConverterUtil.convertToString(EcorePackage.eINSTANCE.getEString(), handler.getName()));

			basePart.setCapacity(EEFConverterUtil.convertToString(EcorePackage.eINSTANCE.getEInt(), handler.getCapacity()));
			
			if (handler.getDescription() != null)
				basePart.setDescription(EcoreUtil.convertToString(EcorePackage.eINSTANCE.getEString(), handler.getDescription()));
//			if (handler.getExecutable() != null)
//				basePart.setExecutable(EEFConverterUtil.convertToString(EcorePackage.eINSTANCE.getEString(), handler.getExecutable()));

			basePart.initSplit((EEnum) ParallelJPackage.eINSTANCE.getProcedure_Split().getEType(), handler.getSplit());
			// init filters

			basePart.initProcedures(handler, null, ParallelJPackage.eINSTANCE.getHandler_Procedures());


			basePart.addFilterToProcedures(new ViewerFilter() {

				/**
				 * {@inheritDoc}
				 * 
				 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
				 */
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (element instanceof EObject)
						return (!basePart.isContainedInProceduresTable((EObject)element));
					return element instanceof Resource;
				}

			});
			basePart.addFilterToProcedures(new EObjectFilter(ParallelJPackage.eINSTANCE.getProcedure()));
			// Start of user code for additional businessfilters for procedures

			basePart.initOutputLinks(handler, null, ParallelJPackage.eINSTANCE.getElement_OutputLinks());
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

			// End of user code

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
		if ((handler != null) && (basePart != null)) { 
			cc.append(SetCommand.create(editingDomain, handler, ParallelJPackage.eINSTANCE.getNamedElement_Name(), EEFConverterUtil.createFromString(EcorePackage.eINSTANCE.getEString(), basePart.getName())));
			cc.append(SetCommand.create(editingDomain, handler, ParallelJPackage.eINSTANCE.getNamedElement_Description(), EcoreUtil.createFromString(EcorePackage.eINSTANCE.getEString(), basePart.getDescription())));
			//cc.append(SetCommand.create(editingDomain, handler, ParallelJPackage.eINSTANCE.getProcedure_Executable(), EEFConverterUtil.createFromString(EcorePackage.eINSTANCE.getEString(), basePart.getExecutable())));
			cc.append(SetCommand.create(editingDomain, handler, ParallelJPackage.eINSTANCE.getProcedure_Capacity(), EEFConverterUtil.createFromString(EcorePackage.eINSTANCE.getEInt(), basePart.getCapacity())));
			cc.append(SetCommand.create(editingDomain, handler, ParallelJPackage.eINSTANCE.getProcedure_Split(), basePart.getSplit()));
			
			List proceduresToAddFromProcedures = basePart.getProceduresToAdd();
			for (Iterator iter = proceduresToAddFromProcedures.iterator(); iter.hasNext();)
				cc.append(AddCommand.create(editingDomain, handler, ParallelJPackage.eINSTANCE.getHandler_Procedures(), iter.next()));
			List proceduresToRemoveFromProcedures = basePart.getProceduresToRemove();
			for (Iterator iter = proceduresToRemoveFromProcedures.iterator(); iter.hasNext();)
				cc.append(RemoveCommand.create(editingDomain, handler, ParallelJPackage.eINSTANCE.getHandler_Procedures(), iter.next()));
			//List proceduresToMoveFromProcedures = basePart.getProceduresToMove();
			//for (Iterator iter = proceduresToMoveFromProcedures.iterator(); iter.hasNext();){
			//	org.eclipse.emf.eef.runtime.impl.utils.EMFListEditUtil.MoveElement moveElement = (org.eclipse.emf.eef.runtime.impl.utils.EMFListEditUtil.MoveElement)iter.next();
			//	cc.append(MoveCommand.create(editingDomain, handler, ParallelJPackage.eINSTANCE.getProcedure(), moveElement.getElement(), moveElement.getIndex()));
			//}

			List outputLinksToMoveFromOutputLinks = basePart.getOutputLinksToMove();
			for (Iterator iter = outputLinksToMoveFromOutputLinks.iterator(); iter.hasNext();){
				org.eclipse.emf.eef.runtime.impl.utils.EMFListEditUtil.MoveElement moveElement = (org.eclipse.emf.eef.runtime.impl.utils.EMFListEditUtil.MoveElement)iter.next();
				cc.append(MoveCommand.create(editingDomain, handler, ParallelJPackage.eINSTANCE.getLink(), moveElement.getElement(), moveElement.getIndex()));
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
		if (source instanceof Handler) {
			Handler handlerToUpdate = (Handler)source;
			handlerToUpdate.setName((java.lang.String)EEFConverterUtil.createFromString(EcorePackage.eINSTANCE.getEString(), basePart.getName()));

			handlerToUpdate.setDescription((java.lang.String)EcoreUtil.createFromString(EcorePackage.eINSTANCE.getEString(), basePart.getDescription()));
			//handlerToUpdate.setExecutable((java.lang.String)EEFConverterUtil.createFromString(EcorePackage.eINSTANCE.getEString(), basePart.getExecutable()));
			handlerToUpdate.setCapacity(EEFConverterUtil.createIntFromString(EcorePackage.eINSTANCE.getEInt(), basePart.getCapacity()));

			

			handlerToUpdate.setSplit((SplitType)basePart.getSplit());

			handlerToUpdate.getProcedures().addAll(basePart.getProceduresToAdd());
			
			handlerToUpdate.getOutputLinks().addAll(basePart.getOutputLinksToAdd());
			
			return handlerToUpdate;
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
			if (ParalleljViewsRepository.Handler.name == event.getAffectedEditor()) {
				command.append(SetCommand.create(liveEditingDomain, handler, ParallelJPackage.eINSTANCE.getNamedElement_Name(), EEFConverterUtil.createFromString(EcorePackage.eINSTANCE.getEString(), (String)event.getNewValue())));
			}
			if (ParalleljViewsRepository.Handler.description == event.getAffectedEditor()) {
				command.append(SetCommand.create(liveEditingDomain, handler, ParallelJPackage.eINSTANCE.getNamedElement_Description(), EcoreUtil.createFromString(EcorePackage.eINSTANCE.getEString(), (String)event.getNewValue())));
			}
			if (ParalleljViewsRepository.Handler.capacity == event.getAffectedEditor()) {
				command.append(SetCommand.create(liveEditingDomain, handler, ParallelJPackage.eINSTANCE.getProcedure_Capacity(), EEFConverterUtil.createFromString(EcorePackage.eINSTANCE.getEInt(), (String)event.getNewValue())));
			}
			if (ParalleljViewsRepository.Handler.split == event.getAffectedEditor())
				command.append(SetCommand.create(liveEditingDomain, handler, ParallelJPackage.eINSTANCE.getProcedure_Split(), event.getNewValue()));


			if (ParalleljViewsRepository.Handler.outputLinks == event.getAffectedEditor()) {
				if (PropertiesEditionEvent.MOVE == event.getKind())
					command.append(MoveCommand.create(liveEditingDomain, handler, ParallelJPackage.eINSTANCE.getLink(), event.getNewValue(), event.getNewIndex()));
			}
			
			
			if (ParalleljViewsRepository.Handler.procedures == event.getAffectedEditor()) {
				if (PropertiesEditionEvent.ADD == event.getKind())
					command.append(AddCommand.create(liveEditingDomain, handler, ParallelJPackage.eINSTANCE.getHandler_Procedures(), event.getNewValue()));
				if (PropertiesEditionEvent.REMOVE == event.getKind())
					command.append(RemoveCommand.create(liveEditingDomain, handler, ParallelJPackage.eINSTANCE.getHandler_Procedures(), event.getNewValue()));
//				if (PropertiesEditionEvent.MOVE == event.getKind())
//					command.append(MoveCommand.create(liveEditingDomain, handler, ParallelJPackage.eINSTANCE.getHandler_Procedures(), event.getNewValue(), event.getNewIndex()));
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
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#getHelpContent(java.lang.String, int)
	 * 
	 */
	public String getHelpContent(String key, int kind) {
		if (key == ParalleljViewsRepository.Handler.name)
			return "Enter Handler Name"; //$NON-NLS-1$
		if (key == ParalleljViewsRepository.Handler.description)
			return "Enter Handler Description"; //$NON-NLS-1$
		if (key == ParalleljViewsRepository.Handler.executable)
			return "Enter Handler Executable"; //$NON-NLS-1$
		if (key == ParalleljViewsRepository.Handler.capacity)
			return "Enter Handler  Capacity"; //$NON-NLS-1$
		if (key == ParalleljViewsRepository.Handler.split)
			return "Select Handler Split"; //$NON-NLS-1$
		if (key == ParalleljViewsRepository.Handler.procedures)
			return "Select Handler Procedures"; //$NON-NLS-1$
		if (key == ParalleljViewsRepository.Handler.outputLinks)
			return "Select Handler OuputLinks"; //$NON-NLS-1$		
		return super.getHelpContent(key, kind);
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
				if (ParalleljViewsRepository.Handler.name == event.getAffectedEditor()) {
					Object newValue = EcoreUtil.createFromString(ParallelJPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newStringValue);
					ret = Diagnostician.INSTANCE.validate(ParallelJPackage.eINSTANCE.getNamedElement_Name().getEAttributeType(), newValue);
				}
				if (ParalleljViewsRepository.Handler.description == event.getAffectedEditor()) {
					Object newValue = EcoreUtil.createFromString(ParallelJPackage.eINSTANCE.getNamedElement_Description().getEAttributeType(), newStringValue);
					ret = Diagnostician.INSTANCE.validate(ParallelJPackage.eINSTANCE.getNamedElement_Description().getEAttributeType(), newValue);
				}
				if (ParalleljViewsRepository.Handler.capacity == event.getAffectedEditor()) {
					Object newValue = EcoreUtil.createFromString(ParallelJPackage.eINSTANCE.getProcedure_Capacity().getEAttributeType(), newStringValue);
					ret = Diagnostician.INSTANCE.validate(ParallelJPackage.eINSTANCE.getProcedure_Capacity().getEAttributeType(), newValue);
				}
				if (ParalleljViewsRepository.Handler.executable == event.getAffectedEditor()) {
					Object newValue = EcoreUtil.createFromString(ParallelJPackage.eINSTANCE.getProcedure_Executable().getEAttributeType(), newStringValue);
					ret = Diagnostician.INSTANCE.validate(ParallelJPackage.eINSTANCE.getProcedure_Executable().getEAttributeType(), newValue);
				}
				if (ParalleljViewsRepository.Handler.split == event.getAffectedEditor()) {
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
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#validate()
	 * 
	 */
	public Diagnostic validate() {
		Diagnostic validate = Diagnostic.OK_INSTANCE;
		if (IPropertiesEditionComponent.BATCH_MODE.equals(editing_mode)) {
			EObject copy = EcoreUtil.copy(handler);
			copy = getPropertiesEditionObject(copy);
			validate =  EEFRuntimePlugin.getEEFValidator().validate(copy);
		}
		else if (IPropertiesEditionComponent.LIVE_MODE.equals(editing_mode))
			validate = EEFRuntimePlugin.getEEFValidator().validate(handler);
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
			handler.eAdapters().remove(semanticAdapter);
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
