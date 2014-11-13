package org.parallelj.code.generator.transformations.structure;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;

import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;
import org.parallelj.model.Handler;

/**
 * This transformation allows to add the correct parameters on an exit method
 * for a Handler object
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class HandlerParametersCreation extends AbstractTransformation<Handler> {

	public HandlerParametersCreation(Handler eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Handler handler = getEObject();

		AbstractMethodDeclaration exitMethod = (AbstractMethodDeclaration) context
				.get(handler, "exit");
		AbstractTypeDeclaration parent = (AbstractTypeDeclaration) context.get(
				getEObject().eContainer(), "self");

		// Exception
		SingleVariableDeclaration declaration = JavaFactory.eINSTANCE
				.createSingleVariableDeclaration();
		declaration.setMethodDeclaration(exitMethod);
		declaration.setModifier(JavaFactory.eINSTANCE.createModifier());
		declaration.setVarargs(false);

		TypeAccess parameterTypeAccess = JavaFactory.eINSTANCE
				.createTypeAccess();
		Type parameterType = JavaFactory.eINSTANCE.createPrimitiveType();
		parameterType.setName("Exception");
		parameterTypeAccess.setType(parameterType);

		declaration.setType(parameterTypeAccess);
		declaration.setName("e");
		declaration.setOriginalCompilationUnit(parent
				.getOriginalCompilationUnit());
		
		// Procedure
		SingleVariableDeclaration contextDeclaration = JavaFactory.eINSTANCE.createSingleVariableDeclaration() ;
		contextDeclaration.setMethodDeclaration(exitMethod);
		contextDeclaration.setModifier(JavaFactory.eINSTANCE.createModifier());
		contextDeclaration.setVarargs(false);

		TypeAccess contextParameterTypeAccess = JavaFactory.eINSTANCE.createTypeAccess();
		Type contextParameterType = JavaFactory.eINSTANCE.createPrimitiveType();
		contextParameterType.setName("Object");
		contextParameterTypeAccess.setType(contextParameterType);

		contextDeclaration.setType(contextParameterTypeAccess);
		contextDeclaration.setName("context");
		contextDeclaration.setOriginalCompilationUnit(parent.getOriginalCompilationUnit()) ;

		context.put(handler, "exit", exitMethod);
	}

}
