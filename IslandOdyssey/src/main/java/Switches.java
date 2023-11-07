import java.io.Serializable;

public class Switches extends Puzzle implements Serializable {
	
	int[] switches = {0, 0, 0, 0, 0};

	//Bao
	Switches(String description, String solution, int maxAttempts){
		super(description, solution, maxAttempts);
		type = "Switches";
	}
	
	public void flip(String flipNum){
			if(switches[Integer.parseInt(flipNum) - 1] == 0)
			{
				switches[Integer.parseInt(flipNum) - 1] = 1;
			}
			else
			{
				switches[Integer.parseInt(flipNum) - 1] = 0;
			}
	}
	
	public int[] getSwitches(){
		return switches;
	}
	
	public boolean check(){
		String temp = "";
		
		for(int i= 0; i < switches.length; i++)
		{
			temp += switches[i];
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
