package entity;

public class Recipe {
    private String text;
    private boolean realized;

    public Recipe(String text) {
        this.text = text;
        this.realized = false;
    }

    public Recipe() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isRealized() {
        return realized;
    }

    public void setRealized(boolean realized) {
        this.realized = realized;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "text='" + text + '\'' +
                ", realized=" + realized +
                '}';
    }
}
