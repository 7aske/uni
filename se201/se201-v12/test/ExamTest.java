import org.junit.Assert;
import org.junit.Test;
import org.junit.Assert.*;

public class ExamTest {

	@Test(expected = IllegalArgumentException.class)
	public void testAddPoints() {
		Student s = new Student("Marko", "Markovic", 3123);
		Exam e = new Exam();
		e.setStudent(s);
		e.addPoints(10.0);
		e.addPoints(25.0);
		e.addPoints(12.0);
		e.addPoints(-12.0);
	}

	@Test
	public void testAddPoints2() {
		Student s = new Student("Marko", "Markovic", 3123);
		Exam e = new Exam();
		e.setStudent(s);
		e.addPoints(10.0);
		e.addPoints(15.0);
		e.addPoints(18.0);
		e.addPoints(20.0);
		e.addPoints(5.0);

		double expected = 63.0;
		double result = e.totalExamPoints__();
		Assert.assertEquals(expected, result, 0.001);
	}

	@Test
	public void testAddPoints3() {
		Student s = new Student("Marko", "Markovic", 3123);
		Exam e = new Exam();
		e.setStudent(s);
		e.addPoints(10.0);
		e.addPoints(15.0);
		e.addPoints(18.0);
		e.addPoints(20.0);
		e.addPoints(5.0);

		double expected = 63.0 * 0.4;
		double result = e.totalPoints__();
		Assert.assertEquals(expected, result, 0.001);
	}

	@Test
	public void testAddPoints4() {
		Student s = new Student("Marko", "Markovic", 3123);
		Exam e = new Exam();
		e.setStudent(s);
		e.addPoints(10.0);
		e.addPoints(15.0);
		e.addPoints(18.0);
		e.addPoints(20.0);
		e.addPoints(5.0);

		double expected = 412 * 0.4;
		double result = e.totalPoints__();
		Assert.assertNotEquals(expected, result, 0.001);
	}
}
