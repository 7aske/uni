
package rs.ac.metropolitan.cs230;

@AStereotype
@Internet
public class InternetStudent extends TraditionalStudent {
	private String course;

	public InternetStudent() {
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

}
