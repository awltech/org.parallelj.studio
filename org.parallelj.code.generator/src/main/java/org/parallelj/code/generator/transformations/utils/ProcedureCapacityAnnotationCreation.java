package org.parallelj.code.generator.transformations.utils;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.javaxmi.operation.methods.Method;

import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.parallelj.model.Procedure;

/**
 * This transformation allows to add the <code>@Capacity</code> annotation on a
 * procedure's entry method
 * 
 * @author Antoine Neveux & a169104
 * @version 1.0
 * 
 */
public class ProcedureCapacityAnnotationCreation extends AbstractTransformation<Procedure> {

	// if putting @Capacity for pipeline
	private boolean isPipeline;

	public ProcedureCapacityAnnotationCreation(Procedure eObject, String id, boolean isPipeline) {
		super(eObject, id);
		this.isPipeline = isPipeline;
	}

	@Override
	protected void transform(ITransformationContext context) {
		String key = isPipeline ? "entry" : "self";
		Procedure procedure = getEObject();
		MethodDeclaration declaration = (MethodDeclaration) context.get(procedure, key);
		new Method(declaration).createAnnotation("org.parallelj", "Capacity").addAnnotationParameter("value",
				procedure.getCapacity());
	}

}
