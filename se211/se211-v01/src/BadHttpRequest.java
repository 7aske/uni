import java.util.ArrayList;
import java.util.List;

public class BadHttpRequest {
	public static final String CLRF = "\r\n";
	public List<String> headers = new ArrayList<>();
	public String body;


	public BadHttpRequest() {
	}

	public BadHttpRequest(List<String> h, String b) {
		this.headers = h;


		this.body = b;
	}

	public BadHttpRequest parseRequest(byte[] raw) {
		// implementation
		return new BadHttpRequest();
	}

	public String parseHeader(byte[] raw) {
		// implementation
		return "";
	}
}
