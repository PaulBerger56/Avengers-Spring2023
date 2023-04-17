import java.awt.event.ItemEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Map {
    private FileReader fr;
    private Scanner sc;
    private Scanner read;
    private FileReader Monster;

    private ArrayList<Room> rooms;

    public Map(String roomFile, String itemFile, String monsterFile, String puzzleFile) {
        this.rooms = new ArrayList<>(fillMap(roomFile, itemFile, monsterFile, puzzleFile));
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    //Joseph
    //Method to read the monster text file
    public ArrayList<Monster> readMonster(){
        ArrayList<Monster> monsters = new ArrayList<>();
        try{
            Monster = new FileReader("MonsterFile.txt");
            read = new Scanner(Monster);
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

                Monster monster = new Monster(name,monsterDescription,hitPoints,strength,attackChance,weakness);
                monsters.add(monster);

            }catch(NumberFormatException ex){
                System.out.println(ex.getMessage());
            }

        }
        read.close();
        return monsters;
    }

    //Edwin moved to map class by Joseph
    public ArrayList<Item> readItems(){
        ArrayList<Item> items = new ArrayList<>();
        try {
            fr = new FileReader("items.txt");
            sc = new Scanner(fr);
        } catch (FileNotFoundException fnfe){}

        while (sc.hasNext()){
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
            } catch (NumberFormatException nfe){}
        }

        sc.close();
        return items;
    }

    public ArrayList<Room> fillMap(String roomFile, String itemFile, String monsterFile, String puzzleFile) {

    }
}
