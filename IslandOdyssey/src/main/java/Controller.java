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
        view.printMainMenu();
        String command = scanner.nextLine();

        switch(command) {
            case "n":
                this.player = new Player("PlayerFile.txt","RoomFile.txt", "ItemFile.txt", "MonsterFile.txt", "PuzzleFile.txt");
                play(this.player);

            case "l":
                this.player = readInPlayer("SaveFile.bin");
                play(this.player);

            case "q":
                view.printQuitting();
                System.exit(0);
                break;

            default:
                view.printInvalidInput();
                break;
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
                    //Joseph
                    case "inspect":
                        String inspectItem = "";
                        for(int i = 1; i < splitCommand.length;i++){
                            inspectItem += (splitCommand[i] + " ");
                        }
                        inspectItem = inspectItem.substring(0, inspectItem.length() - 1);
                        if(player.doesPlayerHaveItem(inspectItem)!= null){
                            printItemDescription(player.doesPlayerHaveItem(inspectItem));
                        }
                        break;
                        //Joseph
                    case "drop":
                        String tmpDropItem = "";
                        for(int i = 1; i < splitCommand.length;i++){
                            tmpDropItem += (splitCommand[i] + " ");
                        }
                        tmpDropItem = tmpDropItem.substring(0, tmpDropItem.length() - 1);
                        Item interimItem = player.doesPlayerHaveItem(tmpDropItem);

                        if(interimItem!= null){
                            player.getCurrentRoomObject().addItem(interimItem);
                            player.removeItem(interimItem.getName());
                            view.printDropItem(interimItem.getName());
                        }
                        break;

                    case "health":
                        break;

                    case "save":
                        view.printSaveMessage();
                        saveGame();
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

    //Edwin
    private void useInteractable(Item tempItem) {
        Interactable tempInteractable = (Interactable) tempItem;
        if (tempInteractable.getHasUsedMaxAttempts() == true){
            System.out.println("You can't use the phone again scrub!");
            printMenu();
        }
        while (tempInteractable.getHasUsedMaxAttempts() == false){

            if (tempInteractable.getAttempts() == 0){
                tempInteractable.setHasUsedMaxAttempts(true);
                view.printFailEnding();
                printMenu();
                break;
            }
            Scanner input = new Scanner(System.in);
            System.out.println("An old satellite phone. You try dialing some numbers, but the call doesn’t go through." +
                    " It is asking for the pin number. Enter 0000 to put the phone away");
            String attempt = input.nextLine();

            if (attempt.equalsIgnoreCase("0000")){
                break;
            }
            else if (attempt.equals(tempInteractable.getPin())){
                //Trigger ending
                System.out.println("’Pin accepted.’\n" +
                        "\n" +
                        "Those words brighten up your entire life. The phone is waiting for you to dial the number of the party you wish to reach.\n" +
                        "\n" +
                        "You decide that calling 9-1-1 would be your best shot of reaching someone that could help you in this situation.\n" +
                        "\n" +
                        "It rings. It rings again. It rings again.\n" +
                        "\n" +
                        "‘9-1-1 what is your emergency?’\n" +
                        "\n" +
                        "Oh, thank heavens.\n" +
                        "\n" +
                        "‘H-hi, my name is [redacted], and I’ve been stranded on a deserted island with no knowledge of how I ended up here. The\n" +
                        "\n" +
                        "last thing I remember was working my shift at [redacted] and losing consciousness.’\n" +
                        "\n" +
                        "‘Okay, do you have any idea of where you are located right now?’\n" +
                        "\n" +
                        "‘I’m not entirely sure. This island looks deserted, but it’s got so much wildlife on it. There’s a mountain with a great view, a volcano, a lighthouse, a sky garden... It’s a bit of a shame nobody else is here, honestly. There’s also been a shipwreck on the edge of the island. I’m calling from a satellite phone I found in the sick bay.’\n" +
                        "\n" +
                        "The officer pauses for a moment. ‘...okay sir, we’re aware of a ship that’s been missing for a few weeks now. We’ll send helicopters out to look for an island with a ship and all these landmarks you’ve described. Please hang tight for as long as possible.’\n" +
                        "\n" +
                        "You detected a hint of distrust in the officer’s voice, but you’re not going to accuse the person who’s about to save your life of anything.\n" +
                        "\n" +
                        "‘Alright, thank you so much. I don’t know what I would’ve done without you.’\n" +
                        "\n" +
                        "What feels like a couple of hours has passed. You’ve made yourself as comfortable as possible on the ship while keeping tabs on the outside to check if any helicopters have flown by. You’ve also carved a large SOS in the sand just outside of the ship to hopefully indicate to anyone flying by of your location.\n" +
                        "\n" +
                        "A slight buzzing of helicopter rotors fills your ear. You rush out of the sickbay and out of the ship and scream at the top of your lungs. The helicopter pauses for a second in the air, then heads right for your location.\n" +
                        "\n" +
                        "The chopper lands near your location. You run up to the pilot and give them your most sincere thanks. They’re not too keen on conversating right now. You’re instructed to head into the back seat and answer questions that they have for you on the ride home. You are, of course, given some food and water before you set for the skies.\n" +
                        "\n" +
                        "It feels like an interrogation. What could you have possibly done wrong? You're asked various questions about names you’ve never heard in your life and what you were doing last month. You answer as honestly as possible, but you never shake the feeling of disco\n" +
                        "\n" +
                        "It’s been a year since the incident, and you feel more endangered at home than you felt on the island. The police suspect you’re the one responsible for the murder of the entire crew of the ship Mordor. It’s folklore at this point that you’re the one who caused the death of an entire crew. You’re not allowed to go 100 miles past the city limits. Your family understands your situation and knows the truth, but the long arm of the law may never know.\n" +
                        "\n" +
                        "The end.");
                mainMenu();
            }
            else if (attempt.length() != 4 || !attempt.matches("[0-9]+")){
                System.out.println("Pin must be 4 digits!");
            }
            else {
                System.out.println("Wrong pin.");
                tempInteractable.decrementAttempts();
            }
        }
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
                                view.printFlipValidNumber();
                            } else if(!commands1[1].equals("1") && !commands1[1].equals("2") && !commands1[1].equals("3") && !commands1[1].equals("4") && !commands1[1].equals("5")) {
                                view.printFlipValidNumber();
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
        boolean inCombat = true;
        while(inCombat) {
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
                    combatVictory(room);
                    inCombat = false;
                    break;
                    }
                    monsterAttack(room);
                    break;
                case "e":
                case "examine":
                    view.print(room.getMonster().getMonsterDescription());
                    view.print("While you were looking at the monster it attacked.");
                    monsterAttack(room);
                    break;
                case "i":
                case "inventory":
                	printPlayerInventory();
                	break;
                case "u":
                case "use":
                	if(commands.length == 1) {
                		view.print("Please enter use and the item you would like to use.");
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
                                	view.print("That was supereffective!");
                                	combatVictory(room);
                                	inCombat = false;
                                	break;
                                }
                                if(player.doesPlayerHaveItem(tempItem).getName().equalsIgnoreCase("Maracas")) {
                                	view.print("You fled the battle.");
                                	int tempCurrentRoom = player.getCurrentRoom();
                                	player.setCurrentRoom(player.getPreviousRoom());
                                	player.setPreviousRoom(tempCurrentRoom);
                                	printRoomDescription();
                                	printMenu();
                                	inCombat = false;
                                	break;
                                }
                                if(!room.getMonster().isDefeated()) {
                                    view.print("That was not very effective.");
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
                                view.print("You can't use that right now!");
                                view.print("While you were fumbling around, the monster attacks.");
                                monsterAttack(room);
                                break;
                        }
                    } else {
                        view.print("You do not have " + tempItem);
                    }
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
        view.print("You received " + room.getPuzzle().getItem().getName());
        player.getInventory().add(room.getPuzzle().getItem());
        room.removePuzzle();
    }
    
    public void combatVictory(Room room) {
    	view.print("Victory! You defeated " + room.getMonster().getName());
    	if(player.getCurrentRoomObject().getMonster().getItem() != null) {
    		view.print("You received " + room.getMonster().getItem().getName());
    		player.addItem(room.getMonster().getItem());
            }
    	room.removeMonster();
    	printRoomDescription();
    	printMenu();
    	
    }
    
    public void monsterAttack(Room room) {
        if(room.getMonster() != null) {
        view.printMonsterAttack(room.getMonster().getName());
        if(room.getMonster().checkHit()) {
            view.print("You took " + room.getMonster().getStrength() + " damage");
            player.takeHit(room.getMonster().getStrength());
        }
        else {
            view.print("It missed.");
            }
        }
    }

}
