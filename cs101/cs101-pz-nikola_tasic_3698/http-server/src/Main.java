import server.Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static server.utils.ServerUtils.pathJoin;

public class Main {
	public static void main(String[] args){
		if (args.length > 0) {
			if (args[0].equals("-help") || args[0].equals("--help") || args[0].equals("-h")) {
				System.out.println("Usage: java -jar basic-http-server.jar [folder] [port]");
				System.out.println("--silent    - Supresses STDOUT request logging");
				System.out.println("--cors      - Enables Cross-Origin request headers");
				System.out.println("--noindex   - Disables auto-serving index.html files");

				return;
			}
		}
		String path;
		try {
			path = args[0];
		} catch (ArrayIndexOutOfBoundsException e){
			path = "";
		}
		int port;
		try {
			port = Integer.parseInt(args[1]);
		} catch (ArrayIndexOutOfBoundsException e){
			port = 8000;
		} catch (NumberFormatException e) {
			port = 8000;
		}
		Server server;
		try {
			server = new Server(pathJoin(System.getProperty("user.dir"), path), port);
		} catch (IOException e) {
			System.out.printf("%s\n", e.getMessage());
			return;
		}
		List<String> newArgs = new ArrayList<>(Arrays.asList(args));
		if (newArgs.contains("--cors")) server.setCORS(true);
		if (newArgs.contains("--silent")) server.setSILENT(true);
		if (newArgs.contains("--noindex")) server.setAUTOINDEX(false);


	}
}
