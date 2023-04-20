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
                    //Paul
                    case "w" :
                        if(player.getCurrentRoomObject().getWestExit() == 99) {
                            view.printNoRoom();
                        } else {
                            player.getCurrentRoomObject().setVisited();
                            player.setPreviousRoom(player.getCurrentRoom());
                            player.setCurrentRoom(player.getCurrentRoomObject().getWestExit());
                        }
                        break;
                    // Paul
                    case "n" :
                        if(player.getCurrentRoomObject().getNorthExit() == 99) {
                            view.printNoRoom();
                        } else {
                            player.getCurrentRoomObject().setVisited();
                            player.setPreviousRoom(player.getCurrentRoom());
                            player.setCurrentRoom(player.getCurrentRoomObject().getNorthExit());
                        }
                        break;
                    // Paul
                    case "e" :
                        if(player.getCurrentRoomObject().getEastExit() == 99) {
                            view.printNoRoom();
                        } else {
                            player.getCurrentRoomObject().setVisited();
                            player.setPreviousRoom(player.getCurrentRoom());
                            player.setCurrentRoom(player.getCurrentRoomObject().getEastExit());
                        }
                        break;
                    // Paul
                    case "s" :
                        if(player.getCurrentRoomObject().getSouthExit() == 99) {
                            view.printNoRoom();
                        } else {
                            player.getCurrentRoomObject().setVisited();
                            player.setPreviousRoom(player.getCurrentRoom());
                            player.setCurrentRoom(player.getCurrentRoomObject().getSouthExit());
                        }
                        break;
                    // Paul
                    case "use" :
                        String tempItemName = "";
                        for(int i = 1; i < splitCommand.length;i++){
                            tempItemName += (splitCommand[i] + " ");
                        }
                        tempItemName = tempItemName.substring(0, tempItemName.length() - 1);
                        
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
                    // Paul
                    case "pickup" :
                        String tempRoomItemName = "";
                        for(int i = 1; i < splitCommand.length;i++){
                            tempRoomItemName += (splitCommand[i] + " ");
                        }
                        Item tempRoomItem = player.getCurrentRoomObject().doesRoomHaveItem(splitCommand[1]);
                        if(tempRoomItem == null) {
                            view.printRoomNoItem();
                            break;
                        } else {
                            // adds item to player inventory and removes it from the room's inventory
                            printPickup(player.getCurrentRoomObject().removeItem(tempRoomItem));
                            break;
                        }
                    //Paul
                    case "explore" :
                        explore();
                        break;
                    //Paul
                    case "examine" :
                        if(player.getCurrentRoomObject().doesRoomHavePuzzle()) {
                            playPuzzle(player.getCurrentRoomObject());
                            break;
                        } else {
                            view.printNoStrangeDevice();
                        }
                    //Paul
                    case "inventory":
                        printPlayerInventory();
                        break;

                    case "inspect":
                        break;

                    case "drop":
                        break;

                    case "health":
                        break;

                    case "save":
                        view.printSaveMessage();
                        saveGame("SaveFile");
                        break;

                    //Paul
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
                boolean hasSolved = false;
                while(!hasSolved) {
                    String[] commands = scanner.nextLine().toLowerCase().split(" ");
                    if(commands.length == 1){
                        if(((Keypad)room.getPuzzle()).check(commands[0])) {
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
                view.print("Please input the decimal number " + room.getPuzzle().getDescription() + " in binary.");
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
                            } else if(!commands1[1].equals("1") && !commands1[1].equals("2") && !commands1[1].equals("3") && !commands1[1].equals("4") && !commands1[1].equals("5")) {
                                view.print("Please enter flip and a valid number of the switch you would like to flip.");
                                System.out.print(commands1[1]);
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
                            }
                }
                break;
        }

    }

    //Bao
    public void combat(Room room) {
        view.print("You see " + room.getMonster().getName());
        view.printCombatMenu();
        while(room.getMonster() != null) {
            String[] commands = scanner.nextLine().toLowerCase().split(" ");
            switch(commands[0]){
                case "a":
                case "attack":
                	view.print("You attack " + room.getMonster().getName() + ".");
                    if(player.checkHit()) {
                    	view.print("You hit " + room.getMonster().getName() + " for " + player.getAttackPower() + " damage!");
                        room.getMonster().takeHit(player.getAttackPower());
                    }
                    else {
                    	view.print("Your attack missed.");
                    }

                    if(room.getMonster().isDefeated()){
                        view.print("Victory!");
                        view.print("You defeated " + room.getMonster().getName());
                        view.print("You received " + room.getMonster().getItem());
                        player.addItem(room.getMonster().getItem());
                        room.removeMonster();
                    }
                    break;
                case "e":
                case "examine":
                    view.print(room.getMonster().getMonsterDescription());
                    view.print("While you were looking at the monster it attacked.");
                    break;
                case "u":
                case "use":
                    String tempItem = "";
                    for(int i = 1; i < commands.length;i++){
                        tempItem += (commands[i] + " ");
                    }
                    tempItem = tempItem.substring(0, tempItem.length() - 1);
                    if(player.doesPlayerHaveItem(tempItem) != null) {
                    	switch(player.doesPlayerHaveItem(tempItem).getType()) {
                    	case "combatItem":
                    		((CombatItem) player.doesPlayerHaveItem(tempItem)).use(room.getMonster());
                    		view.printUsedItem(player.doesPlayerHaveItem(tempItem).getName());
                    		if(!room.getMonster().isDefeated()) {
                    			view.print("That was not very effective.");
                    		}
                    		break;
                    	case "consumable":
                    		player.addHp(((Consumable) player.doesPlayerHaveItem(tempItem)).getHealthPoints());
                    		view.printPlayerHealth(player.getCurrentHp());
                    		break;
                    	default:
                    		view.print("You can't use that right now!");
                    		view.print("While you were fumbling around, the monster attacks.");
                    		break;
                    	}
                    } else {
                        view.print("You do not have" + tempItem);
                    }
                    break;
            }
            if(!room.getMonster().isDefeated()) {
            view.printMonsterAttack(room.getMonster().getName());
            if(room.getMonster().checkHit()) {
            	view.print("You took " + room.getMonster().getStrength() + " damage");
            	player.takeHit(room.getMonster().getStrength());
            }
            else {
            	view.print("It missed.");
            }
            }
            if(player.isDefeated()){
                view.print("You died. Game Over! Please try again.");
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
        if (this.player.getInventory().isEmpty()) {
            view.printInventoryIsEmpty();
        } else {
            view.printPlayerInventory(this.player);
        }
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
        if(this.player.getCurrentRoomObject().isVisited()) {
            view.printFamiliar();
            view.printRoomDescription(this.player.getCurrentRoomObject());
        }
        view.printRoomDescription(this.player.getCurrentRoomObject());
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
        view.print("You received " + room.getPuzzle().getItem().getName());
        player.getInventory().add(room.getPuzzle().getItem());
        room.removePuzzle();
    }

}
