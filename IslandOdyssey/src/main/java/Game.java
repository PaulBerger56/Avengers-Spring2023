
// Paul
public class Game {

    // Paul
    public static void main(String[] args) {
    newGame();
    }

    // Paul
    // uses the default files to start a new game
    public static void newGame() {
        Player player = new Player("RoomFile.txt", "ItemFile.txt", "MonsterFile.txt", "PuzzleFile.txt");
        View pv = new View();
        Controller pc = new Controller(player, pv);
        pc.play();
    }

    // Paul
    // loads the game from a saved .bin file of a player object
    public static void loadGame(String playerFile) {
        View pv = new View();
        Controller pc = new Controller(playerFile, pv);
        pc.play();
    }


}
