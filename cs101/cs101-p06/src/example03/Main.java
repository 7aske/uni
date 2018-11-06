package example03;

public class Main {
	public static void main(String[] args) {
		Die die = new Die();
		for (int i = 0; i < 6; i++) {
			System.out.printf("%d\n", die.throwDie());
		}
	}

}

class Die {
	int n;

	public int throwDie() {
		this.n = (int) (Math.random() * 6) + 1;
		return n;
	}
}
