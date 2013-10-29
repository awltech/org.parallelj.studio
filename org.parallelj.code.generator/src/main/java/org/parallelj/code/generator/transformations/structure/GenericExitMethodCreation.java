package org.parallelj.code.generator.transformations.structure;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.javaxmi.core.annotations.GeneratedAnnotationAdder;
import net.atos.optimus.m2m.javaxmi.core.javadoc.JavadocHelper;

import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.LineComment;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.ReturnStatement;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.VisibilityKind;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;
import org.parallelj.code.generator.core.Messages;
import org.parallelj.model.Procedure;

/**
 * This transformation allows to create an exit method in a parallelj program
 * 
 * @author Antoine Neveux & a169104
 * @version 1.0
 * 
 */
public class GenericExitMethodCreation extends
		AbstractTransformation<Procedure> {

	// if procedure is from pipeline
	private boolean isPipelineProcedure;

	public GenericExitMethodCreation(Procedure eObject, String id,
			boolean isForPipeline) {
		super(eObject, id);
		this.isPipelineProcedure = isForPipeline;
	}

	@Override
	protected void transform(ITransformationContext context) {
		Procedure procedure = getEObject();

		AbstractMethodDeclaration exitMethod = JavaFactory.eINSTANCE
				.createMethodDeclaration();
		exitMethod.setName(procedure.getName());

		AbstractTypeDeclaration parent = (AbstractTypeDeclaration) context.get(
				getEObject().eContainer(), "self");
		exitMethod.setOriginalCompilationUnit(parent
				.getOriginalCompilationUnit());
		exitMethod.setAbstractTypeDeclaration(parent);

		Modifier modifier = JavaFactory.eINSTANCE.createModifier();
		modifier.setVisibility(VisibilityKind.PUBLIC);
		modifier.setOriginalCompilationUnit(parent.getOriginalCompilationUnit());
		exitMethod.setModifier(modifier);

		// if it is pipeline procedure, then it will have one parameter as
		// object in method
		if (isPipelineProcedure) {
			SingleVariableDeclaration declaration = JavaFactory.eINSTANCE
					.createSingleVariableDeclaration();
			declaration.setMethodDeclaration(exitMethod);
			declaration.setModifier(JavaFactory.eINSTANCE.createModifier());
			declaration.setVarargs(false);

			TypeAccess parameterTypeAccess = JavaFactory.eINSTANCE
					.createTypeAccess();
			Type parameterType = JavaFactory.eINSTANCE.createPrimitiveType();
			parameterType.setName("Object");
			parameterTypeAccess.setType(parameterType);

			declaration.setType(parameterTypeAccess);
			declaration.setName("next");
			declaration.setOriginalCompilationUnit(parent
					.getOriginalCompilationUnit());
		}

		LineComment lineComment = JavaFactory.eINSTANCE.createLineComment();
		lineComment.setContent(Messages.COMMENT_TODO.message());
		lineComment.setPrefixOfParent(true);

		ReturnStatement returnStatement = JavaFactory.eINSTANCE
				.createReturnStatement();
		returnStatement.getComments().add(lineComment);

		Block block = JavaFactory.eINSTANCE.createBlock();
		block.getStatements().add(returnStatement);
		exitMethod.setBody(block);

		if (!isPipelineProcedure) {
		JavadocHelper.addJavadoc(exitMethod,
				Messages.JAVADOC_PROCEDURE_EXIT_METHOD.message(getEObject()
						.getName(),
						getEObject().getExecutable() != null ? getEObject()
								.getExecutable() : "", getEObject()
								.getDescription() != null ? getEObject()
								.getDescription() : ""));
		}else{
			JavadocHelper.addJavadoc(exitMethod,
					Messages.JAVADOC_PIPELINE_EXIT_METHOD.message(getEObject()
							.getName(),
							getEObject().getExecutable() != null ? getEObject()
									.getExecutable() : "", getEObject()
									.getDescription() != null ? getEObject()
									.getDescription() : ""));
		}

		GeneratedAnnotationAdder.addGenerated(exitMethod, "//J", true, false);

		context.put(procedure, "exit", exitMethod);
	}
}
