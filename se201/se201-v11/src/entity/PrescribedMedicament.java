package entity;

import java.time.LocalDate;

public class PrescribedMedicament extends Medicament {
    private Recipe recipe;

    public PrescribedMedicament(String name, LocalDate expirationDate, Recipe recipe) {
        super(name, expirationDate);
        this.recipe = recipe;
    }

    public PrescribedMedicament(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        return "PrescribedMedicament{" +
                "recipe=" + recipe +
                '}';
    }
}
