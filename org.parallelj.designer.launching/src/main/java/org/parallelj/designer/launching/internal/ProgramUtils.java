package org.parallelj.designer.launching.internal;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.IAnnotation;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.SourceType;
import org.parallelj.designer.launching.ConfigurationConstants;

@SuppressWarnings("restriction")
public class ProgramUtils {

	public static Map<Object, Object> getProgramParameters(String project,
			String mainJavaType, Map<Object, Object> parametersFromConfig) {
		Map<Object, Object> input = new HashMap<Object, Object>();
		IType mainType = null;
		try {
			mainType = JavaCore
					.create(ResourcesPlugin.getWorkspace().getRoot())
					.getJavaProject(project).findType(mainJavaType);
			String[] compilationUnitSource = mainType.getCompilationUnit()
					.getSource().split(";");
			if (mainType instanceof SourceType) {
				IField[] fields = mainType.getFields();
				for (IField iField : fields) {
					if (fieldContainsAnnotation(compilationUnitSource, iField,
							ConfigurationConstants.PARALLELJ_IN_ANNOTATION)) {
						if (!input.containsKey(iField.getElementName())) {
							input.put(iField.getElementName(), "");
						}
					}
				}
			}
		} catch (JavaModelException e1) {
		}

		if (parametersFromConfig != null) {
			Object[] keys = parametersFromConfig.keySet().toArray();
			for (Object obj : keys) {
				if (!input.containsKey(obj)) {
					parametersFromConfig.remove(obj);
				}
			}
			input.putAll(parametersFromConfig);
		}

		return input;
	}

	public static boolean fieldContainsAnnotation(
			String[] compilationUnitSource, IField iField,
			String fullAnnotationName) throws JavaModelException {
		IAnnotation[] annotations = iField.getAnnotations();
		String[] annotationParts = fullAnnotationName.split("\\.");
		for (IAnnotation iAnnotation : annotations) {
			if (iAnnotation.getElementName().endsWith(fullAnnotationName)) {
				return true;
			}
			if (iAnnotation.getElementName().endsWith(
					annotationParts[annotationParts.length - 1])) {
				for (String string : compilationUnitSource) {
					if (string.trim().startsWith("import")
							&& string.replaceAll("\\s", "").contains(
									fullAnnotationName)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean typeContainsAnnotation(IType itype,
			String fullAnnotationName) throws JavaModelException {
		String[] compilationUnitSource = itype.getCompilationUnit()
				.getSource().split(";");
		IAnnotation[] annotations = itype.getAnnotations();
		String[] annotationParts = fullAnnotationName.split("\\.");
		for (IAnnotation iAnnotation : annotations) {
			if (iAnnotation.getElementName().endsWith(fullAnnotationName)) {
				return true;
			}
			if (iAnnotation.getElementName().endsWith(
					annotationParts[annotationParts.length - 1])) {
				for (String string : compilationUnitSource) {
					if (string.trim().startsWith("import")
							&& string.replaceAll("\\s", "").contains(
									fullAnnotationName)) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
