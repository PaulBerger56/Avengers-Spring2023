import java.io.*;
import java.util.Scanner;

// Paul
public class Controller {

    Scanner scanner;
    Player player;
    View view;

    // Paul
    // constructor for a new game
    public Controller(Player player, View view) {
        this.scanner = new Scanner(System.in);
        this.player = player;
        this.view = view;
    }

    // Paul
    // constructor for loaded game
    public Controller(String playerFileName, View view) {
        this.scanner = new Scanner(System.in);
        this.player = readInPlayer(playerFileName);
        this.view = view;
    }

    // Paul
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

    // Paul
    // Saves player to a binary file to load later
    public void saveGame(String fileName) {
        File file = new File(fileName + ".bin");

        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(this.player);

            oos.close();
            fos.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Paul
    // Holds the actual game loop
    public void play() {
        while(true) {

            // If the current room contains a monster, combat is initiated.
            if(player.getMap().getRooms().get(player.getCurrentRoom()-1).doesRoomHaveMonster()) {
                combat(player.getMap().getRooms().get(player.getCurrentRoom()-1).getMonster());
            }

            printRoomDescription();

            while(true) {
                printMenu();

                // takes the user's input and splits it to allow either case to be entered.
                String command = scanner.nextLine().toLowerCase();
                String[] splitCommand = command.split(" ");

                switch(splitCommand[0]) {
                    case "w" :
                        if(player.getMap().getRooms().get(player.getCurrentRoom() -1).getWestExit() == 0) {
                            view.printNoRoom();
                        } else {
                            player.getMap().getRooms().get(player.getCurrentRoom()-1).setVisited();
                            player.setPreviousRoom(player.getCurrentRoom());
                            player.setCurrentRoom(player.getMap().getRooms().get(player.getCurrentRoom() -1).getWestExit());
                        }
                        break;

                    case "n" :
                        if(player.getMap().getRooms().get(player.getCurrentRoom() -1).getNorthExit() == 0) {
                            view.printNoRoom();
                        } else {
                            player.getMap().getRooms().get(player.getCurrentRoom()-1).setVisited();
                            player.setPreviousRoom(player.getCurrentRoom());
                            player.setCurrentRoom(player.getMap().getRooms().get(player.getCurrentRoom() -1).getNorthExit());
                        }
                        break;

                    case "e" :
                        if(player.getMap().getRooms().get(player.getCurrentRoom() -1).getEastExit() == 0) {
                            view.printNoRoom();
                        } else {
                            player.getMap().getRooms().get(player.getCurrentRoom()-1).setVisited();
                            player.setPreviousRoom(player.getCurrentRoom());
                            player.setCurrentRoom(player.getMap().getRooms().get(player.getCurrentRoom() -1).getEastExit());
                        }
                        break;

                    case "s" :
                        if(player.getMap().getRooms().get(player.getCurrentRoom() -1).getSouthExit() == 0) {
                            view.printNoRoom();
                        } else {
                            player.getMap().getRooms().get(player.getCurrentRoom()-1).setVisited();
                            player.setPreviousRoom(player.getCurrentRoom());
                            player.setCurrentRoom(player.getMap().getRooms().get(player.getCurrentRoom() -1).getSouthExit());
                        }
                        break;

                    default:
                        view.printInvalidInput();
                        break;
                }
                break;
            }



        }
    }

    //Paul
    public void playPuzzle(Puzzle puzzle) {

    }

    //Paul
    public void combat(Monster monster) {

    }

    // Paul
    // sends the current room's inventory to the view to print
    public void explore() {
        view.printExplore(player.explore());
    }

    // Paul
    // Drops the specific item and has the view print the message
    public void dropItem(String itemName) {
        view.dropItem(this.player.drop(itemName));
    }

    // Paul
    // Sends the player inventory to the view to print
    public void printPlayerInventory() {
        view.printPlayerInventory(this.player);
    }

    // Paul
    // gets the player's current Room object and sends it to PlayerView to print the inventory
    public void printRoomInventory() {
        view.printRoomInventory(this.player.getCurrentRoomObject());
    }

    // Paul
    // prints the Item's description if it is in the player's inventory
    public void printItemDescription(String itemName) {
        view.printItemDescription(this.player, itemName);
    }

    // Paul
    // prints the current room's description
    public void printRoomDescription() {
        // prints the familiar message if room has been visited
        if(this.player.getMap().getRooms().get(this.player.getCurrentRoom() -1).isVisited()) {
            view.printFamiliar();
            view.printRoomDescription(this.player.getMap().getRooms().get(this.player.getCurrentRoom() -1));
        }
        view.printRoomDescription(this.player.getMap().getRooms().get(this.player.getCurrentRoom() -1));
    }

    //Paul
    public void printPickup(Item item) {
        view.printPickup(this.player.pickup(item));
    }

    //Paul
    public void printMenu() {
        view.printMenu();
    }



}
