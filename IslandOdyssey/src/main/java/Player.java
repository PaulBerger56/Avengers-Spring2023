import java.io.Serializable;
import java.util.ArrayList;


// Paul
public class Player implements Serializable {

    private final int maxHp;
    private int currentHp;
    private final int attackPower;
    private int currentRoom;
    private int previousRoom;
    private final int attackChance;
    private boolean defeated;
    private final ArrayList<Item> inventory;
    private final Map map;


    // Paul
    // needs to take the names of the files since the map class is accessed through player and fills the map through a
    // call in the player constructor
    public Player(String roomFile, String itemFile, String monsterFile, String puzzleFile) {
        this.maxHp = 200;
        this.currentHp = 200;
        this.attackPower = 20;
        this.currentRoom = 1;
        this.previousRoom = 0;
        this.attackChance = 70;
        this.defeated = false;
        this.inventory = new ArrayList<>();
        this.map = new Map(roomFile, itemFile, monsterFile, puzzleFile);

    }

    //Paul && Bao
    // adds to the current hp as long as it is under or equal to the max hp
    public void addHp(int hp) {
        if((this.currentHp + hp) >= this.maxHp) {
            this.currentHp = maxHp;
        } else {
            this.currentHp += hp;
        }

    }

    // Paul && Bao
    // reduces the player's hp until 0
    public void takeHit(int damage) {
        if((this.currentHp - damage) <= 0) {
            this.currentHp = 0;
            this.defeated = true;
        } else {
            this.currentHp -= damage;
        }
    }

    //Paul
    // checks if the item to pick up already exists in the inventory, and if so increments its count by 1;
    public String pickup(Item item) {
        if(doesPlayerHaveItem(item.getName()) != null) {
            for(int i = 0; i < this.inventory.size(); i++) {
                if(this.inventory.get(i).getName().equalsIgnoreCase(item.getName())) {
                    this.inventory.get(i).incrementQuantity();
                    return item.getName();
                }
            }
        } else {
            this.inventory.add(item);
        }
        return item.getName();
    }


    //Paul
    // removes the specific item from the player's inventory and adds it to the current room's inventory
    public String drop(String itemName) {
        for(int i = 0; i < this.inventory.size(); i++) {
            if(this.inventory.get(i).getName().equals(itemName)) {
                Item tempItem = this.inventory.get(i);
                this.map.getRooms().get(currentRoom).addItem(tempItem);
                this.inventory.remove(i);
                return tempItem.getName();
            }
        }
        return "Player doesn't have that item";
    }
    
    //Bao
    public void addItem(Item item) {
        if(doesPlayerHaveItem(item.getName()) != null) 
        {
            for(int i = 0; i < this.inventory.size(); i++) 
            {
                if(this.inventory.get(i).getName().equals(item.getName())) 
                {
                    this.inventory.get(i).incrementQuantity();
                }
            }
        } else 
        {
            this.inventory.add(item);
        }
    }

    // Paul
    // checks if the player has a specific item, and returns an item object if so.
    public Item doesPlayerHaveItem(String itemName) {
        for(Item i: this.inventory) {
            if(i.getName().equalsIgnoreCase(itemName)) {
                return i;
            }
        }
        return null;
    }

    //Paul
    //Removes the item from the player's inventory, but doesn't drop it into a room.
    public String removeItem(String itemName) {
        for(int i = 0; i < this.inventory.size(); i++) {
            if(this.inventory.get(i).getName().equals(itemName)){
                if((this.inventory.get(i).getQuantity() - 1) <= 0) {
                    this.inventory.remove(i);
                } else {
                    this.inventory.get(i).decrementQuantity();
                }
            }
        }
        return itemName;
    }

    //Paul
    // returns the current room's arraylist
    public ArrayList<Item> explore() {
        return getCurrentRoomObject().getItems();
    }

    // Paul
    public Room getCurrentRoomObject() {
        return this.map.getRooms().get(this.currentRoom);
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
    
    //Bao
    public boolean checkHit() {
    	if(Math.ceil(Math.random() * 100) <= attackChance) {
    		return true;
    	}
    	return false;
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
    //Joseph

    public void setDefeated(boolean defeated) {
        this.defeated = defeated;
    }
}
