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
        readRoom(roomFile);
        readItems(itemFile);
        readMonster(monsterFile);
        readPuzzles(puzzleFile);
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    //Joseph
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
                String name = data[0];
                String monsterDescription = data[1];
                int hitPoints = Integer.parseInt(data[2]);
                int strength = Integer.parseInt(data[3]);
                int attackChance = Integer.parseInt(data[4]);
                String weakness = data[5];
                int roomID = Integer.parseInt(data[6]);

                Monster monster = new Monster(name,monsterDescription,hitPoints,strength,attackChance,weakness, roomID);


            }catch(NumberFormatException ex){
                System.out.println(ex.getMessage());
            }

        }
        read.close();
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
    //Edwin
    // moved to map class by Joseph
    public void readItems(String itemFile) {
        ArrayList<Item> items = new ArrayList<>();
        try {
            fr = new FileReader(itemFile);
            sc = new Scanner(fr);
        } catch (FileNotFoundException fnfe) {
        }

        while (sc.hasNext()) {
            String data = sc.nextLine();
            String[] tokens = data.split("~");

            try {
                switch (tokens[0].toLowerCase()) {
                    case "consumable":
                        String itemName = tokens[1];
                        String itemDesc = tokens[2];
                        int healthPoints = Integer.parseInt(tokens[3]);
                        int roomNumber = Integer.parseInt(tokens[4]);
                        items.add(new Consumable(itemName, itemDesc, healthPoints, roomNumber));
                        break;
                    case "combat":
                        itemName = tokens[1];
                        itemDesc = tokens[2];
                        roomNumber = Integer.parseInt(tokens[3]);
                        items.add(new CombatItem(itemName, itemDesc, roomNumber));
                        break;
                    case "collectible":
                        itemName = tokens[1];
                        itemDesc = tokens[2];
                        roomNumber = Integer.parseInt(tokens[3]);
                        items.add(new Collectible(itemName, itemDesc, roomNumber));
                        break;
                    case "interactable":
                        itemName = tokens[1];
                        itemDesc = tokens[2];
                        roomNumber = Integer.parseInt(tokens[3]);
                        items.add(new Interactable(itemName, itemDesc, roomNumber));
                        break;
                }
            } catch (NumberFormatException nfe) {
            }
        }

        sc.close();
    }
    //Joseph
    public void readPuzzles(String puzzleFile){
        ArrayList<Puzzle> puzzles = new ArrayList<>();
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
                        String name = data[1];
                        String description = data[2];
                        String solution = data[3];
                        int maxAttempts = Integer.parseInt(data[4]);
                        int roomID = Integer.parseInt(data[5]);
                        puzzles.add(new Switches(name,description,solution,maxAttempts));
                        break;
                    case "1":
                        name = data[1];
                        description = data[2];
                        solution = data[3];
                        maxAttempts = Integer.parseInt(data[4]);
                        roomID = Integer.parseInt(data[5]);
                        puzzles.add(new Keypad(name, description, solution, maxAttempts));
                        break;

                }
            }catch (NumberFormatException ex){
                System.out.println(ex.getMessage());
            }
        }
        puzzleReader.close();
    }

}
