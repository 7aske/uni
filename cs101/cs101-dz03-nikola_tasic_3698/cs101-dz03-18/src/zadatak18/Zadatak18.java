package zadatak18;
import java.util.Scanner;
public class Zadatak18 {
    public static void main(String[] args){
        new Zadatak18();
    }
    private Zadatak18(){
        int a,b,c,o;
        a = safeInput();
        b = safeInput();
        c = safeInput();
        if (a > b)
            if(a > c)
                o = a;
            else
                o = c;
        else if(b > c)
            o = b;
        else
            o = c;
        System.out.printf("Najveci broj je: %d",o);
    }
    private int safeInput(){
        Scanner input = new Scanner(System.in);
        System.out.print("Unesite ceo broj: ");
        while(!input.hasNextInt()){
            System.out.print("Unesite ceo broj: ");
            input.nextLine();
        }
        return input.nextInt();
    }
}
