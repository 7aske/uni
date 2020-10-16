import org.junit.Test;

import static org.junit.Assert.*;

public class MathTests {

	@Test
	public void add_twoNumbers(){
		assertEquals(4, Math.add(1,3));
	}

	@Test
	public void even_True(){
		assertTrue(Math.even(10));
	}

	@Test
	public void even_NotTrue(){
		assertFalse(Math.even(11));
	}
}
