import java.util.ArrayList;
import java.util.Arrays;

public class Map {

    private ArrayList<Room> rooms;

    public Map(String roomFile, String itemFile, String monsterFile, String puzzleFile) {
        this.rooms = new ArrayList<>(fillMap(roomFile, itemFile, monsterFile, puzzleFile));
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<Room> fillMap(String roomFile, String itemFile, String monsterFile, String puzzleFile) {

    }
}
