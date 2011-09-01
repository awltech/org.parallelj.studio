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
package org.parallelj.codegen.tests.program;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.parallelj.common.jdt.checkers.JavaCodeChecker.getMethodChecker;

import org.junit.Test;
import org.parallelj.codegen.tests.GeneratedCodeTestCase;
import org.parallelj.common.jdt.checkers.MethodChecker;

public class ProgramTest extends GeneratedCodeTestCase {

	@Override
	protected String getClassName() {
		return "Appli";
	}

	
	@Test
	public void testGeneration(){
		testStep1(getMethodChecker(forkProject.getPrj(), PACKAGE_NAME, getClassName(), "step1"));
		testStep2(getMethodChecker(forkProject.getPrj(), PACKAGE_NAME, getClassName(), "step2"));
		testStep3Entry(getMethodChecker(forkProject.getPrj(), PACKAGE_NAME, getClassName(), "step3"));
		testStep3Exit(getMethodChecker(forkProject.getPrj(), PACKAGE_NAME, getClassName(), "step3","dont.erase.RunnableProg"));
		testStep4Entry(getMethodChecker(forkProject.getPrj(), PACKAGE_NAME, getClassName(), "step4","java.lang.String"));
		testStep4Exit(getMethodChecker(forkProject.getPrj(), PACKAGE_NAME, getClassName(), "step4","org.pj.test.Appli"));
		testStep5Entry(getMethodChecker(forkProject.getPrj(), PACKAGE_NAME, getClassName(), "step5"));
		testStep5Exit(getMethodChecker(forkProject.getPrj(), PACKAGE_NAME, getClassName(), "step5","org.pj.test.Appli"));
		testPredicate(getMethodChecker(forkProject.getPrj(), PACKAGE_NAME, getClassName(), "isValid"));
	}
	
	private void testPredicate(MethodChecker mc) {
		assertNotNull(mc);
		assertTrue(mc.returnsType("boolean"));
		assertTrue(mc.isAnnotated("javax.annotation.Generated"));		
	}


	private void testStep5Exit(MethodChecker mc) {
		assertNotNull(mc);
		assertTrue(mc.returnsType("void"));
		assertTrue(mc.isAnnotated("org.parallelj.AndSplit","end"));
		assertTrue(mc.isAnnotated("javax.annotation.Generated"));		
		assertTrue(mc.containsParamInSignature("org.pj.test.Appli", "runnable"));
	}


	private void testStep5Entry(MethodChecker mc) {
		assertNotNull(mc);
		assertTrue(mc.returnsType("org.pj.test.Appli"));
		assertTrue(mc.isAnnotated("org.parallelj.AndJoin"));
		assertTrue(mc.isAnnotated("javax.annotation.Generated"));
		assertTrue(mc.isAnnotated("org.parallelj.While","valid"));	
	}


	private void testStep4Exit(MethodChecker mc) {
		assertNotNull(mc);
		assertTrue(mc.returnsType("void"));
		assertTrue(mc.isAnnotated("org.parallelj.AndSplit","end"));
		assertTrue(mc.isAnnotated("javax.annotation.Generated"));		
		assertTrue(mc.containsParamInSignature("org.pj.test.Appli", "runnable"));
	}


	private void testStep4Entry(MethodChecker mc) {
		assertNotNull(mc);
		assertTrue(mc.returnsType("org.pj.test.Appli"));
		assertTrue(mc.isAnnotated("org.parallelj.AndJoin"));
		assertTrue(mc.isAnnotated("javax.annotation.Generated"));
		assertTrue(mc.isAnnotated("org.parallelj.Capacity"));
	}


	private void testStep3Exit(MethodChecker mc) {
		assertNotNull(mc);
		assertTrue(mc.returnsType("void"));
		assertTrue(mc.isAnnotated("org.parallelj.AndSplit","step4"));
		assertTrue(mc.isAnnotated("javax.annotation.Generated"));		
		assertTrue(mc.containsParamInSignature("dont.erase.RunnableProg", "runnable"));
	}


	private void testStep3Entry(MethodChecker mc) {
		assertNotNull(mc);
		assertTrue(mc.returnsType("dont.erase.RunnableProg"));
		assertTrue(mc.isAnnotated("org.parallelj.AndJoin"));
		assertTrue(mc.isAnnotated("javax.annotation.Generated"));		
	}


	private void testStep2(MethodChecker mc) {
		assertNotNull(mc);
		assertTrue(mc.returnsType("void"));
		assertTrue(mc.isAnnotated("org.parallelj.AndJoin"));
		assertTrue(mc.isAnnotated("org.parallelj.AndSplit","step5"));
		assertTrue(mc.isAnnotated("javax.annotation.Generated"));		
		assertTrue(mc.containsParamInSignature("java.util.concurrent.Callable", "callable"));
		//TODO Change in next meta model version 
		assertTrue(mc.containsParamInSignature("java.lang.Object", "result"));
	}


	private void testStep1(MethodChecker mc){
		assertNotNull(mc);
		assertTrue(mc.returnsType("void"));
		assertTrue(mc.isAnnotated("org.parallelj.Begin"));
		assertTrue(mc.isAnnotated("org.parallelj.AndSplit"));
		assertTrue(mc.isAnnotated("javax.annotation.Generated"));
		
	}

		
}
