package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.javaxmi.core.annotations.JavaAnnotationHelper;

import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.Annotation;
import org.parallelj.model.Handler;
import org.parallelj.model.Procedure;

/**
 * This transformation allows to add the <code>@Handler</code> annotation on an
 * exit method, and its parameters
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class HandlerAnnotationCreation extends AbstractTransformation<Handler> {

	public HandlerAnnotationCreation(Handler eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Handler handler = getEObject();
		AbstractMethodDeclaration declaration = (AbstractMethodDeclaration) context
				.get(handler, "exit");

		Annotation annotation = JavaAnnotationHelper.addAnnotation(declaration,
				"org.parallelj", "Handler");

		for (Procedure procedure : handler.getProcedures())
			JavaAnnotationHelper.addAnnotationParameter(annotation, "value",
					procedure.getName());

		context.put(handler, "exit", declaration);
	}

}
