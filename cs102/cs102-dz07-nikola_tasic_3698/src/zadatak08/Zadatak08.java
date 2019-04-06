package zadatak08;

import java.util.ArrayList;
import java.util.List;

public class Zadatak08 {
	public static void main(String[] args) {
		ArrayList<Object> strings = new ArrayList<>();
		strings.add("Geeks");
		strings.add("for");
		strings.add("Geeks");

		ArrayList<Integer> indices = getIndices(strings, "Geeks");
		for (Integer i : indices) {
			System.out.println(i);
		}
	}
	public static ArrayList<Integer> getIndices(ArrayList<Object> arr, Object x){
		ArrayList<Integer> out = new ArrayList<>();
		for (int i = 0; i < arr.size(); i++) {
			Object o = arr.get(i);
			if (o.equals(x)){
				out.add(i);
			}
		}
		return out;
	}
}
