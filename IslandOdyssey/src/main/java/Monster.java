import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.SortedMap;

public class Monster {
    //Created by Joseph
    String name;
    String monsterDescription;
    int hitPoints;
    int strength;
    int attackChance;
    String weakness;
    Item item;
    boolean isDefeated = false;

    public Monster(){

    }
    //From Joseph
    public Monster(String name, String monsterDescription, int hitPoints, int strength, int attackChance, String weakness){
        this.name = name;
        this.monsterDescription = monsterDescription;
        this.hitPoints = hitPoints;
        this.strength = strength;
        this.attackChance = attackChance;
        this.weakness = weakness;
    }

    //Joseph
    public Item giveItemToPlayer(){
        item = null;
        return item;
    }
    //Joseph
   public void addItemToMonster(Item monsterItem){
        Monster monster = new Monster();
        monster.addItemToMonster(monsterItem);
   }

    //Joseph
    public void takeHit(int hit){
        hitPoints -= hit;

        if(isWeakTo(weakness)){
            hitPoints = 0;
            whenDefeated();
        }
        if(hitPoints == 0 ){
            whenDefeated();
        }
        isDefeated = false;

    }
    //Joseph
    public void battle(){
        System.out.println("This is " + getName());
        Monster battleMonster = new Monster();
        Player player = new Player("RoomFile.txt","ItemFile.txt","MonsterFile.txt","PuzzleFile.txt");
        System.out.println("Do you want to attack the monster or ignore the call for battle!");
        Scanner input = new Scanner(System.in);
        String action = input.nextLine();
        while(!action.equalsIgnoreCase("Ignore")){
            if(action.equalsIgnoreCase("examine")){
                battleMonster.getMonsterDescription();
            }
            if(action.equalsIgnoreCase("Attack") || action.equalsIgnoreCase("A")){
                battleMonster.takeHit(player.getAttackPower());
                player.takeHit(battleMonster.getStrength());

                if(action.equalsIgnoreCase("Use")){
                    player.use(item);
                    if (item.equals(weakness)){
                        String endItem = weakness;
                        battleMonster.isWeakTo(endItem);
                        break;
                    }
                    else if(item.equals("Maracas")){
                        player.getPreviousRoom();
                        break;
                    }
                }
                battleMonster.whenDefeated();
                break;

            }
        }
        player.getPreviousRoom();

    }
    //Joseph
    public boolean isWeakTo(String itemName){
        if (itemName.equals(weakness)){
            isDefeated = true;
            return true;
        }

        return false;
    }
    //Joseph
    public void whenDefeated(){
        isDefeated = true;
    }
    //Joseph
    public String getName() {
        return name;
    }
    //Joseph
    public String getMonsterDescription() {
        return monsterDescription;
    }
    //Joseph
    public int getHitPoints() {
        return hitPoints;
    }
    //Joseph
    public int getStrength() {
        return strength;
    }

    //Edwin
    public String getWeakness(){
        return weakness;
    }
    //Joseph
    public void setDefeated(boolean isDefeated){
        this.isDefeated = isDefeated;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
