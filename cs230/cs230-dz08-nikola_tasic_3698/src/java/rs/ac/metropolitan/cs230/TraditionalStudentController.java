
package rs.ac.metropolitan.cs230;

import javax.inject.Inject;

@AStereotype
public class TraditionalStudentController {

	@Inject
	private TraditionalStudent traditionalStudent;

	public TraditionalStudentController() {
	}

	public TraditionalStudent getTraditionalStudent() {
		return traditionalStudent;
	}

	public void setTraditionalStudent(TraditionalStudent traditionalStudent) {
		this.traditionalStudent = traditionalStudent;
	}
}
