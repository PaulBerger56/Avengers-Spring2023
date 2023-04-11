import java.util.Scanner;

// Paul
public class PlayerController {

    Scanner scanner;
    Player player;
    View view;

    public PlayerController(Player player, View view) {
        this.scanner = new Scanner(System.in);
        this.player = player;
    }


}
