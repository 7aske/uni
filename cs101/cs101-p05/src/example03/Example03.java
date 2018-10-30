package example03;

import java.util.Random;
import java.util.Scanner;

public class Example03 {
	public static void main(String[] args) {
		new Example03();
	}

	private Example03() {
		Scanner input = new Scanner(System.in);
		String ans;
		Random gen = new Random();
		int number = gen.nextInt(100) + 1, attempts, guess;
		while (true) {
			attempts = 3;
			do {
				if (attempts != 0) {
					System.out.printf("\nAttempts remaining: %d\n", attempts);
					System.out.print("Enter a number: ");
					guess = input.nextInt();
					if (guess == 0) {
						System.out.print("Exit\n");
						break;
					} else if (guess == number) {
						System.out.print("Good job\n");
						break;
					} else if (guess < number) {
						System.out.print("Wanted number is larger\n");
					} else {
						System.out.print("Wanted number is smaller\n");
					}
					attempts--;
				} else {
					System.out.printf("Out of attempts\nNumber was %d\n", number);
					break;
				}

			} while (true);
			System.out.print("Do you want to play again? ");
			ans = input.next();
			if (ans.equals(new String("yes"))) {
				continue;
			} else {
				break;
			}
		} ;

	}
}
