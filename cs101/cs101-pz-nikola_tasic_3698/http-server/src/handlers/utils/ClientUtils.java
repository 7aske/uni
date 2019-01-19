package handlers.utils;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ClientUtils {
	private static String HTMLUpper =
			"<!DOCTYPE html>\n" +
					"<html lang=\"en\">\n" +
					"<head>\n" +
					"<style>" +
					"*{font-family:Ubuntu,Calibri}" +
					"a{text-decoration:none;font-size:20px;}" +
					"a:hover{color:red!important;}"+
					"</style>" +
					"<meta charset=\"UTF-8\">\n" +
					"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
					"<meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
					"<title></title>\n" +
					"</head>\n";
	private static String HTMLLower =
			"<body>\n" +
					"</body>\n" +
					"</html>";

	public static String generateHTML(String root, String rel) throws IOException {
		String sep = rel.endsWith("/") ? "" : "/";
		String out = HTMLUpper;
		out += "<a style=\"font-size:24px\" href=\"/\">" + rel + "</a><hr>";
		// some weird lambda sorting
		// first one sorts files an folders, second one sorts back button to the top
		List<Path> list = Files.walk(Paths.get(root), 1).sorted((o1, o2) -> Files.isDirectory(o1) ? -1 : 1).collect(Collectors.toList());
		list.sort((o1,o2)->o1.getFileName().toString().equals(Paths.get(root).getFileName().toString())?-1:1);
		for (Path p : list) {
			// Check if the current folder to be rendered the one whoose contents are being rendered
			// If true render it as a back button
			if (p.getFileName().toString().equals(Paths.get(root).getFileName().toString())){
				String parent;
				// resolving whether the current folder root of the folder tree
				// trying to get parent of the root folder raises an error
				try {
					parent = Paths.get(rel).getParent().toString();
				} catch (Exception e){
					parent = "/";
				}
				out += String.format("<a style=\"text-decoration:underline;color:black;\" href=\"%s\">&larr;%s</a><br>", parent, "Back");
			} else if (Files.isDirectory(p)) {
				out += String.format("<a style=\"color:black;\" href=\"%s/\">&#128193;%s/</a><br>", rel + sep + p.getFileName().toString(), p.getFileName());
			} else if (Files.isRegularFile(p)) {
				out += String.format("<a style=\"color:blue;\" href=\"%s\">&#128462;%s</a><br>", rel + p.getFileName(), p.getFileName());
			}

		}
		out += HTMLLower;
		return out;
	}
}
