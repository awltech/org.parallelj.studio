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
import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.Generated;

@Generated("XA Designer")
public class InnerTypeTestClass {

	@Generated("XA Designer")
	final class MyInnerType1 extends ArrayList {

		@Generated("XA Designer")
		@javax.annotation.Resource
		private String myProperty1;

		private long myProperty2 = 100;

		@Generated("XA Designer")
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

		@Generated("XA Designer")
		final class MyInnerInnerType1 extends ArrayList {
		}

		class MyInnerInnerType2 extends ArrayList {
		}
	}
	
	/**
	 * MyInnerType2 documentation
	 */
	final class MyInnerType2 extends ArrayList {}
	
	/**
	 * MyInnerType2 documentation
	 */
	@Generated("XA Designer")
	final class MyInnerType4 extends ArrayList {}
	
	abstract class MyInnerType5 implements Serializable {}
	
	/**
	 * MyInnerType6 documentation
	 */
	abstract class MyInnerType6 implements Serializable {}
	
	/**
	 * MyEnum1 documentation
	 */
	@Generated("XA Designer")
	@javax.annotation.Resource
	protected enum MyEnum1 implements Serializable {
		/**
		 * MyConstant1 informations
		 */
		@Generated("XA Designer")
		@javax.annotation.Resource
		MyConstant1, MyConstant3;
		
		private double myProperty = 1.0;
		
		private void myMethod() {
			System.out.print(true);
		}
	}
}
