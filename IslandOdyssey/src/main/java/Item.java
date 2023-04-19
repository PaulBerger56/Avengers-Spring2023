import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Item {

    private String name;
    private String description;
    private int quantity;

    private String type;


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

}

