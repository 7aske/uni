package zadatak02;

import java.util.ArrayList;
import java.util.Scanner;

public class Zadatak02 {
	public static void main(String[] args) {
		new Zadatak02();
	}

	public Zadatak02() {
		Scanner input = new Scanner(System.in);
		System.out.printf("%s", "Enter a string: ");
		String toBeShuffled = input.nextLine();
		System.out.printf("%s", shuffle(toBeShuffled));
		input.close();
	}
	public static String shuffle(String string){
		String out = "";
		ArrayList<Character> in = new ArrayList<>();
		for(char c:string.toCharArray()){
			in.add(c);
		}
		while (out.length() != string.length()){
			int random = (int)(Math.random() * in.size());
			out += in.get(random);
			in.remove(random);
		}
		return out;
	}
}
