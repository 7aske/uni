package example0;

public class Example0 {
	public static void main(String[] args) {
		System.out.printf("%d", fib(5));
		System.out.printf("%d", mult(12));

	}

	public static int fib(int n) {
		int a = 1, b = 2, c = 0;
		for (int i = 0; i < n; i++) {
			c = a + b;
			a = b;
			b = c;
		}
		return c;
	}

	public static int mult(int n) {
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			if (i % 2 == 0) {
				sum += i * 3;
			}
		}
		return sum;
	}
}
