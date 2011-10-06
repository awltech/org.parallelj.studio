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
package org.parallelj.ixea.tools;

import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Control;

/**
 * This builder is able to create, populate and link a form data to a SWT
 * Control object.
 * 
 * @author mvanbesien
 * 
 */
public class FormDataBuilder {

	private FormData data;

	private int defaultOffset;

	public FormDataBuilder offset(int offset) {
		this.defaultOffset = offset;
		return this;
	}

	public FormDataBuilder() {
		this.data = new FormData();
		this.defaultOffset = 5;
	}

	public FormDataBuilder(int offset) {
		this.data = new FormData();
		this.defaultOffset = offset;
	}

	public FormDataBuilder left(int numerator, int offset) {
		data.left = new FormAttachment(numerator, offset);
		return this;
	}

	public FormDataBuilder left(Control control, int offset) {
		data.left = new FormAttachment(control, offset);
		return this;
	}

	public FormDataBuilder left(int numerator) {
		return this.left(numerator, this.defaultOffset);
	}

	public FormDataBuilder left(Control control) {
		return this.left(control, this.defaultOffset);
	}

	public FormDataBuilder left() {
		return this.left(0, this.defaultOffset);
	}

	public FormDataBuilder right(int numerator, int offset) {
		data.right = new FormAttachment(numerator, offset);
		return this;
	}

	public FormDataBuilder right(Control control, int offset) {
		data.right = new FormAttachment(control, offset);
		return this;
	}

	public FormDataBuilder right(int numerator) {
		return this.right(numerator, -this.defaultOffset);
	}

	public FormDataBuilder right(Control control) {
		return this.right(control, -this.defaultOffset);
	}

	public FormDataBuilder right() {
		return this.right(100, -this.defaultOffset);
	}

	public FormDataBuilder top(int numerator, int offset) {
		data.top = new FormAttachment(numerator, offset);
		return this;
	}

	public FormDataBuilder top(Control control, int offset) {
		data.top = new FormAttachment(control, offset);
		return this;
	}

	public FormDataBuilder top(int numerator) {
		return this.top(numerator, this.defaultOffset);
	}

	public FormDataBuilder top(Control control) {
		return this.top(control, this.defaultOffset);
	}

	public FormDataBuilder top() {
		return this.top(0, this.defaultOffset);
	}

	public FormDataBuilder bottom(int numerator, int offset) {
		data.bottom = new FormAttachment(numerator, offset);
		return this;
	}

	public FormDataBuilder bottom(Control control, int offset) {
		data.bottom = new FormAttachment(control, offset);
		return this;
	}

	public FormDataBuilder bottom(int numerator) {
		return this.bottom(numerator, -this.defaultOffset);
	}

	public FormDataBuilder bottom(Control control) {
		return this.bottom(control, -this.defaultOffset);
	}

	public FormDataBuilder bottom() {
		return this.bottom(100, -this.defaultOffset);
	}

	public FormDataBuilder width(int width) {
		data.width = width;
		return this;
	}

	public FormDataBuilder height(int height) {
		data.height = height;
		return this;
	}

	public FormDataBuilder shortLabel() {
		data.width = 80;
		return this;
	}

	public FormDataBuilder mediumLabel() {
		data.width = 120;
		return this;
	}

	public FormDataBuilder longLabel() {
		data.width = 180;
		return this;
	}

	public FormDataBuilder standardButton() {
		data.width = 90;
		data.height = 25;
		return this;
	}

	public FormDataBuilder shortButton() {
		data.width = 90;
		data.height = 20;
		return this;
	}

	public void apply(Control control) {
		control.setLayoutData(this.data);
	}

}
