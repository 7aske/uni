import hotel.Reservation;
import hotel.exception.InvalidCardException;
import org.junit.Test;

import java.time.LocalDate;

public class TestReservation {

	@Test
	public void testCard1() throws InvalidCardException {
		String card1 = "123456789163";
		new Reservation(LocalDate.now(), LocalDate.now(), card1);
	}

	@Test(expected = InvalidCardException.class)
	public void testCard2() throws InvalidCardException {
		String card2 = "123456789101";
		new Reservation(LocalDate.now(), LocalDate.now(), card2);
	}

	@Test(expected = InvalidCardException.class)
	public void testCard3() throws InvalidCardException {
		String card3 = "1234567863";
		new Reservation(LocalDate.now(), LocalDate.now(), card3);

	}

	@Test(expected = InvalidCardException.class)
	public void testCard4() throws InvalidCardException {
		String card4 = "1234567801";
		new Reservation(LocalDate.now(), LocalDate.now(), card4);
	}
}
