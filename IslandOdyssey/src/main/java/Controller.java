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

    //Bao
    // Sysout not final, should be replaced with view commands
    public void playPuzzle(Room room) {
    	switch(room.getPuzzle().getType()) {
    	case "Keypad":
    		view.print("You are presented with a string of digits: " + room.getPuzzle().getDescription());
    		view.print("Please enter the word you would get if you entered this number into a keypad.");
    		String[] commands = scanner.nextLine().toLowerCase().split(" ");
    		boolean hasSolved = false;
    		while(!hasSolved) {
	    		if(commands.length == 1){
	    			if(room.getPuzzle().check(commands[1])) {
	    				hasSolved = true;
	    				solvedPuzzle(room);
	    			}
	    			else if(room.getPuzzle().getAttempts() == 0) {
	    				view.print("You have run out of attempts. Please try again later.");
	    				room.getPuzzle().setAttempts(room.getPuzzle().getMaxAttempts());
	    			}
	    			else {
	    				room.getPuzzle().setAttempts(room.getPuzzle().getAttempts() - 1);
	    				view.print("Your answer was wrong. Try again.");
	    				view.print("You have " + room.getPuzzle().getAttempts() + "attempts left.");
	    			}
	    		}
	    		else{
	    			view.print("Please enter a one word answer.");
	    		}
    		}
    		break;
    	case "Switches":
    		view.print("You are presented with a row of 5 switches.");
    		view.print("The switches are all switched down.");
    		view.print("Please input the decimal number" + room.getPuzzle().getDescription() + " in binary.");
    		view.printSwitchPuzzleMenu();
    		boolean hasSolved1 = false;
    		while(!hasSolved1){
    			String[] commands1 = scanner.nextLine().toLowerCase().split(" ");
    			switch(commands1[0]) {
    			case "s":
    			case "submit":
    				if(room.getPuzzle().check(null)){
    					hasSolved1 = true;
    					solvedPuzzle(room);
    				}
    					;
    				break;
    			case "f":
    			case "flip":
    				if(commands1.length != 2) {
    					view.print("Please enter flip and the number you would like to flip.");
    				}
    				else if(commands1[1] != "1" && commands1[1] != "2" && commands1[1] != "3" && commands1[1] != "4" && commands1[1] != "5") {
    					view.print("Please enter flip and a valid number of the switch you would like to flip.");
    				}
    				else {
    					((Switches) room.getPuzzle()).flip(commands1[1]);
    				}
    					
    				break;
    			
    		}
    		break;
    		}
    	}

    }

    //Bao and Joseph
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
    
    //Bao
    public void solvedPuzzle(Room room) {
    	view.print(room.getPuzzle().puzzleSolvedMessage());
		player.getInventory().add(room.getPuzzle().getItem());
		room.removePuzzle();
    }



}
