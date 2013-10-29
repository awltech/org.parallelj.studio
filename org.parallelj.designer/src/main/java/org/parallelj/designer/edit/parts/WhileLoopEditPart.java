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
import org.parallelj.designer.edit.policies.WhileLoopItemSemanticEditPolicy;
import org.parallelj.designer.part.ParallelJVisualIDRegistry;
import org.parallelj.designer.providers.ParallelJElementTypes;

/**
 * @generated
 */
public class WhileLoopEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 3007;

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
	public WhileLoopEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new WhileLoopItemSemanticEditPolicy());
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
		return primaryShape = new WhileLoopFigure();
	}

	/**
	 * @generated
	 */
	public WhileLoopFigure getPrimaryShape() {
		return (WhileLoopFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof WhileLoopNameEditPart) {
			((WhileLoopNameEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigureWhileLoopNameFigure());
			return true;
		}
		if (childEditPart instanceof WhileLoopExecutableEditPart) {
			((WhileLoopExecutableEditPart) childEditPart)
					.setLabel(getPrimaryShape()
							.getFigureWhileLoopExecutableFigure());
			return true;
		}
		if (childEditPart instanceof WhileLoopPredicateEditPart) {
			((WhileLoopPredicateEditPart) childEditPart)
					.setLabel(getPrimaryShape()
							.getFigureWhileLoopPredicateFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof WhileLoopNameEditPart) {
			return true;
		}
		if (childEditPart instanceof WhileLoopExecutableEditPart) {
			return true;
		}
		if (childEditPart instanceof WhileLoopPredicateEditPart) {
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
				.getType(WhileLoopNameEditPart.VISUAL_ID));
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
		if (targetEditPart instanceof ForEachLoopEditPart) {
			types.add(ParallelJElementTypes.Link_4001);
		}
		if (targetEditPart instanceof org.parallelj.designer.edit.parts.WhileLoopEditPart) {
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
	public class WhileLoopFigure extends RoundedRectangle {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureWhileLoopNameFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureWhileLoopJoinFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureWhileLoopSplitFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureWhileLoopExecutableFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureWhileLoopExecutableNameFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureWhileLoopPredicateFigure;

		/**
		 * @generated
		 */
		private WrappingLabel fFigureWhileLoopExecutablePrecedingFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureWhileLoopPredicatePrecedingFigure;

		/**
		 * @generated
		 */
		public WhileLoopFigure() {

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

			fFigureWhileLoopJoinFigure = new WrappingLabel();
			fFigureWhileLoopJoinFigure.setText("<...>");
			fFigureWhileLoopJoinFigure.setBorder(new MarginBorder(getMapMode()
					.DPtoLP(1), getMapMode().DPtoLP(3), getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0)));

			GridData constraintFFigureWhileLoopJoinFigure = new GridData();
			constraintFFigureWhileLoopJoinFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureWhileLoopJoinFigure.horizontalAlignment = GridData.BEGINNING;
			constraintFFigureWhileLoopJoinFigure.horizontalIndent = 0;
			constraintFFigureWhileLoopJoinFigure.horizontalSpan = 1;
			constraintFFigureWhileLoopJoinFigure.verticalSpan = 1;
			constraintFFigureWhileLoopJoinFigure.grabExcessHorizontalSpace = false;
			constraintFFigureWhileLoopJoinFigure.grabExcessVerticalSpace = false;
			constraintFFigureWhileLoopJoinFigure.widthHint = 43;
			constraintFFigureWhileLoopJoinFigure.heightHint = 55;
			this.add(fFigureWhileLoopJoinFigure,
					constraintFFigureWhileLoopJoinFigure);

			RectangleFigure whileLoopNameHolder0 = new RectangleFigure();
			whileLoopNameHolder0.setFill(false);
			whileLoopNameHolder0.setOutline(false);
			whileLoopNameHolder0.setBorder(new MarginBorder(getMapMode()
					.DPtoLP(4), getMapMode().DPtoLP(0), getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0)));

			GridData constraintWhileLoopNameHolder0 = new GridData();
			constraintWhileLoopNameHolder0.verticalAlignment = GridData.BEGINNING;
			constraintWhileLoopNameHolder0.horizontalAlignment = GridData.CENTER;
			constraintWhileLoopNameHolder0.horizontalIndent = 0;
			constraintWhileLoopNameHolder0.horizontalSpan = 1;
			constraintWhileLoopNameHolder0.verticalSpan = 1;
			constraintWhileLoopNameHolder0.grabExcessHorizontalSpace = true;
			constraintWhileLoopNameHolder0.grabExcessVerticalSpace = false;
			this.add(whileLoopNameHolder0, constraintWhileLoopNameHolder0);

			GridLayout layoutWhileLoopNameHolder0 = new GridLayout();
			layoutWhileLoopNameHolder0.numColumns = 1;
			layoutWhileLoopNameHolder0.makeColumnsEqualWidth = true;
			layoutWhileLoopNameHolder0.horizontalSpacing = 0;
			layoutWhileLoopNameHolder0.verticalSpacing = 0;
			layoutWhileLoopNameHolder0.marginWidth = 0;
			layoutWhileLoopNameHolder0.marginHeight = 0;
			whileLoopNameHolder0.setLayoutManager(layoutWhileLoopNameHolder0);

			fFigureWhileLoopNameFigure = new WrappingLabel();
			fFigureWhileLoopNameFigure.setText("<...>");

			fFigureWhileLoopNameFigure.setFont(FFIGUREWHILELOOPNAMEFIGURE_FONT);

			GridData constraintFFigureWhileLoopNameFigure = new GridData();
			constraintFFigureWhileLoopNameFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureWhileLoopNameFigure.horizontalAlignment = GridData.CENTER;
			constraintFFigureWhileLoopNameFigure.horizontalIndent = 0;
			constraintFFigureWhileLoopNameFigure.horizontalSpan = 0;
			constraintFFigureWhileLoopNameFigure.verticalSpan = 0;
			constraintFFigureWhileLoopNameFigure.grabExcessHorizontalSpace = true;
			constraintFFigureWhileLoopNameFigure.grabExcessVerticalSpace = false;
			whileLoopNameHolder0.add(fFigureWhileLoopNameFigure,
					constraintFFigureWhileLoopNameFigure);

			RectangleFigure whileLoopExecutableHolder1 = new RectangleFigure();
			whileLoopExecutableHolder1.setFill(false);
			whileLoopExecutableHolder1.setOutline(false);
			whileLoopExecutableHolder1.setBorder(new MarginBorder(getMapMode()
					.DPtoLP(-1), getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0), getMapMode().DPtoLP(0)));

			GridData constraintWhileLoopExecutableHolder1 = new GridData();
			constraintWhileLoopExecutableHolder1.verticalAlignment = GridData.CENTER;
			constraintWhileLoopExecutableHolder1.horizontalAlignment = GridData.CENTER;
			constraintWhileLoopExecutableHolder1.horizontalIndent = 0;
			constraintWhileLoopExecutableHolder1.horizontalSpan = 0;
			constraintWhileLoopExecutableHolder1.verticalSpan = 0;
			constraintWhileLoopExecutableHolder1.grabExcessHorizontalSpace = true;
			constraintWhileLoopExecutableHolder1.grabExcessVerticalSpace = false;
			whileLoopNameHolder0.add(whileLoopExecutableHolder1,
					constraintWhileLoopExecutableHolder1);

			GridLayout layoutWhileLoopExecutableHolder1 = new GridLayout();
			layoutWhileLoopExecutableHolder1.numColumns = 3;
			layoutWhileLoopExecutableHolder1.makeColumnsEqualWidth = false;
			layoutWhileLoopExecutableHolder1.horizontalSpacing = 0;
			layoutWhileLoopExecutableHolder1.verticalSpacing = 0;
			layoutWhileLoopExecutableHolder1.marginWidth = 0;
			layoutWhileLoopExecutableHolder1.marginHeight = 0;
			whileLoopExecutableHolder1
					.setLayoutManager(layoutWhileLoopExecutableHolder1);

			fFigureWhileLoopExecutablePrecedingFigure = new WrappingLabel();
			fFigureWhileLoopExecutablePrecedingFigure.setText("");
			fFigureWhileLoopExecutablePrecedingFigure
					.setForegroundColor(ColorConstants.black);

			fFigureWhileLoopExecutablePrecedingFigure
					.setFont(FFIGUREWHILELOOPEXECUTABLEPRECEDINGFIGURE_FONT);

			fFigureWhileLoopExecutablePrecedingFigure
					.setBorder(new MarginBorder(getMapMode().DPtoLP(0),
							getMapMode().DPtoLP(0), getMapMode().DPtoLP(0),
							getMapMode().DPtoLP(0)));

			GridData constraintFFigureWhileLoopExecutablePrecedingFigure = new GridData();
			constraintFFigureWhileLoopExecutablePrecedingFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureWhileLoopExecutablePrecedingFigure.horizontalAlignment = GridData.CENTER;
			constraintFFigureWhileLoopExecutablePrecedingFigure.horizontalIndent = 0;
			constraintFFigureWhileLoopExecutablePrecedingFigure.horizontalSpan = 0;
			constraintFFigureWhileLoopExecutablePrecedingFigure.verticalSpan = 0;
			constraintFFigureWhileLoopExecutablePrecedingFigure.grabExcessHorizontalSpace = true;
			constraintFFigureWhileLoopExecutablePrecedingFigure.grabExcessVerticalSpace = false;
			constraintFFigureWhileLoopExecutablePrecedingFigure.widthHint = 20;
			constraintFFigureWhileLoopExecutablePrecedingFigure.heightHint = 15;
			whileLoopExecutableHolder1.add(
					fFigureWhileLoopExecutablePrecedingFigure,
					constraintFFigureWhileLoopExecutablePrecedingFigure);

			fFigureWhileLoopExecutableFigure = new WrappingLabel();
			fFigureWhileLoopExecutableFigure.setText("");

			fFigureWhileLoopExecutableFigure
					.setFont(FFIGUREWHILELOOPEXECUTABLEFIGURE_FONT);

			fFigureWhileLoopExecutableFigure.setBorder(new MarginBorder(
					getMapMode().DPtoLP(-1), getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0), getMapMode().DPtoLP(0)));

			GridData constraintFFigureWhileLoopExecutableFigure = new GridData();
			constraintFFigureWhileLoopExecutableFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureWhileLoopExecutableFigure.horizontalAlignment = GridData.CENTER;
			constraintFFigureWhileLoopExecutableFigure.horizontalIndent = 0;
			constraintFFigureWhileLoopExecutableFigure.horizontalSpan = 0;
			constraintFFigureWhileLoopExecutableFigure.verticalSpan = 0;
			constraintFFigureWhileLoopExecutableFigure.grabExcessHorizontalSpace = true;
			constraintFFigureWhileLoopExecutableFigure.grabExcessVerticalSpace = false;
			whileLoopExecutableHolder1.add(fFigureWhileLoopExecutableFigure,
					constraintFFigureWhileLoopExecutableFigure);

			fFigureWhileLoopExecutableNameFigure = new WrappingLabel();
			fFigureWhileLoopExecutableNameFigure.setText("");
			fFigureWhileLoopExecutableNameFigure
					.setForegroundColor(ColorConstants.black);

			fFigureWhileLoopExecutableNameFigure
					.setFont(FFIGUREWHILELOOPEXECUTABLENAMEFIGURE_FONT);

			fFigureWhileLoopExecutableNameFigure.setBorder(new MarginBorder(
					getMapMode().DPtoLP(-1), getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0), getMapMode().DPtoLP(0)));

			GridData constraintFFigureWhileLoopExecutableNameFigure = new GridData();
			constraintFFigureWhileLoopExecutableNameFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureWhileLoopExecutableNameFigure.horizontalAlignment = GridData.CENTER;
			constraintFFigureWhileLoopExecutableNameFigure.horizontalIndent = 0;
			constraintFFigureWhileLoopExecutableNameFigure.horizontalSpan = 0;
			constraintFFigureWhileLoopExecutableNameFigure.verticalSpan = 0;
			constraintFFigureWhileLoopExecutableNameFigure.grabExcessHorizontalSpace = true;
			constraintFFigureWhileLoopExecutableNameFigure.grabExcessVerticalSpace = false;
			whileLoopExecutableHolder1.add(
					fFigureWhileLoopExecutableNameFigure,
					constraintFFigureWhileLoopExecutableNameFigure);

			RectangleFigure whileLoopPredicateHolderFigure1 = new RectangleFigure();
			whileLoopPredicateHolderFigure1.setFill(false);
			whileLoopPredicateHolderFigure1.setOutline(false);
			whileLoopPredicateHolderFigure1.setBorder(new MarginBorder(
					getMapMode().DPtoLP(-3), getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0), getMapMode().DPtoLP(0)));

			GridData constraintWhileLoopPredicateHolderFigure1 = new GridData();
			constraintWhileLoopPredicateHolderFigure1.verticalAlignment = GridData.CENTER;
			constraintWhileLoopPredicateHolderFigure1.horizontalAlignment = GridData.CENTER;
			constraintWhileLoopPredicateHolderFigure1.horizontalIndent = 0;
			constraintWhileLoopPredicateHolderFigure1.horizontalSpan = 0;
			constraintWhileLoopPredicateHolderFigure1.verticalSpan = 0;
			constraintWhileLoopPredicateHolderFigure1.grabExcessHorizontalSpace = true;
			constraintWhileLoopPredicateHolderFigure1.grabExcessVerticalSpace = false;
			whileLoopNameHolder0.add(whileLoopPredicateHolderFigure1,
					constraintWhileLoopPredicateHolderFigure1);

			GridLayout layoutWhileLoopPredicateHolderFigure1 = new GridLayout();
			layoutWhileLoopPredicateHolderFigure1.numColumns = 2;
			layoutWhileLoopPredicateHolderFigure1.makeColumnsEqualWidth = false;
			layoutWhileLoopPredicateHolderFigure1.horizontalSpacing = 0;
			layoutWhileLoopPredicateHolderFigure1.verticalSpacing = 0;
			layoutWhileLoopPredicateHolderFigure1.marginWidth = 0;
			layoutWhileLoopPredicateHolderFigure1.marginHeight = 0;
			whileLoopPredicateHolderFigure1
					.setLayoutManager(layoutWhileLoopPredicateHolderFigure1);

			fFigureWhileLoopPredicatePrecedingFigure = new WrappingLabel();
			fFigureWhileLoopPredicatePrecedingFigure.setText("");
			fFigureWhileLoopPredicatePrecedingFigure
					.setForegroundColor(ColorConstants.black);

			fFigureWhileLoopPredicatePrecedingFigure
					.setFont(FFIGUREWHILELOOPPREDICATEPRECEDINGFIGURE_FONT);

			fFigureWhileLoopPredicatePrecedingFigure
					.setBorder(new MarginBorder(getMapMode().DPtoLP(0),
							getMapMode().DPtoLP(0), getMapMode().DPtoLP(0),
							getMapMode().DPtoLP(0)));

			GridData constraintFFigureWhileLoopPredicatePrecedingFigure = new GridData();
			constraintFFigureWhileLoopPredicatePrecedingFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureWhileLoopPredicatePrecedingFigure.horizontalAlignment = GridData.CENTER;
			constraintFFigureWhileLoopPredicatePrecedingFigure.horizontalIndent = 0;
			constraintFFigureWhileLoopPredicatePrecedingFigure.horizontalSpan = 0;
			constraintFFigureWhileLoopPredicatePrecedingFigure.verticalSpan = 0;
			constraintFFigureWhileLoopPredicatePrecedingFigure.grabExcessHorizontalSpace = true;
			constraintFFigureWhileLoopPredicatePrecedingFigure.grabExcessVerticalSpace = false;
			constraintFFigureWhileLoopPredicatePrecedingFigure.widthHint = 35;
			constraintFFigureWhileLoopPredicatePrecedingFigure.heightHint = 15;
			whileLoopPredicateHolderFigure1.add(
					fFigureWhileLoopPredicatePrecedingFigure,
					constraintFFigureWhileLoopPredicatePrecedingFigure);

			fFigureWhileLoopPredicateFigure = new WrappingLabel();
			fFigureWhileLoopPredicateFigure.setText("");

			fFigureWhileLoopPredicateFigure
					.setFont(FFIGUREWHILELOOPPREDICATEFIGURE_FONT);

			fFigureWhileLoopPredicateFigure.setBorder(new MarginBorder(
					getMapMode().DPtoLP(-1), getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0), getMapMode().DPtoLP(0)));

			GridData constraintFFigureWhileLoopPredicateFigure = new GridData();
			constraintFFigureWhileLoopPredicateFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureWhileLoopPredicateFigure.horizontalAlignment = GridData.CENTER;
			constraintFFigureWhileLoopPredicateFigure.horizontalIndent = 0;
			constraintFFigureWhileLoopPredicateFigure.horizontalSpan = 0;
			constraintFFigureWhileLoopPredicateFigure.verticalSpan = 0;
			constraintFFigureWhileLoopPredicateFigure.grabExcessHorizontalSpace = true;
			constraintFFigureWhileLoopPredicateFigure.grabExcessVerticalSpace = false;
			whileLoopPredicateHolderFigure1.add(
					fFigureWhileLoopPredicateFigure,
					constraintFFigureWhileLoopPredicateFigure);

			fFigureWhileLoopSplitFigure = new WrappingLabel();
			fFigureWhileLoopSplitFigure.setText("<...>");
			fFigureWhileLoopSplitFigure.setBorder(new MarginBorder(getMapMode()
					.DPtoLP(1), getMapMode().DPtoLP(1), getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0)));

			GridData constraintFFigureWhileLoopSplitFigure = new GridData();
			constraintFFigureWhileLoopSplitFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureWhileLoopSplitFigure.horizontalAlignment = GridData.END;
			constraintFFigureWhileLoopSplitFigure.horizontalIndent = 0;
			constraintFFigureWhileLoopSplitFigure.horizontalSpan = 1;
			constraintFFigureWhileLoopSplitFigure.verticalSpan = 1;
			constraintFFigureWhileLoopSplitFigure.grabExcessHorizontalSpace = false;
			constraintFFigureWhileLoopSplitFigure.grabExcessVerticalSpace = false;
			constraintFFigureWhileLoopSplitFigure.widthHint = 43;
			constraintFFigureWhileLoopSplitFigure.heightHint = 55;
			this.add(fFigureWhileLoopSplitFigure,
					constraintFFigureWhileLoopSplitFigure);

		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureWhileLoopNameFigure() {
			return fFigureWhileLoopNameFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureWhileLoopJoinFigure() {
			return fFigureWhileLoopJoinFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureWhileLoopSplitFigure() {
			return fFigureWhileLoopSplitFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureWhileLoopExecutableFigure() {
			return fFigureWhileLoopExecutableFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureWhileLoopExecutableNameFigure() {
			return fFigureWhileLoopExecutableNameFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureWhileLoopPredicateFigure() {
			return fFigureWhileLoopPredicateFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureWhileLoopExecutablePrecedingFigure() {
			return fFigureWhileLoopExecutablePrecedingFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureWhileLoopPredicatePrecedingFigure() {
			return fFigureWhileLoopPredicatePrecedingFigure;
		}

	}

	/**
	 * @generated
	 */
	static final Font FFIGUREWHILELOOPNAMEFIGURE_FONT = new Font(
			Display.getCurrent(), Display.getDefault().getSystemFont()
					.getFontData()[0].getName(), 10, SWT.BOLD);

	/**
	 * @generated
	 */
	static final Font FFIGUREWHILELOOPEXECUTABLEPRECEDINGFIGURE_FONT = new Font(
			Display.getCurrent(), Display.getDefault().getSystemFont()
					.getFontData()[0].getName(), 8, SWT.BOLD);

	/**
	 * @generated
	 */
	static final Font FFIGUREWHILELOOPEXECUTABLEFIGURE_FONT = new Font(
			Display.getCurrent(), Display.getDefault().getSystemFont()
					.getFontData()[0].getName(), 8, SWT.NORMAL);

	/**
	 * @generated
	 */
	static final Font FFIGUREWHILELOOPEXECUTABLENAMEFIGURE_FONT = new Font(
			Display.getCurrent(), Display.getDefault().getSystemFont()
					.getFontData()[0].getName(), 8, SWT.BOLD);

	/**
	 * @generated
	 */
	static final Font FFIGUREWHILELOOPPREDICATEPRECEDINGFIGURE_FONT = new Font(
			Display.getCurrent(), Display.getDefault().getSystemFont()
					.getFontData()[0].getName(), 8, SWT.BOLD);

	/**
	 * @generated
	 */
	static final Font FFIGUREWHILELOOPPREDICATEFIGURE_FONT = new Font(
			Display.getCurrent(), Display.getDefault().getSystemFont()
					.getFontData()[0].getName(), 8, SWT.NORMAL);

}
