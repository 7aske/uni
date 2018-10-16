package zadatak6;
import javax.swing.JOptionPane;


public class Zadatak6 {
    public static void main(String[] args){
        new Zadatak6();
    }
    public Zadatak6(){
        float t,v,i;
        JOptionPane input = new JOptionPane(),output = new JOptionPane();
        t = Float.parseFloat(input.showInputDialog(input,"Unesite tezinu:"));
        v = Float.parseFloat(input.showInputDialog(input,"Unesite visinu:"));
        i = v - 110;
        if (i == t) {
            output.showMessageDialog(output,"Vasa tezina je OK");
        } else if (i > t ){
            output.showMessageDialog(output,"Trebalo bi da se ugojite "+ Math.abs(i-t));
        } else {
            output.showMessageDialog(output,"Trebalo bi da smrsate " + Math.abs(i-t));
        }

    }
}
