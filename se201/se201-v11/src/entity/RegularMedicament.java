package entity;

import java.time.LocalDate;

public class RegularMedicament extends Medicament {
    public RegularMedicament(String name, LocalDate expirationDate) {
        super(name, expirationDate);
    }

    public RegularMedicament() {
    }

}
