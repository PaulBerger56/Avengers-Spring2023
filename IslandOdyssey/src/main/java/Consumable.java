//Edwin
public class Consumable extends Item{

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

    //Using a consumable heals the player by the amount of health points, up to the player's max HP
    //When used, the item quantity is decreased by 1
    public void use(Consumable consumable, Player player){
        player.addHp(consumable.getHealthPoints());
        consumable.decrementQuantity();
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
