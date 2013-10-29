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
package org.parallelj.designer.extension.tools;

import java.util.concurrent.Callable;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.actions.OpenNewClassWizardAction;
import org.eclipse.jdt.ui.wizards.NewClassWizardPage;
import org.eclipselabs.resourceselector.core.processor.ResourceProcessorFactory;
import org.eclipselabs.resourceselector.core.resources.ResourceInfo;
import org.eclipselabs.resourceselector.core.selector.ResourceSelector;
import org.eclipselabs.resourceselector.processor.java.JavaTypeFilters;
import org.eclipselabs.resourceselector.processor.java.JavaTypeInfo;
import org.eclipselabs.resourceselector.processor.java.JavaTypeProcessorFactory;
import org.eclipselabs.resourceselector.processor.java.primitives.PrimitiveTypeInfo;
import org.eclipselabs.resourceselector.processor.java.primitives.PrimitiveTypeProcessorFactory;
import org.parallelj.designer.extension.Activator;
import org.parallelj.designer.typeselector.processor.annotation.AnnotationTypeProcessorFactory;
import org.parallelj.designer.typeselector.processor.hierarchy.JavaExecutableProcessorFactory;
import org.parallelj.designer.typeselector.processor.model.ModelTypeInfo;
import org.parallelj.designer.typeselector.processor.model.ModelTypeProcessorFactory;
import org.parallelj.model.Data;

/**
 * This class is used to get the Executable value from the Class Wizard or from
 * the Resource Selector.
 * 
 */
public class ExecutableTools {

	private ExecutableTools() {
	}

	/**
	 * Returns the fully qualified name of Class created. It is used as to open
	 * the New Class Wizard dialog. It uses the OpenNewClassWizardAction to open
	 * the wizard and returns the Class name if Java Element is created.
	 * 
	 * @param eObject
	 * @return name of created Java class
	 */
	public static String getExecutableValueFromClassWizard(EObject eObject) {
		String newExecutableValue = null;
		NewClassWizardPage newClassWizardPage = new NewClassWizardPage();
		newClassWizardPage.addSuperInterface("java.lang.Runnable");
		OpenNewClassWizardAction openNewClassWizardAction = new OpenNewClassWizardAction();
		IPackageFragmentRoot initRoot = null;
		IJavaProject jproject = ResourceSelectorTools
				.getJavaProjectFromEObject(eObject).getJavaProject();
		try {
			if (jproject.exists()) {
				IPackageFragmentRoot[] roots = jproject
						.getPackageFragmentRoots();
				for (int i = 0; i < roots.length; i++) {
					if (roots[i].getKind() == IPackageFragmentRoot.K_SOURCE) {
						initRoot = roots[i];
						break;
					}
				}
			}
		} catch (JavaModelException e) {
			Activator.logError(e.getMessage());
		}
		newClassWizardPage.setPackageFragmentRoot(initRoot, true);
		newClassWizardPage.setMethodStubSelection(false, false, true, true);
		openNewClassWizardAction.setConfiguredWizardPage(newClassWizardPage);
		openNewClassWizardAction.run();
		if (openNewClassWizardAction.getCreatedElement() != null
				&& openNewClassWizardAction.getCreatedElement()
						.getElementName() != null) {
			if (newClassWizardPage.getPackageText() != "") {
				newExecutableValue = newClassWizardPage.getPackageText() + "."
						+ newClassWizardPage.getTypeName();
			} else {
				newExecutableValue = newClassWizardPage.getTypeName();
			}
		}
		return newExecutableValue;
	}

	/**
	 * 
	 * Returns the fully qualified name of Java Class implementing the Runnable
	 * or Callable interfaces selected from the Resource selector in case of
	 * EObject of type Procedure and its sub-objects. Whereas, in case of DATA
	 * EObject it returns either primitive type or Java type class.
	 * 
	 * @param eObject
	 * @return new Executable Value Selected.
	 */
	public static String getExecutableFromResourceSelector(EObject eObject) {
		String newExecutableValue = null;
		String packageName = null;
		ResourceProcessorFactory[] factories = null;

		if (eObject instanceof Data) {
			factories = new ResourceProcessorFactory[] {
					new PrimitiveTypeProcessorFactory(),
					new JavaTypeProcessorFactory() };
		} else {
			factories = new ResourceProcessorFactory[] {
					new JavaExecutableProcessorFactory(Callable.class),
					new JavaExecutableProcessorFactory(Runnable.class),
					new ModelTypeProcessorFactory(eObject),
					new AnnotationTypeProcessorFactory()};
		}
		ResourceSelector selector = new ResourceSelector(factories,
				ResourceSelectorTools.getJavaProjectFromEObject(eObject)
						.getProject());
		selector.setFilter(JavaTypeFilters.getMostUsedJavaTypesFilter());
		selector.run();
		ResourceInfo savedTypeInfo = selector.getSavedResourceInfo();

		if (savedTypeInfo != null) {
			if (savedTypeInfo instanceof PrimitiveTypeInfo) {
				newExecutableValue = savedTypeInfo.getElementName();
			} else if (savedTypeInfo instanceof JavaTypeInfo) {
				if (!"".equals(savedTypeInfo.getPackageName())) {
					packageName = savedTypeInfo.getPackageName() + ".";
				} else {
					packageName = savedTypeInfo.getPackageName();
				}
				newExecutableValue = packageName
						+ savedTypeInfo.getElementName();
			} else if (savedTypeInfo instanceof ModelTypeInfo) {
				if (savedTypeInfo.getPackageName() != null
						&& !"".equals(savedTypeInfo.getPackageName())) {
					packageName = savedTypeInfo.getPackageName() + ".";
				} else {
					packageName = savedTypeInfo.getPackageName();
				}
				newExecutableValue = packageName
						+ savedTypeInfo.getElementName();
			}
		}
		return newExecutableValue;
	}

}
