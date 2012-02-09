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
import org.parallelj.designer.edit.policies.BlockItemSemanticEditPolicy;
import org.parallelj.designer.part.ParallelJVisualIDRegistry;
import org.parallelj.designer.providers.ParallelJElementTypes;

/**
 * @generated
 */
public class BlockEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 3012;

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
	public BlockEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new BlockItemSemanticEditPolicy());
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
		return primaryShape = new BlockFigure();
	}

	/**
	 * @generated
	 */
	public BlockFigure getPrimaryShape() {
		return (BlockFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof BlockNameEditPart) {
			((BlockNameEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigureBlockNameFigure());
			return true;
		}
		if (childEditPart instanceof BlockIconEditPart) {
			((BlockIconEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigureBlockIconFigure());
			return true;
		}
		if (childEditPart instanceof BlockBlockCompartmentEditPart) {
			IFigure pane = getPrimaryShape().getFigureBlockCompartmentFigure();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way 
			pane.add(((BlockBlockCompartmentEditPart) childEditPart)
					.getFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof BlockNameEditPart) {
			return true;
		}
		if (childEditPart instanceof BlockIconEditPart) {
			return true;
		}
		if (childEditPart instanceof BlockBlockCompartmentEditPart) {
			IFigure pane = getPrimaryShape().getFigureBlockCompartmentFigure();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way 
			pane.remove(((BlockBlockCompartmentEditPart) childEditPart)
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
		if (editPart instanceof BlockBlockCompartmentEditPart) {
			return getPrimaryShape().getFigureBlockCompartmentFigure();
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
				.getType(BlockNameEditPart.VISUAL_ID));
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
		if (targetEditPart instanceof org.parallelj.designer.edit.parts.BlockEditPart) {
			types.add(ParallelJElementTypes.Link_4001);
		}
		if (targetEditPart instanceof BlockProcedureEditPart) {
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
			types.add(ParallelJElementTypes.Block_3012);
			types.add(ParallelJElementTypes.Procedure_3013);
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
			types.add(ParallelJElementTypes.Block_3012);
			types.add(ParallelJElementTypes.Procedure_3013);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public class BlockFigure extends RectangleFigure {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureBlockRightFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureBlockLeftFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureBlockIconFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureBlockNameFigure;
		/**
		 * @generated
		 */
		private RectangleFigure fFigureBlockCompartmentFigure;

		/**
		 * @generated
		 */
		public BlockFigure() {

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

			RectangleFigure blockUpperFigure0 = new RectangleFigure();
			blockUpperFigure0.setFill(false);
			blockUpperFigure0.setOutline(false);

			GridData constraintBlockUpperFigure0 = new GridData();
			constraintBlockUpperFigure0.verticalAlignment = GridData.FILL;
			constraintBlockUpperFigure0.horizontalAlignment = GridData.FILL;
			constraintBlockUpperFigure0.horizontalIndent = 0;
			constraintBlockUpperFigure0.horizontalSpan = 0;
			constraintBlockUpperFigure0.verticalSpan = 0;
			constraintBlockUpperFigure0.grabExcessHorizontalSpace = true;
			constraintBlockUpperFigure0.grabExcessVerticalSpace = false;
			this.add(blockUpperFigure0, constraintBlockUpperFigure0);

			GridLayout layoutBlockUpperFigure0 = new GridLayout();
			layoutBlockUpperFigure0.numColumns = 3;
			layoutBlockUpperFigure0.makeColumnsEqualWidth = false;
			layoutBlockUpperFigure0.horizontalSpacing = 0;
			layoutBlockUpperFigure0.verticalSpacing = 0;
			layoutBlockUpperFigure0.marginWidth = 0;
			layoutBlockUpperFigure0.marginHeight = 0;
			blockUpperFigure0.setLayoutManager(layoutBlockUpperFigure0);

			fFigureBlockLeftFigure = new WrappingLabel();
			fFigureBlockLeftFigure.setText("");
			fFigureBlockLeftFigure.setBorder(new MarginBorder(getMapMode()
					.DPtoLP(1), getMapMode().DPtoLP(3), getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0)));

			GridData constraintFFigureBlockLeftFigure = new GridData();
			constraintFFigureBlockLeftFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureBlockLeftFigure.horizontalAlignment = GridData.BEGINNING;
			constraintFFigureBlockLeftFigure.horizontalIndent = 0;
			constraintFFigureBlockLeftFigure.horizontalSpan = 1;
			constraintFFigureBlockLeftFigure.verticalSpan = 1;
			constraintFFigureBlockLeftFigure.grabExcessHorizontalSpace = false;
			constraintFFigureBlockLeftFigure.grabExcessVerticalSpace = false;
			constraintFFigureBlockLeftFigure.widthHint = 43;
			constraintFFigureBlockLeftFigure.heightHint = 32;
			blockUpperFigure0.add(fFigureBlockLeftFigure,
					constraintFFigureBlockLeftFigure);

			RectangleFigure blockNameHolder1 = new RectangleFigure();
			blockNameHolder1.setFill(false);
			blockNameHolder1.setOutline(false);
			blockNameHolder1.setBorder(new MarginBorder(getMapMode().DPtoLP(5),
					getMapMode().DPtoLP(0), getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0)));

			GridData constraintBlockNameHolder1 = new GridData();
			constraintBlockNameHolder1.verticalAlignment = GridData.BEGINNING;
			constraintBlockNameHolder1.horizontalAlignment = GridData.CENTER;
			constraintBlockNameHolder1.horizontalIndent = 0;
			constraintBlockNameHolder1.horizontalSpan = 1;
			constraintBlockNameHolder1.verticalSpan = 1;
			constraintBlockNameHolder1.grabExcessHorizontalSpace = true;
			constraintBlockNameHolder1.grabExcessVerticalSpace = false;
			blockUpperFigure0.add(blockNameHolder1, constraintBlockNameHolder1);

			GridLayout layoutBlockNameHolder1 = new GridLayout();
			layoutBlockNameHolder1.numColumns = 2;
			layoutBlockNameHolder1.makeColumnsEqualWidth = false;
			layoutBlockNameHolder1.horizontalSpacing = 0;
			layoutBlockNameHolder1.verticalSpacing = 0;
			layoutBlockNameHolder1.marginWidth = 0;
			layoutBlockNameHolder1.marginHeight = 0;
			blockNameHolder1.setLayoutManager(layoutBlockNameHolder1);

			fFigureBlockIconFigure = new WrappingLabel();
			fFigureBlockIconFigure.setText("");

			GridData constraintFFigureBlockIconFigure = new GridData();
			constraintFFigureBlockIconFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureBlockIconFigure.horizontalAlignment = GridData.CENTER;
			constraintFFigureBlockIconFigure.horizontalIndent = 0;
			constraintFFigureBlockIconFigure.horizontalSpan = 0;
			constraintFFigureBlockIconFigure.verticalSpan = 0;
			constraintFFigureBlockIconFigure.grabExcessHorizontalSpace = true;
			constraintFFigureBlockIconFigure.grabExcessVerticalSpace = false;
			blockNameHolder1.add(fFigureBlockIconFigure,
					constraintFFigureBlockIconFigure);

			fFigureBlockNameFigure = new WrappingLabel();
			fFigureBlockNameFigure.setText("<...>");

			fFigureBlockNameFigure.setFont(FFIGUREBLOCKNAMEFIGURE_FONT);

			GridData constraintFFigureBlockNameFigure = new GridData();
			constraintFFigureBlockNameFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureBlockNameFigure.horizontalAlignment = GridData.CENTER;
			constraintFFigureBlockNameFigure.horizontalIndent = 0;
			constraintFFigureBlockNameFigure.horizontalSpan = 0;
			constraintFFigureBlockNameFigure.verticalSpan = 0;
			constraintFFigureBlockNameFigure.grabExcessHorizontalSpace = true;
			constraintFFigureBlockNameFigure.grabExcessVerticalSpace = false;
			blockNameHolder1.add(fFigureBlockNameFigure,
					constraintFFigureBlockNameFigure);

			fFigureBlockRightFigure = new WrappingLabel();
			fFigureBlockRightFigure.setText("");
			fFigureBlockRightFigure.setBorder(new MarginBorder(getMapMode()
					.DPtoLP(1), getMapMode().DPtoLP(1), getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0)));

			GridData constraintFFigureBlockRightFigure = new GridData();
			constraintFFigureBlockRightFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureBlockRightFigure.horizontalAlignment = GridData.END;
			constraintFFigureBlockRightFigure.horizontalIndent = 0;
			constraintFFigureBlockRightFigure.horizontalSpan = 1;
			constraintFFigureBlockRightFigure.verticalSpan = 1;
			constraintFFigureBlockRightFigure.grabExcessHorizontalSpace = false;
			constraintFFigureBlockRightFigure.grabExcessVerticalSpace = false;
			constraintFFigureBlockRightFigure.widthHint = 43;
			constraintFFigureBlockRightFigure.heightHint = 32;
			blockUpperFigure0.add(fFigureBlockRightFigure,
					constraintFFigureBlockRightFigure);

			RectangleFigure blockCompartmentHolder0 = new RectangleFigure();
			blockCompartmentHolder0.setFill(false);
			blockCompartmentHolder0.setOutline(false);

			GridData constraintBlockCompartmentHolder0 = new GridData();
			constraintBlockCompartmentHolder0.verticalAlignment = GridData.FILL;
			constraintBlockCompartmentHolder0.horizontalAlignment = GridData.FILL;
			constraintBlockCompartmentHolder0.horizontalIndent = 0;
			constraintBlockCompartmentHolder0.horizontalSpan = 0;
			constraintBlockCompartmentHolder0.verticalSpan = 0;
			constraintBlockCompartmentHolder0.grabExcessHorizontalSpace = true;
			constraintBlockCompartmentHolder0.grabExcessVerticalSpace = true;
			this.add(blockCompartmentHolder0, constraintBlockCompartmentHolder0);

			GridLayout layoutBlockCompartmentHolder0 = new GridLayout();
			layoutBlockCompartmentHolder0.numColumns = 1;
			layoutBlockCompartmentHolder0.makeColumnsEqualWidth = true;
			layoutBlockCompartmentHolder0.horizontalSpacing = 0;
			layoutBlockCompartmentHolder0.verticalSpacing = 0;
			layoutBlockCompartmentHolder0.marginWidth = 0;
			layoutBlockCompartmentHolder0.marginHeight = 0;
			blockCompartmentHolder0
					.setLayoutManager(layoutBlockCompartmentHolder0);

			fFigureBlockCompartmentFigure = new RectangleFigure();
			fFigureBlockCompartmentFigure
					.setForegroundColor(ColorConstants.black);
			fFigureBlockCompartmentFigure.setMinimumSize(new Dimension(
					getMapMode().DPtoLP(255), getMapMode().DPtoLP(58)));

			GridData constraintFFigureBlockCompartmentFigure = new GridData();
			constraintFFigureBlockCompartmentFigure.verticalAlignment = GridData.FILL;
			constraintFFigureBlockCompartmentFigure.horizontalAlignment = GridData.FILL;
			constraintFFigureBlockCompartmentFigure.horizontalIndent = 0;
			constraintFFigureBlockCompartmentFigure.horizontalSpan = 0;
			constraintFFigureBlockCompartmentFigure.verticalSpan = 0;
			constraintFFigureBlockCompartmentFigure.grabExcessHorizontalSpace = true;
			constraintFFigureBlockCompartmentFigure.grabExcessVerticalSpace = true;
			blockCompartmentHolder0.add(fFigureBlockCompartmentFigure,
					constraintFFigureBlockCompartmentFigure);

		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureBlockRightFigure() {
			return fFigureBlockRightFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureBlockLeftFigure() {
			return fFigureBlockLeftFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureBlockIconFigure() {
			return fFigureBlockIconFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureBlockNameFigure() {
			return fFigureBlockNameFigure;
		}

		/**
		 * @generated
		 */
		public RectangleFigure getFigureBlockCompartmentFigure() {
			return fFigureBlockCompartmentFigure;
		}

	}

	/**
	 * @generated
	 */
	static final Font FFIGUREBLOCKNAMEFIGURE_FONT = new Font(
			Display.getCurrent(), Display.getDefault().getSystemFont()
					.getFontData()[0].getName(), 10, SWT.BOLD);

}
