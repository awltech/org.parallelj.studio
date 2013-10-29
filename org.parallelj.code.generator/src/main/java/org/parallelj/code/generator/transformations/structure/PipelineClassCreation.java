package org.parallelj.code.generator.transformations.structure;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.javaxmi.core.annotations.GeneratedAnnotationAdder;
import net.atos.optimus.m2m.javaxmi.core.annotations.JavaAnnotationHelper;
import net.atos.optimus.m2m.javaxmi.core.javadoc.JavadocHelper;

import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.ConstructorDeclaration;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.VisibilityKind;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;
import org.parallelj.code.generator.core.Messages;
import org.parallelj.code.generator.helpers.ParallelJModelHelper;
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

	public PipelineClassCreation(Pipeline eObject, String id) {
		super(eObject, id);
	}

	@Override
	protected void transform(ITransformationContext context) {

		Pipeline pipeline = getEObject();

		String iterableName = pipeline.getIterable().getName();
		String iterableType = pipeline.getIterable().getType();

		ClassDeclaration classDeclaration = JavaFactory.eINSTANCE
				.createClassDeclaration();
		classDeclaration.setName(StringFormatHelper.camelCase(
				pipeline.getName() + "Class", true));
		classDeclaration.setProxy(false);

		Modifier modifier = JavaFactory.eINSTANCE.createModifier();
		modifier.setVisibility(VisibilityKind.PUBLIC);
		classDeclaration.setModifier(modifier);

		AbstractTypeDeclaration parent = (AbstractTypeDeclaration) context.get(
				getEObject().eContainer(), "self");

		classDeclaration.setPackage(parent.getPackage());

		classDeclaration.setOriginalCompilationUnit(parent
				.getOriginalCompilationUnit());
		classDeclaration.setAbstractTypeDeclaration(parent);

		// javadoc on class
		JavadocHelper.addJavadoc(classDeclaration,
				Messages.JAVADOC_PIPELINE_CLASS.message(pipeline.getName(),
						(getEObject().getDescription() != null ? getEObject()
								.getDescription() : "")));

		// generating data field
		FieldDeclaration fieldDeclaration = JavaFactory.eINSTANCE
				.createFieldDeclaration();

		fieldDeclaration.setAbstractTypeDeclaration(classDeclaration);
		fieldDeclaration.setOriginalCompilationUnit(parent
				.getOriginalCompilationUnit());

		VariableDeclarationFragment fragment = JavaFactory.eINSTANCE
				.createVariableDeclarationFragment();
		fragment.setName(iterableName);
		fieldDeclaration.getFragments().add(fragment);

		Type type = JavaFactory.eINSTANCE.createPrimitiveType();
		type.setName(iterableType);

		TypeAccess typeAccess = JavaFactory.eINSTANCE.createTypeAccess();
		typeAccess.setType(type);
		fieldDeclaration.setType(typeAccess);

		JavadocHelper.addJavadoc(fieldDeclaration, Messages.JAVADOC_DATA
				.message(getEObject().getName(),
						(getEObject().getDescription() != null ? getEObject()
								.getDescription() : "")));

		// adding @PipelineData on iterable data inside pipeline class
		JavaAnnotationHelper.addAnnotation(fieldDeclaration, "org.parallelj",
				"PipelineData");

		// generating getter
		MethodDeclaration generateGetter = ParallelJModelHelper.generateGetter(
				iterableName, iterableType);

		generateGetter.setAbstractTypeDeclaration(classDeclaration);

		// generating setter
		MethodDeclaration generateSetter = ParallelJModelHelper.generateSetter(
				iterableName, iterableType);

		generateSetter.setAbstractTypeDeclaration(classDeclaration);

		// generating constructor
		ConstructorDeclaration createConstructorDeclaration = JavaFactory.eINSTANCE
				.createConstructorDeclaration();

		createConstructorDeclaration.setOriginalCompilationUnit(parent
				.getOriginalCompilationUnit());
		createConstructorDeclaration
				.setAbstractTypeDeclaration(classDeclaration);
		Modifier modifier2 = JavaFactory.eINSTANCE.createModifier();
		modifier2.setVisibility(VisibilityKind.PUBLIC);
		createConstructorDeclaration.setModifier(modifier2);
		createConstructorDeclaration.setName(classDeclaration.getName());

		SingleVariableDeclaration declaration = JavaFactory.eINSTANCE
				.createSingleVariableDeclaration();
		declaration.setMethodDeclaration(createConstructorDeclaration);
		declaration.setModifier(JavaFactory.eINSTANCE.createModifier());
		declaration.setVarargs(false);

		TypeAccess parameterTypeAccess = JavaFactory.eINSTANCE
				.createTypeAccess();
		Type parameterType = JavaFactory.eINSTANCE.createPrimitiveType();
		parameterType.setName(iterableType);
		parameterTypeAccess.setType(parameterType);

		declaration.setType(parameterTypeAccess);
		declaration.setName(iterableName);
		declaration.setOriginalCompilationUnit(parent
				.getOriginalCompilationUnit());
		createConstructorDeclaration.getParameters().add(declaration);

		// adding body to constructor
		createConstructorDeclaration.setBody(ParallelJModelHelper
				.getVariableAssignmentBlock(iterableName));

		GeneratedAnnotationAdder.addGenerated(classDeclaration, "//J", false,
				false);

		context.put(pipeline, "self", classDeclaration);
	}
}
