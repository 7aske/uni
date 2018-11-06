package example02;

public class Main {
	public static void main(String[] args) {
		Circle c0 = new Circle(4);
		System.out.printf("%.2f", c0.getCircumference());
	}
}

class Circle {
	protected double r;

	public Circle(double r) {
		this.r = r;
	}

	public void setR() {
		this.r = r;
	}

	public double getArea() {
		return Math.pow(r, 2) * Math.PI;
	}

	public double getCircumference() {
		return 2 * r * Math.PI;
	}

}
