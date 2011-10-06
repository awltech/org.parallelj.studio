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
package org.parallelj.ixea;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * 
 * This Class defines a generic Zone. the Zone is a subdivision of a Section
 * 
 * @see Section
 * @author Atos Worldline
 * 
 */
public abstract class Zone {

	/**
	 * FormData, used to define the Zones position among others.
	 */
	protected FormData fData;

	/**
	 * Selected EObject
	 */
	private EObject eObject;

	/**
	 * Selected Graphical Edit Part
	 */
	private AbstractGraphicalEditPart editPart;

	/**
	 * TransactionalEditingDomain retrieved from current Opened Editor
	 */
	private TransactionalEditingDomain editingDomain;

	/**
	 * Composite standing for the Zone
	 */
	private Composite zone;

	/**
	 * WidgetFactory to create Graphical Elements in properties views
	 */
	private TabbedPropertySheetWidgetFactory widgetFactory = new TabbedPropertySheetWidgetFactory();

	/**
	 * Constructor
	 * 
	 * @param parent :
	 *            parent composite
	 * @param isGroup :
	 *            true creates a Group, false creates a standard Composite.
	 */
	public Zone(Composite parent, boolean isGroup) {

		zone = (isGroup) ? widgetFactory.createGroup(parent, "") : widgetFactory
				.createComposite(parent);
		zone.setLayout(new FormLayout());
	}

	/**
	 * @return Zone in which graphical elements are created ("Background")
	 */
	public final Composite getZone() {
		return zone;
	}

	/**
	 * Initializes the zone by binding Graphical and Semantic objects to it.
	 * 
	 * @param eObject :
	 *            Selected EObject
	 * @param editPart :
	 *            Selected AbstractGraphicalEditPart
	 * @param editingDomain :
	 *            This Editor's TransactionalEditingDomain
	 */
	public final void init(EObject eObject, AbstractGraphicalEditPart editPart,
			TransactionalEditingDomain editingDomain) {
		this.eObject = eObject;
		this.editPart = editPart;
		this.editingDomain = editingDomain;
	}

	/**
	 * Method which purpose is to define and create all graphical Elements for a
	 * Zone.
	 */
	public abstract void addItemsToZone();

	/**
	 * Method which purpose is to define and apply layouts to the graphical
	 * Elements for a Zone
	 */
	public abstract void addLayoutsToItems();

	/**
	 * Method which purpose is to define and apply listeners to the graphical
	 * Elements for a Zone
	 */
	public abstract void addListenersToItems();

	/**
	 * Methodwhich purpose is to update the values of the graphical Elements for
	 * a Zone
	 */
	public abstract void updateItemsValues();

	/**
	 * @return TransactionalEditingDomain, retrieved from Opened Diagram
	 */
	protected final TransactionalEditingDomain getEditingDomain() {
		return editingDomain;
	}

	/**
	 * @return current selected Edit Part in Diagram
	 */
	protected final AbstractGraphicalEditPart getEditPart() {
		return editPart;
	}

	/**
	 * @return selected EObject beyond the selected EditPart in the Diagram
	 */
	protected final EObject getEObject() {
		return eObject;
	}

	/**
	 * If this Zone is a Group, applies a Title to it.
	 * 
	 * @param title :
	 *            Group Title
	 */
	protected final void setText(String title) {
		if (zone instanceof Group)
			((Group) zone).setText(title);
	}

	/**
	 * @return The Widget Factory
	 */
	protected final TabbedPropertySheetWidgetFactory getWidgetFactory() {
		return widgetFactory;
	}

	/**
	 * Refreshes the current Zone, such as the EditPart bound to this Zone.
	 * 
	 */
	protected void refreshZoneAndDiagram() {
		updateItemsValues();
		refreshEditPart();
	}

	/**
	 * Refreshes the current EditPart Also refreshes direct children of this
	 * EditPart
	 */
	protected final void refreshEditPart() {
		if (getEditPart() != null && getEditPart().getParent() != null
				&& getEditPart().getParent().getRoot() != null && getEditPart().getViewer() != null) {
			getEditPart().refresh();
			List<?> children = getEditPart().getChildren();
			for (Object child : children) {
				if (child != null && child instanceof AbstractGraphicalEditPart) {
					AbstractGraphicalEditPart agep = (AbstractGraphicalEditPart) child;
					if (agep.getRoot() != null && agep.getViewer() != null) {
						agep.refresh();
					}
				}
			}
		}
	}
}
