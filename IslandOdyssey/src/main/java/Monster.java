import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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

    //From Joseph
    public Monster(String name, String monsterDescription, int hitPoints, int strength, int attackChance, String weakness, Item item){
        this.name = name;
        this.monsterDescription = monsterDescription;
        this.hitPoints = hitPoints;
        this.strength = strength;
        this.attackChance = attackChance;
        this.weakness = weakness;
        this.item = item;
    }

    //Joseph
    public Item giveItemToPlayer(){
        item = null;
        return item;
    }
    //Joseph
//    public void addItemToMonster(){
//
//    }

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
//    public void battle(){
//
//    }
    //Joseph
    public boolean isWeakTo(String itemName){
        if (itemName.equals(weakness)){
            isDefeated = true;
            return true;
        }

        return false;
    }
    //
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
