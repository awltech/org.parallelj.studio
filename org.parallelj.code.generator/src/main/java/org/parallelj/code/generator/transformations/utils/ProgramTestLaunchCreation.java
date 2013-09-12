package org.parallelj.code.generator.transformations.utils;

import java.util.Arrays;
import java.util.List;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.javaxmi.core.annotations.GeneratedAnnotationAdder;
import net.atos.optimus.m2m.javaxmi.core.annotations.JavaAnnotationHelper;
import net.atos.optimus.m2m.javaxmi.core.javadoc.JavadocHelper;

import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.ExpressionStatement;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.MethodInvocation;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.SingleVariableAccess;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.VisibilityKind;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;
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

	public ProgramTestLaunchCreation(Program eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Program program = getEObject();

		List<String> nameChunks = Arrays.asList(program.getName().split("\\."));
		String className = nameChunks.get(nameChunks.size() - 1);

		AbstractMethodDeclaration declaration = JavaFactory.eINSTANCE
				.createMethodDeclaration();
		declaration.setName("testLaunch" + className);

		AbstractTypeDeclaration parent = (AbstractTypeDeclaration) context.get(
				getEObject(), "testself");
		declaration.setOriginalCompilationUnit(parent
				.getOriginalCompilationUnit());
		declaration.setAbstractTypeDeclaration(parent);

		Modifier modifier = JavaFactory.eINSTANCE.createModifier();
		modifier.setVisibility(VisibilityKind.PUBLIC);
		modifier.setOriginalCompilationUnit(parent.getOriginalCompilationUnit());
		declaration.setModifier(modifier);

		TypeAccess typeAccess = JavaFactory.eINSTANCE.createTypeAccess();
		Type type = JavaFactory.eINSTANCE.createPrimitiveType();
		type.setName("Exception");
		typeAccess.setType(type);
		declaration.getThrownExceptions().add(typeAccess);

		JavaAnnotationHelper.addAnnotation(declaration, "org.junit", "Test");

		JavadocHelper
				.addJavadoc(declaration, "Testcase to launch your program");

		GeneratedAnnotationAdder.addGenerated(declaration, "//J", true, false);

		TypeAccess launcherAccess = JavaFactory.eINSTANCE.createTypeAccess();
		Type launcherType = JavaFactory.eINSTANCE.createPrimitiveType();
		launcherType.setName("org.parallelj.launching.quartz.Launcher");
		launcherAccess.setType(launcherType);

		TypeAccess programTypeAccess = JavaFactory.eINSTANCE.createTypeAccess();
		Type programType = JavaFactory.eINSTANCE.createPrimitiveType();
		programType.setName(program.getName());
		programTypeAccess.setType(programType);

		SingleVariableAccess sva = JavaFactory.eINSTANCE
				.createSingleVariableAccess();
		sva.setQualifier(programTypeAccess);
		VariableDeclarationFragment fragment = JavaFactory.eINSTANCE
				.createVariableDeclarationFragment();
		fragment.setName("class");
		sva.setVariable(fragment);

		MethodInvocation invocation = callMethod(
				callMethod(callMethod(launcherAccess, "getLauncher"),
						"newLaunch", sva), "synchLaunch");

		ExpressionStatement statement = JavaFactory.eINSTANCE
				.createExpressionStatement();
		statement.setExpression(invocation);

		TypeAccess launcherAccess2 = JavaFactory.eINSTANCE.createTypeAccess();
		launcherAccess2.setType(launcherType);

		MethodInvocation invocation2 = callMethod(
				callMethod(launcherAccess2, "getLauncher"), "complete");
		ExpressionStatement statement2 = JavaFactory.eINSTANCE
				.createExpressionStatement();
		statement2.setExpression(invocation2);

		Block block = JavaFactory.eINSTANCE.createBlock();
		block.getStatements().add(statement);
		block.getStatements().add(statement2);

		declaration.setBody(block);

		context.put(program, "testmethod", declaration);
	}

	private static MethodInvocation callMethod(Expression caller, String name,
			Expression... params) {
		MethodInvocation invocation = JavaFactory.eINSTANCE
				.createMethodInvocation();

		invocation.setExpression(caller);
		invocation.setMethod(methodDeclaration(name, params));
		for (Expression param : params) {
			invocation.getArguments().add(param);
		}
		return invocation;
	}

	private static MethodDeclaration methodDeclaration(String name,
			Expression... params) {
		MethodDeclaration methodDeclaration = JavaFactory.eINSTANCE
				.createMethodDeclaration();
		methodDeclaration.setName(name);

		return methodDeclaration;
	}

}
