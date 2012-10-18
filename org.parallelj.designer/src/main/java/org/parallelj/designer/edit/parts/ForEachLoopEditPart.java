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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.parallelj.designer.edit.policies.ForEachLoopItemSemanticEditPolicy;
import org.parallelj.designer.part.ParallelJVisualIDRegistry;
import org.parallelj.designer.providers.ParallelJElementTypes;

/**
 * @generated
 */
public class ForEachLoopEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 3006;

	/**
	 * @generated
	 */
	protected IFigure contentPane;

	/**
	 * @generated
	 */
	protected IFigure primaryShape;

	/**
	 * @generated
	 */
	public ForEachLoopEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new ForEachLoopItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {
		org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy lep = new org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child) {
				EditPolicy result = child
						.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
				if (result == null) {
					result = new NonResizableEditPolicy();
				}
				return result;
			}

			protected Command getMoveChildrenCommand(Request request) {
				return null;
			}

			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}
		};
		return lep;
	}

	/**
	 * @generated
	 */
	protected IFigure createNodeShape() {
		return primaryShape = new ForEachLoopFigure();
	}

	/**
	 * @generated
	 */
	public ForEachLoopFigure getPrimaryShape() {
		return (ForEachLoopFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof ForEachLoopNameEditPart) {
			((ForEachLoopNameEditPart) childEditPart)
					.setLabel(getPrimaryShape()
							.getFigureForEachLoopNameFigure());
			return true;
		}
		if (childEditPart instanceof ForEachLoopExecutableEditPart) {
			((ForEachLoopExecutableEditPart) childEditPart)
					.setLabel(getPrimaryShape()
							.getFigureForEachLoopExecutableFigure());
			return true;
		}
		if (childEditPart instanceof ForEachLoopIterableEditPart) {
			((ForEachLoopIterableEditPart) childEditPart)
					.setLabel(getPrimaryShape()
							.getFigureForEachLoopIterableFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof ForEachLoopNameEditPart) {
			return true;
		}
		if (childEditPart instanceof ForEachLoopExecutableEditPart) {
			return true;
		}
		if (childEditPart instanceof ForEachLoopIterableEditPart) {
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected void addChildVisual(EditPart childEditPart, int index) {
		if (addFixedChild(childEditPart)) {
			return;
		}
		super.addChildVisual(childEditPart, -1);
	}

	/**
	 * @generated
	 */
	protected void removeChildVisual(EditPart childEditPart) {
		if (removeFixedChild(childEditPart)) {
			return;
		}
		super.removeChildVisual(childEditPart);
	}

	/**
	 * @generated
	 */
	protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {
		return getContentPane();
	}

	/**
	 * @generated
	 */
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(265, 55);
		return result;
	}

	/**
	 * @generated
	 */
	public EditPolicy getPrimaryDragEditPolicy() {
		EditPolicy result = super.getPrimaryDragEditPolicy();
		if (result instanceof ResizableEditPolicy) {
			ResizableEditPolicy ep = (ResizableEditPolicy) result;
			ep.setResizeDirections(PositionConstants.WEST
					| PositionConstants.EAST);
		}
		return result;
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */
	protected NodeFigure createNodeFigure() {
		NodeFigure figure = createNodePlate();
		figure.setLayoutManager(new StackLayout());
		IFigure shape = createNodeShape();
		figure.add(shape);
		contentPane = setupContentPane(shape);
		return figure;
	}

	/**
	 * Default implementation treats passed figure as content pane.
	 * Respects layout one may have set for generated figure.
	 * @param nodeShape instance of generated figure class
	 * @generated
	 */
	protected IFigure setupContentPane(IFigure nodeShape) {
		if (nodeShape.getLayoutManager() == null) {
			ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
			layout.setSpacing(5);
			nodeShape.setLayoutManager(layout);
		}
		return nodeShape; // use nodeShape itself as contentPane
	}

	/**
	 * @generated
	 */
	public IFigure getContentPane() {
		if (contentPane != null) {
			return contentPane;
		}
		return super.getContentPane();
	}

	/**
	 * @generated
	 */
	protected void setForegroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setForegroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	protected void setBackgroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setBackgroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	protected void setLineWidth(int width) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineWidth(width);
		}
	}

	/**
	 * @generated
	 */
	protected void setLineType(int style) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineStyle(style);
		}
	}

	/**
	 * @generated
	 */
	public EditPart getPrimaryChildEditPart() {
		return getChildBySemanticHint(ParallelJVisualIDRegistry
				.getType(ForEachLoopNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnSource() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(1);
		types.add(ParallelJElementTypes.Link_4001);
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnSourceAndTarget(
			IGraphicalEditPart targetEditPart) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (targetEditPart instanceof InputConditionEditPart) {
			types.add(ParallelJElementTypes.Link_4001);
		}
		if (targetEditPart instanceof OutputConditionEditPart) {
			types.add(ParallelJElementTypes.Link_4001);
		}
		if (targetEditPart instanceof ConditionEditPart) {
			types.add(ParallelJElementTypes.Link_4001);
		}
		if (targetEditPart instanceof ProcedureEditPart) {
			types.add(ParallelJElementTypes.Link_4001);
		}
		if (targetEditPart instanceof org.parallelj.designer.edit.parts.ForEachLoopEditPart) {
			types.add(ParallelJElementTypes.Link_4001);
		}
		if (targetEditPart instanceof WhileLoopEditPart) {
			types.add(ParallelJElementTypes.Link_4001);
		}
		if (targetEditPart instanceof HandlerEditPart) {
			types.add(ParallelJElementTypes.Link_4001);
		}
		if (targetEditPart instanceof PipelineEditPart) {
			types.add(ParallelJElementTypes.Link_4001);
		}
		if (targetEditPart instanceof PipelineProcedureEditPart) {
			types.add(ParallelJElementTypes.Link_4001);
		}
		if (targetEditPart instanceof BusinessProcedureEditPart) {
			types.add(ParallelJElementTypes.Link_4001);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMATypesForTarget(IElementType relationshipType) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (relationshipType == ParallelJElementTypes.Link_4001) {
			types.add(ParallelJElementTypes.InputCondition_3001);
			types.add(ParallelJElementTypes.OutputCondition_3002);
			types.add(ParallelJElementTypes.Condition_3003);
			types.add(ParallelJElementTypes.Procedure_3005);
			types.add(ParallelJElementTypes.ForEachLoop_3006);
			types.add(ParallelJElementTypes.WhileLoop_3007);
			types.add(ParallelJElementTypes.Handler_3008);
			types.add(ParallelJElementTypes.Pipeline_3015);
			types.add(ParallelJElementTypes.Procedure_3016);
			types.add(ParallelJElementTypes.BusinessProcedure_3014);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnTarget() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(1);
		types.add(ParallelJElementTypes.Link_4001);
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMATypesForSource(IElementType relationshipType) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (relationshipType == ParallelJElementTypes.Link_4001) {
			types.add(ParallelJElementTypes.InputCondition_3001);
			types.add(ParallelJElementTypes.OutputCondition_3002);
			types.add(ParallelJElementTypes.Condition_3003);
			types.add(ParallelJElementTypes.Procedure_3005);
			types.add(ParallelJElementTypes.ForEachLoop_3006);
			types.add(ParallelJElementTypes.WhileLoop_3007);
			types.add(ParallelJElementTypes.Handler_3008);
			types.add(ParallelJElementTypes.Pipeline_3015);
			types.add(ParallelJElementTypes.Procedure_3016);
			types.add(ParallelJElementTypes.BusinessProcedure_3014);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public class ForEachLoopFigure extends RoundedRectangle {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureForEachLoopNameFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureForEachLoopJoinFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureForEachLoopSplitFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureForEachLoopExecutableFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureForEachLoopExecutableNameFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureForEachLoopIterableFigure;

		/**
		 * @generated
		 */
		private WrappingLabel fFigureForEachLoopExecutablePrecedingFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureForEachLoopIterablePrecedingFigure;

		/**
		 * @generated
		 */
		public ForEachLoopFigure() {

			GridLayout layoutThis = new GridLayout();
			layoutThis.numColumns = 3;
			layoutThis.makeColumnsEqualWidth = false;
			layoutThis.horizontalSpacing = 0;
			layoutThis.verticalSpacing = 0;
			layoutThis.marginWidth = 0;
			layoutThis.marginHeight = 0;
			this.setLayoutManager(layoutThis);

			this.setCornerDimensions(new Dimension(getMapMode().DPtoLP(12),
					getMapMode().DPtoLP(12)));
			this.setPreferredSize(new Dimension(getMapMode().DPtoLP(265),
					getMapMode().DPtoLP(55)));
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			fFigureForEachLoopJoinFigure = new WrappingLabel();
			fFigureForEachLoopJoinFigure.setText("<...>");
			fFigureForEachLoopJoinFigure.setBorder(new MarginBorder(
					getMapMode().DPtoLP(1), getMapMode().DPtoLP(3),
					getMapMode().DPtoLP(0), getMapMode().DPtoLP(0)));

			GridData constraintFFigureForEachLoopJoinFigure = new GridData();
			constraintFFigureForEachLoopJoinFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureForEachLoopJoinFigure.horizontalAlignment = GridData.BEGINNING;
			constraintFFigureForEachLoopJoinFigure.horizontalIndent = 0;
			constraintFFigureForEachLoopJoinFigure.horizontalSpan = 1;
			constraintFFigureForEachLoopJoinFigure.verticalSpan = 1;
			constraintFFigureForEachLoopJoinFigure.grabExcessHorizontalSpace = false;
			constraintFFigureForEachLoopJoinFigure.grabExcessVerticalSpace = false;
			constraintFFigureForEachLoopJoinFigure.widthHint = 43;
			constraintFFigureForEachLoopJoinFigure.heightHint = 55;
			this.add(fFigureForEachLoopJoinFigure,
					constraintFFigureForEachLoopJoinFigure);

			RectangleFigure forLoopNameHolder0 = new RectangleFigure();
			forLoopNameHolder0.setFill(false);
			forLoopNameHolder0.setOutline(false);
			forLoopNameHolder0.setBorder(new MarginBorder(getMapMode()
					.DPtoLP(4), getMapMode().DPtoLP(0), getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0)));

			GridData constraintForLoopNameHolder0 = new GridData();
			constraintForLoopNameHolder0.verticalAlignment = GridData.BEGINNING;
			constraintForLoopNameHolder0.horizontalAlignment = GridData.CENTER;
			constraintForLoopNameHolder0.horizontalIndent = 0;
			constraintForLoopNameHolder0.horizontalSpan = 1;
			constraintForLoopNameHolder0.verticalSpan = 1;
			constraintForLoopNameHolder0.grabExcessHorizontalSpace = true;
			constraintForLoopNameHolder0.grabExcessVerticalSpace = false;
			this.add(forLoopNameHolder0, constraintForLoopNameHolder0);

			GridLayout layoutForLoopNameHolder0 = new GridLayout();
			layoutForLoopNameHolder0.numColumns = 1;
			layoutForLoopNameHolder0.makeColumnsEqualWidth = true;
			layoutForLoopNameHolder0.horizontalSpacing = 0;
			layoutForLoopNameHolder0.verticalSpacing = 0;
			layoutForLoopNameHolder0.marginWidth = 0;
			layoutForLoopNameHolder0.marginHeight = 0;
			forLoopNameHolder0.setLayoutManager(layoutForLoopNameHolder0);

			fFigureForEachLoopNameFigure = new WrappingLabel();
			fFigureForEachLoopNameFigure.setText("<...>");

			fFigureForEachLoopNameFigure
					.setFont(FFIGUREFOREACHLOOPNAMEFIGURE_FONT);

			GridData constraintFFigureForEachLoopNameFigure = new GridData();
			constraintFFigureForEachLoopNameFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureForEachLoopNameFigure.horizontalAlignment = GridData.CENTER;
			constraintFFigureForEachLoopNameFigure.horizontalIndent = 0;
			constraintFFigureForEachLoopNameFigure.horizontalSpan = 0;
			constraintFFigureForEachLoopNameFigure.verticalSpan = 0;
			constraintFFigureForEachLoopNameFigure.grabExcessHorizontalSpace = true;
			constraintFFigureForEachLoopNameFigure.grabExcessVerticalSpace = false;
			forLoopNameHolder0.add(fFigureForEachLoopNameFigure,
					constraintFFigureForEachLoopNameFigure);

			RectangleFigure forEachLoopExecutableHolder1 = new RectangleFigure();
			forEachLoopExecutableHolder1.setFill(false);
			forEachLoopExecutableHolder1.setOutline(false);
			forEachLoopExecutableHolder1.setBorder(new MarginBorder(
					getMapMode().DPtoLP(-1), getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0), getMapMode().DPtoLP(0)));

			GridData constraintForEachLoopExecutableHolder1 = new GridData();
			constraintForEachLoopExecutableHolder1.verticalAlignment = GridData.CENTER;
			constraintForEachLoopExecutableHolder1.horizontalAlignment = GridData.CENTER;
			constraintForEachLoopExecutableHolder1.horizontalIndent = 0;
			constraintForEachLoopExecutableHolder1.horizontalSpan = 0;
			constraintForEachLoopExecutableHolder1.verticalSpan = 0;
			constraintForEachLoopExecutableHolder1.grabExcessHorizontalSpace = true;
			constraintForEachLoopExecutableHolder1.grabExcessVerticalSpace = false;
			forLoopNameHolder0.add(forEachLoopExecutableHolder1,
					constraintForEachLoopExecutableHolder1);

			GridLayout layoutForEachLoopExecutableHolder1 = new GridLayout();
			layoutForEachLoopExecutableHolder1.numColumns = 3;
			layoutForEachLoopExecutableHolder1.makeColumnsEqualWidth = false;
			layoutForEachLoopExecutableHolder1.horizontalSpacing = 0;
			layoutForEachLoopExecutableHolder1.verticalSpacing = 0;
			layoutForEachLoopExecutableHolder1.marginWidth = 0;
			layoutForEachLoopExecutableHolder1.marginHeight = 0;
			forEachLoopExecutableHolder1
					.setLayoutManager(layoutForEachLoopExecutableHolder1);

			fFigureForEachLoopExecutablePrecedingFigure = new WrappingLabel();
			fFigureForEachLoopExecutablePrecedingFigure.setText("");
			fFigureForEachLoopExecutablePrecedingFigure
					.setForegroundColor(ColorConstants.black);

			fFigureForEachLoopExecutablePrecedingFigure
					.setFont(FFIGUREFOREACHLOOPEXECUTABLEPRECEDINGFIGURE_FONT);

			fFigureForEachLoopExecutablePrecedingFigure
					.setBorder(new MarginBorder(getMapMode().DPtoLP(0),
							getMapMode().DPtoLP(0), getMapMode().DPtoLP(0),
							getMapMode().DPtoLP(0)));

			GridData constraintFFigureForEachLoopExecutablePrecedingFigure = new GridData();
			constraintFFigureForEachLoopExecutablePrecedingFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureForEachLoopExecutablePrecedingFigure.horizontalAlignment = GridData.CENTER;
			constraintFFigureForEachLoopExecutablePrecedingFigure.horizontalIndent = 0;
			constraintFFigureForEachLoopExecutablePrecedingFigure.horizontalSpan = 0;
			constraintFFigureForEachLoopExecutablePrecedingFigure.verticalSpan = 0;
			constraintFFigureForEachLoopExecutablePrecedingFigure.grabExcessHorizontalSpace = true;
			constraintFFigureForEachLoopExecutablePrecedingFigure.grabExcessVerticalSpace = false;
			constraintFFigureForEachLoopExecutablePrecedingFigure.widthHint = 20;
			constraintFFigureForEachLoopExecutablePrecedingFigure.heightHint = 15;
			forEachLoopExecutableHolder1.add(
					fFigureForEachLoopExecutablePrecedingFigure,
					constraintFFigureForEachLoopExecutablePrecedingFigure);

			fFigureForEachLoopExecutableFigure = new WrappingLabel();
			fFigureForEachLoopExecutableFigure.setText("");

			fFigureForEachLoopExecutableFigure
					.setFont(FFIGUREFOREACHLOOPEXECUTABLEFIGURE_FONT);

			fFigureForEachLoopExecutableFigure.setBorder(new MarginBorder(
					getMapMode().DPtoLP(-1), getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0), getMapMode().DPtoLP(0)));

			GridData constraintFFigureForEachLoopExecutableFigure = new GridData();
			constraintFFigureForEachLoopExecutableFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureForEachLoopExecutableFigure.horizontalAlignment = GridData.CENTER;
			constraintFFigureForEachLoopExecutableFigure.horizontalIndent = 0;
			constraintFFigureForEachLoopExecutableFigure.horizontalSpan = 0;
			constraintFFigureForEachLoopExecutableFigure.verticalSpan = 0;
			constraintFFigureForEachLoopExecutableFigure.grabExcessHorizontalSpace = true;
			constraintFFigureForEachLoopExecutableFigure.grabExcessVerticalSpace = false;
			forEachLoopExecutableHolder1.add(
					fFigureForEachLoopExecutableFigure,
					constraintFFigureForEachLoopExecutableFigure);

			fFigureForEachLoopExecutableNameFigure = new WrappingLabel();
			fFigureForEachLoopExecutableNameFigure.setText("");
			fFigureForEachLoopExecutableNameFigure
					.setForegroundColor(ColorConstants.black);

			fFigureForEachLoopExecutableNameFigure
					.setFont(FFIGUREFOREACHLOOPEXECUTABLENAMEFIGURE_FONT);

			fFigureForEachLoopExecutableNameFigure.setBorder(new MarginBorder(
					getMapMode().DPtoLP(-1), getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0), getMapMode().DPtoLP(0)));

			GridData constraintFFigureForEachLoopExecutableNameFigure = new GridData();
			constraintFFigureForEachLoopExecutableNameFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureForEachLoopExecutableNameFigure.horizontalAlignment = GridData.CENTER;
			constraintFFigureForEachLoopExecutableNameFigure.horizontalIndent = 0;
			constraintFFigureForEachLoopExecutableNameFigure.horizontalSpan = 0;
			constraintFFigureForEachLoopExecutableNameFigure.verticalSpan = 0;
			constraintFFigureForEachLoopExecutableNameFigure.grabExcessHorizontalSpace = true;
			constraintFFigureForEachLoopExecutableNameFigure.grabExcessVerticalSpace = false;
			forEachLoopExecutableHolder1.add(
					fFigureForEachLoopExecutableNameFigure,
					constraintFFigureForEachLoopExecutableNameFigure);

			RectangleFigure forEachLoopIterableHolderFigure1 = new RectangleFigure();
			forEachLoopIterableHolderFigure1.setFill(false);
			forEachLoopIterableHolderFigure1.setOutline(false);
			forEachLoopIterableHolderFigure1.setBorder(new MarginBorder(
					getMapMode().DPtoLP(-3), getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0), getMapMode().DPtoLP(0)));

			GridData constraintForEachLoopIterableHolderFigure1 = new GridData();
			constraintForEachLoopIterableHolderFigure1.verticalAlignment = GridData.CENTER;
			constraintForEachLoopIterableHolderFigure1.horizontalAlignment = GridData.CENTER;
			constraintForEachLoopIterableHolderFigure1.horizontalIndent = 0;
			constraintForEachLoopIterableHolderFigure1.horizontalSpan = 0;
			constraintForEachLoopIterableHolderFigure1.verticalSpan = 0;
			constraintForEachLoopIterableHolderFigure1.grabExcessHorizontalSpace = true;
			constraintForEachLoopIterableHolderFigure1.grabExcessVerticalSpace = false;
			forLoopNameHolder0.add(forEachLoopIterableHolderFigure1,
					constraintForEachLoopIterableHolderFigure1);

			GridLayout layoutForEachLoopIterableHolderFigure1 = new GridLayout();
			layoutForEachLoopIterableHolderFigure1.numColumns = 2;
			layoutForEachLoopIterableHolderFigure1.makeColumnsEqualWidth = false;
			layoutForEachLoopIterableHolderFigure1.horizontalSpacing = 0;
			layoutForEachLoopIterableHolderFigure1.verticalSpacing = 0;
			layoutForEachLoopIterableHolderFigure1.marginWidth = 0;
			layoutForEachLoopIterableHolderFigure1.marginHeight = 0;
			forEachLoopIterableHolderFigure1
					.setLayoutManager(layoutForEachLoopIterableHolderFigure1);

			fFigureForEachLoopIterablePrecedingFigure = new WrappingLabel();
			fFigureForEachLoopIterablePrecedingFigure.setText("");
			fFigureForEachLoopIterablePrecedingFigure
					.setForegroundColor(ColorConstants.black);

			fFigureForEachLoopIterablePrecedingFigure
					.setFont(FFIGUREFOREACHLOOPITERABLEPRECEDINGFIGURE_FONT);

			fFigureForEachLoopIterablePrecedingFigure
					.setBorder(new MarginBorder(getMapMode().DPtoLP(0),
							getMapMode().DPtoLP(0), getMapMode().DPtoLP(0),
							getMapMode().DPtoLP(0)));

			GridData constraintFFigureForEachLoopIterablePrecedingFigure = new GridData();
			constraintFFigureForEachLoopIterablePrecedingFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureForEachLoopIterablePrecedingFigure.horizontalAlignment = GridData.CENTER;
			constraintFFigureForEachLoopIterablePrecedingFigure.horizontalIndent = 0;
			constraintFFigureForEachLoopIterablePrecedingFigure.horizontalSpan = 0;
			constraintFFigureForEachLoopIterablePrecedingFigure.verticalSpan = 0;
			constraintFFigureForEachLoopIterablePrecedingFigure.grabExcessHorizontalSpace = true;
			constraintFFigureForEachLoopIterablePrecedingFigure.grabExcessVerticalSpace = false;
			constraintFFigureForEachLoopIterablePrecedingFigure.widthHint = 21;
			constraintFFigureForEachLoopIterablePrecedingFigure.heightHint = 15;
			forEachLoopIterableHolderFigure1.add(
					fFigureForEachLoopIterablePrecedingFigure,
					constraintFFigureForEachLoopIterablePrecedingFigure);

			fFigureForEachLoopIterableFigure = new WrappingLabel();
			fFigureForEachLoopIterableFigure.setText("");

			fFigureForEachLoopIterableFigure
					.setFont(FFIGUREFOREACHLOOPITERABLEFIGURE_FONT);

			fFigureForEachLoopIterableFigure.setBorder(new MarginBorder(
					getMapMode().DPtoLP(-1), getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0), getMapMode().DPtoLP(0)));

			GridData constraintFFigureForEachLoopIterableFigure = new GridData();
			constraintFFigureForEachLoopIterableFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureForEachLoopIterableFigure.horizontalAlignment = GridData.CENTER;
			constraintFFigureForEachLoopIterableFigure.horizontalIndent = 0;
			constraintFFigureForEachLoopIterableFigure.horizontalSpan = 0;
			constraintFFigureForEachLoopIterableFigure.verticalSpan = 0;
			constraintFFigureForEachLoopIterableFigure.grabExcessHorizontalSpace = true;
			constraintFFigureForEachLoopIterableFigure.grabExcessVerticalSpace = false;
			forEachLoopIterableHolderFigure1.add(
					fFigureForEachLoopIterableFigure,
					constraintFFigureForEachLoopIterableFigure);

			fFigureForEachLoopSplitFigure = new WrappingLabel();
			fFigureForEachLoopSplitFigure.setText("<...>");
			fFigureForEachLoopSplitFigure.setBorder(new MarginBorder(
					getMapMode().DPtoLP(1), getMapMode().DPtoLP(1),
					getMapMode().DPtoLP(0), getMapMode().DPtoLP(0)));

			GridData constraintFFigureForEachLoopSplitFigure = new GridData();
			constraintFFigureForEachLoopSplitFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureForEachLoopSplitFigure.horizontalAlignment = GridData.END;
			constraintFFigureForEachLoopSplitFigure.horizontalIndent = 0;
			constraintFFigureForEachLoopSplitFigure.horizontalSpan = 1;
			constraintFFigureForEachLoopSplitFigure.verticalSpan = 1;
			constraintFFigureForEachLoopSplitFigure.grabExcessHorizontalSpace = false;
			constraintFFigureForEachLoopSplitFigure.grabExcessVerticalSpace = false;
			constraintFFigureForEachLoopSplitFigure.widthHint = 43;
			constraintFFigureForEachLoopSplitFigure.heightHint = 55;
			this.add(fFigureForEachLoopSplitFigure,
					constraintFFigureForEachLoopSplitFigure);

		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureForEachLoopNameFigure() {
			return fFigureForEachLoopNameFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureForEachLoopJoinFigure() {
			return fFigureForEachLoopJoinFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureForEachLoopSplitFigure() {
			return fFigureForEachLoopSplitFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureForEachLoopExecutableFigure() {
			return fFigureForEachLoopExecutableFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureForEachLoopExecutableNameFigure() {
			return fFigureForEachLoopExecutableNameFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureForEachLoopIterableFigure() {
			return fFigureForEachLoopIterableFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureForEachLoopExecutablePrecedingFigure() {
			return fFigureForEachLoopExecutablePrecedingFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureForEachLoopIterablePrecedingFigure() {
			return fFigureForEachLoopIterablePrecedingFigure;
		}

	}

	/**
	 * @generated
	 */
	static final Font FFIGUREFOREACHLOOPNAMEFIGURE_FONT = new Font(
			Display.getCurrent(), Display.getDefault().getSystemFont()
					.getFontData()[0].getName(), 10, SWT.BOLD);

	/**
	 * @generated
	 */
	static final Font FFIGUREFOREACHLOOPEXECUTABLEPRECEDINGFIGURE_FONT = new Font(
			Display.getCurrent(), Display.getDefault().getSystemFont()
					.getFontData()[0].getName(), 8, SWT.BOLD);

	/**
	 * @generated
	 */
	static final Font FFIGUREFOREACHLOOPEXECUTABLEFIGURE_FONT = new Font(
			Display.getCurrent(), Display.getDefault().getSystemFont()
					.getFontData()[0].getName(), 8, SWT.NORMAL);

	/**
	 * @generated
	 */
	static final Font FFIGUREFOREACHLOOPEXECUTABLENAMEFIGURE_FONT = new Font(
			Display.getCurrent(), Display.getDefault().getSystemFont()
					.getFontData()[0].getName(), 8, SWT.BOLD);

	/**
	 * @generated
	 */
	static final Font FFIGUREFOREACHLOOPITERABLEPRECEDINGFIGURE_FONT = new Font(
			Display.getCurrent(), Display.getDefault().getSystemFont()
					.getFontData()[0].getName(), 8, SWT.BOLD);

	/**
	 * @generated
	 */
	static final Font FFIGUREFOREACHLOOPITERABLEFIGURE_FONT = new Font(
			Display.getCurrent(), Display.getDefault().getSystemFont()
					.getFontData()[0].getName(), 8, SWT.NORMAL);

}
