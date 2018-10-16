package zadatak5;
import javax.swing.JOptionPane;


public class Zadatak5 {
    public static void main(String[] args){
        new Zadatak5();
    }
    public Zadatak5(){
        int b;
        JOptionPane input = new JOptionPane(),output = new JOptionPane();
        b = Integer.parseInt(input.showInputDialog(input,"Unesite broj:"));
        if (b % 3 == 0 && b % 2 == 0) {
            output.showMessageDialog(output,String.format("Broj %d je deljiv i sa 2 i sa 3",b));
        } else if (b % 3 == 0) {
            output.showMessageDialog(output,String.format("Broj %d je deljiv 3",b));
        } else if (b % 2 == 0) {
            output.showMessageDialog(output,String.format("Broj %d je deljiv i sa 2",b));
        } else {
            output.showMessageDialog(output,String.format("Broj %d nije deljiv ni sa 2 ni sa 3",b));
        }

    }
}
