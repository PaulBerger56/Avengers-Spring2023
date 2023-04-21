import java.io.Serializable;

//Edwin
public class Collectible extends Item implements Serializable {

    private final String name;
    private final String description;
    private int quantity;
    private final int roomNumber;
    private final String type;

    public Collectible(String name, String description, int roomNumber){
        this.name = name;
        this.description = description;
        this.quantity = 1;
        this.roomNumber = roomNumber;
        this.type = "Collectible";
    }

    public void setQuantityToOne() {
        this.quantity = 1;
    }


    //Using a collectible item will just return the item's description
    public String use(Collectible item){
        return item.getDescription();
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


