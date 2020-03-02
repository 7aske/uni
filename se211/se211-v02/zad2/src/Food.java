import errors.InvalidCodeException;

public class Food extends Product {
	public Food(String name, double price, String code) throws InvalidCodeException {
		super(name, price, code);
	}
}