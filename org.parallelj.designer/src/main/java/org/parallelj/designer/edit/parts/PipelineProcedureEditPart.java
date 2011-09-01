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
import org.parallelj.designer.edit.policies.PipelineProcedureItemSemanticEditPolicy;
import org.parallelj.designer.part.ParallelJVisualIDRegistry;
import org.parallelj.designer.providers.ParallelJElementTypes;

/**
 * @generated
 */
public class PipelineProcedureEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 3010;

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
	public PipelineProcedureEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new PipelineProcedureItemSemanticEditPolicy());
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
		return primaryShape = new ProcedureFigure();
	}

	/**
	 * @generated
	 */
	public ProcedureFigure getPrimaryShape() {
		return (ProcedureFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof PipelineProcedureNameEditPart) {
			((PipelineProcedureNameEditPart) childEditPart)
					.setLabel(getPrimaryShape().getFigureProcedureNameFigure());
			return true;
		}
		if (childEditPart instanceof PipelineProcedureExecutableEditPart) {
			((PipelineProcedureExecutableEditPart) childEditPart)
					.setLabel(getPrimaryShape()
							.getFigureProcedureExecutableFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof PipelineProcedureNameEditPart) {
			return true;
		}
		if (childEditPart instanceof PipelineProcedureExecutableEditPart) {
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
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(265, 43);
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
				.getType(PipelineProcedureNameEditPart.VISUAL_ID));
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
		if (targetEditPart instanceof WhileLoopEditPart) {
			types.add(ParallelJElementTypes.Link_4001);
		}
		if (targetEditPart instanceof HandlerEditPart) {
			types.add(ParallelJElementTypes.Link_4001);
		}
		if (targetEditPart instanceof PipelineEditPart) {
			types.add(ParallelJElementTypes.Link_4001);
		}
		if (targetEditPart instanceof org.parallelj.designer.edit.parts.PipelineProcedureEditPart) {
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
			types.add(ParallelJElementTypes.Pipeline_3009);
			types.add(ParallelJElementTypes.Procedure_3010);
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
			types.add(ParallelJElementTypes.Pipeline_3009);
			types.add(ParallelJElementTypes.Procedure_3010);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public class ProcedureFigure extends RoundedRectangle {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureProcedureNameFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureProcedureJoinFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureProcedureSplitFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureProcedureExecutableFigure;

		/**
		 * @generated
		 */
		private WrappingLabel fFigureProcedureExecutablePrecedingFigure;

		/**
		 * @generated
		 */
		private WrappingLabel fFigureProcedureExecutableNameFigure;

		/**
		 * @generated
		 */
		public ProcedureFigure() {

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
					getMapMode().DPtoLP(43)));
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			fFigureProcedureJoinFigure = new WrappingLabel();
			fFigureProcedureJoinFigure.setText("");
			fFigureProcedureJoinFigure.setBorder(new MarginBorder(getMapMode()
					.DPtoLP(1), getMapMode().DPtoLP(3), getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0)));

			GridData constraintFFigureProcedureJoinFigure = new GridData();
			constraintFFigureProcedureJoinFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureProcedureJoinFigure.horizontalAlignment = GridData.BEGINNING;
			constraintFFigureProcedureJoinFigure.horizontalIndent = 0;
			constraintFFigureProcedureJoinFigure.horizontalSpan = 1;
			constraintFFigureProcedureJoinFigure.verticalSpan = 1;
			constraintFFigureProcedureJoinFigure.grabExcessHorizontalSpace = false;
			constraintFFigureProcedureJoinFigure.grabExcessVerticalSpace = false;
			constraintFFigureProcedureJoinFigure.widthHint = 43;
			constraintFFigureProcedureJoinFigure.heightHint = 43;
			this.add(fFigureProcedureJoinFigure,
					constraintFFigureProcedureJoinFigure);

			RectangleFigure procedureNameHolder0 = new RectangleFigure();
			procedureNameHolder0.setFill(false);
			procedureNameHolder0.setOutline(false);
			procedureNameHolder0.setBorder(new MarginBorder(getMapMode()
					.DPtoLP(3), getMapMode().DPtoLP(0), getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0)));

			GridData constraintProcedureNameHolder0 = new GridData();
			constraintProcedureNameHolder0.verticalAlignment = GridData.BEGINNING;
			constraintProcedureNameHolder0.horizontalAlignment = GridData.CENTER;
			constraintProcedureNameHolder0.horizontalIndent = 0;
			constraintProcedureNameHolder0.horizontalSpan = 1;
			constraintProcedureNameHolder0.verticalSpan = 1;
			constraintProcedureNameHolder0.grabExcessHorizontalSpace = true;
			constraintProcedureNameHolder0.grabExcessVerticalSpace = false;
			this.add(procedureNameHolder0, constraintProcedureNameHolder0);

			GridLayout layoutProcedureNameHolder0 = new GridLayout();
			layoutProcedureNameHolder0.numColumns = 1;
			layoutProcedureNameHolder0.makeColumnsEqualWidth = true;
			layoutProcedureNameHolder0.horizontalSpacing = 0;
			layoutProcedureNameHolder0.verticalSpacing = 0;
			layoutProcedureNameHolder0.marginWidth = 0;
			layoutProcedureNameHolder0.marginHeight = 0;
			procedureNameHolder0.setLayoutManager(layoutProcedureNameHolder0);

			fFigureProcedureNameFigure = new WrappingLabel();
			fFigureProcedureNameFigure.setText("<...>");

			fFigureProcedureNameFigure.setFont(FFIGUREPROCEDURENAMEFIGURE_FONT);

			GridData constraintFFigureProcedureNameFigure = new GridData();
			constraintFFigureProcedureNameFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureProcedureNameFigure.horizontalAlignment = GridData.CENTER;
			constraintFFigureProcedureNameFigure.horizontalIndent = 0;
			constraintFFigureProcedureNameFigure.horizontalSpan = 0;
			constraintFFigureProcedureNameFigure.verticalSpan = 0;
			constraintFFigureProcedureNameFigure.grabExcessHorizontalSpace = true;
			constraintFFigureProcedureNameFigure.grabExcessVerticalSpace = false;
			procedureNameHolder0.add(fFigureProcedureNameFigure,
					constraintFFigureProcedureNameFigure);

			RectangleFigure procedureExecutableHolder1 = new RectangleFigure();
			procedureExecutableHolder1.setFill(false);
			procedureExecutableHolder1.setOutline(false);
			procedureExecutableHolder1.setBorder(new MarginBorder(getMapMode()
					.DPtoLP(-1), getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0), getMapMode().DPtoLP(0)));

			GridData constraintProcedureExecutableHolder1 = new GridData();
			constraintProcedureExecutableHolder1.verticalAlignment = GridData.CENTER;
			constraintProcedureExecutableHolder1.horizontalAlignment = GridData.CENTER;
			constraintProcedureExecutableHolder1.horizontalIndent = 0;
			constraintProcedureExecutableHolder1.horizontalSpan = 0;
			constraintProcedureExecutableHolder1.verticalSpan = 0;
			constraintProcedureExecutableHolder1.grabExcessHorizontalSpace = true;
			constraintProcedureExecutableHolder1.grabExcessVerticalSpace = false;
			procedureNameHolder0.add(procedureExecutableHolder1,
					constraintProcedureExecutableHolder1);

			GridLayout layoutProcedureExecutableHolder1 = new GridLayout();
			layoutProcedureExecutableHolder1.numColumns = 3;
			layoutProcedureExecutableHolder1.makeColumnsEqualWidth = false;
			layoutProcedureExecutableHolder1.horizontalSpacing = 0;
			layoutProcedureExecutableHolder1.verticalSpacing = 0;
			layoutProcedureExecutableHolder1.marginWidth = 0;
			layoutProcedureExecutableHolder1.marginHeight = 0;
			procedureExecutableHolder1
					.setLayoutManager(layoutProcedureExecutableHolder1);

			fFigureProcedureExecutablePrecedingFigure = new WrappingLabel();
			fFigureProcedureExecutablePrecedingFigure.setText("");
			fFigureProcedureExecutablePrecedingFigure
					.setForegroundColor(ColorConstants.black);

			fFigureProcedureExecutablePrecedingFigure
					.setFont(FFIGUREPROCEDUREEXECUTABLEPRECEDINGFIGURE_FONT);

			fFigureProcedureExecutablePrecedingFigure
					.setBorder(new MarginBorder(getMapMode().DPtoLP(0),
							getMapMode().DPtoLP(0), getMapMode().DPtoLP(0),
							getMapMode().DPtoLP(0)));

			GridData constraintFFigureProcedureExecutablePrecedingFigure = new GridData();
			constraintFFigureProcedureExecutablePrecedingFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureProcedureExecutablePrecedingFigure.horizontalAlignment = GridData.CENTER;
			constraintFFigureProcedureExecutablePrecedingFigure.horizontalIndent = 0;
			constraintFFigureProcedureExecutablePrecedingFigure.horizontalSpan = 0;
			constraintFFigureProcedureExecutablePrecedingFigure.verticalSpan = 0;
			constraintFFigureProcedureExecutablePrecedingFigure.grabExcessHorizontalSpace = true;
			constraintFFigureProcedureExecutablePrecedingFigure.grabExcessVerticalSpace = false;
			constraintFFigureProcedureExecutablePrecedingFigure.widthHint = 20;
			constraintFFigureProcedureExecutablePrecedingFigure.heightHint = 15;
			procedureExecutableHolder1.add(
					fFigureProcedureExecutablePrecedingFigure,
					constraintFFigureProcedureExecutablePrecedingFigure);

			fFigureProcedureExecutableFigure = new WrappingLabel();
			fFigureProcedureExecutableFigure.setText("");

			fFigureProcedureExecutableFigure
					.setFont(FFIGUREPROCEDUREEXECUTABLEFIGURE_FONT);

			fFigureProcedureExecutableFigure.setBorder(new MarginBorder(
					getMapMode().DPtoLP(-1), getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0), getMapMode().DPtoLP(0)));

			GridData constraintFFigureProcedureExecutableFigure = new GridData();
			constraintFFigureProcedureExecutableFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureProcedureExecutableFigure.horizontalAlignment = GridData.CENTER;
			constraintFFigureProcedureExecutableFigure.horizontalIndent = 0;
			constraintFFigureProcedureExecutableFigure.horizontalSpan = 0;
			constraintFFigureProcedureExecutableFigure.verticalSpan = 0;
			constraintFFigureProcedureExecutableFigure.grabExcessHorizontalSpace = true;
			constraintFFigureProcedureExecutableFigure.grabExcessVerticalSpace = false;
			procedureExecutableHolder1.add(fFigureProcedureExecutableFigure,
					constraintFFigureProcedureExecutableFigure);

			fFigureProcedureExecutableNameFigure = new WrappingLabel();
			fFigureProcedureExecutableNameFigure.setText("");
			fFigureProcedureExecutableNameFigure
					.setForegroundColor(ColorConstants.black);

			fFigureProcedureExecutableNameFigure
					.setFont(FFIGUREPROCEDUREEXECUTABLENAMEFIGURE_FONT);

			fFigureProcedureExecutableNameFigure.setBorder(new MarginBorder(
					getMapMode().DPtoLP(-1), getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0), getMapMode().DPtoLP(0)));

			GridData constraintFFigureProcedureExecutableNameFigure = new GridData();
			constraintFFigureProcedureExecutableNameFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureProcedureExecutableNameFigure.horizontalAlignment = GridData.CENTER;
			constraintFFigureProcedureExecutableNameFigure.horizontalIndent = 0;
			constraintFFigureProcedureExecutableNameFigure.horizontalSpan = 0;
			constraintFFigureProcedureExecutableNameFigure.verticalSpan = 0;
			constraintFFigureProcedureExecutableNameFigure.grabExcessHorizontalSpace = true;
			constraintFFigureProcedureExecutableNameFigure.grabExcessVerticalSpace = false;
			procedureExecutableHolder1.add(
					fFigureProcedureExecutableNameFigure,
					constraintFFigureProcedureExecutableNameFigure);

			fFigureProcedureSplitFigure = new WrappingLabel();
			fFigureProcedureSplitFigure.setText("");
			fFigureProcedureSplitFigure.setBorder(new MarginBorder(getMapMode()
					.DPtoLP(1), getMapMode().DPtoLP(1), getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0)));

			GridData constraintFFigureProcedureSplitFigure = new GridData();
			constraintFFigureProcedureSplitFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureProcedureSplitFigure.horizontalAlignment = GridData.END;
			constraintFFigureProcedureSplitFigure.horizontalIndent = 0;
			constraintFFigureProcedureSplitFigure.horizontalSpan = 1;
			constraintFFigureProcedureSplitFigure.verticalSpan = 1;
			constraintFFigureProcedureSplitFigure.grabExcessHorizontalSpace = false;
			constraintFFigureProcedureSplitFigure.grabExcessVerticalSpace = false;
			constraintFFigureProcedureSplitFigure.widthHint = 43;
			constraintFFigureProcedureSplitFigure.heightHint = 43;
			this.add(fFigureProcedureSplitFigure,
					constraintFFigureProcedureSplitFigure);

		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureProcedureNameFigure() {
			return fFigureProcedureNameFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureProcedureJoinFigure() {
			return fFigureProcedureJoinFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureProcedureSplitFigure() {
			return fFigureProcedureSplitFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureProcedureExecutableFigure() {
			return fFigureProcedureExecutableFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureProcedureExecutablePrecedingFigure() {
			return fFigureProcedureExecutablePrecedingFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureProcedureExecutableNameFigure() {
			return fFigureProcedureExecutableNameFigure;
		}

	}

	/**
	 * @generated
	 */
	static final Font FFIGUREPROCEDURENAMEFIGURE_FONT = new Font(
			Display.getCurrent(), Display.getDefault().getSystemFont()
					.getFontData()[0].getName(), 10, SWT.BOLD);

	/**
	 * @generated
	 */
	static final Font FFIGUREPROCEDUREEXECUTABLEPRECEDINGFIGURE_FONT = new Font(
			Display.getCurrent(), Display.getDefault().getSystemFont()
					.getFontData()[0].getName(), 8, SWT.BOLD);

	/**
	 * @generated
	 */
	static final Font FFIGUREPROCEDUREEXECUTABLEFIGURE_FONT = new Font(
			Display.getCurrent(), Display.getDefault().getSystemFont()
					.getFontData()[0].getName(), 8, SWT.NORMAL);

	/**
	 * @generated
	 */
	static final Font FFIGUREPROCEDUREEXECUTABLENAMEFIGURE_FONT = new Font(
			Display.getCurrent(), Display.getDefault().getSystemFont()
					.getFontData()[0].getName(), 8, SWT.BOLD);

}
