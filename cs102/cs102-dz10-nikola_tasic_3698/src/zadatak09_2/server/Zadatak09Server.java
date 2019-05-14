package zadatak09_2.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Zadatak09Server {
	public static final int PORT = 8000;

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(PORT);
		while (true) {
			Socket socket = serverSocket.accept();
			DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String line = reader.readLine();
			System.out.println("Client sent: \n" + line);
			String trimmedLine = line.trim();
			System.out.println("Server sends: \n" + trimmedLine);
			writer.writeBytes(trimmedLine);

			writer.close();
			reader.close();
		}
	}
}
