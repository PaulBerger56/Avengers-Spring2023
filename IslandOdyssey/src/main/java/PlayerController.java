import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

// Paul
public class PlayerController {

    Scanner scanner;
    Player player;
    PlayerView playerView;

    // constructor for a new game
    public PlayerController(Player player, PlayerView view) {
        this.scanner = new Scanner(System.in);
        this.player = player;
        this.playerView = view;
    }

    // constructor for loaded game
    public PlayerController(String playerFileName, PlayerView view) {
        this.scanner = new Scanner(System.in);
        this.player = readInPlayer(playerFileName);
        this.playerView = view;
    }

    // reads in the binary file to load a saved player
    private Player readInPlayer(String playerFileName) {
        Player tempPlayer = null;

        try {
            FileInputStream fis = new FileInputStream(playerFileName);
            ObjectInputStream ois = new ObjectInputStream(fis);

            tempPlayer = (Player) ois.readObject();

            ois.close();
            fis.close();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return tempPlayer;
    }

    // Holds the actual game loop
    public void play() {
        while(true) {

        }
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

    // prints the current room's description
    public void printRoomDescription() {
        playerView.printRoomDescription(this.player.getMap().getRooms().get(this.player.getCurrentRoom() -1));
    }




}
