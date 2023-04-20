import java.io.Serializable;

public abstract class Puzzle implements Serializable {
	
	protected String description, solution, type;
	protected int attempts, maxAttempts;
	protected Item item;
	
	//Bao
	Puzzle(String description, String solution, int maxAttempts){
		this.description = description;
		this.solution = solution;
		this.maxAttempts = maxAttempts;
		this.attempts = maxAttempts;
	}
	
	public String puzzleSolvedMessage() {
		return "You solved the puzzle!";
	}

	public String getDescription() {
		return description;
	}

	public String getSolution() {
		return solution;
	}
	
	public String getType() {
		return type;
	}
	
	public int getAttempts() {
		return attempts;
	}

	public int getMaxAttempts() {
		return maxAttempts;
	}
	
	public Item getItem() {
		return item;
	}
	
	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	
}
