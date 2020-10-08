import http.Request;
import junit.framework.*;

public class RequestTest extends TestCase {

	public void testBlankRequest() {
		Request req = Request.generateRequest();
		assertNotSame(req, null);
		assertSame(req.getClass(), Request.class);
		assertEquals(req.getPath(), "/");
		assertEquals(req.getMethod(), "GET");
		assertEquals(Request.HTTP_VER, "HTTP/1.1");
	}

	public void testParamRequest() {
		Request req = Request.generateRequest("POST", "/index");
		assertNotSame(req, null);
		assertSame(req.getClass(), Request.class);
		assertEquals(req.getPath(), "/index");
		assertEquals(req.getMethod(), "POST");
		assertEquals(Request.HTTP_VER, "HTTP/1.1");
	}

	public void testRequestSetBody() {
		String body = "Hello World!";
		Request req = Request.generateRequest("POST", "/index");
		assertNotSame(req, null);

		req.setBody(body);

		assertTrue(req.containsHeader("Content-Length"));
		assertEquals(req.getHeader("Content-Length").getValue(), String.valueOf(body.length()));
		assertEquals(req.toString().split(Request.CLRF).length, 4);
	}

	public void testRequestStatics() {
		assertEquals(Request.HTTP_VER, "HTTP/1.1");
		assertEquals(Request.CLRF, "\r\n");
	}
}

