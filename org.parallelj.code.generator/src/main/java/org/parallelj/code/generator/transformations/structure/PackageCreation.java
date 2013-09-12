package org.parallelj.code.generator.transformations.structure;

import java.util.Arrays;
import java.util.Iterator;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;

import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;
import org.parallelj.model.Program;

/**
 * This transformation allows to create a java package from the name provided in
 * the program
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class PackageCreation extends AbstractTransformation<Program> {

	public PackageCreation(Program eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Model javaModel = (Model) context.getRoot("java");

		Program program = getEObject();

		String packageName = program.getName().substring(0,
				program.getName().lastIndexOf("."));

		Package currentPackage = null;

		Iterator<String> chunks = Arrays.asList(packageName.split("\\."))
				.iterator();

		if (chunks.hasNext()) {
			currentPackage = JavaFactory.eINSTANCE.createPackage();
			currentPackage.setName(chunks.next());
			currentPackage.setModel(javaModel);
		}
		while (chunks.hasNext()) {
			Package oldPackage = currentPackage;
			currentPackage = JavaFactory.eINSTANCE.createPackage();
			currentPackage.setName(chunks.next());
			currentPackage.setPackage(oldPackage);
		}

		context.put(program, "package", currentPackage);
	}

}
