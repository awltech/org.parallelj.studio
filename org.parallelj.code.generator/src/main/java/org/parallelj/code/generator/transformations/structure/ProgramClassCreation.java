package org.parallelj.code.generator.transformations.structure;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.ctxinject.api.ContextElementVisibility;
import net.atos.optimus.m2m.engine.ctxinject.api.ObjectContextElement;
import net.atos.optimus.m2m.javaxmi.core.annotations.GeneratedAnnotationAdder;
import net.atos.optimus.m2m.javaxmi.operation.classes.ClassDeclarationBuilder;
import net.atos.optimus.m2m.javaxmi.operation.classes.ClassHelper;
import net.atos.optimus.m2m.javaxmi.operation.classes.JavaClass;
import net.atos.optimus.m2m.javaxmi.operation.enums.EnumHelper;
import net.atos.optimus.m2m.javaxmi.operation.enums.JavaEnum;
import net.atos.optimus.m2m.javaxmi.operation.imports.ImportDeclarationHelper;
import net.atos.optimus.m2m.javaxmi.operation.interfaces.Interface;
import net.atos.optimus.m2m.javaxmi.operation.interfaces.InterfaceHelper;
import net.atos.optimus.m2m.javaxmi.operation.packages.JavaPackage;

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
	private JavaPackage javaPackage;

	@ObjectContextElement(value = "self", visibility = ContextElementVisibility.OUT, nullable = false)
	private JavaClass javaClass;

	public ProgramClassCreation(Program eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Program program = getEObject();

		this.javaClass = ClassHelper
				.builder(this.javaPackage,
						program.getName().substring(program.getName().lastIndexOf(".") + 1, program.getName().length()))
				.build()
				.addJavadoc(
						Messages.JAVADOC_PROGRAM_CLASS.message(program.getName(),
								(getEObject().getDescription() != null ? getEObject().getDescription() : "")), true);

		ImportDeclarationHelper.createImportDeclaration("my.other.javapackage.*", false // static_state
				);

		JavaClass anotherClass = ClassHelper.builder(javaPackage, "AnotherClass").addImports("java.util.List")
				.addStaticImports("java.util.Collections.*").build();

		Interface anotherInterface = InterfaceHelper.builder(javaPackage, "AnotherInterface")
				.addImports("java.util.*", "java.lang.*").addStaticImports("java.util.Collections.*").build();

		JavaEnum anotherEnum = EnumHelper.builder(javaPackage, "AnotherEnum").addImports("java.util.*", "java.lang.*")
				.addStaticImports("java.util.Collections.*").build();
		
		String className = "MyClass<T extends Object, S extends AnotherClass>";
		
		ClassDeclarationBuilder.builder().setCompilationUnit(null).setName(className).build();

		GeneratedAnnotationAdder.addGenerated(this.javaClass.getDelegate(), "//J", false, false);
	}
}
