package entity;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    private int id;
    private List<Medicament> medicaments = new ArrayList<>();

    public Warehouse() {
    }

    public Warehouse(int id) {
        this.id = id;
    }

    public Warehouse(int id, List<Medicament> medicaments) {
        this.id = id;
        this.medicaments = medicaments;
    }

    public List<Medicament> getMedicaments() {
        return medicaments;
    }

    public void addMedicament(Medicament medicament) {
        this.medicaments.add(medicament);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                '}';
    }
}
