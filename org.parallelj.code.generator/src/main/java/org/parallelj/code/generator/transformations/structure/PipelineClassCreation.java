package org.parallelj.code.generator.transformations.structure;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.ctxinject.api.ContextElementVisibility;
import net.atos.optimus.m2m.engine.ctxinject.api.ObjectContextElement;
import net.atos.optimus.m2m.engine.ctxinject.api.ParentContextElement;
import net.atos.optimus.m2m.javaxmi.core.annotations.GeneratedAnnotationAdder;
import net.atos.optimus.m2m.javaxmi.operation.classes.ClassHelper;
import net.atos.optimus.m2m.javaxmi.operation.classes.JavaClass;
import net.atos.optimus.m2m.javaxmi.operation.constructors.ConstructorHelper;
import net.atos.optimus.m2m.javaxmi.operation.fields.Field;
import net.atos.optimus.m2m.javaxmi.operation.fields.FieldHelper;
import net.atos.optimus.m2m.javaxmi.operation.methods.GetterHelper;
import net.atos.optimus.m2m.javaxmi.operation.methods.SetterHelper;

import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.VisibilityKind;
import org.parallelj.code.generator.core.Messages;
import org.parallelj.code.generator.helpers.StringFormatHelper;
import org.parallelj.model.Pipeline;

/**
 * This transformation allows to create an inner class pipeline in a parallelj
 * program
 * 
 * @author a169104
 * @version 1.0
 * 
 */
public class PipelineClassCreation extends AbstractTransformation<Pipeline> {

	@ParentContextElement(value = "self", nullable = false)
	private ClassDeclaration parent;

	@ObjectContextElement(value = "self", visibility = ContextElementVisibility.OUT, nullable = false)
	private ClassDeclaration classDeclaration;

	public PipelineClassCreation(Pipeline eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		Pipeline pipeline = getEObject();
		String iterableName = pipeline.getIterable().getName();
		String iterableType = pipeline.getIterable().getType();

		JavaClass javaClass = ClassHelper
				.internalClassBuilder(new JavaClass(parent),
						StringFormatHelper.camelCase(pipeline.getName() + "Class", true))
				.build()
				.addJavadoc(
						Messages.JAVADOC_PIPELINE_CLASS.message(pipeline.getName(),
								(getEObject().getDescription() != null ? getEObject().getDescription() : "")), true);
		this.classDeclaration = javaClass.getDelegate();

		Field field = FieldHelper
				.builder(javaClass, iterableType)
				.setName(iterableName)
				.setVisibility(VisibilityKind.NONE)
				.build()
				.addAnnotation("org.parallelj", "PipelineData")
				.addJavadoc(
						Messages.JAVADOC_DATA.message(getEObject().getName(),
								(getEObject().getDescription() != null ? getEObject().getDescription() : "")), true);

		GetterHelper.builder(javaClass, field).build()
				.addJavadoc(Messages.JAVADOC_DATA_GETTER_METHOD.message(iterableName, iterableType), true);
		SetterHelper.builder(javaClass, field).setParameterName(iterableName).build()
				.addJavadoc(Messages.JAVADOC_DATA_SETTER_METHOD.message(iterableName, iterableType), true);

		ConstructorHelper.builder(javaClass).addParameterAndSetAssociatedField(iterableName, field);

		GeneratedAnnotationAdder.addGenerated(classDeclaration, "//J", false, false);
	}
}
