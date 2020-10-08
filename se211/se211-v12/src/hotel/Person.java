package hotel;

import hotel.exception.InvalidJmbgException;

public class Person {
	private String firstName;
	private String lastName;
	private String jmbg;

	public Person(String firstName, String lastName, String jmbg) throws InvalidJmbgException {
		this.firstName = firstName;
		this.lastName = lastName;
		setJmbg(jmbg);
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

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) throws InvalidJmbgException {
		if (!isJmbgValid(jmbg)) {
			throw new InvalidJmbgException(String.format("%s is not a valid JMBG", jmbg));
		}
		this.jmbg = jmbg;
	}

	private static boolean isJmbgValid(String jmbg) {
		if (jmbg.length() != 13) {
			return false;
		}
		// More implementation
		return true;
	}

	@Override
	public String toString() {
		return "Person{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", jmbg='" + jmbg + '\'' +
				'}';
	}
}
