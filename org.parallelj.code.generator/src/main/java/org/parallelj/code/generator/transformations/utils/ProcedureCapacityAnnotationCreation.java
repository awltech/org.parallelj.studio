package org.parallelj.code.generator.transformations.utils;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.javaxmi.core.annotations.JavaAnnotationHelper;

import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.Annotation;
import org.eclipse.gmt.modisco.java.AnnotationMemberValuePair;
import org.eclipse.gmt.modisco.java.NumberLiteral;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;
import org.parallelj.model.Procedure;

/**
 * This transformation allows to add the <code>@Capacity</code> annotation on a
 * procedure's entry method
 * 
 * @author Antoine Neveux & a169104
 * @version 1.0
 * 
 */
public class ProcedureCapacityAnnotationCreation extends
		AbstractTransformation<Procedure> {
	
	// if putting @Capacity for pipeline
	private boolean isPipeline;

	public ProcedureCapacityAnnotationCreation(Procedure eObject, String id,boolean isPipeline) {
		super(eObject, id);
		this.isPipeline = isPipeline;
	}

	@Override
	protected void transform(ITransformationContext context) {
		
		String key = "self";
		
		if(isPipeline){
			key = "entry";
		}
		
		Procedure procedure = getEObject();
		AbstractMethodDeclaration declaration = (AbstractMethodDeclaration) context
				.get(procedure, key);

		Annotation annotation = JavaAnnotationHelper.addAnnotation(declaration,
				"org.parallelj", "Capacity");

		AnnotationMemberValuePair mvp = JavaFactory.eINSTANCE
				.createAnnotationMemberValuePair();
		mvp.setName("value");
		NumberLiteral nlt = JavaFactory.eINSTANCE.createNumberLiteral();
		nlt.setTokenValue(String.valueOf(procedure.getCapacity()));
		mvp.setValue(nlt);
		annotation.getValues().add(mvp);

		context.put(procedure, key, declaration);
	}

}
