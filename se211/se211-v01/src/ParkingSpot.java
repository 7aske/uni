import exception.InvalidLocationException;

import java.util.regex.Pattern;

/**
 * Class representation of the parking spot entity
 */
public class ParkingSpot {
	// Location validity pattern
	private final Pattern alphanum = Pattern.compile("^[a-zA-Z0-9 ]*$");

	private String location;
	private double price;
	private String description;

	public ParkingSpot(String location, double price, String description) throws InvalidLocationException {
		setLocation(location);
		this.price = price;
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) throws InvalidLocationException {
		if (!isValidLocation(location)) {
			throw new InvalidLocationException(String.format("Invalid location '%s'", location));
		}
		this.location = location;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Verifies whether location string contains invalid characters
	 *
	 * @param location Location string to be validated
	 * @return Location validity
	 */
	private boolean isValidLocation(String location) {
		return alphanum.matcher(location).find();
	}

	@Override
	public String toString() {
		return "ParkingSpot{" +
				", location='" + location + '\'' +
				", price=" + price +
				", description='" + description + '\'' +
				'}';
	}
}