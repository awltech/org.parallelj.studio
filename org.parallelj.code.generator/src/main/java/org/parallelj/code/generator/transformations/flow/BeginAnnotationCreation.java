package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.javaxmi.operation.annotations.JavaAnnotation;
import net.atos.optimus.m2m.javaxmi.operation.methods.Method;

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

	public BeginAnnotationCreation(Procedure eObject, String id, boolean isPipeline) {
		super(eObject, id);
		this.isPipeline = isPipeline;
	}

	@Override
	protected void transform(ITransformationContext context) {
		String key = isPipeline ? "entry" : "self";
		Procedure procedure = getEObject();
		Method method = (Method) context.get(procedure, key);
		if (method == null) {
			key = "exit";
			method = (Method) context.get(procedure, key);
		}
		if(method == null){
			JavaAnnotation.createOrphanAnnotation("org.parallelj", "Begin");
			return;
		}
		method.addAnnotation("org.parallelj", "Begin");
		context.put(procedure, key, method);
	}
}
