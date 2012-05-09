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
package org.parallelj.designer.extension.dialog;

import static org.eclipse.jface.dialogs.IDialogConstants.CANCEL_LABEL;
import static org.eclipse.jface.dialogs.IDialogConstants.ENTRY_FIELD_WIDTH;
import static org.eclipse.jface.dialogs.IDialogConstants.OK_ID;
import static org.eclipse.jface.dialogs.IDialogConstants.OK_LABEL;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.parallelj.designer.extension.Activator;
import org.parallelj.designer.extension.Messages;
import org.parallelj.designer.extension.part.contrib.BusinessProcedureContribution;
import org.parallelj.designer.extension.part.contrib.BusinessProcedureContributionManager;

/**
 * SelectionDialog, adapted for ParallelJ, since it is able to retrieve all
 * Diagram models from a project
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public abstract class SelectionDialog extends MessageDialog {

	private static final String DIALOG_SETTINGS_SECTION = "ClasDiagramsDialogSettings"; //$NON-NLS-1$

	private static final String DIALOG_ORIGIN_X = "DIALOG_X_ORIGIN"; //$NON-NLS-1$

	private static final String DIALOG_ORIGIN_Y = "DIALOG_Y_ORIGIN"; //$NON-NLS-1$

	private static final String DIALOG_WIDTH = "DIALOG_WIDTH"; //$NON-NLS-1$

	private static final String DIALOG_HEIGHT = "DIALOG_HEIGHT"; //$NON-NLS-1$

	private static final String TOGGLE_SELECTED = "TOGGLE_SELECTED"; //$NON-NLS-1$

	private Button allButton, selectButton;

	protected CheckboxTableViewer contributionsTable;

	protected IJavaProject currentProject = null;

	protected List<BusinessProcedureContribution> selectedContributions = null;

	protected List<BusinessProcedureContribution> allContributions = null;

	protected IWorkbenchWindow window = null;

	/**
	 * Creates a new class diagrams dialog.
	 * 
	 * @param window
	 *            the window to create it in
	 * @param selection
	 *            the currently selected project
	 */
	public SelectionDialog(IWorkbenchWindow window, IJavaProject selectedProject) {
		super(window.getShell(), Messages.UI_DIALOG_TITLE.getMessage(), null,
				Messages.UI_DIALOG_MESSAGE.getMessage(), QUESTION,
				new String[] { OK_LABEL, CANCEL_LABEL }, 0);

		this.currentProject = selectedProject;
		this.selectedContributions = new ArrayList<BusinessProcedureContribution>();
		this.allContributions = new ArrayList<BusinessProcedureContribution>();
		this.window = window;

		setShellStyle(SWT.RESIZE | getShellStyle());
	}

	protected void buttonPressed(int buttonId) {
		final boolean openAll = allButton.getSelection();
		super.buttonPressed(buttonId);

		if (buttonId != OK_ID) {
			return;
		}

		performOperation(openAll);
	}

	protected Control createCustomArea(Composite parent) {
		Composite area = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = layout.marginHeight = 0;
		layout.numColumns = 2;
		layout.makeColumnsEqualWidth = true;
		area.setLayout(layout);
		area.setLayoutData(new GridData(GridData.FILL_BOTH));

		SelectionListener updateEnablement = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				updateEnablement();
			}
		};

		IDialogSettings settings = getDialogSettings(DIALOG_SETTINGS_SECTION);
		boolean selectSelectedButton = settings.getBoolean(TOGGLE_SELECTED);

		allButton = new Button(area, SWT.RADIO);
		String projectName = currentProject != null ? currentProject
				.getProject().getName() : null;
		allButton.setText(Messages.UI_ALL_BUTTON_MESSAGE_PROJECT
				.getMessage(projectName));
		allButton.setSelection(!selectSelectedButton);
		allButton.addSelectionListener(updateEnablement);

		selectButton = new Button(area, SWT.RADIO);
		selectButton.setText(Messages.UI_SELECT_BUTTON_MESSAGE.getMessage());
		selectButton.setSelection(selectSelectedButton);
		selectButton.addSelectionListener(updateEnablement);

		createmodelSelectionTable(area);

		contributionsTable.getTable().setEnabled(selectSelectedButton);
		return area;
	}

	private void searchContributions() {

		Collection<BusinessProcedureContribution> contributions = BusinessProcedureContributionManager
				.getInstance().getContributions();

		if (!contributions.isEmpty()) {

			for (BusinessProcedureContribution contribution : contributions) {

				contributionsTable.add(contribution);
				allContributions.add(contribution);
			}

		}
	}

	private void createmodelSelectionTable(Composite radioGroup) {
		contributionsTable = CheckboxTableViewer.newCheckList(radioGroup,
				SWT.BORDER);
		contributionsTable.setLabelProvider(new SelectionDialogLabelProvider());

		searchContributions();

		GridData data = new GridData(GridData.FILL_BOTH);
		data.horizontalSpan = 2;
		data.widthHint = ENTRY_FIELD_WIDTH;
		data.heightHint = ENTRY_FIELD_WIDTH;
		contributionsTable.getTable().setLayoutData(data);
		contributionsTable.getTable().setEnabled(selectButton.getSelection());
		contributionsTable.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				if (event.getChecked()
						&& !selectedContributions.contains(event.getElement())) {
					selectedContributions
							.add((BusinessProcedureContribution) event
									.getElement());
				} else if (!event.getChecked()
						&& selectedContributions.contains(event.getElement())) {
					selectedContributions
							.remove((BusinessProcedureContribution) event
									.getElement());
				}

				updateEnablement();
			}
		});
	}

	/**
	 * Performs the actual operation.
	 */
	protected abstract void performOperation(boolean allSelected);

	/**
	 * Updates the enablement of the dialog's ok button based on the current
	 * choices in the dialog.
	 */
	protected void updateEnablement() {
		contributionsTable.getTable().setEnabled(selectButton.getSelection());
		boolean enabled = allButton.getSelection()
				|| selectedContributions.size() > 0;
		getButton(OK).setEnabled(enabled);
	}

	public boolean close() {
		persistDialogSettings(getShell(), DIALOG_SETTINGS_SECTION);
		return super.close();
	}

	protected Point getInitialLocation(Point initialSize) {
		Point p = getInitialLocation(DIALOG_SETTINGS_SECTION);
		return p != null ? p : super.getInitialLocation(initialSize);
	}

	protected Point getInitialSize() {
		Point p = super.getInitialSize();
		return getInitialSize(DIALOG_SETTINGS_SECTION, p);
	}

	/**
	 * Returns the initial location which is persisted in the Ant UI Plugin
	 * dialog settings under the provided dialog setttings section name. If
	 * location is not persisted in the settings, the <code>null</code> is
	 * returned.
	 * 
	 * @param dialogSettingsSectionName
	 *            The name of the dialog settings section
	 * @return The initial location or <code>null</code>
	 */
	public Point getInitialLocation(String dialogSettingsSectionName) {
		IDialogSettings settings = getDialogSettings(dialogSettingsSectionName);
		try {
			int x = settings.getInt(DIALOG_ORIGIN_X);
			int y = settings.getInt(DIALOG_ORIGIN_Y);
			return new Point(x, y);
		} catch (NumberFormatException e) {
		}
		return null;
	}

	private IDialogSettings getDialogSettings(String dialogSettingsSectionName) {
		IDialogSettings settings = Activator.getDefault().getDialogSettings();
		IDialogSettings section = settings
				.getSection(dialogSettingsSectionName);

		if (section == null) {
			section = settings.addNewSection(dialogSettingsSectionName);
		}

		return section;
	}

	/**
	 * Persists the location and dimensions of the shell and other user settings
	 * in the plugin's dialog settings under the provided dialog settings
	 * section name
	 * 
	 * @param shell
	 *            The shell whose geometry is to be stored
	 * @param dialogSettingsSectionName
	 *            The name of the dialog settings section
	 */
	private void persistDialogSettings(Shell shell,
			String dialogSettingsSectionName) {
		Point shellLocation = shell.getLocation();
		Point shellSize = shell.getSize();
		IDialogSettings settings = getDialogSettings(dialogSettingsSectionName);
		settings.put(DIALOG_ORIGIN_X, shellLocation.x);
		settings.put(DIALOG_ORIGIN_Y, shellLocation.y);
		settings.put(DIALOG_WIDTH, shellSize.x);
		settings.put(DIALOG_HEIGHT, shellSize.y);
		settings.put(TOGGLE_SELECTED, selectButton.getSelection());
	}

	/**
	 * Returns the initial size which is the larger of the
	 * <code>initialSize</code> or the size persisted in the Ant UI Plugin
	 * dialog settings under the provided dialog setttings section name. If no
	 * size is persisted in the settings, the <code>initialSize</code> is
	 * returned.
	 * 
	 * @param initialSize
	 *            The initialSize to compare against
	 * @param dialogSettingsSectionName
	 *            The name of the dialog settings section
	 * @return the initial size
	 */
	private Point getInitialSize(String dialogSettingsSectionName,
			Point initialSize) {
		IDialogSettings settings = getDialogSettings(dialogSettingsSectionName);
		try {
			int x, y;
			x = settings.getInt(DIALOG_WIDTH);
			y = settings.getInt(DIALOG_HEIGHT);
			return new Point(Math.max(x, initialSize.x), Math.max(y,
					initialSize.y));
		} catch (NumberFormatException e) {
		}
		return initialSize;
	}

}
