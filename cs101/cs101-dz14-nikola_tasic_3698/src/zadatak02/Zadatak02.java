package zadatak02;

import java.io.*;


public class Zadatak02 {
	public static void main(String[] args) throws IOException {
		File file = new File("zadatak02.txt");
		StringBuilder content = new StringBuilder();
		if (file.exists()) {
			String line;
			BufferedReader reader = new BufferedReader(new FileReader(file),16384);
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				content.append(line);
			}
			reader.close();
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		if (content.toString().length() != 0){
			writer.write(content.toString() + "\n");
		}
		for (int i = 0; i < 100; i++) {
			char c = getChar(i);
			writer.write(c);
		}
		writer.close();
	}

	private static char getChar(int i) {
		int num = (int) (Math.random() * 26 + 65);
		if (i % 2 == 0) {
			return (char) (num + 32);
		} else {
			return (char) num;
		}
	}
}
