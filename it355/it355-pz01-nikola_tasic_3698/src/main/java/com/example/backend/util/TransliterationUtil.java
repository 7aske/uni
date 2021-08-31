package com.example.backend.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransliterationUtil {
	private static final String[] cyrillic = {"А", "а", "Б", "б", "В", "в", "Г", "г", "Д", "д", "Ђ", "ђ", "Е", "е", "Ж", "ж", "З", "з", "И", "и", "Ј", "ј", "К", "к", "Л", "л", "Љ", "љ", "М", "м", "Н", "н", "Њ", "њ", "О", "о", "П", "п", "Р", "р", "С", "с", "Т", "т", "Ћ", "ћ", "У", "у", "Ф", "ф", "Х", "х", "Ц", "ц", "Ч", "ч", "Џ", "џ", "Ш", "ш"};
	private static final String[] latin = {"A", "a", "B", "b", "V", "v", "G", "g", "D", "d", "Đ", "đ", "E", "e", "Ž", "ž", "Z", "z", "I", "i", "J", "j", "K", "k", "L", "l", "Lj", "lj", "M", "m", "N", "n", "Nj", "nj", "O", "o", "P", "p", "R", "r", "S", "s", "T", "t", "Ć", "ć", "U", "u", "F", "f", "H", "h", "C", "c", "Č", "č", "Dž", "dž", "Š", "š"};

	private static final Map<String, String> cyrillicToLatinMapping = MapUtils.zip(cyrillic, latin);
	private static final Map<String, String> latinToCyrillicMapping = MapUtils.zip(latin, cyrillic);

	public static String cyrillicToLatin(String cyrillicInput) {
		StringBuilder latin = new StringBuilder();
		for (int i = 0; i < cyrillicInput.length(); i++) {
			latin.append(convertChar(cyrillicInput.charAt(i), cyrillicToLatinMapping));
		}
		return latin.toString();
	}

	public static String latinToCyrillic(String latinInput) {
		StringBuilder cyrillic = new StringBuilder();
		for (int i = 0; i < latinInput.length(); i++) {
			char currChar = latinInput.charAt(i);
			char nextChar = i + 1 < latinInput.length() ? latinInput.charAt(i + 1) : 0;

			if ((currChar == 'N') && (nextChar == 'J' || nextChar == 'j')) {
				cyrillic.append('\u040A'); // Nj
				i++;
			} else if (currChar == 'n' && (nextChar == 'J' || nextChar == 'j')) {
				cyrillic.append('\u045A'); // nj
				i++;
			} else if ((currChar == 'L') && (nextChar == 'J' || nextChar == 'j')) {
				cyrillic.append('\u0409'); // Lj
				i++;
			} else if ((currChar == 'l') && (nextChar == 'J' || nextChar == 'j')) {
				cyrillic.append('\u0459'); // lj
				i++;
			} else if ((currChar == 'D') && (nextChar == '\u017D' || nextChar == '\u017E')) {
				cyrillic.append('\u040F'); // Dz
				i++;
			} else if ((currChar == 'd') && (nextChar == '\u017D' || nextChar == '\u017E')) {
				cyrillic.append('\u045F'); // dz
				i++;
			} else {
				cyrillic.append(convertChar(currChar, latinToCyrillicMapping));
			}

		}
		return cyrillic.toString();
	}

	private static String convertChar(Character character, Map<String, String> characterMapping) {
		String str = String.valueOf(character);
		return characterMapping.getOrDefault(str, str);
	}
}