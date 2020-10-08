package rs.ac.metropolitan.cs230.rsclient;

public class StavkaMain {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		StavkaClient sc = new StavkaClient();
		String stavke1 = sc.find_XML(String.class, "1");
		String stavke2 = sc.find_JSON(String.class, "1");

		System.out.println(stavke1);
		System.out.println(stavke2);

		sc.close();
	}

}
