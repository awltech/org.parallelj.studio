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
package org.parallelj.designer.extension.edit.parts;

import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.draw2d.ui.figures.RoundedRectangleBorder;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.RGB;
import org.parallelj.designer.edit.parts.HandlerEditPart;
import org.parallelj.designer.extension.adapters.HandlerAdapter;
import org.parallelj.designer.extension.adapters.LinkPredicateRefreshmentOnLinkAddRemAdapter;
import org.parallelj.designer.extension.adapters.LinkPredicateRefreshmentOnSplitAdapter;
import org.parallelj.designer.extension.tools.BoundsRefreshment;
import org.parallelj.designer.extension.tools.Drawer;
import org.parallelj.designer.extension.tools.JoinSplitUpdater;
import org.parallelj.model.Handler;
import org.parallelj.model.Procedure;
import org.parallelj.model.impl.HandlerImpl;

public class HandlerExtendedEditPart extends HandlerEditPart {

	/**
	 * Default main color of this object
	 */
	private final RGB preferredColor = new RGB(116, 155, 194);

	/**
	 * Actual colors of this object
	 */

	private RGB startColor;

	private RGB endColor;

	private RGB lightColor;

	/**
	 * flag for highlighting procedure
	 */
	private boolean showSelected;

	/**
	 * flag stands for handler is selected or not
	 */
	private boolean isSelected;

	public HandlerExtendedEditPart(View view) {
		super(view);
		addAdapters(view);
		updateColor(preferredColor, false);
		// adding selection listener
		addEditPartListener(new EditPartListener.Stub() {
			public void selectedStateChanged(EditPart part) {
				if (part instanceof HandlerExtendedEditPart) {
					HandlerExtendedEditPart handlerExtendedEditPart = (HandlerExtendedEditPart) part;
					if (handlerExtendedEditPart.getSelected() > 0) {
						isSelected = true;
					} else {
						isSelected = false;
					}
					handlerExtendedEditPart.findLinkedProcedures();
				}
			}
		});
	}

	/**
	 * This will find the all the managed
	 * procedures/forEachLoops/whileLoops/handlers/blocks and based on parameter
	 * it will show object highlighted or will clear.
	 * 
	 */
	public void findLinkedProcedures() {
		// getting all managed procedures
		if (getHandler() != null) {
			EList<Procedure> procedures = getHandler().getProcedures();
			EditPart compartment = this.getParent();
			List children = compartment.getChildren();

			for (Object object : children) {
				Procedure innerProcedure = null;
				if (object instanceof ProcedureExtendedEditPart) {
					ProcedureExtendedEditPart procedureExtendedEditPart = (ProcedureExtendedEditPart) object;
					EObject element = ((View) procedureExtendedEditPart
							.getModel()).getElement();
					if (element instanceof Procedure) {
						innerProcedure = (Procedure) element;
						/**
						 * Finding edit part for Handler's managed procedures.
						 * If inner procedure instance is matching with any
						 * instance from handler's procedure, than based on
						 * showSelected variable, it will highlight the current
						 * editpart or clear.
						 */
						if (checkMatch(procedures, innerProcedure)) {
							if (isSelected) {
								procedureExtendedEditPart.showSelected();
							} else {
								procedureExtendedEditPart.clearSelection();
							}
						} else {
							procedureExtendedEditPart.clearSelection();
						}
					}
				} else if (object instanceof WhileLoopExtendedEditPart) {
					WhileLoopExtendedEditPart whileLoopExtendedEditPart = (WhileLoopExtendedEditPart) object;
					EObject element = ((View) whileLoopExtendedEditPart
							.getModel()).getElement();
					if (element instanceof Procedure) {
						innerProcedure = (Procedure) element;
						if (checkMatch(procedures, innerProcedure)) {
							if (isSelected) {
								whileLoopExtendedEditPart.showSelected();
							} else {
								whileLoopExtendedEditPart.clearSelection();
							}
						} else {
							whileLoopExtendedEditPart.clearSelection();
						}
					}
				} else if (object instanceof ForEachLoopExtendedEditPart) {
					ForEachLoopExtendedEditPart forEachLoopExtendedEditPart = (ForEachLoopExtendedEditPart) object;
					EObject element = ((View) forEachLoopExtendedEditPart
							.getModel()).getElement();
					if (element instanceof Procedure) {
						innerProcedure = (Procedure) element;
						if (checkMatch(procedures, innerProcedure)) {
							if (isSelected) {
								forEachLoopExtendedEditPart.showSelected();
							} else {
								forEachLoopExtendedEditPart.clearSelection();
							}
						} else {
							forEachLoopExtendedEditPart.clearSelection();
						}
					}
				} else if (object instanceof HandlerExtendedEditPart) {
					HandlerExtendedEditPart handlerExtendedEditPart = (HandlerExtendedEditPart) object;
					EObject element = ((View) handlerExtendedEditPart
							.getModel()).getElement();
					if (element instanceof Procedure) {
						innerProcedure = (Procedure) element;
						if (checkMatch(procedures, innerProcedure)) {
							if (isSelected) {
								handlerExtendedEditPart.showSelected();
							} else {
								handlerExtendedEditPart.clearSelection();
							}
						} else {
							handlerExtendedEditPart.clearSelection();
						}
					}
				} else if (object instanceof BlockExtendedEditPart) {
					BlockExtendedEditPart blockExtendedEditPart = (BlockExtendedEditPart) object;
					EObject element = ((View) blockExtendedEditPart.getModel())
							.getElement();
					if (element instanceof Procedure) {
						innerProcedure = (Procedure) element;
						if (checkMatch(procedures, innerProcedure)) {
							if (isSelected) {
								blockExtendedEditPart.showSelected();
							} else {
								blockExtendedEditPart.clearSelection();
							}
						} else {
							blockExtendedEditPart.clearSelection();
						}
					}
				} else if (object instanceof BusinessProcedureExtendedEditPart) {
					BusinessProcedureExtendedEditPart businessProcedureExtendedEditPart = (BusinessProcedureExtendedEditPart) object;
					EObject element = ((View) businessProcedureExtendedEditPart
							.getModel()).getElement();
					if (element instanceof Procedure) {
						innerProcedure = (Procedure) element;
						if (checkMatch(procedures, innerProcedure)) {
							if (isSelected) {
								businessProcedureExtendedEditPart
										.showSelected();
							} else {
								businessProcedureExtendedEditPart
										.clearSelection();
							}
						} else {
							businessProcedureExtendedEditPart.clearSelection();
						}
					}
				}
			}
		}
	}

	/**
	 * This method will clear all selection from Program
	 * 
	 * @param parent
	 */
	public void clearAllSelection(EditPart parent) {
		List children = parent.getChildren();

		for (Object object : children) {
			if (object instanceof ProcedureExtendedEditPart) {
				((ProcedureExtendedEditPart) object).clearSelection();
			} else if (object instanceof WhileLoopExtendedEditPart) {
				((WhileLoopExtendedEditPart) object).clearSelection();
			} else if (object instanceof ForEachLoopExtendedEditPart) {
				((ForEachLoopExtendedEditPart) object).clearSelection();
			} else if (object instanceof HandlerExtendedEditPart) {
				((HandlerExtendedEditPart) object).clearSelection();
			} else if (object instanceof BlockExtendedEditPart) {
				((BlockExtendedEditPart) object).clearSelection();
			} else if (object instanceof BusinessProcedureExtendedEditPart) {
				((BusinessProcedureExtendedEditPart) object).clearSelection();
			}
		}
	}

	/**
	 * This method helps finding the Edit part for Handler's managed procedures.
	 * 
	 * @param procedures
	 * @param innerProcedure
	 * @return
	 */
	private boolean checkMatch(EList<Procedure> procedures,
			Procedure innerProcedure) {
		for (Procedure procedure : procedures) {
			if (innerProcedure.equals(procedure)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Adds adapters on view associated with Edit Part.
	 * 
	 * @param view
	 */
	private void addAdapters(View view) {
		EObject element = view.getElement();
		if (element != null) {
			element.eAdapters().add(new HandlerAdapter(this));
			element.eAdapters().add(
					new LinkPredicateRefreshmentOnSplitAdapter(this));
			element.eAdapters().add(
					new LinkPredicateRefreshmentOnLinkAddRemAdapter(this));
		}
	}

	/**
	 * Calls on activate of diagram, based upon initial status on node, show
	 * SPLIT icon.
	 */
	@Override
	public void activate() {
		super.activate();
		updateSplitJoin();
	}

	/**
	 * Fixing the height of the Handler node and updating diagram with last
	 * saved color when diagram closed and opened.
	 */
	@Override
	protected void refreshBounds() {
		BoundsRefreshment.refreshBounds(this, null, 35);
		this.updateColor(Drawer.getSavedRGB(this), false);
	}

	/**
	 * Creates rectangle for Handler node, with color gradient starting from
	 * top-left to right-bottom, also having light and shadow effect. It will
	 * also show Handler highlighted, if it's linked to any Handler and that
	 * Handler object is selected.
	 */
	@Override
	protected IFigure createNodeShape() {
		IFigure figure = new HandlerFigure() {
			public void paintFigure(Graphics graphics) {
				Drawer.rectangleGradient(graphics, getBounds(), endColor,
						startColor, new RGB(0, 0, 0), lightColor, showSelected);
			}
		};
		return primaryShape = figure;
	}

	/**
	 * Updates involved diagram for SPLIT icon, based on value of the same.
	 * SPLIT icon will appear, based on number of outgoing links.
	 */
	public void updateSplitJoin() {
		JoinSplitUpdater.updateSplitJoin(getHandler(), null, this
				.getPrimaryShape().getFigureHandlerSplitFigure());
	}

	/**
	 * Updates SPLIT icon based on passed mode. This will triggered when user
	 * change SPLIT value from property view.
	 * 
	 * @param mode
	 *            it can be AND, XOR or OR
	 */
	public void setSplitIcon(String mode) {
		JoinSplitUpdater.setSplitIcon(getHandler(), mode, this
				.getPrimaryShape().getFigureHandlerSplitFigure());
	}

	public Handler getHandler() {
		if (!(((View) this.getModel()).getElement() instanceof HandlerImpl)) {
			return null;
		}
		return (Handler) ((View) this.getModel()).getElement();
	}

	/**
	 * default main color, this value will be taken on reset of color.
	 */
	@Override
	public Object getPreferredValue(EStructuralFeature feature) {
		if (feature == NotationPackage.eINSTANCE.getFillStyle_FillColor()) {
			return FigureUtilities.RGBToInteger(preferredColor);
		} else {
			return super.getPreferredValue(feature);
		}
	}

	/**
	 * Handle the event for fill color change.
	 */
	@Override
	protected void handleNotificationEvent(Notification notification) {
		super.handleNotificationEvent(notification);
		Object feature = notification.getFeature();
		if (NotationPackage.eINSTANCE.getFillStyle_FillColor().equals(feature)) {
			int newIntColor = ((Integer) notification.getNewValue()).intValue();
			RGB newRGBColor = FigureUtilities.integerToRGB(newIntColor);
			this.updateColor(newRGBColor, true);
		} else if (notification.getEventType() == Notification.ADD) {
			Drawer.drawWithDefault(notification, this);
		}
	}

	/**
	 * Update the main color and respective gradient for node.
	 * 
	 * @param rgb
	 *            new color
	 */
	public void updateColor(RGB rgb, boolean isPaint) {
		startColor = rgb;
		endColor = Drawer.lighten(40, startColor);
		lightColor = Drawer.lighten(60, startColor);
		if (isPaint) {
			this.getFigure().repaint();
		}
	}

	/**
	 * It will also show Handler highlighted, if it's linked to any Handler and
	 * that Handler object is selected.
	 */
	public void showSelected() {
		if (this.getFigure().getBorder() == null) {
			showSelected = true;
			BoundsRefreshment.refreshBounds(this, this.getSize().width + 2, 39);
			RoundedRectangleBorder border = new RoundedRectangleBorder(10, 10);
			border.setWidth(2);
			border.setColor(ColorConstants.orange);
			this.getFigure().setBorder(border);
			this.getFigure().repaint();
		}
	}

	/**
	 * It will clear highlight effect.
	 */
	public void clearSelection() {
		if (this.getFigure().getBorder() != null) {
			showSelected = false;
			BoundsRefreshment.refreshBounds(this, this.getSize().width - 2, 35);
			this.getFigure().setBorder(null);
			this.getFigure().repaint();
		}
	}
}
