package org.parallelj.code.generator.transformations.structure;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.ctxinject.api.ContextElementVisibility;
import net.atos.optimus.m2m.engine.ctxinject.api.ObjectContextElement;
import net.atos.optimus.m2m.engine.ctxinject.api.ParentContextElement;
import net.atos.optimus.m2m.javaxmi.operation.classes.JavaClass;
import net.atos.optimus.m2m.javaxmi.operation.fields.Field;
import net.atos.optimus.m2m.javaxmi.operation.methods.SetterHelper;

import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
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
	private ClassDeclaration classDeclaration;

	@ObjectContextElement(value = "self", visibility = ContextElementVisibility.INOUT, nullable = false)
	private FieldDeclaration declaration;

	public DataSetterMethodCreation(Data eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		if (declaration.getFragments() != null && declaration.getFragments().get(0) != null
				&& declaration.getType() != null && declaration.getType().getType() != null) {
			Field field = new Field(this.declaration);
			SetterHelper
					.builder(new JavaClass(this.classDeclaration), field)
					.setParameterName(field.getName())
					.build()
					.addJavadoc(Messages.JAVADOC_DATA_SETTER_METHOD.message(field.getName(), field.getTypeName()), true);
		}
	}
}
