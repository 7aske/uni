package example04;

public class Main {
	public static void main(String[] args) {
		Student s1 = new Student("Nikola", "nikola@example.com",3698);
		Student s2 = new Student("Aleksandra", "aleksandra@example.com");
		System.out.printf("%s\n%s\n", s1.toString(), s2.toString());
		s2.setIndex(4566);
		System.out.printf("%s\n", s2.toString());
	}

}

class Student {
	private String name, email;
	private int index;

	public Student(String name, String email, int index) {
		this.name = name;
		this.email = email;
		this.index = index;
	}

	public Student(String name, String email) {
		this.name = name;
		this.email = email;
		this.index = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return "Student{" +
				"name='" + name + '\'' +
				", email='" + email + '\'' +
				", index=" + index +
				'}';
	}
}