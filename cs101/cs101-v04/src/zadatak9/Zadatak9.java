package zadatak9;

import java.util.Scanner;

public class Zadatak9 {
    public static void main(String[] args) {
        new Zadatak9();
    }

    private Zadatak9() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a year: ");
        int year = input.nextInt();
        System.out.print("Enter a month: ");
        String month = input.next();
        String out = "January";
        int nDays;
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        for (int i = 0; i < months.length; i++) {
            if (months[i].toUpperCase().startsWith(month.toUpperCase())) {
                out = months[i];
                break;
            }
        }
        switch (out) {
            case "April":
            case "June":
            case "September":
            case "November":
                nDays = 30;
                break;
            case "February":
                nDays = ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) ? 29 : 28;
                break;
            default:
                nDays = 31;
        }
        System.out.printf("In %s month of %s has %d days.", year, out, nDays);
    }
}
