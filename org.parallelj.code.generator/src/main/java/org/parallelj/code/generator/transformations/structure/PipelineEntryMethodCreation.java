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
import org.eclipse.gmt.modisco.java.SingleVariableAccess;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.VisibilityKind;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;
import org.parallelj.code.generator.core.Messages;
import org.parallelj.code.generator.helpers.StringFormatHelper;
import org.parallelj.model.Pipeline;

/**
 * This transformation allows to create an entry method for a pipeline in a
 * parallelj program
 * 
 * @author a169104
 * @version 1.0
 * 
 */
public class PipelineEntryMethodCreation extends
		AbstractTransformation<Pipeline> {

	public PipelineEntryMethodCreation(Pipeline eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Pipeline pipeline = getEObject();

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

		TypeAccess createTypeAccess = JavaFactory.eINSTANCE.createTypeAccess();
		Type type = JavaFactory.eINSTANCE.createClassDeclaration();
		type.setName(StringFormatHelper.camelCase(pipeline.getName() + "Class",true));
		createTypeAccess.setType(type);
		methodDeclaration.setReturnType(createTypeAccess);

		ReturnStatement returnStatement = JavaFactory.eINSTANCE
				.createReturnStatement();
		ClassInstanceCreation returnSta = JavaFactory.eINSTANCE
				.createClassInstanceCreation();
		createTypeAccess = JavaFactory.eINSTANCE.createTypeAccess();
		Type classType = JavaFactory.eINSTANCE
				.createUnresolvedClassDeclaration();

		classType.setName(StringFormatHelper.camelCase(pipeline.getName() + "Class",true));
		createTypeAccess.setType(classType);
		returnSta.setType(createTypeAccess);
		
		// creating variable declaration
		VariableDeclarationFragment variableDeclarationFragment = JavaFactory.eINSTANCE
				.createVariableDeclarationFragment();

		// adding name
		variableDeclarationFragment.setName(pipeline.getIterable().getName());
		variableDeclarationFragment.setProxy(false);

		SingleVariableAccess singleVariableAccess = JavaFactory.eINSTANCE
				.createSingleVariableAccess();
		singleVariableAccess.setVariable(variableDeclarationFragment);
		
		returnSta.getArguments().add(singleVariableAccess);
		
		returnStatement.setExpression(returnSta);

		LineComment lineComment = JavaFactory.eINSTANCE.createLineComment();
		lineComment.setContent(Messages.COMMENT_TODO.message());
		lineComment.setPrefixOfParent(true);
		returnStatement.getComments().add(lineComment);

		Block block = JavaFactory.eINSTANCE.createBlock();
		block.getStatements().add(returnStatement);
		entryMethod.setBody(block);

		JavadocHelper.addJavadoc(entryMethod,
				Messages.JAVADOC_PIPELINE_ENTRY_METHOD.message(getEObject()
						.getName(), getEObject().getExecutable(), (getEObject()
						.getDescription() != null ? getEObject()
						.getDescription() : "")));

		GeneratedAnnotationAdder.addGenerated(entryMethod, "//J", true, false);

		context.put(pipeline, "entry", entryMethod);
	}

}
