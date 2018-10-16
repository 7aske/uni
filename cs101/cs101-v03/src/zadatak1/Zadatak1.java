package zadatak1;
import javax.swing.JOptionPane;


public class Zadatak1 {
    public static void main(String[] args){
        new Zadatak1();
    }
    public Zadatak1(){
        final float k = 118.2366f;
        float d;
        JOptionPane input = new JOptionPane(),output = new JOptionPane();
        d = Float.parseFloat( input.showInputDialog(input,"Unesite vrednost u dinarima:"));
        input.setVisible(true);
        output.showMessageDialog(output,String.format("Iznos od %.2f isnosi %.2f eura po kursu od %.2f",d,d / k,k));
    }
}
