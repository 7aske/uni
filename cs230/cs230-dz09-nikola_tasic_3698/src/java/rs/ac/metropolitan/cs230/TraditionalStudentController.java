
package rs.ac.metropolitan.cs230;

import javax.inject.Inject;

@Stereotype
public class TraditionalStudentController {
    
    @Inject
    private TraditionalStudent traditionalStudent;

    public TraditionalStudentController() {
    }

    public void setTradicionalniStudent(TraditionalStudent tradicionalniStudent) {
        this.traditionalStudent= tradicionalniStudent;
    }
    
    public String navigateToTraditionalStudents(){
        return "traditionalStudents";
    }
}
