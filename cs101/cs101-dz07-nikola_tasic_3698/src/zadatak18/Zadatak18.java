package zadatak18;

import java.util.Scanner;

public class Zadatak18 {
	public static void main(String[] args) {
		new Zadatak18();
	}

	public Zadatak18() {
		Scanner input = new Scanner(System.in);
		System.out.printf("%s", "Enter a year: ");
		int year = input.nextInt();
		if (isLeap(year)) System.out.printf("%s", "Year is leap.");
		else System.out.printf("%s", "Year is not leap.");
		input.close();
	}

	public static boolean isLeap(int year) {
		return (year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0);
	}
}
