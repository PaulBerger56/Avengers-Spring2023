import java.util.ArrayList;

// Paul
public class View {
	
	//Bao
	public void print(String msg)
	{
		System.out.println(msg);
	}

    // Paul
    public void printPlayerInventory(Player player) {
        for(Item i: player.getInventory()) {
            System.out.println(i.getName());
        }
    }

    // Paul
    public void printRoomInventory(Room room) {
        for(Item i: room.getItems()) {
            System.out.println(i.getName());
        }
    }

    // Paul
    public void printItemDescription(Player player, String itemName) {
        Item tempItem = player.doesPlayerHaveItem(itemName);
        if(tempItem == null) {
            System.out.println("Sorry, you do not have that item in your inventory");
        } else {
            System.out.println(tempItem.getDescription());
        }

    }
    // Paul
    public void printRoomDescription(Room room) {
        System.out.println("You are currently in " + room.getRoomNumber() + ", The " + room.getName());
        System.out.println(room.getDescription());
    }

    // Paul
    public void printSaveMessage() {
        System.out.println("What would you like to name your file?\n" +
                "Please leave off the .bin or other file type");
    }

    // Paul
    public void dropItem(String dropMessage) {
        System.out.println(dropMessage);
    }

    // Paul
    // prints the current room's inventory
    public void printExplore(ArrayList<Item> items) {
        for(Item i: items) {
            System.out.println(i.getName());
        }
    }

    // Paul
    public void printFamiliar() {
        System.out.println("This looks familiar...");
    }

    // Paul
    public void printPickup(String itemName) {
        System.out.println("The player picked up a(n) " + itemName);
    }

    //Paul
    public void printMenu() {
        System.out.println("Which direction would you like to go? (N,E,S,W");
        System.out.println("type help for extra commands");
    }

    //Paul
    public void printInvalidInput() {
        System.out.println("Sorry, that was an invalid input");
    }

    //Paul
    public void printNoRoom() {
        System.out.println("Sorry, there is no room in that direction");
    }
    
    //Bao
    public void printSwitchPuzzleMenu() {
    	System.out.println("Would you like to (F)lip or (S)ubmit?");
    }
}
