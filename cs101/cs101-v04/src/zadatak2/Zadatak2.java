package zadatak2;

import java.util.Scanner;

public class Zadatak2 {
    public static void main(String[] args) {
        new Zadatak2();
    }

    public Zadatak2() {
        Scanner input = new Scanner(System.in);
        double a, r;
        System.out.print("Enter a function(exp, round, floor, ceil): ");
        String choice = input.next();
        switch (choice) {
            case "exp":
                System.out.print("Enter a number: ");
                a = input.nextDouble();
                r = Math.exp(a);
                System.out.println(r);
                break;
            case "round":
                System.out.print("Enter a number: ");
                a = input.nextDouble();
                r = Math.round(a);
                System.out.printf("%.0f\n",r);
                break;
            case "floor":
                System.out.print("Enter a number: ");
                a = input.nextDouble();
                r = Math.floor(a);
                System.out.printf("%.0f\n",r);
                break;
            case "ceil":
                System.out.print("Enter a number: ");
                a = input.nextDouble();
                r = Math.ceil(a);
                System.out.printf("%.0f\n",r);
                break;
            default:
                System.out.println("Invalid choice.");

        }
    }
}
