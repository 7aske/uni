package grupa1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Zadatak18 {
	public static void main(String[] args) {
		new Zadatak18();
	}

	public Zadatak18() {
		Scanner input = new Scanner(System.in);
		int a = 0, b = 0;
		System.out.printf("Collection A length: ");
		while (!input.hasNextInt()) {
			System.out.printf("Collection A length: ");
			input.nextLine();
		}
		a = input.nextInt();

		System.out.printf("Collection B length: ");
		while (!input.hasNextInt()) {
			System.out.printf("Collection B length: ");
			input.nextLine();
		}

		b = input.nextInt();

		String[] array_a = new String[a];
		String[] array_b = new String[b];

		for (int i = 0; i < a; i++) {
			System.out.printf("Enter %s A element: ", formatOrdinal(i));
			array_a[i] = input.next();
		}
		for (int j = 0; j < b; j++) {
			System.out.printf("Enter %s B element: ", formatOrdinal(j));
			array_b[j] = input.next();
		}
		System.out.printf("\nCollection difference of A and B is: \n %s", arrayString(getDiff(array_a,array_b)));
		System.out.printf("\nCollection difference of B and A is: \n %s", arrayString(getDiff(array_b,array_a)));


	}
	public static String[] getDiff(String[] a, String[] b){
		ArrayList<String> outList = new ArrayList<>();
		for(int i = 0; i < a.length; i++){
			if (Arrays.asList(b).indexOf(a[i])== -1){
				outList.add(a[i]);
			}
		}
		String[] outArray = new String[outList.size()];
		outArray = outList.toArray(outArray);
		return outList.toArray(outArray);
	}

	public static String arrayString(String[] a){
		String out = "";
		for (int j = 0; j < a.length; j++) {
			out += String.format("%s ", a[j]);
		}
		return  out;
	}

	public static String formatOrdinal(int x) {
		if (x == 0) {
			return "1st";
		} else if (x == 1) {
			return "2nd";
		} else if (x == 2) {
			return "3rd";
		} else {
			return String.format("%dth", x + 1);
		}
	}
}
