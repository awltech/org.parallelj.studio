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
package org.parallelj.designer.extension.part.contrib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.parallelj.designer.extension.Activator;
import org.parallelj.designer.extension.Messages;

/**
 * Singleton that manages the reading of the Extension Point related to the
 * contributions to the ParallelJ Designer Diagram Editor
 * 
 * @author mvanbesien $Id:$
 * 
 */
public class ParallelJDiagramEditorContributionManager {

	/*
	 * Constant holding the name of the Extension Point
	 */
	private static final String EXTENSION_POINT_NAME = "IEditorContribution";

	/*
	 * Constant holding the name of the Configuration Element of the Extension
	 * Point
	 */
	private static final String CONFIGURATION_ELEMENT_NAME = "Contribution";

	/*
	 * Constant holding the name of the Executable Extension field, for this
	 * Extension Point
	 */
	private static final String EXECUTABLE_EXTENSION_NAME = "class";

	/*
	 * Singleton Instance
	 */
	private static ParallelJDiagramEditorContributionManager instance;

	/**
	 * Returns the ParallelJ Designer Diagram Editor Contribution Extension
	 * Point Manager
	 * 
	 * @return manager
	 */
	public static final synchronized ParallelJDiagramEditorContributionManager getInstance() {
		if (ParallelJDiagramEditorContributionManager.instance == null) {
			ParallelJDiagramEditorContributionManager.instance = new ParallelJDiagramEditorContributionManager();
		}
		return ParallelJDiagramEditorContributionManager.instance;
	}

	/*
	 * Private Constructor
	 */
	private ParallelJDiagramEditorContributionManager() {
		this.loadContributions();
	}

	/*
	 * Contributions that were loaded from the Extension Point
	 */
	private Collection<IParallelJDiagramEditorContribution> contributions = new ArrayList<IParallelJDiagramEditorContribution>();

	/*
	 * Loads the Extension Point contributions
	 */
	private void loadContributions() {
		this.contributions.clear();
		IExtensionPoint extensionPoint = Platform
				.getExtensionRegistry()
				.getExtensionPoint(
						Activator.PLUGIN_ID,
						ParallelJDiagramEditorContributionManager.EXTENSION_POINT_NAME);
		if (extensionPoint == null) {
			Activator.logError(Messages.NULL_CONTRIB_EXTPOINT.getMessage());
			return;
		}

		IExtension[] extensions = extensionPoint.getExtensions();
		for (IExtension extension : extensions) {
			for (IConfigurationElement configurationElement : extension
					.getConfigurationElements()) {
				if (ParallelJDiagramEditorContributionManager.CONFIGURATION_ELEMENT_NAME
						.equals(configurationElement.getName())) {
					try {
						IParallelJDiagramEditorContribution contributionImplementation = (IParallelJDiagramEditorContribution) configurationElement
								.createExecutableExtension(ParallelJDiagramEditorContributionManager.EXECUTABLE_EXTENSION_NAME);
						if (contributionImplementation != null) {
							this.contributions.add(contributionImplementation);
						}
					} catch (CoreException e) {
						Activator
								.logException(
										Messages.EXCEPTION_IN_CONTRIB_LOAD
												.getMessage(), e);
					}
				}
			}
		}
	}

	/**
	 * Returns the list of Contributions for the ParalleJ Designer Diagram
	 * Editor
	 * 
	 * @return Collection of Contributions
	 */
	public Collection<IParallelJDiagramEditorContribution> getContributions() {
		return Collections.unmodifiableCollection(this.contributions);
	}

}
