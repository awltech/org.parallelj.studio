package org.parallelj.code.generator.transformations.structure;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.ctxinject.api.ContextElementVisibility;
import net.atos.optimus.m2m.engine.ctxinject.api.ObjectContextElement;
import net.atos.optimus.m2m.engine.ctxinject.api.ParentContextElement;
import net.atos.optimus.m2m.javaxmi.core.annotations.GeneratedAnnotationAdder;
import net.atos.optimus.m2m.javaxmi.operation.classes.JavaClass;
import net.atos.optimus.m2m.javaxmi.operation.instruction.CallInstructionHelper;
import net.atos.optimus.m2m.javaxmi.operation.methods.MethodHelper;

import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.parallelj.code.generator.core.Messages;
import org.parallelj.code.generator.helpers.StringFormatHelper;
import org.parallelj.model.Pipeline;

/**
 * This transformation allows to create an entry method for a pipeline in a
 * parallelj program
 * 
 * @author a169104
 * @version 1.0
 * 
 */
public class PipelineEntryMethodCreation extends AbstractTransformation<Pipeline> {

	@ParentContextElement(value = "self", nullable = false)
	private ClassDeclaration parent;

	@ObjectContextElement(value = "entry", visibility = ContextElementVisibility.OUT, nullable = false)
	private MethodDeclaration entryMethod;

	public PipelineEntryMethodCreation(Pipeline eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Pipeline pipeline = getEObject();
		this.entryMethod = MethodHelper
				.builder(new JavaClass(this.parent), pipeline.getName())
				.setReturnType(StringFormatHelper.camelCase(pipeline.getName() + "Class", true))
				.build()
				.addInstructions(
						CallInstructionHelper
								.createClassInstanciationInstruction(
										StringFormatHelper.camelCase(pipeline.getName() + "Class", true))
								.addVariableArgument(pipeline.getIterable().getName()).convertToReturnInstruction()
								.addComment(Messages.COMMENT_TODO.message(), true))
				.addJavadoc(
						Messages.JAVADOC_PIPELINE_ENTRY_METHOD.message(getEObject().getName(), getEObject()
								.getExecutable(), (getEObject().getDescription() != null ? getEObject()
								.getDescription() : "")), true).getDelegate();

		GeneratedAnnotationAdder.addGenerated(entryMethod, "//J", true, false);
	}
}
