package org.parallelj.code.generator.transformations.structure;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;

import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;
import org.parallelj.code.generator.helpers.StringFormatHelper;
import org.parallelj.model.Pipeline;

/**
 * This transformation allows to create an exit method for a pipeline in a
 * parallelj program
 * 
 * @author a169104
 * @version 1.0
 * 
 */
public class PipelineExitMethodCreation extends
		AbstractTransformation<Pipeline> {

	public PipelineExitMethodCreation(Pipeline eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Pipeline pipeline = getEObject();

		AbstractMethodDeclaration exitMethod = (AbstractMethodDeclaration) context
				.get(pipeline, "exit");
		AbstractTypeDeclaration parent = (AbstractTypeDeclaration) context.get(
				getEObject().eContainer(), "self");

		SingleVariableDeclaration declaration = JavaFactory.eINSTANCE
				.createSingleVariableDeclaration();
		declaration.setMethodDeclaration(exitMethod);
		declaration.setModifier(JavaFactory.eINSTANCE.createModifier());
		declaration.setVarargs(false);

		TypeAccess parameterTypeAccess = JavaFactory.eINSTANCE
				.createTypeAccess();
		Type parameterType = JavaFactory.eINSTANCE.createPrimitiveType();
		parameterType.setName(StringFormatHelper.camelCase(pipeline.getName() + "Class",true));
		parameterTypeAccess.setType(parameterType);

		declaration.setType(parameterTypeAccess);
		declaration.setName("executable");
		declaration.setOriginalCompilationUnit(parent
				.getOriginalCompilationUnit());

		context.put(pipeline, "exit", exitMethod);
	}
}
