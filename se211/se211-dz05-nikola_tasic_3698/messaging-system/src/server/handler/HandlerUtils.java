package server.handler;

import http.Request;
import server.database.DBController;
import server.user.User;

import java.util.HashMap;

public class HandlerUtils {

	public static String getToken(Request request) {
		String token = "";
		if (request.containsHeader("Token")) {
			token = request.getHeader("Token").getValue();
		} else if (request.containsHeader("Cookie")) {
			String[] cookie = request.getHeader("Cookie").getValue().split("=");
			if (cookie.length == 2) {
				token = cookie[1];
			}
		}
		return token;
	}
	public static boolean verifyLoginForm(HashMap<String, String> form) {
		if (form.containsKey("username") && form.containsKey("password")) {
			User user = DBController.getUser(form.get("username"));
			if (user != null) {
				return user.checkPassword(form.get("password"));
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	public static boolean verifyRegisterForm(HashMap<String, String> form) {
		for (String f : User.fieldList) {
			if (!form.containsKey(f)) {
				return false;
			}
		}
		return true;
	}

}
