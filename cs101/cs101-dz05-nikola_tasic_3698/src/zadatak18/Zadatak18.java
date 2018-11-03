package zadatak18;

import java.util.Scanner;

public class Zadatak18 {
	public static void main(String[] args) {
		new Zadatak18();
	}

	private Zadatak18() {
		Scanner input = new Scanner(System.in);
		int n;

		System.out.printf("n = ");
		n = input.nextInt();
		System.out.printf("\n");

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// uslovi redom predstavljaju vertikalnu, horizontalnu i dijagonalnu liniju
				if (j == 0 || i == n - 1 || j == i) {
					System.out.printf("*");
				} else {
					System.out.printf(" ");
				}
			}
			System.out.printf("\n");
		}
		input.close();
	}
}
