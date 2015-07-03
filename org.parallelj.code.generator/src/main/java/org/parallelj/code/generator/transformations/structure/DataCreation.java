package org.parallelj.code.generator.transformations.structure;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.ctxinject.api.ContextElementVisibility;
import net.atos.optimus.m2m.engine.ctxinject.api.ObjectContextElement;
import net.atos.optimus.m2m.engine.ctxinject.api.ParentContextElement;
import net.atos.optimus.m2m.javaxmi.operation.classes.JavaClass;
import net.atos.optimus.m2m.javaxmi.operation.fields.Field;
import net.atos.optimus.m2m.javaxmi.operation.fields.FieldHelper;

import org.eclipse.gmt.modisco.java.VisibilityKind;
import org.parallelj.code.generator.core.Messages;
import org.parallelj.model.Data;

/**
 * This transformation allows to create a data in a ParallelJ program
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class DataCreation extends AbstractTransformation<Data> {

	@ParentContextElement(value = "self", nullable = false)
	private JavaClass javaClass;

	@ObjectContextElement(value = "self", visibility = ContextElementVisibility.OUT, nullable = false)
	private Field field;

	public DataCreation(Data eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		this.field = FieldHelper
				.builder(this.javaClass, getEObject().getType())
				.setName(getEObject().getName())
				.setVisibility(VisibilityKind.NONE)
				.build()
				.addJavadoc(
						Messages.JAVADOC_DATA.message(getEObject().getName(),
								(getEObject().getDescription() != null ? getEObject().getDescription() : "")), true);
	}

}
