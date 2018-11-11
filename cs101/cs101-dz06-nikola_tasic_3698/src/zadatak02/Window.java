package zadatak02;

public class Window {
	private double height;
	private double width;
	private double depth;
	private double price;

	public Window(double height, double width, double depth, double price) {
		this.height = height;
		this.width = width;
		this.depth = depth;
		this.price = price;
	}
	// Cisto radi primera overload-ing konstruktora
	public Window(double height, double width, double depth) {
		this(height, width, depth, 0);
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getDepth() {
		return depth;
	}

	public void setDepth(double depth) {
		this.depth = depth;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
