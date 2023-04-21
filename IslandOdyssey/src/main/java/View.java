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
        String temp0 = "",temp1 = "", temp2 = "", temp3 = "", temp4 = "", temp5 = "", temp6 = "";
        int count = 0;
        for(int s: switchState) {
            count++;
            if(s == 0){
                temp0 += "(" + count + ")   ";
                temp1 += " 1    ";
                temp2 += " |    ";
                temp3 += " |    ";
                temp4 += " |\\   ";
                temp5 += " | \\  ";
                temp6 += " 0    ";
            } else {
                temp0 += "(" + count + ")   ";
                temp1 += " 1    ";
                temp2 += " | /  ";
                temp3 += " |/   ";
                temp4 += " |    ";
                temp5 += " |    ";
                temp6 += " 0    ";
            }
        }
        System.out.println(temp0);
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

    // Paul
    public void printInventoryIsEmpty() {
        System.out.println("The player's inventory is empty");
    }

    // Paul
    public void printMainMenu() {
        System.out.println("Start a (n)ew game, or (l)oad a save file, or (q)?");
    }

    // Paul
    public void printQuitting() {
        System.out.println("Now quitting.  Thank you for playing!");
    }

    // Paul
    public void printPlayerDefeated() {
        System.out.println("You died. Game Over! Please try again.");
    }

    // Joseph

    public void printDropItem(String name) {
        System.out.println("You dropped " + name + ".");
    }

    // Edwin
    public void printFlipValidNumber() {
        System.out.println("Please enter flip followed by a valid number of the switch you would like to flip.");
    }

    // Edwin
    public void printFailEnding() {
        System.out.println("You have used all your attempts!");
        System.out.println("You will never know the real end to your story!");
        System.out.println();
    }

    // Paul
    public void printFled() {
        System.out.println("You fled the battle.");
    }

    // Paul
    public void printSuperEffective() {
        System.out.println("That was supereffective!");
    }

    // Paul
    public void printUseFormat() {
        System.out.println("Please enter use and the item you would like to use.");
    }

    // Paul
    public void printExamineMonster(Monster monster) {
        System.out.println(monster.getMonsterDescription());
        System.out.println("While you were looking at the monster it attacked.");
    }

    //Paul
    public void printAttackMissed() {
        System.out.println("Your attack missed.");
    }

    // Paul
    public void printConfirmedHitOnMonster(Monster monster, Player player) {
        System.out.println("You hit " + monster.getName() + " for " + player.getAttackPower() + " damage!");
    }

    // Paul
    public void printPlayerAttacksMonster(Monster monster) {
        System.out.println("You attack " + monster.getName() + ".");
    }

    // Paul
    public void printSeeMonster(Monster monster) {
        System.out.println("You see " + monster.getName());
    }

    // Paul
    public void printFlippedSwitch(String s) {
        System.out.println("You have flipped switch #" + s);
    }

    // Paul
    public void printSwitchesInstructions(Room room) {
        System.out.println("You are presented with a row of 5 switches with a 1 inscribed on top and a 0 inscribed on the bottom of each switch.");
        System.out.println("The switches are all switched down.");
        System.out.println("Please input the decimal number " + room.getPuzzle().getDescription() + " in binary.");
    }

    // Paul
    public void printKeypadInstructions(Room room) {
        System.out.println("You are presented with a string of digits: " + room.getPuzzle().getDescription());
        System.out.println("Please enter the word you would get if you entered this number into a keypad.");
    }

    // Paul
    public void printOneWordAnswer() {
        System.out.println("Please enter a one word answer.");
    }

    public void printKeypadWrongAnswer(Room room) {
        System.out.println("Your answer was wrong. Try again.");
        System.out.println("You have " + room.getPuzzle().getAttempts() + "attempts left.");
    }

    // Paul
    public void printKeypadOutOfAttempts() {
        System.out.println("You have run out of attempts. Please try again later.");
    }

    // Paul
    public void printWrongPin() {
        System.out.println("Wrong pin.");
    }

    // Paul
    public void printCombatVictory(Monster monster) {
        System.out.println("Victory! You defeated " + monster.getName());
    }

    // Paul
    public void printRecievedItem(String itemName) {
        System.out.println("You received " + itemName);
        System.out.println();
    }

    // Paul
    public void printTakeDamage(int strength) {
        System.out.println("You took " + strength + " damage");
    }

    // Paul
    public void printMissed() {
        System.out.println("It missed.");
    }

    // Paul
    public void printDontHaveTempItem(String tempItem) {
        System.out.println("You do not have " + tempItem);
    }

    // Paul
    public void printFumbling() {
        System.out.println("You can't use that right now!");
        System.out.println("While you were fumbling around, the monster attacks.");
    }

    // Paul
    public void printNotEffective() {
        System.out.println("That was not very effective.");
    }

    // Paul
    public void printFourDigits() {
        System.out.println("Pin must be 4 digits!");
    }

    // Paul
    public void printEnding() {
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
    }

    public void printInteractableInstructions() {
        System.out.println("An old satellite phone. You try dialing some numbers, but the call doesn’t go through." +
                " It is asking for the pin number. Enter 0000 to put the phone away");
    }

    public void printInteractableClosed() {
        System.out.println("The phone is unresponsive...  Hopefully it wasn't too important...");
    }

    public void printPlayerHp(Player player) {
        System.out.println("The player currently has " + player.getCurrentHp() + " hp");
    }

    //Edwin
    public void printAttemptsLeft(Interactable interactable) {
        if (interactable.getAttempts() == 5){
            System.out.println("You have " + interactable.getAttempts() + " attempts.");
        }
        else {
            System.out.println("You have " + interactable.getAttempts() + " attempts left.");
        }
    }

    //Edwin
    public void printEmptyLine(){
        System.out.println();
    }

    //Edwin
    public void printHelpMenu() {
        System.out.println("\nCommands:\n");
        System.out.println("N E S W - Use this to move around between rooms.");
        System.out.println("Inventory - Use this when you want a list of your items in your inventory.");
        System.out.println("Pickup [item] - Use this to pick an item up from the room you are in.");
        System.out.println("Drop [item] - Use this to drop an item in the room you are in.");
        System.out.println("Use [item] - Use this to use an item in your inventory.");
        System.out.println("Inspect [item] - Use this to get a description of an item in your inventory.");
        System.out.println("Explore - Use this to reveal items and puzzles in the room you are in.");
        System.out.println("Examine - Use this when a room contains a strange device and you would like to initiate a puzzle.");
        System.out.println("Health - Use this when you would like to know your current health points.");
        System.out.println("Save - Use this when you would like to save the game.");
    }

    //Paul
    public void printWouldYouLikeToQuit() {
        System.out.println("Would you like to quit for now? (y/n)");
    }

    public void printGoodLuck() {
        System.out.println("Good luck on your adventure!");
    }
}
