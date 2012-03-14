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
package org.parallelj.designer.extension.part;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditorInput;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.parallelj.designer.extension.part.contrib.BusinessProcedureContribution;
import org.parallelj.designer.extension.part.contrib.BusinessProcedureContributionManager;
import org.parallelj.designer.extension.part.contrib.IParallelJDiagramEditorContribution;
import org.parallelj.designer.extension.part.contrib.ParallelJDiagramEditorContributionManager;
import org.parallelj.designer.part.ParallelJDiagramEditor;
import org.parallelj.designer.providers.ParallelJElementTypes;

/**
 * ParallelJ Designer Diagram Editor Extension.
 * 
 * This class has been extended, so additional behaviour can be added on editor
 * startup, thanks to Contribution extension point
 * 
 * @author mvanbesien $Id:$
 * 
 */
public class ParallelJDiagramExtendedEditor extends ParallelJDiagramEditor {

	// The contributor ID
	public static final String PROPERTIES_CONTRIBUTOR = "org.parallelj.properties";

	/**
	 * Contributions that were loaded from the Extension Point
	 */
	private Collection<BusinessProcedureContribution> businessProcedureContributions = new ArrayList<BusinessProcedureContribution>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.
	 * DiagramDocumentEditor#init(org.eclipse.ui.IEditorSite,
	 * org.eclipse.ui.IEditorInput)
	 */
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		super.init(site, input);

		// Creating a custom Diagram Editor Input and passing it to the
		// contributors
		DiagramEditorInput diagramEditorInput = new DiagramEditorInput(
				this.getDiagram());
		Collection<IParallelJDiagramEditorContribution> contributions = ParallelJDiagramEditorContributionManager
				.getInstance().getContributions();
		for (IParallelJDiagramEditorContribution contribution : contributions) {
			contribution.contribute(site, diagramEditorInput);
		}

		businessProcedureContributions = BusinessProcedureContributionManager
				.getInstance().getContributions();
	}

	@Override
	protected PaletteRoot createPaletteRoot(PaletteRoot existingPaletteRoot) {
		PaletteRoot root = super.createPaletteRoot(existingPaletteRoot);

		List children = root.getChildren();
		for (Object object : children) {
			if (object instanceof PaletteDrawer) {
				PaletteDrawer paletteDrawer = (PaletteDrawer) object;
				if ("createAdvanced4Group".equals(paletteDrawer.getId())) {
					// setting Advanced section, folded by default
					paletteDrawer.setInitialState(1);
				}
			}
		}

		PaletteContainer createUserProcedureGroup = null;
		if (!businessProcedureContributions.isEmpty()) {
			createUserProcedureGroup = createUserProcedureGroup();
		}

		int count = 1;
		for (BusinessProcedureContribution businessProcedureContribution : businessProcedureContributions) {
			createUserProcedureGroup
					.add(createBusinessProcedureTool(businessProcedureContribution, count));
			count ++;
		}

		if (createUserProcedureGroup != null) {
			root.add(createUserProcedureGroup);
		}

		return root;
	}

	/**
	 * Creates "User's Procedures" palette tool group
	 * 
	 * @generated
	 */
	private PaletteContainer createUserProcedureGroup() {
		PaletteDrawer paletteContainer = new PaletteDrawer("User's Procedures");
		paletteContainer.setId("userProceduresGroup"); //$NON-NLS-1$
		return paletteContainer;
	}

	/**
	 * @param businessProcedureContribution
	 * @return
	 */
	private ToolEntry createBusinessProcedureTool(
			BusinessProcedureContribution businessProcedureContribution, int count) {

		NodeToolEntry entry = new NodeToolEntry(
				businessProcedureContribution.getName(),
				businessProcedureContribution.getDescription(),
				Collections
						.singletonList(ParallelJElementTypes.BusinessProcedure_3014));
		entry.setId("createBusinessProcedureTool"+count); //$NON-NLS-1$

		entry.setSmallIcon(ImageDescriptor
				.createFromImage(businessProcedureContribution.getImage()));
		entry.setLargeIcon(entry.getSmallIcon());

		entry.setToolProperty("businessProcedureContribution",
				businessProcedureContribution);

		return entry;
	}

	/**
	 * @generated
	 */
	public String getContributorId() {

		// This is the contributor id for designer properties
		// project based on EEF.
		return PROPERTIES_CONTRIBUTOR;
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
}
