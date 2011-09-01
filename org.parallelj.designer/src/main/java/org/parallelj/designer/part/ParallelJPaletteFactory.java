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
package org.parallelj.designer.part;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.parallelj.designer.providers.ParallelJElementTypes;

/**
 * @generated
 */
public class ParallelJPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createContext1Group());
		paletteRoot.add(createObjects2Group());
		paletteRoot.add(createComplexObjects3Group());
		paletteRoot.add(createAdvanced4Group());
	}

	/**
	 * Creates "Context" palette tool group
	 * @generated
	 */
	private PaletteContainer createContext1Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				Messages.Context1Group_title);
		paletteContainer.setId("createContext1Group"); //$NON-NLS-1$
		paletteContainer.add(createProgram1CreationTool());
		paletteContainer.add(createLink2CreationTool());
		paletteContainer.add(createPredicate3CreationTool());
		paletteContainer.add(createData4CreationTool());
		return paletteContainer;
	}

	/**
	 * Creates "Objects" palette tool group
	 * @generated
	 */
	private PaletteContainer createObjects2Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				Messages.Objects2Group_title);
		paletteContainer.setId("createObjects2Group"); //$NON-NLS-1$
		paletteContainer.add(createProcedure1CreationTool());
		paletteContainer.add(createForEachLoop2CreationTool());
		paletteContainer.add(createWhileLoop3CreationTool());
		paletteContainer.add(createHandler4CreationTool());
		return paletteContainer;
	}

	/**
	 * Creates "Complex Objects" palette tool group
	 * @generated
	 */
	private PaletteContainer createComplexObjects3Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				Messages.ComplexObjects3Group_title);
		paletteContainer.setId("createComplexObjects3Group"); //$NON-NLS-1$
		paletteContainer.add(createPipeline1CreationTool());
		return paletteContainer;
	}

	/**
	 * Creates "Advanced" palette tool group
	 * @generated
	 */
	private PaletteContainer createAdvanced4Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				Messages.Advanced4Group_title);
		paletteContainer.setId("createAdvanced4Group"); //$NON-NLS-1$
		paletteContainer.add(createInputCondition1CreationTool());
		paletteContainer.add(createOutputCondition2CreationTool());
		paletteContainer.add(createCondition3CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createProgram1CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Program1CreationTool_title,
				Messages.Program1CreationTool_desc,
				Collections.singletonList(ParallelJElementTypes.Program_2001));
		entry.setId("createProgram1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ParallelJElementTypes
				.getImageDescriptor(ParallelJElementTypes.Program_2001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createLink2CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.Link2CreationTool_title,
				Messages.Link2CreationTool_desc,
				Collections.singletonList(ParallelJElementTypes.Link_4001));
		entry.setId("createLink2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ParallelJElementTypes
				.getImageDescriptor(ParallelJElementTypes.Link_4001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createPredicate3CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Predicate3CreationTool_title,
				Messages.Predicate3CreationTool_desc,
				Collections.singletonList(ParallelJElementTypes.Predicate_3004));
		entry.setId("createPredicate3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ParallelJElementTypes
				.getImageDescriptor(ParallelJElementTypes.Predicate_3004));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createData4CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Data4CreationTool_title,
				Messages.Data4CreationTool_desc,
				Collections.singletonList(ParallelJElementTypes.Data_3011));
		entry.setId("createData4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ParallelJElementTypes
				.getImageDescriptor(ParallelJElementTypes.Data_3011));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createProcedure1CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ParallelJElementTypes.Procedure_3005);
		types.add(ParallelJElementTypes.Procedure_3010);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Procedure1CreationTool_title,
				Messages.Procedure1CreationTool_desc, types);
		entry.setId("createProcedure1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ParallelJElementTypes
				.getImageDescriptor(ParallelJElementTypes.Procedure_3005));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createForEachLoop2CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.ForEachLoop2CreationTool_title,
				Messages.ForEachLoop2CreationTool_desc,
				Collections
						.singletonList(ParallelJElementTypes.ForEachLoop_3006));
		entry.setId("createForEachLoop2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ParallelJElementTypes
				.getImageDescriptor(ParallelJElementTypes.ForEachLoop_3006));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createWhileLoop3CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.WhileLoop3CreationTool_title,
				Messages.WhileLoop3CreationTool_desc,
				Collections.singletonList(ParallelJElementTypes.WhileLoop_3007));
		entry.setId("createWhileLoop3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ParallelJElementTypes
				.getImageDescriptor(ParallelJElementTypes.WhileLoop_3007));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createHandler4CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Handler4CreationTool_title,
				Messages.Handler4CreationTool_desc,
				Collections.singletonList(ParallelJElementTypes.Handler_3008));
		entry.setId("createHandler4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ParallelJElementTypes
				.getImageDescriptor(ParallelJElementTypes.Handler_3008));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createPipeline1CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Pipeline1CreationTool_title,
				Messages.Pipeline1CreationTool_desc,
				Collections.singletonList(ParallelJElementTypes.Pipeline_3009));
		entry.setId("createPipeline1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ParallelJElementTypes
				.getImageDescriptor(ParallelJElementTypes.Pipeline_3009));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createInputCondition1CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.InputCondition1CreationTool_title,
				Messages.InputCondition1CreationTool_desc,
				Collections
						.singletonList(ParallelJElementTypes.InputCondition_3001));
		entry.setId("createInputCondition1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ParallelJElementTypes
				.getImageDescriptor(ParallelJElementTypes.InputCondition_3001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createOutputCondition2CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.OutputCondition2CreationTool_title,
				Messages.OutputCondition2CreationTool_desc,
				Collections
						.singletonList(ParallelJElementTypes.OutputCondition_3002));
		entry.setId("createOutputCondition2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ParallelJElementTypes
				.getImageDescriptor(ParallelJElementTypes.OutputCondition_3002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createCondition3CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Condition3CreationTool_title,
				Messages.Condition3CreationTool_desc,
				Collections.singletonList(ParallelJElementTypes.Condition_3003));
		entry.setId("createCondition3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ParallelJElementTypes
				.getImageDescriptor(ParallelJElementTypes.Condition_3003));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private static class NodeToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List<IElementType> elementTypes;

		/**
		 * @generated
		 */
		private NodeToolEntry(String title, String description,
				List<IElementType> elementTypes) {
			super(title, description, null, null);
			this.elementTypes = elementTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeCreationTool(elementTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}

	/**
	 * @generated
	 */
	private static class LinkToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List<IElementType> relationshipTypes;

		/**
		 * @generated
		 */
		private LinkToolEntry(String title, String description,
				List<IElementType> relationshipTypes) {
			super(title, description, null, null);
			this.relationshipTypes = relationshipTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}
}
