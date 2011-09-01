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
package org.parallelj.common.jdt.mergers.output;

import java.io.IOException;
import java.util.List;

import javax.annotation.Generated;

public abstract class MethodTestClass {

	/**
	 * myMethod0 javadoc
	 */
	@Generated(value = "XA", comments = "-1263875845")
	@javax.annotation.Resource
	public void myMethod0() {
		System.out.println("myMethod0 body");
	}

	/**
	 * myMethod javadoc
	 * 
	 * @generated XA
	 */
	@Generated("XA Designer")
	public void myMethod1() throws IllegalArgumentException {
		System.out.println("myMethod1 body");
	}

	@Generated("XA Designer")
	public void myMethod2() {
	}

	@Generated("XA Designer")
	public void myMethod3() {
	}

	protected void myMethod6() {
	}

	/**
	 * myMethod7 javadoc
	 */
	protected void myMethod7() {
		System.out.println("IN myMethod7");
	}

	@Generated(value = "XA", comments = "-1263875845")
	protected void myMethod8() {
		System.out.println("IN myMethod8");
	}

	@Generated("XA Designer")
	private void myMethod8(String s, int i) {
	}
	
	@Generated("XA Designer")
	private void myMethod8(java.util.List<org.parallelj.common.jdt.mergers.output.MethodTestClass> param) {
	}

	@Generated("XA Designer")
	private void myMethod8(org.parallelj.common.jdt.mergers.output.MethodTestClass param) {
	}
	
	@Generated("XA Designer")
	public abstract void myMethod9(String s, int i);

	@Generated("XA Designer")
	protected String myMethod11(String s1, double d1) {
		return null;
	}

	@Generated("XA Designer")
	protected String myMethod12(String s1, int d1) {
		return null;
	}

	@Generated("XA Designer")
	public void myMethod13(List values) {
	}

	@Generated("XA Designer")
	public void myMethod14(List<java.lang.String> values) {
	}

	@Generated(value = "XA", comments = "0")
	public abstract void myMethod15(List values) throws IOException;

	@Generated("XA Designer")
	public static void myMethod16(List values) {
		System.out.println("In myMethod16");
	}
	
	@Generated(value = "XA", comments = "200411248")
	public static void myMethod17(Float inputValue) {
		System.out.println("This is myMethod17");
	}
	
	@Generated("XA Designer")
	public static void myMethod18(@javax.annotation.Generated("XA") List values) {
	}
	
	@Generated("XA Designer")
	public void myMethod19(final List values) {
	}

}
