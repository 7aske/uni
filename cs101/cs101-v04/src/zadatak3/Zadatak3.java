package zadatak3;

import java.util.Scanner;

public class Zadatak3 {
    public static void main(String[] args) {
        new Zadatak3();
    }

    public Zadatak3() {
        Scanner input = new Scanner(System.in);
        double x, x1, x2, r;
        x = input.nextDouble();
        x1 = (Math.pow(x, 3) - 4) / (Math.sqrt(Math.floor(5 * x)));
        x2 = (Math.pow(x, 3) - 5 * Math.pow(x, 3) - 6 * x + 2) / (Math.sqrt(2 * x));
        r = x1 * x2 / Math.sqrt(x);
        System.out.println(r);
    }
}
