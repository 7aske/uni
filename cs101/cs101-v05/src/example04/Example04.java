package example04;

public class Example04 {
	public static void main(String[] args) {
		new Example04();
	}

	private Example04() {
		int n = 11;
		for (int i = 0; i < n / 2 + 1; i++) {
			for (int j = 0; j <= n; j++) {
				if (i + j == n / 2 || j - i == n / 2)
					System.out.printf("*");
				else
					System.out.printf(" ");
			}
			System.out.printf("\n");
		}
		for (int k = 0; k < n / 2; k++) {
			for (int l = 0; l < n; l++) {
				if (k == 0 || k == n / 2 - 1)
					System.out.printf("*");
				else if (l == 0 || l == n - 1)
					System.out.printf("*");
				else
					System.out.printf(" ");

			}
			System.out.printf("\n");
		}
	}
}
