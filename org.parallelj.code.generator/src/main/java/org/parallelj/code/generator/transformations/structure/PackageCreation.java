package org.parallelj.code.generator.transformations.structure;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.ctxinject.api.ContextElementVisibility;
import net.atos.optimus.m2m.engine.ctxinject.api.ObjectContextElement;
import net.atos.optimus.m2m.engine.ctxinject.api.RootContextElement;
import net.atos.optimus.m2m.javaxmi.operation.packages.JavaPackage;
import net.atos.optimus.m2m.javaxmi.operation.packages.PackageHelper;

import org.eclipse.gmt.modisco.java.Model;
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
	
	@RootContextElement(value = "java", nullable = false)
	private Model javaModel;

	@ObjectContextElement(value = "package", visibility = ContextElementVisibility.OUT, nullable = false)
	private JavaPackage currentPackage;

	public PackageCreation(Program eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Program program = getEObject();
		this.currentPackage = PackageHelper.createPackage(this.javaModel,
				program.getName().substring(0, program.getName().lastIndexOf(".")));
	}
}
