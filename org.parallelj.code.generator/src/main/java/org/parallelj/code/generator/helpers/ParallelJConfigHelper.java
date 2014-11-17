package org.parallelj.code.generator.helpers;

import static org.parallelj.code.generator.core.ParallelJGeneratorConstants.SRC_MAIN_RESOURCES;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.Annotation;
import org.eclipse.gmt.modisco.java.AnnotationTypeDeclaration;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.Type;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class ParallelJConfigHelper {

	private static final String CONFIG_FILENAME = SRC_MAIN_RESOURCES
			+ "/parallelj.xml";

	private static final String SERVERS = "servers";

	private static final String BEANS = "beans";

	private static final String BEAN = "bean";

	private static final String CLASS = "class";

	private static final String PROGRAM = "Program";

	private static Namespace rootNameSpace;

	private ParallelJConfigHelper() {
	}

	/**
	 * This will modify the //J configuration file i.e. parallelj.xml with
	 * program class added in bean tag
	 * 
	 * @param javaModel
	 * @param sharedResourceSet
	 * @param project
	 * @throws IOException
	 * @throws CoreException
	 * @throws JDOMException
	 */
	public static void generateConfig(Model javaModel,
			ResourceSet sharedResourceSet, IProject project)
			throws IOException, CoreException, JDOMException {

		// At first, we load the resources.
		IResource foundMember = project.getProject()
				.findMember(CONFIG_FILENAME);

		if (foundMember == null) {
			ConfigFilePathManager configFilePathManager = new ConfigFilePathManager();
			foundMember = configFilePathManager.loadFilePath(project);
		}

		if (foundMember != null && foundMember instanceof IFile) {

			IFile iFile = (IFile) foundMember;

			Document document;
			document = new SAXBuilder().build(iFile.getContents());
			Element rootElement = document.getRootElement();
			rootNameSpace = rootElement.getNamespace();

			// Then, we find or create the beans tag
			// final Element beans = findOrCreateBeans(root);
			final Element beans = findOrCreateBeans(rootElement);

			EList<CompilationUnit> compilationUnits = javaModel
					.getCompilationUnits();

			// getting compilation unit with @Program
			for (CompilationUnit compilationUnit : compilationUnits) {
				EList<AbstractTypeDeclaration> types = compilationUnit
						.getTypes();
				for (AbstractTypeDeclaration iType : types) {
					EList<Annotation> annotations = iType.getAnnotations();
					for (Annotation annotation : annotations) {
						Type type = annotation.getType().getType();
						if (type instanceof AnnotationTypeDeclaration) {
							AnnotationTypeDeclaration declaration = (AnnotationTypeDeclaration) type;
							if (declaration.getName().equals(PROGRAM)) {
								String beanName = "";
								Package packages = compilationUnit.getPackage();
								while (packages != null) {
									if (beanName.equals("")) {
										beanName = packages.getName()
												+ "."
												+ compilationUnit.getName()
														.replace(".java", "");
									} else {
										beanName = packages.getName() + "."
												+ beanName;
									}
									packages = packages.getPackage();
								}

								// create bean tag with class name if not exist
								findOrCreateBean(beans, beanName.toString());
							}
						}
					}
				}
			}

			File file = new File(iFile.getLocation().toString());
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			new XMLOutputter(Format.getPrettyFormat()).output(document, fos);
			fos.close();
		}

		// refresh
		project.getProject().refreshLocal(IResource.DEPTH_INFINITE, null);

	}

	/**
	 * This will create beans tag inside servers tag
	 * 
	 * @param root
	 * @return
	 */
	private static Element findOrCreateBeans(Element root) {

		Element serversElement = null;
		// find the servers
		for (final Object rootChild : root.getChildren()) {
			if (rootChild instanceof Element) {
				final Element element = (Element) rootChild;
				if (SERVERS.equals(element.getName())) {
					serversElement = element;
					for (final Object child : element.getChildren()) {
						if (child instanceof Element) {
							final Element beansElement = (Element) child;
							if (BEANS.equals(beansElement.getName())) {
								return beansElement;
							}
						}
					}
				}
			}
		}

		// If not exists, we create & return a new one.

		final Element element = createElement(serversElement, BEANS);
		return element;
	}

	/**
	 * This will create bean tag inside beans tag
	 * 
	 * @param beans
	 * @param elementName
	 * @return
	 */
	private static Element findOrCreateBean(Element beans, String elementName) {

		for (final Object bean : beans.getChildren()) {
			if (bean instanceof Element) {
				final Element beanElement = (Element) bean;
				for (final Object attribute : beanElement.getAttributes()) {
					if (attribute instanceof Attribute) {
						final Attribute clazz = (Attribute) attribute;
						if (clazz.getValue().equals(elementName)) {
							return beanElement;
						}
					}
				}
			}
		}

		// If not exists, we create & return a new one.
		final Element beanElement = createElement(beans, BEAN);
		createAttribute(beanElement, CLASS, elementName);

		return beanElement;

	}

	/**
	 * Creates a new XML Element with name, in provided element
	 * 
	 * @param element
	 * @param name
	 * @return
	 */
	private static Element createElement(final Element element,
			final String name) {
		final Element createdElement = new Element(name, rootNameSpace);
		element.addContent(createdElement);
		return createdElement;
	}

	/**
	 * Creates a new XML Attribute with name and value, in element provided
	 * 
	 * @param element
	 * @param name
	 * @param value
	 * @return
	 */
	private static Attribute createAttribute(final Element element,
			final String name, final String value) {
		final Attribute attribute = new Attribute(name, value,
				Namespace.NO_NAMESPACE);
		element.setAttribute(attribute);
		return attribute;
	}
}
