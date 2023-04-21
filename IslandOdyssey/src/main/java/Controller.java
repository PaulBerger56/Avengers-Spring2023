import java.io.*;
import java.util.Scanner;

// Paul
public class Controller {

    Scanner scanner;
    Player player;
    View view;

    //Paul
    //experimenting with start loop in controller
    public Controller(View view) {
        this.scanner = new Scanner(System.in);
        this.view = view;
        this.player = null;
        mainMenu();
    }

    //Paul
    // Main menu in the controller
    public void mainMenu() {
        while(true) {
            view.printMainMenu();
            String command = scanner.nextLine();
            switch(command) {
                case "n":
                    this.player = new Player("PlayerFile.txt","RoomFile.txt", "ItemFile.txt", "MonsterFile.txt", "PuzzleFile.txt");
                    play(this.player);
                    break;

                case "l":
                    this.player = readInPlayer("SaveFile.bin");
                    play(this.player);
                    break;

                case "q":
                    view.printQuitting();
                    System.exit(0);
                    break;

                default:
                    view.printInvalidInput();
                    break;

            }
        }
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
    public void saveGame() {
        File file = new File("SaveFile.bin");

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
    public void play(Player player) {
        printRoomDescription();
        printMenu();
        while(true) {

            // If the current room contains a monster, combat is initiated.
            if(player.getCurrentRoomObject().doesRoomHaveMonster()) {
                combat(player.getCurrentRoomObject());
            }

            while(true) {

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
                            printRoomDescription();
                            if(player.getCurrentRoomObject().getMonster() == null) {
                                printMenu();
                            }
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
                            printRoomDescription();
                            if(player.getCurrentRoomObject().getMonster() == null) {
                                printMenu();
                            }
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
                            printRoomDescription();
                            if(player.getCurrentRoomObject().getMonster() == null) {
                                printMenu();
                            }
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
                            printRoomDescription();
                            if(player.getCurrentRoomObject().getMonster() == null) {
                                printMenu();
                            }
                        }
                        break;
                    // Paul
                    case "use" :
                        if(splitCommand.length <= 1) {
                            view.printUseReminder();
                            break;
                        }
                        String tempUseItem = "";
                        for(int i = 1; i < splitCommand.length;i++){
                            tempUseItem += (splitCommand[i] + " ");
                        }
                        tempUseItem = tempUseItem.substring(0, tempUseItem.length() - 1);
                        
                        Item tempItem = player.doesPlayerHaveItem(tempUseItem);
                        if(tempItem == null) {
                            view.printPlayerDoesntHaveItem();
                            break;
                        } else if(tempItem.getType().equals("Consumable")){
                            Consumable tempConsumable = (Consumable) tempItem;
                            player.addHp(tempConsumable.getHealthPoints());
                            view.useItem(player.removeItem(tempConsumable.getName()));
                            view.printPlayerHp(player);
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
                        if(splitCommand.length <= 1) {
                            view.printPickupReminder();
                            break;
                        }
                        String tempPickupItem = "";
                        for(int i = 1; i < splitCommand.length;i++){
                            tempPickupItem += (splitCommand[i] + " ");
                        }
                        tempPickupItem = tempPickupItem.substring(0, tempPickupItem.length() - 1);
                        Item tempRoomItem = player.getCurrentRoomObject().doesRoomHaveItem(tempPickupItem);
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
                            break;
                        }
                    //Paul
                    case "inventory":
                        printPlayerInventory();
                        break;
                    //Joseph && Paul
                    case "inspect":
                        if(splitCommand.length <= 1) {
                            view.printInspectReminder();
                            break;
                        }

                        String inspectItem = "";
                        for(int i = 1; i < splitCommand.length;i++){
                            inspectItem += (splitCommand[i] + " ");
                        }
                        inspectItem = inspectItem.substring(0, inspectItem.length() - 1);

                        Item tempInspectItem = player.doesPlayerHaveItem(inspectItem);
                        if(tempInspectItem == null) {
                            view.printRoomNoItem();
                            break;
                        }
                        if(tempInspectItem!= null){
                            printItemDescription(player.doesPlayerHaveItem(inspectItem));
                        }
                        break;
                    //Joseph && Paul
                    case "drop":
                        if(splitCommand.length <= 1) {
                            view.printDropReminder();
                            break;
                        }
                        String tmpDropItem = "";
                        for(int i = 1; i < splitCommand.length;i++){
                            tmpDropItem += (splitCommand[i] + " ");
                        }
                        tmpDropItem = tmpDropItem.substring(0, tmpDropItem.length() - 1);
                        Item interimItem = player.doesPlayerHaveItem(tmpDropItem);

                        if(interimItem == null) {
                            view.printPlayerDoesntHaveItem();
                            break;
                        }

                        if(interimItem!= null){
                            player.getCurrentRoomObject().addItem(interimItem);
                            player.removeItem(interimItem.getName());
                            view.printDropItem(interimItem.getName());
                        }
                        break;
                    // Paul
                    case "health":
                        view.printPlayerHp(player);
                        break;

                    // Paul
                    case "save":
                        view.printSaveMessage();
                        saveGame();

                        while(true) {
                            view.printWouldYouLikeToQuit();
                            String yOrn = scanner.nextLine();
                            if(yOrn.equalsIgnoreCase("y")) {
                                view.printQuitting();
                                System.exit(0);
                                break;
                            } else if (yOrn.equalsIgnoreCase("n")){
                                view.printGoodLuck();
                                view.printMenu();
                                break;
                            } else {
                                view.printInvalidInput();
                            }
                        }
                        break;

                    //Edwin
                    case "help":
                        view.printHelpMenu();
                        break;

                    //Paul & Edwin
                    default:
                        view.printInvalidInput();
                        printMenu();
                        break;
                }
                break;
            }
        }
    }

    //Edwin
    private void useInteractable(Item tempItem) {
        Interactable tempInteractable = (Interactable) tempItem;
        if (tempInteractable.getHasUsedMaxAttempts() == true){
            view.printInteractableClosed();
            printMenu();
        }
        while (tempInteractable.getHasUsedMaxAttempts() == false){

            if (tempInteractable.getAttempts() == 0){
                tempInteractable.setHasUsedMaxAttempts(true);
                view.printFailEnding();
                printMenu();
                break;
            }
            view.printInteractableInstructions();
            view.printAttemptsLeft(tempInteractable);
            String attempt = scanner.nextLine();

            if (attempt.equalsIgnoreCase("0000")){
                view.printEmptyLine();
                printMenu();
                break;
            }
            else if (attempt.equals(tempInteractable.getPin())){
                //Trigger ending
                view.printEnding();
                mainMenu();
            }
            else if (attempt.length() != 4 || !attempt.matches("[0-9]+")){
                view.printFourDigits();
            }
            else {
                view.printWrongPin();
                tempInteractable.decrementAttempts();
            }
        }
    }

    //Bao & Edwin
    public void playPuzzle(Room room) {
        switch(room.getPuzzle().getType()) {
            case "Keypad":
                view.printKeypadInstructions(room);
                boolean hasFinished = false;
                while(!hasFinished) {
                    String[] commands = scanner.nextLine().toLowerCase().split(" ");
                    if(commands.length == 1){
                        if(((Keypad)room.getPuzzle()).check(commands[0])) {
                            hasFinished = true;
                            solvedPuzzle(room);
                        } else if(room.getPuzzle().getAttempts() == 0) {
                            view.printPuzzleOutOfAttempts();
                            room.getPuzzle().setAttempts(room.getPuzzle().getMaxAttempts());
                            hasFinished = true;
                        } else {
                            room.getPuzzle().setAttempts(room.getPuzzle().getAttempts() - 1);
                            view.printPuzzleWrongAnswer(room);
                        }
                    } else{
                        view.printOneWordAnswer();
                    }
                }
                break;
            case "Switches":
                view.printEmptyLine();
                view.printSwitchesInstructions(room);
                view.printSwitchState(((Switches)room.getPuzzle()).getSwitches());
                view.printSwitchPuzzleMenu();
                boolean hasFinished1 = false;
                while(!hasFinished1){

                    String command = scanner.nextLine().toLowerCase();
                    String[] commands1 = command.split(" ");

                    switch(commands1[0]) {
                        case "s":
                        case "submit":
                            if(((Switches)room.getPuzzle()).check()){
                                hasFinished1 = true;
                                solvedPuzzle(room);
                            }
                            else if(room.getPuzzle().getAttempts() == 0){
                                view.printPuzzleOutOfAttempts();
                                room.getPuzzle().setAttempts(room.getPuzzle().getMaxAttempts());
                                hasFinished1 = true;
                            }
                            else {
                                room.getPuzzle().setAttempts(room.getPuzzle().getAttempts() - 1);
                                view.printPuzzleWrongAnswer(room);
                            }
                            break;
                        case "f":
                        case "flip":
                            if(commands1.length != 2) {
                                view.printFlipValidNumber();
                            } else if(!commands1[1].equals("1") && !commands1[1].equals("2") && !commands1[1].equals("3") && !commands1[1].equals("4") && !commands1[1].equals("5")) {
                                view.printFlipValidNumber();
                            } else {
                                ((Switches) room.getPuzzle()).flip(commands1[1]);
                                view.printFlippedSwitch(commands1[1]);
                                view.printSwitchState(((Switches)room.getPuzzle()).getSwitches());
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
        view.printSeeMonster(room.getMonster());
        view.printCombatMenu();
        boolean inCombat = true;
        while(inCombat) {
            String[] commands = scanner.nextLine().toLowerCase().split(" ");
            switch(commands[0]){
                case "a":
                case "attack":
                    view.printPlayerAttacksMonster(room.getMonster());
                    if(player.checkHit()) {
                        view.printConfirmedHitOnMonster(room.getMonster(), player);
                        room.getMonster().takeHit(player.getAttackPower());
                    }
                    else {
                        view.printAttackMissed();
                    }
                    if(room.getMonster().isDefeated()){
                    combatVictory(room);
                    inCombat = false;
                    break;
                    }
                    monsterAttack(room);
                    break;
                case "e":
                case "examine":
                    view.printExamineMonster(room.getMonster());
                    monsterAttack(room);
                    break;
                case "i":
                case "inventory":
                    printPlayerInventory();
                    break;
                case "u":
                case "use":
                    if(commands.length == 1) {
                        view.printUseFormat();
                        break;
                    }
                    String tempItem = "";
                    for(int i = 1; i < commands.length;i++){
                        tempItem += (commands[i] + " ");
                    }
                    tempItem = tempItem.substring(0, tempItem.length() - 1);
                    if(player.doesPlayerHaveItem(tempItem) != null) {
                        switch(player.doesPlayerHaveItem(tempItem).getType().toLowerCase()) {
                            case "combatitem":
                                ((CombatItem) player.doesPlayerHaveItem(tempItem)).use(room.getMonster());
                                view.printUsedItem(player.doesPlayerHaveItem(tempItem).getName());
                                if(room.getMonster().isDefeated()) {
                                    view.printSuperEffective();
                                    combatVictory(room);
                                    inCombat = false;
                                    break;
                                }
                                if(player.doesPlayerHaveItem(tempItem).getName().equalsIgnoreCase("Maracas")) {
                                    view.printFled();
                                    int tempCurrentRoom = player.getCurrentRoom();
                                    player.setCurrentRoom(player.getPreviousRoom());
                                    player.setPreviousRoom(tempCurrentRoom);
                                    printRoomDescription();
                                    printMenu();
                                    inCombat = false;
                                    break;
                                }
                                if(!room.getMonster().isDefeated()) {
                                    view.printNotEffective();
                                    monsterAttack(room);
                                }
                                break;
                            case "consumable":
                                player.addHp(((Consumable) player.doesPlayerHaveItem(tempItem)).getHealthPoints());
                                if((player.doesPlayerHaveItem(tempItem).getQuantity() == 1)) {
                                    player.removeItem(player.doesPlayerHaveItem(tempItem).getName());
                                } else {
                                    player.doesPlayerHaveItem(tempItem).decrementQuantity();
                                }
                                view.printPlayerHealth(player.getCurrentHp());
                                monsterAttack(room);
                                break;
                            default:
                                view.printFumbling();
                                monsterAttack(room);
                                break;
                        }
                    } else {
                        view.printDontHaveTempItem(tempItem);
                    }
                    break;
                //Edwin
                default:
                    view.printInvalidInput();
                    view.printCombatMenu();
                    break;
            }
            if(player.isDefeated()){
                view.printPlayerDefeated();
                inCombat = false;
                mainMenu();
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
    public void printItemDescription(Item tmpItem) {
        view.printItemDescription(this.player, tmpItem.getName());
    }

    // Paul
    // prints the current room's description
    public void printRoomDescription() {
        view.printRoomDescription(this.player.getCurrentRoomObject(), this.player.getCurrentRoomObject().isVisited());
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
        view.printRecievedItem(room.getPuzzle().getItem().getName());
        player.getInventory().add(room.getPuzzle().getItem());
        room.removePuzzle();
    }

    // Bao
    public void combatVictory(Room room) {
        view.printCombatVictory(room.getMonster());
        if(player.getCurrentRoomObject().getMonster().getItem() != null) {
            view.printRecievedItem(room.getMonster().getItem().getName());
            player.addItem(room.getMonster().getItem());
            }
        room.removeMonster();
        printRoomDescription();
        printMenu();

    }

    // Bao
    public void monsterAttack(Room room) {
        if(room.getMonster() != null) {
        view.printMonsterAttack(room.getMonster().getName());
        if(room.getMonster().checkHit()) {
            view.printTakeDamage(room.getMonster().getStrength());
            player.takeHit(room.getMonster().getStrength());
        }
        else {
            view.printMissed();
            }
        }
    }

}
