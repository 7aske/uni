package zadatak4;

public class Zadatak4 {
    public static void main(String[] args) {
        new Zadatak4();
    }
    private Zadatak4() {
        String text = "Napraviti program koji menja svaku reč Jabuka u tekstu u Kruška i svaku reč Put u tekstu u\n" +
                "Autoput. Ispisati prva 3 i zadnja 3 karaktera izmenjenog teksta. Napomena za zamenu reči u\n" +
                "tekstu koristiti. Replace(“stara reč”,”nova reč”);\n";
        text = text.replace("Jabuka","Kruška");
        text = text.replace("Put","Autoput");
        System.out.print(text);
        String first = text.substring(0,3);
        String last = text.substring(text.length()-3);
        System.out.printf("Prva tri: %s\nPoslednja tri: %s", first, last);
    }
}
