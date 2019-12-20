/***********************************************************************
 * Module:  Exam.java
 * Author:  nik
 * Purpose: Defines the Class Exam
 ***********************************************************************/

import java.util.*;

public class Exam {
	private List<Double> points = new ArrayList<>();
	public Student student;

	public double totalPoints__() {
		return totalExamPoints__() * 0.4;
	}

	public double totalExamPoints__() {
		Collections.sort(this.points);
		double sum = 0;
		for (int i = 1; i < this.points.size(); i++) {
			sum += this.points.get(i);
		}
		return sum;
	}

	public List<Double> getPoints() {
		return points;
	}

	public void setPoints(List<Double> points) {
		this.points = points;
	}

	public void addPoints(Double point) throws IllegalArgumentException {
		if (point < 0 || point > 20) {
			throw new IllegalArgumentException("Points must be larger than 0 or less than 20.");
		}
		if (this.points.size() >= 6) {
			throw new IllegalArgumentException("Number of exam tasks is 6.");
		}
		this.points.add(point);

	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Exam{" +
				"points=" + points +
				", student=" + student +
				'}';
	}
}