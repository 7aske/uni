import hotel.Reservation;
import junit.framework.TestCase;
import org.junit.Test;

import java.time.LocalDate;

public class TestReservation {

	@Test
	public void testCard1() throws Reservation.InvalidCardException {
		String card1 = "123456789163";
		new Reservation(LocalDate.now(), LocalDate.now(), card1);
	}

	@Test(expected = Reservation.InvalidCardException.class)
	public void testCard2() throws Reservation.InvalidCardException {
		String card2 = "123456789101";
		new Reservation(LocalDate.now(), LocalDate.now(), card2);
	}

	@Test(expected = Reservation.InvalidCardException.class)
	public void testCard3() throws Reservation.InvalidCardException {
		String card3 = "1234567863";
		new Reservation(LocalDate.now(), LocalDate.now(), card3);

	}

	@Test(expected = Reservation.InvalidCardException.class)
	public void testCard4() throws Reservation.InvalidCardException {
		String card4 = "1234567801";
		new Reservation(LocalDate.now(), LocalDate.now(), card4);
	}
}
