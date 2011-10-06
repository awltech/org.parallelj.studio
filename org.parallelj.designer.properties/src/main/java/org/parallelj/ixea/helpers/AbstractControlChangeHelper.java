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
package org.parallelj.ixea.helpers;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * AbstractControlChangeHelper notifies the listener of Control lifecycle events 
 * on behalf of the widget(s) it listens to. 
 * 
 */
public abstract class AbstractControlChangeHelper implements Listener {
	
	private boolean nonUserChange;

	/**
	 * Marks the start of a programmatic change to the widget contents.
	 * Clients must call startNonUserChange() before directly setting 
	 * the widget contents to avoid unwanted lifecycle events.
	 * @throws IllegalArgumentException if a programmatic change is 
	 * already in progress.
	 */
	public void startNonUserChange() {
		if (nonUserChange)
			throw new IllegalStateException("we already started a non user change");//$NON-NLS-1$
		nonUserChange = true;
	}

	/**
	 * Clients who call startNonUserChange() should call 
	 * finishNonUserChange() as soon as possible after the change is done.
	 * @throws IllegalArgumentException if no change is in progress.
	 */
	public void finishNonUserChange() {
		if (!nonUserChange)
			throw new IllegalStateException("we are not in a non user change");//$NON-NLS-1$
		nonUserChange = false;
	}

	/**
	 * Determine if a programmatic change is in progress.
	 * @return <code>true</code> if a programmatic change is in progress.
	 */
	public boolean isNonUserChange() {
		return nonUserChange;
	}

	/**
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	abstract public void handleEvent(Event event);

	/**
	 * Abstract method notified when a text field has been changed.
	 * @param control the given control.
	 */
	public abstract void controlChanged(Control control);

	/**
	 * Registers this helper with the given control to listen for events
	 * which indicate that a change is in progress (or done).
	 * @param control the given control.
	 */
	abstract public void startListeningTo(Control control);

	/**
	 * Registers this helper with the given control to listen for the
	 * Enter key.  When Enter is pressed, the change is considered done 
	 * (this is only appropriate for single-line Text widgets).
	 * @param control the given control.
	 */
	abstract public void startListeningForEnter(Control control);

	/**
	 * Unregisters this helper from a control previously passed to
	 * startListeningTo() and/or startListeningForEnter().
	 * @param control the given control.
	 */
	abstract public void stopListeningTo(Control control);
}