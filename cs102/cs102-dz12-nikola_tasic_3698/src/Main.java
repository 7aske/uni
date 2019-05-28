import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        DataBase.createDB();
        DataBase.createFakulteti();
        DataBase.createStudentTable();
        /*
            1.Kreirajte bazu
            2.Ukoliko baza nije popunjena pozovite metodu DataBase.insertIntoTable();

         */
        Scanner input = new Scanner(System.in);

        System.out.println("Unesite donju granicu");
        int num1 = input.nextInt();

        System.out.println("Unesite gornju granicu");
        int num2 = input.nextInt();

        System.out.println(DataBase.readTable(num1, num2));
    }

}
