import java.io.Serializable;

public class Keypad extends Puzzle implements Serializable {

	Keypad(String description, String solution, int maxAttempts){
		super(description, solution, maxAttempts);
		type = "Keypad";
	}
	
	public boolean check(String input){
		if(input.toLowerCase().equals(solution)){
			
			return true;
		}
		else{
			attempts--;
			return false;
		}
	}
}
