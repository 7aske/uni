package rs.ac.metropolitan.it355.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static rs.ac.metropolitan.it355.util.StringUtils.union;

class StringUtilsTests {
	@Test
	void test_union_twoPathsHaveCommon(){
		String path1 = "/srv/http/frigo/static/media";
		String path2 = "static/media/2/cover.jpg";
		String expected = "/srv/http/frigo/static/media/2/cover.jpg";

		assertEquals(expected, union(path1, path2));
	}

	@Test
	void test_union_twoPathsNoCommon(){
		String path1 = "Hello World";
		String path2 = "Testing Testing";
		String expected = "Hello WorldTesting Testing";

		assertEquals(expected, union(path1, path2));
	}

	@Test
	void test_union_twoPathsSame(){
		String path1 = "Hello World";

		assertEquals(path1, union(path1, path1));
	}

	@Test
	void test_union_stringNullShouldThrow() {
		assertThrows(NullPointerException.class,
				() -> union(null, null));
	}
}
