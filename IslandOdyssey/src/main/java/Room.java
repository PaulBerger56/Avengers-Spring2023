import java.util.ArrayList;

public class Room {

    private String description;
    private ArrayList<Item> items;

    public Room() {
        this.items = new ArrayList<>();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public String getDescription() {
        return this.description;
    }
}
