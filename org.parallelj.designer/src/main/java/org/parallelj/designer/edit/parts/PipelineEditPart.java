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
import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
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
import org.parallelj.designer.edit.policies.PipelineItemSemanticEditPolicy;
import org.parallelj.designer.part.ParallelJVisualIDRegistry;
import org.parallelj.designer.providers.ParallelJElementTypes;

/**
 * @generated
 */
public class PipelineEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 3009;

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
	public PipelineEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new PipelineItemSemanticEditPolicy());
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
		return primaryShape = new PipelineFigure();
	}

	/**
	 * @generated
	 */
	public PipelineFigure getPrimaryShape() {
		return (PipelineFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof PipelineNameEditPart) {
			((PipelineNameEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigurePipelineNameFigure());
			return true;
		}
		if (childEditPart instanceof PipelineIconEditPart) {
			((PipelineIconEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigurePipelineIconFigure());
			return true;
		}
		if (childEditPart instanceof PipelinePipelineCompartmentEditPart) {
			IFigure pane = getPrimaryShape()
					.getFigurePipelineCompartmentFigure();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way 
			pane.add(((PipelinePipelineCompartmentEditPart) childEditPart)
					.getFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof PipelineNameEditPart) {
			return true;
		}
		if (childEditPart instanceof PipelineIconEditPart) {
			return true;
		}
		if (childEditPart instanceof PipelinePipelineCompartmentEditPart) {
			IFigure pane = getPrimaryShape()
					.getFigurePipelineCompartmentFigure();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way 
			pane.remove(((PipelinePipelineCompartmentEditPart) childEditPart)
					.getFigure());
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
		if (editPart instanceof PipelinePipelineCompartmentEditPart) {
			return getPrimaryShape().getFigurePipelineCompartmentFigure();
		}
		return getContentPane();
	}

	/**
	 * @generated
	 */
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(284, 89);
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
				.getType(PipelineNameEditPart.VISUAL_ID));
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
		if (targetEditPart instanceof org.parallelj.designer.edit.parts.PipelineEditPart) {
			types.add(ParallelJElementTypes.Link_4001);
		}
		if (targetEditPart instanceof PipelineProcedureEditPart) {
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
	public class PipelineFigure extends RectangleFigure {

		/**
		 * @generated
		 */
		private WrappingLabel fFigurePipelineJoinFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigurePipelineIconFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigurePipelineNameFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigurePipelineSplitFigure;
		/**
		 * @generated
		 */
		private RectangleFigure fFigurePipelineCompartmentFigure;

		/**
		 * @generated
		 */
		public PipelineFigure() {

			GridLayout layoutThis = new GridLayout();
			layoutThis.numColumns = 1;
			layoutThis.makeColumnsEqualWidth = true;
			layoutThis.horizontalSpacing = 0;
			layoutThis.verticalSpacing = 0;
			layoutThis.marginWidth = 0;
			layoutThis.marginHeight = 0;
			this.setLayoutManager(layoutThis);

			this.setOutline(false);
			this.setPreferredSize(new Dimension(getMapMode().DPtoLP(284),
					getMapMode().DPtoLP(89)));
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			RectangleFigure pipelineUpperFigure0 = new RectangleFigure();
			pipelineUpperFigure0.setFill(false);
			pipelineUpperFigure0.setOutline(false);

			GridData constraintPipelineUpperFigure0 = new GridData();
			constraintPipelineUpperFigure0.verticalAlignment = GridData.FILL;
			constraintPipelineUpperFigure0.horizontalAlignment = GridData.FILL;
			constraintPipelineUpperFigure0.horizontalIndent = 0;
			constraintPipelineUpperFigure0.horizontalSpan = 0;
			constraintPipelineUpperFigure0.verticalSpan = 0;
			constraintPipelineUpperFigure0.grabExcessHorizontalSpace = true;
			constraintPipelineUpperFigure0.grabExcessVerticalSpace = false;
			this.add(pipelineUpperFigure0, constraintPipelineUpperFigure0);

			GridLayout layoutPipelineUpperFigure0 = new GridLayout();
			layoutPipelineUpperFigure0.numColumns = 3;
			layoutPipelineUpperFigure0.makeColumnsEqualWidth = false;
			layoutPipelineUpperFigure0.horizontalSpacing = 0;
			layoutPipelineUpperFigure0.verticalSpacing = 0;
			layoutPipelineUpperFigure0.marginWidth = 0;
			layoutPipelineUpperFigure0.marginHeight = 0;
			pipelineUpperFigure0.setLayoutManager(layoutPipelineUpperFigure0);

			fFigurePipelineJoinFigure = new WrappingLabel();
			fFigurePipelineJoinFigure.setText("");
			fFigurePipelineJoinFigure.setBorder(new MarginBorder(getMapMode()
					.DPtoLP(1), getMapMode().DPtoLP(3), getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0)));

			GridData constraintFFigurePipelineJoinFigure = new GridData();
			constraintFFigurePipelineJoinFigure.verticalAlignment = GridData.CENTER;
			constraintFFigurePipelineJoinFigure.horizontalAlignment = GridData.BEGINNING;
			constraintFFigurePipelineJoinFigure.horizontalIndent = 0;
			constraintFFigurePipelineJoinFigure.horizontalSpan = 1;
			constraintFFigurePipelineJoinFigure.verticalSpan = 1;
			constraintFFigurePipelineJoinFigure.grabExcessHorizontalSpace = false;
			constraintFFigurePipelineJoinFigure.grabExcessVerticalSpace = false;
			constraintFFigurePipelineJoinFigure.widthHint = 43;
			constraintFFigurePipelineJoinFigure.heightHint = 32;
			pipelineUpperFigure0.add(fFigurePipelineJoinFigure,
					constraintFFigurePipelineJoinFigure);

			RectangleFigure pipelineNameHolder1 = new RectangleFigure();
			pipelineNameHolder1.setFill(false);
			pipelineNameHolder1.setOutline(false);
			pipelineNameHolder1.setBorder(new MarginBorder(getMapMode().DPtoLP(
					5), getMapMode().DPtoLP(0), getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0)));

			GridData constraintPipelineNameHolder1 = new GridData();
			constraintPipelineNameHolder1.verticalAlignment = GridData.BEGINNING;
			constraintPipelineNameHolder1.horizontalAlignment = GridData.CENTER;
			constraintPipelineNameHolder1.horizontalIndent = 0;
			constraintPipelineNameHolder1.horizontalSpan = 1;
			constraintPipelineNameHolder1.verticalSpan = 1;
			constraintPipelineNameHolder1.grabExcessHorizontalSpace = true;
			constraintPipelineNameHolder1.grabExcessVerticalSpace = false;
			pipelineUpperFigure0.add(pipelineNameHolder1,
					constraintPipelineNameHolder1);

			GridLayout layoutPipelineNameHolder1 = new GridLayout();
			layoutPipelineNameHolder1.numColumns = 2;
			layoutPipelineNameHolder1.makeColumnsEqualWidth = false;
			layoutPipelineNameHolder1.horizontalSpacing = 0;
			layoutPipelineNameHolder1.verticalSpacing = 0;
			layoutPipelineNameHolder1.marginWidth = 0;
			layoutPipelineNameHolder1.marginHeight = 0;
			pipelineNameHolder1.setLayoutManager(layoutPipelineNameHolder1);

			fFigurePipelineIconFigure = new WrappingLabel();
			fFigurePipelineIconFigure.setText("");

			GridData constraintFFigurePipelineIconFigure = new GridData();
			constraintFFigurePipelineIconFigure.verticalAlignment = GridData.CENTER;
			constraintFFigurePipelineIconFigure.horizontalAlignment = GridData.CENTER;
			constraintFFigurePipelineIconFigure.horizontalIndent = 0;
			constraintFFigurePipelineIconFigure.horizontalSpan = 0;
			constraintFFigurePipelineIconFigure.verticalSpan = 0;
			constraintFFigurePipelineIconFigure.grabExcessHorizontalSpace = true;
			constraintFFigurePipelineIconFigure.grabExcessVerticalSpace = false;
			pipelineNameHolder1.add(fFigurePipelineIconFigure,
					constraintFFigurePipelineIconFigure);

			fFigurePipelineNameFigure = new WrappingLabel();
			fFigurePipelineNameFigure.setText("<...>");

			fFigurePipelineNameFigure.setFont(FFIGUREPIPELINENAMEFIGURE_FONT);

			GridData constraintFFigurePipelineNameFigure = new GridData();
			constraintFFigurePipelineNameFigure.verticalAlignment = GridData.CENTER;
			constraintFFigurePipelineNameFigure.horizontalAlignment = GridData.CENTER;
			constraintFFigurePipelineNameFigure.horizontalIndent = 0;
			constraintFFigurePipelineNameFigure.horizontalSpan = 0;
			constraintFFigurePipelineNameFigure.verticalSpan = 0;
			constraintFFigurePipelineNameFigure.grabExcessHorizontalSpace = true;
			constraintFFigurePipelineNameFigure.grabExcessVerticalSpace = false;
			pipelineNameHolder1.add(fFigurePipelineNameFigure,
					constraintFFigurePipelineNameFigure);

			fFigurePipelineSplitFigure = new WrappingLabel();
			fFigurePipelineSplitFigure.setText("");
			fFigurePipelineSplitFigure.setBorder(new MarginBorder(getMapMode()
					.DPtoLP(1), getMapMode().DPtoLP(1), getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0)));

			GridData constraintFFigurePipelineSplitFigure = new GridData();
			constraintFFigurePipelineSplitFigure.verticalAlignment = GridData.CENTER;
			constraintFFigurePipelineSplitFigure.horizontalAlignment = GridData.END;
			constraintFFigurePipelineSplitFigure.horizontalIndent = 0;
			constraintFFigurePipelineSplitFigure.horizontalSpan = 1;
			constraintFFigurePipelineSplitFigure.verticalSpan = 1;
			constraintFFigurePipelineSplitFigure.grabExcessHorizontalSpace = false;
			constraintFFigurePipelineSplitFigure.grabExcessVerticalSpace = false;
			constraintFFigurePipelineSplitFigure.widthHint = 43;
			constraintFFigurePipelineSplitFigure.heightHint = 32;
			pipelineUpperFigure0.add(fFigurePipelineSplitFigure,
					constraintFFigurePipelineSplitFigure);

			RectangleFigure pipelineCompartmentHolder0 = new RectangleFigure();
			pipelineCompartmentHolder0.setFill(false);
			pipelineCompartmentHolder0.setOutline(false);

			GridData constraintPipelineCompartmentHolder0 = new GridData();
			constraintPipelineCompartmentHolder0.verticalAlignment = GridData.FILL;
			constraintPipelineCompartmentHolder0.horizontalAlignment = GridData.FILL;
			constraintPipelineCompartmentHolder0.horizontalIndent = 0;
			constraintPipelineCompartmentHolder0.horizontalSpan = 0;
			constraintPipelineCompartmentHolder0.verticalSpan = 0;
			constraintPipelineCompartmentHolder0.grabExcessHorizontalSpace = true;
			constraintPipelineCompartmentHolder0.grabExcessVerticalSpace = true;
			this.add(pipelineCompartmentHolder0,
					constraintPipelineCompartmentHolder0);

			GridLayout layoutPipelineCompartmentHolder0 = new GridLayout();
			layoutPipelineCompartmentHolder0.numColumns = 1;
			layoutPipelineCompartmentHolder0.makeColumnsEqualWidth = true;
			layoutPipelineCompartmentHolder0.horizontalSpacing = 0;
			layoutPipelineCompartmentHolder0.verticalSpacing = 0;
			layoutPipelineCompartmentHolder0.marginWidth = 0;
			layoutPipelineCompartmentHolder0.marginHeight = 0;
			pipelineCompartmentHolder0
					.setLayoutManager(layoutPipelineCompartmentHolder0);

			fFigurePipelineCompartmentFigure = new RectangleFigure();
			fFigurePipelineCompartmentFigure
					.setForegroundColor(ColorConstants.black);
			fFigurePipelineCompartmentFigure.setMinimumSize(new Dimension(
					getMapMode().DPtoLP(255), getMapMode().DPtoLP(58)));

			GridData constraintFFigurePipelineCompartmentFigure = new GridData();
			constraintFFigurePipelineCompartmentFigure.verticalAlignment = GridData.FILL;
			constraintFFigurePipelineCompartmentFigure.horizontalAlignment = GridData.FILL;
			constraintFFigurePipelineCompartmentFigure.horizontalIndent = 0;
			constraintFFigurePipelineCompartmentFigure.horizontalSpan = 0;
			constraintFFigurePipelineCompartmentFigure.verticalSpan = 0;
			constraintFFigurePipelineCompartmentFigure.grabExcessHorizontalSpace = true;
			constraintFFigurePipelineCompartmentFigure.grabExcessVerticalSpace = true;
			pipelineCompartmentHolder0.add(fFigurePipelineCompartmentFigure,
					constraintFFigurePipelineCompartmentFigure);

		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigurePipelineJoinFigure() {
			return fFigurePipelineJoinFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigurePipelineIconFigure() {
			return fFigurePipelineIconFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigurePipelineNameFigure() {
			return fFigurePipelineNameFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigurePipelineSplitFigure() {
			return fFigurePipelineSplitFigure;
		}

		/**
		 * @generated
		 */
		public RectangleFigure getFigurePipelineCompartmentFigure() {
			return fFigurePipelineCompartmentFigure;
		}

	}

	/**
	 * @generated
	 */
	static final Font FFIGUREPIPELINENAMEFIGURE_FONT = new Font(
			Display.getCurrent(), Display.getDefault().getSystemFont()
					.getFontData()[0].getName(), 10, SWT.BOLD);

}
