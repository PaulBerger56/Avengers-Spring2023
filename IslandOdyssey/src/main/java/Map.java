import java.awt.event.ItemEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Map {
    private FileReader fr;
    private Scanner sc;
    private Scanner read;
    private FileReader monster;
    private FileReader puzzleScan;
    private Scanner puzzleReader;

    private ArrayList<Room> rooms;
    
    //Joseph and Bao
    public Map(String roomFile, String itemFile, String monsterFile, String puzzleFile) {
        this.rooms = new ArrayList<>();
        readRoom(roomFile);
        readItems(itemFile);
        readMonster(monsterFile);
        readPuzzles(puzzleFile);

    }
    
    //Bao and Joseph
    public void readRoom(String roomFile){
        try{
            fr = new FileReader(roomFile);
            sc = new Scanner(fr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(sc.hasNext()){
            String data = sc.nextLine();
            String[] reading = data.split("~");

            try{
                int roomNumber = Integer.parseInt(reading[0]);
                String name = reading[1];
                String description = reading[2];
                int northExit = Integer.parseInt(reading[3]);
                int eastExit = Integer.parseInt(reading[4]);
                int southExit = Integer.parseInt(reading[5]);
                int westExit = Integer.parseInt(reading[6]);
                Room roomies = new Room(roomNumber,name, description,northExit,eastExit,southExit,westExit);
                rooms.add(roomies);
            }catch (NumberFormatException ex){
                System.out.println(ex.getMessage());
            }
        }
        sc.close();
    }

    //Joseph and Bao
    //Method to read the monster text file
    public void readMonster(String monsterFile){

        try{
            monster = new FileReader(monsterFile);
            read = new Scanner(monster);
        }catch (FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        while(read.hasNext()){
            String memory = read.nextLine();
            String[] data = memory.split("~");
            try{
                rooms.get(Integer.parseInt(data[6])).addMonster(new Monster(data[0],data[1],Integer.parseInt(data[2]),Integer.parseInt(data[3]),Integer.parseInt(data[4]),data[5]));
            }catch(NumberFormatException ex){
                System.out.println(ex.getMessage());
            }

        }
        read.close();
    }

    //Edwin and Bao
    public void readItems(String itemFile) {
        try {
            fr = new FileReader(itemFile);
            sc = new Scanner(fr);
        } catch (FileNotFoundException fnfe) {
        }

        while (sc.hasNext()) {
            String data = sc.nextLine();
            String[] tokens = data.split("~");

            try {
                String itemName = tokens[1];
                String itemDesc = tokens[2];
                int roomNumber =Integer.parseInt(tokens[3]);
                switch (tokens[0].toLowerCase()) {
                    case "consumable":
                        int healthPoints = Integer.parseInt(tokens[4]);
                        rooms.get(roomNumber).addItem(new Consumable(itemName, itemDesc, healthPoints));
                        break;
                    case "combat":
                        rooms.get(roomNumber).addItem(new CombatItem(itemName, itemDesc, roomNumber));
                        break;
                    case "collectible":
                        rooms.get(roomNumber).addItem(new Collectible(itemName, itemDesc, roomNumber));
                        break;
                    case "interactable":
                        rooms.get(roomNumber).addItem(new Interactable(itemName, itemDesc, roomNumber));
                        break;
                }
            } catch (NumberFormatException nfe) {
            	nfe.printStackTrace();
            }
        }

        sc.close();
    }
    //Joseph
    public void readPuzzles(String puzzleFile){
        try{
            puzzleScan = new FileReader(puzzleFile);
            puzzleReader = new Scanner(puzzleScan);
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
        while(puzzleReader.hasNext()){
            String puzzleData = puzzleReader.nextLine();
            String[] data = puzzleData.split("~");
            try{
                switch (data[0].toLowerCase()){
                    case "0":
                        rooms.get(Integer.parseInt(data[4])).addPuzzle(new Switches(data[1],data[2],Integer.parseInt(data[3])));
                        break;
                    case "1":
                    	rooms.get(Integer.parseInt(data[4])).addPuzzle(new Keypad(data[1],data[2],Integer.parseInt(data[3])));
                        break;

                }
            }catch (NumberFormatException ex){
                System.out.println(ex.getMessage());
            }
        }
        puzzleReader.close();
    }
    
    public ArrayList<Room> getRooms() {
        return rooms;
    }

}
