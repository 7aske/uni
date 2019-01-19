package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.Server;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static handlers.utils.ClientUtils.generateHTML;
import static handlers.utils.HandlerUtils.getContentType;
import static handlers.utils.HandlerUtils.readResource;
import static server.utils.ServerUtils.pathJoin;


public class RootHandler implements HttpHandler {
	private String root;
	private Server server;
	private List<Path> validPaths;

	public RootHandler(Server s) {
		this.root = s.getRootDir();
		this.server = s;
		try {
			validPaths = Files.walk(Paths.get(this.root)).collect(Collectors.toList());
			//validPaths = Files.walk(Paths.get(this.root)).filter(p -> Files.isRegularFile(p)).collect(Collectors.toList());
		} catch (IOException e) {
			validPaths = new ArrayList<>();
		}
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		final String METHOD = exchange.getRequestMethod().toUpperCase();
		// Print out method and request url for debugging
		if (!server.isSILENT())
			System.out.printf("%s %s\n", METHOD, exchange.getRequestURI().getPath());
		if (METHOD.equals("GET")) {
			String path = exchange.getRequestURI().getPath();
			byte[] content;
			String contentType;
			int status;
			// set cors headers if enabled
			if (server.isCORS())
				exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
			if (validPaths.contains(Paths.get(pathJoin(server.getRootDir(), path)))) {
				if (Files.isDirectory(Paths.get(pathJoin(server.getRootDir(), path)))) {
					// autoserve index.html if it exists in the first level of root folder structure
					if (server.isAUTOINDEX() && Files.walk(Paths.get(pathJoin(root, path)), 1).anyMatch(p -> p.endsWith("index.html"))) {
						content = readResource(new FileInputStream(pathJoin(root, path, "index.html")));
					} else {
						content = generateHTML(pathJoin(server.getRootDir(), path), path).getBytes();
					}
					contentType = "text/html";
				} else {

					content = readResource(new FileInputStream(pathJoin(server.getRootDir(), path)));
					contentType = getContentType(path);
				}
				status = 200;
			} else {
				content = "404 Not Found".getBytes();
				contentType = "text/plain";
				status = 404;
			}
			exchange.getResponseHeaders().set("Content-Type", contentType);
			exchange.sendResponseHeaders(status, content.length);
			exchange.getResponseBody().write(content);
			exchange.getResponseBody().close();

		} else {
			exchange.getResponseHeaders().set("Content-Type", "text/plain");
			exchange.sendResponseHeaders(501, "501 Not implemented".length());
			exchange.getResponseBody().write("501 Not implemented".getBytes());
			exchange.getResponseBody().close();
		}


	}
}