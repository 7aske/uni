package zadatak1;
import java.util.Scanner;
public class Zadatak1 {
    public static void main(String[] args) {
        new Zadatak1();
    }
    public Zadatak1(){
        Scanner input = new Scanner(System.in);
        double y,x;
        System.out.print("Unesite x: ");
        x = input.nextDouble();
        y = (Math.pow(x,3) - 4) / (Math.sqrt(Math.floor(5*x)));
        System.out.printf("%.2f\n",y);
    }
}
