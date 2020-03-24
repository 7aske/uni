package hotel;

public class Room {
	private int number;
	private int numberOfBeds;

	public Room(int number, int numberOfBeds) {
		this.number = number;
		this.numberOfBeds = numberOfBeds;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
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
