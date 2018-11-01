package example02;

import java.util.Scanner;

public class Example02 {
	public static void main(String[] args) {
		new Example02();
	}

	public Example02() {
		Scanner input = new Scanner(System.in);
		double price;
		do {
			System.out.print("Enter price: ");
			price = input.nextDouble();
			if (price > 0)
				System.out.printf("Price with taxes: %.2f$\n", price * 1.2);
			else if (price < 0 )
				System.out.print("Price can't be negative\n");

		} while (price != 0);
		input.close();

	}
}
