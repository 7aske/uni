package proteinTracker;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class TrackingServiceTest {
	TrackingService trackingService;

	@Before
	public void setUp() {
		trackingService = new TrackingService();
	}

	@Test
	public void newTrackingService_TotalIsZero() {
		assertEquals(0, trackingService.getTotal());
	}

	@Test
	public void addProtein_TotalIncreasesByAmount() {
		int amount = 50;
		trackingService.addProtein(amount);
		assertEquals(amount, trackingService.getTotal());
	}

	@Test
	public void removeProtein_RemoveProteinTotalRemainsZero() {
		trackingService.removeProtein(50);
		assertEquals(0, trackingService.getTotal());
	}

	@Test(expected = IllegalArgumentException.class)
	public void setGoal_GoalNegative_ExceptionThrown() {
		trackingService.setGoal(-10);
	}

	@Ignore
	@Test(timeout = 200)
	public void addProtein_BadTest() {
		for (int i = 0; i < 10_000_000; i++) {
			trackingService.addProtein(i);
		}
	}
}