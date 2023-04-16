import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Item {

    private String name;
    private String description;
    private int quantity;

    private FileReader fr;
    private Scanner sc;

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        quantity--;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getQuantity() {
        return quantity;
    }

    //Edwin
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

    //Joseph
    public abstract void use();
}

