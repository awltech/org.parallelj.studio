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
package org.parallelj.designer.extension.helpers;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.gmt.modisco.xml.Element;
import org.eclipse.gmt.modisco.xml.Node;
import org.eclipse.gmt.modisco.xml.Root;
import org.eclipse.gmt.modisco.xml.Text;
import org.eclipse.gmt.modisco.xml.emf.MoDiscoXMLFactory;
import org.eclipse.jdt.core.IJavaProject;
import org.parallelj.designer.extension.part.contrib.BusinessProcedureContribution;

public class DependencyManagementHelper {

	private static final String FILENAME = "pom.xml";

	private static final String PROJECT = "project";

	private static final String DEPENDENCIES = "dependencies";

	private static final String DEPENDENCY = "dependency";

	private static final String GROUP_ID = "groupId";

	private static final String ARTIFACT_ID = "artifactId";

	private static final String VERSION = "version";

	private static final String SCOPE = "scope";

	private DependencyManagementHelper() {

	}

	/**
	 * This will update pom.xml based passed business procedures contributions
	 * selected by user
	 * 
	 * @param sharedResourceSet
	 * @param selectedJavaProject
	 * @param contributions
	 * @throws IOException
	 * @throws CoreException
	 */
	public static void updatePom(ResourceSet sharedResourceSet,
			IJavaProject selectedJavaProject,
			List<BusinessProcedureContribution> contributions)
			throws IOException, CoreException {

		// At first, we load the resource
		final IResource foundMember = selectedJavaProject.getProject()
				.findMember(FILENAME);
		Resource resource = null;

		// if file found the ready to append else create new
		if (foundMember != null) {
			final IPath foundMemberPath = foundMember.getFullPath();
			final URI uri = URI.createPlatformResourceURI(
					foundMemberPath.toString(), true);
			resource = foundMember.exists() ? sharedResourceSet.getResource(
					uri, true) : sharedResourceSet.createResource(uri);
		} else {
			final URI uri = URI.createPlatformResourceURI(selectedJavaProject
					.getProject().getFullPath().append(FILENAME).toString(),
					true);
			resource = sharedResourceSet.createResource(uri);
		}

		if (resource == null)
			return;

		Root root = resource.getContents().size() > 0 ? (Root) resource
				.getContents().get(0) : null;
		if (root == null) {
			root = MoDiscoXMLFactory.eINSTANCE.createRoot();
			root.setName(PROJECT);
			resource.getContents().add(root);
		}

		// Then, we find or create the dependencies tag
		final Element dependencies = findOrCreateDependencies(root);

		// Based on Business Procedure contribution add the dependency tag in
		// pom
		for (BusinessProcedureContribution contribution : contributions) {
			// Then, we find or create the beans tag
			findOrCreateDependency(dependencies, contribution);
		}

		// And finally, we save !
		resource.save(null);
		selectedJavaProject.getProject()
				.refreshLocal(IResource.DEPTH_ONE, null);

	}

	/**
	 * This will create dependencies tag inside project tag if not exist
	 * 
	 * @param root
	 * @return
	 */
	private static Element findOrCreateDependencies(Root root) {

		for (final Node rootChild : root.getChildren()) {
			if (rootChild instanceof Element) {
				final Element element = (Element) rootChild;
				if (DEPENDENCIES.equals(element.getName())) {
					return element;
				}
			}
		}

		// If not exists, we create & return a new one.
		final Element element = createElement(root, DEPENDENCIES);
		return element;
	}

	/**
	 * This will create dependency tag inside dependencies tag
	 * 
	 * @param dependencies
	 * @param contribution
	 * @return
	 */
	private static Element findOrCreateDependency(Element dependencies,
			BusinessProcedureContribution contribution) {

		boolean groupIdMatch = false;
		boolean artifactIdMatch = false;
		boolean versionMatch = false;
		boolean scopeMatch = false;

		// Is same dependency already present
		for (final Node dependency : dependencies.getChildren()) {
			if (dependency instanceof Element) {
				final Element dependencyElement = (Element) dependency;
				for (final Node element : dependencyElement.getChildren()) {
					if (element instanceof Element) {

						if (element.getName().equals(GROUP_ID)) {
							final Element groupId = (Element) element;
							Node node = groupId.getChildren().get(0);

							if (node instanceof Text
									&& ((Text) node).getName().equals(
											contribution.getGroupId())) {
								groupIdMatch = true;
							}
						} else if (element.getName().equals(ARTIFACT_ID)) {
							final Element artifactId = (Element) element;
							Node node = artifactId.getChildren().get(0);

							if (node instanceof Text
									&& ((Text) node).getName().equals(
											contribution.getArtifactId())) {
								artifactIdMatch = true;
							}
						} else if (element.getName().equals(VERSION)) {
							final Element version = (Element) element;
							Node node = version.getChildren().get(0);

							if (node instanceof Text
									&& ((Text) node).getName().equals(
											contribution.getVersion())) {
								versionMatch = true;
							}
						} else if (element.getName().equals(SCOPE)) {
							final Element scope = (Element) element;
							Node node = scope.getChildren().get(0);

							if (node instanceof Text
									&& ((Text) node).getName().equals(
											contribution.getScope())) {
								scopeMatch = true;
							}
						}
					}
				}

				// if same exist then return it
				if (groupIdMatch && artifactIdMatch && versionMatch
						&& scopeMatch) {
					return dependencyElement;
				}

			}
		}

		// If not exists, we create & return a new one.
		final Element dependencyElement = createElement(dependencies,
				DEPENDENCY);
		createDependencyChildren(dependencyElement, contribution);
		return dependencyElement;

	}

	/**
	 * This will create other dependency inner element like groupID, artifactId,
	 * version and scope etc.
	 * 
	 * @param dependencyElement
	 * @param contribution
	 */
	private static void createDependencyChildren(Element dependencyElement,
			BusinessProcedureContribution contribution) {

		final Element groupIdElement = createElement(dependencyElement,
				GROUP_ID);
		createText(groupIdElement, contribution.getGroupId());

		final Element artifactIdElement = createElement(dependencyElement,
				ARTIFACT_ID);
		createText(artifactIdElement, contribution.getArtifactId());

		final Element versionElement = createElement(dependencyElement, VERSION);
		createText(versionElement, contribution.getVersion());

		final Element scopeElement = createElement(dependencyElement, SCOPE);
		createText(scopeElement, contribution.getScope());

	}

	/*
	 * Creates a new XML Element with name, in provided element
	 */
	private static Element createElement(final Element element,
			final String name) {

		final Element createdElement = MoDiscoXMLFactory.eINSTANCE
				.createElement();
		createdElement.setName(name);
		createdElement.setParent(element);
		return createdElement;
	}

	/*
	 * Creates a new XML Attribute with name and value, in element provided
	 */
	private static Text createText(final Element element, final String name) {
		final Text text = MoDiscoXMLFactory.eINSTANCE.createText();
		text.setName(name);
		text.setParent(element);
		return text;
	}

}
