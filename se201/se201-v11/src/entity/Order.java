package entity;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int id = 0;
    private List<Item> items = new ArrayList<>();

    public Order() {
        id++;
    }

    public Order(List<Item> items) {
        id++;
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        if (item.getMedicament() instanceof PrescribedMedicament){
            ((PrescribedMedicament)item.getMedicament()).getRecipe().setRealized(true);
        }
        this.items.add(item);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                "items=" + items +
                '}';
    }
}
