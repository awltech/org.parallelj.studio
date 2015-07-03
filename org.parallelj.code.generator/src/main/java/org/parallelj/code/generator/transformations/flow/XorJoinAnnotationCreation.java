package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.javaxmi.operation.annotations.JavaAnnotation;
import net.atos.optimus.m2m.javaxmi.operation.methods.Method;

import org.parallelj.code.generator.helpers.ParallelJModelHelper;
import org.parallelj.model.Condition;
import org.parallelj.model.Element;
import org.parallelj.model.Link;
import org.parallelj.model.Procedure;

/**
 * This transformation allows to add the <code>XorJoin</code> annotation on an
 * entry method, and all its parameters
 * 
 * @author Antoine Neveux & a169104
 * @version 1.0
 * 
 */
public class XorJoinAnnotationCreation extends AbstractTransformation<Procedure> {

	// if putting @XorJoin on pipeline
	private boolean isPipeline;

	public XorJoinAnnotationCreation(Procedure eObject, String id, boolean isPipeline) {
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
			JavaAnnotation.createOrphanAnnotation("org.parallelj", "XorJoin");
			return;
		}
		JavaAnnotation annotation = method.createAnnotation("org.parallelj", "XorJoin");
		for (Link inputLink : procedure.getInputLinks()) {
			if (inputLink.eContainer() instanceof Condition && ((Element) inputLink.eContainer()).getName() != null) {
				annotation.addAnnotationParameter("value",
						ParallelJModelHelper.getConditionName((Element) inputLink.eContainer()), true);
			}
		}
		context.put(procedure, key, method);
	}

}
