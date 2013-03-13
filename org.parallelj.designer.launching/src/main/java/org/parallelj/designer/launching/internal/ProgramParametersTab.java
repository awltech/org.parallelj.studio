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
package org.parallelj.designer.launching.internal;

import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.internal.ui.SWTFactory;
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaLaunchTab;
import org.eclipse.jdt.internal.debug.ui.JavaDebugImages;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.parallelj.designer.launching.ParalleljMainTab;

/**
 * This tab appears for java applet launch configurations and allows the user to
 * edit applet-specific attributes such as width, height, name & applet
 * parameters.
 * <p>
 * This class may be instantiated.
 * </p>
 * 
 * @since 2.1
 * @noextend This class is not intended to be subclassed by clients.
 */
@SuppressWarnings("restriction")
public class ProgramParametersTab extends JavaLaunchTab {
	private Button fParametersEditButton;

	ParalleljMainTab mainTab;

	public ProgramParametersTab(ParalleljMainTab mainTab) {
		super();
		this.mainTab = mainTab;
	}

	private class ProgramParametersTabListener extends SelectionAdapter
			implements ModifyListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.
		 * events.ModifyEvent)
		 */
		public void modifyText(ModifyEvent e) {
			updateLaunchConfigurationDialog();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse
		 * .swt.events.SelectionEvent)
		 */
		public void widgetSelected(SelectionEvent e) {
			Object source = e.getSource();
			if (source == fViewer.getTable() || source == fViewer) {
				setParametersButtonsEnableState();
			} else if (source == fParametersEditButton) {
				handleParametersEditButtonSelected();
			}
		}
	}

	private ProgramParametersTabListener fListener = new ProgramParametersTabListener();

	/**
	 * The parameters table viewer
	 */
	private TableViewer fViewer;

	/**
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		
		Composite comp = SWTFactory.createComposite(parent, 1, 1,
				GridData.FILL_HORIZONTAL);
		setControl(comp);

		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);

		Composite paramcomp = SWTFactory.createComposite(comp, comp.getFont(),
				2, 1, GridData.FILL_BOTH, 0, 0);

		SWTFactory.createLabel(paramcomp, "WARNING: ", 2);
		SWTFactory.createLabel(paramcomp, "      Each parameter must have a public setter method in the Program !!!", 2);
		SWTFactory.createLabel(paramcomp, "Parameters:", 2);

		Table ptable = new Table(paramcomp, SWT.FULL_SELECTION | SWT.BORDER);
		fViewer = new TableViewer(ptable);
		gd = new GridData(GridData.FILL_BOTH);
		ptable.setLayoutData(gd);
		TableColumn column1 = new TableColumn(ptable, SWT.NONE);
		column1.setText("Name");
		TableColumn column2 = new TableColumn(ptable, SWT.NONE);
		column2.setText("Value");
		TableLayout tableLayout = new TableLayout();
		ptable.setLayout(tableLayout);
		tableLayout.addColumnData(new ColumnWeightData(100));
		tableLayout.addColumnData(new ColumnWeightData(100));
		ptable.setHeaderVisible(true);
		ptable.setLinesVisible(true);
		ptable.addSelectionListener(fListener);
		ptable.addMouseListener(new MouseAdapter() {
			public void mouseDoubleClick(MouseEvent e) {
				setParametersButtonsEnableState();
				if (fParametersEditButton.isEnabled()) {
					handleParametersEditButtonSelected();
				}
			}
		});

		fViewer.setContentProvider(new IStructuredContentProvider() {
			public Object[] getElements(Object inputElement) {
				@SuppressWarnings("rawtypes")
				Map params = (Map) inputElement;
				return params.keySet().toArray();
			}

			public void dispose() {
			}

			public void inputChanged(Viewer viewer, Object oldInput,
					Object newInput) {
			}
		});

		fViewer.setLabelProvider(new ITableLabelProvider() {
			public Image getColumnImage(Object element, int columnIndex) {
				return null;
			}

			public String getColumnText(Object element, int columnIndex) {
				if (columnIndex == 0) {
					return element.toString();
				}

				String key = (String) element;
				@SuppressWarnings("rawtypes")
				Map params = (Map) fViewer.getInput();
				Object object = params.get(key);
				if (object != null)
					return object.toString();
				return null;
			}

			public void addListener(ILabelProviderListener listener) {
			}

			public void dispose() {
			}

			public boolean isLabelProperty(Object element, String property) {
				return false;
			}

			public void removeListener(ILabelProviderListener listener) {
			}
		});

		fViewer.setComparator(new ViewerComparator());

		Composite envcomp = SWTFactory.createComposite(paramcomp,
				paramcomp.getFont(), 1, 1, GridData.VERTICAL_ALIGN_BEGINNING
						| GridData.HORIZONTAL_ALIGN_FILL, 0, 0);

		fParametersEditButton = createPushButton(envcomp, "Edit...", null);
		fParametersEditButton.addSelectionListener(fListener);

		setParametersButtonsEnableState();
		Dialog.applyDialogFont(parent);
	}

	/**
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#isValid(ILaunchConfiguration)
	 */
	public boolean isValid(ILaunchConfiguration launchConfig) {
		setErrorMessage(null);
		return true;
	}

	private void handleParametersEditButtonSelected() {
		IStructuredSelection selection = (IStructuredSelection) fViewer
				.getSelection();
		String key = (String) selection.getFirstElement();
		@SuppressWarnings("rawtypes")
		Map params = (Map) fViewer.getInput();
		String value = (String) params.get(key);

		InputDialog dialog = new InputDialog(getShell(),
				"Edit Program parameter", "Name:" + key, value, null);

		openNewParameterDialog(dialog, key);
	}

	/**
	 * Set the enabled state of the three environment variable-related buttons
	 * based on the selection in the Table widget.
	 */
	private void setParametersButtonsEnableState() {
		IStructuredSelection selection = (IStructuredSelection) fViewer
				.getSelection();
		int selectCount = selection.size();
		if (selectCount < 1) {
			fParametersEditButton.setEnabled(false);
		} else {
			if (selectCount == 1) {
				fParametersEditButton.setEnabled(true);
			} else {
				fParametersEditButton.setEnabled(false);
			}
		}
	}

	/**
	 * Show the specified dialog and update the parameter table based on its
	 * results.
	 * 
	 * @param updateItem
	 *            the item to update, or <code>null</code> if adding a new item
	 */
	@SuppressWarnings("unchecked")
	private void openNewParameterDialog(InputDialog dialog, String key) {
		if (dialog.open() != Window.OK) {
			return;
		}
		String value = dialog.getValue();
		@SuppressWarnings("rawtypes")
		Map params = (Map) fViewer.getInput();
		params.remove(key);
		params.put(key, value);
		fViewer.refresh();
		updateLaunchConfigurationDialog();
	}

	/**
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#performApply(ILaunchConfigurationWorkingCopy)
	 */
	@SuppressWarnings("rawtypes")
	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		configuration.setAttribute(
				"org.parallelj.studio.launching.PARALLELJ_PARAMETERS",
				(Map) fViewer.getInput());
	}

	/**
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#setDefaults(ILaunchConfigurationWorkingCopy)
	 */
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
		System.out.println("test");
	}

	/**
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(ILaunchConfiguration)
	 */
	@SuppressWarnings("rawtypes")
	public void initializeFrom(ILaunchConfiguration config) {
		//set
		try {
			@SuppressWarnings("unchecked")
			Map<Object, Object> savedParams = config.getAttribute(
					"org.parallelj.studio.launching.PARALLELJ_PARAMETERS",
					(Map) null);
			Map input = ProgramUtils.getProgramParameters(
					this.mainTab.getMainProject(), this.mainTab.getMainType(),
					savedParams);
			fViewer.setInput(input);
		} catch (CoreException e) {
		}

	}

	/**
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getName()
	 */
	public String getName() {
		return "Program Parameters";
	}

	/**
	 * @see org.eclipse.debug.ui.AbstractLaunchConfigurationTab#getId()
	 * 
	 * @since 3.3
	 */
	public String getId() {
		return "org.parallelj.studio.launching.paralleljParametersTab"; //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getImage()
	 */
	public Image getImage() {
		return JavaDebugImages.get(JavaDebugImages.IMG_VIEW_ARGUMENTS_TAB);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.debug.ui.ILaunchConfigurationTab#activated(org.eclipse.debug
	 * .core.ILaunchConfigurationWorkingCopy)
	 */
	public void activated(ILaunchConfigurationWorkingCopy workingCopy) {
		@SuppressWarnings("unchecked")
		Map<Object, Object> finput = (Map<Object, Object>)fViewer.getInput();

		if (this.mainTab.getMainProject()!= null
				&& this.mainTab.getMainProject().length()>0
				&& this.mainTab.getMainType()!=null
				&& this.mainTab.getMainType().length()>0
				&&finput.size()==0
				) {
			Map<Object, Object> map = ProgramUtils.getProgramParameters(this.mainTab.getMainProject(), this.mainTab.getMainType(), null);
			fViewer.setInput(map);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.debug.ui.ILaunchConfigurationTab#deactivated(org.eclipse.
	 * debug.core.ILaunchConfigurationWorkingCopy)
	 */
	public void deactivated(ILaunchConfigurationWorkingCopy workingCopy) {
		// do nothing when de-activated
	}

}
