package auth;

import entity.User;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

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
		String userstring = user.getUsername().replaceAll(" ", "_") + " " + user.getPassword();
		String b64 = new BASE64Encoder().encode(userstring.getBytes());
		fileWriter.append(b64+"\n");
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
		FileWriter fileWriter = new FileWriter("/tmp/users2.txt");
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		String line;
		while ((line = bufferedReader.readLine()) != null) {

			String decoded = Arrays.toString(new BASE64Decoder().decodeBuffer(line));
			bufferedWriter.append(line);
			String usernamePart = decoded.split(" ")[0];
			String passwordPart = decoded.split(" ")[1];
			userList.add(new User(usernamePart, passwordPart));
		}
		return userList;
	}
}
