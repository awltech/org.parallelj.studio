package org.parallelj.code.generator.transformations.utils;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.javaxmi.core.annotations.JavaAnnotationHelper;

import org.eclipse.gmt.modisco.java.Annotation;
import org.eclipse.gmt.modisco.java.AnnotationMemberValuePair;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.NumberLiteral;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;
import org.parallelj.model.Program;

/**
 * This transformation allows to add the <code>@Capacity</code> annotation on a
 * parallelj program
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class ProgramCapacityAnnotationCreation extends
		AbstractTransformation<Program> {

	public ProgramCapacityAnnotationCreation(Program eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Program program = getEObject();
		ClassDeclaration classDeclaration = (ClassDeclaration) context.get(
				program, "self");

		Annotation addedAnnotation = JavaAnnotationHelper.addAnnotation(
				classDeclaration, "org.parallelj", "Capacity");

		AnnotationMemberValuePair mvp = JavaFactory.eINSTANCE
				.createAnnotationMemberValuePair();
		mvp.setName("value");
		NumberLiteral nlt = JavaFactory.eINSTANCE.createNumberLiteral();
		nlt.setTokenValue(String.valueOf(program.getCapacity()));
		mvp.setValue(nlt);

		addedAnnotation.getValues().add(mvp);

		context.put(program, "self", classDeclaration);
	}

}
