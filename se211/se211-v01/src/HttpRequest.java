import java.util.ArrayList;
import java.util.List;

/**
 * Object representation of a HTTP request
 */
public class HttpRequest {
	// HTTP RFC line ending
	private static final String CLRF = "\r\n";
	private List<String> headers = new ArrayList<>();
	private String body;

	private HttpRequest() {
	}

	public HttpRequest(List<String> headers, String body) {
		this.headers = headers;
		this.body = body;
	}

	/**
	 * @param rawRequest array of raw http request bytes
	 * @return Parsed HttpRequest object
	 */
	public HttpRequest fromBytes(byte[] rawRequest) {
		HttpRequest req = new HttpRequest();
		req.body = "";
		req.headers = new ArrayList<>();
		return req;
	}

	/**
	 * @param rawBytes array of raw http request bytes
	 * @return Prased Http header
	 */
	private String parseHeader(byte[] rawBytes) {
		// implementation
		return "";
	}
}
