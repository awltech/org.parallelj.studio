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
import org.parallelj.designer.edit.policies.BusinessProcedureItemSemanticEditPolicy;
import org.parallelj.designer.part.ParallelJVisualIDRegistry;
import org.parallelj.designer.providers.ParallelJElementTypes;

/**
 * @generated
 */
public class BusinessProcedureEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 3014;

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
	public BusinessProcedureEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new BusinessProcedureItemSemanticEditPolicy());
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
		return primaryShape = new BusinessProcedureFigure();
	}

	/**
	 * @generated
	 */
	public BusinessProcedureFigure getPrimaryShape() {
		return (BusinessProcedureFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof BusinessProcedureNameEditPart) {
			((BusinessProcedureNameEditPart) childEditPart)
					.setLabel(getPrimaryShape()
							.getFigureBusinessProcedureNameFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof BusinessProcedureNameEditPart) {
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
				.getType(BusinessProcedureNameEditPart.VISUAL_ID));
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
		if (targetEditPart instanceof PipelineProcedureEditPart) {
			types.add(ParallelJElementTypes.Link_4001);
		}
		if (targetEditPart instanceof org.parallelj.designer.edit.parts.BusinessProcedureEditPart) {
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
	public class BusinessProcedureFigure extends RoundedRectangle {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureBusinessProcedureNameFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureBusinessProcedureJoinFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureBusinessProcedureSplitFigure;

		/**
		 * @generated
		 */
		public BusinessProcedureFigure() {

			GridLayout layoutThis = new GridLayout();
			layoutThis.numColumns = 3;
			layoutThis.makeColumnsEqualWidth = true;
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

			fFigureBusinessProcedureJoinFigure = new WrappingLabel();
			fFigureBusinessProcedureJoinFigure.setText("");
			fFigureBusinessProcedureJoinFigure.setBorder(new MarginBorder(
					getMapMode().DPtoLP(1), getMapMode().DPtoLP(3),
					getMapMode().DPtoLP(0), getMapMode().DPtoLP(0)));

			GridData constraintFFigureBusinessProcedureJoinFigure = new GridData();
			constraintFFigureBusinessProcedureJoinFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureBusinessProcedureJoinFigure.horizontalAlignment = GridData.BEGINNING;
			constraintFFigureBusinessProcedureJoinFigure.horizontalIndent = 0;
			constraintFFigureBusinessProcedureJoinFigure.horizontalSpan = 1;
			constraintFFigureBusinessProcedureJoinFigure.verticalSpan = 1;
			constraintFFigureBusinessProcedureJoinFigure.grabExcessHorizontalSpace = false;
			constraintFFigureBusinessProcedureJoinFigure.grabExcessVerticalSpace = false;
			constraintFFigureBusinessProcedureJoinFigure.widthHint = 43;
			constraintFFigureBusinessProcedureJoinFigure.heightHint = 43;
			this.add(fFigureBusinessProcedureJoinFigure,
					constraintFFigureBusinessProcedureJoinFigure);

			RectangleFigure businessProcedureNameHolder0 = new RectangleFigure();
			businessProcedureNameHolder0.setFill(false);
			businessProcedureNameHolder0.setOutline(false);
			businessProcedureNameHolder0.setBorder(new MarginBorder(
					getMapMode().DPtoLP(3), getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0), getMapMode().DPtoLP(0)));

			GridData constraintBusinessProcedureNameHolder0 = new GridData();
			constraintBusinessProcedureNameHolder0.verticalAlignment = GridData.BEGINNING;
			constraintBusinessProcedureNameHolder0.horizontalAlignment = GridData.CENTER;
			constraintBusinessProcedureNameHolder0.horizontalIndent = 0;
			constraintBusinessProcedureNameHolder0.horizontalSpan = 1;
			constraintBusinessProcedureNameHolder0.verticalSpan = 1;
			constraintBusinessProcedureNameHolder0.grabExcessHorizontalSpace = true;
			constraintBusinessProcedureNameHolder0.grabExcessVerticalSpace = false;
			this.add(businessProcedureNameHolder0,
					constraintBusinessProcedureNameHolder0);

			GridLayout layoutBusinessProcedureNameHolder0 = new GridLayout();
			layoutBusinessProcedureNameHolder0.numColumns = 1;
			layoutBusinessProcedureNameHolder0.makeColumnsEqualWidth = true;
			layoutBusinessProcedureNameHolder0.horizontalSpacing = 0;
			layoutBusinessProcedureNameHolder0.verticalSpacing = 0;
			layoutBusinessProcedureNameHolder0.marginWidth = 0;
			layoutBusinessProcedureNameHolder0.marginHeight = 0;
			businessProcedureNameHolder0
					.setLayoutManager(layoutBusinessProcedureNameHolder0);

			fFigureBusinessProcedureNameFigure = new WrappingLabel();
			fFigureBusinessProcedureNameFigure.setText("<...>");

			fFigureBusinessProcedureNameFigure
					.setFont(FFIGUREBUSINESSPROCEDURENAMEFIGURE_FONT);

			GridData constraintFFigureBusinessProcedureNameFigure = new GridData();
			constraintFFigureBusinessProcedureNameFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureBusinessProcedureNameFigure.horizontalAlignment = GridData.CENTER;
			constraintFFigureBusinessProcedureNameFigure.horizontalIndent = 0;
			constraintFFigureBusinessProcedureNameFigure.horizontalSpan = 0;
			constraintFFigureBusinessProcedureNameFigure.verticalSpan = 0;
			constraintFFigureBusinessProcedureNameFigure.grabExcessHorizontalSpace = true;
			constraintFFigureBusinessProcedureNameFigure.grabExcessVerticalSpace = false;
			businessProcedureNameHolder0.add(
					fFigureBusinessProcedureNameFigure,
					constraintFFigureBusinessProcedureNameFigure);

			fFigureBusinessProcedureSplitFigure = new WrappingLabel();
			fFigureBusinessProcedureSplitFigure.setText("");
			fFigureBusinessProcedureSplitFigure.setBorder(new MarginBorder(
					getMapMode().DPtoLP(1), getMapMode().DPtoLP(1),
					getMapMode().DPtoLP(0), getMapMode().DPtoLP(0)));

			GridData constraintFFigureBusinessProcedureSplitFigure = new GridData();
			constraintFFigureBusinessProcedureSplitFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureBusinessProcedureSplitFigure.horizontalAlignment = GridData.END;
			constraintFFigureBusinessProcedureSplitFigure.horizontalIndent = 0;
			constraintFFigureBusinessProcedureSplitFigure.horizontalSpan = 1;
			constraintFFigureBusinessProcedureSplitFigure.verticalSpan = 1;
			constraintFFigureBusinessProcedureSplitFigure.grabExcessHorizontalSpace = false;
			constraintFFigureBusinessProcedureSplitFigure.grabExcessVerticalSpace = false;
			constraintFFigureBusinessProcedureSplitFigure.widthHint = 43;
			constraintFFigureBusinessProcedureSplitFigure.heightHint = 43;
			this.add(fFigureBusinessProcedureSplitFigure,
					constraintFFigureBusinessProcedureSplitFigure);

		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureBusinessProcedureNameFigure() {
			return fFigureBusinessProcedureNameFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureBusinessProcedureJoinFigure() {
			return fFigureBusinessProcedureJoinFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureBusinessProcedureSplitFigure() {
			return fFigureBusinessProcedureSplitFigure;
		}

	}

	/**
	 * @generated
	 */
	static final Font FFIGUREBUSINESSPROCEDURENAMEFIGURE_FONT = new Font(
			Display.getCurrent(), Display.getDefault().getSystemFont()
					.getFontData()[0].getName(), 10, SWT.BOLD);

}
