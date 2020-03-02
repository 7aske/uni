import errors.InvalidCodeException;

import java.util.regex.Pattern;

public class Product {
	private final Pattern alphanum = Pattern.compile("^[a-zA-Z0-9 ]*$");

	private String name;
	private double price;
	private String code;

	public Product(String name, double price, String code) throws InvalidCodeException {
		setName(name);
		setPrice(price);
		setCode(code);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) throws InvalidCodeException {
		if (code.length() != 4) {
			throw new InvalidCodeException("'code' length must be 4 characters long");
		}
		if (!alphanum.matcher(code).find()) {
			// throw new InvalidCodeException("'code' length must be alphanumeric characters");
			throw new InvalidCodeException("'code' must contain only alphanumeric characters");
		}
		this.code = code;
	}

	@Override
	public String toString() {
		return "Product{" +
				"name='" + name + '\'' +
				", price=" + price +
				", code='" + code + '\'' +
				'}';
	}
}