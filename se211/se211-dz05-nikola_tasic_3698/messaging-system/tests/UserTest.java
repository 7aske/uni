import http.Response;
import junit.framework.TestCase;
import server.user.User;
import utils.Encryption;

import java.util.Arrays;
import java.util.HashMap;

public class UserTest extends TestCase {

	public void testUserFromFormHashMap() {
		HashMap<String, String> form = new HashMap<>();
		form.put("username", "username");
		form.put("password", "password");
		form.put("firstName", "firstName");
		form.put("lastName", "lastName");
		form.put("company", "company");
		form.put("email", "email");
		form.put("phone", "phone");

		User user = User.fromForm(form);

		assertEquals(user.getUsername(), "username");
		assertEquals(user.getPassword(), Encryption.getSHA1Hash("password"));
		assertEquals(user.getFirstName(), "firstName");
		assertEquals(user.getLastName(), "lastName");
		assertEquals(user.getCompany(), "company");
		assertEquals(user.getEmail(), "email");
		assertEquals(user.getPhone(), "phone");
	}

	public void testUserFromFormString() {
		String form = "username=username&password=password&firstName=firstName&lastName=lastName&email=email&phone=phone&company=company";

		User user = User.fromForm(form);

		assertEquals(user.getUsername(), "username");
		assertEquals(user.getPassword(), Encryption.getSHA1Hash("password"));
		assertEquals(user.getFirstName(), "firstName");
		assertEquals(user.getLastName(), "lastName");
		assertEquals(user.getCompany(), "company");
		assertEquals(user.getEmail(), "email");
		assertEquals(user.getPhone(), "phone");

	}

	public void testUserFields() {
		assertTrue(Arrays.asList(User.fieldList).contains("username"));
		assertTrue(Arrays.asList(User.fieldList).contains("password"));
		assertTrue(Arrays.asList(User.fieldList).contains("firstName"));
		assertTrue(Arrays.asList(User.fieldList).contains("lastName"));
		assertTrue(Arrays.asList(User.fieldList).contains("email"));
		assertTrue(Arrays.asList(User.fieldList).contains("phone"));
		assertTrue(Arrays.asList(User.fieldList).contains("company"));
	}

	public void testUserAsResponseString() {
		String form = "username=username&password=password&firstName=firstName&lastName=lastName&email=email&phone=phone&company=company";

		User user = User.fromForm(form);

		assertTrue(user.asResponseString().endsWith(Response.CLRF));
		assertEquals(user.asResponseString().split("&").length, 6);
		assertTrue(user.asResponseString().contains("username"));
		assertTrue(user.asResponseString().contains("firstName"));
		assertTrue(user.asResponseString().contains("lastName"));
		assertTrue(user.asResponseString().contains("email"));
		assertTrue(user.asResponseString().contains("phone"));
		assertTrue(user.asResponseString().contains("company"));
	}
}
