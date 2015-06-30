package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.ctxinject.api.ContextElementVisibility;
import net.atos.optimus.m2m.engine.ctxinject.api.ObjectContextElement;
import net.atos.optimus.m2m.javaxmi.operation.classes.JavaClass;

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

	@ObjectContextElement(value = "self", visibility = ContextElementVisibility.INOUT, nullable = false)
	private ClassDeclaration classDeclaration;

	public ProgramAnnotationCreation(Program eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		(new JavaClass(this.classDeclaration)).addAnnotation("org.parallelj", "Program");
	}
	
}
