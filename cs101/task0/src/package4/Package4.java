
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package4;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author student
 */
public class Package4 {
    public static void main(String[] args){
        new Package4();
    }
    public Package4(){
        Scanner input = new Scanner(System.in);
        //WTF NO DYNAMIC ARRAYS
        List<Integer> ints = new ArrayList<Integer>();
        int a;
        do {
            System.out.print("Enter a number: ");
            a = input.nextInt();
            ints.add(a);
            
        } while (a != 0);
        int sum = 0;
        
        System.out.print();
    }
}
