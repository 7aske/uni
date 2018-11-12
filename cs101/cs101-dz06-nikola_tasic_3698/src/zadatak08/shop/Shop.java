package zadatak08.shop;

import zadatak08.shop.Product;
import zadatak08.shop.Purchase;

import java.util.ArrayList;

public class Shop {
	private String name;
	private ArrayList<Product> products;
	private ArrayList<Purchase> purchases;
	private double revenue;

	public Shop(String name) {
		this.name = name;
		this.products = new ArrayList<>();
		this.purchases = new ArrayList<>();

	}

	public double getRevenue(){ return revenue; }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public ArrayList<Purchase> getPurchases() {
		return purchases;
	}

	public void addProduct(Product p) {
		products.add(p);
	}

	public void removeProduct(Product p) {
		products.remove(p);
	}

	public void addPurchase(Purchase p) {
		purchases.add(p);
	}

	public void removePurchase(Purchase p) {
		purchases.remove(p);
	}

}
