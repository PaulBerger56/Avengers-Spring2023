import java.awt.event.ItemEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Map {

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
                //Don't know how to parse item yet
                Item item = null;

                Monster monster = new Monster(name,monsterDescription,hitPoints,strength,attackChance,weakness,item);
                monsters.add(monster);

            }catch(NumberFormatException ex){
                System.out.println(ex.getMessage());
            }

        }
        read.close();
        return monsters;
    }
    public ArrayList<Room> fillMap(String roomFile, String itemFile, String monsterFile, String puzzleFile) {

    }
}
