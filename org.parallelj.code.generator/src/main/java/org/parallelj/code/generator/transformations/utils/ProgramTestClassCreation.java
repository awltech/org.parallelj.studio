package org.parallelj.code.generator.transformations.utils;

import java.util.Arrays;
import java.util.Iterator;
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
 * This transformation allows to create a test class from a parallelj program
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class ProgramTestClassCreation extends AbstractTransformation<Program> {

	public ProgramTestClassCreation(Program eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Model javaModel = (Model) context.getRoot("test");

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

		context.put(program, "testpackage", currentPackage);

		List<String> nameChunks = Arrays.asList(program.getName().split("\\."));
		String className = nameChunks.get(nameChunks.size() - 1);

		ClassDeclaration classDeclaration = JavaFactory.eINSTANCE
				.createClassDeclaration();
		classDeclaration.setPackage(currentPackage);
		classDeclaration.setName(className + "Test");
		classDeclaration.setProxy(false);

		Modifier modifier = JavaFactory.eINSTANCE.createModifier();
		modifier.setVisibility(VisibilityKind.PUBLIC);
		classDeclaration.setModifier(modifier);

		CompilationUnit compilationUnit = JavaFactory.eINSTANCE
				.createCompilationUnit();
		compilationUnit.setName(className + "Test" + ".java");
		compilationUnit.setPackage(currentPackage);

		javaModel.getCompilationUnits().add(compilationUnit);

		classDeclaration.setOriginalCompilationUnit(compilationUnit);
		compilationUnit.getTypes().add(classDeclaration);

		JavadocHelper.addJavadoc(classDeclaration,
				Messages.JAVADOC_PROGRAM_CLASS.message(program.getName(),
						(getEObject().getDescription() != null ? getEObject()
								.getDescription() : "")));

		GeneratedAnnotationAdder.addGenerated(classDeclaration, "//J", false,
				false);

		context.put(program, "testself", classDeclaration);
	}

}
