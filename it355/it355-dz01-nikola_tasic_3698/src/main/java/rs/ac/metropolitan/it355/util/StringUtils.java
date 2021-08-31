package rs.ac.metropolitan.it355.util;

public class StringUtils {
	private StringUtils() {}

	public static String union(String str1, String str2) {
		for (int i = 0; i < str1.length(); i++) {
			String chunk = str1.substring(i);
			if (str2.startsWith(chunk)) {
				return str1.substring(0, i) + str2;
			}
		}
		return str1 + str2;
	}
}
