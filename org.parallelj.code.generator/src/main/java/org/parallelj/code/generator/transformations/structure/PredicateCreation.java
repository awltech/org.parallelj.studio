package org.parallelj.code.generator.transformations.structure;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.ctxinject.api.ContextElementVisibility;
import net.atos.optimus.m2m.engine.ctxinject.api.ObjectContextElement;
import net.atos.optimus.m2m.engine.ctxinject.api.ParentContextElement;
import net.atos.optimus.m2m.javaxmi.core.annotations.GeneratedAnnotationAdder;
import net.atos.optimus.m2m.javaxmi.operation.classes.JavaClass;
import net.atos.optimus.m2m.javaxmi.operation.instructions.complex.ReturnInstructionHelper;
import net.atos.optimus.m2m.javaxmi.operation.methods.Method;
import net.atos.optimus.m2m.javaxmi.operation.methods.MethodHelper;

import org.parallelj.code.generator.core.Messages;
import org.parallelj.model.Predicate;

/**
 * This transformation allows to create a predicate in a parallelj program
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class PredicateCreation extends AbstractTransformation<Predicate> {

	@ParentContextElement(value = "self", nullable = false)
	private JavaClass parentClass;

	@ObjectContextElement(value = "self", visibility = ContextElementVisibility.OUT, nullable = false)
	private Method method;

	public PredicateCreation(Predicate eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Predicate predicate = getEObject();
		this.method = MethodHelper
				.builder(this.parentClass,
						"is" + predicate.getName().substring(0, 1).toUpperCase() + predicate.getName().substring(1))
				.setReturnType("boolean")
				.addInstructions(
						ReturnInstructionHelper.createReturnInstruction(false).addComment(
								Messages.COMMENT_TODO.message(), true))
				.build()
				.addJavadoc(
						Messages.JAVADOC_PREDICATE.message(getEObject().getName(),
								(getEObject().getDescription() != null ? getEObject().getDescription() : "")), true);
		GeneratedAnnotationAdder.addGenerated(method.getDelegate(), "//J", true, false);
	}
}
