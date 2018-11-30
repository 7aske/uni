package grupa2;

import java.util.Scanner;

public class Zadatak08 {
	public static void main(String[] args){
		new Zadatak08();
	}

	public Zadatak08() {
		Scanner input = new Scanner(System.in);
		int x = 0, y = 0;
		System.out.printf("x length: ");
		while (!input.hasNextInt()) {
			System.out.printf("x length: ");
			input.nextLine();
		}
		x = input.nextInt();

		System.out.printf("y length: ");
		while (!input.hasNextInt()) {
			System.out.printf("y length: ");
			input.nextLine();
		}

		y = input.nextInt();

		int[][] m = new int[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				m[i][j] = (int) (Math.random() * 89 + 10);
			}
		}
		printMatrix(m);
		System.out.printf("Diagonal sum: %d", diagonalSum(m));
	}
	public static void printMatrix(int[][] m){
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				System.out.printf("[%d]", m[i][j]);
			}
			System.out.printf("\n");
		}
	}

	public static int diagonalSum(int[][] m){
		int sum = 0;
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				if(i == j) sum += m[i][j] * 5;
			}
		}
		return sum;
	}
}
