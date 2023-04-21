//Edwin
import java.io.Serializable;
import java.util.Scanner;

public class Interactable extends Item implements Serializable {

    private String name;
    private String description;
    private int quantity;
    private int roomNumber;

    private String type;

    private String pin;
    private int attempts;
    private boolean hasUsedMaxAttempts = false;

    public Interactable(String name, String description, int roomNumber){
        this.name = name;
        this.description = description;
        this.quantity = 1;
        this.roomNumber = roomNumber;
        this.type = "Interactable";
        this.pin = "6057";
        this.attempts = 5;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public String getPin() {
        return pin;
    }

    public int getAttempts() {
        return attempts;
    }

    public boolean getHasUsedMaxAttempts() {
        return hasUsedMaxAttempts;
    }

    public void setHasUsedMaxAttempts(boolean maxAttempts){
        this.hasUsedMaxAttempts = maxAttempts;
    }

    public void decrementAttempts(){
        attempts--;
    }
}
