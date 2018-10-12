
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package3;
import java.util.Scanner;

/**
 *
 * @author student
 */
public class Package3 {
    public static void main(String[] args){
        new Package3();
    }
    public Package3(){
        double r;
        double p;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter r: ");
        r = input.nextDouble();
        p = Math.pow(r, 2) * Math.PI;
        System.out.printf("Area is %.2f \n", p);
    }
}
