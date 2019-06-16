import client.state.State;
import junit.framework.TestCase;

import java.util.ArrayList;

public class StateTest extends TestCase {
	public void testState() {
		State state = new State();

		assertNotNull(state.getContacts());
		assertNotNull(state.getMessages());
		assertEquals(state.getContacts().getClass(), ArrayList.class);
		assertEquals(state.getMessages().getClass(), ArrayList.class);
	}
}
