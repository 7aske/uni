package individualni4;

import javax.swing.*;

public class Individualni4 {
    public static void main(String[] args){
        new Individualni4();
    }
    public Individualni4(){
        String out;
        float temp = Float.parseFloat(JOptionPane.showInputDialog(null,"Unesite temperaturu:"));
        if (36.3 < temp && temp < 37.1){
            out = "OK";
        } else {
            out = (temp <= 36.3) ? "snizena" : "povisena";
        }
        JOptionPane.showMessageDialog(null,String.format("Vasa temperatura je %s.",out));
    }
}
