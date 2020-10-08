package hotel;

import java.time.LocalDate;

public class Reservation {
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private String cardNumber;

	public Reservation(LocalDate checkInDate, LocalDate checkOutDate, String cardNumber) throws InvalidCardException {
		this.setCheckInDate(checkInDate);
		this.setCheckOutDate(checkOutDate);
		this.setCardNumber(cardNumber);
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) throws InvalidCardException {
		if (isCardValid(cardNumber)) {
			this.cardNumber = cardNumber;
		} else {
			throw new InvalidCardException(String.format("'%s' is an invalid card number", cardNumber));
		}
	}

	private static boolean isCardValid(String cardNumber) {
		// Neki kao zahtevi
		if (cardNumber.length() != 12) {
			return false;
		}
		if (!cardNumber.endsWith("63")) {
			return false;
		}
		return true;

	}

	@Override
	public String toString() {
		return "Reservation{" +
				"checkInDate=" + checkInDate +
				", checkOutDate=" + checkOutDate +
				", cardNumber='" + cardNumber + '\'' +
				'}';
	}

	public static class InvalidCardException extends Exception {
		public InvalidCardException(String message) {
			super(message);
		}
	}
}
