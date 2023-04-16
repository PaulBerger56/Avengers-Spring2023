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
    //Joseph
    public Item giveItemToPlayer(){
        if(isDefeated == true){
            System.out.println("Here's " + item + "will you pick it up?");
            Scanner input = new Scanner(System.in);
            String answer = input.nextLine();

            if(answer.equalsIgnoreCase("yes")){
                System.out.println(item + " is a part of your inventory.");
            }
            else if(answer.equalsIgnoreCase("no")){
                System.out.println("Okay hope I don't regret this later.");
            }
        }
        return item;
    }
    //Joseph
    public void addItemToMonster(){
        Monster roomMonster = new Monster("Khabib","BBBB",90,2,32,"Darkness",item,false);
        roomMonster.addItemToMonster();
    }

    //Joseph
    public void takeHit(int hit){
        hitPoints = hitPoints - hit;

        if(weakness.equals(Item.getItem())){
            hitPoints = 0;
            isDefeated = true;
        }
        isDefeated = false;

        if(hitPoints == 0 ){
            isDefeated = true;
        }

    }

    //Joseph
    public boolean isWeakTo(String itemName){
        if (itemName.equals(items.getItem())){
            isDefeated = true;
            return true;
        }

        return false;
    }
    //
    public void whenDefeated(){
        Monster monster = new Monster();
        if(isDefeated == true){
            System.out.println(monster + " has been defeated great job.\n Now continue your journey!");
        }
    }
    //Joseph
    public String getName() {
        return name;
    }
    //Joseph
    public String getDescription() {
        return description;
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

    public void setDefeated(boolean isDefeated){
        this.isDefeated = isDefeated;
    }


}
