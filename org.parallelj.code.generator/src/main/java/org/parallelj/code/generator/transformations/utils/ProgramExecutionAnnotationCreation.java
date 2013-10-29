package org.parallelj.code.generator.transformations.utils;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.javaxmi.core.annotations.JavaAnnotationHelper;

import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.parallelj.model.Program;

/**
 * This transformation allows to add the <code>@QuartzExecution</code> on a
 * parallelj program
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class ProgramExecutionAnnotationCreation extends
		AbstractTransformation<Program> {

	public ProgramExecutionAnnotationCreation(Program eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Program program = getEObject();
		ClassDeclaration classDeclaration = (ClassDeclaration) context.get(
				program, "self");
		JavaAnnotationHelper.addAnnotation(classDeclaration,
				"org.parallelj.launching", "QuartzExecution");
		context.put(program, "self", classDeclaration);
	}

}
