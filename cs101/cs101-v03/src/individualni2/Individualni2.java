package individualni2;
import javax.swing.JOptionPane;
public class Individualni2 {
    public static void main(String[] args){
        new Individualni2();
    }
    public Individualni2(){
        double cena;
        String lice = "";

        do {
            lice = JOptionPane.showInputDialog(null,"Pravno ili fizicko lice?");
            System.out.print(lice);
        } while (lice == "pravno"||lice == "fizicko");

        cena = Float.parseFloat(JOptionPane.showInputDialog(null,"Unesite cenu bez PDV-a"));
        cena = (lice == "pravno") ? cena * 1.2 : cena * 1.08;

        JOptionPane.showMessageDialog(null,String.format("Cena posle uracunatog PDV-a je %.2f",cena));
    }
}
