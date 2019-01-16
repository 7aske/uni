package zadatak08;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Zadatak08 {
	public static void main(String[] args) throws InvalidOperatorException {
		new Calculator();
	}

}

class Calculator {
	private List<String> operations = Arrays.asList("+", "-", "*", "/");
	private String operation;
	private double operand1;
	private double operand2;

	Calculator() throws InvalidOperatorException {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter an operation (+ -  *  /): ");
		operation = in.next();
		if (!operations.contains(operation)) {
			throw new InvalidOperatorException();
		}
		System.out.print("Enter number 1: ");
		try {
			operand1 = in.nextDouble();
		} catch (InputMismatchException e) {
			throw new InputMismatchException();
		}
		System.out.print("Enter number 2: ");
		try {
			operand2 = in.nextDouble();
		} catch (InputMismatchException e) {
			throw new InputMismatchException();
		}
		calculate();
	}

	private void calculate() {
		switch (operation) {
			case "+":
				System.out.println(operand1 + operand2);
				break;
			case "-":
				System.out.println(operand1 - operand2);
				break;
			case "*":
				System.out.println(operand1 * operand2);
				break;
			case "/":
				try {
					System.out.println(operand1 / operand2);
				} catch (ArithmeticException e) {
					System.out.println("Division with zero.");
				}
				break;

		}
	}
}


class InvalidOperatorException extends Exception {
	InvalidOperatorException() {
		System.out.println("Invalid operator");
	}
}