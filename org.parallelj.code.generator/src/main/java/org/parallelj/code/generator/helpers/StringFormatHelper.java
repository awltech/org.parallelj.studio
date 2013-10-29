package org.parallelj.code.generator.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This util class is used for formating string
 * 
 * @author Abhijit
 * 
 */
public class StringFormatHelper {

	/**
	 * This method will return passed parameter string in CamelCase.
	 * 
	 * @param lowerCaseAndUnderscoredWord
	 * @param uppercaseFirstLetter
	 * @param delimiterChars
	 * @return
	 */
	public static String camelCase(String lowerCaseAndUnderscoredWord,
			boolean uppercaseFirstLetter, char... delimiterChars) {

		if (lowerCaseAndUnderscoredWord == null) {
			return null;
		}

		lowerCaseAndUnderscoredWord = lowerCaseAndUnderscoredWord.trim();

		if (lowerCaseAndUnderscoredWord.length() == 0) {
			return "";
		}

		if (uppercaseFirstLetter) {
			String result = lowerCaseAndUnderscoredWord;

			// Replace any extra delimiters with underscores (before the
			// underscores are converted in the next step)...

			if (delimiterChars != null) {
				for (char delimiterChar : delimiterChars) {
					result = result.replace(delimiterChar, '_');
				}
			}

			// Change the case at the beginning at after each underscore ...
			return replaceAllWithUppercase(result, "(^|_)(.)", 2);
		}

		if (lowerCaseAndUnderscoredWord.length() < 2) {
			return lowerCaseAndUnderscoredWord;
		}

		return ""
				+ Character.toLowerCase(lowerCaseAndUnderscoredWord.charAt(0))
				+ camelCase(lowerCaseAndUnderscoredWord, true, delimiterChars)
						.substring(1);

	}

	protected static String replaceAllWithUppercase(String input, String regex,
			int groupNumberToUppercase) {
		Pattern underscoreAndDotPattern = Pattern.compile(regex);
		Matcher matcher = underscoreAndDotPattern.matcher(input);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(groupNumberToUppercase)
					.toUpperCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

}
