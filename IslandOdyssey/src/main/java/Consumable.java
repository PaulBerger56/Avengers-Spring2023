//Edwin
public class Consumable extends Item{

    private String name;
    private String description;
    private int quantity;
    private int healthPoints;

    public Consumable(String name, String description, int healthPoints){
        this.name = name;
        this.description = description;
        this.quantity = 1;
        this.healthPoints = healthPoints;
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

}
