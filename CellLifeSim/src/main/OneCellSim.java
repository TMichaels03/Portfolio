package main;

import java.util.Random;

public class OneCellSim
{
	private int[][] life;
	private int[][] nextLife;
	
	private int rows;
	private int cols;
	
	private int avePopulation;
	private int its;
	
	public OneCellSim(int rows, int cols)
	{
		this.rows = rows;
		this.cols = cols;
		life = new int[rows][cols];
		nextLife = new int[rows][cols];
		avePopulation = 0;
		its = 100;
	}
	
	public void run()
	{
		this.populate();
		this.print(0);
		this.copy(0);
		
		for(int i = 0; i < its; i++)
		{
			this.applyRules();
			System.out.printf("Iteration: %d%n", i + 1);
			this.print(0);
		}
		
		System.out.printf("Total Population: %d%nAverage Population: %d%n", avePopulation, avePopulation / its);
	}
	
	private void populate()
	{
		Random rnd = new Random(System.currentTimeMillis());
		int gen = (rows >= cols ? rows : cols) * 6;
		for(int i = 0; i < gen; i++)
		{
			int x = rnd.nextInt(rows);
			int y = rnd.nextInt(cols);
			life[x][y] = 1;
		}
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
			System.out.printf("Population: %d%n", this.getNum(1));
			avePopulation += this.getNum(1);
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
			System.out.printf("Population: %d%n", this.getNum(1));
			System.out.printf("%n%n");
		}
	}
	
	private boolean equals(int[][] b1, int[][] b2)
	{
		for(int y = 0; y < cols; y++)
		{
			for(int x = 0; x < rows; x++)
			{
				if(b1[x][y] != b2[x][y])
				{
					return false;
				}
			}
		}
		return true;
	}
	
	private synchronized void applyRules()
	{
		int neighbors = 0;
		
		for(int y = 0; y < cols; y++)
		{
			for(int x = 0; x < rows; x++)
			{
				neighbors = 0;
				try
				{
					if(life[x-1][y+1] == 1)
					{
						neighbors++;
					}
				}
				catch(Exception e) {}
				try
				{
					if(life[x][y+1] == 1)
					{
						neighbors++;
					}
				}
				catch(Exception e) {}
				try
				{
					if(life[x+1][y+1] == 1)
					{
						neighbors++;
					}
				}
				catch(Exception e) {}
				try
				{
					if(life[x-1][y] == 1)
					{
						neighbors++;
					}
				}
				catch(Exception e) {}
				try
				{
					if(life[x+1][y] == 1)
					{
						neighbors++;
					}
				}
				catch(Exception e) {}
				try
				{
					if(life[x-1][y-1] == 1)
					{
						neighbors++;
					}
				}
				catch(Exception e) {}
				try
				{
					if(life[x][y-1] == 1)
					{
						neighbors++;
					}
				}
				catch(Exception e) {}
				try
				{
					if(life[x+1][y-1] == 1)
					{
						neighbors++;
					}
				}
				catch(Exception e) {}
				
				
				// Generate next generation
				
				// Kill if less than 2 / greater than 3
				if(neighbors < 2 || neighbors > 3)
				{
					nextLife[x][y] = 0;
				}
				// Birth if = 3
				if(neighbors == 3)
				{
					nextLife[x][y] = 1;
				}
			}
		}
		if(equals(life, nextLife))
		{
			print(1);
			System.out.printf("Generations are equal, terminating iterations.%n");
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
