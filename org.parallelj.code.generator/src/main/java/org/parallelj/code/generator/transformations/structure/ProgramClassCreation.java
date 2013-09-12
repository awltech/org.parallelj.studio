package org.parallelj.code.generator.transformations.structure;

import java.util.Arrays;
import java.util.List;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.javaxmi.core.annotations.GeneratedAnnotationAdder;
import net.atos.optimus.m2m.javaxmi.core.javadoc.JavadocHelper;

import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.VisibilityKind;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;
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

	public ProgramClassCreation(Program eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Program program = getEObject();

		Package javaPackage = (Package) context.get(program, "package");

		List<String> chunks = Arrays.asList(program.getName().split("\\."));
		String className = chunks.get(chunks.size() - 1);

		ClassDeclaration classDeclaration = JavaFactory.eINSTANCE
				.createClassDeclaration();
		classDeclaration.setPackage(javaPackage);
		classDeclaration.setName(className);
		classDeclaration.setProxy(false);

		Modifier modifier = JavaFactory.eINSTANCE.createModifier();
		modifier.setVisibility(VisibilityKind.PUBLIC);
		classDeclaration.setModifier(modifier);

		CompilationUnit compilationUnit = JavaFactory.eINSTANCE
				.createCompilationUnit();
		compilationUnit.setName(className + ".java");
		compilationUnit.setPackage(javaPackage);

		Model javaModel = (Model) context.getRoot("java");
		javaModel.getCompilationUnits().add(compilationUnit);

		classDeclaration.setOriginalCompilationUnit(compilationUnit);
		compilationUnit.getTypes().add(classDeclaration);

		JavadocHelper.addJavadoc(classDeclaration,
				Messages.JAVADOC_PROGRAM_CLASS.message(program.getName(),
						(getEObject().getDescription() != null ? getEObject()
								.getDescription() : "")));

		GeneratedAnnotationAdder.addGenerated(classDeclaration, "//J", false,
				false);

		context.put(program, "self", classDeclaration);
	}

}
