
package rs.ac.metropolitan.cs230;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

@Stereotype

public class InternetStudentController {

	private static final Logger logger = Logger.getLogger(InternetStudentController.class.getName());
	@Inject
	@Internet
	private TraditionalStudent tradicionalniStudent;

	public String navigateToInternetStudents() {
		InternetStudent internetStudent = (InternetStudent) tradicionalniStudent;
		logger.log(Level.INFO, "Saving info \n{0} {1}, course = {2}, {3}",
				new Object[]{internetStudent.getFirstName(),
						internetStudent.getLastName(),
						internetStudent.getCourse(),
						internetStudent.getEmail()});
		return "internetStudents";
	}
}
