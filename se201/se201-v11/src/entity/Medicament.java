package entity;

import java.time.LocalDate;

public class Medicament {
    private String name;
    private LocalDate expirationDate;

    public Medicament() {
    }

    public Medicament(String name, LocalDate expirationDate) {
        this.name = name;
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "name='" + name + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
