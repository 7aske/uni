package zadatak2;
import javax.swing.JOptionPane;


public class Zadatak2 {
    public static void main(String[] args){
        new Zadatak2();
    }
    public Zadatak2(){
        // 1 foot 0.3058
        final float k = 0.3058f;
        int s;
        JOptionPane input = new JOptionPane(),output = new JOptionPane();
        s = Integer.parseInt(input.showInputDialog(input,"Unesite vrednost u stopama:"));
        input.setVisible(true);
        output.showMessageDialog(output,String.format("%d stopa isnosi %.2f metara ",s,s * k));
    }
}
