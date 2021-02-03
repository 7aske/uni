import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TriangleTest {
	@Test
	public void isValid_NotValid() {
		boolean result = Triangle.isValid(1, 2, 3);
		assertFalse(result);
	}

	@Test
	public void isValid_Valid() {
		boolean result = Triangle.isValid(1, 2, 4);
		assertFalse(result);
	}

	@Test
	public void isValid_ShouldThrow() {
		assertThrows(IllegalArgumentException.class, () -> Triangle.isValid(0, 1, 2));
	}
}