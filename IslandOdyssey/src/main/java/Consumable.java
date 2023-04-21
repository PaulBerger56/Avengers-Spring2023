import java.io.Serializable;

//Edwin
public class Consumable extends Item implements Serializable {

    private final String name;
    private final String description;
    private int quantity;
    private final int healthPoints;
    private final String type;

    public Consumable(String name, String description, int healthPoints){
        this.name = name;
        this.description = description;
        this.quantity = 1;
        this.healthPoints = healthPoints;
        this.type = "Consumable";
    }

    public void setQuantityToOne() {
        this.quantity = 1;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        quantity--;
    }

    public int getHealthPoints() {
        return healthPoints;
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

    public String getType() {
        return type;
    }

}
