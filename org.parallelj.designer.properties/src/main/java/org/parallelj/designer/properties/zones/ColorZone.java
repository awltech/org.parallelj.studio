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
package org.parallelj.designer.properties.zones;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.FillStyle;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.parallelj.designer.properties.Activator;
import org.parallelj.designer.properties.helpers.ParallelJPropertiesMessages;
import org.parallelj.ixea.Zone;
import org.parallelj.ixea.tools.FormDataBuilder;
import org.parallelj.model.Condition;
import org.parallelj.model.Data;
import org.parallelj.model.Element;
import org.parallelj.model.ForEachLoop;
import org.parallelj.model.Handler;
import org.parallelj.model.NamedElement;
import org.parallelj.model.Predicate;
import org.parallelj.model.Procedure;
import org.parallelj.model.Program;
import org.parallelj.model.WhileLoop;

public class ColorZone extends Zone {

	private CLabel colorLabel;

	private CLabel colorPreview;

	private Button colorChange;

	private Button colorPropagate;

	private static final String ICONS_FILL_COLOR_GIF = "/icons/fill_color.gif";

	public ColorZone(Composite parent, boolean isGroup) {
		super(parent, isGroup);
	}

	@Override
	public void addItemsToZone() {
		colorLabel = this.getWidgetFactory().createCLabel(getZone(),
				ParallelJPropertiesMessages.element_color.message());
		colorPreview = this.getWidgetFactory().createCLabel(getZone(), "",
				SWT.BORDER_DOT);
		colorChange = this.getWidgetFactory().createButton(getZone(), "",
				SWT.PUSH);
		colorPropagate = this.getWidgetFactory().createButton(getZone(),
				ParallelJPropertiesMessages.button_propagate.message(),
				SWT.PUSH);
		colorChange.setImage(Activator.getDefault().getImage(
				ICONS_FILL_COLOR_GIF));
	}

	@Override
	public void addLayoutsToItems() {
		new FormDataBuilder().left().top().shortLabel().apply(colorLabel);
		new FormDataBuilder().left(colorLabel).top().longLabel()
				.apply(colorPreview);
		new FormDataBuilder().left(colorPreview).top().shortButton()
				.apply(colorChange);
		new FormDataBuilder().left(colorChange).top().shortButton()
				.apply(colorPropagate);
	}

	@Override
	public void addListenersToItems() {
		colorChange.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				if (getEditPart() == null)
					return;
				ColorDialog colorDialog = new ColorDialog(new Shell(getZone()
						.getDisplay()));
				RGB rgb = colorDialog.open();
				View model = (View) getEditPart().getModel();
				applyColorTo(model, FigureUtilities.RGBToInteger(rgb),
						getEditingDomain());
			}
		});
		colorPropagate.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				if (getEditPart() == null)
					return;
				View model = (View) getEditPart().getModel();
				Color selectedColor = colorPreview.getBackground();
				Collection<View> referenceViews = null;
				if (getEObject() instanceof Handler)
					referenceViews = ColorZone.this
							.<Handler> getCrossReferenceViews(
									(Handler) getEObject(), model);
				else if (getEObject() instanceof ForEachLoop)
					referenceViews = ColorZone.this
							.<ForEachLoop> getCrossReferenceViews(
									(ForEachLoop) getEObject(), model);
				else if (getEObject() instanceof WhileLoop)
					referenceViews = ColorZone.this
							.<WhileLoop> getCrossReferenceViews(
									(WhileLoop) getEObject(), model);
				else if (getEObject() instanceof Procedure)
					referenceViews = ColorZone.this
							.<Procedure> getCrossReferenceViews(
									(Procedure) getEObject(), model);
				else if (getEObject() instanceof Condition)
					referenceViews = ColorZone.this
							.<Condition> getCrossReferenceViews(
									(Condition) getEObject(), model);
				else if (getEObject() instanceof Data)
					referenceViews = ColorZone.this
							.<Data> getCrossReferenceViews((Data) getEObject(),
									model);
				else if (getEObject() instanceof Predicate)
					referenceViews = ColorZone.this
							.<Predicate> getCrossReferenceViews(
									(Predicate) getEObject(), model);
				else
					return;
				for (View crossReferenceView : referenceViews)
					applyColorTo(crossReferenceView,
							FigureUtilities.colorToInteger(selectedColor),
							getEditingDomain());
			}
		});
	}

	@Override
	public void updateItemsValues() {
		boolean isFillElementsEnabled = false;
		View view = null;
		if (getEditPart() != null && getEditPart().getModel() instanceof View) {
			view = (View) getEditPart().getModel();
			isFillElementsEnabled = view != null && view instanceof Node;
		}

		colorLabel.setEnabled(isFillElementsEnabled);
		colorPreview.setEnabled(isFillElementsEnabled);
		colorChange.setEnabled(isFillElementsEnabled);
		colorPropagate.setEnabled(isFillElementsEnabled);

		if (isFillElementsEnabled) {
			RGB rgb = getFillColor(view);
			if (rgb != null) {
				Color color = new Color(null, rgb.red, rgb.green, rgb.blue);
				colorPreview.setBackground(color);
				colorLabel
						.setToolTipText(ParallelJPropertiesMessages.tooltip_selected_color
								.message(rgb.red, rgb.green, rgb.blue));
				colorPreview
						.setToolTipText(ParallelJPropertiesMessages.tooltip_selected_color
								.message(rgb.red, rgb.green, rgb.blue));
				color.dispose();
			}
		} else {
			colorPreview.setBackground(getZone().getDisplay().getSystemColor(
					SWT.COLOR_WHITE));
			colorLabel
					.setToolTipText(ParallelJPropertiesMessages.tooltip_color_disabled
							.message());
			colorPreview
					.setToolTipText(ParallelJPropertiesMessages.tooltip_color_disabled
							.message());
		}
	}

	protected RGB getFillColor(View view) {
		FillStyle style = (FillStyle) view.getStyle(NotationPackage.eINSTANCE
				.getFillStyle());
		return style == null ? null : FigureUtilities.integerToRGB(style
				.getFillColor());
	}

	protected void applyColorTo(View model, int color, EditingDomain domain) {
		FillStyle fillStyle = (FillStyle) model
				.getStyle(NotationPackage.eINSTANCE.getFillStyle());
		if (fillStyle == null) {
			fillStyle = NotationFactory.eINSTANCE.createFillStyle();
			AddCommand addCommand = new AddCommand(domain, model,
					NotationPackage.eINSTANCE.getView_Styles(), fillStyle);
			domain.getCommandStack().execute(addCommand);
		}
		SetCommand fillCommand = new SetCommand(domain, fillStyle,
				NotationPackage.eINSTANCE.getFillStyle_FillColor(), color);
		domain.getCommandStack().execute(fillCommand);
	}

	@SuppressWarnings("unchecked")
	protected <T extends NamedElement> Collection<View> getCrossReferenceViews(
			T basedElement, View view) {
		Collection<View> matchingViews = new ArrayList<View>();
		if (basedElement == null || !(basedElement instanceof NamedElement))
			return matchingViews;

		Collection<T> matchingElements = new ArrayList<T>();

		Program program = basedElement.eContainer() != null
				&& basedElement.eContainer() instanceof Program ? (Program) basedElement
				.eContainer() : null;

		if (program == null)
			return matchingViews;

		for (Element e : program.getElements()) {
			if (e.getClass().equals(basedElement.getClass()))
				matchingElements.add((T) e);
		}

		Diagram diagram = view.getDiagram();
		TreeIterator<EObject> eAllContents = diagram.eAllContents();
		while (eAllContents.hasNext()) {
			EObject next = eAllContents.next();
			if (next instanceof View
					&& matchingElements.contains(((View) next).getElement()))
				matchingViews.add((View) next);
		}
		return matchingViews;
	}

}
