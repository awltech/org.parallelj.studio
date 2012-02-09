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
package org.parallelj.designer.typeselector.processor.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.parallelj.model.Block;
import org.parallelj.model.Element;
import org.parallelj.model.ParallelJPackage;
import org.parallelj.model.Program;
import org.parallelj.model.Specification;
import org.parallelj.model.impl.BlockImpl;
import org.parallelj.model.impl.ForEachLoopImpl;
import org.parallelj.model.impl.WhileLoopImpl;

/**
 * Class to extracts all Parallel J Model Types from Resource
 * 
 */
public class ModelResourceProcessor {

	/**
	 * Collection of Valid EClasses. These EClasses are defining which Model
	 * Objects are valid as Model Types.
	 */
	protected static Collection<EClass> validEClasses = null;

	/**
	 * Extracts ModelTypesInfos from Resource.
	 * 
	 * @param resource
	 *            : Model Resource
	 * @param selectedObject
	 * @return collection of ModelTypesInfos
	 */
	public Collection<ModelTypeInfo> lookForTypesInResource(Resource resource,
			EObject selectedObject) {
		if (ModelResourceProcessor.validEClasses == null)
			ModelResourceProcessor.instanciateValidEClasses();

		Collection<ModelTypeInfo> typeInfos = new HashSet<ModelTypeInfo>();

		for (EObject eObject : resource.getContents()) {
			if (eObject instanceof Specification) {
				Specification specification = (Specification) eObject;
				EList<Program> programs = specification.getPrograms();

				for (Program program : programs) {

					if (program.getName() != null) {
						String typePatternAsString = program.getName();
						String packagePatternAsString = "";
						if (typePatternAsString.contains(".")) {
							packagePatternAsString = typePatternAsString
									.substring(0, typePatternAsString
											.lastIndexOf("."));
							typePatternAsString = typePatternAsString
									.substring(typePatternAsString
											.lastIndexOf(".") + 1);
						}

						ModelTypeInfo programType = new ModelTypeInfo(
								typePatternAsString, packagePatternAsString,
								ModelType.create(program.eClass()));

						if (!typeInfos.contains(programType.getElementName())) {
							typeInfos.add(programType);
						}
					}
				}
				break;
			}
		}

		Program program = selectedObject.eContainer() instanceof Program ? (Program) selectedObject
				.eContainer() : null;
		if (program != null) {
			for (Element e : program.getElements()) {
				if (e instanceof BlockImpl
						&& !isAlreadyUsed((BlockImpl) e, selectedObject)) {
					Block block = (BlockImpl) e;
					String typePatternAsString = block.getName();
					String packagePatternAsString = "";
					if (typePatternAsString.contains(".")) {
						packagePatternAsString = typePatternAsString.substring(
								0, typePatternAsString.lastIndexOf("."));
						typePatternAsString = typePatternAsString
								.substring(typePatternAsString.lastIndexOf(".") + 1);
					}

					ModelTypeInfo blockType = new ModelTypeInfo(
							typePatternAsString, packagePatternAsString,
							ModelType.create(block.eClass()));

					if (!typeInfos.contains(blockType.getElementName())) {
						typeInfos.add(blockType);
					}
				}
			}
		}
		return typeInfos;
	}

	protected synchronized static void instanciateValidEClasses() {

		if (ModelResourceProcessor.validEClasses == null) {
			ModelResourceProcessor.validEClasses = new ArrayList<EClass>();
			ModelResourceProcessor.validEClasses.add(ParallelJPackage.eINSTANCE
					.getProgram());
			ModelResourceProcessor.validEClasses.add(ParallelJPackage.eINSTANCE
					.getBlock());
		}

	}

	private boolean isAlreadyUsed(Block p, EObject selectedObject) {
		if (selectedObject instanceof ForEachLoopImpl) {
			if (((ForEachLoopImpl) selectedObject).getExecutable() == null) {
				return false;
			} else {
				return ((ForEachLoopImpl) selectedObject).getExecutable()
						.equals(p.getName());
			}
		} else if (selectedObject instanceof WhileLoopImpl) {
			if (((WhileLoopImpl) selectedObject).getExecutable() == null) {
				return false;
			} else {
				return ((WhileLoopImpl) selectedObject).getExecutable().equals(
						p.getName());
			}
		} else {
			return true;
		}
	}
}
