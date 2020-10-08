package entity;

public class Item {
    private Medicament medicament;
    private int quantity;

    public Item() {
    }

    public Item(Medicament medicament, int quantity) {
        this.medicament = medicament;
        this.quantity = quantity;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "medicament=" + medicament +
                ", quantity=" + quantity +
                '}';
    }
}
