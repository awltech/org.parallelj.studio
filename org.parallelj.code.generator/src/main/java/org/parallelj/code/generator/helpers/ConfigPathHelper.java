package org.parallelj.code.generator.helpers;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;

public class ConfigPathHelper {

	/**
	 * currently processed project
	 */
	// getter setter
	private IProject project;
	private IResource foundMemberFile;

	public IProject getProject() {
		return project;
	}

	public void setProject(IProject project) {
		this.project = project;
	}

	public IResource getFoundMemberFile() {
		return foundMemberFile;
	}

	public void setFoundMemberFile(IResource foundMemberFile) {
		this.foundMemberFile = foundMemberFile;
	}

}
