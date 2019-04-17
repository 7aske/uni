package e00;

import java.util.Random;

public class Main {
	public static void main(String[] args) {
		Random rand = new Random();

		Runnable flipCoin = () -> {
			int heads = 0, tails = 0;
			for (int i = 1000; i > 0; i--) {
				int flip = rand.nextInt(2);
				if (flip == 0) {
					heads++;
				} else {
					tails++;
				}
			}
			System.out.printf("Heads: %d Tails: %d\n", heads, tails);
		};
		Thread t0 = new Thread(flipCoin);
		Thread t1 = new Thread(flipCoin);
		Thread t2 = new Thread(flipCoin);
		Thread t3 = new Thread(flipCoin);
		Thread t4 = new Thread(flipCoin);
		t0.start();
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}
