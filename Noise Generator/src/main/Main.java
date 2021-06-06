package main;

import java.util.Random;

public class Main
{
	public static void main(String[] args)
	{
		Random r = new Random(System.currentTimeMillis());
		
		
		int s = 30;
		
		int rows = s;
		int cols = s;
		
		double[][] testMap = new double[cols][rows];
		
		for(int y = 0; y < cols; y++)
		{
			for(int x = 0; x < rows; x++)
			{
				testMap[y][x] = 0;
			}
		}
		
		//printMap(testMap, cols, rows);
		
		// Generate initial land plot
		int randX = r.nextInt(rows);
		int randY = r.nextInt(cols);
		testMap[randY][randX] = 1;
		
		//printMap(testMap, cols, rows);
		
		// Generate plots of land
		int plots = 1100;
		int initX = 0;
		int initY = 0;
		for(int y = 0; y < cols; y++)
		{
			for(int x = 0; x < rows; x++)
			{
				if(testMap[y][x] == 1)
				{
					initX = x;
					initY = y;
				}
			}
		}
		
		int xx = initX;
		int yy = initY;
		for(int i = 0; i < plots; i++)
		{
			//System.out.printf("Iteration: %d%n", i);
			
			int op = r.nextInt(4);
			
			if(op == 0)
			{
				yy++;
				
				if(yy >= cols)
				{
					yy = cols - 1;
				}
								
				if(testMap[yy][xx] == 0.0)
				{testMap[yy][xx] = 1.0;}
				else
				{testMap[yy][xx] += 0.1;}
			}
			else if(op == 1)
			{
				xx++;
				
				if(xx >= rows)
				{
					xx = rows - 1;
				}
				
				if(testMap[yy][xx] == 0.0)
				{testMap[yy][xx] = 1.0;}
				else
				{testMap[yy][xx] += 0.1;}
			}
			else if(op == 2)
			{
				yy--;
				
				if(yy < 0)
				{
					yy = 0;
				}
				
				if(testMap[yy][xx] == 0.0)
				{testMap[yy][xx] = 1.0;}
				else
				{testMap[yy][xx] += 0.1;}
			}
			else if(op == 3)
			{
				xx--;
				
				if(xx < 0)
				{
					xx = 0;
				}
				
				if(testMap[yy][xx] == 0.0)
				{testMap[yy][xx] = 1.0;}
				else
				{testMap[yy][xx] += 0.1;}
			}
		}
		
		printMap(convertMap(testMap, cols, rows), cols, rows);
		
	}	
		
	
	public static double genNoise(Random r)
	{
		double minAmp = 0.0;
		double maxAmp = 1.0;
		
		double res = minAmp + (maxAmp - minAmp) * r.nextDouble();
		return res;
	}
	
	public static void printMap(double[][] map, int cols, int rows)
	{
		for(int y = 0; y < cols; y++)
		{
			for(int x = 0; x < rows; x++)
			{
				System.out.printf("%.2f ", map[y][x]);
			}
			System.out.printf("%n");
		}
		System.out.printf("%n%n%n");
	}
	
	public static void printMap(char[][] map, int cols, int rows)
	{
		for(int y = 0; y < cols; y++)
		{
			for(int x = 0; x < rows; x++)
			{
				System.out.printf("%c ", map[y][x]);
			}
			System.out.printf("%n");
		}
		System.out.printf("%n%n%n");
	}
	
	public static char[][] convertMap(double[][] map, int cols, int rows)
	{
		char[][] newMap = new char[cols][rows];
		
		for(int y = 0; y < cols; y++)
		{
			for(int x = 0; x < rows; x++)
			{
				// 0 = '~'
				if(map[y][x] == 0.0)
				{
					newMap[y][x] = '~';
				}
				// 1 = '#'
				else if(map[y][x] == 1.0)
				{
					newMap[y][x] = '#';
				}
				else if(map[y][x] > 1.0 && map[y][x] <= 1.3)
				{
					newMap[y][x] = '&';
				}
				else if(map[y][x] > 1.3 && map[y][x] <= 1.6)
				{
					newMap[y][x] = '/';
				}
				else if(map[y][x] > 1.6 && map[y][x] <= 1.7)
				{
					newMap[y][x] = '$';
				}
				else if(map[y][x] > 1.7)
				{
					newMap[y][x] = '^';
				}
				else
				{
					newMap[y][x] = ' ';
				}
			}	
		}
		
		return newMap;
	}
}
