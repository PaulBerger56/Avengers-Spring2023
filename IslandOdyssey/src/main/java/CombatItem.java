import java.io.Serializable;

//Edwin
public class CombatItem extends Item implements Serializable {

    private String name;
    private String description;
    private int quantity;
    private int roomNumber;
    private String type;

    public CombatItem(String name, String description, int roomNumber){
        this.name = name;
        this.description = description;
        this.quantity = 1;
        this.roomNumber = roomNumber;
        this.type = "CombatItem";
    }

    //When using a combat item, if the monster is weak to that item the monster will be defeated
    public void use(CombatItem item, Monster monster){
        if (monster.getWeakness().equalsIgnoreCase(item.getName())){
            monster.setDefeated(true);
        }
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
}
