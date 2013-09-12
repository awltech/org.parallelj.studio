package org.parallelj.code.generator.transformations.structure;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;

import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.parallelj.code.generator.helpers.ParallelJModelHelper;
import org.parallelj.model.Data;

/**
 * This transformation allows to create a data getter method in a ParallelJ
 * program
 * 
 * @author Abhijit Gurav
 * @version 1.0
 * 
 */
public class DataGetterMethodCreation extends AbstractTransformation<Data> {

	public DataGetterMethodCreation(Data eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {

		FieldDeclaration declaration = (FieldDeclaration) context.get(
				getEObject(), "self");

		AbstractTypeDeclaration parentDeclaration = (AbstractTypeDeclaration) context
				.get(getEObject().eContainer(), "self");

		// getter method generation
		if (declaration.getFragments() != null
				&& declaration.getFragments().get(0) != null
				&& declaration.getType() != null
				&& declaration.getType().getType() != null) {
			
			MethodDeclaration generateGetter = ParallelJModelHelper
					.generateGetter(
							declaration.getFragments().get(0).getName(),
							declaration.getType().getType().getName());

			generateGetter.setAbstractTypeDeclaration(parentDeclaration);
		}
		context.put(getEObject(), "self", declaration);
	}
}
