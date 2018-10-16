package individualni3;
import javax.swing.JOptionPane;
public class Individualni3 {
    public static void main(String[] args){
        new Individualni3();
    }
    public Individualni3(){
        int a;
        float p;
        a = Integer.parseInt(JOptionPane.showInputDialog(null,"Unesite ocenu"));
        JOptionPane.showMessageDialog(null,prosek(a));

    }
    public String prosek(int p){
        switch (p) {
            case 5:
                return "Odlican";
            case 4:
                return "Vrlo Dobar";
            case 3:
                return "Dobar";
            case 2:
                return "Dovoljan";
            case 1:
                return "Nedovoljan";
            default:
                return "Prosek mora biti od 1 do 5";
        }
    }

}
