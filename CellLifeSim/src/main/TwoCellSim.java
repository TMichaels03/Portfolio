package main;

import java.util.Random;

public class TwoCellSim
{
	private int[][] life;
	private int[][] nextLife;
	
	private int rows;
	private int cols;
	
	public TwoCellSim(int rows, int cols)
	{
		this.rows = rows;
		this.cols = cols;
		life = new int[rows][cols];
		nextLife = new int[rows][cols];
	}
	
	public void run()
	{
		this.populate();		
		System.out.printf("Base%n");	
		this.print(0);
		this.copy(0);		
		int its = 100;
		for(int i = 0; i < its; i++)
		{
			this.applyRules();
			System.out.printf("Iteration: %d%n", i + 1);
			this.print(0);
		}
	}
	
	private void populate()
	{
		Random rnd = new Random(System.currentTimeMillis());
		int gen = (rows >= cols ? rows : cols) * 4;
		for(int i = 0; i < gen; i++)
		{
			int x = rnd.nextInt(rows);
			int y = rnd.nextInt(cols);
			life[x][y] = 1;
			
			while(this.getNum(1) != this.getNum(2))
			{
				x = rnd.nextInt(rows);
				y = rnd.nextInt(cols);
				if(life[x][y] == 0)
				{
					life[x][y] = 2;
				}
			}
		}
		System.out.printf("Count of [1]: %d%nCount of [2]: %d%n%n", this.getNum(1), this.getNum(2));
	}
	
	private int getNum(int num)
	{
		int cnt = 0;
		for(int y = 0; y < cols; y++)
		{
			for(int x = 0; x < rows; x++)
			{
				if(life[x][y] == num)
				{
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	private void copy(int option)
	{
		if(option == 0)
		{
			for(int y = 0; y < cols; y++)
			{
				for(int x = 0; x < rows; x++)
				{
					nextLife[x][y] = life[x][y];
				}
			}
		}
		else if(option == 1)
		{
			for(int y = 0; y < cols; y++)
			{
				for(int x = 0; x < rows; x++)
				{
					life[x][y] = nextLife[x][y];
				}
			}
		}
	}
	
	private void print(int board)
	{
		if(board == 0)
		{
			for(int y = 0; y < cols; y++)
			{
				for(int x = 0; x < rows; x++)
				{
					System.out.printf("%d ", life[x][y]);
				}
				System.out.printf("%n");
			}
			System.out.printf("Population [1]: %d%nPopulation [2]: %d%n", this.getNum(1), this.getNum(2));
			System.out.printf("%n%n");
		}
		else if(board == 1)
		{
			for(int y = 0; y < cols; y++)
			{
				for(int x = 0; x < rows; x++)
				{
					System.out.printf("%d ", nextLife[x][y]);
				}
				System.out.printf("%n");
			}
			System.out.printf("%n%n");
		}
	}
	
	private synchronized void applyRules()
	{
		int neighborsOne = 0;
		int neighborsTwo = 0;
		
		for(int y = 0; y < cols; y++)
		{
			for(int x = 0; x < rows; x++)
			{
				neighborsOne = 0;
				neighborsTwo = 0;
				try
				{
					if(life[x-1][y+1] == 1)
					{
						neighborsOne++;
					}
					if(life[x-1][y+1] == 2)
					{
						neighborsTwo++;
					}
				}
				catch(Exception e) {}
				try
				{
					if(life[x][y+1] == 1)
					{
						neighborsOne++;
					}
					if(life[x][y+1] == 2)
					{
						neighborsTwo++;
					}
				}
				catch(Exception e) {}
				try
				{
					if(life[x+1][y+1] == 1)
					{
						neighborsOne++;
					}
					if(life[x+1][y+1] == 2)
					{
						neighborsTwo++;
					}
				}
				catch(Exception e) {}
				try
				{
					if(life[x-1][y] == 1)
					{
						neighborsOne++;
					}
					if(life[x-1][y] == 2)
					{
						neighborsTwo++;
					}
				}
				catch(Exception e) {}
				try
				{
					if(life[x+1][y] == 1)
					{
						neighborsOne++;
					}
					if(life[x+1][y] == 2)
					{
						neighborsTwo++;
					}
				}
				catch(Exception e) {}
				try
				{
					if(life[x-1][y-1] == 1)
					{
						neighborsOne++;
					}
					if(life[x-1][y-1] == 2)
					{
						neighborsTwo++;
					}
				}
				catch(Exception e) {}
				try
				{
					if(life[x][y-1] == 1)
					{
						neighborsOne++;
					}
					if(life[x][y-1] == 2)
					{
						neighborsTwo++;
					}
				}
				catch(Exception e) {}
				try
				{
					if(life[x+1][y-1] == 1)
					{
						neighborsOne++;
					}
					if(life[x+1][y-1] == 2)
					{
						neighborsTwo++;
					}
				}
				catch(Exception e) {}
				
				
				// Generate next generation
				if(life[x][y] == 1)
				{
					// Kill if less than 2 / greater than 3
					if(neighborsOne < 2 || neighborsOne > 3)
					{
						nextLife[x][y] = 0;
					}
					// Kill if near two 2
					if(neighborsTwo >= 2)
					{
						nextLife[x][y] = 0;
					}
				}
				if(life[x][y] == 2)
				{
					// Kill if less than 2 / greater than 3
					if(neighborsTwo < 2 || neighborsTwo > 3)
					{
						nextLife[x][y] = 0;
					}
					// Kill if near two 1
					if(neighborsOne >= 2)
					{
						nextLife[x][y] = 0;
					}
				}
				else if(life[x][y] == 0)
				{
					// Birth if = 3
					Random rnd = new Random(System.currentTimeMillis());
					int ch = rnd.nextInt(1) + 1;
					if(ch == 1)
					{
						if(neighborsOne == 3)
						{
							nextLife[x][y] = 1;
						}
					}
					else if(ch == 2)
					{
						if(neighborsTwo == 3)
						{
							nextLife[x][y] = 2;
						}
					}
					
				}
			}
		}
		if(life.equals(nextLife))
		{
			System.out.printf("Iterations terminated early.%n");
			System.exit(0);
		}
		else
		{
			this.copy(1);
			try
			{
				Thread.sleep(200);
			}
			catch (InterruptedException e) {}
		}
	}
}
