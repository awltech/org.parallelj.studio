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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.core.IJavaProject;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.Text;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.parallelj.designer.extension.part.contrib.BusinessProcedureContribution;

public class DependencyManagementHelper {

	private static final String FILENAME = "pom.xml";

	private static final String DEPENDENCIES = "dependencies";

	private static final String DEPENDENCY = "dependency";

	private static final String GROUP_ID = "groupId";

	private static final String ARTIFACT_ID = "artifactId";

	private static final String VERSION = "version";

	private static final String SCOPE = "scope";

	private static Namespace rootNameSpace;

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
	 * @throws JDOMException
	 */
	public static void updatePom(ResourceSet sharedResourceSet,
			IJavaProject selectedJavaProject,
			List<BusinessProcedureContribution> contributions)
			throws IOException, CoreException, JDOMException {

		// At first, we load the resource
		final IResource foundMember = selectedJavaProject.getProject()
				.findMember(FILENAME);

		if (foundMember != null && foundMember instanceof IFile) {

			IFile iFile = (IFile) foundMember;

			Document document;
			document = new SAXBuilder().build(iFile.getContents());
			Element rootElement = document.getRootElement();
			rootNameSpace = rootElement.getNamespace();

			// Then, we find or create the dependencies tag
			final Element dependencies = findOrCreateDependencies(rootElement);

			// Based on Business Procedure contribution add the dependency tag
			// in pom
			for (BusinessProcedureContribution contribution : contributions) {
				// Then, we find or create the beans tag
				findOrCreateDependency(dependencies, contribution);
			}

			File file = new File(iFile.getLocation().toString());
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			new XMLOutputter(Format.getPrettyFormat()).output(document, fos);
			fos.close();

		}

		// refresh
		selectedJavaProject.getProject().refreshLocal(IResource.DEPTH_INFINITE,
				null);

	}

	/**
	 * This will create dependencies tag inside project tag if not exist
	 * 
	 * @param root
	 * @return
	 */
	private static Element findOrCreateDependencies(Element root) {

		for (final Object rootChild : root.getChildren()) {
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
		for (final Object dependency : dependencies.getChildren()) {
			if (dependency instanceof Element) {
				final Element dependencyElement = (Element) dependency;
				for (final Object element : dependencyElement.getChildren()) {
					if (element instanceof Element) {

						if (((Element) element).getName().equals(GROUP_ID)) {
							final Element groupId = (Element) element;
							Object node = groupId.getContent().get(0);

							if (node instanceof Text
									&& ((Text) node).getText().equals(
											contribution.getGroupId())) {
								groupIdMatch = true;
							}
						} else if (((Element) element).getName().equals(
								ARTIFACT_ID)) {
							final Element artifactId = (Element) element;
							Object node = artifactId.getContent().get(0);

							if (node instanceof Text
									&& ((Text) node).getText().equals(
											contribution.getArtifactId())) {
								artifactIdMatch = true;
							}
						} else if (((Element) element).getName()
								.equals(VERSION)) {
							final Element version = (Element) element;
							Object node = version.getContent().get(0);

							if (node instanceof Text
									&& ((Text) node).getText().equals(
											contribution.getVersion())) {
								versionMatch = true;
							}
						} else if (((Element) element).getName().equals(SCOPE)) {
							final Element scope = (Element) element;
							Object node = scope.getContent().get(0);

							if (node instanceof Text
									&& ((Text) node).getText().equals(
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
		final Element createdElement = new Element(name, rootNameSpace);
		element.addContent(createdElement);
		return createdElement;
	}

	/*
	 * Creates a new XML Attribute with name and value, in element provided
	 */
	private static Text createText(final Element element, final String name) {
		final Text text = new Text(name);
		element.addContent(text);
		return text;
	}
}
