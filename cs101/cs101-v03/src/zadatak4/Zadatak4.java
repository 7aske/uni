package zadatak4;
import javax.swing.JOptionPane;


public class Zadatak4 {
    public static void main(String[] args){
        new Zadatak4();
    }
    public Zadatak4(){
        int g;
        JOptionPane input = new JOptionPane(),output = new JOptionPane();
        g = Integer.parseInt(input.showInputDialog(input,"Unesite godinu:"));
        boolean isLeap = (g % 4 == 0 && g % 100 != 0) || (g % 400 == 0);
        String out = String.format("Godina %s prestupna.",isLeap?"je":"nije");
        output.showMessageDialog(output,out);

    }
}
