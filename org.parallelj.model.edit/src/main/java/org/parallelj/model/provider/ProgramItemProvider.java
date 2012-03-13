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
package org.parallelj.model.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemColorProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.parallelj.model.ParallelJFactory;
import org.parallelj.model.ParallelJPackage;
import org.parallelj.model.Program;

/**
 * This is the item provider adapter for a {@link org.parallelj.model.Program} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ProgramItemProvider
	extends NamedElementItemProvider
	implements
		IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProgramItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addInputConditionPropertyDescriptor(object);
			addOutputConditionPropertyDescriptor(object);
			addCapacityPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Input Condition feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addInputConditionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Program_inputCondition_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Program_inputCondition_feature", "_UI_Program_type"),
				 ParallelJPackage.Literals.PROGRAM__INPUT_CONDITION,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Output Condition feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOutputConditionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Program_outputCondition_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Program_outputCondition_feature", "_UI_Program_type"),
				 ParallelJPackage.Literals.PROGRAM__OUTPUT_CONDITION,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Capacity feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCapacityPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Program_capacity_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Program_capacity_feature", "_UI_Program_type"),
				 ParallelJPackage.Literals.PROGRAM__CAPACITY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(ParallelJPackage.Literals.PROGRAM__PREDICATES);
			childrenFeatures.add(ParallelJPackage.Literals.PROGRAM__ELEMENTS);
			childrenFeatures.add(ParallelJPackage.Literals.PROGRAM__DATA);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns Program.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Program"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Program)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Program_type") :
			getString("_UI_Program_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(Program.class)) {
			case ParallelJPackage.PROGRAM__CAPACITY:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ParallelJPackage.PROGRAM__PREDICATES:
			case ParallelJPackage.PROGRAM__ELEMENTS:
			case ParallelJPackage.PROGRAM__DATA:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(ParallelJPackage.Literals.PROGRAM__PREDICATES,
				 ParallelJFactory.eINSTANCE.createPredicate()));

		newChildDescriptors.add
			(createChildParameter
				(ParallelJPackage.Literals.PROGRAM__ELEMENTS,
				 ParallelJFactory.eINSTANCE.createElement()));

		newChildDescriptors.add
			(createChildParameter
				(ParallelJPackage.Literals.PROGRAM__ELEMENTS,
				 ParallelJFactory.eINSTANCE.createCondition()));

		newChildDescriptors.add
			(createChildParameter
				(ParallelJPackage.Literals.PROGRAM__ELEMENTS,
				 ParallelJFactory.eINSTANCE.createInputCondition()));

		newChildDescriptors.add
			(createChildParameter
				(ParallelJPackage.Literals.PROGRAM__ELEMENTS,
				 ParallelJFactory.eINSTANCE.createOutputCondition()));

		newChildDescriptors.add
			(createChildParameter
				(ParallelJPackage.Literals.PROGRAM__ELEMENTS,
				 ParallelJFactory.eINSTANCE.createProcedure()));

		newChildDescriptors.add
			(createChildParameter
				(ParallelJPackage.Literals.PROGRAM__ELEMENTS,
				 ParallelJFactory.eINSTANCE.createForEachLoop()));

		newChildDescriptors.add
			(createChildParameter
				(ParallelJPackage.Literals.PROGRAM__ELEMENTS,
				 ParallelJFactory.eINSTANCE.createWhileLoop()));

		newChildDescriptors.add
			(createChildParameter
				(ParallelJPackage.Literals.PROGRAM__ELEMENTS,
				 ParallelJFactory.eINSTANCE.createHandler()));

		newChildDescriptors.add
			(createChildParameter
				(ParallelJPackage.Literals.PROGRAM__ELEMENTS,
				 ParallelJFactory.eINSTANCE.createBlock()));

		newChildDescriptors.add
			(createChildParameter
				(ParallelJPackage.Literals.PROGRAM__ELEMENTS,
				 ParallelJFactory.eINSTANCE.createBusinessProcedure()));

		newChildDescriptors.add
			(createChildParameter
				(ParallelJPackage.Literals.PROGRAM__DATA,
				 ParallelJFactory.eINSTANCE.createData()));
	}

}
