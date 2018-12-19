package zadatak09;

import java.awt.*;

public class Main {
	public static void main(String[] args) {
		Circle c1 = new Circle(new Point(0, 0), 10, "red");
		System.out.printf("Circle: %s", c1.toString());
		System.out.printf("(10, 10)%s\n", c1.isInside(new Point(10, 10)));
		System.out.printf("(5, 5)%s\n", c1.isInside(new Point(5, 5)));
		System.out.printf("(2, 2)%s\n", c1.isInside(new Point(2, 2)));
		System.out.printf("(20, 20)%s\n", c1.isInside(new Point(20, 20)));
		c1.setCenter(new Point(3, 7));
		c1.setColor("black");
		System.out.printf("Circle: %s", c1.toString());
	}
}


class Circle {
	private Point center;
	private String color;
	private double radius;

	public Circle(Point center, double radius, String color) {
		this.center = center;
		this.color = color;
		this.radius = radius;
	}

	public Circle() {
		this(new Point(0, 0), 10, "red");
	}

	public double getArea() {
		return radius * radius * Math.PI;
	}

	public double getCircumference() {
		return 2 * radius * Math.PI;
	}

	public boolean isInside(Point p) {
		return Math.pow(p.getX() - center.getX(), 2) + Math.pow(p.getY() - center.getY(), 2) <= Math.pow(radius, 2);
	}

	@Override
	public String toString() {
		return String.format("{\n\tx: %s,\n\ty: %s,\n\tradius: %s,\n\tcolor: \"%s\"\n}\n", center.getX(), center.getY(), getRadius(), getColor());
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
}
