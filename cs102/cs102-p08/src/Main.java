import java.util.HashMap;

public class Main {
	    public static void main(String[] args){
	    	String input = "neki test text test";
	    	String[] words = input.split(" ");
		    HashMap<String, Integer> map = new HashMap<>();
		    for (String word: words) {
		    	if (map.containsKey(wFord)) {
				    map.put(word, map.get(word) + 1);
			    } else {
		    		map.put(word, 1);
			    }
		    }
		    System.out.println(map.toString());
	    }
}
