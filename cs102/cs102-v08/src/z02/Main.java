package z02;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		HashMap<Character, Integer> scores = new HashMap<>();
		Stack<Character> alphabet = new Stack<>();
		for (int i = 'D'; i >= 'A'; i-- ){
			alphabet.push((char)i);
		}
		while(!alphabet.isEmpty()) {
			System.out.print("Enter a character: ");
			char input = (char)(scanner.next().charAt(0) - 32);
			if (alphabet.peek() == input) {
				alphabet.pop();
				System.out.println("You guessed correct!");

			} else {
				System.out.println("Wrong. Try Again.");
				if (!scores.containsKey(alphabet.peek())) {
					scores.put(alphabet.peek(), -1);
				} else {
					scores.put(alphabet.peek(), scores.get(alphabet.peek()) - 1);
				}
			}
		}
		System.out.println(scores.toString());
		Map.Entry<Character, Integer> worst = scores.entrySet().iterator().next();
		for (Map.Entry<Character, Integer> c: scores.entrySet()){
			if (c.getValue() < worst.getValue()){
				worst = c;
			}

		}
		System.out.println("Worst performance on character: " + worst.getKey());
	}
}
