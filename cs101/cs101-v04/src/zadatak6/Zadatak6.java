package zadatak6;
import java.util.Scanner;
public class Zadatak6 {
    public static void main(String[] args){
        new Zadatak6();
    }
    private Zadatak6(){
        Scanner input = new Scanner(System.in);
        String text = input.next();
        for(int i = 0; i < 10; i++){
            text += (char)((int)(Math.random()*26+97));
        }
        text = text.replaceAll("a","b");
        System.out.print(text);
    }
}
