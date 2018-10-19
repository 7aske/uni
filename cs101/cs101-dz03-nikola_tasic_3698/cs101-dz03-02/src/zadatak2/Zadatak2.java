package zadatak2;

import java.util.Scanner;

public class Zadatak2 {

    public static void main(String[] args) {
        new Zadatak2();
    }

    public Zadatak2() {
        Scanner input = new Scanner(System.in);
        String type = "";
        float price;

        System.out.print("Unesite cenu: ");
        while (!input.hasNextFloat()) {
            System.out.print("Unesite cenu: ");
        }
        price = input.nextFloat();

        System.out.print("Akcija ili regularna (a/r): ");
        type = input.next();

        switch (type){
            case "regularna":
                price *= 1.2f;
                System.out.printf("Nova cena je %.2f",price);
                break;
            case "akcija":
                price *= 0.95f;
                System.out.printf("Nova cena je %.2f",price);
                break;
            case "r":
                price *= 1.2f;
                System.out.printf("Nova cena je %.2f",price);
                break;
            case "a":
                price *= 0.95f;
                System.out.printf("Nova cena je %.2f",price);
                break;
            default:
                System.out.printf("Niste izabrali \"akcija\" ili \"regularna\".\nCena ostaje nepromenjena: %.2f\n",price);
        }
    }
}
