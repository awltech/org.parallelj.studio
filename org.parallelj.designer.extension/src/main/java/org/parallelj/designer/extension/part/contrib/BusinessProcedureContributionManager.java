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

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.graphics.Image;
import org.parallelj.designer.extension.Activator;
import org.parallelj.designer.extension.tools.ImageLoader;

public class BusinessProcedureContributionManager {

	/**
	 * Constant holding the name of the Extension Point
	 */
	private static final String EXTENSION_POINT_NAME = "IBusinessProcedureContribution";

	/**
	 * Constant holding the name of the Configuration Element of the Extension
	 * Point
	 */
	private static final String CONTRIBUTION = "Contribution";

	/**
	 * Business procedure name
	 */
	private static final String NAME = "name";

	/**
	 * Business procedure color
	 */
	private static final String COLOR = "color";

	/**
	 * Business procedure executable
	 */
	private static final String EXECUTABLE = "executable";

	/**
	 * Business procedure inputs
	 */
	private static final String INPUTS = "inputs";

	/**
	 * Business procedure outputs
	 */
	private static final String OUTPUTS = "outputs";

	/**
	 * Business procedure description
	 */
	private static final String DESCRIPTION = "description";

	/**
	 * Business procedure image
	 */
	private static final String IMAGE = "image";

	/**
	 * Singleton Instance
	 */
	private static BusinessProcedureContributionManager instance;

	/**
	 * Returns the Business Procedure Contribution Extension Point Manager
	 * 
	 * @return manager
	 */
	public static final synchronized BusinessProcedureContributionManager getInstance() {
		if (BusinessProcedureContributionManager.instance == null) {
			BusinessProcedureContributionManager.instance = new BusinessProcedureContributionManager();
		}
		return BusinessProcedureContributionManager.instance;
	}

	/**
	 * Private Constructor
	 */
	private BusinessProcedureContributionManager() {
		this.loadContributions();
	}

	/**
	 * Contributions that were loaded from the Extension Point
	 */
	private Collection<BusinessProcedureContribution> contributions = new ArrayList<BusinessProcedureContribution>();

	/**
	 * Loads the Extension Point contributions
	 */
	private void loadContributions() {
		this.contributions.clear();

		IExtension[] extensions = Platform.getExtensionRegistry()
				.getExtensionPoint(Activator.PLUGIN_ID, EXTENSION_POINT_NAME)
				.getExtensions();

		for (IExtension extension : extensions) {
			for (IConfigurationElement configurationElement : extension
					.getConfigurationElements()) {
				if (CONTRIBUTION.equals(configurationElement.getName())) {

					BusinessProcedureContribution businessProcedureContribution = new BusinessProcedureContribution();

					String icon = configurationElement.getAttribute(IMAGE);
					String extendingPluginId = configurationElement
							.getNamespaceIdentifier();
					Image image = ImageLoader.getImage(extendingPluginId, icon);

					businessProcedureContribution.setName(configurationElement
							.getAttribute(NAME));
					businessProcedureContribution.setColor(configurationElement
							.getAttribute(COLOR));
					businessProcedureContribution
							.setExecutable(configurationElement
									.getAttribute(EXECUTABLE));
					businessProcedureContribution
							.setInputs(configurationElement
									.getAttribute(INPUTS));
					businessProcedureContribution
							.setOutputs(configurationElement
									.getAttribute(OUTPUTS));
					businessProcedureContribution
							.setDescription(configurationElement
									.getAttribute(DESCRIPTION));
					businessProcedureContribution.setImage(image);

					contributions.add(businessProcedureContribution);

				}
			}
		}
	}

	/**
	 * Returns the list of Contributions for the Business Procedures
	 * contribution
	 * 
	 * @return Collection of Contributions
	 */
	public Collection<BusinessProcedureContribution> getContributions() {
		return Collections.unmodifiableCollection(this.contributions);
	}
}
