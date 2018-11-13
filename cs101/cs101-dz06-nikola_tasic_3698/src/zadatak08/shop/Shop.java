package zadatak08.shop;

import zadatak08.shop.Product;
import zadatak08.shop.Purchase;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;

public class Shop {
	private String name;
	private ArrayList<Product> products;
	private ArrayList<Purchase> purchases;
	private ArrayList<Customer> customers;
	private double revenue;

	public Shop(String name) {
		this.name = name;
		this.products = new ArrayList<>();
		this.purchases = new ArrayList<>();
		this.customers = new ArrayList<>();

	}
	public void addPurchase(Purchase p){
		this.purchases.add(p);
		for (int i =0; i < this.purchases.size();i++){
			this.revenue += this.purchases.get(i).getValue();
		}
	}
	public void addCustomer(Customer c) {
		this.customers.add(c);
	}
	public void removeCustomer(int index){
		this.customers.remove(index);
	}
	public Customer getCustomerById(String id){
		Customer c = null;
		for(int i = 0; i < this.customers.size(); i++){
			if(this.customers.get(i).getUid() == id){

				c = this.customers.get(i);
			}
		}
		return c;
	}
	public ArrayList<Customer> getCustomers() {
		return customers;
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
	public void removeProduct(int index){

		this.products.remove(index);

	}

	public void removePurchase(Purchase p) {
		purchases.remove(p);
	}

}
