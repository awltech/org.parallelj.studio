package org.parallelj.designer.validation.tools;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.ITypeParameter;
import org.eclipse.jdt.core.JavaModelException;
import org.parallelj.designer.validation.DiagramValidationPlugin;

/**
 * Utility class for parsing Java Type from String, and to propose validation
 * method on it.
 * 
 * @author A125788
 * 
 */
public class JavaTypeValidator {

	/**
	 * Name of the type
	 */
	private String name;

	/**
	 * List of Type parameters contained in this type.
	 */
	private final List<JavaTypeValidator> parameters = new ArrayList<JavaTypeValidator>();

	/**
	 * Static list of Java Primitive Types.
	 */
	private static final String[] PRIMITIVE_TYPES = new String[] { "int",
			"long", "short", "double", "float", "char", "byte", "boolean" };

	/**
	 * Creates a new Java Type descriptot from the string provided as parameter
	 * 
	 * @param typeAsString
	 *            type pattern
	 * @throws IllegalStateException
	 *             if there is any syntax error in the type pattern.
	 */
	public JavaTypeValidator(String typeAsString)
			throws IllegalStateException {
		String cleanTypeAsString = typeAsString.trim();

		int[] ltIndices = getIndices(cleanTypeAsString, "<");
		int[] gtIndices = getIndices(cleanTypeAsString, ">");
		int[] commaIndices = getIndices(cleanTypeAsString, ",");

		if (ltIndices.length != gtIndices.length)
			throw new IllegalStateException("Malformed Type: "
					+ cleanTypeAsString);

		if (ltIndices.length == 0) {
			this.name = cleanTypeAsString;
		} else {
			this.name = cleanTypeAsString.substring(0, ltIndices[0]);
			List<Integer> firstLevelCommaIndices = new ArrayList<Integer>();
			firstLevelCommaIndices.add(ltIndices[0]);

			for (int i = 0; i < commaIndices.length; i++) {
				int ltInf = 0;
				for (int j = 0; j < ltIndices.length; j++) {
					if (ltIndices[j] < commaIndices[i])
						ltInf++;
				}
				int gtSup = 0;
				for (int j = 0; j < gtIndices.length; j++) {
					if (gtIndices[j] < commaIndices[i])
						gtSup++;
				}
				if (ltInf - gtSup == 1) {
					firstLevelCommaIndices.add(commaIndices[i]);
				}
			}
			firstLevelCommaIndices.add(cleanTypeAsString.length() - 1);

			for (int i = 0; i < firstLevelCommaIndices.size() - 1; i++) {
				this.parameters.add(new JavaTypeValidator(
						cleanTypeAsString.substring(
								firstLevelCommaIndices.get(i) + 1,
								firstLevelCommaIndices.get(i + 1))));
			}
		}
	}

	private int[] getIndices(String cleanTypeAsString, String pattern) {
		List<Integer> indices = new ArrayList<Integer>();

		int trigger = -1;
		while (cleanTypeAsString.indexOf(pattern, trigger + 1) > -1) {
			trigger = cleanTypeAsString.indexOf(pattern, trigger + 1);
			indices.add(trigger);
		}

		int[] indicesArray = new int[indices.size()];
		for (int i = 0; i < indicesArray.length; i++) {
			indicesArray[i] = indices.get(i);
		}

		return indicesArray;
	}

	public String getName() {
		return name;
	}

	public List<JavaTypeValidator> getTypeParameters() {
		return parameters;
	}

	public IStatus validate(IJavaProject javaProject) throws JavaModelException {
		return this.validate(javaProject, null, false);
	}

	private IStatus validate(IJavaProject javaProject, IType parentType,
			boolean isTypeParameter) throws JavaModelException {

		if (isPrimitiveType()) {
			if (!isTypeParameter) {
				return Status.OK_STATUS;
			} else {
				return new Status(IStatus.ERROR,
						DiagramValidationPlugin.PLUGIN_ID, "The primitive <"
								+ name
								+ "> type cannot be used as Type Parameter.");
			}
		}

		// Support of ? super
		if (name.indexOf(" extends ") > -1) {
			if (isTypeParameter) {
				MultiStatus multiStatus = new MultiStatus(
						DiagramValidationPlugin.PLUGIN_ID, IStatus.OK, "", null);
				String extension = name.substring(name.indexOf(" extends ")
						+ " extends ".length());
				String[] extensionChunks = extension.split("&");
				for (String extensionChunk : extensionChunks) {
					IStatus status = new JavaTypeValidator(
							extensionChunk).validate(javaProject, parentType,
							isTypeParameter);
					multiStatus.add(status);
					if (status.getSeverity() == IStatus.OK)
						return Status.OK_STATUS;
				}
				return new Status(IStatus.ERROR,
						DiagramValidationPlugin.PLUGIN_ID,
						"None of the extensions of type " + name
								+ " match the classpath");
			} else {
				return new Status(IStatus.ERROR,
						DiagramValidationPlugin.PLUGIN_ID,
						"Type parameter erasure cannot be used to define a type");
			}
		}

		if (name.indexOf(" super ") > -1) {
			if (isTypeParameter) {
				MultiStatus multiStatus = new MultiStatus(
						DiagramValidationPlugin.PLUGIN_ID, IStatus.OK, "", null);
				String extension = name.substring(name.indexOf(" super ")
						+ " super".length());
				String[] extensionChunks = extension.split("&");
				for (String extensionChunk : extensionChunks) {
					IStatus status = new JavaTypeValidator(
							extensionChunk).validate(javaProject, parentType,
							isTypeParameter);
					multiStatus.add(status);
					if (status.getSeverity() == IStatus.OK)
						return Status.OK_STATUS;
				}
				return new Status(IStatus.ERROR,
						DiagramValidationPlugin.PLUGIN_ID,
						"None of the extensions of type " + name
								+ " match the classpath");
			} else {
				return new Status(IStatus.ERROR,
						DiagramValidationPlugin.PLUGIN_ID,
						"Type parameter erasure cannot be used to define a type");
			}
		}

		IType effectiveType = javaProject.findType(name.trim());
		if (effectiveType == null)
			return new Status(IStatus.ERROR, DiagramValidationPlugin.PLUGIN_ID,
					"Type with name: " + name
							+ " could not be found in the project classpath.");

		if (parentType != null) {
			ITypeHierarchy newSupertypeHierarchy = effectiveType
					.newSupertypeHierarchy(new NullProgressMonitor());
			IType[] allTypes = newSupertypeHierarchy.getAllTypes();
			boolean foundSuperType = false;
			for (int j = 0; j < allTypes.length && !foundSuperType; j++) {
				IType superType = allTypes[j];
				if (superType.getFullyQualifiedName().equals(
						parentType.getFullyQualifiedName())) {
					foundSuperType = true;
				}
			}
			if (!foundSuperType)
				return new Status(IStatus.ERROR,
						DiagramValidationPlugin.PLUGIN_ID, "Type "
								+ effectiveType.getElementName()
								+ " could not be cast to the following type "
								+ parentType.getElementName());
		}

		/*
		 * if (parameters.size() == 0) return Status.OK_STATUS;
		 */
		ITypeParameter[] typeParameters = effectiveType.getTypeParameters();

		if (parameters.size() != typeParameters.length) {
			return new Status(IStatus.ERROR, DiagramValidationPlugin.PLUGIN_ID,
					"Wrong number of Type parameters. Expected "
							+ typeParameters.length + " and got "
							+ parameters.size());
		}

		for (int i = 0; i < parameters.size(); i++) {
			ITypeParameter typeParameter = typeParameters[i];
			for (String bound : typeParameter.getBounds()) {
				IType boundType = javaProject.findType(bound.trim());
				if (boundType == null) {
					return new Status(IStatus.ERROR,
							DiagramValidationPlugin.PLUGIN_ID,
							"Could not resolve bound type <" + bound
									+ "> from classpath.");
				}
				IStatus validatedType = parameters.get(i).validate(javaProject,
						boundType, true);
				if (validatedType.getSeverity() != IStatus.OK)
					return validatedType;
			}
		}
		return Status.OK_STATUS;

	}

	private boolean isPrimitiveType() {
		for (String primitiveType : PRIMITIVE_TYPES)
			if (primitiveType.equals(this.name))
				return true;
		return false;
	}

}
