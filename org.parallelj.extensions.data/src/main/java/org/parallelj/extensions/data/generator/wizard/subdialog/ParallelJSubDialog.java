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
package org.parallelj.extensions.data.generator.wizard.subdialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.parallelj.extensions.data.generator.Activator;
import org.parallelj.extensions.data.generator.wizard.dialog.PackageSelectionDialog.DialogData;
import org.parallelj.extensions.data.generator.wizard.filter.FilePackageSearchFilter;
import org.parallelj.extensions.data.generator.wizard.util.CustomContentProvider;
import org.parallelj.extensions.data.generator.wizard.util.CustomLabelProvider;

/**
 * Generic Sub Dialog
 * 
 * @author A169104
 * 
 */
public abstract class ParallelJSubDialog extends Dialog {
	protected int tableViewerStyle = SWT.BORDER | SWT.FULL_SELECTION;
	private Table table;
	private HashMap<Integer, Button> buttons = new HashMap<Integer, Button>();
	private Label dialogLabel;
	private Text searchText;
	private TableViewer tableViewer;
	private FilePackageSearchFilter filter;
	private java.util.List<?> lobjects;
	public ChildDialogData childData;
	public DialogData dialogData;

	protected abstract java.util.List<? extends Object> getObjectsList();

	protected abstract String getLabel();

	protected abstract String getTitle();

	public ParallelJSubDialog(Shell parentShell, DialogData dialogData) {
		super(parentShell);
		this.dialogData = dialogData;
		setShellStyle(SWT.MAX | SWT.APPLICATION_MODAL);
	}

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
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		childData = new ChildDialogData();
		{
			dialogLabel = new Label(container, SWT.NONE);
			{
				GridData gridData = new GridData(SWT.LEFT, SWT.CENTER, false,
						false, 1, 1);
				gridData.widthHint = 133;
				dialogLabel.setLayoutData(gridData);
			}
			dialogLabel.setText(getLabel());
		}
		{
			searchText = new Text(container, SWT.BORDER);
			searchText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
					false, 1, 1));
			searchText.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent ke) {
					filter.setSearchText(searchText.getText());
					tableViewer.refresh();

					if (table.getItemCount() == 0) {
						buttons.get(0).setEnabled(false);
					} else {
						buttons.get(0).setEnabled(true);
						table.select(0);
					}
				}
			});
		}
		filter = new FilePackageSearchFilter();
		tableViewer = new TableViewer(container, tableViewerStyle);
		tableViewer.setContentProvider(new CustomContentProvider());
		tableViewer.setLabelProvider(new CustomLabelProvider());
		tableViewer.addFilter(filter);
		lobjects = getObjectsList();
		if (lobjects != null && lobjects.size() > 0) {
			tableViewer.setInput(lobjects);
			childData.setLobjects(lobjects);
		}
		table = tableViewer.getTable();
		table.select(0);
		{
			GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true, 1,
					1);
			gridData.heightHint = 300;
			tableViewer.getControl().setLayoutData(gridData);
		}
		table.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				List<Object> datas = new ArrayList<Object>();
				for (int indice : table.getSelectionIndices()) {
					datas.add(table.getItem(indice).getData());

				}
				childData.setObject(datas);
			}
		});
		return container;
	}

	@Override
	protected Button createButton(Composite parent, int id, String label,
			boolean defaultButton) {
		((GridLayout) parent.getLayout()).numColumns++;
		Button button = new Button(parent, SWT.PUSH);
		button.setText(label);
		button.setFont(JFaceResources.getDialogFont());
		button.setData(new Integer(id));
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				if (((Integer) event.widget.getData()).intValue() == 0) {
					if (childData.getObject() == null) {
						if (table.getItem(0) != null) {
							List<Object> datas = new ArrayList<Object>();
							datas.add(table.getItem(0).getData());
							childData.setObject(datas);
						}
					}
				} else {
					if (childData != null)
						childData = null;
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
		if (((Integer) button.getData()).intValue() == 0) {
			if (childData != null && childData.getLobjects() == null) {
				button.setEnabled(false);
				dialogLabel.setEnabled(false);
				searchText.setEnabled(false);
			}
		}
		setButtonLayoutData(button);
		return button;
	}

	/**
	 * Return the initial size of the dialog.
	 */

	@Override
	protected Point getInitialSize() {
		return new Point(338, 376);
	}

	protected IDialogSettings getDialogSettings() {
		IDialogSettings workbenchSettings = Activator.getDefault()
				.getDialogSettings();
		IDialogSettings rootSettings = workbenchSettings
				.getSection("ParallelJSubDialog.org.parallelj.extensions.data");
		if (rootSettings == null)
			rootSettings = workbenchSettings
					.addNewSection("ParallelJSubDialog.org.parallelj.extensions.data");
		IDialogSettings settings = rootSettings.getSection(dialogData
				.getJavaProject().getElementName());
		if (settings == null)
			settings = rootSettings.addNewSection(dialogData.getJavaProject()
					.getElementName());
		return settings;
	}

	/**
	 * return ChildDialogData this method returns Package Dialog data to parent
	 * Data Perspective Dialog
	 */

	public ChildDialogData getChildData() {
		return childData;
	}

	/**
	 * this inner class contains Sub Dialog Data which is passed to the parent
	 * dialog on successful processing
	 * 
	 * @author A169104
	 * 
	 */
	public class ChildDialogData {
		private Object object = null;
		private java.util.List<?> lobjects;

		/**
		 * this method returns Object (IPackageFragment or XSD files)
		 * 
		 * @return Object
		 */
		public Object getObject() {
			return object;
		}

		/**
		 * 
		 * @param object
		 */

		public void setObject(Object object) {
			this.object = object;
		}

		/**
		 * this method returns a list of Objects(IPackageFragment or XSD files)
		 * 
		 * @return java.util.List<?>
		 */

		public java.util.List<?> getLobjects() {
			return lobjects;
		}

		/**
		 * 
		 * @param lobjects
		 */

		public void setLobjects(java.util.List<?> lobjects) {
			this.lobjects = lobjects;
		}
	}
}
