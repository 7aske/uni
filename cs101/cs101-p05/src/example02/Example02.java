package example02;

import java.util.Scanner;

public class Example02 {
	public static void main(String[] args) {
		new Example02();
	}

	private Example02() {
		int s = new Scanner(System.in).nextInt();
		int e = new Scanner(System.in).nextInt();
		int sum = 0;
		for (int i = s; i <= e; i++) {
			sum += i;
		}
		System.out.println(sum);
	}
}
