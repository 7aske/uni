package zadatak02;

import java.util.Scanner;

public class Zadatak02 {
	public static void main (String[] args){
		new Zadatak02();
	}
	private Zadatak02(){
		Scanner input  = new Scanner(System.in);
		double r = 1 , x;
		int n;

		System.out.printf("Enter a namber you want to calulate the power of: ");
		x = input.nextDouble();
		System.out.printf("Enter the power(integer): ");
		n = input.nextInt();

		for (int i = 0; i < Math.abs(n); i++){
			r *= x;
		}

		System.out.printf("Result is %f", r);
		input.close();
	}
}
