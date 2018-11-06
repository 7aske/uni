package example01;

public class Main {
	public static void main(String[] args) {
		Student[] students = new Student[3];
		for (int i = 0; i < students.length; i++) {
			students[i] = new Student("Student" + i, "email" + i + "@example.com", 1000 + i);
		}
		for (int j = 0; j < students.length ; j++) {
			System.out.printf("%s\n", students[j].getStudentString());
		}
	}
}
class Student {
	protected String name;
	protected String email;
	protected int index;

	public Student(String name, String email, int index) {
		this.name = name;
		this.email = email;
		this.index = index;
	}

	public Student getStundent() {
		return this;
	}

	public String getStudentString() {
		return String.format("%s - %s - %d", this.name, this.email, this.index);
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setEmail(String email){
		this.email = email;
	}
}
