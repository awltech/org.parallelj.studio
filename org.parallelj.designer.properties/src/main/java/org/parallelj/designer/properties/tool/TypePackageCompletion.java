package org.parallelj.designer.properties.tool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.core.search.TypeNameRequestor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

public class TypePackageCompletion {

	private static final String JAVA_UTIL = "java.util";
	private static final String JAVA_LANG = "java.lang";
	private static final String[] PRIMITIVE_TYPES = new String[] { "byte", "char", "long", "short", "int", "boolean",
			"float", "double", "void" };

	public static String[] getPackages(String type, IJavaProject javaProject) throws JavaModelException {

		// Validation, just in case...
		if (type == null || javaProject == null || !javaProject.exists())
			return new String[0];

		// At first we check the type is not a primitive.
		boolean isPrimitive = false;
		for (int i = 0; i < PRIMITIVE_TYPES.length && !isPrimitive; i++) {
			isPrimitive = PRIMITIVE_TYPES[i].equals(type);
		}
		if (isPrimitive)
			return new String[] { type };
		// new String[0];

		// Then, we look if there is already a package set...
		if (type.indexOf(".") > -1) {
			return new String[] { type };
		}

		// Now we use the Java Search Scope to get the matching type from the
		// classpath
		final Collection<String> packages = new ArrayList<String>();
		IJavaSearchScope createdJavaSearchScope = SearchEngine
				.createJavaSearchScope(new IJavaProject[] { javaProject });

		new SearchEngine().searchAllTypeNames(null, SearchPattern.R_PATTERN_MATCH, type.toCharArray(),
				SearchPattern.R_EXACT_MATCH, IJavaSearchConstants.TYPE, createdJavaSearchScope,
				new TypeNameRequestor() {
					@Override
					public void acceptType(int modifiers, char[] packageName, char[] simpleTypeName,
							char[][] enclosingTypeNames, String path) {
						packages.add(String.valueOf(packageName) + "." + String.valueOf(simpleTypeName));
					}
				}, IJavaSearchConstants.FORCE_IMMEDIATE_SEARCH, new NullProgressMonitor());
		return packages.toArray(new String[packages.size()]);
	}

	public static String getPackage(String type, IJavaProject javaProject) throws JavaModelException {
		String[] packages = getPackages(type, javaProject);
		if (packages.length == 0)
			return null;
		if (packages.length == 1)
			return packages[0];

		List<String> restrictedPackages = new ArrayList<String>();
		for (String pack : packages) {
			if (pack != null && (pack.startsWith(JAVA_LANG) || pack.startsWith(JAVA_UTIL))) {
				restrictedPackages.add(pack);
			}
		}

		if (restrictedPackages.size() == 1)
			return restrictedPackages.get(0);
		
		return openDialogForUserSelection(packages, type);
	}

	/**
	 * Opens a dialog box, for a parameter, that allows the user to select the
	 * wanted Type in list after a Search occurs
	 * 
	 * 
	 * @param pattern
	 *            : Initial pattern
	 * @param searchResults
	 *            : Results from Search
	 * @return type selected by User
	 */
	private static String openDialogForUserSelection(String[] results, String pattern) {

		final Shell shell = new Shell(Display.getDefault());
		final ILabelProvider labelProvider = new ILabelProvider() {

			@Override
			public Image getImage(Object element) {
				// TODO
				return null;
			}

			@Override
			public String getText(Object element) {
				return String.valueOf(element);
			}

			@Override
			public void addListener(ILabelProviderListener listener) {
			}

			@Override
			public void dispose() {
			}

			@Override
			public boolean isLabelProperty(Object element, String property) {
				return false;
			}

			@Override
			public void removeListener(ILabelProviderListener listener) {
			}

		};
		final ElementListSelectionDialog elementListSelectionDialog = new ElementListSelectionDialog(shell,
				labelProvider);
		elementListSelectionDialog.setElements(results);

		elementListSelectionDialog.setTitle("Type Selection Dialog");
		// elementListSelectionDialog.setImage(AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID,
		// "/icons/pattern_explorer_view.gif").createImage());
		StringBuffer message = new StringBuffer();
		message.append("Please select the type you would like to use for " + pattern);

		elementListSelectionDialog.setMessage(message.toString());
		elementListSelectionDialog.open();
		if (elementListSelectionDialog.getReturnCode() == Window.OK)
			return elementListSelectionDialog.getFirstResult() == null ? null : String
					.valueOf(elementListSelectionDialog.getFirstResult());
		else
			return null;

	}
}
