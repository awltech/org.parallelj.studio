package org.parallelj.code.generator.transformations.structure;

import java.util.concurrent.Callable;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.parallelj.code.generator.Activator;
import org.parallelj.model.Procedure;

/**
 * This transformation allows to create an exit method for a procedure in a
 * parallelj program
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class ProcedureExitMethodCreation extends
		AbstractTransformation<Procedure> {

	public ProcedureExitMethodCreation(Procedure eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Procedure procedure = getEObject();

		AbstractMethodDeclaration exitMethod = (AbstractMethodDeclaration) context
				.get(procedure, "exit");
		AbstractTypeDeclaration parent = (AbstractTypeDeclaration) context.get(
				getEObject().eContainer(), "self");
		
		exitMethod.getParameters().clear();

		SingleVariableDeclaration declaration = JavaFactory.eINSTANCE
				.createSingleVariableDeclaration();
		declaration.setMethodDeclaration(exitMethod);
		declaration.setModifier(JavaFactory.eINSTANCE.createModifier());
		declaration.setVarargs(false);

		TypeAccess parameterTypeAccess = JavaFactory.eINSTANCE
				.createTypeAccess();
		Type parameterType = JavaFactory.eINSTANCE.createPrimitiveType();
		parameterType.setName(procedure.getExecutable());
		parameterTypeAccess.setType(parameterType);

		declaration.setType(parameterTypeAccess);
		declaration.setName("executable");
		declaration.setOriginalCompilationUnit(parent
				.getOriginalCompilationUnit());

		// Get the Type of the executable and search if it is a Callable.
		String executable = procedure.getExecutable();
		IFile file = WorkspaceSynchronizer.getFile(procedure.eResource());
		IProject project = file.getProject();
		IJavaProject javaProject = JavaCore.create(project);
		try {
			IType type = javaProject.findType(executable);
			if (type!=null) {
				ITypeHierarchy typehierarchy = type.newSupertypeHierarchy(null);
				IType allInterfaces[] = typehierarchy.getAllInterfaces();
				for (IType iType : allInterfaces) {
					if (iType.getFullyQualifiedName().equals(Callable.class.getName())) {
						// The executable is a Callable, so we have to add the return value of the executable as a parameter of the exit method.
						SingleVariableDeclaration callableDeclaration = JavaFactory.eINSTANCE.createSingleVariableDeclaration() ;
						callableDeclaration.setMethodDeclaration(exitMethod);
						callableDeclaration.setModifier(JavaFactory.eINSTANCE.createModifier());
						callableDeclaration.setVarargs(false);
	
						TypeAccess callableParameterTypeAccess = JavaFactory.eINSTANCE.createTypeAccess();
						Type callableParameterType = JavaFactory.eINSTANCE.createPrimitiveType();
						callableParameterType.setName("Object");
						callableParameterTypeAccess.setType(callableParameterType);
	
						callableDeclaration.setType(callableParameterTypeAccess);
						callableDeclaration.setName("value");
						callableDeclaration.setOriginalCompilationUnit(parent.getOriginalCompilationUnit()) ;
					}
				}
			}
		} catch (JavaModelException e) {
			Activator.sendErrorToErrorLog("An Exception occurred during generation", e);
		}
		
		context.put(procedure, "exit", exitMethod);
	}

}
