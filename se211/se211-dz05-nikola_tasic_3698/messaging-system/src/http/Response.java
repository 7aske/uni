package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Response {
	public static final String CLRF = "\r\n";
	public static final String HTTP_VER = "HTTP/1.1";
	private String version;
	private int statusCode;
	private String reasonPhrase;

	private HashMap<String, String> headers;
	private String body;

	private Response(StatusCodes code) {
		this.statusCode = HttpStatus.getStatusCode(code);
		this.reasonPhrase = HttpStatus.getStatusText(code);
		this.version = HTTP_VER;
	}
	private Response() {
		this.headers = new HashMap<>();
	}

	public static Response generateResponse(StatusCodes code) {
		Response response = new Response(code);
		response.headers = new HashMap<>();
		return response;
	}

	public static Response generateResponse(StatusCodes code, String body) {
		Response response = new Response(code);
		response.headers = new HashMap<>();
		response.setBody(body);
		return response;
	}
	
	public static Response generateResponse(BufferedReader reader) throws IOException {
		Response response = new Response();
		String line;
		while ((line = reader.readLine()) != null) {
			if (line.isEmpty()) {
				StringBuilder buffer = new StringBuilder();
				Map.Entry<String, String> cl = response.getHeader("Content-length");
				if (cl != null) {
					int length = Integer.parseInt(cl.getValue());
					while (length > 0) {
						buffer.append((char) (reader.read()));
						length--;
					}
					response.body = buffer.toString();
				}
				break;
			} else {
				String[] parts = line.split(": ");
				if (parts.length == 2) {
					response.headers.put(parts[0].toLowerCase(), parts[1]);
				} else {
					String[] requestLine = line.split(" ", 3);
					if (requestLine.length == 3) {
						response.version = requestLine[0];
						response.setStatusCode(Integer.parseInt(requestLine[1]));
						response.reasonPhrase = requestLine[2];
					}

				}
			}
		}
		return response;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getReasonPhrase() {
		return reasonPhrase;
	}

	public void setReasonPhrase(String reasonPhrase) {
		this.reasonPhrase = reasonPhrase;
	}

	public HashMap<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(HashMap<String, String> headers) {
		this.headers = headers;
	}

	public Map.Entry<String, String> getHeader(String header) {
		for (Map.Entry<String, String> h : this.headers.entrySet()) {
			if (h.getKey().toUpperCase().equals(header.toUpperCase())) {
				return h;
			}
		}
		return null;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.headers.put("Content-length", Integer.toString(body.length()));
		this.body = body;
	}

	public void setBody(StringBuilder body) {
		this.setBody(body.toString());
	}

	public byte[] getBytes() {
		return this.toString().getBytes();
	}

	@Override
	public String toString() {
		StringBuilder headersString = new StringBuilder();
		for (Map.Entry<String, String> h : this.headers.entrySet()) {
			headersString.append(String.format("%s: %s\r\n", h.getKey(), h.getValue()));
		}
		return
				this.version + " " + this.statusCode + " " + this.reasonPhrase  + Response.CLRF
						+ headersString.toString()
						+ Response.CLRF
						+ (this.body == null ? "" : this.body);
	}

	public void setHeader(String key, String value) {
		this.headers.put(key, value);
	}
}