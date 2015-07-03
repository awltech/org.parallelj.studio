package org.parallelj.code.generator.transformations.structure;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.ctxinject.api.ContextElementVisibility;
import net.atos.optimus.m2m.engine.ctxinject.api.ObjectContextElement;
import net.atos.optimus.m2m.engine.ctxinject.api.ParentContextElement;
import net.atos.optimus.m2m.javaxmi.core.annotations.GeneratedAnnotationAdder;
import net.atos.optimus.m2m.javaxmi.operation.classes.JavaClass;
import net.atos.optimus.m2m.javaxmi.operation.instruction.CallInstructionHelper;
import net.atos.optimus.m2m.javaxmi.operation.methods.Method;
import net.atos.optimus.m2m.javaxmi.operation.methods.MethodHelper;

import org.parallelj.code.generator.core.Messages;
import org.parallelj.model.Procedure;

/**
 * This transformation allows to create an entry method for a procedure in a
 * parallelj program
 * 
 * @author Antoine Neveux & a169104
 * @version 1.0
 * 
 */
public class ProcedureEntryMethodCreation extends AbstractTransformation<Procedure> {

	@ParentContextElement(value = "self", nullable = false)
	private JavaClass parentClass;

	@ObjectContextElement(value = "self", visibility = ContextElementVisibility.OUT, nullable = false)
	private Method entryMethod;

	// if putting entry method for pipeline
	private boolean isForPipeline;

	public ProcedureEntryMethodCreation(Procedure eObject, String id, boolean isForPipeline) {
		super(eObject, id);
		this.isForPipeline = isForPipeline;
	}

	@Override
	protected void transform(ITransformationContext context) {
		Procedure procedure = getEObject();
		this.entryMethod = MethodHelper
				.builder(this.parentClass, procedure.getName())
				.setReturnType(procedure.getExecutable())
				.addInstructions(
						CallInstructionHelper.createClassInstanciationInstruction(procedure.getExecutable())
								.convertToReturnInstruction().addComment(Messages.COMMENT_TODO.message(), true))
				.build()
				.addJavadoc(
						Messages.JAVADOC_PROCEDURE_ENTRY_METHOD.message(getEObject().getName(), getEObject()
								.getExecutable(), (getEObject().getDescription() != null ? getEObject()
								.getDescription() : "")), true);
		if (isForPipeline) {
			this.entryMethod.addParameter("Object", "next");
		}
		GeneratedAnnotationAdder.addGenerated(this.entryMethod.getDelegate(), "//J", true, false);
	}

}
