import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DataBase {
	private static Connection connection = null;
	private static String url = "jdbc:mysql://localhost:3306";
	private static String username = "root";
	private static String password = "";
	private static String databaseName = "cs102dz12";

	public static void openConection() throws SQLException {
		connection = DriverManager.getConnection(url + "/" + databaseName, username, password);
	}

	public static void createDB() {
		try {
			openConection();
			PreparedStatement preparedStatement = connection.prepareStatement("create database if not exists cs102dz12");
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createFakulteti() {
		try {
			openConection();
			PreparedStatement preparedStatement = connection.prepareStatement("create table if not exists fakulteti (\n" +
					"    id              int auto_increment primary key,\n" +
					"    naziv           longtext,\n" +
					"    grad            varchar(15),\n" +
					"    adresa          longtext,\n" +
					"    kontakt_telefon longtext\n" +
					");");
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createStudentTable() {
		try {
			openConection();
			PreparedStatement preparedStatement = connection.prepareStatement("create table if not exists studenti\n" +
					"(\n" +
					"    ime          varchar(20),\n" +
					"    prezime      varchar(20),\n" +
					"    broj_indeksa varchar(30) primary key,\n" +
					"    godina_upisa int,\n" +
					"    status       varchar(10),\n" +
					"    id_fakulteta int not null,\n" +
					"    foreign key (id_fakulteta) references fakulteti (id)\n" +
					");");
			preparedStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ObservableList<Student> readTable(int num1, int num2) {
		ObservableList<Student> studentObservableList = FXCollections.observableArrayList();
		ObservableList<String> filteredList = FXCollections.observableArrayList();

		try {
			openConection();
			PreparedStatement preparedStatement = connection.prepareStatement("select ime, prezime, broj_indeksa,godina_upisa, k.naziv, k.grad\n" +
					"from studenti\n" +
					"         join fakulteti k on studenti.id_fakulteta = k.id");

			ResultSet resultSet = preparedStatement.executeQuery();

			int i = 0;
			while (resultSet.next()) {
				Student student = new Student();

				student.setIme(resultSet.getString(1));
				student.setPrezime(resultSet.getString(2));
				student.setBrojIndeksa(resultSet.getString(3));
				student.setGodinaUpisa(resultSet.getInt(4));
				student.setNazivFakulteta(resultSet.getString(5));
				student.setGrad(resultSet.getString(6));

				filteredList.add(student.getNazivFakulteta());
				String fax = filteredList.get(i);
				String faxLc = fax.toLowerCase();

				if (num1 > num2) {
					System.out.println("Invalidna pretraga");
					break;
				}
				if (faxLc.indexOf("info") != -1 && student.getGodinaUpisa() >= num1 && student.getGodinaUpisa() <= num2) {
					studentObservableList.add(student);
				}
				i++;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return studentObservableList;
	}

	public static void emptyMessage() {
		System.out.println("Prazna pretraga");
	}

	public static void insertIntoTable() {
		try {
			openConection();
			PreparedStatement preparedStatement1 = connection.prepareStatement("insert into fakulteti(id, naziv, grad, adresa, kontakt_telefon)\n" +
					"VALUES (0, \"Univerzitet Metropolitan\", \"Beograd\", \"Tadeusa Koscuska\", \"011345124\");");

			PreparedStatement preparedStatement2 = connection.prepareStatement("insert into fakulteti(id, naziv, grad, adresa, kontakt_telefon)\n" +
					"VALUES (0, \"Informacione Tehnologije\", \"Novi sad\", \"Jovana Cvijica 2\", \"015345124\");");

			PreparedStatement preparedStatement3 = connection.prepareStatement("insert into fakulteti(id, naziv, grad, adresa, kontakt_telefon)\n" +
					"VALUES (0, \"PMF-Informatika\", \"Nis\", \"bb\", \"085345124\");");

			PreparedStatement preparedStatement4 = connection.prepareStatement("insert into fakulteti(id, naziv, grad, adresa, kontakt_telefon)\n" +
					"VALUES (0, \"Pravni fakultet\", \"Kragujevac\", \"Karadjordjeva 10\", \"085345124\");");

			PreparedStatement preparedStatement5 = connection.prepareStatement("insert into studenti (ime, prezime, broj_indeksa, godina_upisa, status, id_fakulteta)\n" +
					"VALUES (\"Dimitrije\", \"Ilic\", \"4207\", \"2017\", \"Studira\", 1);");

			PreparedStatement preparedStatement6 = connection.prepareStatement("insert into studenti (ime, prezime, broj_indeksa, godina_upisa, status, id_fakulteta)\n" +
					"VALUES (\"Jovana\", \"Miljkovic\", \"1233/2013\", \"2013\", \"Studira\", 2);");

			PreparedStatement preparedStatement7 = connection.prepareStatement("insert into studenti (ime, prezime, broj_indeksa, godina_upisa, status, id_fakulteta)\n" +
					"VALUES (\"Marko\", \"Jankovic\", \"6543/2015\", \"2015\", \"Studira\", 3);");

			PreparedStatement preparedStatement8 = connection.prepareStatement("insert into studenti (ime, prezime, broj_indeksa, godina_upisa, status, id_fakulteta)\n" +
					"VALUES (\"Milica\", \"Markovic\", \"1235\", \"2018\", \"Studira\", 4);");

			preparedStatement1.execute();
			preparedStatement2.execute();
			preparedStatement3.execute();
			preparedStatement4.execute();
			preparedStatement5.execute();
			preparedStatement6.execute();
			preparedStatement7.execute();
			preparedStatement8.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
