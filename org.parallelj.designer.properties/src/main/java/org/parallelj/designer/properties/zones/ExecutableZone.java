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
package org.parallelj.designer.properties.zones;

import java.util.concurrent.Callable;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.jdt.internal.ui.wizards.NewElementWizard;
import org.eclipse.jdt.internal.ui.wizards.NewWizardMessages;
import org.eclipse.jdt.ui.wizards.NewClassWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipselabs.resourceselector.core.processor.ResourceProcessorFactory;
import org.eclipselabs.resourceselector.core.resources.ResourceInfo;
import org.eclipselabs.resourceselector.core.selector.ResourceSelector;
import org.parallelj.designer.properties.Activator;
import org.parallelj.designer.properties.helpers.ParallelJPropertiesMessages;
import org.parallelj.designer.properties.helpers.Tools;
import org.parallelj.designer.typeselector.processor.annotation.AnnotationTypeProcessorFactory;
import org.parallelj.designer.typeselector.processor.hierarchy.JavaExecutableProcessorFactory;
import org.parallelj.designer.typeselector.processor.model.ModelTypeProcessorFactory;
import org.parallelj.ixea.Zone;
import org.parallelj.ixea.helpers.TextChangeHelper;
import org.parallelj.ixea.tools.Commands;
import org.parallelj.ixea.tools.FormDataBuilder;
import org.parallelj.model.ParallelJPackage;
import org.parallelj.model.Procedure;

@SuppressWarnings("restriction")
public class ExecutableZone extends Zone {

	private CLabel executableLabel;

	private Text executableText;

	private Button createExecutable;

	private Button selectExecutable;

	public ExecutableZone(Composite parent, boolean isGroup) {
		super(parent, isGroup);
	}

	@Override
	public void addItemsToZone() {
		executableLabel = this.getWidgetFactory().createCLabel(getZone(),
				ParallelJPropertiesMessages.element_executable.message());
		executableText = this.getWidgetFactory().createText(getZone(), "",
				SWT.READ_ONLY);
		executableText.setEditable(false);
		createExecutable = this.getWidgetFactory().createButton(getZone(),
				ParallelJPropertiesMessages.button_create.message(), SWT.PUSH);
		selectExecutable = this.getWidgetFactory().createButton(getZone(),
				ParallelJPropertiesMessages.button_select.message(), SWT.PUSH);
	}

	@Override
	public void addLayoutsToItems() {
		new FormDataBuilder().left().top().mediumLabel().apply(executableLabel);
		new FormDataBuilder().left(executableLabel).right().top()
				.apply(executableText);
		new FormDataBuilder().top(executableText).right().shortButton()
				.apply(selectExecutable);
		new FormDataBuilder().top(executableText).right(selectExecutable)
				.shortButton().apply(createExecutable);
	}

	@Override
	public void addListenersToItems() {
		TextChangeHelper executableTextListener = new TextChangeHelper() {
			@Override
			public void textChanged(Control control) {
				String value = ((Text) control).getText();
				String formattedValue = value;
				Commands.doSetValue(getEditingDomain(), getEObject(),
						ParallelJPackage.eINSTANCE.getProcedure_Executable(),
						formattedValue, getEditPart());
			}
		};
		executableTextListener.startListeningTo(executableText);
		executableTextListener.startListeningForEnter(executableText);
		selectExecutable.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				ExecutableZone.this.askForExecutable();
			}
		});
		createExecutable.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				ExecutableZone.this.createExecutable();
			}
		});
	}

	@Override
	public void updateItemsValues() {
		Object o = getEObject().eGet(
				ParallelJPackage.eINSTANCE.getProcedure_Executable());
		executableText.setText(o != null ? (String) o : "");
	}

	protected void askForExecutable() {
		ResourceProcessorFactory[] factories = new ResourceProcessorFactory[] {
				new AnnotationTypeProcessorFactory(),
				new ModelTypeProcessorFactory(getEObject()),
				new JavaExecutableProcessorFactory(Runnable.class),
				new JavaExecutableProcessorFactory(Callable.class) };
		ResourceSelector selector = new ResourceSelector(factories, Tools
				.getJavaProjectFromEObject(getEObject()).getProject());
		selector.run();
		ResourceInfo savedTypeInfo = selector.getSavedResourceInfo();

		if (savedTypeInfo == null)
			return;

		String newType;
		if (savedTypeInfo.getPackageName() != null
				&& savedTypeInfo.getPackageName().length() > 0) {
			newType = savedTypeInfo.getPackageName() + "."
					+ savedTypeInfo.getElementName();
		} else {
			newType = savedTypeInfo.getElementName();
		}

		if (getEObject() != null && getEObject() instanceof Procedure) {
			if (!newType.equals(((Procedure) getEObject()).getExecutable())) {
				Commands.doSetValue(getEditingDomain(), getEObject(),
						ParallelJPackage.eINSTANCE.getProcedure_Executable(),
						newType, getEditPart());
				this.executableText.setText(newType);
			}
		}
	}

	protected void createExecutable() {
		CustomNewClassCreationWizard wizard = new CustomNewClassCreationWizard();
		WizardDialog dialog = new WizardDialog(getZone().getShell(), wizard);
		dialog.open();
		String createdType = wizard.getCreatedType();
		if (createdType == null)
			return;

		if (getEObject() != null && getEObject() instanceof Procedure) {
			if (!createdType.equals(((Procedure) getEObject()).getExecutable())) {
				Commands.doSetValue(getEditingDomain(), getEObject(),
						ParallelJPackage.eINSTANCE.getProcedure_Executable(),
						createdType, getEditPart());
				this.executableText.setText(createdType);
			}
		}
	}

	class CustomNewClassCreationWizard extends NewElementWizard {
		private NewClassWizardPage page;

		public CustomNewClassCreationWizard() {
			setDefaultPageImageDescriptor(JavaPluginImages.DESC_WIZBAN_NEWCLASS);
			setDialogSettings(JavaPlugin.getDefault().getDialogSettings());
			setWindowTitle(NewWizardMessages.NewClassCreationWizard_title);
			page = new NewClassWizardPage();
			page.addSuperInterface(Callable.class.getCanonicalName()+"<Void>");
			page.setMethodStubSelection(false, true, true, false);
		}

		@Override
		public void addPages() {
			super.addPages();
			addPage(page);
		}

		@Override
		protected boolean canRunForked() {
			return !page.isEnclosingTypeSelected();
		}

		@Override
		protected void finishPage(IProgressMonitor monitor)
				throws InterruptedException, CoreException {
			try {
				page.createType(monitor);
			} catch (NullPointerException npe) {
				Activator
						.getDefault()
						.logError(
								ParallelJPropertiesMessages.error_runnable_creation
										.message(), npe);
			}
		}

		@Override
		public boolean performFinish() {
			// warnAboutTypeCommentDeprecation();
			return super.performFinish();
		}

		@Override
		public IJavaElement getCreatedElement() {
			return page.getCreatedType();
		}

		public String getCreatedType() {
			if (page != null && page.getCreatedType() != null) {
				return page.getCreatedType().getFullyQualifiedName();
			} else {
				return null;
			}
		}

	}

}
