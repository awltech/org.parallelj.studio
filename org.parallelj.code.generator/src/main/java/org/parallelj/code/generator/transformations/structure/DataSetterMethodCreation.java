package org.parallelj.code.generator.transformations.structure;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.ctxinject.api.ContextElementVisibility;
import net.atos.optimus.m2m.engine.ctxinject.api.ObjectContextElement;
import net.atos.optimus.m2m.engine.ctxinject.api.ParentContextElement;
import net.atos.optimus.m2m.javaxmi.operation.classes.JavaClass;
import net.atos.optimus.m2m.javaxmi.operation.fields.Field;
import net.atos.optimus.m2m.javaxmi.operation.methods.SetterHelper;

import org.parallelj.code.generator.core.Messages;
import org.parallelj.model.Data;

/**
 * This transformation allows to create a data setter method in a ParallelJ
 * program
 * 
 * @author Abhijit Gurav
 * @version 1.0
 * 
 */
public class DataSetterMethodCreation extends AbstractTransformation<Data> {

	@ParentContextElement(value = "self", nullable = false)
	private JavaClass javaClass;

	@ObjectContextElement(value = "self", visibility = ContextElementVisibility.INOUT, nullable = false)
	private Field field;

	public DataSetterMethodCreation(Data eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		SetterHelper
				.builder(this.javaClass, this.field)
				.setParameterName(this.field.getName())
				.build()
				.addJavadoc(
						Messages.JAVADOC_DATA_SETTER_METHOD.message(this.field.getName(), this.field.getTypeName()),
						true);
	}
}
