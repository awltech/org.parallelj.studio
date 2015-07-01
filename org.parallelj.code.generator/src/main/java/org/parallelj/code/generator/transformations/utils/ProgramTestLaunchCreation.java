package org.parallelj.code.generator.transformations.utils;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.ctxinject.api.ContextElementVisibility;
import net.atos.optimus.m2m.engine.ctxinject.api.ObjectContextElement;
import net.atos.optimus.m2m.javaxmi.core.annotations.GeneratedAnnotationAdder;
import net.atos.optimus.m2m.javaxmi.operation.classes.JavaClass;
import net.atos.optimus.m2m.javaxmi.operation.instruction.CallInstructionHelper;
import net.atos.optimus.m2m.javaxmi.operation.methods.MethodHelper;

import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.parallelj.model.Program;

/**
 * This transformation allows to create the content of a testcase from a
 * parallelj program
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class ProgramTestLaunchCreation extends AbstractTransformation<Program> {

	@ObjectContextElement(value = "testself", nullable = false)
	private ClassDeclaration parent;

	@ObjectContextElement(value = "testmethod", visibility = ContextElementVisibility.OUT, nullable = false)
	private MethodDeclaration declaration;

	public ProgramTestLaunchCreation(Program eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Program program = getEObject();
		this.declaration = MethodHelper
				.builder(
						new JavaClass(this.parent),
						"testLaunch"
								+ program.getName().substring(program.getName().lastIndexOf(".") + 1,
										program.getName().length()))
				.addExceptions("Exception")
				.build()
				.addAnnotation("org.junit", "Test")
				.addJavadoc("Testcase to launch your program", true)
				.addInstructions(
						CallInstructionHelper.createCallMethodInstruction(
								CallInstructionHelper.createCallMethodInstruction(
										CallInstructionHelper.createStaticMethodCallInstruction(
												"org.parallelj.launching.Launcher", "getLauncher"), "newLaunch")
										.addVariableArgument(program.getName() + ".class"), "synchLaunch"))
				.getDelegate();

		GeneratedAnnotationAdder.addGenerated(declaration, "//J", true, false);
	}

}
