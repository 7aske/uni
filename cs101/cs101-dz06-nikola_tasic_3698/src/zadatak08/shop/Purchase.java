package zadatak08.shop;

import zadatak08.shop.Customer;
import zadatak08.shop.Product;

import java.util.ArrayList;

public class Purchase {
	private ArrayList<Product> purchesed_products;
	private Customer customer;
	private double value;

	public Purchase(ArrayList<Product> purchesed_products, Customer customer) {
		this.purchesed_products = purchesed_products;
		this.customer = customer;
		this.value = this.calculateValue();
	}

	private double calculateValue() {
		double v = 0;
		for (int i = 0; i < purchesed_products.size(); i++) {
			v += purchesed_products.get(i).getPrice();
		}
		return v;
	}

	public void addProduct(Product p) {
		this.purchesed_products.add(p);
		this.value = this.calculateValue();
	}

	public void removeProduct(Product p) {
		this.purchesed_products.remove(p);
		this.value = this.calculateValue();
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
