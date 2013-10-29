package org.parallelj.code.generator.transformations.structure;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.javaxmi.core.annotations.GeneratedAnnotationAdder;
import net.atos.optimus.m2m.javaxmi.core.javadoc.JavadocHelper;

import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.ClassInstanceCreation;
import org.eclipse.gmt.modisco.java.LineComment;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
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
 * This transformation allows to create an entry method for a procedure in a
 * parallelj program
 * 
 * @author Antoine Neveux & a169104
 * @version 1.0
 * 
 */
public class ProcedureEntryMethodCreation extends
		AbstractTransformation<Procedure> {

	// if putting entry method for pipeline
	private boolean isForPipeline;

	public ProcedureEntryMethodCreation(Procedure eObject, String id,
			boolean isForPipeline) {
		super(eObject, id);
		this.isForPipeline = isForPipeline;
	}

	@Override
	protected void transform(ITransformationContext context) {
		Procedure procedure = getEObject();

		AbstractMethodDeclaration entryMethod = JavaFactory.eINSTANCE
				.createMethodDeclaration();
		entryMethod.setName(getEObject().getName());

		AbstractTypeDeclaration parent = (AbstractTypeDeclaration) context.get(
				getEObject().eContainer(), "self");
		entryMethod.setOriginalCompilationUnit(parent
				.getOriginalCompilationUnit());
		entryMethod.setAbstractTypeDeclaration(parent);

		Modifier modifier = JavaFactory.eINSTANCE.createModifier();
		modifier.setVisibility(VisibilityKind.PUBLIC);
		modifier.setOriginalCompilationUnit(parent.getOriginalCompilationUnit());
		entryMethod.setModifier(modifier);

		MethodDeclaration methodDeclaration = (MethodDeclaration) entryMethod;

		// if it is pipeline procedure, then it will have one parameter as
		// object in method
		if (isForPipeline) {
			SingleVariableDeclaration declaration = JavaFactory.eINSTANCE
					.createSingleVariableDeclaration();
			declaration.setMethodDeclaration(entryMethod);
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

		TypeAccess createTypeAccess = JavaFactory.eINSTANCE.createTypeAccess();
		Type type = JavaFactory.eINSTANCE.createClassDeclaration();
		type.setName(procedure.getExecutable());
		createTypeAccess.setType(type);
		methodDeclaration.setReturnType(createTypeAccess);

		ReturnStatement returnStatement = JavaFactory.eINSTANCE
				.createReturnStatement();
		ClassInstanceCreation returnSta = JavaFactory.eINSTANCE
				.createClassInstanceCreation();
		createTypeAccess = JavaFactory.eINSTANCE.createTypeAccess();
		Type classType = JavaFactory.eINSTANCE
				.createUnresolvedClassDeclaration();

		classType.setName(getEObject().getExecutable());
		createTypeAccess.setType(classType);
		returnSta.setType(createTypeAccess);
		returnStatement.setExpression(returnSta);

		LineComment lineComment = JavaFactory.eINSTANCE.createLineComment();
		lineComment.setContent(Messages.COMMENT_TODO.message());
		lineComment.setPrefixOfParent(true);
		returnStatement.getComments().add(lineComment);

		Block block = JavaFactory.eINSTANCE.createBlock();
		block.getStatements().add(returnStatement);
		entryMethod.setBody(block);

		JavadocHelper.addJavadoc(entryMethod,
				Messages.JAVADOC_PROCEDURE_ENTRY_METHOD.message(getEObject()
						.getName(), getEObject().getExecutable(), (getEObject()
						.getDescription() != null ? getEObject()
						.getDescription() : "")));

		GeneratedAnnotationAdder.addGenerated(entryMethod, "//J", true, false);

		context.put(procedure, "self", entryMethod);
	}

}
