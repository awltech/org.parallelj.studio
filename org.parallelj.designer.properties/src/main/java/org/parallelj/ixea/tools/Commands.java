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
package org.parallelj.ixea.tools;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.commands.operations.TriggeredOperations;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.emf.commands.core.commands.RepositionEObjectCommand;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.MetamodelType;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.parallelj.designer.properties.Activator;

/**
 * 
 * EMF and GMF Commands framework applied to GMF Diagrams.
 * 
 * @author mvanbesien
 * 
 */
public class Commands {

	/**
	 * 
	 * Sets a value to an EAttribute field of an EObject
	 * 
	 * @param ted
	 *            : Transactional Editing Domain
	 * @param eo
	 *            : EObject
	 * @param esf
	 *            : EStructuralFeature describing the EAttribute field to set
	 * @param value
	 *            : Value to apply to the EAttribute Field
	 * @param gep
	 *            : Abstract Graphical Edit Part for this EObject
	 */
	public static void doSetValue(TransactionalEditingDomain ted, EObject eo,
			EStructuralFeature esf, Object value, AbstractGraphicalEditPart gep) {
		try {
			SetRequest request = new SetRequest(ted, eo, esf, value);

			SetValueCommand command = new SetValueCommand(request);
			// TriggeredOperations triggerOperation = new
			// TriggeredOperations(command,
			// OperationHistoryFactory.getOperationHistory());
			// IStatus iStatus =
			// OperationHistoryFactory.getOperationHistory().execute(
			// triggerOperation, new NullProgressMonitor(), null);
			IStatus iStatus = command.execute(new NullProgressMonitor(), null);
			if (!iStatus.isOK())
				Commands.displayStatus(iStatus, "doSetValue");
		} catch (ExecutionException e) {
			Activator.getDefault().logError(
					"An exception has been thrown while executing Command", e);
		}
	}

	/**
	 * Sets the resource (Diagram or Semantic) containing the Abstract Graphical
	 * Edit Part or the EObject as dirty
	 * 
	 * @param abstractGraphicalEditPart
	 *            : Abstract Graphical Edit Part contained by the diagram to set
	 *            as Dirty.
	 * @param eo
	 *            : EObject contained by the Semantic Model to set as Dirty.
	 */
	private static void setDiagramDirty(
			AbstractGraphicalEditPart abstractGraphicalEditPart, EObject eo) {
		Resource res = null;
		if (abstractGraphicalEditPart != null)
			res = ((View) abstractGraphicalEditPart.getModel()).getDiagram()
					.eResource();
		else if (eo != null)
			res = eo.eResource();
		if (res != null)
			res.setModified(true);
	}

	/**
	 * Method used to display information about a command, when this one fails.
	 * 
	 * @param status
	 *            : IStatus to be parsed
	 * @param title
	 *            : Custom message to display info about the status
	 */
	public static void displayStatus(IStatus status, String title) {
		System.err.println("This Status State (" + title + "): ");
		System.err.println("	isOK = " + status.isOK());
		String severity = (status.getSeverity() == IStatus.INFO ? "INFO" : "")
				+ (status.getSeverity() == IStatus.OK ? "OK" : "")
				+ (status.getSeverity() == IStatus.ERROR ? "ERROR" : "")
				+ (status.getSeverity() == IStatus.WARNING ? "WARNING" : "")
				+ (status.getSeverity() == IStatus.CANCEL ? "CANCEL" : "");
		System.err.println("	Sev. = " + severity);
		System.err.println("	Code = " + status.getPlugin() + "( Code : "
				+ status.getCode() + " )");
		System.err.println("	Mess = " + status.getMessage());
	}

	/**
	 * Adds a value to an EList, only in Containment relationships
	 * 
	 * @param ted
	 *            : Transactionnal Editing Domain
	 * @param container
	 *            : Container (EObject) in which the element has to be added.
	 * @param er
	 *            : EReference describing the Containment feature.
	 * @param value
	 *            : Element to add to the Container.
	 * @param gep
	 *            : Abstract Graphical Edit Part.
	 */
	public static void doAddValue(TransactionalEditingDomain ted,
			EObject container, EReference er, Object value,
			AbstractGraphicalEditPart gep) {
		if (er.isMany()) {
			EList<?> values = (EList<?>) container.eGet(er);
			if (!values.contains(value)) {

				AddCommand command = new AddCommand(ted, container, er, value);
				ted.getCommandStack().execute(command);
				Commands.setDiagramDirty(gep, container);
			}
		}

	}

	/**
	 * Creates an element, then adds it to an EList, only in Containment
	 * relationships
	 * 
	 * @param ted
	 *            : Transactionnal Editing Domain
	 * @param container
	 *            : Container (EObject) in which the element has to be added.
	 * @param containmentFeature
	 *            : EReference describing the Containment feature.
	 * @param editPart
	 *            : Abstract Graphical Edit Part.
	 * @param displayedLabelFeature
	 *            : Sets a default value to the this feature from the created
	 *            object.
	 * @param factory
	 *            : Factory used for Element Creation.
	 */
	public static EObject doCreateAndAddElement(TransactionalEditingDomain ted,
			EObject container, EReference containmentFeature,
			AbstractGraphicalEditPart editPart,
			EAttribute displayedLabelFeature, EFactory factory) {
		EObject element = null;
		try {

			IElementType[] elementTypes = ElementTypeRegistry.getInstance()
					.getContainedTypes(container, containmentFeature);
			IElementType elementType = null;
			for (IElementType elementTypeTemp : elementTypes)
				if (elementTypeTemp instanceof MetamodelType)
					elementType = elementTypeTemp;
			if (elementType != null) {
				container.eGet(containmentFeature);
				CreateElementRequest request1 = new CreateElementRequest(ted,
						container, elementType, containmentFeature);
				CreateElementCommand command1 = new CreateElementCommand(
						request1);
				TriggeredOperations triggerOperation1 = new TriggeredOperations(
						command1, OperationHistoryFactory.getOperationHistory());
				IStatus iStatus = OperationHistoryFactory.getOperationHistory()
						.execute(triggerOperation1, new NullProgressMonitor(),
								null);
				element = command1.getNewElement();
				if (!iStatus.isOK())
					Commands.displayStatus(iStatus, "doCreateAndAdd");
				if (displayedLabelFeature != null) {
					Commands.doSetValue(ted, command1.getNewElement(),
							displayedLabelFeature, "<***>", editPart);
					return command1.getNewElement();
				}
			} else {
				EClass eClass = containmentFeature.getEReferenceType();
				element = factory.create(eClass);
				Commands.doAddValue(ted, container, containmentFeature,
						element, editPart);
			}

		} catch (ExecutionException e) {
			Activator.getDefault().logError(
					"An exception has been thrown while executing Command", e);
		}
		return element;
	}

	/**
	 * Removes a value from an EList, only in Containment relationships
	 * 
	 * @param ted
	 *            : Transactionnal Editing Domain
	 * @param eo
	 *            : Container (EObject) in which the element has to be removed.
	 * @param er
	 *            : EReference describing the Containment feature.
	 * @param value
	 *            : Element to remove from the Container.
	 * @param gep
	 *            : Abstract Graphical Edit Part.
	 */
	public static void doRemoveValue(TransactionalEditingDomain ted,
			EObject eo, EReference er, Object value,
			AbstractGraphicalEditPart gep) {
		if (er.isMany()) {
			EList<?> values = (EList<?>) eo.eGet(er);
			if (values.contains(value)) {
				RemoveCommand command = new RemoveCommand(ted, eo, er, value);
				ted.getCommandStack().execute(command);
				Commands.setDiagramDirty(gep, eo);
			}
		}
	}

	/**
	 * Removes and destroys value from an EList, only in Containment
	 * relationships
	 * 
	 * @param ted
	 *            : Transactionnal Editing Domain
	 * @param container
	 *            : Container (EObject) in which the element has to be removed.
	 * @param eo
	 *            : EObject to be removed
	 * @param er
	 *            : EReference describing the Containment feature.
	 * @param gep
	 *            : Abstract Graphical Edit Part.
	 */
	public static void doRemoveAndDestroyValue(TransactionalEditingDomain ted,
			EObject container, EObject eo, EReference er,
			AbstractGraphicalEditPart editPart) {

		try {
			IStatus is3 = null;
			GraphicalEditPart eoGEP = Commands
					.retrieveEditPartFromEditPartContainerAndEObject(editPart,
							eo);
			if (eoGEP != null)
				is3 = new DeleteCommand((View) eoGEP.getModel()).execute(
						new NullProgressMonitor(), null);
			DestroyElementRequest request2 = new DestroyElementRequest(ted, eo,
					false);
			DestroyElementCommand command2 = new DestroyElementCommand(request2);
			TriggeredOperations triggerOperation2 = new TriggeredOperations(
					command2, OperationHistoryFactory.getOperationHistory());
			IStatus is2 = OperationHistoryFactory
					.getOperationHistory()
					.execute(triggerOperation2, new NullProgressMonitor(), null);
			if (is3 != null && !is2.isOK())
				Commands.displayStatus(is3, "DeleteCommand");
			if (!is2.isOK())
				Commands.displayStatus(is2, "DestroyElementCommand");
		} catch (ExecutionException e) {
			Activator.getDefault().logError(
					"An exception has been thrown while executing Command", e);
		}
		Commands.setDiagramDirty(editPart, eo);
	}

	/**
	 * Constant : Tells the doMoveValue Method to move Value up.
	 */
	public static final int MOVE_UP = -1;

	/**
	 * Constant : Tells the doMoveValue Method to move Value down.
	 */
	public static final int MOVE_DOWN = +1;

	/**
	 * Moves a value in an EList, only in Containment relationships, in EMF
	 * Models
	 * 
	 * @param ted
	 *            : Transactionnal Editing Domain
	 * @param container
	 *            : Container (EObject) in which the element has to be moved.
	 * @param er
	 *            : EReference describing the Containment feature.
	 * @param value
	 *            : Object to be moved.
	 * @param typeOfMove
	 *            : Tells the method to move Element up or down if Container
	 * @param editPart
	 *            : Abstract Graphical Edit Part.
	 */
	public static void doMoveEMFValue(EObject container, EReference er,
			Object value, int typeOfMove, AbstractGraphicalEditPart editPart) {
		if (er.isMany()) {
			@SuppressWarnings("unchecked")
			EList<Object> values = (EList<Object>) container.eGet(er);
			if (values.contains(value)) {
				int index = values.indexOf(value);
				index += typeOfMove;
				if (index > -1 && index < values.size()) {
					values.move(index, value);
					Commands.setDiagramDirty(editPart, container);
				}
			}
		}
	}

	/**
	 * Moves a value in an EList, only in Containment relationships
	 * 
	 * @param ted
	 *            : Transactionnal Editing Domain
	 * @param container
	 *            : Container (EObject) in which the element has to be moved.
	 * @param er
	 *            : EReference describing the Containment feature.
	 * @param value
	 *            : Object to be moved.
	 * @param typeOfMove
	 *            : Tells the method to move Element up or down if Container
	 * @param editPart
	 *            : Abstract Graphical Edit Part.
	 */
	public static void doMoveValue(TransactionalEditingDomain ted,
			EObject container, EReference er, Object value, int typeOfMove,
			AbstractGraphicalEditPart editPart) {
		if (er.isMany()) {
			EList<?> values = (EList<?>) container.eGet(er);
			if (values.contains(value)) {
				RepositionEObjectCommand command = new RepositionEObjectCommand(
						ted, "", values, (EObject) value, typeOfMove);
				ICommandProxy commandProxy = new ICommandProxy(command);
				commandProxy.execute();
				Commands.setDiagramDirty(editPart, container);
			}

		}
	}

	/**
	 * this method retrieves an Edit Part from its bound EObject, and a
	 * Containing Edit Part
	 * 
	 * @param editPart
	 *            : Containing Edit Part
	 * @param eo
	 *            :EObject
	 * @return EditPart linked to EObject
	 */
	public static GraphicalEditPart retrieveEditPartFromEditPartContainerAndEObject(
			GraphicalEditPart editPart, EObject eo) {
		GraphicalEditPart subGEP = null;
		if (editPart != null) {
			Iterator<?> iteOnChildren = editPart.getChildren().iterator();
			while (iteOnChildren.hasNext() && subGEP == null) {
				GraphicalEditPart child = (GraphicalEditPart) iteOnChildren
						.next();
				EObject childObj = ((View) child.getModel()).getElement();
				if (childObj != null)
					if (eo.equals(childObj))
						subGEP = child;
					else
						subGEP = Commands
								.retrieveEditPartFromEditPartContainerAndEObject(
										child, eo);
			}
		}
		return subGEP;
	}

	/**
	 * Refreshes all Edit parts for the diagram containing the Abstract
	 * Graphical Edit Part passed as Parameter.
	 * 
	 * @param abstractGraphicalEditPart
	 */
	public static void doRefreshAllEditParts(
			org.eclipse.gef.GraphicalEditPart abstractGraphicalEditPart) {
		Commands.doRefreshAllEditParts(abstractGraphicalEditPart, true);
	}

	/**
	 * Refreshes all Edit parts for the diagram containing the Abstract
	 * Graphical Edit Part passed as Parameter. This method allows to choose, if
	 * you want to consider Root Edit Part as well, or not.
	 * 
	 * @param abstractGraphicalEditPart
	 * @param retrieveRoot
	 *            : True to retrieve Diagram Root as well, false otherwise
	 */
	private static void doRefreshAllEditParts(
			org.eclipse.gef.GraphicalEditPart abstractGraphicalEditPart,
			boolean retrieveRoot) {

		GraphicalEditPart editPart = abstractGraphicalEditPart;
		if (retrieveRoot)
			while (editPart.getParent() != null)
				editPart = (GraphicalEditPart) editPart.getParent();
		Iterator<?> children = editPart.getChildren().iterator();
		while (children.hasNext()) {
			AbstractGraphicalEditPart subAGEP = (AbstractGraphicalEditPart) children
					.next();
			// We suppose that an Edge always starts from a Node to terminate to
			// another Node, so we don't check Targets but only Sources...
			Iterator<?> sourceConnectionsIterator = subAGEP.getSourceConnections()
					.iterator();
			while (sourceConnectionsIterator.hasNext()) {
				AbstractGraphicalEditPart edgePart = (AbstractGraphicalEditPart) sourceConnectionsIterator
						.next();
				edgePart.refresh();
				Commands.doRefreshAllEditParts(edgePart, false);
			}
			subAGEP.refresh();
			Commands.doRefreshAllEditParts(subAGEP, false);
		}
	}

	/**
	 * Moves an EObject from a Container EObject to another Container EObject.
	 * Both source and destination must use list containments.
	 * 
	 * @param ted
	 *            : Editing Domain
	 * @param value
	 *            : EObject to move
	 * @param sourceContainer
	 *            : EObject Source Container
	 * @param sourceReference
	 *            : Link Reference between Object and Source Container
	 * @param destinationContainer
	 *            : EObject Destination Container
	 * @param destinationReference
	 *            :Link Reference between Object and Destination Container
	 * @param agep
	 *            : Graphical Edit Part
	 */
	public static void doChangeObjectContainer(TransactionalEditingDomain ted,
			EObject value, EObject sourceContainer, EReference sourceReference,
			EObject destinationContainer, EReference destinationReference,
			AbstractGraphicalEditPart agep) {

		CompoundCommand globalCommand = new CompoundCommand();

		if (sourceReference.isMany()) {
			EList<?> values = (EList<?>) sourceContainer.eGet(sourceReference);
			if (values.contains(value)) {
				RemoveCommand command = new RemoveCommand(ted, sourceContainer,
						sourceReference, value);
				globalCommand.append(command);
			}
		}
		if (destinationReference.isMany()) {
			EList<?> values = (EList<?>) destinationContainer
					.eGet(destinationReference);
			if (!values.contains(value)) {
				AddCommand command = new AddCommand(ted, destinationContainer,
						destinationReference, value);
				globalCommand.append(command);
			}
		}
		ted.getCommandStack().execute(globalCommand);
		Commands.setDiagramDirty(agep, value);
	}

	/**
	 * Add multiple values to an EList, not only for containment relationships
	 * 
	 * @param editingDomain
	 *            : Transactional Editing Domain
	 * @param eObject
	 *            : EObject containing the EList
	 * @param esf
	 *            : EStructuralFeature describing the field containing the EList
	 * @param values
	 *            : Values to add to the EList
	 * @param editPart
	 *            : Abstract Graphical Edit Part for this EObject
	 */
	public static void doAddValues(TransactionalEditingDomain editingDomain,
			EObject eObject, EStructuralFeature esf, Collection<?> values,
			AbstractGraphicalEditPart editPart) {
		Command addCommand = AddCommand.create(editingDomain, eObject, esf,
				values);
		editingDomain.getCommandStack().execute(addCommand);
	}

	/**
	 * Remove multiple values to an EList, not only for containment
	 * relationships
	 * 
	 * @param editingDomain
	 *            : Transactional Editing Domain
	 * @param eObject
	 *            : EObject containing the EList
	 * @param esf
	 *            : EStructuralFeature describing the field containing the EList
	 * @param values
	 *            : Values to add to the EList
	 * @param editPart
	 *            : Abstract Graphical Edit Part for this EObject
	 */
	public static void doRemoveValues(TransactionalEditingDomain editingDomain,
			EObject eObject, EStructuralFeature esf, Collection<?> values,
			AbstractGraphicalEditPart editPart) {
		Command addCommand = RemoveCommand.create(editingDomain, eObject, esf,
				values);
		editingDomain.getCommandStack().execute(addCommand);
	}
}
