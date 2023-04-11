import java.util.ArrayList;

// Paul
public class PlayerView {

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

}
