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
package org.parallelj.designer.validation.adapters;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.swt.widgets.Shell;
import org.parallelj.designer.validation.DiagramValidationEngine;
import org.parallelj.designer.validation.DiagramValidationPlugin;
import org.parallelj.model.impl.BusinessProcedureImpl;

/**
 * LiveValidationContentAdapter is an adapter that maintains itself as an
 * adapter for all contained objects as they come and go.
 * 
 */
public class LiveValidationContentAdapter extends EContentAdapter {

	/**
	 * shell
	 */
	private final Shell shell;

	/**
	 * diagram
	 */
	private final Diagram diagram;

	// This is commented for further discussion.
	// private ILiveValidator validator = null;

	/**
	 * Constructor
	 * 
	 * @param enableLiveValidationDelegate
	 */
	public LiveValidationContentAdapter(Shell shell, Diagram diagram) {
		this.shell = shell;
		this.diagram = diagram;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.ecore.util.EContentAdapter#notifyChanged(org.eclipse.
	 * emf.common.notify.Notification)
	 */
	@Override
	public void notifyChanged(final Notification notification) {
		super.notifyChanged(notification);

		// If the Notification#getEventType is same as
		// Notification.REMOVING_ADAPTER then don't perform the live Validation
		// to avoid errors.
		if (notification.getEventType() != Notification.REMOVING_ADAPTER
				&& isBusinessProcedureToCheck(notification)) {

			// This is commented explicitly to discuss further.
			// if (validator == null) {
			// validator = ModelValidationService.getInstance().newValidator(
			// EvaluationMode.LIVE);
			// }
			// IStatus status = validator.validate(notification);
			// System.out.println(notification.getEventType());
			// if (notification.getEventType() == Notification.SET) {
			// DiagramValidationEngine.runValidation(this.diagram);
			// }

			// Checks that Live Validation is enabled
			if (DiagramValidationPlugin.getDefault().isLiveValidationEnabled()) {
				// Performs Validation
				this.shell.getDisplay().asyncExec(new Runnable() {
					public void run() {
						// if (validator == null) {
						// validator = ModelValidationService.getInstance()
						// .newValidator(EvaluationMode.LIVE);
						// }
						// IStatus status = validator.validate(notification);
						DiagramValidationEngine.runValidation(diagram);
					}
				});
			}
		}
	}

	/**
	 * This method checks is notification is new, since for BusinessProcedure
	 * notification goes in infinite loop
	 * 
	 * @param notification
	 * @return
	 */
	private boolean isBusinessProcedureToCheck(Notification notification) {
		if (notification.getNotifier() instanceof BusinessProcedureImpl
				&& notification.getEventType() == Notification.SET
				&& notification.getOldValue() != null
				&& (notification.getNewValue().toString().equals(notification
						.getOldValue().toString()))) {
			return false;
		} else {
			return true;
		}
	}
}
