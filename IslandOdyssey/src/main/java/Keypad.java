
public class Keypad extends Puzzle{

	Keypad(String type, String name, String description, String solution, int maxAttempts, int roomID){
		super(name, description, solution, maxAttempts, roomID);
		this.type = "Keypad";
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
