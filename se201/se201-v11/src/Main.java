import entity.*;
import exception.WarehouseException;

import java.time.LocalDate;
import java.time.Month;

public class Main {
    public static void main(String[] args) {
        Pharmacy pharmacy = new Pharmacy("Benu");
        Warehouse warehouse1 = new Warehouse(1);
        Warehouse warehouse2 = new Warehouse(2);
        Warehouse warehouse3 = new Warehouse(3);

        try {
            pharmacy.addWarehouse(warehouse1);
            pharmacy.addWarehouse(warehouse2);
            pharmacy.addWarehouse(warehouse3);
        } catch (WarehouseException e){
            e.printStackTrace();
        }

        Medicament medicament1 = new Medicament("Lek1", LocalDate.of(2020, Month.APRIL, 21));
        Medicament medicament2 = new Medicament("Lek2", LocalDate.of(2020, Month.APRIL, 21));
        Medicament medicament3 = new Medicament("Lek3", LocalDate.of(2020, Month.APRIL, 21));
        Medicament medicament4 = new PrescribedMedicament("Lek4", LocalDate.of(2020, Month.APRIL, 21), new Recipe("OPEN SOURCE PLS"));

        Client client = new Client("Mark", "Marks", "1st Avenue");
        Order order1 = new Order();
        order1.addItem(new Item(medicament1, 2));
        order1.addItem(new Item(medicament2, 5));
        order1.addItem(new Item(medicament3, 1));
        order1.addItem(new Item(medicament4, 4));

        client.addOrder(order1);
        System.out.println(order1);
        System.out.println(client);
    }
}
