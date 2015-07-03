package org.parallelj.code.generator.transformations.structure;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.ctxinject.api.ContextElementVisibility;
import net.atos.optimus.m2m.engine.ctxinject.api.ObjectContextElement;
import net.atos.optimus.m2m.engine.ctxinject.api.ParentContextElement;
import net.atos.optimus.m2m.javaxmi.core.annotations.GeneratedAnnotationAdder;
import net.atos.optimus.m2m.javaxmi.operation.classes.JavaClass;
import net.atos.optimus.m2m.javaxmi.operation.instruction.complex.ComplexInstruction;
import net.atos.optimus.m2m.javaxmi.operation.methods.Method;
import net.atos.optimus.m2m.javaxmi.operation.methods.MethodHelper;

import org.parallelj.code.generator.core.Messages;
import org.parallelj.model.Procedure;

/**
 * This transformation allows to create an exit method in a parallelj program
 * 
 * @author Antoine Neveux & a169104
 * @version 1.0
 * 
 */
public class GenericExitMethodCreation extends AbstractTransformation<Procedure> {

	@ParentContextElement(value = "self", nullable = false)
	private JavaClass parentClass;

	@ObjectContextElement(value = "exit", visibility = ContextElementVisibility.OUT, nullable = false)
	private Method exitMethod;

	// if procedure is from pipeline
	private boolean isPipelineProcedure;

	public GenericExitMethodCreation(Procedure eObject, String id, boolean isForPipeline) {
		super(eObject, id);
		this.isPipelineProcedure = isForPipeline;
	}

	@Override
	protected void transform(ITransformationContext context) {
		this.exitMethod = MethodHelper
				.builder(this.parentClass, getEObject().getName())
				.build()
				.addInstructions(
						ComplexInstruction.createEmptyReturnInstruction().addComment(Messages.COMMENT_TODO.message(),
								true));
		if (isPipelineProcedure) {
			exitMethod.addParameter("Object", "next").addJavadoc(
					Messages.JAVADOC_PIPELINE_EXIT_METHOD.message(getEObject().getName(),
							getEObject().getExecutable() != null ? getEObject().getExecutable() : "", getEObject()
									.getDescription() != null ? getEObject().getDescription() : ""), true);
		} else {
			exitMethod.addJavadoc(Messages.JAVADOC_PROCEDURE_EXIT_METHOD.message(getEObject().getName(), getEObject()
					.getExecutable() != null ? getEObject().getExecutable() : "",
					getEObject().getDescription() != null ? getEObject().getDescription() : ""), true);
		}
		GeneratedAnnotationAdder.addGenerated(exitMethod.getDelegate(), "//J", true, false);
	}
}
