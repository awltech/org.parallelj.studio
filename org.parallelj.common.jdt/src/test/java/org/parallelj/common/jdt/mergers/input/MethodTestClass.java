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
package org.parallelj.common.jdt.mergers.input;

import java.io.IOException;
import java.util.List;

import javax.annotation.Generated;

import org.parallelj.common.jdt.mergers.ToBeRenamed;

public abstract class MethodTestClass {

	/**
	 * myMethod0 javadoc
	 */
	@Generated(value = "XA", comments = "2114679885")
	public void myMethod0() throws IllegalArgumentException {
		int inc = 0;
	}

	@Generated(value = "XA", comments = "-1111")
	@javax.annotation.Resource
	protected final int myMethod1() throws IOException {
		return 0;
	}

	/**
	 * myMethod2 javadoc
	 */
	protected void myMethod2() {
		System.out.println("myMethod2 body");
	}

	@Generated(value = "XA", comments = "-1111")
	public void myMethod4() {
	}

	/**
	 * myMethod5 javadoc
	 */
	public void myMethod5() {
		System.out.println("IN myMethod5");
	}

	/**
	 * myMethod6 javadoc
	 */
	public void myMethod6() {
		System.out.println("IN myMethod6");
	}

	@Generated("XA Designer")
	public final void myMethod8() {
	}

	public final void myMethod8(String s, int i) {
		System.out.println("IN myMethod8(String,int)");
	}
	
	
	/**
	 * @param param
	 */
	private void myMethod8(java.util.List<org.parallelj.common.jdt.mergers.output.MethodTestClass> param) {
	}

	
	/**
	 * @param param
	 */
	private void myMethod8(org.parallelj.common.jdt.mergers.output.MethodTestClass param) {
	}

	/**
	 * MyMethod9 documentation
	 * 
	 * @param s
	 * @param i
	 */
	@Generated("XA Designer")
	public abstract void myMethod9(String s, int i) throws IllegalAccessError;

	/**
	 * MyMethod documentation
	 */
	@ToBeRenamed(methodName = "myMethod11")
	public final String myMethod10(String s1, double d1) {
		System.out.println("IN myMethod10");
		return "";
	}

	/**
	 * MyMethod12 documentation
	 */
	@javax.annotation.Resource
	@ToBeRenamed(methodName = "myMethod12", params = { "java.lang.String", "int" })
	public final String myMethod12(String s1, double d1) {
		System.out.println("IN myMethod12");
		return "";
	}

	@Generated("XA Designer")
	@javax.annotation.Resource
	public void myMethod13(List<String> values) {
	}

	@Generated("XA Designer")
	public static void myMethod14(List values) {
	}

	@Generated(value = "XA", comments = "0")
	protected abstract void myMethod15(List values);
	
	@Generated(value = "XA", comments = "2114679885")
	public static void myMethod16(List values) {
		int inc = 0;
	}
	
	@Generated(value = "XA", comments = "200411248")
	public static void myMethod17(float inputValue) {
		System.out.println("This is myMethod17");
	}
	
	@Generated("XA Designer")
	public static void myMethod18(List values) {
	}
	
	@Generated("XA Designer")
	public void myMethod19(final List values) {
	}
}
