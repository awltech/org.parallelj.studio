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
package org.parallelj.common.jdt.test;

import java.io.Serializable;
import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * TypeCheckerTestCaseInput class body
 * 
 * @author Atos Worldline
 * 
 */
@Generated("ParallelJ")
@java.lang.annotation.Target(value = "javax.annotation.Generated")
public class TypeCheckerTestCaseInput extends ArrayList implements Serializable {

	private Map myProperty = new HashMap(5);

	private void myMethod() {
	}

	private void mySecondMethod(int i, String s) {
	}

	private final class TypeCheckerTestCaseInputInnerType {
		private void mySecondMethod(double d, String s) {
		}
	}
}

@Documented
protected abstract static final class TypeCheckerSecondTestCaseInput<E> {

}

private class TypeCheckerThirdTestCaseInput {

}
