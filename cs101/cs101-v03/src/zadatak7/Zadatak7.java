package zadatak7;
import javax.swing.JOptionPane;


public class Zadatak7 {
    public static void main(String[] args){
        new Zadatak7();
    }
    public Zadatak7(){
        float b,p;
        JOptionPane  input = new JOptionPane(),output = new JOptionPane();
        b = Integer.parseInt(input.showInputDialog(input,"Unesite mesecni budzet:"));
        p = Integer.parseInt(input.showInputDialog(input,"Unesite nedeljnu potrosnju:"));
        float d = p * 4;
        if (d > b) {
            output.showMessageDialog(output,String.format("Trebalo bi manje da trosite. Vas budzet je u minusu %.2f", d - p));
        } else if (d < p) {
            output.showMessageDialog(output,String.format("Vas budzet je OK. Vas budzet je u plusu %.2f", d - p));
        } else {
            output.showMessageDialog(output,"Vas budzet je OK. Na nuli ste.");
        }

    }
}
