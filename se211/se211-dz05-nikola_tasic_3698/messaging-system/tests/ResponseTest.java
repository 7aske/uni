import http.Response;
import http.StatusCodes;
import junit.framework.*;

public class ResponseTest extends TestCase {

	public void testBlankResponse() {
		Response res = Response.generateResponse(StatusCodes.OK);
		assertNotSame(res, null);
		assertSame(res.getClass(), Response.class);

		assertNotNull(res.getHeaders());
		assertNull(res.getBody());
		assertEquals(res.getStatusCode(), 200);
		assertEquals(Response.HTTP_VER, "HTTP/1.1");
	}

	public void testResponseSetBody() {
		String body = "Hello World!";
		Response res = Response.generateResponse(StatusCodes.NotFound, body);

		assertEquals(res.getBody().length(), body.length());
		assertNotNull(res.getHeaders());
		assertEquals(res.getHeader("Content-Length").getValue(), String.valueOf(body.length()));
		assertEquals(res.toString().split(Response.CLRF).length, 4);
	}

	public void testResponseStatics() {
		assertEquals(Response.HTTP_VER, "HTTP/1.1");
		assertEquals(Response.CLRF, "\r\n");
	}
}
