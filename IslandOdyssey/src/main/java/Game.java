
// Paul
public class Game {

    // Paul
    public static void main(String[] args) {


    }

    // Paul
    // uses the default files to start a new game
    public static void newGame() {
        Player player = new Player(100, 100, "RoomFile.txt", "ItemFile.txt", "MonserFile.txt", "PuzzleFile.txt");
        PlayerView pv = new PlayerView();
        PlayerController pc = new PlayerController(player, pv);
        pc.play();
    }

    // Paul
    // loads the game from a saved .bin file of a player object
    public static void loadGame(String playerFile) {
        PlayerView pv = new PlayerView();
        PlayerController pc = new PlayerController(playerFile, pv);
        pc.play();
    }


}
