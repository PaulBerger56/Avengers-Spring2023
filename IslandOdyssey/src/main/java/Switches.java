
public class Switches extends Puzzle{
	
	int[] switches = {0, 0, 0, 0, 0};

	Switches(String name, String description, String solution, int maxAttempts){
		super(name, description, solution, maxAttempts);
	}
	
	public void flip(String flipNum)
	{
			if(switches[Integer.parseInt(flipNum) - 1] == 0)
			{
				switches[Integer.parseInt(flipNum)] = 1;
			}
			else
			{
				switches[Integer.parseInt(flipNum)] = 0;
			}
	}
	
	public String printSwitches()
	{
		String temp = "";
		
		for(int i: switches)
		{
			temp += switches[i - 1];
		}
		
		return temp;
	}
	
	public boolean check() 
	{
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
