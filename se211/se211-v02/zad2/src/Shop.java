
import java.util.*;

public class Shop {
	private String name;
	private String address;
	public List<Product> productList;

	public Shop(String name, String address, List<Product> productList) {
		this.name = name;
		this.address = address;
		this.productList = productList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	@Override
	public String toString() {
		return "Shop{" +
				"name='" + name + '\'' +
				", address='" + address + '\'' +
				", productList=" + productList +
				'}';
	}
}