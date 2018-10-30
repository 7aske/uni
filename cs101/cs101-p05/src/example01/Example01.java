package example01;

import java.util.Scanner;

public class Example01 {
	public static void main(String[] args) {
		new Example01();
	}

	private Example01() {
		int a = new Scanner(System.in).nextInt();
		int i = 0;
		while (i < a) {
			i++;
			System.out.printf("( ͡° ʖ̯ ͡°) %d\n", i);
		}
	}

}
