package zadatak5;

public class Zadatak5 {
    public static void main(String[] args) {
        new Zadatak5();
    }
    private Zadatak5() {
        String r = "";
        for (int i = 0; i < 10; i++) {
            char a = (char) Math.floor(Math.random() * 26 + 97);
            if (i == 0) {
                r += (char) (a - 32);
            } else {
                r += a;
            }
        }
        System.out.print(r);
    }
}
