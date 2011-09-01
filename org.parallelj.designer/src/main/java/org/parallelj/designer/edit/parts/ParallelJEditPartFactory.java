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
package org.parallelj.designer.edit.parts;

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.parallelj.designer.part.ParallelJVisualIDRegistry;

/**
 * @generated
 */
public class ParallelJEditPartFactory implements EditPartFactory {

	/**
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (ParallelJVisualIDRegistry.getVisualID(view)) {

			case SpecificationEditPart.VISUAL_ID:
				return new SpecificationEditPart(view);

			case ProgramEditPart.VISUAL_ID:
				return new ProgramEditPart(view);

			case ProgramNameEditPart.VISUAL_ID:
				return new ProgramNameEditPart(view);

			case InputConditionEditPart.VISUAL_ID:
				return new InputConditionEditPart(view);

			case InputConditionNameEditPart.VISUAL_ID:
				return new InputConditionNameEditPart(view);

			case OutputConditionEditPart.VISUAL_ID:
				return new OutputConditionEditPart(view);

			case OutputConditionNameEditPart.VISUAL_ID:
				return new OutputConditionNameEditPart(view);

			case ConditionEditPart.VISUAL_ID:
				return new ConditionEditPart(view);

			case ConditionNameEditPart.VISUAL_ID:
				return new ConditionNameEditPart(view);

			case PredicateEditPart.VISUAL_ID:
				return new PredicateEditPart(view);

			case PredicateNameEditPart.VISUAL_ID:
				return new PredicateNameEditPart(view);

			case ProcedureEditPart.VISUAL_ID:
				return new ProcedureEditPart(view);

			case ProcedureNameEditPart.VISUAL_ID:
				return new ProcedureNameEditPart(view);

			case ProcedureExecutableEditPart.VISUAL_ID:
				return new ProcedureExecutableEditPart(view);

			case ForEachLoopEditPart.VISUAL_ID:
				return new ForEachLoopEditPart(view);

			case ForEachLoopNameEditPart.VISUAL_ID:
				return new ForEachLoopNameEditPart(view);

			case ForEachLoopExecutableEditPart.VISUAL_ID:
				return new ForEachLoopExecutableEditPart(view);

			case ForEachLoopIterableEditPart.VISUAL_ID:
				return new ForEachLoopIterableEditPart(view);

			case WhileLoopEditPart.VISUAL_ID:
				return new WhileLoopEditPart(view);

			case WhileLoopNameEditPart.VISUAL_ID:
				return new WhileLoopNameEditPart(view);

			case WhileLoopExecutableEditPart.VISUAL_ID:
				return new WhileLoopExecutableEditPart(view);

			case WhileLoopPredicateEditPart.VISUAL_ID:
				return new WhileLoopPredicateEditPart(view);

			case HandlerEditPart.VISUAL_ID:
				return new HandlerEditPart(view);

			case HandlerNameEditPart.VISUAL_ID:
				return new HandlerNameEditPart(view);

			case PipelineEditPart.VISUAL_ID:
				return new PipelineEditPart(view);

			case PipelineNameEditPart.VISUAL_ID:
				return new PipelineNameEditPart(view);

			case PipelineIconEditPart.VISUAL_ID:
				return new PipelineIconEditPart(view);

			case PipelineProcedureEditPart.VISUAL_ID:
				return new PipelineProcedureEditPart(view);

			case PipelineProcedureNameEditPart.VISUAL_ID:
				return new PipelineProcedureNameEditPart(view);

			case PipelineProcedureExecutableEditPart.VISUAL_ID:
				return new PipelineProcedureExecutableEditPart(view);

			case DataEditPart.VISUAL_ID:
				return new DataEditPart(view);

			case DataNameEditPart.VISUAL_ID:
				return new DataNameEditPart(view);

			case DataTypeEditPart.VISUAL_ID:
				return new DataTypeEditPart(view);

			case ProgramProgramCompartmentEditPart.VISUAL_ID:
				return new ProgramProgramCompartmentEditPart(view);

			case PipelinePipelineCompartmentEditPart.VISUAL_ID:
				return new PipelinePipelineCompartmentEditPart(view);

			case LinkEditPart.VISUAL_ID:
				return new LinkEditPart(view);

			case LinkPredicateInfoEditPart.VISUAL_ID:
				return new LinkPredicateInfoEditPart(view);

			}
		}
		return createUnrecognizedEditPart(context, model);
	}

	/**
	 * @generated
	 */
	private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
		// Handle creation of unrecognized child node EditParts here
		return null;
	}

	/**
	 * @generated
	 */
	public static CellEditorLocator getTextCellEditorLocator(
			ITextAwareEditPart source) {
		if (source.getFigure() instanceof WrappingLabel)
			return new TextCellEditorLocator((WrappingLabel) source.getFigure());
		else {
			return new LabelCellEditorLocator((Label) source.getFigure());
		}
	}

	/**
	 * @generated
	 */
	static private class TextCellEditorLocator implements CellEditorLocator {

		/**
		 * @generated
		 */
		private WrappingLabel wrapLabel;

		/**
		 * @generated
		 */
		public TextCellEditorLocator(WrappingLabel wrapLabel) {
			this.wrapLabel = wrapLabel;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getWrapLabel() {
			return wrapLabel;
		}

		/**
		 * @generated
		 */
		public void relocate(CellEditor celleditor) {
			Text text = (Text) celleditor.getControl();
			Rectangle rect = getWrapLabel().getTextBounds().getCopy();
			getWrapLabel().translateToAbsolute(rect);
			if (!text.getFont().isDisposed()) {
				if (getWrapLabel().isTextWrapOn()
						&& getWrapLabel().getText().length() > 0) {
					rect.setSize(new Dimension(text.computeSize(rect.width,
							SWT.DEFAULT)));
				} else {
					int avr = FigureUtilities.getFontMetrics(text.getFont())
							.getAverageCharWidth();
					rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT,
							SWT.DEFAULT)).expand(avr * 2, 0));
				}
			}
			if (!rect.equals(new Rectangle(text.getBounds()))) {
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
			}
		}
	}

	/**
	 * @generated
	 */
	private static class LabelCellEditorLocator implements CellEditorLocator {

		/**
		 * @generated
		 */
		private Label label;

		/**
		 * @generated
		 */
		public LabelCellEditorLocator(Label label) {
			this.label = label;
		}

		/**
		 * @generated
		 */
		public Label getLabel() {
			return label;
		}

		/**
		 * @generated
		 */
		public void relocate(CellEditor celleditor) {
			Text text = (Text) celleditor.getControl();
			Rectangle rect = getLabel().getTextBounds().getCopy();
			getLabel().translateToAbsolute(rect);
			if (!text.getFont().isDisposed()) {
				int avr = FigureUtilities.getFontMetrics(text.getFont())
						.getAverageCharWidth();
				rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT,
						SWT.DEFAULT)).expand(avr * 2, 0));
			}
			if (!rect.equals(new Rectangle(text.getBounds()))) {
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
			}
		}
	}
}
