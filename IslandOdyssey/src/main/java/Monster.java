public class Monster {
    //Created by Joseph
    String name;
    String description;
    int hitPoints;
    int strength;
    int attackChance;
    String weakness;
    Item item;
    boolean isDefeated;

    //From Joseph
    public Monster(String name, String description, int hitPoints, int strength, int attackChance, String weakness, Item item, boolean isDefeated){
        this.name = name;
        this.description = description;
        this.hitPoints = hitPoints;
        this.strength = strength;
        this.attackChance = attackChance;
        this.weakness = weakness;
        this.isDefeated = isDefeated;
    }


}
