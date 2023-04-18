import java.util.ArrayList;

public class Room {

    private final int roomNumber;
    private final String name;
    private final String description;
    private boolean visited;
    private final int northExit;
    private final int eastExit;
    private final int southExit;
    private final int westExit;
    private boolean hasDevice;
    private Puzzle puzzle;
    private Monster monster;
    private final ArrayList<Item> items;


    //Paul
    public Room(int roomNumber, String name, String description, int northExit, int eastExit, int southExit, int westExit) {
        this.roomNumber = roomNumber;
        this.name = name;
        this.description = description;
        this.northExit = northExit;
        this.eastExit = eastExit;
        this.southExit = southExit;
        this.westExit = westExit;
        this.items = new ArrayList<>();
        this.visited = false;
    }

    //Paul
    // if the item is in the inventory, returns it and removes it from the arraylist, otherwise returns null
    public Item removeItem(String itemName) {
        for(int i = 0; i < this.items.size(); i++) {
            if(this.items.get(i).getName().equals(itemName)) {
                Item tempItem = this.items.get(i);
                this.items.remove(i);
                return tempItem;
            }
        }
        return null;
    }

    //Paul
    public void removePuzzle() {
        this.puzzle = null;
    }

    //Paul
    public void removeMonster() {
        this.monster = null;
    }

    //Paul
    public boolean doesRoomHaveMonster() {
        return this.monster != null;
    }

    //Paul
    public boolean doesRoomHavePuzzle() {
        return this.puzzle != null;
    }

    //Paul
    public void addItem(Item item) {
        this.items.add(item);
    }

    //Paul
    public void addMonster(Monster monster) {
        this.monster = monster;
    }

    //Paul
    public void addPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    //Paul
    public int getRoomNumber() {
        return roomNumber;
    }

    //Paul
    public int getNorthExit() {
        return northExit;
    }

    //Paul
    public int getEastExit() {
        return eastExit;
    }

    //Paul
    public int getSouthExit() {
        return southExit;
    }

    //Paul
    public int getWestExit() {
        return westExit;
    }

    //Paul
    public boolean isHasDevice() {
        return hasDevice;
    }

    //Paul
    public Puzzle getPuzzle() {
        return puzzle;
    }

    //Paul
    public Monster getMonster() {
        return monster;
    }

    //Paul
    public String getName() {
        return this.name;
    }

    //Paul
    public String getDescription() {
        return this.description;
    }

    //Paul
    public boolean isVisited() {
        return this.visited;
    }

    //Paul
    public void setVisited() {
        this.visited = true;
    }

    //Paul
    public ArrayList<Item> getItems() {
        return items;
    }
}
