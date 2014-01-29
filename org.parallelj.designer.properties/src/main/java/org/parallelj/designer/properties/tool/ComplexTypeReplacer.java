package org.parallelj.designer.properties.tool;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;

public class ComplexTypeReplacer {
	/**
	 * replaces the simple java generic types with the fully qualified named
	 * types
	 * 
	 * 
	 * @param type
	 *            : simple generic type
	 * @param javaProject
	 *            : selected project
	 * @return String after replacing the simple type with fully qualified named
	 *         types.
	 */
	public String replaceWithFQNs(String type, IJavaProject javaProject) {
		final String LT = "<";
		final String GT = ">";
		final String COMMA = ",";
		final String QM = "\\?";
		final String EXTENDS = "extends";
		final String SUPER = "super";
		final String DOT = ".";
		final String TEMP_VALUE = "NA";

		String newType = type.trim();

		String[] regexValues = { LT, COMMA, GT, EXTENDS, SUPER, "?" };

		// regular expression for the generic string "type"
		String regex = LT + "|" + COMMA + "|" + GT + "|" + EXTENDS + "|"
				+ SUPER + "|" + QM;
		Pattern pattern = Pattern.compile(regex);

		// add the elements to string array after splitting the type
		String[] elms = pattern.split(newType);

		// remove the empty or null values and add the elements to array list
		List<String> elements = Arrays.asList(elms);

		// tree map to store the element and its index position from the "type",
		// it will store strings as well as the regular expression values.
		Map<Integer, String> map = new TreeMap<Integer, String>();

		int fromIndex = 0;

		for (String elm : elements) {
			if (!elm.isEmpty() && (elm != null)) {
				fromIndex = newType.indexOf(elm, fromIndex);
				map.put(fromIndex, elm.trim());
				fromIndex = fromIndex + elm.length();
			}
		}

		Matcher matcher = pattern.matcher(newType);

		// check all occurance of the regular expression values and store in
		// treemap
		while (matcher.find()) {
			if (!matcher.group().isEmpty() && (matcher.group() != null)) {
				map.put(matcher.start(), matcher.group());
			}
		}

		Iterator<Map.Entry<Integer, String>> iterator = map.entrySet()
				.iterator();

		StringBuilder sb = new StringBuilder();

		while (iterator.hasNext()) {
			Map.Entry<Integer, String> entry = iterator.next();
			String value = entry.getValue().trim();

			boolean regexType = false;

			for (int i = 0; i < regexValues.length; i++) {
				regexType = regexValues[i].equals(value);
			}

			if (!regexType && !(value.indexOf(DOT) > -1)) {
				try {
					String package1 = null;
					package1 = TypePackageCompletion.getPackage(value,
							javaProject);

					package1 = (package1 == null ? TEMP_VALUE : package1);

					if (!package1.equals(TEMP_VALUE)) {
						sb = sb.append(package1);
					} else {
						sb = sb.append(value);
					}
				} catch (JavaModelException e) {
					e.printStackTrace();
				}
			} else {
				sb = sb.append(value);
			}
		}

		newType = sb.toString();

		return newType;
	}
}
