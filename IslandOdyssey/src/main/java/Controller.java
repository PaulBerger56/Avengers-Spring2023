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
            if(player.getCurrentRoomObject().doesRoomHaveMonster()) {
                combat(player.getCurrentRoomObject());
            }

            printRoomDescription();

            while(true) {
                printMenu();

                // takes the user's input and splits it to allow either case to be entered.
                String command = scanner.nextLine().toLowerCase();
                String[] splitCommand = command.split(" ");

                switch(splitCommand[0]) {
                    case "w" :
                        if(player.getCurrentRoomObject().getWestExit() == 0) {
                            view.printNoRoom();
                        } else {
                            player.getCurrentRoomObject().setVisited();
                            player.setPreviousRoom(player.getCurrentRoom());
                            player.setCurrentRoom(player.getCurrentRoomObject().getWestExit());
                        }
                        break;

                    case "n" :
                        if(player.getCurrentRoomObject().getNorthExit() == 0) {
                            view.printNoRoom();
                        } else {
                            player.getCurrentRoomObject().setVisited();
                            player.setPreviousRoom(player.getCurrentRoom());
                            player.setCurrentRoom(player.getCurrentRoomObject().getNorthExit());
                        }
                        break;

                    case "e" :
                        if(player.getCurrentRoomObject().getEastExit() == 0) {
                            view.printNoRoom();
                        } else {
                            player.getCurrentRoomObject().setVisited();
                            player.setPreviousRoom(player.getCurrentRoom());
                            player.setCurrentRoom(player.getCurrentRoomObject().getEastExit());
                        }
                        break;

                    case "s" :
                        if(player.getCurrentRoomObject().getSouthExit() == 0) {
                            view.printNoRoom();
                        } else {
                            player.getCurrentRoomObject().setVisited();
                            player.setPreviousRoom(player.getCurrentRoom());
                            player.setCurrentRoom(player.getCurrentRoomObject().getSouthExit());
                        }
                        break;

                    case "use" :
                        String tempItemName = "";
                        for(int i = 1; i < splitCommand.length;i++){
                            tempItemName += (splitCommand[i] + " ");
                        }
                        Item tempItem = player.doesPlayerHaveItem(tempItemName);
                        if(tempItem == null) {
                            view.printPlayerDoesntHaveItem();
                            break;
                        } else if(tempItem.getType().equals("Consumable")){
                            Consumable tempConsumable = (Consumable) tempItem;
                            player.addHp(tempConsumable.getHealthPoints());
                            view.useItem(player.removeItem(tempConsumable.getName()));
                            break;
                        } else if(tempItem.getType().equals("Collectible")) {
                            view.printCollectible(tempItem);
                            break;
                        } else if(tempItem.getType().equals("Interactable")) {
                            useInteractable(tempItem);
                            break;
                        } else {
                            view.printCantUseHere();
                            break;
                        }

                    case "pickup" :
                        String tempRoomItemName = "";
                        for(int i = 1; i < splitCommand.length;i++){
                            tempRoomItemName += (splitCommand[i] + " ");
                        }
                        Item tempRoomItem = player.getMap().getRooms().get(player.getCurrentRoom()).doesRoomHaveItem(tempRoomItemName);
                        if(tempRoomItem == null) {
                            view.printRoomNoItem();
                            break;
                        } else {
                            // adds item to player inventory and removes it from the room's inventory
                            printPickup(player.getMap().getRooms().get(player.getCurrentRoom()).removeItem(tempRoomItemName));
                            break;
                        }

                    case "explore" :
                        explore();
                        break;

                    case "examine" :
                        if(player.getCurrentRoomObject().doesRoomHavePuzzle()) {
                            playPuzzle(player.getCurrentRoomObject());
                            break;
                        } else {
                            view.printNoStrangeDevice();
                        }

                    default:
                        view.printInvalidInput();
                        break;

                }
                break;
            }



        }
    }

    private void useInteractable(Item tempItem) {
        // move the interactable logic here
    }

    //Bao
    public void playPuzzle(Room room) {
        switch(room.getPuzzle().getType()) {
            case "Keypad":
                view.print("You are presented with a string of digits: " + room.getPuzzle().getDescription());
                view.print("Please enter the word you would get if you entered this number into a keypad.");
                String[] commands = scanner.nextLine().toLowerCase().split(" ");
                boolean hasSolved = false;
                while(!hasSolved) {
                    if(commands.length == 1){
                        if(((Keypad)room.getPuzzle()).check(commands[1])) {
                            hasSolved = true;
                            solvedPuzzle(room);
                        } else if(room.getPuzzle().getAttempts() == 0) {
                            view.print("You have run out of attempts. Please try again later.");
                            room.getPuzzle().setAttempts(room.getPuzzle().getMaxAttempts());
                        } else {
                            room.getPuzzle().setAttempts(room.getPuzzle().getAttempts() - 1);
                            view.print("Your answer was wrong. Try again.");
                            view.print("You have " + room.getPuzzle().getAttempts() + "attempts left.");
                        }
                    } else{
                        view.print("Please enter a one word answer.");
                    }
                }
                break;
            case "Switches":
                view.print("You are presented with a row of 5 switches with a 1 inscribed on top and a 0 inscribed on the bottom of each switch.");
                view.print("The switches are all switched down.");
                view.print("Please input the decimal number" + room.getPuzzle().getDescription() + " in binary.");
                view.printSwitchPuzzleMenu();
                boolean hasSolved1 = false;
                while(!hasSolved1){
                    String[] commands1 = scanner.nextLine().toLowerCase().split(" ");
                    switch(commands1[0]) {
                        case "s":
                        case "submit":
                            if(((Switches)room.getPuzzle()).check()){
                                hasSolved1 = true;
                                solvedPuzzle(room);
                            }
                            break;
                        case "f":
                        case "flip":
                            if(commands1.length != 2) {
                                view.print("Please enter flip followed by switch number you would like to flip.");
                            } else if(commands1[1] != "1" && commands1[1] != "2" && commands1[1] != "3" && commands1[1] != "4" && commands1[1] != "5") {
                                view.print("Please enter flip and a valid number of the switch you would like to flip.");
                            } else {
                                ((Switches) room.getPuzzle()).flip(commands1[1]);
                                view.print("You have flipped switch #" + commands1[1]);
                                view.printSwitchState(((Switches)room.getPuzzle()).printSwitches());
                            }
                            break;
                        case "h":
                        case "help":
                        default:
                            view.printSwitchPuzzleMenu();
                            break;
                    }
                    break;
                }
        }

    }

    //Bao and Joseph (*incomplete*)
    public void combat(Room room) {
        view.print("This is " + room.getMonster().getName());
        view.printCombatMenu();
        while(room.getMonster() != null) {
            String[] commands = scanner.nextLine().toLowerCase().split(" ");
            switch(commands[0]){
                case "a":
                case "attack":
                    room.getMonster().takeHit(player.getAttackPower());
                    if(room.getMonster().isDefeated){
                        view.print("Victory!");
                        //*do things that happen when monster is defeated*
                        break;
                    }
                    break;
                case "e":
                case "examine":
                    view.print(room.getMonster().getMonsterDescription());
                    break;
                case "u":
                case "use":
                    String tempItem = "";
                    for(int i = 1; i < commands.length;i++){
                        tempItem += (commands[i] + " ");
                    }
                    if(player.doesPlayerHaveItem(tempItem) != null) {
                        //*use item*
                    } else {
                        view.printPlayerDoesntHaveItem();
                    }
                    break;
            }
            player.takeHit(room.getMonster().getStrength());
            if(player.isDefeated()){
                //*do things when player is defeated*
                //*delete save file and restart from beginning?*
            }
        }
    }

    // Paul
    // sends the current room's inventory to the view to print
    public void explore() {
        view.printExplore(player.explore());
        if(player.getCurrentRoomObject().doesRoomHavePuzzle()) {
            view.printHasStrangeDevice();
        }
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
        if(this.player.getMap().getRooms().get(this.player.getCurrentRoom()).isVisited()) {
            view.printFamiliar();
            view.printRoomDescription(this.player.getMap().getRooms().get(this.player.getCurrentRoom()));
        }
        view.printRoomDescription(this.player.getMap().getRooms().get(this.player.getCurrentRoom()));
    }

    //Paul
    public void printPickup(Item item) {
        view.printPickup(this.player.pickup(item));
    }

    //Paul
    public void printMenu() {
        view.printMenu();
    }
    
    //Bao
    public void solvedPuzzle(Room room) {
        view.print(room.getPuzzle().puzzleSolvedMessage());
        player.getInventory().add(room.getPuzzle().getItem());
        room.removePuzzle();
    }

}
