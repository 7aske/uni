package hotel;

public class Person {
	private String firstName;
	private String lastName;
	private String jmbg;

	public Person(String firstName, String lastName, String jmbg) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.jmbg = jmbg;
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

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
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
