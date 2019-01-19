package server;

import com.sun.net.httpserver.HttpServer;
import handlers.RootHandler;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class Server {
	public static String rootDir;
	private boolean CORS = false;
	private boolean SILENT = false;
	private boolean AUTOINDEX = true;

	public Server(String root, int port) throws IOException {

		this.rootDir = root;
		System.out.printf("Root: %s\n", root);
		HttpServer httpServer = HttpServer.create();
		httpServer.createContext("/", new RootHandler(this));
		// lookup local ip address
		String ip;
		try (final DatagramSocket socket = new DatagramSocket()) {
			socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			ip = socket.getLocalAddress().getHostAddress();
		}
		try {
			System.out.println("Starting up server...");
			httpServer.bind(new InetSocketAddress(ip, port), 100);

		} catch (IOException e) {
			System.out.printf("%s (localhost:%d)\n", e.getMessage(), port);
			System.exit(-1);
		}
		httpServer.start();
		System.out.printf("Server running on %s:%d\n", "127.0.0.1", port);
		System.out.printf("Server running on %s:%d\n", ip, port);
	}

	public String getRootDir() {
		return rootDir;
	}

	public boolean isCORS() {
		return CORS;
	}

	public void setCORS(boolean CORS) {
		this.CORS = CORS;
	}

	public boolean isSILENT() {
		return SILENT;
	}

	public void setSILENT(boolean SILENT) {
		this.SILENT = SILENT;
	}

	public boolean isAUTOINDEX() {
		return AUTOINDEX;
	}

	public void setAUTOINDEX(boolean AUTOINDEX) {
		this.AUTOINDEX = AUTOINDEX;
	}
}

