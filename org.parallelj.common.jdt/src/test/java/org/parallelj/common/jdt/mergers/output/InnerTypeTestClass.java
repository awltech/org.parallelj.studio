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

import java.io.Serializable;
import java.util.Vector;

import javax.annotation.Generated;

@Generated("XA Designer")
public class InnerTypeTestClass {

	/**
	 * MyInnerType1 documentation
	 * 
	 * @generated XA
	 */
	@Generated("XA Designer")
	@javax.annotation.Resource
	abstract class MyInnerType1 implements Serializable {

		/**
		 * MyProperty1 description
		 */
		@javax.annotation.Generated("XA Designer")
		protected int myProperty1 = 10;

		@javax.annotation.Generated("XA Designer")
		protected long myProperty2;

		/**
		 * myProperty3 field
		 */
		@javax.annotation.Generated("XA Designer")
		private int myProperty3;

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

		@Generated("XA Designer")
		class MyInnerInnerType1 extends Vector implements Serializable {
		}

		class MyInnerInnerType2 extends Vector {
		}

		@Generated("XA Designer")
		class MyInnerInnerType3 {
		}
	}

	@Generated("XA Designer")
	@javax.annotation.Resource
	class MyInnerType2 implements Serializable {
	}

	/**
	 * MyInnerType6 documentation
	 */
	@Generated("XA Designer")
	final class MyInnerType3 {
	}

	class MyInnerType6 implements Cloneable {
	}

	/**
	 * MyInnerType7 documentation
	 */
	class MyInnerType7 implements Cloneable {
	}

	@Generated("XA Designer")
	public enum MyEnum1 implements Cloneable {

		@Generated("XA Designer")
		MyConstant1, @Generated("XA Designer")
		MyConstant2;
	}
}
