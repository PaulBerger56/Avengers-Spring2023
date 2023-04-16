//Edwin
public class CombatItem extends Item{

    private String name;
    private String description;
    private int quantity;
    private int roomNumber;

    public CombatItem(String name, String description, int roomNumber){
        this.name = name;
        this.description = description;
        this.quantity = 1;
        this.roomNumber = roomNumber;
    }

    //When using a combat item, if the monster is weak to that item the monster will be defeated
    public void use(CombatItem item, Monster monster){
        if (monster.getWeakness().equalsIgnoreCase(item.getName())){
            monster.setDefeated(true);
        }
    }

}
