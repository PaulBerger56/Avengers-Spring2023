//Edwin
public class Consumable extends Item{

    private String name;
    private String description;
    private int quantity;
    private int healthPoints;
    private int roomNumber;

    public Consumable(String name, String description, int healthPoints, int roomNumber){
        this.name = name;
        this.description = description;
        this.quantity = 1;
        this.healthPoints = healthPoints;
        this.roomNumber = roomNumber;
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
