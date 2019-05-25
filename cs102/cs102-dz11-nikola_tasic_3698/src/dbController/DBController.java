package dbController;

import flightReservation.FlightReservation;

import java.sql.*;
import java.util.ArrayList;

public class DBController {
	private static final String dbUrl = "jdbc:sqlite://" + System.getProperty("user.dir") + "/database/flights.db";
	private static final String sqlInit = "create table if not exists flights (" +
			"flight_id integer primary key autoincrement," +
			"flight_date numeric not null," +
			"flight_reserved_by varchar(20) not null," +
			"flight_from_to varchar(8) not null," +
			"flight_passengers integer not null," +
			"flight_price float not null" +
			")";
	private static final String sqlReset = "drop table if exists flights";
	private static final String removeFlightSql = "delete from flights where flight_id = ?";
	private static final String addFlightSql = "insert into flights (flight_date, flight_reserved_by, flight_from_to, flight_passengers, flight_price) values (?, ?, ?, ?, ?)";
	private static final String updateFlightSql = "update flights set flight_date = ?, flight_reserved_by = ?, flight_from_to = ?, flight_passengers = ?, flight_price = ? where flight_id = ?";
	private static final String getAllFlightsSql = "select * from flights";

	public static void initDatabase() {
		try (Connection conn = DriverManager.getConnection(dbUrl);
		     Statement stmt = conn.createStatement()) {
			// stmt.execute(sqlReset);
			stmt.execute(sqlInit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<FlightReservation> getAllFlights() {
		ArrayList<FlightReservation> out = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(dbUrl);
		     Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(getAllFlightsSql);
			while (rs.next()) {
				long id = rs.getLong(1);
				Date date = new Date(rs.getLong(2));
				String reservedBy = rs.getString(3);
				String fromTo = rs.getString(4);
				short passengers = rs.getShort(5);
				double price = rs.getDouble(6);
				final FlightReservation f = new FlightReservation(id, date, reservedBy, fromTo, passengers, price);
				out.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return out;
	}

	public static boolean addFlight(FlightReservation f) {
		try (Connection conn = DriverManager.getConnection(dbUrl);
		     PreparedStatement stmt = conn.prepareStatement(addFlightSql)) {
			stmt.setLong(1, f.getDate().getTime());
			stmt.setString(2, f.getReservedBy());
			stmt.setString(3, f.getFromTo());
			stmt.setShort(4, f.getNoOfPassengers());
			stmt.setDouble(5, f.getPrice());
			return stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void removeFlight(long id) {
		try (Connection conn = DriverManager.getConnection(dbUrl);
		     PreparedStatement stmt = conn.prepareStatement(removeFlightSql)) {
			stmt.setLong(1, id);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void removeFlight(FlightReservation f) {
		removeFlight(f.getId());
	}

	public static void updateFlight(FlightReservation f) {
		try (Connection conn = DriverManager.getConnection(dbUrl);
		     PreparedStatement stmt = conn.prepareStatement(updateFlightSql)) {
			stmt.setLong(1, f.getDate().getTime());
			stmt.setString(2, f.getReservedBy());
			stmt.setString(3, f.getFromTo());
			stmt.setShort(4, f.getNoOfPassengers());
			stmt.setDouble(5, f.getPrice());
			stmt.setLong(6, f.getId());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
