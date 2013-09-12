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
package org.parallelj.designer.extension.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.parallelj.model.Data;

/**
 * Utility class to get all the super interfaces for the selected data model and
 * populate all the data which are iterable in nature.
 * 
 */
public class IterableDataUtils {

	/**
	 * private constructor to avoid its instantiation.
	 */
	private IterableDataUtils() {
	}
	
	@SuppressWarnings("rawtypes")
	private static final Map<String, Class> builtInMap = new HashMap<String, Class>();
	static {
		builtInMap.put("int", Integer.TYPE);
		builtInMap.put("long", Long.TYPE);
		builtInMap.put("double", Double.TYPE);
		builtInMap.put("float", Float.TYPE);
		builtInMap.put("boolean", Boolean.TYPE);
		builtInMap.put("char", Character.TYPE);
		builtInMap.put("byte", Byte.TYPE);
		builtInMap.put("void", Void.TYPE);
		builtInMap.put("short", Short.TYPE);
	}

	/**
	 * Populates the data type containing its parent as Collection interface.
	 * 
	 * @param contents
	 * @param data
	 */
	public static void populateIterableData(ArrayList<EObject> contents,
			Data data) {
		if (data != null && data.getType() != null) {
			try {
				if(isIterable(data.getType())){
					contents.add(data);
				}
			} catch (ClassNotFoundException e) {
				//Activator.logError(e.getMessage());
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isIterable(String type) throws ClassNotFoundException {
		// check for generic type
		int indexOf = type.indexOf("<");
		if (indexOf > 0) {
			type = type.substring(0, indexOf);
		}

		// checking is the type permeative
		if (builtInMap.containsKey(type)) {
			return false;
		} else {
			Class classDefinition = Class.forName(type);
			return Iterable.class.isAssignableFrom(classDefinition);
		}
	}
}
