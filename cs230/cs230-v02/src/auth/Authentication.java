package auth;

import entity.User;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

/**
 * Authentication singleton class
 */
public class Authentication {
	private static final String usersFilename = "/tmp/users.txt";

	private static Authentication instance = null;

	public Authentication() throws IOException {
		FileWriter fileWriter = new FileWriter(usersFilename);
		fileWriter.write("");
		fileWriter.close();
	}

	public static Authentication getAuthentication() throws IOException {
		if (instance == null)
			instance = new Authentication();
		return instance;
	}

	public User getLoggedInUser(String username, String password) throws IOException {
		for (User user : getUserList())
			if (user.getUsername().equals(username) && user.getPassword().equals(password))
				return user;
		return null;
	}

	public void saveUser(User user) throws IOException {
		FileWriter fileWriter = new FileWriter(usersFilename);
		String userstring = user.getUsername() + " " + user.getPassword();
		// Base64 encoding for quasi-security :)
		String b64 = new BASE64Encoder().encode(userstring.getBytes());
		fileWriter.append(b64 + "\n");
		fileWriter.close();
	}

	public boolean userExists(String username) throws IOException {
		for (User user : getUserList())
			if (user.getUsername().equals(username))
				return true;
		return false;
	}

	private static List<User> getUserList() throws IOException {
		FileReader fileReader = new FileReader(usersFilename);

		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<User> userList = new ArrayList<>();

		String line;
		while ((line = bufferedReader.readLine()) != null) {
			String decoded = new String(new BASE64Decoder().decodeBuffer(line), StandardCharsets.UTF_8);
			String[] userParts = decoded.split(" ");

			// handle the situation where password has a space
			String password = String.join(" ", Arrays.asList(Arrays.copyOfRange(userParts, 1, userParts.length)));
			userList.add(new User(userParts[0], password));
		}
		return userList;
	}
}
