//Edwin
import java.io.Serializable;
import java.util.Scanner;

public class Interactable extends Item implements Serializable {

    private final String name;
    private final String description;
    private int quantity;
    private final int roomNumber;

    private final String type;

    private final String pin;
    private int attempts;
    private boolean hasUsedMaxAttempts = false;

    public Interactable(String name, String description, int roomNumber, String pin){
        this.name = name;
        this.description = description;
        this.quantity = 1;
        this.roomNumber = roomNumber;
        this.type = "Interactable";
        this.pin = pin;
        this.attempts = 5;
    }

    public void setQuantityToOne() {
        this.quantity = 1;
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
