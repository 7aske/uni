//datum, nosilac rezervacije, relacija, broj osoba i cena
package flightReservation;

import java.util.Date;

public class FlightReservation {
	private long id;
	private Date date;
	private String reservedBy;
	private String fromTo;
	private short noOfPassengers;
	private double price;

	public FlightReservation() {

	}

	public FlightReservation(long id, Date date, String reservedBy, String fromTo, short noOfPassengers, double price) {
		this.id = id;
		this.date = date;
		this.reservedBy = reservedBy;
		this.fromTo = fromTo;
		this.noOfPassengers = noOfPassengers;
		this.price = price;
	}

	public FlightReservation(Date date, String reservedBy, String fromTo, short noOfPassengers, double price) {
		this(0, date, reservedBy, fromTo, noOfPassengers, price);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getReservedBy() {
		return reservedBy;
	}

	public void setReservedBy(String reservedBy) {
		this.reservedBy = reservedBy;
	}

	public String getFromTo() {
		return fromTo;
	}

	public void setFromTo(String fromTo) {
		this.fromTo = fromTo;
	}

	public short getNoOfPassengers() {
		return noOfPassengers;
	}

	public void setNoOfPassengers(short noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return  "" + id + " " + fromTo + " " + noOfPassengers + " " + price;
	}
}
