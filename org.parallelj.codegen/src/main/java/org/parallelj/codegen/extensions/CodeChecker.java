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
package org.parallelj.codegen.extensions;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.parallelj.model.Element;

public class CodeChecker {

	public final static String CALLABLE = "java.util.concurrent.Callable"; 
	public final static String RUNNABLE = "java.lang.Runnable";
	
	public static boolean isCallable(String name, Element elem) {
		return checkInterface(CALLABLE, name, elem);
	} 
	
	public static boolean isRunnable(String name, Element elem) {
		return checkInterface(RUNNABLE, name, elem);
	}

	private static boolean checkInterface(String type,String name, Element elem){
		try{
			if(name == null || "".equals(name))
				return false;
			
			IFile file = WorkspaceSynchronizer.getFile(elem.eResource());
			if (file == null || !file.exists())
				return false;
	
			IProject p = file.getProject();
			if (p == null || !p.exists())
				return false;
	
			IJavaProject project = JavaCore.create(p);
			if(project == null)
				return false;
			
			if(project.findType(name) == null)
				return false;
			
			String gtype = project.findType(name).resolveType(type)[0][1];
			if(gtype == null)
				return false;
			
			for(String i : project.findType(name).getSuperInterfaceNames()){
				if(gtype.equals(project.findType(name).resolveType(i)[0][1]))
					return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public static String getGeneric(String str){
		String val = str;
		if(str.indexOf("<") != -1 && str.indexOf(">") != -1){
			val = str.substring(str.indexOf("<")+1, str.lastIndexOf(">"));
		}
		return val;
	}
	
	public static String getCallableGeneric(String name,Element elem){
		String ret = "Object";
		try{
			if(name == null || "".equals(name))
				return ret;
			
			IFile file = WorkspaceSynchronizer.getFile(elem.eResource());
			if (file == null || !file.exists())
				return ret;
		
			IProject p = file.getProject();
			if (p == null || !p.exists())
				return ret;
		
			IJavaProject project = JavaCore.create(p);
			if(project == null)
				return ret;
				
			if(project.findType(name) == null)
				return ret;
				
			String gtype = project.findType(name).resolveType(CALLABLE)[0][1];
			if(gtype == null)
				return ret;
				
			for(String i : project.findType(name).getSuperInterfaceNames()){
				if(gtype.equals(project.findType(name).resolveType(i)[0][1])){
					ret = getGeneric(i);
					return ret;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return ret;
	}
	
	
	
}
