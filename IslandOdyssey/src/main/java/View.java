import java.util.ArrayList;

// Paul
public class View {

    //Bao
    public void print(String msg) {
        System.out.println(msg);
    }

    // Paul
    public void printPlayerInventory(Player player) {
        System.out.println("The player's inventory contains: ");
        for(Item i: player.getInventory()) {
            System.out.println(i.getName() + ": " + i.getQuantity());
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
    public void printRoomDescription(Room room, boolean isVisited) {
        
        if(isVisited) {
        	System.out.print("You are currently in room " + room.getRoomNumber() + ", The " + room.getName() + ". ");
        	printFamiliar();
        }
        else {
        	System.out.println("You are currently in room " + room.getRoomNumber() + ", The " + room.getName() + ".");
        }
        System.out.println(room.getDescription());
    }

    // Paul
    public void printSaveMessage() {
        System.out.println("Now saving your game!");
    }

    // Paul
    public void dropItem(String dropMessage) {
        System.out.println(dropMessage);
    }

    // Paul
    // prints the current room's inventory
    public void printExplore(ArrayList<Item> items) {
        if(items.isEmpty()) {
            System.out.println("The room is empty!");
        }else {
            System.out.println("This room contains: ");
            for (Item i : items) {
                System.out.println(i.getName() + ": " + i.getQuantity());
            }
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
    
    //Bao
    public void printMonsterAttack(String monsterName) {
        System.out.println(monsterName + " attacks you.");
    }

    //Paul
    public void printPlayerDoesntHaveItem() {
        System.out.println("Sorry, the player does not have that item");
    }
    
    //Bao
    public void printPlayerHealth(int health) {
        System.out.println("You have " + health + "HP.");
    }
    
    //Bao
    public void printUsedItem(String itemName) {
        System.out.println("You used " + itemName + ".");
    }

    //Paul
    public void printCantUseHere() {
        System.out.println("Sorry, you can't use that Item here");
    }

    //Paul
    public void printCollectible(Item tempItem) {
        System.out.println(tempItem.getDescription());
    }

    //Paul
    public void printRoomNoItem() {
        System.out.println("The room does not contain that item");
    }

    //Paul
    public void useItem(String itemName) {
        System.out.println("The player used the " + itemName);
    }

    //Paul
    public void printHasStrangeDevice() {
        System.out.println("The room contains a strange device. Perhaps you should examine it");
    }

    //Paul
    public void printNoStrangeDevice() {
        System.out.println("Sorry, there is no strange device in this room");
    }

    public void printInventoryIsEmpty() {
        System.out.println("The player's inventory is empty");
    }

    public void printMainMenu() {
        System.out.println("Start a (n)ew game, or (l)oad a save file, or (q)?");
    }

    public void printQuitting() {
        System.out.println("Now quitting.  Thank you for playing!");
    }

    public void printPlayerDefeated() {
        System.out.println("You died. Game Over! Please try again.");
    }

    public void printDropItem(String name) {
        System.out.println("You dropped " + name + ".");
    }

    public void printFlipValidNumber() {
        System.out.println("Please enter flip followed by a valid number of the switch you would like to flip.");
    }

    public void printFailEnding() {
        System.out.println("You have used all your attempts!");
        System.out.println("You will never know the real end to your story!");
        System.out.println();
    }
}
