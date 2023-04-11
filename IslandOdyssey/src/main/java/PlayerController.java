import java.util.Scanner;

// Paul
public class PlayerController {

    Scanner scanner;
    Player player;
    PlayerView playerView;

    public PlayerController(Player player, PlayerView view) {
        this.scanner = new Scanner(System.in);
        this.player = player;
        this.playerView = view;
    }

    // Sends the player inventory to the view to print
    public void printPlayerInventory() {
        playerView.printPlayerInventory(this.player);
    }

    //gets the player's current Room object and sends it to PlayerView to print the inventory
    public void printRoomInventory() {
        playerView.printRoomInventory(this.player.getCurrentRoomObject());
    }

    // prints the Item's description if it is in the player's inventory
    public void printItemDescription(String itemName) {
        playerView.printItemDescription(this.player, itemName);
    }


}
