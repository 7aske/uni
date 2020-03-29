package server.database;

import server.message.Message;
import server.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBController {

	private static final String DB_URL = "jdbc:sqlite:" + System.getProperty("user.dir") + "/db/database.db";

	public static void initDatabase() {
		String sql = "CREATE TABLE IF NOT EXISTS users (\n"
				+ "	userid      INTEGER PRIMARY KEY AUTOINCREMENT,\n"
				+ "	username    CHARACTER(20) NOT NULL UNIQUE ,\n"
				+ "	password    CHARACTER(20) NOT NULL,\n"
				+ "	email       CHARACTER(20) NOT NULL UNIQUE ,\n"
				+ "	firstName   CHARACTER(20) NOT NULL,\n"
				+ "	lastName    CHARACTER(20) NOT NULL,\n"
				+ "	phone       CHARACTER(20) NOT NULL,\n"
				+ "	company     CHARACTER(20) NOT NULL\n"
				+ ");";
		String sql2 = "CREATE TABLE IF NOT EXISTS messages (\n" +
				"messageid   INTEGER PRIMARY KEY AUTOINCREMENT ,\n" +
				"sentTo      TEXT NOT NULL, \n" +
				"sentFrom    TEXT NOT NULL, \n" +
				"messageText TEXT NOT NULL , \n" +
				"dateSent    INTEGER NOT NULL \n" +
				")";
		try (Connection conn = DriverManager.getConnection(DBController.DB_URL);
		     Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
			stmt.execute(sql2);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public static User getUser(String query) {
		String sql = "SELECT * FROM users WHERE username = ?";
		try (Connection conn = DriverManager.getConnection(DBController.DB_URL);
		     PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1, query);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				// for (int i = 1; i <= 8; i++) {
				// 	System.out.printf("%s %s\n", rs.getMetaData().getColumnLabel(i), rs.getString(i));
				// }
				int id = rs.getInt(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				String email = rs.getString(4);
				String firstName = rs.getString(5);
				String lastName = rs.getString(6);
				String phone = rs.getString(7);
				String company = rs.getString(8);
				return new User(id, username, password, email, firstName, lastName, phone, company);
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


	public static ArrayList<Message> getMessagesFrom(String query) {
		String sql = "SELECT * FROM messages WHERE sentFrom = ?";
		ArrayList<Message> out = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(DBController.DB_URL);
		     PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1, query);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				// for (int i = 1; i <= 8; i++) {
				// 	System.out.printf("%s %s\n", rs.getMetaData().getColumnLabel(i), rs.getString(i));
				// }
				long id = rs.getLong(1);
				String sentTo = rs.getString(2);
				String sentFrom = rs.getString(3);
				String messageText = rs.getString(4);
				long dateSent = rs.getLong(5);
				out.add(new Message(id, sentTo, sentFrom, messageText, dateSent));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(out);
		return out;
	}

	public static ArrayList<Message> getMessagesTo(String query) {
		String sql = "SELECT * FROM messages WHERE sentTo = ?";
		ArrayList<Message> out = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(DBController.DB_URL);
		     PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1, query);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				// for (int i = 1; i <= 8; i++) {
				// 	System.out.printf("%s %s\n", rs.getMetaData().getColumnLabel(i), rs.getString(i));
				// }
				long id = rs.getLong(1);
				String sentTo = rs.getString(2);
				String sentFrom = rs.getString(3);
				String messageText = rs.getString(4);
				long dateSent = rs.getLong(5);
				System.out.println("HERE");
				out.add(new Message(id, sentTo, sentFrom, messageText, dateSent));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return out;
	}

	public static void addUser(User user) {
		String sql = "INSERT INTO users (username, password, email, firstname, lastname, phone, company) \n" +
				"VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = DriverManager.getConnection(DBController.DB_URL);
		     PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getFirstName());
			statement.setString(5, user.getLastName());
			statement.setString(6, user.getPhone());
			statement.setString(7, user.getCompany());
			statement.execute();
		} catch (SQLException e) {
			switch (e.getErrorCode()) {
				case 19:
					System.out.println(e.getMessage());
					break;
				default:
					System.out.println(e.getErrorCode());
					e.printStackTrace();

			}
		}
	}

	public static void addMessage(Message msg) {
		String sql = "INSERT INTO messages (sentTo, sentFrom, messageText, dateSent) \n" +
				"VALUES (?, ?, ?, ?)";

		try (Connection conn = DriverManager.getConnection(DBController.DB_URL);
		     PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1, msg.getSentTo());
			statement.setString(2, msg.getSentFrom());
			statement.setString(3, msg.getMessageText());
			statement.setLong(4, msg.getDateSent());
			statement.execute();
		} catch (SQLException e) {
			switch (e.getErrorCode()) {
				case 19:
					System.out.println(e.getMessage());
					break;
				default:
					System.out.println(e.getErrorCode());
					e.printStackTrace();

			}
		}
	}

	// SQL INJECTION RIGHT THERE WOHOO
	public static ArrayList<User> getUsers(HashMap<String, String> query) {
		ArrayList<User> out = new ArrayList<>();

		StringBuilder sql = new StringBuilder("SELECT * FROM users");
		if (query != null) {
			sql.append(" WHERE ");
			for (Map.Entry<String, String> kv : query.entrySet()) {
				if (sql.length() > 26) {
					sql.append(" OR ");
				}
				sql.append(String.format("%s LIKE '%%%s%%'", kv.getKey(), kv.getValue()));
			}
		}

		try (Connection conn = DriverManager.getConnection(DBController.DB_URL);
		     Statement statement = conn.createStatement()) {
			ResultSet rs = statement.executeQuery(sql.toString());
			while (rs.next()) {
				int id = rs.getInt(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				String email = rs.getString(4);
				String firstName = rs.getString(5);
				String lastName = rs.getString(6);
				String phone = rs.getString(7);
				String company = rs.getString(8);
				out.add(new User(id, username, password, email, firstName, lastName, phone, company));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return out;
	}
}
