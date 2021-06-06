package main;

public class Main
{

	public static void main(String[] args)
	{
		int ch = 0;
		
		
		if(ch == 0)
		{
			OneCellSim ocs = new OneCellSim(40, 40);
			ocs.run();
		}
		else if(ch == 1)
		{
			TwoCellSim tcs = new TwoCellSim(40, 40);
			tcs.run();
		}
	}
}
