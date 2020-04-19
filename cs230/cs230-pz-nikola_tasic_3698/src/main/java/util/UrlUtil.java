package util;

public class UrlUtil {
	public static String getUrlBase(String url){
		String[] parts = url.split("/");
		return parts[parts.length - 1];
	}
}
