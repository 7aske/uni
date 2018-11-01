package example01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Example01 {
	public static void main(String[] args) {
		Scanner stInput = new Scanner(System.in);
		System.out.print("Enter number of students you want to input: ");
		new Example01(stInput.nextInt());
		stInput.close();
	}

	private Example01(int nOfStudents) {
		Scanner input = new Scanner(System.in);
		List<Student> students = new ArrayList<>();
		for (int i = 0; i < nOfStudents; i++) {
			System.out.print("First name: ");
			String fname = input.next();

			System.out.print("Last name: ");
			String lname = input.next();

			System.out.print("Grade: ");
			double grade = input.nextDouble();

			students.add(new Student(fname, lname, grade));
		}
		input.close();

		students.forEach(s -> System.out.printf("%s \n", s.getStudent()));
	}
}

class Student {
	protected String firstName;
	protected String lastName;
	protected double grade;

	public Student(String fname, String lname, double grad) {
		this.firstName = fname;
		this.lastName = lname;
		this.grade = grad;
	}

	public String getStudent() {
		return String.format("%s %s %.2f", this.firstName, this.lastName, this.grade);
	}
}
