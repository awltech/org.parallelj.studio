package org.parallelj.code.generator.transformations.structure;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.ctxinject.api.ContextElementVisibility;
import net.atos.optimus.m2m.engine.ctxinject.api.ObjectContextElement;
import net.atos.optimus.m2m.javaxmi.core.annotations.GeneratedAnnotationAdder;
import net.atos.optimus.m2m.javaxmi.operation.classes.ClassHelper;
import net.atos.optimus.m2m.javaxmi.operation.packages.JavaPackage;

import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.Package;
import org.parallelj.code.generator.core.Messages;
import org.parallelj.model.Program;

/**
 * This transformation allows to create a class from a parallelj program
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class ProgramClassCreation extends AbstractTransformation<Program> {

	@ObjectContextElement(value = "package", nullable = false)
	private Package javaPackage;

	@ObjectContextElement(value = "self", visibility = ContextElementVisibility.OUT, nullable = false)
	private ClassDeclaration classDeclaration;

	public ProgramClassCreation(Program eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Program program = getEObject();

		this.classDeclaration = ClassHelper
				.builder(new JavaPackage(this.javaPackage),
						program.getName().substring(program.getName().lastIndexOf(".") + 1, program.getName().length()))
				.build()
				.addJavadoc(
						Messages.JAVADOC_PROGRAM_CLASS.message(program.getName(),
								(getEObject().getDescription() != null ? getEObject().getDescription() : "")), true)
				.getDelegate();

		GeneratedAnnotationAdder.addGenerated(this.classDeclaration, "//J", false, false);
	}
}
