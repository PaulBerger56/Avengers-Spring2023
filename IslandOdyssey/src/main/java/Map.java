import java.awt.event.ItemEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Map implements Serializable {

    private final ArrayList<Room> rooms;
    private final ArrayList<Consumable> monsterDrops;
    private final ArrayList<Integer> monsterLocations;

    //Joseph and Bao
    public Map(String roomFile, String itemFile, String monsterFile, String puzzleFile) {
        this.rooms = new ArrayList<>();
        this.monsterDrops = new ArrayList<>();
        this.monsterLocations = new ArrayList<>();
        readRoom(roomFile);
        readPuzzles(puzzleFile);
        readMonster(monsterFile);
        readItems(itemFile);
        distributeDrops();
    }
    
    //Bao and Joseph
    public void readRoom(String roomFile){

        try{
            File readRoomFile = new File(roomFile);
            Scanner roomScanner = new Scanner(readRoomFile);

            while(roomScanner.hasNext()) {
                String data = roomScanner.nextLine();
                String[] reading = data.split("~");


                int roomNumber = Integer.parseInt(reading[0]);
                String name = reading[1];
                String description = reading[2];
                int northExit = Integer.parseInt(reading[3]);
                int eastExit = Integer.parseInt(reading[4]);
                int southExit = Integer.parseInt(reading[5]);
                int westExit = Integer.parseInt(reading[6]);
                Room roomies = new Room(roomNumber,name, description,northExit,eastExit,southExit,westExit);
                rooms.add(roomies);

            }
            roomScanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //Joseph and Bao
    //Method to read the monster text file
    public void readMonster(String monsterFile){

        try {

            File readMonsterFile = new File(monsterFile);
            Scanner monsterScanner = new Scanner(readMonsterFile);

            while (monsterScanner.hasNext()) {

                String memory = monsterScanner.nextLine();
                String[] data = memory.split("~");
                try{
                    rooms.get(Integer.parseInt(data[6])).addMonster(new Monster(data[0],data[1],Integer.parseInt(data[2]),Integer.parseInt(data[3]),Integer.parseInt(data[4]),data[5]));
                    monsterLocations.add(Integer.parseInt(data[6]));
                }catch(NumberFormatException ex){
                    System.out.println(ex.getMessage() + "\u001B[31m" + "monster file problem" + "\u001B[0m");
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //Edwin and Bao
    public void readItems(String itemFile) {
        try {
            File readItemFile = new File(itemFile);
            Scanner sc = new Scanner(readItemFile);


        while (sc.hasNext()) {
            String data = sc.nextLine();
            String[] tokens = data.split("~");

            String itemName = tokens[1];
            String itemDesc = tokens[2];
            int roomNumber = Integer.parseInt(tokens[3]);
            switch (tokens[0].toLowerCase()) {
                case "consumable":
                    int healthPoints = Integer.parseInt(tokens[4]);
                    rooms.get(roomNumber).addItem(new Consumable(itemName, itemDesc, healthPoints));
                    monsterDrops.add(new Consumable(itemName, itemDesc, healthPoints));
                    break;
                case "combat":
                    rooms.get(roomNumber).addItem(new CombatItem(itemName, itemDesc, roomNumber));
                    break;
                case "collectible":
                    rooms.get(roomNumber).getPuzzle().setItem(new Collectible(itemName, itemDesc, roomNumber));
                    break;
                case "interactable":
                    rooms.get(roomNumber).addItem(new Interactable(itemName, itemDesc, roomNumber));
                    break;
                }
            }
                sc.close();
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
                System.out.println("\u001B[31m" + "Item file problem" + "\u001B[0m");
            } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //Joseph
    public void readPuzzles(String puzzleFile) {
        try{
            File readPuzzleFile = new File(puzzleFile);
            Scanner puzzleReader = new Scanner(readPuzzleFile);

            while (puzzleReader.hasNext()) {
                String puzzleData = puzzleReader.nextLine();
                String[] data = puzzleData.split("~");

                switch (data[0].toLowerCase()) {
                    case "0":
                        rooms.get(Integer.parseInt(data[4])).addPuzzle(new Switches(data[1], data[2], Integer.parseInt(data[3])));
                        break;
                    case "1":
                        rooms.get(Integer.parseInt(data[4])).addPuzzle(new Keypad(data[1], data[2], Integer.parseInt(data[3])));
                        break;
                }
            }
            puzzleReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
    
    public void distributeDrops() {
    	for(int room: this.monsterLocations) {
    		int temp = (int)Math.random() * 3;
    		rooms.get(room).getMonster().addItemToMonster(new Consumable(monsterDrops.get(temp).getName(), monsterDrops.get(temp).getDescription(), monsterDrops.get(temp).getHealthPoints()));
    	}
    }
    


    public ArrayList<Room> getRooms() {
        return rooms;
    }

}
