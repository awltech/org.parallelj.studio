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
package org.parallelj.codegen.constants;

import org.eclipse.osgi.util.NLS;

public class CodeGeneratorMessages {

	private static final String BUNDLE_NAME = "CodeGeneratorMessages";

	static {
		NLS.initializeMessages(BUNDLE_NAME, CodeGeneratorMessages.class);
	}

	private CodeGeneratorMessages() {
	}

	public static String CODEGEN_JOBNAME;
	public static String CODEGEN_VALIDATION_ERROR;

	public static String ABST_CODEGEN_NOELEMENT;
	public static String ABST_CODEGEN_VALIDATING;
	public static String ABST_CODEGEN_GENERATING;
	public static String ABST_CODEGEN_REFRESHING_PROJECT;
	public static String ABST_CODEGEN_REFRESHING_PROJECT_ERROR;
	public static String ABST_CODEGEN_GENERATING_SUB;

}
