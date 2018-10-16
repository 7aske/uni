package zadatak3;
import javax.swing.JOptionPane;


public class Zadatak3 {
    public static void main(String[] args){
        new Zadatak3();
    }
    public Zadatak3(){

        int b,r;
        JOptionPane input = new JOptionPane(),output = new JOptionPane();
        b = Integer.parseInt(input.showInputDialog(input,"Unesite broj:"));
        input.setVisible(true);
        r = b % 10 + b % 100 / 10 + b / 100;
        output.showMessageDialog(output,String.format("Zbir cifara broja %d je %d",b,r));
    }
}
