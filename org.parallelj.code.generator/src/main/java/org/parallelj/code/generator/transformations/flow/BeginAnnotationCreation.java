package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.javaxmi.operation.methods.Method;

import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.parallelj.model.Procedure;

/**
 * This transformation allows to add the <code>@Begin</code> annotation on an
 * entry method
 * 
 * @author Antoine Neveux & a169104
 * @version 1.0
 * 
 */
public class BeginAnnotationCreation extends AbstractTransformation<Procedure> {

	// if putting @Begin on pipeline
	private boolean isPipeline;

	public BeginAnnotationCreation(Procedure eObject, String id,
			boolean isPipeline) {
		super(eObject, id);
		this.isPipeline = isPipeline;
	}

	@Override
	protected void transform(ITransformationContext context) {
		String key = isPipeline ? "entry" : "self";
		Procedure procedure = getEObject();
		MethodDeclaration declaration = (MethodDeclaration) context.get(procedure, key);
		if (declaration == null) {
			key = "exit";
			declaration = (MethodDeclaration) context.get(procedure, key);
		}
		new Method(declaration).addAnnotation("org.parallelj", "Begin");
		context.put(procedure, key, declaration);
	}
}
