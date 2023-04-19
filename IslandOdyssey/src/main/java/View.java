import java.util.ArrayList;

// Paul
public class View {

    //Bao
    public void print(String msg) {
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
        System.out.println("You are currently in room " + room.getRoomNumber() + ", The " + room.getName());
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
        System.out.println("This room contains");
        for(Item i: items) {
            System.out.println(i.getName() + ": qauntity:" + i.getQuantity());
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
        System.out.println("Which direction would you like to go? (N,E,S,W)");
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
    
    //Puzzle Prints
    //Bao
    public void printSwitchPuzzleMenu() {
        System.out.println("Would you like to (F)lip or (S)ubmit?");
    }
    
    //Bao
    public void printSwitchState(int[] switchState){
        String temp1 = "", temp2 = "", temp3 = "", temp4 = "", temp5 = "", temp6 = "";
        for(int s: switchState) {
            if(s == 0){
                temp1 += "1    ";
                temp2 += "|    ";
                temp3 += "|    ";
                temp4 += "|\\   ";
                temp5 += "| \\  ";
                temp6 += "0    ";
            } else {
                temp1 += "1    ";
                temp2 += "| /  ";
                temp3 += "|/   ";
                temp4 += "|    ";
                temp5 += "|    ";
                temp6 += "0    ";
            }
        }
        System.out.println(temp1);
        System.out.println(temp2);
        System.out.println(temp3);
        System.out.println(temp4);
        System.out.println(temp5);
        System.out.println(temp6);
    }
    
    //Bao
    public void printCombatMenu(){
        System.out.println("Would you like to (A)ttack, (U)se an item or (E)xamine the monster?");
    }

    //Paul
    public void printPlayerDoesntHaveItem() {
        System.out.println("Sorry, the player does not have that item");
    }


    public void printCantUseHere() {
        System.out.println("Sorry, you can't use that Item here");
    }

    public void printCollectible(Item tempItem) {
        System.out.println(tempItem.getDescription());
    }

    public void printRoomNoItem() {
        System.out.println("The room does not contain that item");
    }

    public void useItem(String itemName) {
        System.out.println("The player used the " + itemName);
    }

    public void printHasStrangeDevice() {
        System.out.println("The room contains a strange device. Perhaps you should examine it");
    }

    public void printNoStrangeDevice() {
        System.out.println("Sorry, there is no strange device in this room");
    }
}
