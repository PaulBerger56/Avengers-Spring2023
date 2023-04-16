
public abstract class Puzzle{
	
	protected String name, description, solution, type;
	protected int attempts, maxAttempts;
	
	Puzzle(String name, String description, String solution, int maxAttempts){
		this.name = name;
		this.description = description;
		this.solution = solution;
		this.maxAttempts = maxAttempts;
		this.attempts = maxAttempts;
	}
	
	public String puzzleSolvedMessage()
	{
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
	
	
}
