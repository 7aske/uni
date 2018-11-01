package example03;

public class Example03 {
	public static void main(String[] args) {
		new Example03();
	}
	private Example03(){
		int n = 5;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(i == j)
					System.out.print("*");
				else if (n - 1 == i + j)
					System.out.print("*");
				else
					System.out.print(" ");
			}
			System.out.print("\n");
		}
	}
}
