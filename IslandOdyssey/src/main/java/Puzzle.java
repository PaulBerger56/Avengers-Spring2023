
public abstract class Puzzle{
	
	protected String name, description, solution, type;
	protected int attempts, maxAttempts, roomID;
	protected Item item;
	
	//Bao
	Puzzle(String name, String description, String solution, int maxAttempts,int roomID){
		this.name = name;
		this.description = description;
		this.solution = solution;
		this.maxAttempts = maxAttempts;
		this.attempts = maxAttempts;
		this.roomID = roomID;
	}
	
	public abstract boolean check(String input);
	
	public String puzzleSolvedMessage(){
		return "You solved the puzzle!";
	}

	public String getName(){
		return name;
	}

	public String getDescription(){
		return description;
	}

	public String getSolution(){
		return solution;
	}

	public String getType(){
		return type;
	}

	public int getAttempts(){
		return attempts;
	}

	public int getMaxAttempts(){
		return maxAttempts;
	}
	
	public Item getItem(){
		return item;
	}
	
	public void setAttempts(int attempts){
		this.attempts = attempts;
	}
	
	
}
