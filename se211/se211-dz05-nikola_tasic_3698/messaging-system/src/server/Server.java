package server;

import http.StatusCodes;
import http.Request;
import http.Response;
import server.database.DBController;
import server.handler.Handler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements AutoCloseable {

	public static final int DEFAULT_SMTP_PORT = 25;
	static private final int THREAD_TIMEOUT = 1000;
	private int port;
	private ServerSocket serverSocket;
	private volatile boolean running;
	private Thread workerThread;

	public static void main(String[] args) {
		DBController.initDatabase();
		try {
			Server.start(8000);
		} catch (
				IOException e) {
			e.printStackTrace();
		}
	}

	private Server(ServerSocket socket) {
		this.serverSocket = socket;
		this.port = socket.getLocalPort();
		this.running = true;
		this.workerThread = new Thread(this::doWork);
		this.workerThread.start();
	}

	public static Server start(int port) throws IOException {
		return new Server(new ServerSocket(Math.max(port, Server.DEFAULT_SMTP_PORT)));
	}

	private void doWork() {
		while (this.running) {
			try {
				new Thread(new HandlerThread(serverSocket.accept())).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public int getPort() {
		return port;
	}

	private void stop() throws InterruptedException, IOException {
		this.running = false;
		this.serverSocket.close();
		this.workerThread.join(Server.THREAD_TIMEOUT);
	}


	@Override
	public void close() throws Exception {
		stop();
	}
}

class HandlerThread implements Runnable {
	private Socket socket;

	HandlerThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
			Request request = Request.generateRequest(reader);

			System.out.println(request.toString());

			if (request.getPath().equals("/register")) {
				Handler.handleRegister(request, writer);
			} else if (request.getPath().equals("/login")) {
				Handler.handleLogin(request, writer);
			} else if (request.getPath().startsWith("/api/")) {
				Handler.handleApi(request, writer);
			} else if (request.getPath().equals("/messages")) {
				Handler.handleMessage(request, writer);
			} else {
				Response response = Response.generateResponse(StatusCodes.NotFound);
				response.setBody("404 Not Found");
				writer.writeBytes(response.toString());
			}

			reader.close();
			writer.flush();
			writer.close();
			socket.close();
		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
		}
	}
}