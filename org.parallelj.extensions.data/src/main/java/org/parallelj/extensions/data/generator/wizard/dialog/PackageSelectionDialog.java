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
package org.parallelj.extensions.data.generator.wizard.dialog;

import java.util.HashMap;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchWindow;
import org.parallelj.extensions.data.generator.Activator;
import org.parallelj.extensions.data.generator.GenerationType;
import org.parallelj.extensions.data.generator.jobs.DataConfiguration;
import org.parallelj.extensions.data.generator.logs.Messages;
import org.parallelj.extensions.data.generator.wizard.factory.SubDialogFactory;
import org.parallelj.extensions.data.generator.wizard.filter.PackageFilter;
import org.parallelj.extensions.data.generator.wizard.subdialog.ParallelJPackageDialog;
import org.parallelj.extensions.data.generator.wizard.subdialog.ParallelJSubDialog.ChildDialogData;

public class PackageSelectionDialog extends Dialog {

	private IJavaProject javaProject;
	private DialogData dialogData;
	private ChildDialogData chilDialogData;
	private IDialogSettings settings;
	private Composite composite;
	private CLabel errorWarningLabel;
	private Label separatorLabel;
	private Label packageLabel;
	private Label optionLabel;
	private FormData formData;
	private FormData formData_2;
	private Text packageText;
	private Text optionText;
	private Button selectPackageButton;
	private HashMap<Integer, Button> buttons = new HashMap<Integer, Button>();
	private DataConfiguration wizardOutput;
	private IFile selectedFile;

	public PackageSelectionDialog(IWorkbenchWindow window,
			IJavaProject javaProject, IFile selectedFile) {
		super(window.getShell());
		this.javaProject = javaProject;
		dialogData = new DialogData();
		this.selectedFile = selectedFile;

		PackageFilter packageFilter = new PackageFilter();
		dialogData.setLallXMLPackageFragments(packageFilter
				.getPackageFragments(javaProject));
	}

	/**
	 * method used to configure shell title
	 */

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(getTitle());
	}

	@Override
	protected IDialogSettings getDialogBoundsSettings() {
		return getDialogSettings();
	}

	@Override
	protected int getDialogBoundsStrategy() {
		return DIALOG_PERSISTLOCATION | DIALOG_PERSISTSIZE;
	}

	/**
	 * * enumeration is used to select File Type
	 */

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */

	@Override
	protected Control createDialogArea(Composite parent) {
		settings = getDialogSettings();
		Composite container = new Composite(parent, SWT.NONE);
		container = modifyParentContainer(container);
		createTopComposite(container);
		createHorizontalSeparator(container);
		createMiddleComposite(container);
		createHorizontalSeparator(container);
		createBottomComposite(container);
		createHorizontalSeparator(container);
		return container;
	}

	/**
	 * this method will return the modified parent container
	 * 
	 * @param container
	 * @return
	 */

	protected Composite modifyParentContainer(Composite container) {
		GridLayout layout = new GridLayout();
		layout.marginHeight = convertVerticalDLUsToPixels(0);
		layout.marginWidth = convertHorizontalDLUsToPixels(0);
		layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
		layout.horizontalSpacing = convertHorizontalDLUsToPixels(2);
		layout.marginRight = 2;
		container.setLayout(layout);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		applyDialogFont(container);
		return container;
	}

	/**
	 * this method design the upper part of the dialog box
	 * 
	 * @param composite
	 */
	protected void createTopComposite(Composite container) {
		composite = new Composite(container, SWT.NONE);
		composite.setBackground(composite.getDisplay().getSystemColor(
				SWT.COLOR_WHITE));
		composite.setLayout(new FormLayout());
		{
			GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true, 1,
					1);
			gridData.heightHint = 64;
			gridData.widthHint = 587;
			composite.setLayoutData(gridData);
		}

		createGenerationLabel(composite);
		createErrorWarninglabel(composite);
	}

	/**
	 * 
	 * @param composite
	 */

	protected void createGenerationLabel(Composite composite) {
		Label javaGenerationLabel = new Label(composite, SWT.BOLD);
		javaGenerationLabel.setBackground(composite.getDisplay()
				.getSystemColor(SWT.COLOR_WHITE));
		{
			FormData formData = new FormData();
			formData.top = new FormAttachment(0, 10);
			formData.left = new FormAttachment(0, 7);
			formData.width = 270;
			javaGenerationLabel.setLayoutData(formData);
		}
		Font initialFont = javaGenerationLabel.getFont();
		FontData[] fontData = initialFont.getFontData();
		Font newFont;
		fontData[0].setHeight(10);
		fontData[0].setStyle(SWT.BOLD);
		newFont = new Font(composite.getDisplay(), fontData[0]);
		javaGenerationLabel.setFont(newFont);
		javaGenerationLabel.setText(Messages.JAVA_GENERATION_LABEL.message());
	}

	/**
	 * this method creates label used to display the messages or errors
	 * 
	 * @param composite
	 */

	protected void createErrorWarninglabel(Composite composite) {
		errorWarningLabel = new CLabel(composite, SWT.CAP_ROUND);
		errorWarningLabel.setBackground(composite.getDisplay().getSystemColor(
				SWT.COLOR_WHITE));
		{
			FormData formData = new FormData();
			formData.top = new FormAttachment(0, 30);
			formData.left = new FormAttachment(0, 7);
			formData.right = new FormAttachment(0, SWT.LEFT);
			formData.height = 38;
			errorWarningLabel.setLayoutData(formData);
		}
	}

	/**
	 * 
	 * @param container
	 */

	protected void createHorizontalSeparator(Composite container) {
		separatorLabel = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		{
			GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true, 1,
					1);
			gridData.widthHint = 582;
			gridData.horizontalIndent = 3;
			gridData.verticalIndent = 10;
			separatorLabel.setLayoutData(gridData);
		}
		separatorLabel.setText("");
	}

	/**
	 * this method design the middle part of the dialog box
	 * 
	 * @param container
	 */

	protected void createMiddleComposite(Composite container) {
		Composite composite_1 = new Composite(container, SWT.NONE);
		composite_1.setLayout(new FormLayout());
		{
			GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true, 1,
					1);
			gridData.heightHint = 25;
			gridData.widthHint = 587;
			composite_1.setLayoutData(gridData);
		}
		createTargetLabel(composite_1);
		createPackageSelectButton(composite_1);
		createPackageTextButton(composite_1);
	}

	/**
	 * this method design the middle part of the dialog box
	 * 
	 * @param container
	 */

	protected void createBottomComposite(Composite container) {
		Composite composite_1 = new Composite(container, SWT.NONE);
		composite_1.setLayout(new FormLayout());
		{
			GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true, 1,
					1);
			gridData.heightHint = 25;
			gridData.widthHint = 587;
			composite_1.setLayoutData(gridData);
		}
		createOptionLabel(composite_1);
		createOptionText(composite_1);
	}

	/**
	 * 
	 * @param composite
	 */

	protected void createTargetLabel(Composite composite) {
		packageLabel = new Label(composite, SWT.NONE);
		{
			formData = new FormData();
			formData.left = new FormAttachment(0, 10);
			formData.top = new FormAttachment(0, 1);
			packageLabel.setLayoutData(formData);
		}
		packageLabel.setText(Messages.PACKAGE_LABEL.message());
	}

	/**
	 * 
	 * @param composite
	 */

	protected void createOptionLabel(Composite composite) {
		optionLabel = new Label(composite, SWT.NONE);
		{
			formData = new FormData();
			formData.left = new FormAttachment(0, 10);
			formData.top = new FormAttachment(0, 1);
			optionLabel.setLayoutData(formData);
		}
		optionLabel.setText(Messages.OPTION_LABEL.message());
	}

	/**
	 * 
	 * @param composite
	 */

	protected void createPackageTextButton(Composite composite) {
		packageText = new Text(composite, SWT.BORDER);
		if (settings.get("PACKAGE_FRAGMENT") != null)
			packageText.setText(settings.get("PACKAGE_FRAGMENT"));

		packageText.addListener(SWT.KeyUp, new Listener() {

			public void handleEvent(Event event) {
				dataPerspectiveDialogEvents();

			}
		});

		packageText.addListener(SWT.FocusOut, new Listener() {
			public void handleEvent(Event e) {
				dataPerspectiveDialogEvents();
			}

		});
		{
			formData_2 = new FormData();
			formData_2.left = new FormAttachment(packageLabel, 5);
			formData_2.right = new FormAttachment(selectPackageButton, -3,
					SWT.LEFT);
			packageText.setLayoutData(formData_2);
		}
	}

	/**
	 * 
	 * @param composite
	 */

	protected void createOptionText(Composite composite) {
		optionText = new Text(composite, SWT.BORDER);

		if (settings.get("OPTION_FRAGMENT") != null)
			optionText.setText(settings.get("OPTION_FRAGMENT"));

		{
			formData_2 = new FormData();

			formData_2.left = new FormAttachment(0, 20);
			formData_2.top = new FormAttachment(optionLabel, 5);
			formData_2.right = new FormAttachment(86, -3);
			optionText.setLayoutData(formData_2);
		}
	}

	/**
	 * this method is used to create and open Package Dialog and setting
	 * properties of DialogMasterData class
	 * 
	 * @param composite
	 * 
	 */

	protected void createPackageSelectButton(Composite composite) {
		selectPackageButton = new Button(composite, SWT.PUSH);
		{
			FormData formData = new FormData();
			formData.bottom = new FormAttachment(packageLabel, 8, SWT.BOTTOM);
			formData.right = new FormAttachment(100, -4);
			formData.width = 77;
			selectPackageButton.setLayoutData(formData);
		}
		selectPackageButton.setText(Messages.PACKAGE_SELECT_BUTTON.message());
		selectPackageButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				setDialogMaster("PackageDialog");
				ParallelJPackageDialog packageDialog = (ParallelJPackageDialog) SubDialogFactory
						.getFactoryInstance().getDialog("PackageDialog",
								getShell(), dialogData, null);
				if (packageDialog != null) {
					packageDialog.create();
					packageDialog.setBlockOnOpen(true);
					if (packageDialog.open() == 0) {
						chilDialogData = packageDialog.getChildData();
						if (chilDialogData != null) {
							Object curChildObj = chilDialogData.getObject();
							if (curChildObj != null
									&& curChildObj instanceof List) {
								List lstChieldObject = (List) curChildObj;
								for (Object childObject : lstChieldObject) {
									if (childObject != null
											&& childObject instanceof IPackageFragment
											&& !(((IPackageFragment) childObject)
													.getElementName())
													.equals("")) {
										dialogData
												.setPackageFragment((IPackageFragment) childObject);
										packageText.setText(dialogData
												.getPackageFragment()
												.getElementName());
									}
								}
							}
						}
					}
				}
				dataPerspectiveDialogEvents();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}

	/**
	 * Below method is used for event handling on Data Perspective dialog box
	 */

	private void dataPerspectiveDialogEvents() {
		eventsOnXMLFileSelection(dialogData.getLallXMLPackageFragments());
	}

	/**
	 * Below method contains events when File Type selected as XML file
	 * 
	 * @param packageFragment
	 *            :packageFragment contains a list of IPackageFragment
	 */

	private void eventsOnXMLFileSelection(
			java.util.List<IPackageFragment> packageFragment) {
		if (packageText.getText() == null
				|| packageText.getText().length() == 0) {

			errorWarningLabel.setImage(Activator.getDefault().getImage(
					WizardConstants.ERROR_IMAGE));
			errorWarningLabel.setText(Messages.INVALID_XMLPACKAGE_ERROR
					.message());
			enableDisableOKButton(false);
		} else {
			errorWarningLabel.setImage(null);
			errorWarningLabel.setText(Messages.JAXBANNOTED_POJO_INFO.message());
			enableDisableOKButton(true);
			IFolder mainSourceFolder = javaProject.getProject().getFolder(
					WizardConstants.TARGET_PACKAGE_SOURCE);
			IPackageFragmentRoot mainPackageFragmentRoot = javaProject
					.getPackageFragmentRoot(mainSourceFolder);
			if (mainPackageFragmentRoot != null)
				dialogData.setPackageFragment(mainPackageFragmentRoot
						.getPackageFragment(packageText.getText()));
		}
	}

	/**
	 * this method initializes the DialogMasterData
	 */

	private void initializeDialogMaster() {
		dialogData.setPackageFragment(dialogData.getPackageFragment());
		dialogData.setSchemaFileName(selectedFile);
	}

	/**
	 * this method enable or disable the OK button
	 * 
	 * @param enableDisable
	 * 
	 */

	private void enableDisableOKButton(boolean enableDisable) {
		buttons.get(0).setEnabled(enableDisable);
	}

	/**
	 * this method is used to set the properties of DialogMasterData which will
	 * be used further in sub dialogs
	 * 
	 * @param dialogType
	 * 
	 */

	protected void setDialogMaster(String dialogType) {
		dialogData.setDialogSelect(dialogType);
		dialogData.setJavaProject(this.javaProject);
	}

	/**
	 * this method sets properties of output configurable POJO
	 */

	protected void setWizardData() {
		if (dialogData != null) {

			dialogData.setOptions(optionText.getText());

			this.wizardOutput = new DataConfiguration(dialogData.getFileType(),
					dialogData.getPackageFragment(), true,
					dialogData.getSchemaFileName(), dialogData.getOptions());
		}
	}

	/**
	 * 
	 * @return this method returns output POJO to calling class's method
	 */

	public DataConfiguration getResult() {
		return wizardOutput;
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * below method creates default OK and Cancel button
	 */

	@Override
	protected Button createButton(Composite parent, int id, String label,
			boolean defaultButton) {
		if (id != 0) {
			dataPerspectiveDialogEvents();
			initializeDialogMaster();

		}
		((GridLayout) parent.getLayout()).numColumns++;
		Button button = new Button(parent, SWT.PUSH);
		button.setText(label);
		button.setFont(JFaceResources.getDialogFont());
		button.setData(new Integer(id));
		button.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent event) {
				if (((Integer) event.widget.getData()).intValue() == 1) {
					if (wizardOutput != null)
						wizardOutput = null;
				} else {
					setWizardData();
					setDialogSettings();

				}

				buttonPressed(((Integer) event.widget.getData()).intValue());
			}
		});
		if (defaultButton) {
			Shell shell = parent.getShell();
			if (shell != null) {
				shell.setDefaultButton(button);
			}
		}
		buttons.put(new Integer(id), button);
		setButtonLayoutData(button);
		return button;
	}

	/**
	 * Return the initial size of the dialog.
	 */

	@Override
	protected Point getInitialSize() {
		return new Point(600, 350);
	}

	/**
	 * this method returns the JAXB generation wizard Title
	 * 
	 * @return String
	 */

	protected String getTitle() {
		return Messages.PARALLELJ_TITLE.message();
	}

	/**
	 * this method sets the Dialog Settings
	 */

	private void setDialogSettings() {
		settings.put("PACKAGE_FRAGMENT", packageText.getText());
		settings.put("OPTION_FRAGMENT", optionText.getText());
	}

	protected IDialogSettings getDialogSettings() {
		IDialogSettings workbenchSettings = Activator.getDefault()
				.getDialogSettings();
		IDialogSettings rootSettings = workbenchSettings
				.getSection("ParallelJ.org.parallelj.extensions.data");
		if (rootSettings == null)
			rootSettings = workbenchSettings
					.addNewSection("ParallelJ.org.parallelj.extensions.data");
		IDialogSettings settings = rootSettings.getSection(javaProject
				.getElementName());
		if (settings == null)
			settings = rootSettings.addNewSection(javaProject.getElementName());
		return settings;
	}

	public class DialogData {

		private IJavaProject javaProject;
		private IPackageFragment packageFragment;
		private IFile schemaFileName;
		private String dialogSelect;
		private GenerationType fileType;
		private java.util.List<IPackageFragment> lallXMLPackageFragments;
		private String options;

		public GenerationType getFileType() {
			return fileType;
		}

		public void setFileType(GenerationType fileType) {
			this.fileType = fileType;
		}

		public String getDialogSelect() {
			return dialogSelect;
		}

		public void setDialogSelect(String dialogSelect) {
			this.dialogSelect = dialogSelect;
		}

		public IJavaProject getJavaProject() {
			return javaProject;
		}

		public void setJavaProject(IJavaProject javaProject) {
			this.javaProject = javaProject;
		}

		public IPackageFragment getPackageFragment() {
			return packageFragment;
		}

		public void setPackageFragment(IPackageFragment packageFragment) {
			this.packageFragment = packageFragment;
		}

		public IFile getSchemaFileName() {
			return schemaFileName;
		}

		public void setSchemaFileName(IFile schemaFileName) {
			this.schemaFileName = schemaFileName;
		}

		public java.util.List<IPackageFragment> getLallXMLPackageFragments() {
			return lallXMLPackageFragments;
		}

		public void setLallXMLPackageFragments(
				java.util.List<IPackageFragment> lallXMLPackageFragments) {
			this.lallXMLPackageFragments = lallXMLPackageFragments;
		}

		public String getOptions() {
			return options;
		}

		public void setOptions(String options) {
			this.options = options;
		}
	}
}
