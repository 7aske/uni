package http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Request {
	public static final String CLRF = "\r\n";
	public static final String HTTP_VER = "HTTP/1.1";
	private String method;
	private String path;
	private String version;
	private HashMap<String, String> headers;
	private String body = null;

	private Request() {
		this.headers = new HashMap<>();
	}


	public static Request generateRequest(BufferedReader reader) throws IOException {
		Request request = new Request();
		String line;
		while ((line = reader.readLine()) != null) {
			if (line.isEmpty()) {
				StringBuilder buffer = new StringBuilder();
				Map.Entry<String, String> cl = request.getHeader("Content-length");
				if (cl != null) {
					int length = Integer.parseInt(cl.getValue());
					while (length > 0) {
						buffer.append((char) (reader.read()));
						length--;
					}
					request.body = buffer.toString();
				}
				break;
			} else {
				String[] parts = line.split(": ");
				if (parts.length == 2) {
					request.headers.put(parts[0].toLowerCase(), parts[1]);
				} else {
					String[] requestLine = line.split(" ");
					if (requestLine.length == 3) {
						request.method = requestLine[0];
						request.path = requestLine[1];
						request.version = requestLine[2];
					}

				}
			}
		}
		return request;
	}

	public static Request generateRequest(String method, String path) {
		Request request = new Request();
		request.version = Request.HTTP_VER;
		request.path = path;
		request.method = method;
		return request;
	}
	public static Request generateRequest() {
		Request request = new Request();
		request.version = Request.HTTP_VER;
		request.path = "/";
		request.method = "GET";
		return request;
	}

	public String getMethod() {
		return method;
	}

	public String getPath() {
		return path;
	}

	public String getVersion() {
		return version;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public HashMap<String, String> getHeaders() {
		return headers;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.setHeader("Content-Length", String.valueOf(body.length()));
		this.body = body;
	}

	public void setHeader(String key, String value) {
		this.headers.put(key, value);
	}

	public Map.Entry<String, String> getHeader(String header) {
		for (Map.Entry<String, String> h : this.headers.entrySet()) {
			if (h.getKey().compareToIgnoreCase(header) == 0) {
				return h;
			}
		}
		return null;
	}

	public boolean containsHeader(String header){
		for (Map.Entry<String, String> h : this.headers.entrySet()) {
			if (h.getKey().compareToIgnoreCase(header) == 0) {
				return true;
			}
		}
		return false;
	}

	public HashMap<String, String> getFormData() {
		HashMap<String, String> form = new HashMap<>();
		if (this.method.toUpperCase().equals("POST")) {
			Map.Entry<String, String> cdata = this.getHeader("Content-type");
			if (cdata == null){
				return form;
			}
			if (cdata.getValue().compareToIgnoreCase("application/x-www-form-urlencoded") == 0) {
				String[] pairs = this.body.split("&");
				for (String pair : pairs) {
					String[] fields = pair.split("=");
					if (fields.length == 2) {
						form.put(fields[0], fields[1]);
					}
				}
			}
		}
		return form;
	}

	public void setFormData(HashMap<String, String> form) {
		StringBuilder formBody = new StringBuilder();
		for (Map.Entry<String, String> e : form.entrySet()) {
			if (formBody.length() != 0) {
				formBody.append("&");
			}
			formBody.append(String.format("%s=%s", e.getKey(), e.getValue()));
		}
		this.body = formBody.toString();
		this.setHeader("Content-Length", Integer.toString(formBody.length()));
		this.setHeader("Content-Type", "application/x-www-form-urlencoded");
	}
	public Response send(String hostname, int port) throws IOException {
			InetAddress addr = InetAddress.getByName(hostname);
			Socket socket = new Socket(addr, port);
			DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer.writeBytes(this.toString());
			return Response.generateResponse(reader);
	}

	@Override
	public String toString() {
		StringBuilder headersString = new StringBuilder();
		for (Map.Entry<String, String> h : this.headers.entrySet()) {
			headersString.append(String.format("%s: %s\r\n", h.getKey(), h.getValue()));
		}
		return
				this.method + " " + this.path + " " + this.version  + Request.CLRF
						+ headersString.toString()
						+ Request.CLRF
						+ (this.body == null ? "" : this.body);
	}
}
