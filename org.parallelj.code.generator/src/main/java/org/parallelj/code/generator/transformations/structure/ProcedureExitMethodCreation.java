package org.parallelj.code.generator.transformations.structure;

import java.util.concurrent.Callable;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.ctxinject.api.ContextElementVisibility;
import net.atos.optimus.m2m.engine.ctxinject.api.ObjectContextElement;
import net.atos.optimus.m2m.engine.ctxinject.api.ParentContextElement;
import net.atos.optimus.m2m.javaxmi.operation.methods.Method;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
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
public class ProcedureExitMethodCreation extends AbstractTransformation<Procedure> {

	@ParentContextElement(value = "self", nullable = false)
	private ClassDeclaration parent;

	@ObjectContextElement(value = "exit", visibility = ContextElementVisibility.INOUT, nullable = false)
	private MethodDeclaration exitMethod;

	public ProcedureExitMethodCreation(Procedure eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Procedure procedure = getEObject();
		Method method = new Method(this.exitMethod).removeParameters().addParameter(procedure.getExecutable(),
				"executable");

		// Get the Type of the executable and search if it is a Callable.
		String executable = procedure.getExecutable();
		IFile file = WorkspaceSynchronizer.getFile(procedure.eResource());
		IProject project = file.getProject();
		IJavaProject javaProject = JavaCore.create(project);
		try {
			IType type = javaProject.findType(executable);
			if (type != null) {
				ITypeHierarchy typehierarchy = type.newSupertypeHierarchy(null);
				IType allInterfaces[] = typehierarchy.getAllInterfaces();
				for (IType iType : allInterfaces) {
					if (iType.getFullyQualifiedName().equals(Callable.class.getName())) {
						// The executable is a Callable, so we have to add the
						// return value of the executable as a parameter of the
						// exit method.
						method.addParameter("Object", "value");
					}
				}
			}
		} catch (JavaModelException e) {
			Activator.sendErrorToErrorLog("An Exception occurred during generation", e);
		}
		this.exitMethod = method.getDelegate();
	}

}
