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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * Dialog box which opens to display the error if click on code generator
 * despite of errors in Model diagram
 * 
 */
public class ValidationErrorDialog {

	/**
	 * Shell for Dialog Box
	 */
	private Shell shell;

	/**
	 * Boolean managing dialog
	 */
	private boolean keepOpen = true;

	/**
	 * OK Button
	 */
	private Button okButton;

	/**
	 * Creates new Dialog from input Element
	 */
	public ValidationErrorDialog() {

		this.shell = new Shell(Display.getDefault(), SWT.DIALOG_TRIM
				| SWT.RESIZE | SWT.APPLICATION_MODAL);
		this.shell.setText("Errors in the Parallel J Diagram !!! ");
		Label label1 = new Label(this.shell, SWT.NONE);
		label1.setText(" Remaining errors prevented from generating the code.... ");

		this.shell.addListener(SWT.Traverse, new Listener() {
			public void handleEvent(Event e) {
				if (e.detail == SWT.TRAVERSE_ESCAPE) {
					e.doit = false;
					ValidationErrorDialog.this.keepOpen = false;
				}
			}
		});
		this.shell.setLayout(new FormLayout());
		this.okButton = new Button(this.shell, SWT.PUSH);
		this.okButton.setText("OK");

		FormData data;
		data = new FormData();
		data.left = new FormAttachment(0, 5);
		data.top = new FormAttachment(0, 5);
		data.right = new FormAttachment(100, -5);
		label1.setLayoutData(data);

		data = new FormData();
		data.bottom = new FormAttachment(100, -5);
		data.right = new FormAttachment(100, -5);
		data.width = 80;
		this.okButton.setLayoutData(data);

		this.okButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				close();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

	}

	/**
	 * Opens dialog
	 */
	public void open() {

		this.shell.setMinimumSize(310, 95);
		this.shell.pack();
		Rectangle rect = this.shell.getBounds();
		this.shell.setLocation(
				(this.shell.getDisplay().getBounds().width - rect.width) / 2,
				(this.shell.getDisplay().getBounds().height - rect.height) / 2);
		this.shell.open();
		while (this.keepOpen && !this.shell.isDisposed())
			if (!this.shell.getDisplay().readAndDispatch())
				this.shell.getDisplay().sleep();
		this.shell.dispose();
	}

	/**
	 * Closes dialog
	 */
	public void close() {
		this.keepOpen = false;
	}

}
