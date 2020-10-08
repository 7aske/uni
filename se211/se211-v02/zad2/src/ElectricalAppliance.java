import errors.InvalidCodeException;

public class ElectricalAppliance extends Product {
	public ElectricalAppliance(String name, double price, String code) throws InvalidCodeException {
		super(name, price, code);
	}
}