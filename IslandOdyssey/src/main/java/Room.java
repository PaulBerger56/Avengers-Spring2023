import java.util.ArrayList;

public class Room {

    private String description;
    private ArrayList<Item> items;

    private boolean visited;

    public Room() {
        this.items = new ArrayList<>();
        this.visited = false;
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

    public boolean isVisited() {
        return this.visited;
    }

    public void setVisited() {
        this.visited = true;
    }
}
