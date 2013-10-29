package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.javaxmi.core.annotations.JavaAnnotationHelper;

import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
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
		
		String key = "self";
		
		if(isPipeline){
			key = "entry";
		}
		
		Procedure procedure = getEObject();
		boolean noop = false;
		AbstractMethodDeclaration declaration = (AbstractMethodDeclaration) context
				.get(procedure, key);

		if (declaration == null) {
			noop = true;
			declaration = (AbstractMethodDeclaration) context.get(procedure,
					"exit");
		}

		JavaAnnotationHelper.addAnnotation(declaration, "org.parallelj",
				"Begin");

		if (!noop)
			context.put(procedure, key, declaration);
		else
			context.put(procedure, "exit", declaration);
	}
}
