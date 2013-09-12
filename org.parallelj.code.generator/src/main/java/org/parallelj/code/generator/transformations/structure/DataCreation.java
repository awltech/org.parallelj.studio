package org.parallelj.code.generator.transformations.structure;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.javaxmi.core.javadoc.JavadocHelper;

import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;
import org.parallelj.code.generator.core.Messages;
import org.parallelj.model.Data;

/**
 * This transformation allows to create a data in a ParallelJ program
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class DataCreation extends AbstractTransformation<Data> {

	public DataCreation(Data eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		FieldDeclaration declaration = JavaFactory.eINSTANCE
				.createFieldDeclaration();

		AbstractTypeDeclaration parentDeclaration = (AbstractTypeDeclaration) context
				.get(getEObject().eContainer(), "self");
		declaration.setAbstractTypeDeclaration(parentDeclaration);
		declaration.setOriginalCompilationUnit(parentDeclaration
				.getOriginalCompilationUnit());

		VariableDeclarationFragment fragment = JavaFactory.eINSTANCE
				.createVariableDeclarationFragment();
		fragment.setName(getEObject().getName());
		declaration.getFragments().add(fragment);

		Type type = JavaFactory.eINSTANCE.createPrimitiveType();
		type.setName(getEObject().getType());

		TypeAccess typeAccess = JavaFactory.eINSTANCE.createTypeAccess();
		typeAccess.setType(type);
		declaration.setType(typeAccess);

		JavadocHelper.addJavadoc(declaration, Messages.JAVADOC_DATA.message(
				getEObject().getName(),
				(getEObject().getDescription() != null ? getEObject()
						.getDescription() : "")));

		context.put(getEObject(), "self", declaration);
	}

}
