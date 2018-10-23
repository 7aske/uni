package zadatak8;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Zadatak8 {
    public static void main(String[] args) {
        new Zadatak8();
    }

    private Zadatak8() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an ASCII code: ");
        byte a;
        try {
            a = input.nextByte();
            if (a >= 0) {
                char c = (char) a;
                System.out.printf("%d is \"%c\" in ascii table.", a, c);
            }
        } catch (InputMismatchException e){
            System.exit(1);
        }



    }
}
