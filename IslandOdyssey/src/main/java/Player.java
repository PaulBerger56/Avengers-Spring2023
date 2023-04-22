import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;


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
    public Player(String playerFile, String roomFile, String itemFile, String monsterFile, String puzzleFile) {
        String[] temp = readPlayerFile(playerFile);
        this.maxHp = Integer.parseInt(temp[0]);
        this.currentHp = maxHp;
        this.attackPower = Integer.parseInt(temp[1]);
        this.currentRoom = 0;
        this.previousRoom = 0;
        this.attackChance = Integer.parseInt(temp[2]);
        this.defeated = false;
        this.inventory = new ArrayList<>();
        this.map = new Map(roomFile, itemFile, monsterFile, puzzleFile);
    }

    //Paul and Bao
    // reads in the player file and adds the appropriate values to the current player
    public String[] readPlayerFile(String playerFile) {
        String[] split = new String[3];
        try {
            File playerReadFile = new File(playerFile);
            Scanner playerScanner = new Scanner(playerReadFile);

            while(playerScanner.hasNext()) {
                String line = playerScanner.nextLine();
                split = line.split("~");
                return split;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return split;
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
    public void setDefeated() {
        this.defeated = true;
    }
}
