package hotel;

public class Room {
	private int number;
	private int numberOfBeds;

	public Room(int number, int numberOfBeds) throws Exception {
		setNumber(number);
		setNumberOfBeds(numberOfBeds);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) throws IllegalArgumentException {
		if (number < 1) {
			throw new IllegalArgumentException(String.format("%d is not a valid room number", number));
		}
		this.number = number;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		if (numberOfBeds < 0) {
			throw new IllegalArgumentException(String.format("%d is not a valid number of beds", number));
		}
		this.numberOfBeds = numberOfBeds;
	}

	@Override
	public String toString() {
		return "Room{" +
				"number=" + number +
				", numberOfBeds=" + numberOfBeds +
				'}';
	}
}
