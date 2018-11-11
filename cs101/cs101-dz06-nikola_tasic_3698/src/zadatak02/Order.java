package zadatak02;

public class Order {
	private Window[] windows;
	private String address;
	private double price;
	private int id;


	public Order(Window[] windows, String address, double price) {
		this.windows = windows;
		this.address = address;
		this.price = price;
		this.id = (int) (Math.random() * 10000) + 1;
	}

	public Order(Window[] windows, String address) {
		double price = 0;
		for (int i = 0; i < windows.length; i++) {
			price += windows[i].getPrice();
		}
		this.windows = windows;
		this.address = address;
		this.price = price;
		this.id = (int) (Math.random() * 10000) + 1;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Window[] getWindows() {
		return windows;
	}

	public void setWindows(Window[] windows) {
		this.windows = windows;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
