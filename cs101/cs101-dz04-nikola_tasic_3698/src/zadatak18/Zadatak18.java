package zadatak18;

import javax.swing.*;

public class Zadatak18 {
	public static void main(String[] args) {
		new Zadatak18();
	}

	private Zadatak18() {
		String input = JOptionPane.showInputDialog(null, "Enter a string:");
		int lastCharIndex = input.length() - 1;
		char lastChar = input.charAt(lastCharIndex);
		if (input.length() % 2 == 1 && ((int)lastChar > 48 && (int)lastChar < 54)) {
			switch (lastChar) {
				case 49:
					input = input.substring(0, lastCharIndex) + "jedan";
					break;
				case 50:
					input = input.substring(0, lastCharIndex) + "dva";
					break;
				case 51:
					input = input.substring(0, lastCharIndex) + "tri";
					break;
				case 52:
					input = input.substring(0, lastCharIndex) + "cetiri";
					break;
				case 53:
					input = input.substring(0, lastCharIndex) + "pet";
					break;
			}
		}
		JOptionPane.showMessageDialog(null, input);
		//Sometimes when using JOptionPane program doesn't exit after closing the window;
		System.exit(0);
	}
}
