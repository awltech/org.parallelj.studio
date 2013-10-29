package org.parallelj.code.generator.transformations.flow;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.javaxmi.core.annotations.JavaAnnotationHelper;

import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.Annotation;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;
import org.parallelj.model.ForEachLoop;

/**
 * This transformation allows to add the <code>@ForEach</code> annotation and
 * the method parameter on an entry method. It also manages the parameters of
 * the annotation
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class ForEachAnnotationCreation extends
		AbstractTransformation<ForEachLoop> {

	public ForEachAnnotationCreation(ForEachLoop eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {
		ForEachLoop forEach = getEObject();
		AbstractMethodDeclaration declaration = (AbstractMethodDeclaration) context
				.get(forEach, "self");

		AbstractTypeDeclaration parent = (AbstractTypeDeclaration) context.get(
				getEObject().eContainer(), "self");

		SingleVariableDeclaration variable = JavaFactory.eINSTANCE
				.createSingleVariableDeclaration();
		variable.setMethodDeclaration(declaration);
		variable.setModifier(JavaFactory.eINSTANCE.createModifier());
		variable.setVarargs(false);

		TypeAccess parameterTypeAccess = JavaFactory.eINSTANCE
				.createTypeAccess();
		Type parameterType = JavaFactory.eINSTANCE.createPrimitiveType();
		parameterType.setName("Object"); //forEach.getIterable().getType());
		parameterTypeAccess.setType(parameterType);

		variable.setType(parameterTypeAccess);
		variable.setName("val");

		Annotation annotation = JavaAnnotationHelper.addAnnotation(
				variable.getMethodDeclaration(), "org.parallelj", "ForEach");

		JavaAnnotationHelper.addAnnotationParameter(annotation, "value",
				forEach.getIterable() != null ? forEach.getIterable().getName()
						: "");

		variable.getAnnotations().add(annotation);
		variable.setOriginalCompilationUnit(parent.getOriginalCompilationUnit());

		context.put(forEach, "self", declaration);
	}

}
