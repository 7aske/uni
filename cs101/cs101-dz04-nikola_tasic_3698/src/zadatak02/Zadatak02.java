package zadatak02;

import javax.swing.JOptionPane;

public class Zadatak02 {
	public static void main(String[] args) {
		new Zadatak02();
	}

	private Zadatak02() {
		double x = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter x:")) * 10;
		double y = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter y:")) * 15;
		double z = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter z:")) * 5;
		double n = (Math.sin(50 * x) + Math.cos(30 * y)) / (5 * x - Math.sqrt(10 * y) + Math.sin(19 * z));
		JOptionPane.showMessageDialog(null, String.format("<html><u>\uD835\uDC60\uD835\uDC56\uD835\uDC5B(50\uD835\uDC65) + cos(30\uD835\uDC66)</u></html>	\n" +
				"5\uD835\uDC65 − √10\uD835\uDC66 + sin(19\uD835\uDC67)\n" +
				"Result is %.2f", n));
		//Sometimes when using JOptionPane program doesn't exit after closing the window;
		System.exit(0);
	}
}
