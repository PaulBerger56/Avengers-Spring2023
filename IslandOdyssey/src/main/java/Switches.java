
public class Switches extends Puzzle{
	
	int[] switches = {0, 0, 0, 0, 0};

	//Bao
	Switches(String type, String name, String description, String solution, int maxAttempts, int roomID){
		super(name, description, solution, maxAttempts, roomID);
		this.type = "Switches";
	}
	
	public void flip(String flipNum){
			if(switches[Integer.parseInt(flipNum) - 1] == 0)
			{
				switches[Integer.parseInt(flipNum)] = 1;
			}
			else
			{
				switches[Integer.parseInt(flipNum)] = 0;
			}
	}
	
	public String printSwitches(){
		String temp = "";
		
		for(int i: switches)
		{
			temp += switches[i - 1];
		}
		
		return temp;
	}
	
	public boolean check(String input){
		String temp = "";
		
		for(int i: switches)
		{
			temp += switches[i - 1];
		}
		
		if(temp.equals(solution))
		{
			return true;
		}
		else
		{
			attempts--;
			return false;
		}
	}

}
