package zadatak09_2.client;

import zadatak09_2.server.Zadatak09Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Zadatak09Client {
	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("localhost", Zadatak09Server.PORT);
		DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter a string to be trimmed: ");
		String line = scanner.nextLine();
		System.out.println("Client sends: \n" + line);
		writer.writeBytes(line + "\n");

		String out = reader.readLine();
		System.out.print("Server returns: \n" + out);

		writer.close();
		reader.close();
	}
}
