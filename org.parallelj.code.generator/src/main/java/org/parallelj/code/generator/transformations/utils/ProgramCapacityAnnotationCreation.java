package org.parallelj.code.generator.transformations.utils;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.ctxinject.api.ContextElementVisibility;
import net.atos.optimus.m2m.engine.ctxinject.api.ObjectContextElement;
import net.atos.optimus.m2m.javaxmi.operation.classes.JavaClass;

import org.parallelj.model.Program;

/**
 * This transformation allows to add the <code>@Capacity</code> annotation on a
 * parallelj program
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class ProgramCapacityAnnotationCreation extends AbstractTransformation<Program> {

	@ObjectContextElement(value = "self", visibility = ContextElementVisibility.INOUT, nullable = false)
	private JavaClass javaClass;

	public ProgramCapacityAnnotationCreation(Program eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Program program = getEObject();
		this.javaClass.createAnnotation("org.parallelj", "Capacity").addAnnotationParameter("value",
				program.getCapacity());
	}

}
