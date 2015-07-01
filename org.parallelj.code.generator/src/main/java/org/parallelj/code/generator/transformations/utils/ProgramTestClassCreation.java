package org.parallelj.code.generator.transformations.utils;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.ctxinject.api.ContextElementVisibility;
import net.atos.optimus.m2m.engine.ctxinject.api.ObjectContextElement;
import net.atos.optimus.m2m.engine.ctxinject.api.RootContextElement;
import net.atos.optimus.m2m.javaxmi.core.annotations.GeneratedAnnotationAdder;
import net.atos.optimus.m2m.javaxmi.operation.classes.ClassHelper;
import net.atos.optimus.m2m.javaxmi.operation.packages.JavaPackage;
import net.atos.optimus.m2m.javaxmi.operation.packages.PackageHelper;

import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.Package;
import org.parallelj.code.generator.core.Messages;
import org.parallelj.model.Program;

/**
 * This transformation allows to create a test class from a parallelj program
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class ProgramTestClassCreation extends AbstractTransformation<Program> {

	@RootContextElement(value = "test", nullable = false)
	private Model javaModel;

	@ObjectContextElement(value = "testpackage", visibility = ContextElementVisibility.OUT, nullable = false)
	private Package currentPackage;

	@ObjectContextElement(value = "testself", visibility = ContextElementVisibility.OUT, nullable = false)
	private ClassDeclaration classDeclaration;

	public ProgramTestClassCreation(Program eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Program program = getEObject();

		JavaPackage javaPackage = PackageHelper.createPackage(this.javaModel,
				program.getName().substring(0, program.getName().lastIndexOf(".")));
		this.currentPackage = javaPackage.getDelegate();

		this.classDeclaration = ClassHelper
				.builder(
						javaPackage,
						program.getName().substring(program.getName().lastIndexOf(".") + 1, program.getName().length())
								+ "Test")
				.build()
				.addJavadoc(
						Messages.JAVADOC_PROGRAM_CLASS.message(program.getName(),
								(getEObject().getDescription() != null ? getEObject().getDescription() : "")), true)
				.getDelegate();

		GeneratedAnnotationAdder.addGenerated(classDeclaration, "//J", false, false);
	}
}
