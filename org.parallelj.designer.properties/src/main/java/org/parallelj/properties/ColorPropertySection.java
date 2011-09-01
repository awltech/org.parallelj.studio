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
package org.parallelj.properties;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.properties.sections.appearance.ColorsAndFontsPropertySection;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.emf.core.util.PackageUtil;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.parallelj.designer.edit.parts.InputConditionEditPart;
import org.parallelj.designer.edit.parts.LinkEditPart;
import org.parallelj.designer.edit.parts.OutputConditionEditPart;
import org.parallelj.designer.edit.parts.SpecificationEditPart;
import org.parallelj.designer.properties.PropertiesActivator;
import org.parallelj.designer.properties.tools.ColorPaletteDialog;

/**
 * Colors section to represent fill colors properties
 * 
 */
public class ColorPropertySection extends ColorsAndFontsPropertySection
		implements IFilter {

	private static final String LABEL_COLOR = "Colors : ";
	private static final String ICONS_FILL_COLOR_GIF = "icons/fill_color.gif";

	public boolean select(Object pToTest) {
		if (pToTest instanceof SpecificationEditPart
				|| pToTest instanceof InputConditionEditPart
				|| pToTest instanceof OutputConditionEditPart
				|| pToTest instanceof LinkEditPart) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Create colors group
	 */
	@Override
	protected Group createFontsAndColorsGroups(Composite parent) {
		colorsAndFontsGroup = getWidgetFactory().createGroup(parent,
				LABEL_COLOR);
		GridLayout layout = new GridLayout(1, false);
		colorsAndFontsGroup.setLayout(layout);
		createFontsGroup(colorsAndFontsGroup);

		return colorsAndFontsGroup;
	}

	protected Composite createFontsGroup(Composite contents) {
		Composite toolBar = createColorsGroup(contents);

		fillColorButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				changeFillColor(event);
			}
		});
		if (isReadOnly()) {
			fillColorButton.setEnabled(false);
		} else {
			fillColorButton.setEnabled(true);
		}
		return toolBar;

	}

	/**
	 * Create Color tool bar group
	 */
	protected Composite createColorsGroup(Composite parent) {
		Composite familySize = getWidgetFactory().createComposite(parent);
		GridLayout layout = new GridLayout(1, false);
		layout.horizontalSpacing = 1;
		layout.verticalSpacing = 1;
		layout.marginHeight = 2;
		layout.marginWidth = 2;
		familySize.setLayout(layout);

		Composite toolBar = new Composite(parent, SWT.SHADOW_NONE);
		toolBar.setLayout(new GridLayout(2, false));
		toolBar.setBackground(parent.getBackground());

		fillColorButton = new Button(toolBar, SWT.PUSH);
		fillColorButton.getAccessible().addAccessibleListener(
				new AccessibleAdapter() {
					public void getName(AccessibleEvent e) {
						e.result = DiagramUIMessages.PropertyDescriptorFactory_FillColor;
					}
				});
		fillColorButton.setEnabled(false);

		Button propogateButton = new Button(toolBar, SWT.PUSH);
		propogateButton.setText("Propagate");
		propogateButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				propogateColor(event);
			}
		});

		return toolBar;
	}

	@SuppressWarnings("unchecked")
	protected void propogateColor(SelectionEvent event) {

		GraphicalEditPart eps = (GraphicalEditPart) getSingleInput();
		EObject selectedElement = ((View) eps.getModel()).getElement();
		List<ICommand> commands = new ArrayList<ICommand>();
		final EStructuralFeature feature = (EStructuralFeature) PackageUtil
				.getElement(PackageUtil.getID(NotationPackage.eINSTANCE
						.getFillStyle_FillColor()));

		SpecificationEditPart specificationEditPart = getSpecificationEditPart(eps);

		List<IGraphicalEditPart> programEditParts = specificationEditPart
				.getChildren();
		Iterator<IGraphicalEditPart> programIterator = programEditParts
				.iterator();
		while (programIterator.hasNext()) {
			final IGraphicalEditPart programEditPart = programIterator.next();
			setPropogatedColorCommand(selectedElement, commands, feature,
					programEditPart);

			List<IGraphicalEditPart> elementEditParts = programEditPart
					.getChildren();
			Iterator<IGraphicalEditPart> elementIterator = elementEditParts
					.iterator();

			while (elementIterator.hasNext()) {
				final IGraphicalEditPart elementEditPart = elementIterator
						.next();
				setPropogatedColorCommand(selectedElement, commands, feature,
						elementEditPart);
				List<IGraphicalEditPart> childElementEditParts = elementEditPart
						.getChildren();
				Iterator<IGraphicalEditPart> childElementIterator = childElementEditParts
						.iterator();

				while (childElementIterator.hasNext()) {
					final IGraphicalEditPart childElementEditPart = childElementIterator
							.next();
					setPropogatedColorCommand(selectedElement, commands,
							feature, childElementEditPart);
					List<IGraphicalEditPart> childChildElementEditParts = childElementEditPart
							.getChildren();
					Iterator<IGraphicalEditPart> childChildElementIterator = childChildElementEditParts
							.iterator();

					while (childChildElementIterator.hasNext()) {
						final IGraphicalEditPart childChildElementEditPart = childChildElementIterator
								.next();
						setPropogatedColorCommand(selectedElement, commands,
								feature, childChildElementEditPart);
						List<IGraphicalEditPart> subChildElementEditParts = childChildElementEditPart
								.getChildren();
						Iterator<IGraphicalEditPart> subChildElementIterator = subChildElementEditParts
								.iterator();

						while (subChildElementIterator.hasNext()) {
							final IGraphicalEditPart subChildElementPart = subChildElementIterator
									.next();
							setPropogatedColorCommand(selectedElement,
									commands, feature, subChildElementPart);
						}
					}
				}
			}
		}
		if (!commands.isEmpty()) {
			executeAsCompositeCommand(FILL_COLOR_COMMAND_NAME, commands);
		}
	}

	private SpecificationEditPart getSpecificationEditPart(GraphicalEditPart eps) {
		SpecificationEditPart specificationEditPart = null;

		if (eps.getParent() instanceof SpecificationEditPart) {
			specificationEditPart = (SpecificationEditPart) eps.getParent();
		} else if (eps.getParent().getParent() instanceof SpecificationEditPart) {
			specificationEditPart = (SpecificationEditPart) eps.getParent()
					.getParent();
		} else if (eps.getParent().getParent().getParent() instanceof SpecificationEditPart) {
			specificationEditPart = (SpecificationEditPart) eps.getParent()
					.getParent().getParent();
		} else if (eps.getParent().getParent().getParent().getParent() instanceof SpecificationEditPart) {
			specificationEditPart = (SpecificationEditPart) eps.getParent()
					.getParent().getParent().getParent();
		} else if (eps.getParent().getParent().getParent().getParent()
				.getParent() instanceof SpecificationEditPart) {
			specificationEditPart = (SpecificationEditPart) eps.getParent()
					.getParent().getParent().getParent().getParent();
		}
		return specificationEditPart;
	}

	private void setPropogatedColorCommand(EObject selectedElement,
			List<ICommand> commands, final EStructuralFeature feature,
			final IGraphicalEditPart ep) {
		EObject propogatedElement = ((View) ep.getModel()).getElement();
		if (propogatedElement.eClass() == selectedElement.eClass()) {
			commands.add(createCommand(FILL_COLOR_COMMAND_NAME,
					((View) ep.getModel()).eResource(), new Runnable() {
						public void run() {
							ENamedElement element = PackageUtil
									.getElement(PackageUtil
											.getID(NotationPackage.eINSTANCE
													.getFillStyle_FillColor()));
							if (element instanceof EStructuralFeature)
								ep.setStructuralFeatureValue(feature,
										FigureUtilities.RGBToInteger(fillColor));
						}
					}));
		}
	}

	/**
	 * Change fill color property value.
	 * 
	 */
	protected void changeFillColor(SelectionEvent event) {
		if (fillColor != null) {
			previousColor = FigureUtilities.RGBToInteger(fillColor);
		}
		fillColor = changeColorSelected(event, fillColorButton,
				PackageUtil.getID(NotationPackage.eINSTANCE
						.getFillStyle_FillColor()), FILL_COLOR_COMMAND_NAME,
				AbstractUIPlugin.imageDescriptorFromPlugin(
						PropertiesActivator.PLUGIN_ID, ICONS_FILL_COLOR_GIF));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#refresh()
	 */
	public void refresh() {
		if (!isDisposed()) {
			Image overlyedImage = new ColorOverlayImageDescriptor(
					AbstractUIPlugin
							.imageDescriptorFromPlugin(
									PropertiesActivator.PLUGIN_ID,
									ICONS_FILL_COLOR_GIF).getImageData(),
					fillColor).createImage();
			disposeImage(fillColorButton.getImage());
			fillColorButton.setImage(overlyedImage);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gmf.runtime.diagram.ui.properties.sections.appearance.
	 * ColorsAndFontsPropertySection#updateColorCache()
	 */
	protected void updateColorCache() {
		executeAsReadAction(new Runnable() {
			public void run() {
				if (getSingleInput() instanceof GraphicalEditPart) {
					GraphicalEditPart ep = (GraphicalEditPart) getSingleInput();
					fillColor = FigureUtilities
							.integerToRGB((Integer) ep
									.getStructuralFeatureValue(NotationPackage.eINSTANCE
											.getFillStyle_FillColor()));
				} else
					fillColor = DEFAULT_PREF_COLOR;
			}
		});

	}

	/**
	 * Change the color selected by the user.
	 * 
	 */
	@SuppressWarnings("unchecked")
	protected RGB changeColorSelected(SelectionEvent event, Button button,
			final String propertyId, String commandName,
			ImageDescriptor imageDescriptor) {

		ColorPaletteDialog popup = new ColorPaletteDialog(button.getParent()
				.getShell(), IDialogConstants.BUTTON_BAR_HEIGHT);
		popup.setPreviousColor(previousColor);
		Rectangle r = button.getBounds();
		Point location = button.getParent().toDisplay(r.x, r.y);
		popup.open(new Point(location.x, location.y + r.height));
		if (popup.getSelectedColor() == null && !popup.useDefaultColor()) {
			return null;
		}

		// selectedColor should be null if we are to use the default color
		final RGB selectedColor = popup.getSelectedColor();
		final EStructuralFeature feature = (EStructuralFeature) PackageUtil
				.getElement(propertyId);

		// Update model in response to user
		List<ICommand> commands = new ArrayList<ICommand>();
		Iterator<IGraphicalEditPart> it = getInputIterator();

		RGB colorToReturn = selectedColor;
		RGB color = selectedColor;
		while (it.hasNext()) {
			final IGraphicalEditPart ep = it.next();
			color = selectedColor;
			if (popup.useDefaultColor()) {
				Object preferredValue = ep.getPreferredValue(feature);
				if (preferredValue instanceof Integer) {
					color = FigureUtilities
							.integerToRGB((Integer) preferredValue);
				}
			}

			// If we are using default colors, we want to return the color of
			// the first selected element to be consistent
			if (colorToReturn == null) {
				colorToReturn = color;
			}

			if (color != null) {
				final RGB finalColor = color; // need a final variable
				commands.add(createCommand(commandName,
						((View) ep.getModel()).eResource(), new Runnable() {

							public void run() {
								ENamedElement element = PackageUtil
										.getElement(propertyId);
								if (element instanceof EStructuralFeature)
									ep.setStructuralFeatureValue(feature,
											FigureUtilities
													.RGBToInteger(finalColor));
							}
						}));
			}
		}
		if (!commands.isEmpty()) {
			executeAsCompositeCommand(commandName, commands);
			Image overlyedImage = new ColorOverlayImageDescriptor(
					imageDescriptor.getImageData(), color).createImage();
			disposeImage(button.getImage());
			button.setImage(overlyedImage);
		}
		return colorToReturn;
	}

}
