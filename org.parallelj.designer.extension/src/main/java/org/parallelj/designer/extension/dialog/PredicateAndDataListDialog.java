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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
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
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.FilteredList;
import org.eclipse.ui.dialogs.FilteredList.FilterMatcher;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.parallelj.designer.extension.tools.IterableDataUtils;
import org.parallelj.designer.part.ParallelJDiagramEditorPlugin;
import org.parallelj.model.Data;
import org.parallelj.model.Element;
import org.parallelj.model.ForEachLoop;
import org.parallelj.model.Link;
import org.parallelj.model.Pipeline;
import org.parallelj.model.Predicate;
import org.parallelj.model.Program;
import org.parallelj.model.provider.ParallelJEditPlugin;

/**
 * Dialog box which opens to display the List of Available Predicate or Data as
 * per selected object.
 * 
 */
public class PredicateAndDataListDialog {

	/**
	 * Dialog Result
	 */
	private Object result = null;

	/**
	 * Dialog return Status
	 */
	private int returnStatus = SWT.OK;

	/**
	 * "No element" mock object
	 */
	public static final String NO_ELEMENT = "(Null Element)";

	/**
	 * Shell for Dialog Box
	 */
	private Shell shell;

	/**
	 * Filtered List
	 */
	private FilteredList filteredList;

	/**
	 * Boolean managing dialog.
	 */
	private boolean keepOpen = true;

	/**
	 * OK Button
	 */
	private Button okButton;

	/**
	 * Cancel Button
	 */
	private Button cancelButton;

	/**
	 * Input EObject
	 */
	private EObject inputEObject;

	/**
	 * Input Structural Feature
	 */
	private EStructuralFeature inputStructuralFeature;

	/**
	 * @return Dialog Result Object
	 */
	public Object getResult() {
		return this.result;
	}

	/**
	 * @return Dialog return Status
	 */
	public int getReturnStatus() {
		return this.returnStatus;
	}

	/**
	 * Creates new Dialog from input Element
	 * 
	 * @param inputEObject
	 *            input EObject
	 * @param inputStructuralFeature
	 *            input EStructuralFeature
	 */
	public PredicateAndDataListDialog(EObject inputEObject,
			EStructuralFeature inputStructuralFeature) {
		this.inputEObject = inputEObject;
		this.inputStructuralFeature = inputStructuralFeature;
		this.shell = new Shell(Display.getDefault(), SWT.DIALOG_TRIM
				| SWT.RESIZE | SWT.APPLICATION_MODAL);
		String instanceClassName = inputStructuralFeature.getEGenericType()
				.getEClassifier().getInstanceClass().getSimpleName();
		if (instanceClassName.equals("Data")) {
			instanceClassName = "Iterable " + instanceClassName;
		}
		this.shell.setText("ParallelJ: List of available " + instanceClassName);

		Label label1 = new Label(this.shell, SWT.NONE);
		label1.setText("Enter pattern");

		Text pattern = new Text(this.shell, SWT.BORDER);

		Label label2 = new Label(this.shell, SWT.NONE);
		label2.setText(String.format("Matching %s:", instanceClassName + "s"));
		ImageDescriptor descriptor = ParallelJDiagramEditorPlugin
				.getBundledImageDescriptor("/icons/obj16/ParallelJDiagramFile.png");
		if (descriptor != null)
			this.shell.setImage(descriptor.createImage());
		this.shell.addListener(SWT.Traverse, new Listener() {
			public void handleEvent(Event e) {
				if (e.detail == SWT.TRAVERSE_ESCAPE) {
					PredicateAndDataListDialog.this.result = null;
					PredicateAndDataListDialog.this.returnStatus = SWT.CANCEL;
					e.doit = false;
					PredicateAndDataListDialog.this.keepOpen = false;
				}
			}
		});

		this.shell.addListener(SWT.Close, new Listener() {
			public void handleEvent(Event e) {
				PredicateAndDataListDialog.this.result = null;
				PredicateAndDataListDialog.this.returnStatus = SWT.CANCEL;
				e.doit = false;
				PredicateAndDataListDialog.this.keepOpen = false;
			}
		});

		this.shell.setLayout(new FormLayout());

		this.filteredList = new FilteredList(this.shell, SWT.BORDER,
				new ThisLabelProvider(), true, false, true);

		this.filteredList.setFilterMatcher(new FilterMatcher() {

			private String pattern;

			public void setFilter(String pattern, boolean ignoreCase,
					boolean ignoreWildCards) {
				this.pattern = pattern;
			}

			public boolean match(Object element) {
				if (this.pattern == null || this.pattern.length() == 0)
					return true;
				if (element instanceof EObject) {
					String name = PredicateAndDataListDialog.this
							.getName((EObject) element);
					if (name != null) {
						if (name.lastIndexOf(".") > -1 && !name.endsWith(".")) {
							String shortName = name.substring(name
									.lastIndexOf(".") + 1);
							if (shortName.toLowerCase().startsWith(
									this.pattern.toLowerCase()))
								return true;
						} else
							return name.toLowerCase().startsWith(
									this.pattern.toLowerCase());
					}
				} else
					return this.pattern != null
							&& this.pattern.equalsIgnoreCase(element.toString());
				return false;
			}
		});
		this.okButton = new Button(this.shell, SWT.PUSH);
		this.okButton.setText("OK");
		this.cancelButton = new Button(this.shell, SWT.PUSH);
		this.cancelButton.setText("Cancel");

		FormData data;
		data = new FormData();
		data.left = new FormAttachment(0, 5);
		data.top = new FormAttachment(0, 5);
		data.right = new FormAttachment(100, -5);
		label1.setLayoutData(data);

		data = new FormData();
		data.left = new FormAttachment(0, 5);
		data.top = new FormAttachment(label1, 5);
		data.right = new FormAttachment(100, -5);
		pattern.setLayoutData(data);

		data = new FormData();
		data.left = new FormAttachment(0, 5);
		data.top = new FormAttachment(pattern, 10);
		data.right = new FormAttachment(100, -5);
		label2.setLayoutData(data);

		data = new FormData();
		data.left = new FormAttachment(0, 5);
		data.top = new FormAttachment(label2, 5);
		data.bottom = new FormAttachment(this.okButton, -5);
		data.right = new FormAttachment(100, -5);
		this.filteredList.setLayoutData(data);

		data = new FormData();
		data.bottom = new FormAttachment(100, -5);
		data.right = new FormAttachment(100, -5);
		data.width = 80;
		this.cancelButton.setLayoutData(data);

		data = new FormData();
		data.bottom = new FormAttachment(100, -5);
		data.right = new FormAttachment(this.cancelButton, -5);
		data.width = 80;
		this.okButton.setLayoutData(data);

		this.okButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				Object[] selection = PredicateAndDataListDialog.this.filteredList
						.getSelection();
				if (selection.length > 0) {
					PredicateAndDataListDialog.this.result = selection[0];
					PredicateAndDataListDialog.this.returnStatus = SWT.OK;
					PredicateAndDataListDialog.this.keepOpen = false;
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		this.cancelButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				PredicateAndDataListDialog.this.result = null;
				PredicateAndDataListDialog.this.returnStatus = SWT.CANCEL;
				PredicateAndDataListDialog.this.keepOpen = false;
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		pattern.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				PredicateAndDataListDialog.this.filteredList
						.setFilter(((Text) e.getSource()).getText().trim());
			}
		});

		pattern.addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.ARROW_DOWN)
					PredicateAndDataListDialog.this.filteredList.setFocus();
				else if (e.keyCode == SWT.ARROW_UP)
					PredicateAndDataListDialog.this.filteredList.setFocus();
			}

			public void keyReleased(KeyEvent e) {
			}
		});

		this.filteredList.addListener(SWT.Traverse, new Listener() {
			public void handleEvent(Event e) {
				if (e.detail == SWT.TRAVERSE_RETURN) {
					Object[] selection = PredicateAndDataListDialog.this.filteredList
							.getSelection();
					if (selection.length > 0) {
						PredicateAndDataListDialog.this.result = selection[0];
						PredicateAndDataListDialog.this.returnStatus = SWT.OK;
						PredicateAndDataListDialog.this.keepOpen = false;
					}
				}
			}
		});

		this.filteredList.addListener(SWT.MouseDoubleClick, new Listener() {
			public void handleEvent(Event e) {
				Object[] selection = PredicateAndDataListDialog.this.filteredList
						.getSelection();
				if (selection.length > 0) {
					PredicateAndDataListDialog.this.result = selection[0];
					PredicateAndDataListDialog.this.returnStatus = SWT.OK;
					PredicateAndDataListDialog.this.keepOpen = false;
				}
			}
		});

		this.filteredList.addMouseListener(new MouseListener() {

			public void mouseUp(MouseEvent e) {

			}

			public void mouseDown(MouseEvent e) {

			}

			public void mouseDoubleClick(MouseEvent e) {

			}
		});
	}

	/**
	 * Opens dialog
	 */
	public void open() {
		ArrayList<EObject> contents = new ArrayList<EObject>();
		Program program = null;
		EObject eObject = this.inputEObject;
		Collection<Object> elements = new ArrayList<Object>();

		if (eObject instanceof Link) {
			elements.add(PredicateAndDataListDialog.NO_ELEMENT);
		}

		while (eObject != null) {
			if (eObject instanceof Program) {
				program = (Program) eObject;
				break;
			} else {
				eObject = eObject.eContainer();
			}
		}
		if (program != null) {
			if (this.inputEObject instanceof ForEachLoop || this.inputEObject instanceof Pipeline) {
				EList<Data> datas = program.getData();
				for (Data data : datas) {
					IterableDataUtils.populateIterableData(contents, data);
				}

				// contents.addAll(program.getData());
			} else {
				contents.addAll(program.getPredicates());
			}
		}
		elements.addAll(contents);
		Object currentValue = this.inputEObject
				.eGet(this.inputStructuralFeature);

		this.filteredList.setElements(elements.toArray());
		this.filteredList
				.setSelection(new Object[] { currentValue == null ? PredicateAndDataListDialog.NO_ELEMENT
						: currentValue });
		this.shell.setMinimumSize(500, 400);
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

	/**
	 * Label provider for FilteredList
	 * 
	 * 
	 */
	private class ThisLabelProvider extends LabelProvider {

		/**
		 * Image Cache
		 */
		private Map<EClass, Image> imageCache = new HashMap<EClass, Image>();

		/**
		 * Constant, defining the URL prefix to images
		 */
		private static final String IMG_PATH_PREFIX = "/icons/full/obj16/";

		/**
		 * Constant, defining the file extension of UML images
		 */
		private static final String IMG_EXTENSION = ".gif";

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
		 */
		@Override
		public String getText(Object element) {
			if (element instanceof String)
				return (String) element;
			if (element instanceof EObject) {
				EObject eObject = (EObject) element;
				String name = PredicateAndDataListDialog.this.getName(eObject);
				String type = eObject.eClass().getName();
				String location = "";
				if (eObject.eResource() != null
						&& !eObject.eResource().equals(
								PredicateAndDataListDialog.this.inputEObject
										.eResource()))
					location = " (In Model \""
							+ eObject.eResource().getURI().lastSegment()
							+ "\")";
				return "<" + type + "> " + name + location;
			}
			return super.getText(element);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
		 */
		@Override
		public Image getImage(Object element) {
			if (element instanceof EObject) {
				EObject eObject = (EObject) element;
				Image image = this.imageCache.get(eObject.eClass());
				if (image == null) {
					ImageDescriptor imageDescriptor = AbstractUIPlugin
							.imageDescriptorFromPlugin(
									ParallelJEditPlugin.INSTANCE
											.getSymbolicName(),
									ThisLabelProvider.IMG_PATH_PREFIX
											+ eObject.eClass().getName()
											+ ThisLabelProvider.IMG_EXTENSION);
					if (imageDescriptor != null) {
						image = imageDescriptor.createImage();
						this.imageCache.put(eObject.eClass(), image);
					}
				}
				return image;
			}
			return super.getImage(element);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.BaseLabelProvider#dispose()
		 */
		@Override
		public void dispose() {
			for (Image image : this.imageCache.values())
				image.dispose();
			this.imageCache.clear();
		}

	}

	/**
	 * Returns ParallelJ element name
	 * 
	 * @param element
	 *            : ParallelJ element
	 * @return ParallelJ element name
	 */
	public String getName(EObject element) {
		if (element instanceof Data)
			return ((Data) element).getName();
		else if (element instanceof Element)
			return ((Element) element).getName();
		else if (element instanceof Predicate)
			return ((Predicate) element).getName();
		return "";
	}

}
