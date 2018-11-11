package zadatak08.shop;

public class Product {
	private String brand;
	private String name;
	private double price;
	private String barcode;
	private int stock;

	public Product(String brand, String name, double price, String barcode, int stock) {
		this.brand = brand;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.barcode = barcode;

	}

	public Product(String brand, String name, String barcode, double price) {
		this(brand, name, price, barcode, 0);
	}

	public void removeOne() {
		if (this.stock > 0) {
			this.stock--;
		}
	}

	public void addOne() {
		this.stock++;
	}

	public void addMultiple(int number) {
		this.stock += number;
	}

	public void removeMultiple(int number) {
		if (number < this.stock) {
			this.stock -= number;
		}

	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
}
