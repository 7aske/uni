/***********************************************************************
 * Module:  Student.java
 * Author:  nik
 * Purpose: Defines the Class Student
 ***********************************************************************/

import java.util.*;

public class Student {
	private int index;
	private String firstName;
	private String lastName;

	public Student(String firstName, String lastName, int index) {
		this.index = index;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Student{" +
				"index=" + index +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				'}';
	}
}