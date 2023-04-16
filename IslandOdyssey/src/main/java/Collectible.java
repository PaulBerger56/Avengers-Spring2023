//Edwin
public class Collectible extends Item{

    private String name;
    private String description;
    private int quantity;
    private int roomNumber;

    public Collectible(String name, String description, int roomNumber){
        this.name = name;
        this.description = description;
        this.quantity = 1;
        this.roomNumber = roomNumber;
    }


    //Using a collectible item will just return the item's description
    public String use(Collectible item){
        return item.getDescription();
    }

}
