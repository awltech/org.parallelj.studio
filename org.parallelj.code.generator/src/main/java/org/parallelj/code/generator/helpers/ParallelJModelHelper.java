package org.parallelj.code.generator.helpers;

import java.util.concurrent.Callable;

import net.atos.optimus.m2m.javaxmi.core.javadoc.JavadocHelper;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.Assignment;
import org.eclipse.gmt.modisco.java.AssignmentKind;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.ExpressionStatement;
import org.eclipse.gmt.modisco.java.FieldAccess;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.ReturnStatement;
import org.eclipse.gmt.modisco.java.SingleVariableAccess;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.ThisExpression;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.VisibilityKind;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;
import org.parallelj.code.generator.core.Messages;
import org.parallelj.model.Element;
import org.parallelj.model.InputCondition;
import org.parallelj.model.JoinType;
import org.parallelj.model.Link;
import org.parallelj.model.Pipeline;
import org.parallelj.model.Procedure;
import org.parallelj.model.SplitType;

/**
 * This class contains some helpers used while working with ParallelJ models
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class ParallelJModelHelper {

	/**
	 * This method allows to know if a procedure is the first one of a diagram
	 * 
	 * @param procedure
	 *            a procedure to analyze
	 * @return true if the provided procedure is the first one of the diagram
	 */
	public static boolean isFirstProcedure(Procedure procedure) {
		for (Link link : procedure.getInputLinks()) {
			if (link.eContainer() instanceof InputCondition)
				return true;
		}
		return false;
	}

	/**
	 * This method allows to know if a procedure is the first one of a pipeline
	 * 
	 * @param eObject
	 *            a procedure to analyze
	 * @return true if the provided procedure is the first one of the pipeline
	 */
	public static boolean isFirstPipelineProcedure(EObject eObject) {

		Pipeline pipeline = (Pipeline) eObject.eContainer();
		EList<Procedure> procedures = pipeline.getProcedures();

		return procedures.indexOf((Procedure) eObject) == 0 ? true : false;
	}

	/**
	 * This method allows get next procedure name in pipeline
	 * 
	 * @param eObject
	 *            a procedure to analyze
	 * @return next procedure name in pipeline
	 */
	public static String getNextPipelineProcedure(EObject eObject) {

		Pipeline pipeline = (Pipeline) eObject.eContainer();
		EList<Procedure> procedures = pipeline.getProcedures();

		int nextIndex = procedures.indexOf((Procedure) eObject) + 1;

		if (procedures.size() <= nextIndex) {
			return "pipelineend";
		} else {
			return getConditionName(procedures.get(nextIndex));
		}
	}

	/**
	 * This method allows to know if a procedure has an AndJoin
	 * 
	 * @param procedure
	 *            a procedure to analyze
	 * @return true if the join of the procedure is an AndJoin
	 */
	public static boolean hasAndJoin(Procedure procedure) {
		return procedure.getJoin() == JoinType.AND;
	}

	/**
	 * This method allows to know if a procedure has an OrJoin
	 * 
	 * @param procedure
	 *            a procedure to analyze
	 * @return true if the join of the procedure is an OrJoin
	 */
	public static boolean hasOrJoin(Procedure procedure) {
		return procedure.getJoin() == JoinType.OR;
	}

	/**
	 * This method allows to know if a procedure has an XorJoin
	 * 
	 * @param procedure
	 *            a procedure to analyze
	 * @return true if the join of the procedure is an XorJoin
	 */
	public static boolean hasXorJoin(Procedure procedure) {
		return procedure.getJoin() == JoinType.XOR;
	}

	/**
	 * This method allows to know if a procedure has an AndSplit
	 * 
	 * @param procedure
	 *            a procedure to analyze
	 * @return true if the join of the procedure is an AndSplit
	 */
	public static boolean hasAndSplit(Procedure procedure) {
		return procedure.getSplit() == SplitType.AND;
	}

	/**
	 * This method allows to know if a procedure has an OrSplit
	 * 
	 * @param procedure
	 *            a procedure to analyze
	 * @return true if the join of the procedure is an OrSplit
	 */
	public static boolean hasOrSplit(Procedure procedure) {
		return procedure.getSplit() == SplitType.OR;
	}

	/**
	 * This method allows to know if a procedure has an XorSplit
	 * 
	 * @param procedure
	 *            a procedure to analyze
	 * @return true if the join of the procedure is an XorSplit
	 */
	public static boolean hasXorSplit(Procedure procedure) {
		return procedure.getSplit() == SplitType.XOR;
	}

	/**
	 * This procedure allows to get the properly formatted name of a Condition
	 * object
	 * 
	 * @param element
	 *            an element we want the name of
	 * @return the properly formatted name of the provided element
	 */
	public static String getConditionName(Element element) {
		return element.getName().substring(0, 1).toLowerCase()
				+ element.getName().substring(1);
	}

	/**
	 * This method allows to know if the provided object is an executable
	 * 
	 * @param eObject
	 *            the executable to analyze
	 * @return true if the provided object is an executable
	 */
	public static boolean isExecutable(EObject eObject) {
		return eObject instanceof Callable
				|| eObject instanceof Runnable
				|| (eObject instanceof Procedure
						&& ((Procedure) eObject).getExecutable() != null && ((Procedure) eObject)
						.getExecutable().length() > 0);
	}

	public static boolean isPipelineProcedure(EObject eObject) {

		return eObject.eContainer() instanceof Pipeline;

	}

	/**
	 * This method will return getter method for variable.
	 * 
	 * @param variableName
	 *            : data name
	 * @param variableType
	 *            : data type
	 * @return getter method
	 */
	public static MethodDeclaration generateGetter(String variableName,
			String variableType) {

		// getter method name in camelcase
		String name = StringFormatHelper
				.camelCase("get_" + variableName, false);

		// create the Modisco method declaration for getter method
		MethodDeclaration newGetterMethodDeclaration = JavaFactory.eINSTANCE
				.createMethodDeclaration();

		// setting modifier for getter method
		Modifier getterMethodModifier = JavaFactory.eINSTANCE.createModifier();
		getterMethodModifier.setVisibility(VisibilityKind.PUBLIC);
		newGetterMethodDeclaration.setModifier(getterMethodModifier);

		// setting name for method
		newGetterMethodDeclaration.setName(name);

		// setting return type for method
		TypeAccess getterReturnType = ParallelJModelHelper
				.getVariableDataType(variableType);
		newGetterMethodDeclaration.setReturnType(getterReturnType);

		// adding body for method
		Block getterBlock = JavaFactory.eINSTANCE.createBlock();

		// getting variable with 'this' expression
		// i.e. this.<name of variable>
		FieldAccess getterFieldAccess = ParallelJModelHelper
				.getVariableWithThis(variableName);

		// adding return statement to method body
		ReturnStatement getterReturnStatement = JavaFactory.eINSTANCE
				.createReturnStatement();
		getterReturnStatement.setExpression(getterFieldAccess);
		getterBlock.getStatements().add(getterReturnStatement);

		// adding body to method
		newGetterMethodDeclaration.setBody(getterBlock);

		JavadocHelper.addJavadoc(newGetterMethodDeclaration,
				Messages.JAVADOC_DATA_GETTER_METHOD.message(variableName,
						variableType));

		// finally returning getter method
		return newGetterMethodDeclaration;
	}

	/**
	 * This method will return setter method for variable.
	 * 
	 * @param variableName
	 *            : data name
	 * @param variableType
	 *            : data type
	 * @return setter method
	 * 
	 */
	public static MethodDeclaration generateSetter(String variableName,
			String variableType) {

		// setter method name in camelcase
		String name = StringFormatHelper
				.camelCase("set_" + variableName, false);

		// create the Modisco method declaration for setter method
		MethodDeclaration newSetterMethodDeclaration = JavaFactory.eINSTANCE
				.createMethodDeclaration();

		// setting modifier for getter method
		Modifier setterMethodModifier = JavaFactory.eINSTANCE.createModifier();
		setterMethodModifier.setVisibility(VisibilityKind.PUBLIC);
		newSetterMethodDeclaration.setModifier(setterMethodModifier);

		// setting name for method
		newSetterMethodDeclaration.setName(name);

		// creating parameter name
		String parameterName = StringFormatHelper
				.camelCase(variableName, false);

		// creating parameter for setting method
		SingleVariableDeclaration paramSingleVariableDeclaration = JavaFactory.eINSTANCE
				.createSingleVariableDeclaration();
		paramSingleVariableDeclaration.setName(parameterName);
		TypeAccess paramType = ParallelJModelHelper
				.getVariableDataType(variableType);
		paramSingleVariableDeclaration.setType(paramType);
		newSetterMethodDeclaration.getParameters().add(
				paramSingleVariableDeclaration);

		// adding body to setter method
		newSetterMethodDeclaration.setBody(ParallelJModelHelper
				.getVariableAssignmentBlock(variableName));

		JavadocHelper.addJavadoc(newSetterMethodDeclaration,
				Messages.JAVADOC_DATA_SETTER_METHOD.message(variableName,
						variableType));

		// finally returning setter method
		return newSetterMethodDeclaration;

		// finally setting up the parent for setter method as class
	}

	/**
	 * @param variableName
	 * @return expression block with left and right assignment e.g.
	 *         this.varaibleName = varaibleName
	 */
	public static Block getVariableAssignmentBlock(String variableName) {

		// preparing body block for setter method
		Block setterBlock = JavaFactory.eINSTANCE.createBlock();

		// creating assignment for expression
		Assignment setterAssignment = JavaFactory.eINSTANCE.createAssignment();
		// adding '=' operator
		setterAssignment.setOperator(AssignmentKind.ASSIGN);

		// building left side expression

		// getting variable with 'this' expression
		// i.e. this.<name of variable>
		FieldAccess setterFieldAccess = ParallelJModelHelper
				.getVariableWithThis(variableName);

		// adding left side expression to main expression
		setterAssignment.setLeftHandSide(setterFieldAccess);

		// finish left side expression

		// building right side expression

		SingleVariableDeclaration createSingleVariableDeclaration = JavaFactory.eINSTANCE
				.createSingleVariableDeclaration();
		createSingleVariableDeclaration.setName(StringFormatHelper.camelCase(
				variableName, false));

		SingleVariableAccess createSingleVariableAccess = JavaFactory.eINSTANCE
				.createSingleVariableAccess();
		createSingleVariableAccess.setVariable(createSingleVariableDeclaration);

		// adding right side expression to main expression
		setterAssignment.setRightHandSide(createSingleVariableAccess);

		// finish right side expression

		// creating the expression statement
		ExpressionStatement setterExpressionStatement = JavaFactory.eINSTANCE
				.createExpressionStatement();

		// adding expression to statement
		setterExpressionStatement.setExpression(setterAssignment);

		// adding statement to body
		setterBlock.getStatements().add(setterExpressionStatement);

		return setterBlock;

	}

	/**
	 * This method will return the data type for variable.
	 * 
	 * @param variableType
	 * @return
	 */
	public static TypeAccess getVariableDataType(String variableType) {

		org.eclipse.gmt.modisco.java.Type type = JavaFactory.eINSTANCE
				.createPrimitiveType();
		type.setName(variableType);

		TypeAccess typeAccess = JavaFactory.eINSTANCE.createTypeAccess();
		typeAccess.setType(type);

		return typeAccess;
	}

	/**
	 * This method will return variable with 'this' expression. i.e. this.<name
	 * of variable>
	 * 
	 * @param name
	 * @return
	 */
	public static FieldAccess getVariableWithThis(String name) {

		// creating variable declaration
		VariableDeclarationFragment variableDeclarationFragment = JavaFactory.eINSTANCE
				.createVariableDeclarationFragment();

		// adding name
		variableDeclarationFragment.setName(name);
		variableDeclarationFragment.setProxy(false);

		SingleVariableAccess singleVariableAccess = JavaFactory.eINSTANCE
				.createSingleVariableAccess();
		singleVariableAccess.setVariable(variableDeclarationFragment);

		FieldAccess fieldAccess = JavaFactory.eINSTANCE.createFieldAccess();
		fieldAccess.setField(singleVariableAccess);

		// creating 'this' expression
		ThisExpression thisExpression = JavaFactory.eINSTANCE
				.createThisExpression();
		fieldAccess.setExpression(thisExpression);

		return fieldAccess;
	}

}
