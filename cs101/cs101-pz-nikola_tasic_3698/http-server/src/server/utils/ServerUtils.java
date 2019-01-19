package server.utils;

import java.nio.file.Paths;

public class ServerUtils {
	public static String pathJoin(String... path) {
		String out = path[0];
		String sep = !System.getProperty("os.name").contains("win") ? "/" : "\\";
		for (int i = 1; i < path.length; i++) {
			if (path[i].startsWith("../")) {
				out = Paths.get(out).getParent().toString() + path[i].substring(2);
			} else if (path[i].startsWith("./")) {
				out = Paths.get(out).toString() + path[i].substring(1);
			} else if (path[i].startsWith("/")) {
				out = Paths.get(out).toString() + path[i];
			} else {
				out = Paths.get(out).toString() + sep + path[i];
			}
		}
		return out;
	}
}
