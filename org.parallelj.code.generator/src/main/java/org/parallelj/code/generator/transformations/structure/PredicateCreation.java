package org.parallelj.code.generator.transformations.structure;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.javaxmi.core.annotations.GeneratedAnnotationAdder;
import net.atos.optimus.m2m.javaxmi.core.javadoc.JavadocHelper;

import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.BooleanLiteral;
import org.eclipse.gmt.modisco.java.LineComment;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.ReturnStatement;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.VisibilityKind;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;
import org.parallelj.code.generator.core.Messages;
import org.parallelj.model.Predicate;

/**
 * This transformation allows to create a predicate in a parallelj program
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class PredicateCreation extends AbstractTransformation<Predicate> {

	public PredicateCreation(Predicate eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Predicate predicate = getEObject();

		AbstractMethodDeclaration declaration = JavaFactory.eINSTANCE
				.createMethodDeclaration();

		String predicateName = "is"
				+ predicate.getName().substring(0, 1).toUpperCase()
				+ predicate.getName().substring(1);
		declaration.setName(predicateName);

		AbstractTypeDeclaration parent = (AbstractTypeDeclaration) context.get(
				getEObject().eContainer(), "self");
		declaration.setOriginalCompilationUnit(parent
				.getOriginalCompilationUnit());
		declaration.setAbstractTypeDeclaration(parent);

		Modifier modifier = JavaFactory.eINSTANCE.createModifier();
		modifier.setVisibility(VisibilityKind.PUBLIC);
		modifier.setOriginalCompilationUnit(parent.getOriginalCompilationUnit());
		declaration.setModifier(modifier);

		MethodDeclaration methodDeclaration = (MethodDeclaration) declaration;
		TypeAccess returnTypeAccess = JavaFactory.eINSTANCE.createTypeAccess();
		Type type = JavaFactory.eINSTANCE.createPrimitiveType();
		type.setName("boolean");
		returnTypeAccess.setType(type);
		methodDeclaration.setReturnType(returnTypeAccess);

		ReturnStatement returnStatement = JavaFactory.eINSTANCE
				.createReturnStatement();
		BooleanLiteral literal = JavaFactory.eINSTANCE.createBooleanLiteral();
		literal.setValue(false);
		returnStatement.setExpression(literal);

		LineComment lineComment = JavaFactory.eINSTANCE.createLineComment();
		lineComment.setContent(Messages.COMMENT_TODO.message());
		lineComment.setPrefixOfParent(true);
		returnStatement.getComments().add(lineComment);

		Block block = JavaFactory.eINSTANCE.createBlock();
		block.getStatements().add(returnStatement);
		declaration.setBody(block);

		JavadocHelper.addJavadoc(declaration, Messages.JAVADOC_PREDICATE
				.message(getEObject().getName(),
						(getEObject().getDescription() != null ? getEObject()
								.getDescription() : "")));

		GeneratedAnnotationAdder.addGenerated(declaration, "//J", true, false);

		context.put(predicate, "self", declaration);
	}

}
