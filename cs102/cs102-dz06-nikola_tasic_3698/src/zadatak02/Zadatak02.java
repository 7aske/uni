package zadatak02;

import java.util.Arrays;

public class Zadatak02 {
	public static void main(String[] args) {
		Rectangle[] rects = {new Rectangle(10, 20),new Rectangle(20, 15), new Rectangle(10, 10)};
		Circle[] circles = {new Circle(5),new Circle(15), new Circle(10)};
		for (Circle c : circles) {
			System.out.println(c.circumference());
		}
		for (Rectangle r : rects) {
			System.out.println(r.area());
		}

		ShapeCtrl.sortByCirc(circles);
		ShapeCtrl.sortByArea(rects);

		for (Circle c : circles) {
			System.out.println(c.circumference());
		}
		for (Rectangle r : rects) {
			System.out.println(r.area());
		}
	}
}

abstract class Shape2D {
	abstract public float circumference();

	abstract public float area();
}

class ShapeCtrl {
	public static <Shape2d> void sortByArea(Shape2D[] arr) {
		Arrays.sort(arr, (a, b) -> Float.compare(a.area(), b.area()));
	}

	public static <Shape2d> void sortByCirc(Shape2D[] arr) {
		Arrays.sort(arr, (a, b) -> Float.compare(a.circumference(), b.circumference()));
	}

}

class Rectangle extends Shape2D {
	private int a;
	private int b;

	public Rectangle(int a, int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public float circumference() {
		return 2 * a + 2 * b;
	}

	@Override
	public float area() {
		return a * b;
	}
}

class Circle extends Shape2D {
	private int r;

	public Circle(int r) {
		this.r = r;
	}

	@Override
	public float circumference() {
		return 2 * r * (float) Math.PI;
	}

	@Override
	public float area() {
		return r * r * (float) Math.PI;
	}
}

class Triangle extends Shape2D {
	private int a;
	private int b;
	private int c;

	public Triangle(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	public float circumference() {
		return a + b + c;
	}

	// pretpostavimo da je pravougli jer me mrzi da pisem
	@Override
	public float area() {
		return a * b / 2;
	}
}