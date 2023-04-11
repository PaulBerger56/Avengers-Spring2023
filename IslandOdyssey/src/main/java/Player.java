import java.io.Serializable;
import java.util.ArrayList;

// Paul
public class Player implements Serializable {

    private int maxHp;
    private int currentHp;
    private int attackPower;
    private int currentRoom;
    private int previousRoom;
    private boolean defeated;
    private ArrayList<Item> inventory;
    private Map map;


    // Paul
    // needs to take the names of the files since the map class is accessed through player and fills the map through a
    // call in the player constructor
    public Player(int maxHp, int attackPower, String roomFile, String itemFile, String monsterFile, String puzzleFile) {
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.attackPower = attackPower;
        this.currentRoom = 1;
        this.previousRoom = 0;
        this.defeated = false;
        this.inventory = new ArrayList<>();
        this.map = new Map(roomFile, itemFile, monsterFile, puzzleFile);

    }

    //Paul
    // adds to the current hp as long as it is under or equal to the max hp
    public void addHp(int hp) {
        if((this.currentHp + hp) >= this.maxHp) {
            this.currentHp = maxHp;
        }
        this.currentHp += hp;
    }

    // Paul
    // reduces the player's hp until 0
    public void takeHit(int damage) {
        if((this.currentHp - damage) <= 0) {
            this.defeated = true;
        }
        this.currentHp -= damage;
    }

    //Paul
    public void pickup(Item item) {
        this.inventory.add(item);
    }

    //Paul
    // not sure if this will be needed, or just handled in the controller
    public void use(Item item) {
        if(doesPlayerHaveItem(item.getName()) !=null) {
            item.use();
        }
    }

    //Paul
    public String drop(String itemName) {
        for(int i = 0; i < this.inventory.size(); i++) {
            if(this.inventory.get(i).getName().equals(itemName)) {
                Item tempItem = this.inventory.get(i);
                this.map.getRooms().get(currentRoom - 1).addItem(tempItem);
                this.inventory.remove(i);
                return tempItem.getName();
            }
        }
        return "Player doesn't have that item";
    }

    // Paul
    public Item doesPlayerHaveItem(String itemName) {
        for(Item i: this.inventory) {
            if(i.getName().equals(itemName)) {
                return i;
            }
        }
        return null;
    }

    //Paul
    // returns the current room's arraylist
    public ArrayList<Item> explore() {
        return this.map.getRooms().get(currentRoom - 1).getItems();
    }

    // Paul
    public Room getCurrentRoomObject() {
        return this.map.getRooms().get(this.currentRoom -1);
    }

    // Paul
    public boolean isDefeated() {
        return defeated;
    }

    // Paul
    public int getMaxHp() {
        return maxHp;
    }

    // Paul
    public int getCurrentHp() {
        return currentHp;
    }

    // Paul
    public int getAttackPower() {
        return attackPower;
    }

    // Paul
    public int getCurrentRoom() {
        return currentRoom;
    }

    //Paul
    public void setCurrentRoom(int roomNumber) {
        this.currentRoom = roomNumber;
    }

    // Paul
    public int getPreviousRoom() {
        return previousRoom;
    }

    //Paul
    public void setPreviousRoom(int roomNumber) {
        this.previousRoom = roomNumber;
    }

    // Paul
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    // Paul
    public Map getMap() {
        return map;
    }
}
