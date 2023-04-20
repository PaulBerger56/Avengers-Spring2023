import java.io.Serializable;

//Edwin
public class Consumable extends Item implements Serializable {

    private String name;
    private String description;
    private int quantity;
    private int healthPoints;
    private String type;

    public Consumable(String name, String description, int healthPoints){
        this.name = name;
        this.description = description;
        this.quantity = 1;
        this.healthPoints = healthPoints;
        this.type = "Consumable";
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
