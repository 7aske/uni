package registration;


import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class RegistrationBean implements Serializable {

	private String studyType;
	private String firstName;
	private String lastName;
	private int age;
	private String email;
	private String faculty;

	public RegistrationBean() {
	}

	public RegistrationBean(String studyType, String firstName, String lastBame, int age, String email, String faculty) {
		this.studyType = studyType;
		this.firstName = firstName;
		this.lastName = lastBame;
		this.age = age;
		this.email = email;
		this.faculty = faculty;
	}

	public String getStudyType() {
		return studyType;
	}

	public void setStudyType(String studyType) {
		this.studyType = studyType;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	@Override
	public String toString() {
		return "RegistrationBean{" +
				"studyType='" + studyType + '\'' +
				", firstName='" + firstName + '\'' +
				", lastBame='" + lastName + '\'' +
				", age=" + age +
				", email='" + email + '\'' +
				", faculty='" + faculty + '\'' +
				'}';
	}
}
