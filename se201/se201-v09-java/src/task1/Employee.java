package task1;

abstract public class Employee {
	private String firstName;
	private String lastName;
	private String jmbg;
	private double wage;

	public Employee() {
	}

	public Employee(String firstName, String lastName, String jmbg, double wage) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.jmbg = jmbg;
		this.wage = wage;
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

	public double getWage() {
		return wage;
	}

	public void setWage(double wage) {
		this.wage = wage;
	}
}
