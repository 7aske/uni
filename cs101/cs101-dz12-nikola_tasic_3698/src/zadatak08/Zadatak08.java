package zadatak08;

//		Zadatak 8
//		Napraviti klasu Student (Ime, Prezime, Indeks) i klasu Ispit (Naziv predmeta, Student, Datum,
//		Ocena). Kreirati metod koji će na osnovu liste Ispita da izračuna prosečnu ocenu zadatog
//		studenta.

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Zadatak08 {
	public static void main(String[] args){

	}
}

class Student {
	private String firstName;
	private String lastName;
	private String index;
	private ArrayList<Exam> exams = new ArrayList<>();

	public Student(String firstName, String lastName, String index) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.index = index;
	}
	public void addExam(Exam e){
		exams.add(e);
	}
	public double averageMark(){
		int sum = 0;
		for (int i = 0; i < exams.size(); i++) {
			sum += exams.get(i).getMark();
		}
		try {
			return sum / exams.size();
		} catch (ArithmeticException e) {
			return 0;
		}
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

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}
}

class Exam {
	private String name;
	private Student student;
	private Date date;
	private int mark;

	public Exam(String name, Student student, Date date, int mark) {
		this.name = name;
		this.student = student;
		this.date = date;
		this.mark = mark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}
}
