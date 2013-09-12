package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.javaxmi.core.annotations.JavaAnnotationHelper;

import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.parallelj.model.Program;

/**
 * This transformation allows to add the <code>@Program</code> annotation on a
 * program class
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class ProgramAnnotationCreation extends AbstractTransformation<Program> {

	public ProgramAnnotationCreation(Program eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Program program = getEObject();
		ClassDeclaration classDeclaration = (ClassDeclaration) context.get(
				program, "self");

		JavaAnnotationHelper.addAnnotation(classDeclaration, "org.parallelj",
				"Program");

		context.put(program, "self", classDeclaration);
	}

}
