
// Paul
public class Game {

    public static void main(String[] args) {


    }

    // uses the default files to start a new game
    public static void newGame() {
        Player player = new Player(100, 100, "RoomFile.txt", "ItemFile.txt", "MonserFile.txt", "PuzzleFile.txt");
        PlayerView pv = new PlayerView();
        PlayerController pc = new PlayerController(player, pv);
        pc.play();
    }

    public static void loadGame(String playerFile) {

    }

    public static void saveGame(Player player) {

    }
}
