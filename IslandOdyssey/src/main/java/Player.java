import java.util.ArrayList;

// Paul
public class Player {

    private int maxHp;
    private int currentHp;
    private int attackPower;
    private int currentRoom;
    private int previousRoom;
    private boolean defeated;
    private ArrayList<Item> inventory;
    private Map map;



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

    public void takeHit(int damage) {
        if((this.currentHp - damage) <= 0) {
            this.defeated = true;
        }
        this.currentHp -= damage;
    }

    public Item doesPlayerHaveItem(String itemName) {
        for(Item i: this.inventory) {
            if(i.getName().equals(itemName)) {
                return i;
            }
        }
        return null;
    }

    public Room getCurrentRoomObject() {
        return this.map.getRooms().get(this.currentRoom -1);
    }

    public boolean isDefeated() {
        return defeated;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getCurrentRoom() {
        return currentRoom;
    }

    public int getPreviousRoom() {
        return previousRoom;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public Map getMap() {
        return map;
    }
}
