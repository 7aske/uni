package server.user;

import server.database.DBController;
import utils.Encryption;

import java.time.Instant;
import java.util.HashMap;

public class UserUtils {


	public static String generateToken(User user) {
		long unixTime = Instant.now().getEpochSecond();
		String token = String.format("expiresAt=%d&issuer=server&user=%s", unixTime + 86400, user.getUsername());
		return Encryption.encrypt(token);
	}

	public static boolean validateToken(User user, String token) {
		return validateToken(user.getUsername(), token);
	}

	public static boolean validateToken(String user, String token) {
		if (DBController.getUser(user) == null) {
			return false;
		}
		long unixTime = Instant.now().getEpochSecond();
		String dToken = Encryption.decrypt(token);
		if (dToken != null) {
			String[] fields = dToken.split("&");
			for (String f : fields) {
				String[] parts = f.split("=");
				switch (parts[0]) {
					case "expiresAt":
						if (Integer.parseInt(parts[1]) - unixTime <= 0) {
							return false;
						}
						break;
					case "issuer":
						if (!parts[1].equals("server"))
							return false;
						break;
					case "user":
						if (!user.equals(parts[1]))
							return false;
						break;
					default:
						return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	public static boolean isValid(User user) {
		if (user.getUsername() == null)
			return false;
		if (user.getFirstName() == null)
			return false;
		if (user.getLastName() == null)
			return false;
		if (user.getEmail() == null)
			return false;
		if (user.getPhone() == null)
			return false;
		return user.getCompany() != null;
	}
}
