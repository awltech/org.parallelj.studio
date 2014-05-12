package org.parallelj.code.generator.helpers;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.parallelj.code.generator.Activator;

public class ConfigFilePathManager {

	private static final String EXTENSION_POINT_NAME = "ParallelJCodeGeneratorConfigFilePath";
	private static final String ELEMENET_NAME = "filePath";
	private static final String ATTRIBUTE_NAME = "configPathName";
	private IProject iProject = null;

	public IResource loadFilePath() {

		IFile foundMember = null;
		try {
			IExtension[] extensions = Platform
					.getExtensionRegistry()
					.getExtensionPoint(Activator.PLUGIN_ID,
							EXTENSION_POINT_NAME).getExtensions();

			for (IExtension extension : extensions) {
				for (IConfigurationElement configurationElement : extension
						.getConfigurationElements()) {
					if (ELEMENET_NAME.equals(configurationElement.getName())) {

						Object object = configurationElement
								.createExecutableExtension(ATTRIBUTE_NAME);
						if (object != null
								&& object instanceof ConfigPathHelper) {
							ConfigPathHelper configPathhelper = (ConfigPathHelper) object;
							foundMember = (IFile) configPathhelper
									.getFoundMemberFile();

							iProject = configPathhelper.getProject();

						}

					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return foundMember;
	}

	public IProject getProject() {

		return iProject;
	}
}
