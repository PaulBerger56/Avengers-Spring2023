import java.util.ArrayList;

public class Room {

    private String description;
    private ArrayList<Item> items;

    public Room() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public String getDescription() {
        return this.description;
    }
}
