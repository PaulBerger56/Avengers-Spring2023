import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.SortedMap;

public class Monster implements Serializable {
    //Created by Joseph
    private String name, monsterDescription, weakness;
    private int hitPoints, strength, attackChance;
    private Item item;
    private boolean isDefeated = false;

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
   public void addItemToMonster(Item monsterItem){
        Monster monster = new Monster();
        monster.addItemToMonster(monsterItem);
   }

    //Joseph
    public void takeHit(int hit){
        if(hitPoints - hit  <= 0) {
            this.hitPoints = 0;
            setDefeated(true);
        } else {
            hitPoints -= hit;
        }
    }
    
    //Joseph
    // checks if the monster is weak to a certain item, sets to defeated if true, and returns the boolean
    public boolean isWeakTo(String itemName){
        if (itemName.equalsIgnoreCase(this.weakness)){
            setDefeated(true);
            return true;
        }
        return false;
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
    public String getWeakness() {
        return weakness;
    }
    
    //Bao
    public Item getItem() {
    	return item;
    }
    //Bao
    public boolean checkHit() {
    	if(Math.ceil(Math.random() * 100) <= attackChance) {
    		return true;
    	}
    	return false;
    }
    
    
    public boolean isDefeated() {
    	return isDefeated;
    }

    //Joseph
    public void setDefeated(boolean isDefeated) {
        this.isDefeated = isDefeated;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}
