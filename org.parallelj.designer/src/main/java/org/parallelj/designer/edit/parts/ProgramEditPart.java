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

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
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
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.parallelj.designer.edit.policies.ProgramItemSemanticEditPolicy;
import org.parallelj.designer.part.ParallelJVisualIDRegistry;

/**
 * @generated
 */
public class ProgramEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 2001;

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
	public ProgramEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new ProgramItemSemanticEditPolicy());
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
		return primaryShape = new ProgramFigure();
	}

	/**
	 * @generated
	 */
	public ProgramFigure getPrimaryShape() {
		return (ProgramFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof ProgramNameEditPart) {
			((ProgramNameEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigureProgramNameFigure());
			return true;
		}
		if (childEditPart instanceof ProgramProgramCompartmentEditPart) {
			IFigure pane = getPrimaryShape()
					.getFigureProgramCompartmentFigure();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way 
			pane.add(((ProgramProgramCompartmentEditPart) childEditPart)
					.getFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof ProgramNameEditPart) {
			return true;
		}
		if (childEditPart instanceof ProgramProgramCompartmentEditPart) {
			IFigure pane = getPrimaryShape()
					.getFigureProgramCompartmentFigure();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way 
			pane.remove(((ProgramProgramCompartmentEditPart) childEditPart)
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
		if (editPart instanceof ProgramProgramCompartmentEditPart) {
			return getPrimaryShape().getFigureProgramCompartmentFigure();
		}
		return getContentPane();
	}

	/**
	 * @generated
	 */
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(500, 300);
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
				.getType(ProgramNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public class ProgramFigure extends RectangleFigure {

		/**
		 * @generated
		 */
		private RectangleFigure fFigureProgramCompartmentFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureProgramNameFigure;

		/**
		 * @generated
		 */
		public ProgramFigure() {

			GridLayout layoutThis = new GridLayout();
			layoutThis.numColumns = 1;
			layoutThis.makeColumnsEqualWidth = true;
			layoutThis.horizontalSpacing = 0;
			layoutThis.verticalSpacing = 0;
			layoutThis.marginWidth = 0;
			layoutThis.marginHeight = 0;
			this.setLayoutManager(layoutThis);

			this.setPreferredSize(new Dimension(getMapMode().DPtoLP(500),
					getMapMode().DPtoLP(300)));
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			RectangleFigure programUpperFigure0 = new RectangleFigure();
			programUpperFigure0.setFill(false);
			programUpperFigure0.setOutline(false);

			GridData constraintProgramUpperFigure0 = new GridData();
			constraintProgramUpperFigure0.verticalAlignment = GridData.CENTER;
			constraintProgramUpperFigure0.horizontalAlignment = GridData.FILL;
			constraintProgramUpperFigure0.horizontalIndent = 0;
			constraintProgramUpperFigure0.horizontalSpan = 0;
			constraintProgramUpperFigure0.verticalSpan = 0;
			constraintProgramUpperFigure0.grabExcessHorizontalSpace = true;
			constraintProgramUpperFigure0.grabExcessVerticalSpace = false;
			this.add(programUpperFigure0, constraintProgramUpperFigure0);

			GridLayout layoutProgramUpperFigure0 = new GridLayout();
			layoutProgramUpperFigure0.numColumns = 1;
			layoutProgramUpperFigure0.makeColumnsEqualWidth = true;
			layoutProgramUpperFigure0.horizontalSpacing = 0;
			layoutProgramUpperFigure0.verticalSpacing = 0;
			layoutProgramUpperFigure0.marginWidth = 0;
			layoutProgramUpperFigure0.marginHeight = 0;
			programUpperFigure0.setLayoutManager(layoutProgramUpperFigure0);

			RectangleFigure programNameHolder1 = new RectangleFigure();
			programNameHolder1.setFill(false);
			programNameHolder1.setOutline(false);
			programNameHolder1.setBorder(new MarginBorder(getMapMode()
					.DPtoLP(0), getMapMode().DPtoLP(0), getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0)));

			GridData constraintProgramNameHolder1 = new GridData();
			constraintProgramNameHolder1.verticalAlignment = GridData.CENTER;
			constraintProgramNameHolder1.horizontalAlignment = GridData.CENTER;
			constraintProgramNameHolder1.horizontalIndent = 0;
			constraintProgramNameHolder1.horizontalSpan = 1;
			constraintProgramNameHolder1.verticalSpan = 1;
			constraintProgramNameHolder1.grabExcessHorizontalSpace = true;
			constraintProgramNameHolder1.grabExcessVerticalSpace = true;
			constraintProgramNameHolder1.widthHint = 490;
			constraintProgramNameHolder1.heightHint = 28;
			programUpperFigure0.add(programNameHolder1,
					constraintProgramNameHolder1);

			GridLayout layoutProgramNameHolder1 = new GridLayout();
			layoutProgramNameHolder1.numColumns = 1;
			layoutProgramNameHolder1.makeColumnsEqualWidth = true;
			layoutProgramNameHolder1.horizontalSpacing = 0;
			layoutProgramNameHolder1.verticalSpacing = 0;
			layoutProgramNameHolder1.marginWidth = 0;
			layoutProgramNameHolder1.marginHeight = 0;
			programNameHolder1.setLayoutManager(layoutProgramNameHolder1);

			fFigureProgramNameFigure = new WrappingLabel();
			fFigureProgramNameFigure.setText("<...>");

			fFigureProgramNameFigure.setFont(FFIGUREPROGRAMNAMEFIGURE_FONT);

			GridData constraintFFigureProgramNameFigure = new GridData();
			constraintFFigureProgramNameFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureProgramNameFigure.horizontalAlignment = GridData.CENTER;
			constraintFFigureProgramNameFigure.horizontalIndent = 0;
			constraintFFigureProgramNameFigure.horizontalSpan = 0;
			constraintFFigureProgramNameFigure.verticalSpan = 0;
			constraintFFigureProgramNameFigure.grabExcessHorizontalSpace = true;
			constraintFFigureProgramNameFigure.grabExcessVerticalSpace = true;
			programNameHolder1.add(fFigureProgramNameFigure,
					constraintFFigureProgramNameFigure);

			RectangleFigure programCompartmentHolder0 = new RectangleFigure();
			programCompartmentHolder0.setFill(false);
			programCompartmentHolder0.setOutline(false);

			GridData constraintProgramCompartmentHolder0 = new GridData();
			constraintProgramCompartmentHolder0.verticalAlignment = GridData.FILL;
			constraintProgramCompartmentHolder0.horizontalAlignment = GridData.FILL;
			constraintProgramCompartmentHolder0.horizontalIndent = 0;
			constraintProgramCompartmentHolder0.horizontalSpan = 0;
			constraintProgramCompartmentHolder0.verticalSpan = 0;
			constraintProgramCompartmentHolder0.grabExcessHorizontalSpace = true;
			constraintProgramCompartmentHolder0.grabExcessVerticalSpace = true;
			this.add(programCompartmentHolder0,
					constraintProgramCompartmentHolder0);

			GridLayout layoutProgramCompartmentHolder0 = new GridLayout();
			layoutProgramCompartmentHolder0.numColumns = 1;
			layoutProgramCompartmentHolder0.makeColumnsEqualWidth = true;
			layoutProgramCompartmentHolder0.horizontalSpacing = 0;
			layoutProgramCompartmentHolder0.verticalSpacing = 0;
			layoutProgramCompartmentHolder0.marginWidth = 0;
			layoutProgramCompartmentHolder0.marginHeight = 0;
			programCompartmentHolder0
					.setLayoutManager(layoutProgramCompartmentHolder0);

			fFigureProgramCompartmentFigure = new RectangleFigure();
			fFigureProgramCompartmentFigure
					.setForegroundColor(ColorConstants.black);
			fFigureProgramCompartmentFigure.setBorder(new MarginBorder(
					getMapMode().DPtoLP(0), getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0), getMapMode().DPtoLP(0)));

			GridData constraintFFigureProgramCompartmentFigure = new GridData();
			constraintFFigureProgramCompartmentFigure.verticalAlignment = GridData.FILL;
			constraintFFigureProgramCompartmentFigure.horizontalAlignment = GridData.FILL;
			constraintFFigureProgramCompartmentFigure.horizontalIndent = 0;
			constraintFFigureProgramCompartmentFigure.horizontalSpan = 0;
			constraintFFigureProgramCompartmentFigure.verticalSpan = 0;
			constraintFFigureProgramCompartmentFigure.grabExcessHorizontalSpace = true;
			constraintFFigureProgramCompartmentFigure.grabExcessVerticalSpace = true;
			programCompartmentHolder0.add(fFigureProgramCompartmentFigure,
					constraintFFigureProgramCompartmentFigure);

		}

		/**
		 * @generated
		 */
		public RectangleFigure getFigureProgramCompartmentFigure() {
			return fFigureProgramCompartmentFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureProgramNameFigure() {
			return fFigureProgramNameFigure;
		}

	}

	/**
	 * @generated
	 */
	static final Font FFIGUREPROGRAMNAMEFIGURE_FONT = new Font(
			Display.getCurrent(), Display.getDefault().getSystemFont()
					.getFontData()[0].getName(), 10, SWT.BOLD);

}
